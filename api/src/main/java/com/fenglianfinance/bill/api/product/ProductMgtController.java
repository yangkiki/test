package com.fenglianfinance.bill.api.product;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fenglianfinance.bill.domain.Product;
import com.fenglianfinance.bill.domain.ProductCriteria;
//import com.fenglianfinance.bill.gateway.common.GatewayService;
import com.fenglianfinance.bill.model.ApiConstants;
import com.fenglianfinance.bill.model.ProductDetails;
import com.fenglianfinance.bill.model.ProductForm;
//import com.fenglianfinance.bill.payment.model.AddBidInfoForm;
import com.fenglianfinance.bill.service.ProductService;

@RestController
@RequestMapping(value = ApiConstants.URI_API_MGT + ApiConstants.URI_PRODUCT)
public class ProductMgtController {

    private static final Logger log = LoggerFactory.getLogger(ProductMgtController.class);

    @Inject
    private ProductService productService;

//    @Inject
//    private GatewayService gatewayService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page<ProductDetails>> getAllProduct(@RequestParam("q") String q,
            @RequestParam("active") boolean active, @RequestParam("type") Product.Type type,
            @RequestParam("status") Product.Status status,
            @PageableDefault(page = 0, size = 10, sort = { "createdDate" }, direction = Direction.DESC) Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("call getAllProducts");
        }
        Page<ProductDetails> result = productService.findProductsByKeyword(q, active, type, status, page);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = { "/search" }, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Page<ProductDetails>> searchProducts(@RequestBody ProductCriteria criteria,
            @PageableDefault(page = 0, size = 10, sort = { "createdDate" }, direction = Direction.DESC) Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("get all products by  search@" + criteria);
        }

        Page<ProductDetails> result = productService.findProducts(criteria, page);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> createProduct(@RequestBody ProductForm form, UriComponentsBuilder uriComponentsBuilder) {

        if (log.isDebugEnabled()) {
            log.debug("save ProductForm data @" + form);
        }

        ProductDetails product = productService.saveProduct(form);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path(ApiConstants.URI_API_PUBLIC + ApiConstants.URI_PRODUCT + "/{id}")
                .buildAndExpand(product.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = { "/{id}" }, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Void> updateProduct(@PathVariable("id") Long id, @RequestBody ProductForm form) {

        if (log.isDebugEnabled()) {
            log.debug("ProductForm @" + form);
        }

        productService.updateProduct(id, form);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ProductDetails> getProduct(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("get product data by id @" + id);
        }

        ProductDetails product = productService.findProductById(id);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = { "/{id}" }, method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("soft delete product @" + id + ", deactivate product not delete it");
        }

        productService.deactivateProduct(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = { "/{id}" }, params = { "action=PUBLISH" }, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Void> applyProduct(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("soft apply product @" + id + ", not apply it");
        }

        productService.updateStatus(id, "ON_SALE");

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = { "/{id}" }, params = { "action=PROMOTE" }, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Void> promoteProduct(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("soft release product @" + id + ", not release it");
        }

        productService.updatePromoted(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = { "/{id}" }, params = { "action=UNPROMOTE" }, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Void> unshelveProduct(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("soft unshelve product @" + id + ", not unshelve it");
        }

        productService.updateUnshelve(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = { "/search" }, method = RequestMethod.GET, params = { "action=DEMAND" })
    @ResponseBody
    public ResponseEntity<Boolean> exsistProduct() {
        if (log.isDebugEnabled()) {
            log.debug("call exsistProduct");
        }
        Boolean result = productService.findProductByDemand();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
