package com.fenglianfinance.bill.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.fenglianfinance.bill.DTOUtils;
import com.fenglianfinance.bill.domain.BackLogAmount;
import com.fenglianfinance.bill.domain.BackLogAmountTotal;
import com.fenglianfinance.bill.domain.BackLogItem;
import com.fenglianfinance.bill.domain.Bill;
import com.fenglianfinance.bill.domain.Post;
import com.fenglianfinance.bill.domain.Product;
import com.fenglianfinance.bill.domain.ProductCriteria;
import com.fenglianfinance.bill.domain.WithFundingInfos;
import com.fenglianfinance.bill.exception.ProductNameExistedException;
import com.fenglianfinance.bill.exception.ProductNotOnSaleException;
import com.fenglianfinance.bill.exception.ProductPromotedExistedException;
import com.fenglianfinance.bill.exception.ResourceNotFoundException;
import com.fenglianfinance.bill.model.ProductDetails;
import com.fenglianfinance.bill.model.ProductForm;
import com.fenglianfinance.bill.model.SimpleProductDetails;
import com.fenglianfinance.bill.repository.BillRepository;
import com.fenglianfinance.bill.repository.PostRepository;
import com.fenglianfinance.bill.repository.ProductRepository;
import com.fenglianfinance.bill.repository.ProductSpecifications;
import com.fenglianfinance.bill.repository.WithFundingInfosRepository;

@Service
public class ProductService {

    private final static Logger log = LoggerFactory.getLogger(ProductService.class);

    private static final String BACKLOGTOTAL_SN_CACHE = "BL:SN:BACKLOGTOTAL";

    @Inject
    private ProductRepository productRepository;

    @Inject
    private PostRepository postRepository;

	@Inject
	private BillRepository billRepository;
	
	@Inject
	private WithFundingInfosRepository withFundingInfosRepository;

    @Inject
    private BackLogService backLogService;

    @Inject
    private RedisTemplate<Object, Object> redisTemplate;

    @Transactional
    public ProductDetails saveProduct(ProductForm form) {

        if (log.isDebugEnabled()) {
            log.debug("saving product@" + form);
        }
        if (productRepository.findByName(form.getName()) != null) {
            throw new ProductNameExistedException(form.getName());
        }
        Product product = DTOUtils.map(form, Product.class);
        product.setStatus(Product.Status.FOR_SALE);

        Post post = postRepository.findOne(form.getLicensefk().getId());
        if (null == post) {
            throw new ResourceNotFoundException(" @@ post can't find by post :" + form.getLicensefk().getId());
        }
        product.setLicense(post);

        if (form.getBackLogItemId() != null) {
            Bill bill = billRepository.findOne(form.getBackLogItemId());
            if (null == bill) {
                throw new ResourceNotFoundException(" @@ bill can't find by bill :" + form.getBackLogItemId());
            }
            product.setBill(bill);
            product.setEnterprise(bill.getEnterprise());
        }

        product.setId(null);

        Product saved = productRepository.save(product);

        this.backLogService.updateBackLogItemStatus(form.getBackLogItemId(), BackLogItem.Status.ASSIGNED);

        return DTOUtils.map(saved, ProductDetails.class);
    }

    private void setAmountToRedis(BackLogAmountTotal backLogAmountTotalRedis, Product.Type type, BigDecimal amount) {

        //BigDecimal totalBillAmount = backLogAmountTotalRedis.getTotalBillAmount();
        BigDecimal totalFinancingAmount = backLogAmountTotalRedis.getTotalFinancingAmount();

        HashMap<String, BackLogAmount> backLogAmountMap = backLogAmountTotalRedis.getBackLogAmountMap();

        String backLogType = convertProductTypeToBacklogItemType(type).toString();

        BackLogAmount currentBacklogAmount = backLogAmountMap.get(backLogType);

        if (currentBacklogAmount == null) {
            currentBacklogAmount = new BackLogAmount();
        }

        //totalBillAmount = totalBillAmount.add(backLogItem.getBill().getDenomination());
        totalFinancingAmount = totalFinancingAmount.add(amount);

        currentBacklogAmount.setFinancingAmount(currentBacklogAmount.getFinancingAmount().add(amount));

        backLogAmountMap.put(backLogType, currentBacklogAmount);

        backLogAmountTotalRedis.setTotalFinancingAmount(totalFinancingAmount);
        backLogAmountTotalRedis.setBackLogAmountMap(backLogAmountMap);

        redisTemplate.opsForValue().set(BACKLOGTOTAL_SN_CACHE, backLogAmountTotalRedis);
    }

    public Page<ProductDetails> findProductsByKeyword(String name, boolean active, Product.Type type,
            Product.Status status, Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("search product by name@" + name + ", active @" + active + ", type @" + type + ", status @"
                    + status + ", page @" + page);
        }

