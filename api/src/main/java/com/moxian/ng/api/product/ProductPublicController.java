package com.moxian.ng.api.product;

import com.moxian.ng.domain.Product;
import com.moxian.ng.exception.ResourceNotFoundException;

import javax.inject.Inject;

import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.ProductDetails;
import com.moxian.ng.model.SimpleProductDetails;
import com.moxian.ng.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

@RestController
@RequestMapping(value = ApiConstants.URI_API_PUBLIC + ApiConstants.URI_PRODUCT)
public class ProductPublicController {

    private static final Logger log = LoggerFactory.getLogger(ProductPublicController.class);

    @Inject
    private ProductService productService;

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ProductDetails> getProduct(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("get product data by id @" + id);
        }

        ProductDetails product = productService.findProductById(id);

        //protect product access if it is not active.
        if (!product.isActive()) {
            throw new ResourceNotFoundException(product.getId());
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = "!f")
    @ResponseBody
    public ResponseEntity<Page<SimpleProductDetails>> getAllProducts(
            @PageableDefault(page = 0, size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page) {

        if (log.isDebugEnabled()) {
            log.debug("get all products @" + page);
        }

        Page<SimpleProductDetails> products = productService.findAcitveProducts( page);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = "f=NEWBIE")
    @ResponseBody
    public ResponseEntity<Page<SimpleProductDetails>> getNewbieProducts(
            @PageableDefault(page = 0, size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page) {

        if (log.isDebugEnabled()) {
            log.debug("get newbie products @" + page);
        }

        Page<SimpleProductDetails> products = productService.findAcitveProductsByType(Product.Type.NEWBIE, page);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = "f=HOT")
    @ResponseBody
    public ResponseEntity<Page<SimpleProductDetails>> getHotProducts(
            @PageableDefault(page = 0, size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page) {

        if (log.isDebugEnabled()) {
            log.debug("get hot products @" + page);
        }

        Page<SimpleProductDetails> products = productService.findAcitveProductsByType(Product.Type.HOT, page);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = "f=FIXED")
    @ResponseBody
    public ResponseEntity<Page<SimpleProductDetails>> getFixedProducts(
            @PageableDefault(page = 0, size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page) {

        if (log.isDebugEnabled()) {
            log.debug("get fixed products @" + page);
        }

        Page<SimpleProductDetails> products = productService.findAcitveProductsByType(Product.Type.FIXED, page);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = "f=DEMAND")
    @ResponseBody
    public ResponseEntity<Page<SimpleProductDetails>> getDemandProducts(
            @PageableDefault(page = 0, size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page) {

        if (log.isDebugEnabled()) {
            log.debug("get fixed products @" + page);
        }

        Page<SimpleProductDetails> products = productService.findAcitveProductsByType(Product.Type.DEMAND, page);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
	
	@RequestMapping(method = RequestMethod.GET, params = "f=WITHFUNDING_DAY")
	@ResponseBody
	public ResponseEntity<Page<SimpleProductDetails>> getWithFundingDayProducts(
			@PageableDefault(page = 0, size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page) {

		if (log.isDebugEnabled()) {
			log.debug("get WITHFUNDING_DAY products @" + page);
		}

		Page<SimpleProductDetails> products = productService
				.findAcitveProductsByType(Product.Type.WITHFUNDING_DAY, page);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, params = "f=WITHFUNDING_MONTH")
	@ResponseBody
	public ResponseEntity<Page<SimpleProductDetails>> getWithFundingMonthProducts(
			@PageableDefault(page = 0, size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page) {

		if (log.isDebugEnabled()) {
			log.debug("get WITHFUNDING_MONTH products @" + page);
		}

		Page<SimpleProductDetails> products = productService
				.findAcitveProductsByType(Product.Type.WITHFUNDING_MONTH, page);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

    @RequestMapping(method = RequestMethod.GET, params = "f=PROMOTE")
    @ResponseBody
    public ResponseEntity<SimpleProductDetails> getPromoteProducts() {

        if (log.isDebugEnabled()) {
            log.debug("get PROMOTE products");
        }
        SimpleProductDetails product = productService.getProductsByPromoted();

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
