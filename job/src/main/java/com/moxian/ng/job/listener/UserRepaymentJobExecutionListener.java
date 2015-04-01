package com.moxian.ng.job.listener;

import java.time.LocalDate;
import java.util.List;

import com.moxian.ng.repository.ProductRepository;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import com.moxian.ng.domain.Product;

/**
 * Created by admin on 2015/2/6.
 */
public class UserRepaymentJobExecutionListener implements JobExecutionListener {

    private ProductRepository productRepository;

    public UserRepaymentJobExecutionListener(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        List<Long> ids = productRepository.findToCompletedProduct(LocalDate.now());

        for (Long id : ids) {
            Product product = productRepository.findOne(id);
            product.setStatus(Product.Status.COMPLETED);
            productRepository.save(product);
        }

    }

}