        Page<Product> products = productRepository.findAll(
                ProductSpecifications.findProductByName(name, active, type, status), page);

        if (log.isDebugEnabled()) {
            log.debug("get products size @" + products.getTotalElements());
        }

        return DTOUtils.mapPage(products, ProductDetails.class);
    }

    @Transactional
    public void updateProduct(Long id, ProductForm form) {

        Assert.notNull(id, "Product id can not be null");
        if (log.isDebugEnabled()) {
            log.debug("find Product by id @" + id);
        }

        Product product = productRepository.findOne(id);

        if (product == null) {
            throw new ResourceNotFoundException(id);
        }

        Product compareToproduct = productRepository.findByName(form.getName());

        if (compareToproduct != null && !compareToproduct.getName().equals(product.getName())) {
            throw new ProductNameExistedException(form.getName());
        }

        DTOUtils.mapTo(form, product);
        product.setId(id);

        Post post = postRepository.findOne(form.getLicensefk().getId());
        if (null == post) {
            throw new ResourceNotFoundException(" @@ post can't find by post :" + form.getLicensefk().getId());
        }
        product.setLicense(post);

        //      if (form.getBillfk().getId() != null) {
        if (form.getBackLogItemId() != null) {
            //          Bill bill = billRepository.findOne(form.getBillfk().getId());
            Bill bill = billRepository.findOne(form.getBackLogItemId());
            if (null == bill) {
                throw new ResourceNotFoundException(" @@ bill can't find by bill :" + form.getBackLogItemId());
                //              throw new ResourceNotFoundException(" @@ bill can't find by bill :" + form.getBillfk().getId());
            }
            product.setBill(bill);
        }
        Product saved = productRepository.save(product);

        this.backLogService.updateBackLogItemStatus(form.getBackLogItemId(), BackLogItem.Status.ASSIGNED);

        if (log.isDebugEnabled()) {
            log.debug("updated product @" + saved);
        }

    }

    @Transactional
    public void deactivateProduct(Long id) {

        Assert.notNull(id, "Product id can not be null");

        productRepository.deactivate(id);

        if (log.isDebugEnabled()) {
            log.debug("deactivate Product @" + id);
        }
    }

    @Transactional
    public void updateStatus(Long id, String statusString) {

        Assert.notNull(id, "Product id can not be null");
        if (log.isDebugEnabled()) {
            log.debug("find Product by id @" + id);
        }

        Product product = productRepository.findOne(id);

        if (product == null) {
            throw new ResourceNotFoundException(id);
        }

        product.setStatus(Product.Status.valueOf(statusString));

        BackLogAmountTotal backLogAmountTotalRedis = getAmountFromRedis();

        setAmountToRedis(backLogAmountTotalRedis, product.getType(), product.getTotalAmount());

        Product saved = productRepository.save(product);

        if (log.isDebugEnabled()) {
            log.debug("updated product @" + saved);
        }
    }

    public ProductDetails findProductById(Long id) {
        Assert.notNull(id, "Product id can not be null");
        if (log.isDebugEnabled()) {
            log.debug("find Product by id @" + id);
        }

        Product product = productRepository.findOne(id);

        if (product == null) {
            throw new ResourceNotFoundException(id);
        }

        return DTOUtils.map(product, ProductDetails.class);
    }

    public Page<SimpleProductDetails> findAcitveProductsByType(Product.Type type, Pageable page) {
        Assert.notNull(type, "Product name can not be null");

        if (log.isDebugEnabled()) {
            log.debug("find active productes by type@" + type);
        }

        Page<Product> products = productRepository.findByActiveIsTrueAndType(type, page);

        return DTOUtils.mapPage(products, SimpleProductDetails.class);
    }

    public Page<SimpleProductDetails> findAcitveProducts(Pageable page) {

        if (log.isDebugEnabled()) {
            log.debug("find active productes@");
        }

        Page<Product> products = productRepository.findByActiveIsTrue(page);

        return DTOUtils.mapPage(products, SimpleProductDetails.class);
    }

    public Page<ProductDetails> findProducts(ProductCriteria criteria, Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("findProducts by @start @" + criteria + ", page@" + page);
        }

        Page<Product> products = productRepository.findAll(ProductSpecifications.searchProducts(criteria), page);

        if (log.isDebugEnabled()) {
            log.debug("all products @" + products.getTotalElements());
        }

        return DTOUtils.mapPage(products, ProductDetails.class);
    }

    public Boolean findProductByDemand() {
        Product product = productRepository.findProductByDemand();
        return product == null ? true : false;
    }

    public SimpleProductDetails getProductsByPromoted() {

        if (log.isDebugEnabled()) {
            log.debug("find Product by Promoted @" + true);
        }

        Product product = productRepository.findByPromoted();

        if (product == null) {
            throw new ResourceNotFoundException("not found");
        }

        return DTOUtils.map(product, SimpleProductDetails.class);
    }

    @Transactional
    public void updatePromoted(Long id) {

        Assert.notNull(id, "Product id can not be null");
        if (log.isDebugEnabled()) {
            log.debug("find Product by id @" + id);
        }

        Product product = productRepository.findOne(id);

        if (product == null) {
            throw new ResourceNotFoundException(id);
        }

        LocalDateTime now = LocalDateTime.now();

        if (now.isBefore(product.getOnSaleDate())) {
            throw new ProductNotOnSaleException(id);
        }

        Product pro = productRepository.findByPromoted();

        if (pro != null) {
            throw new ProductPromotedExistedException(pro.getName());
        }

        product.setPromoted(true);

        Product saved = productRepository.save(product);

        if (log.isDebugEnabled()) {
            log.debug("updated product @" + saved);
        }
    }

    @Transactional
    public void updateUnshelve(Long id) {

        Assert.notNull(id, "Product id can not be null");
        if (log.isDebugEnabled()) {
            log.debug("find Product by id @" + id);
        }

        Product product = productRepository.findOne(id);

        if (product == null) {
            throw new ResourceNotFoundException(id);
        }

        //        Product pro = productRepository.findByPromoted();
        //
        //        if (pro != null) {
        //            throw new ProductPromotedExistedException(pro.getName());
        //        }
        product.setPromoted(false);

        Product saved = productRepository.save(product);

        if (log.isDebugEnabled()) {
            log.debug("updated product @" + saved);
        }
    }

    private BackLogItem.Type convertProductTypeToBacklogItemType(Product.Type type) {

        BackLogItem.Type itemType = null;

        switch (type) {
            case NEWBIE:
                itemType = BackLogItem.Type.SINGLE;
                break;
            case HOT:
                itemType = BackLogItem.Type.SINGLE;
                break;
            case DEMAND:
                itemType = BackLogItem.Type.DEMAND;
                break;
            case FIXED:
                itemType = BackLogItem.Type.FIXED;
                break;
        }

        return itemType;

    }

    private BackLogAmountTotal getAmountFromRedis() {
        BackLogAmountTotal backLogAmountTotalRedis = (BackLogAmountTotal) redisTemplate.opsForValue().get(
                BACKLOGTOTAL_SN_CACHE);

        if (backLogAmountTotalRedis == null) {
            backLogAmountTotalRedis = new BackLogAmountTotal();
        }

        return backLogAmountTotalRedis;
    }

	/**
	 * 
	 * @param form
	 * @return
	 * @author wangli@flf77.com
	 * @date 
	 */
	@Transactional
	public ProductDetails saveProduct4WithFunding(ProductForm form) {
		if (log.isDebugEnabled()) {
			log.debug("saving product for WithFunding@" + form);
		}
		if (productRepository.findByName(form.getName()) != null) {
			throw new ProductNameExistedException(form.getName());
		}

		WithFundingInfos withFundingInfos = withFundingInfosRepository
				.findOne(form.getWithFundingInfosId());

		if (null != withFundingInfos
				&& withFundingInfos.getStatus().equals(
						WithFundingInfos.Status.UNPUBLISHED)) {

			Product product = DTOUtils.map(form, Product.class);

			Post post = postRepository.findOne(form.getLicensefk().getId());
			if (null == post) {
				throw new ResourceNotFoundException(
						" @@ post can't find by post :"
								+ form.getLicensefk().getId());
			}
			if (null == form.getOnSaleDate()) {
				product.setOnSaleDate(LocalDateTime.now());
			}
			//
			if (withFundingInfos.getMode().equals(WithFundingInfos.Mode.DAY)) {
				LocalDateTime interestSettledDate = product.getOnSaleDate()
						.plusDays(withFundingInfos.getTerms() - 1);
				product.setInterestSettledDate(interestSettledDate);
				LocalDate completedDate = interestSettledDate.toLocalDate();
				product.setCompletedDate(completedDate);
			} else if (withFundingInfos.getMode().equals(
					WithFundingInfos.Mode.MONTH)) {
				LocalDateTime interestSettledDate = product.getOnSaleDate()
						.plusMonths(withFundingInfos.getTerms()).minusDays(1);
				product.setInterestSettledDate(interestSettledDate);
				LocalDate completedDate = interestSettledDate.toLocalDate();
				product.setCompletedDate(completedDate);
			}
			product.setLicense(post);
			product.setId(null);
			product.setEnterprise(withFundingInfos.getEnterprise());
			product.setStatus(Product.Status.ON_SALE);
			Product saved = productRepository.save(product);

			withFundingInfosRepository.updateStatus(
					withFundingInfos.getSerialNumber(),
					WithFundingInfos.Status.PUBLISHED);

			return DTOUtils.map(saved, ProductDetails.class);
		}
		return null;
	}

}
