package com.fenglianfinance.bill.job.config;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import com.fenglianfinance.bill.domain.BackLogItem;
import com.fenglianfinance.bill.domain.PurchaseOrder;
import com.fenglianfinance.bill.domain.WithFundingInfos;
import com.fenglianfinance.bill.gateway.common.GatewayService;
import com.fenglianfinance.bill.job.listener.UserRepaymentJobExecutionListener;
import com.fenglianfinance.bill.job.processor.DailyPurchaseOrderItemProcessor;
import com.fenglianfinance.bill.job.processor.ExpirationBillProcessor;
import com.fenglianfinance.bill.job.processor.ExpiredWithFundingInfosItemProcessor;
import com.fenglianfinance.bill.job.processor.SinglePurchaseOrderItemProcessor;
import com.fenglianfinance.bill.job.processor.UserRepaymentProcessor;
import com.fenglianfinance.bill.job.writer.UserRepaymentWriter;
import com.fenglianfinance.bill.repository.BackLogItemRepository;
import com.fenglianfinance.bill.repository.OrderRepository;
import com.fenglianfinance.bill.repository.ProductRepository;
import com.fenglianfinance.bill.repository.TransactionLogRepository;
import com.fenglianfinance.bill.repository.WithFundingInfosRepository;

@Configuration
public class JobConfiguration {

    private static final Logger log = LoggerFactory.getLogger(JobConfiguration.class);

    // @Inject
    // private DataSource dataSource;

    @Inject
    private PlatformTransactionManager transactionManager;

    @Inject
    private JobBuilderFactory jobBuilderFactory;
    @Inject
    private StepBuilderFactory stepBuilderFactory;
    @Inject
    private EntityManagerFactory entityManagerFactory;

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private BackLogItemRepository backLogItemRepository;

    @Inject
    private TransactionLogRepository transactionLogRepository;

    @Inject
    private RedisTemplate<Object, Object> redisTemplate;

    @Inject
    private GatewayService gatewayService;
    
    @Inject
    private ProductRepository productRepository;
    
    @Inject
    private WithFundingInfosRepository withFundingInfosRepository;

    @Bean
    public Job dailyInterestJob() {
        return jobBuilderFactory.get("dailyInterestJob").start(dailyInterestStep()).build();
    }

