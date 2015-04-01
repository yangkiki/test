/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.api;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import com.moxian.ng.domain.*;
import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.LabelValueBean;
import com.moxian.ng.repository.CityRepository;
import com.moxian.ng.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.moxian.ng.repository.PermissionRepository;

/**
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 */
@RestController
@RequestMapping(value = ApiConstants.URI_API + "/public/refs")
public class DataReferenceController {

    private static final Logger log = LoggerFactory.getLogger(DataReferenceController.class);

//    @Inject
//    private CategoryRepository categoryRepository;
//
//    @Inject
//    private ProviderRepository providerRepository;

//    @Inject
//    private BrandRepository brandRepository;

    @Inject
    private CityRepository cityRepository;
//
//    @Inject
//    private BedTypeRepository bedTypeRepository;

    @Inject
    private RoleRepository roleRepository;

    @Inject
    private PermissionRepository permissionRepository;

//    @Inject
//    private BankRepository bankRepository;

//    @RequestMapping(value = "/starLevels")
//    public Hotel.StarLevel[] starLevels() {
//        return Hotel.StarLevel.values();
//    }
    
//    @RequestMapping(value = "/acctTransactionTypes")
//    public AcctTransactionType[] acctTransactionTypes() {
//        return AcctTransactionType.values();
//    }

//    @RequestMapping(value = "/breakfastTypes")
//    public Room.BreakfastType[] breakfastTypes() {
//        return Room.BreakfastType.values();
//    }
//
//    @RequestMapping(value = "/bankTypes")
//    public Bank.Type[] bankTypes() {
//        return Bank.Type.values();
//    }
//
//    @RequestMapping(value = "/enterpriseTypes")
//    public Enterprise.Type[] enterpriseTypes() {
//        return Enterprise.Type.values();
//    }
//
//    @RequestMapping(value = "/productTypes")
//    public Product.Type[] productTypes() {
//        return Product.Type.values();
//    }

//    @RequestMapping(value = "/productStatus")
//    public Product.Status[] productStatus() {
//        return Product.Status.values();
//    }
//
//    @RequestMapping(value = "/orderStatus")
//    public PurchaseOrder.Status[] orderStatus() {
//        return PurchaseOrder.Status.values();
//    }

    @RequestMapping(value = "/postTypes")
    public Post.Type[] postTypes() {
        return Post.Type.values();
    }

    @RequestMapping(value = "/postStatus")
    public Post.Status[] postStatus() {
        return Post.Status.values();
    }

//    @RequestMapping(value = "/interestAccrualTypes")
//    public Product.InterestAccrualType[] interestAccrualTypes() {
//        return Product.InterestAccrualType.values();
//
//    }
//
//    @RequestMapping(value = "/accountTypes")
//    public Enterprise.AccountTypes[] accountTypes() {
//        return Enterprise.AccountTypes.values();
//    }
//
//    @RequestMapping(value = "/enterpriseVerifyStatus")
//    public Enterprise.VerifyStatus[] enterpriseVerifyStatus() {
//        return Enterprise.VerifyStatus.values();
//    }

//    @RequestMapping(value = "/bedTypes")
//    public List<BedType> bedTypes() {
//        return bedTypeRepository.findAll();
//    }
//
//    @RequestMapping(value = "/categories")
//    public List<Category> categories() {
//        return categoryRepository.findAll();
//    }
//
//    @RequestMapping(value = "/providers")
//    public List<Provider> providers() {
//        return providerRepository.findAll();
//    }
//
//    @RequestMapping(value = "/brands")
//    public List<Brand> brands() {
//        return brandRepository.findAll();
//    }

    @RequestMapping(value = "/cities")
    public List<LabelValueBean> cities(@RequestParam("q") String q) {
        if (log.isDebugEnabled()) {
            log.debug("get cities by q@" + q);
        }

        List<LabelValueBean> result = cityRepository.filterByKeyword(q);

        if (log.isDebugEnabled()) {
            log.debug("found cities @" + result.size());
        }

        return result;
    }

//    @RequestMapping(value = "/banks")
//    public List<BankDetails> banks(@RequestParam("q") String q) {
//        if (log.isDebugEnabled()) {
//            log.debug("get cities by q@" + q);
//        }
//
//        List<Bank> result = bankRepository.filterByKeyword(q);
//
//        if (log.isDebugEnabled()) {
//            log.debug("found cities @" + result.size());
//        }
//
//        if (!result.isEmpty()) {
//            return DTOUtils.mapList(result, BankDetails.class);
//        }
//
//        return null;
//    }

    @RequestMapping(value = {ApiConstants.URI_ROLES}, method = RequestMethod.GET)
    @ResponseBody
    public List<String> allActiveRoles() {

        if (log.isDebugEnabled()) {
            log.debug(" find ref roles ");
        }

        List<Role> roles = roleRepository.findByActiveIsTrue();

        List<String> roleNames = new ArrayList<>();
        for (Role r : roles) {
            roleNames.add(r.getName());
        }

        if (log.isDebugEnabled()) {
            log.debug("public reference roles@" + roleNames);
        }

        return roleNames;
    }

    @RequestMapping(value = {ApiConstants.URI_PERMISSIONS}, method = RequestMethod.GET)
    @ResponseBody
    public List<String> allActivePermissions() {

        if (log.isDebugEnabled()) {
            log.debug(" find ref permissions... ");
        }

        List<Permission> perms = permissionRepository.findByActiveIsTrue();

        List<String> permNames = new ArrayList<>();
        for (Permission r : perms) {
            permNames.add(r.getName());
        }

        if (log.isDebugEnabled()) {
            log.debug("public ref permissions@" + permNames);
        }

        return permNames;
    }
}