    @Bean
    public Step dailyInterestStep() {
        return stepBuilderFactory.get("dailyInterestStep")//
                .allowStartIfComplete(true)//
                .transactionManager(transactionManager)//
                .<PurchaseOrder, PurchaseOrder>chunk(100)//
                .reader(dailyInterestReader())//
                .processor(dailyInterestProcessor())//
                .writer(dailyInterestWriter())//
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<PurchaseOrder> dailyInterestReader() {
        RepositoryItemReader<PurchaseOrder> reader = new RepositoryItemReader<PurchaseOrder>();
        reader.setPageSize(20);
        reader.setMethodName("findDemandPaidPurchaseOrder");
        reader.setRepository(orderRepository);
        Map<String, Sort.Direction> sorts = new HashMap<String, Sort.Direction>();
        sorts.put("id", Sort.Direction.ASC);
        reader.setSort(sorts);
        return reader;
    }

    @Bean
    public ItemProcessor<PurchaseOrder, PurchaseOrder> dailyInterestProcessor() {
        return new DailyPurchaseOrderItemProcessor();
    }

    @Bean
    @StepScope
    public ItemWriter<PurchaseOrder> dailyInterestWriter() {

        RepositoryItemWriter<PurchaseOrder> writer = new RepositoryItemWriter<PurchaseOrder>();
        writer.setMethodName("saveAndFlush");
        writer.setRepository(orderRepository);
        return writer;

    }

    @Bean
    public Job expirationBillJob() {
        return jobBuilderFactory.get("expirationBillJob").start(expirationBillStep()).build();
    }

    @Bean
    public Step expirationBillStep() {
        return stepBuilderFactory.get("expirationBillStep")//
                .allowStartIfComplete(true)//
                .transactionManager(transactionManager)//
                .<BackLogItem, BackLogItem>chunk(100)//
                .reader(expirationBillReader())//
                .processor(expirationBillProcessor())//
                .writer(expirationBillWriter())//
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<BackLogItem> expirationBillReader() {
        RepositoryItemReader<BackLogItem> reader = new RepositoryItemReader<BackLogItem>();
        reader.setPageSize(20);
        reader.setMethodName("findByExpirationBill");
        reader.setRepository(backLogItemRepository);
        Map<String, Sort.Direction> sorts = new HashMap<String, Sort.Direction>();
        sorts.put("id", Sort.Direction.ASC);
        reader.setSort(sorts);

        List<LocalDate> arguments = new ArrayList<LocalDate>();
        arguments.add(LocalDate.now().plusDays(-1));
        reader.setArguments(arguments);

        return reader;
    }

    @Bean
    public ItemProcessor<BackLogItem, BackLogItem> expirationBillProcessor() {
        return new ExpirationBillProcessor(redisTemplate);
    }

    @Bean
    @StepScope
    public ItemWriter<BackLogItem> expirationBillWriter() {
        RepositoryItemWriter<BackLogItem> writer = new RepositoryItemWriter<BackLogItem>();
        writer.setMethodName("saveAndFlush");
        writer.setRepository(backLogItemRepository);
        return writer;
    }

    @Bean
    public Job userRepaymentJob() {
        return jobBuilderFactory.get("userRepaymentJob")//
                .start(userRepaymentStep())//
                .listener(userRepaymentJobExecutionListener())//
                .build();
    }

    @Bean
    public Step userRepaymentStep() {
        return stepBuilderFactory.get("userRepaymentStep")//
                .allowStartIfComplete(true)//
                .transactionManager(transactionManager)//
                .<PurchaseOrder, PurchaseOrder>chunk(100)//
                .reader(userRepaymentReader())//
                .processor(userRepaymentOrderProcessor())//
                .writer(userRepaymentWriter())//
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<PurchaseOrder> userRepaymentReader() {
        RepositoryItemReader<PurchaseOrder> reader = new RepositoryItemReader<PurchaseOrder>();
        reader.setPageSize(20);
        reader.setMethodName("findNotDemandPaid");
        reader.setRepository(orderRepository);
        Map<String, Sort.Direction> sorts = new HashMap<String, Sort.Direction>();
        sorts.put("id", Sort.Direction.ASC);
        reader.setSort(sorts);

        List<LocalDate> arguments = new ArrayList<LocalDate>();
        arguments.add(LocalDate.now());
        reader.setArguments(arguments);

        return reader;
    }

    @Bean
    public ItemProcessor<PurchaseOrder, PurchaseOrder> userRepaymentOrderProcessor() {
        return new UserRepaymentProcessor(gatewayService);
    }

    @Bean
    @StepScope
    public ItemWriter<PurchaseOrder> userRepaymentWriter() {
        UserRepaymentWriter writer = new UserRepaymentWriter(gatewayService);
        // RepositoryItemWriter<PurchaseOrder> writer = new RepositoryItemWriter<PurchaseOrder>();
        writer.setMethodName("saveAndFlush");
        writer.setRepository(orderRepository);
        return writer;
    }
    
    @Bean
    public JobExecutionListener userRepaymentJobExecutionListener(){
        return new UserRepaymentJobExecutionListener(productRepository);
    }

    @Bean
    public Job singleInterestJob() {
        return jobBuilderFactory.get("singleInterestJob").start(singleInterestStep()).build();
    }

    @Bean
    public Step singleInterestStep() {
        return stepBuilderFactory.get("singleInterest")//
                .allowStartIfComplete(true)//
                .transactionManager(transactionManager)//
                .<PurchaseOrder, PurchaseOrder>chunk(100)//
                .reader(singleInterestReader())//
                .processor(singleInterestProcessor())//
                .writer(singleInterestWriter())//
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<PurchaseOrder> singleInterestReader() {
        RepositoryItemReader<PurchaseOrder> reader = new RepositoryItemReader<PurchaseOrder>();
        reader.setPageSize(20);
        reader.setMethodName("findNewbieOrHotPaidPurchaseOrder");
        reader.setRepository(orderRepository);
        Map<String, Sort.Direction> sorts = new HashMap<String, Sort.Direction>();
        sorts.put("id", Sort.Direction.ASC);
        reader.setSort(sorts);
        return reader;
    }

    @Bean
    public ItemProcessor<PurchaseOrder, PurchaseOrder> singleInterestProcessor() {
        return new SinglePurchaseOrderItemProcessor();
    }

    @Bean
    @StepScope
    public ItemWriter<PurchaseOrder> singleInterestWriter() {
        RepositoryItemWriter<PurchaseOrder> writer = new RepositoryItemWriter<PurchaseOrder>();
        writer.setMethodName("saveAndFlush");
        writer.setRepository(orderRepository);
        return writer;
    }
    
    @Bean
    public Job expiredWithFundingInfosJob() {
        return jobBuilderFactory.get("expiredWithFundingInfosJob").start(expiredWithFundingInfosStep()).build();
    }

    @Bean
    public Step expiredWithFundingInfosStep() {
        return stepBuilderFactory.get("expiredWithFundingInfosStep")//
                .allowStartIfComplete(true)//
                .transactionManager(transactionManager)//
                .<WithFundingInfos, WithFundingInfos>chunk(100)//
                .reader(expiredWithFundingInfosReader())//
                .processor(expiredWithFundingInfosProcessor())//
                .writer(expiredWithFundingInfosWriter())//
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<WithFundingInfos> expiredWithFundingInfosReader() {
        RepositoryItemReader<WithFundingInfos> reader = new RepositoryItemReader<WithFundingInfos>();
        reader.setPageSize(20);
        reader.setMethodName("findUnpublishedWithFundingInfos");
        reader.setRepository(withFundingInfosRepository);
        Map<String, Sort.Direction> sorts = new HashMap<String, Sort.Direction>();
        sorts.put("id", Sort.Direction.ASC);
        reader.setSort(sorts);
        return reader;
    }

    @Bean
    public ItemProcessor<WithFundingInfos, WithFundingInfos> expiredWithFundingInfosProcessor() {
        return new ExpiredWithFundingInfosItemProcessor();
    }

    @Bean
    @StepScope
    public ItemWriter<WithFundingInfos> expiredWithFundingInfosWriter() {
        RepositoryItemWriter<WithFundingInfos> writer = new RepositoryItemWriter<WithFundingInfos>();
        writer.setMethodName("saveAndFlush");
        writer.setRepository(withFundingInfosRepository);
        return writer;

    }

    // @Bean
    // public JobLauncher launcher() throws Exception {
    // SimpleJobLauncher bean = new SimpleJobLauncher();
    // bean.setJobRepository(jobRepository());
    // bean.setTaskExecutor(new SimpleAsyncTaskExecutor());
    // return bean;
    // }
    //
    // @Bean
    // public JobRepository jobRepository() throws Exception {
    // JobRepositoryFactoryBean bean = new JobRepositoryFactoryBean();
    // bean.setDataSource(dataSource);
    // bean.setIsolationLevelForCreate("ISOLATION_DEFAULT");
    // bean.setTransactionManager(transactionManager);
    // return bean.getObject();
    // }
    //
    // @Override
    // public JobRepository getJobRepository() throws Exception {
    // return jobRepository();
    // }
    //
    // @Override
    // public PlatformTransactionManager getTransactionManager() throws Exception {
    // return this.transactionManager;
    // }

    // @PostConstruct
    // public void initialize() throws Exception {
    // this.transactionManager = createTransactionManager();
    // this.jobRepository = createJobRepository();
    // this.jobLauncher = createJobLauncher();
    // }
    //
    // private JobLauncher createJobLauncher() throws Exception {
    // SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
    // jobLauncher.setJobRepository(getJobRepository());
    // jobLauncher.afterPropertiesSet();
    // return jobLauncher;
    // }
    //
    // protected PlatformTransactionManager createTransactionManager() {
    // if (this.entityManagerFactory != null) {
    // return new JpaTransactionManager(this.entityManagerFactory);
    // }
    // return new DataSourceTransactionManager(this.dataSource);
    // }
    //
    // protected JobRepository createJobRepository() throws Exception {
    // JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
    // factory.setDataSource(this.dataSource);
    // factory.setTransactionManager(getTransactionManager());
    // factory.afterPropertiesSet();
    // return (JobRepository) factory.getObject();
    // }
    //
    // @Override
    // public JobLauncher getJobLauncher() throws Exception {
    // return this.jobLauncher;
    // }
    //
    // @Override
    // public JobExplorer getJobExplorer() throws Exception {
    // return null;
    // }
    //
    // public void setJobRepository(JobRepository jobRepository) {
    // this.jobRepository = jobRepository;
    // }
    //
    // public void setJobLauncher(JobLauncher jobLauncher) {
    // this.jobLauncher = jobLauncher;
    // }

    // @Bean
    // public JobExplorer jobExplorer() throws Exception {
    // JobExplorerFactoryBean bean = new JobExplorerFactoryBean();
    // bean.setDataSource(dataSource);
    // bean.afterPropertiesSet();
    // return (JobExplorer) bean.getObject();
    // }
    //
    // @Bean
    // public JobRegistry jobRegistry() {
    // return new MapJobRegistry();
    // }
    //
    // @Bean
    // public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor() {
    //
    // JobRegistryBeanPostProcessor bean = new JobRegistryBeanPostProcessor();
    // bean.setJobRegistry(jobRegistry());
    // try {
    // bean.afterPropertiesSet();
    // } catch (Exception e) {
    // }
    // return bean;
    // }
    //
    // @Bean
    // public SimpleJobOperator jobOperator() throws Exception {
    // SimpleJobOperator bean = new SimpleJobOperator();
    // bean.setJobExplorer(jobExplorer());
    // bean.setJobRepository(jobRepository());
    // bean.setJobLauncher(launcher());
    // bean.setJobRegistry(jobRegistry());
    // bean.afterPropertiesSet();
    // return bean;
    // }
    // @Autowired(required = false)
    // @Override
    // public void setDataSource(DataSource dataSource) {
    // this.dataSource = dataSource;
    // }
    //
    // @Override
    // public PlatformTransactionManager getTransactionManager() {
    // return this.transactionManager;
    // }
    //
    // @Override
    // @Bean
    // public JobExplorer getJobExplorer() {
    // return super.getJobExplorer(); //To change body of generated methods, choose Tools |
    // Templates.
    // }
    //
    // @Override
    // @Bean
    // public JobLauncher getJobLauncher() {
    // return super.getJobLauncher(); //To change body of generated methods, choose Tools |
    // Templates.
    // }
    //
    // @Override
    // @Bean
    // public JobRepository getJobRepository() {
    // return super.getJobRepository(); //To change body of generated methods, choose Tools |
    // Templates.
    // }

}
