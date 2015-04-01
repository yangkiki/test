package com.moxian.ng.service;


import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.moxian.ng.domain.City;
import com.moxian.ng.domain.Country;
import com.moxian.ng.domain.GrantedPermission;
import com.moxian.ng.domain.Permission;
import com.moxian.ng.domain.Post;
import com.moxian.ng.domain.Province;
import com.moxian.ng.domain.Role;
import com.moxian.ng.domain.UserAccount;
import com.moxian.ng.domain.UserProfile;

import com.moxian.ng.repository.CityRepository;
import com.moxian.ng.repository.CountryRepository;

import com.moxian.ng.repository.GrantedPermissionRepository;
import com.moxian.ng.repository.MessageRepository;
import com.moxian.ng.repository.PermissionRepository;
import com.moxian.ng.repository.PostRepository;
import com.moxian.ng.repository.ProvinceRepository;
import com.moxian.ng.repository.RoleRepository;
import com.moxian.ng.repository.UserProfileRepository;
import com.moxian.ng.repository.UserRepository;

@Named
@Transactional
public class DataImporter {

    private static final Logger log = LoggerFactory.getLogger(DataImporter.class);

    @Inject
    private Environment env;

    @Inject
    private UserRepository userRepository;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private CountryRepository countryRepository;

    @Inject
    private ProvinceRepository provinceRepository;

    @Inject
    private CityRepository cityRepository;

    @Inject
    private RoleRepository roleRepository;

    @Inject
    private PermissionRepository permissionRepository;

    @Inject
    private GrantedPermissionRepository grantedPermissionRepository;

//    @Inject
//    private StaffProfileRepository staffProfileRepository;
//
//    @Inject
//    private BankRepository bankRepository;
//
//    @Inject
//    private EnterpriseRepository enterpriseRepository;

    @Inject
    private MessageRepository messageRepository;

    @Inject
    private PostRepository postRepository;
//
//    @Inject
//    private ProductRepository productRepository;
//
//    @Inject
//    private OrderRepository orderRepository;

//    @Inject
//    private BillRepository billRepository;
//
//    @Inject
//    private BackLogRepository backLogRepository;
//
//    @Inject
//    private BackLogItemRepository backLogItemRepository;

    @Inject
    private UserProfileRepository userProfileRepository;

//    @Inject
//    private BankCardInfoRepository bankCardInfoRepository;

    @Inject
    private ResourceLoader resourceLoader;

    @Inject
    DataSource dataSource;

    @PostConstruct
    public void onPostConstruct() {
        if (log.isInfoEnabled()) {
            log.info("importing data into database...");
        }

        initialzePostDatas();

        loadAddressData();
        loadPermissions();
        loadReservedUserData();

        if (!"prod".equals(env.getActiveProfiles()[0])) {
           // loadTestData();
        }

    }

    private void loadAddressData() {
        if (log.isInfoEnabled()) {
            log.info("loading address data...");
        }

        // add contry, city sample data.
        Country country = new Country("中国");
        if (this.countryRepository.findByName("中国") == null) {
            country = countryRepository.save(country);
        }

        Province province = new Province("上海");
        province.setCountry(country);

        if (this.provinceRepository.findByName("上海") == null) {
            provinceRepository.save(province);
        }

        City city = new City("上海");
        city.setProvince(province);
        if (this.cityRepository.findByName("上海") == null) {
            cityRepository.save(city);
        }

        Province province2 = new Province("江苏");
        province2.setCountry(country);
        if (this.provinceRepository.findByName("江苏") == null) {
            provinceRepository.save(province2);
        }

        City city2 = new City("南京");
        city2.setProvince(province2);
        if (this.cityRepository.findByName("南京") == null) {
            cityRepository.save(city2);
        }

        City city3 = new City("苏州");
        city3.setProvince(province2);
        if (this.cityRepository.findByName("苏州") == null) {
            cityRepository.save(city3);
        }
    }

    private void loadPermissions() {
        if (log.isInfoEnabled()) {
            log.info("loading permissions...");
        }

//        createPermissionsIfAbsent(bankPermissions());
//        createPermissionsIfAbsent(accountPermissions());
//        createPermissionsIfAbsent(acctPermissions());
//        createPermissionsIfAbsent(confPermissions());
//        createPermissionsIfAbsent(userPermissions());
//        createPermissionsIfAbsent(enterprisePermissions());
//        createPermissionsIfAbsent(productPermissions());
//        createPermissionsIfAbsent(postPermissions());
//        createPermissionsIfAbsent(orderPermissions());
//        createPermissionsIfAbsent(billPermissions());
//        createPermissionsIfAbsent(reportPermissions());
    }

//    private Permission[] bankPermissions() {
//        int i = 1;
//        Permission[] permissions
//                = new Permission[]{
//                    new Permission(Category.BANK, "PERM_VIEW_BANK_COLLECTION", "^/api/mgt/banks(/?)(\\?.*)?$",
//                            "GET", i++),
//                    new Permission(Category.BANK, "PERM_ADD_BANK", "^/api/mgt/banks(/?)$", "POST", i++),
//                    new Permission(Category.BANK, "PERM_VIEW_BANK", "^/api/mgt/banks/[\\d](/?)$", "GET", i++),
//                    new Permission(Category.BANK, "PERM_EDIT_BANK", "^/api/mgt/banks/[\\d](/?)$", "PUT", i++),
//                    new Permission(Category.BANK, "PERM_ACTIVATE_BANK",
//                            "^/api/mgt/banks/[\\d](/?)(\\?action=ACTIVATE)$", "PUT", i++),
//                    new Permission(Category.BANK, "PERM_DEACTIVATE_BANK", "^/api/mgt/banks/[\\d](/?)$", "DELETE",
//                            i++)};
//
//        if (log.isDebugEnabled()) {
//            log.debug("bank permissions @" + permissions);
//        }
//
//        return permissions;
//    }
//
//    private Permission[] postPermissions() {
//        int i = 1;
//        Permission[] permissions
//                = new Permission[]{
//                    new Permission(Category.CONTENT_POST, "PERM_VIEW_POST_COLLECTION",
//                            "^/api/mgt/posts(/?)(\\?.*)?$", "GET", i++),
//                    new Permission(Category.CONTENT_POST, "PERM_ADD_POST", "^/api/mgt/posts(/?)$", "POST", i++),
//                    new Permission(Category.CONTENT_POST, "PERM_VIEW_POST", "^/api/mgt/posts/[\\d](/?)$", "GET",
//                            i++),
//                    new Permission(Category.CONTENT_POST, "PERM_PUBLISH_POST",
//                            "^/api/mgt/posts/[\\d](/?)\\?action=PUBLISH$", "PUT", i++),
//                    new Permission(Category.CONTENT_POST, "PERM_ACTIVATE_POST",
//                            "^/api/mgt/posts/[\\d](/?)\\?action=ACTIVATE", "PUT", i++),
//                    new Permission(Category.CONTENT_POST, "PERM_EDIT_POST", "^/api/mgt/posts/[\\d](/?)$", "PUT",
//                            i++),
//                    new Permission(Category.CONTENT_POST, "PERM_DEACTIVATE_POST", "^/api/mgt/posts/[\\d](/?)$",
//                            "DELETE", i++)};
//
//        if (log.isDebugEnabled()) {
//            log.debug("post permissions @" + permissions);
//        }
//
//        return permissions;
//    }
//
//    private Permission[] billPermissions() {
//        int i = 1;
//        Permission[] permissions
//                = new Permission[]{
//                    new Permission(Category.BILL, "PERM_VIEW_BILL_COLLECTION",
//                            "^/api/mgt/bills/search(/?)(\\?.*)?$", "POST", i++),
//                    new Permission(Category.BILL, "PERM_VIEW_BILL", "^/api/mgt/bills(/?)(\\?.*)?$", "GET", i++),
//                    new Permission(Category.BILL, "PERM_ADD_BILL", "^/api/mgt/bills(/?)$", "POST", i++),
//                    new Permission(Category.BILL, "PERM_EDIT_BILL", "^/api/mgt/bills(/?)$", "PUT", i++),
//                    new Permission(Category.BILL, "PERM_APPROVED_BILL",
//                            "^/api/mgt/bills/[\\d](/?)\\?action=APPROVED$", "PUT", i++),
//                    new Permission(Category.BILL, "PERM_DEPRECATED_BILL",
//                            "^/api/mgt/bills/[\\d](/?)\\?action=DEPRECATED", "PUT", i++),
//                    // new Permission(Category.BILL, "PERM_OPERATE_BILL",
//                    // "^/api/mgt/bills/(\\?.*)action=OPERATE(.*)$", "GET", i++),
//                    new Permission(Category.BILL, "PERM_LOCK_BILL",
//                            "^/api/mgt/bills/[\\d](/?)\\?action=DEACTIVATE$", "PUT", i++),
//                    new Permission(Category.BILL, "PERM_UNLOCK_BILL",
//                            "^/api/mgt/bills/[\\d](/?)\\?action=ACTIVATE$", "PUT", i++),
//                    // new Permission(Category.BILL, "PERM_SN_SEARCH_BILL",
//                    // "^/api/mgt/bills/[\\d](/?)\\?action=CHECK_EXISTENCE$", "GET", i++),
//
//                    new Permission(Category.BILL, "PERM_VIEW_BACKLOGITEM_COLLECTION",
//                            "^/api/mgt/backlogs/search(/?)(\\?.*)?$", "POST", i++),
//                    // new Permission(Category.BACKLOGITEM, "PERM_ADDBATCH_BACKLOGITEM",
//                    // "^/api/mgt/backlogs/\\?action=ADDBATCH$", "POST", i++),
//                    new Permission(Category.BILL, "PERM_ADD_BACKLOGITEM", "^/api/mgt/backlogs(/?)$", "POST", i++), // new Permission(Category.BILL, "PERM_RECOUNT_BACKLOGITEM",
//                // "^/api/mgt/backlogs/recount(/?)(\\?.*)?$", "GET", i++),
//                };
//
//        if (log.isDebugEnabled()) {
//            log.debug("post permissions @" + permissions);
//        }
//
//        return permissions;
//    }
//
//    private Permission[] accountPermissions() {
//
//        int i = 1;
//
//        Permission[] permissions
//                = new Permission[]{
//                    new Permission(Category.ACCOUNT, "PERM_VIEW_ACCOUNT_COLLECTION",
//                            "^/api/mgt/accounts(/?)(\\?.*)?$", "GET", i++),
//                    new Permission(Category.ACCOUNT, "PERM_ADD_ACCOUNT", "^/api/mgt/accounts(/?)$", "POST", i++),
//                    new Permission(Category.ACCOUNT, "PERM_VIEW_ACCOUNT", "^/api/mgt/accounts/[\\d](/?)$", "GET",
//                            i++),
//                    new Permission(Category.ACCOUNT, "PERM_EDIT_ACCOUNT", "^/api/mgt/accounts/[\\d](/?)$", "PUT",
//                            i++),
//                    new Permission(Category.ACCOUNT, "PERM_LOCK_ACCOUNT", "^/api/mgt/accounts/[\\d](/?)$",
//                            "DELETE", i++),
//                    new Permission(Category.ACCOUNT, "PERM_UNLOCK_ACCOUNT",
//                            "^/api/mgt/accounts/[\\d](/?)\\?action=UNLOCK$", "PUT", i++),
//                    new Permission(Category.ACCOUNT, "PERM_VIEW_ROLE", "^/api/mgt/roles(/?)(\\?.*)?$", "GET", i++),
//                    new Permission(Category.ACCOUNT, "PERM_ADD_ROLE", "^/api/mgt/roles(/?)$", "POST", i++),
//                    new Permission(Category.ACCOUNT, "PERM_EDIT_ROLE", "^/api/mgt/roles/[\\d](/?)$", "PUT", i++),
//                    new Permission(Category.ACCOUNT, "PERM_LOCK_ROLE", "^/api/mgt/roles/[\\d](/?)$", "DELETE", i++),
//                    new Permission(Category.ACCOUNT, "PERM_UNLOCK_ROLE",
//                            "^/api/mgt/roles/[\\d](/?)\\?action=UNLOCK$", "PUT", i++),
//                    new Permission(Category.ACCOUNT, "PERM_ASSIGN_PERMISSION",
//                            "^/api/mgt/roles/[\\d]/permissions(/?)$", "PUT", i++),
//                    new Permission(Category.ACCOUNT, "PERM_VIEW_PERMISSION", "^/api/mgt/permissions/[\\d](/?)$",
//                            "GET", i++),
//                    new Permission(Category.ACCOUNT, "PERM_EDIT_PERMISSION", "^/api/mgt/permissions/[\\d](/?)$",
//                            "PUT", i++)
//
//                };
//
//        if (log.isDebugEnabled()) {
//            log.debug("account permissions @" + permissions);
//        }
//
//        return permissions;
//    }
//
//    private Permission[] acctPermissions() {
//
//        int i = 1;
//
//        Permission[] permissions = new Permission[]{
//            new Permission(Category.ACCOUNTING, "PERM_VIEW_ACCOUNTING", "^/api/mgt/acct(/?)(\\?.*)$", "GET", i++),
//            new Permission(Category.ACCOUNTING, "PERM_ADD_BIDINFO", "^/api/mgt/acct/addbidinfo(/?)$", "POST", i++),
//            new Permission(Category.ACCOUNTING, "PERM_UPDATE_TRANSACTION_LOG", "^/api/mgt/acct/[\\d](/?)$", "PUT", i++),
//            new Permission(Category.ACCOUNTING, "PERM_VIEW_TRANSFER_LOGS", "^/api/mgt/acct/transfer(/?)(\\?.*)$", "GET", i++),
//            new Permission(Category.ACCOUNTING, "PERM_TRANSFER", "^/api/mgt/acct/transfer(/?)$", "POST", i++),
//            new Permission(Category.ACCOUNTING, "PERM_VIEW_RECONCILIATIONS", "^/api/mgt/acct/reconciliations(/?)(\\?.*)$", "GET", i++),};
//
//        if (log.isDebugEnabled()) {
//            log.debug("accounting permissions @" + permissions);
//        }
//
//        return permissions;
//    }
//
//    private Permission[] confPermissions() {
//
//        int i = 1;
//
//        Permission[] permissions = new Permission[]{
//            new Permission(Category.CONFIG, "PERM_VIEW_CONF", "^/api/mgt/conf(/?)$", "GET", i++),
//            new Permission(Category.CONFIG, "PERM_UPDATE_CONF", "^/api/mgt/conf(/?)$", "PUT", i++),
//            new Permission(Category.CONFIG, "PERM_VIEW_APPUPDATE_COLLECTION", "^/api/mgt/appupdates(/?)(\\?.*)?$", "GET", i++),
//            new Permission(Category.CONFIG, "PERM_ADD_APPUPDATE", "^/api/mgt/appupdates(/?)$", "POST", i++),
//            new Permission(Category.CONFIG, "PERM_EDIT_APPUPDATE", "^/api/mgt/appupdates(/?)$", "PUT", i++)
//        };
//
//        if (log.isDebugEnabled()) {
//            log.debug("system config permissions @" + permissions);
//        }
//
//        return permissions;
//    }
//
//    private Permission[] userPermissions() {
//
//        int i = 1;
//
//        Permission[] permissions
//                = new Permission[]{
//                    new Permission(Category.USER, "PERM_VIEW_USER_COLLECTION", "^/api/mgt/users(/?)(\\?.*)?$",
//                            "GET", i++),
//                    new Permission(Category.USER, "PERM_ADD_USER", "^/api/mgt/users(/?)$", "POST", i++),
//                    new Permission(Category.USER, "PERM_VIEW_USER", "^/api/mgt/users/[\\d](/?)(\\?.*)?$", "GET",
//                            i++),
//                    // new Permission(Category.USER, "PERM_VIEW_USER_PROFILE",
//                    // "^/api/mgt/users/[\\d]/profile(/?)$", "GET", i++),
//                    new Permission(Category.USER, "PERM_EDIT_USER", "^/api/mgt/users/[\\d](/?)$", "PUT", i++),
//                    new Permission(Category.USER, "PERM_DEACTIVATE_USER", "^/api/mgt/users/[\\d](/?)$", "DELETE",
//                            i++),
//                    new Permission(Category.USER, "PERM_ACTIVATE_USER",
//                            "^/api/mgt/users/[\\d](/?)\\?action=ACTIVATE$", "PUT", i++),
//                    new Permission(Category.USER, "PERM_LOCK_USER", "^/api/mgt/users/[\\d](/?)\\?action=LOCK$",
//                            "PUT", i++),
//                    new Permission(Category.USER, "PERM_UNLOCK_USER", "^/api/mgt/users/[\\d](/?)\\?action=UNLOCK$",
//                            "PUT", i++)
//                };
//
//        if (log.isDebugEnabled()) {
//            log.debug("account permissions @" + permissions);
//        }
//
//        return permissions;
//    }
//
//    private Permission[] enterprisePermissions() {
//        int i = 1;
//        Permission[] permissions
//                = new Permission[]{
//                    new Permission(Category.ENTERPRISE, "PERM_VIEW_ENTERPRISE_COLLECTION",
//                            "^/api/mgt/enterprises(/?)(\\?.*)?$", "GET", i++),
//                    new Permission(Category.ENTERPRISE, "PERM_ADD_ENTERPRISE", "^/api/mgt/enterprises(/?)$",
//                            "POST", i++),
//                    new Permission(Category.ENTERPRISE, "PERM_VIEW_ENTERPRISE", "^/api/mgt/enterprises/[\\d](/?)$",
//                            "GET", i++),
//                    new Permission(Category.ENTERPRISE, "PERM_EDIT_ENTERPRISE", "^/api/mgt/enterprises/[\\d](/?)$",
//                            "PUT", i++),
//                    new Permission(Category.ENTERPRISE, "PERM_DELETE_ENTERPRISE",
//                            "^/api/mgt/enterprises/[\\d](/?)$", "DELETE", i++),
//                    new Permission(Category.ENTERPRISE, "PERM_PASS_ENTERPRISE",
//                            "^/api/mgt/enterprises/[\\d](/?)\\?action=APPROVE$", "PUT", i++),
//                    new Permission(Category.ENTERPRISE, "PERM_REJECT_ENTERPRISE",
//                            "^/api/mgt/enterprises/[\\d](/?)\\?action=REJECT$", "PUT", i++),
//                    new Permission(Category.ENTERPRISE, "PERM_DISCARD_ENTERPRISE",
//                            "^/api/mgt/enterprises/[\\d](/?)\\?action=DISCARD$", "PUT", i++),
//                    new Permission(Category.ENTERPRISE, "PERM_ACCOUNT_ENTERPRISE",
//                            "^/api/mgt/enterprises/[\\d](/?)\\?action=ACCOUNT$", "PUT", i++)
//
//                };
//
//        if (log.isDebugEnabled()) {
//            log.debug("account permissions @" + permissions);
//        }
//
//        return permissions;
//    }
//
//    private Permission[] productPermissions() {
//        int i = 1;
//        Permission[] permissions
//                = new Permission[]{
//                    new Permission(Category.PRODUCT, "PERM_VIEW_PRODUCT_COLLECTION",
//                            "^/api/mgt/products(/?)(\\?.*)?$", "GET", i++),
//                    new Permission(Category.PRODUCT, "PERM_ADD_PRODUCT", "^/api/mgt/products(/?)$", "POST", i++),
//                    new Permission(Category.PRODUCT, "PERM_VIEW_PRODUCT", "^/api/mgt/products/[\\d](/?)$", "GET",
//                            i++),
//                    new Permission(Category.PRODUCT, "PERM_EDIT_PRODUCT", "^/api/mgt/products/[\\d](/?)$", "PUT",
//                            i++),
//                    new Permission(Category.PRODUCT, "PERM_DELETE_PRODUCT", "^/api/mgt/products/[\\d](/?)$",
//                            "DELETE", i++),
//                    new Permission(Category.PRODUCT, "PERM_APPLY_PRODUCT", "^/api/mgt/products/[\\d](/?)$", "POST",
//                            i++),
//                    new Permission(Category.PRODUCT, "PERM_RELEASE_PRODUCT",
//                            "^/api/mgt/products/[\\d](/?)\b?action=PROMOTE$", "POST", i++)
//
//                };
//
//        if (log.isDebugEnabled()) {
//            log.debug("product permissions @" + permissions);
//        }
//
//        return permissions;
//    }
//
//    private Permission[] orderPermissions() {
//        int i = 1;
//        Permission[] permissions
//                = new Permission[]{
//                    new Permission(Category.ORDER, "PERM_VIEW_ORDER_COLLECTION", "^/api/mgt/orders/search(/?)(\\?.*)?$",
//                            "POST", i++),
//                    new Permission(Category.ORDER, "PERM_VIEW_ORDER", "^/api/mgt/orders/[\\d](/?)(\\?.*)?$", "GET",
//                            i++),
//                    new Permission(Category.ORDER, "PERM_REPAY_ORDER", "^/api/mgt/orders/[\\d](/?)?action=REQUEST_REPAYMENT$", "PUT",
//                            i++),
//                    new Permission(Category.ORDER, "PERM_DEACTIVATE_ORDER", "^/api/mgt/orders/[\\d](/?)?action=DEACTIVATE$", "PUT",
//                            i++),
//                    new Permission(Category.ORDER, "PERM_ACTIVATE_ORDER",
//                            "^/api/mgt/orders/[\\d](/?)?action=ACTIVATE$", "PUT", i++)};
//
//        if (log.isDebugEnabled()) {
//            log.debug("order permissions @" + permissions);
//        }
//
//        return permissions;
//    }
//
//    private Permission[] reportPermissions() {
//        int i = 1;
//        Permission[] permissions
//                = new Permission[]{
//                    new Permission(Category.REPORT, "PERM_VIEW_REPORT", "^/api/mgt/reports/(.*)(\\?.*)?$",
//                            "GET", i++),};
//
//        if (log.isDebugEnabled()) {
//            log.debug("order permissions @" + permissions);
//        }
//
//        return permissions;
//    }
//
//    private void createPermissionsIfAbsent(Permission... permissions) {
//        for (Permission perm : permissions) {
//            createPermissionIfAbsent(perm);
//        }
//    }

    private void createPermissionIfAbsent(Permission permission) {
        if (this.permissionRepository.findByName(permission.getName()) == null) {
            this.permissionRepository.save(permission);
        }
    }

    private void loadReservedUserData() {
        if (log.isInfoEnabled()) {
            log.info("loading reserved roles...");
        }

        Role[] roles = new Role[]{new Role(Role.ROLE_ADMIN), new Role(Role.ROLE_USER)};

        for (Role r : roles) {
            createRoleIfAbsent(r);
        }

        UserAccount userAccount
                = new UserAccount("admin", passwordEncoder.encode("test123"), "Administrator", false,
                        UserAccount.Type.STAFF, "ADMIN");

        createUserAccountIfAbsent(userAccount);
    }

    private void createRoleIfAbsent(Role role) {
        if (this.roleRepository.findByName(role.getName()) == null) {
            this.roleRepository.save(role);
        }
    }

    private UserAccount createUserAccountIfAbsent(UserAccount user) {
        final UserAccount existedUserAccount = this.userRepository.findByUsername(user.getUsername());
        if (existedUserAccount == null) {
            UserAccount userAccount = this.userRepository.save(user);
            this.userRepository.flush();
            return userAccount;
        }
        return existedUserAccount;
    }

//    private void loadTestData() {
//        if (log.isInfoEnabled()) {
//            log.info("load test user data...");
//        }
//
//        createRoleIfAbsent(new Role("TEST"));
//
//        GrantedPermission grantedPermission = new GrantedPermission("TEST", "PERM_VIEW_BANK");
//        GrantedPermission grantedPermission2 = new GrantedPermission("TEST", "PERM_ADD_BANK");
//
//        createGrantedPermissionIfAbsent(grantedPermission);
//        createGrantedPermissionIfAbsent(grantedPermission2);
//
//        UserAccount userAccount1
//                = new UserAccount("test1", passwordEncoder.encode("test123"), "Test User 1", false,
//                        UserAccount.Type.STAFF, "USER");
//        UserAccount userAccount2
//                = new UserAccount("test2", passwordEncoder.encode("test123"), "Test User 2", false,
//                        UserAccount.Type.STAFF, "TEST");
//
//        UserAccount grandz
//                = new UserAccount("grandzh", passwordEncoder.encode("grandzh@123"), "Grand Zhang", false,
//                        UserAccount.Type.USER, "USER");
//
//        UserAccount roy
//                = new UserAccount("roy", passwordEncoder.encode("test123"), "Roy Yang", false,
//                        UserAccount.Type.ENTERPRISE, "USER");
//        AccountingInfo accountingInfo1 = new AccountingInfo("ycy_alibabaworld", "6000060000806249");
//        accountingInfo1.setAuditDesc("%E5%BC%80%E6%88%B7%E6%88%90%E5%8A%9F");
//        accountingInfo1.setAuditStatus("Y");
//        roy.setAccountingInfo(accountingInfo1);
//        UserAccount savedroy = createUserAccountIfAbsent(roy);
//
//        UserAccount yang
//                = new UserAccount("yang", passwordEncoder.encode("test123"), "Yang Kui", false,
//                        UserAccount.Type.ENTERPRISE, "USER");
//        AccountingInfo accountingInfo2 = new AccountingInfo("ycy_microsoftworld", "6000060000806258");
//        accountingInfo2.setAuditDesc("%E5%BC%80%E6%88%B7%E6%88%90%E5%8A%9F");
//        accountingInfo2.setAuditStatus("Y");
//        yang.setAccountingInfo(accountingInfo2);
//        UserAccount saveyang = createUserAccountIfAbsent(yang);
//
//        UserAccount savedTestUser1 = createUserAccountIfAbsent(userAccount1);
//        UserAccount saved2 = createUserAccountIfAbsent(userAccount2);
//
//        AccountingInfo zhangAccountingInfo = new AccountingInfo("1", "6000060000757435");
//        grandz.setAccountingInfo(zhangAccountingInfo);
//        UserAccount savedZhang = createUserAccountIfAbsent(grandz);
//
//        UserAccount appTestAccount
//                = new UserAccount("appTest", passwordEncoder.encode("123456"), "appTest", false,
//                        UserAccount.Type.USER, "USER");
//        appTestAccount.setEmail("appTestAccount");
//        appTestAccount.setIdCardVerification(new IdCardVerification("阿呆发", "430423198108255881"));
//
//        AccountingInfo appTestAccountingInfo = new AccountingInfo("ycy_appTest", "6000060000827440");
//        appTestAccount.setAccountingInfo(appTestAccountingInfo);
//        UserAccount appTestAccountSaved = createUserAccountIfAbsent(appTestAccount);
//
//        BankCardInfo card = new BankCardInfo();
//        card.setCardId("4367425678998765");
//        card.setBankId("CCB");
//        card.setUser(appTestAccountSaved);
//        createBankCardInfoIfAbsent(card);
//
//        UserProfile appTestProfile = new UserProfile();
//        appTestProfile.setAccount(appTestAccountSaved);
//        appTestProfile.setSecondaryEmail("Hantsy Bai<hantsy@gmail.com>bai@tom.com");
//        appTestProfile.setSecondaryMobileNumber("138001380000");
//        appTestProfile.setInstantMessager("QQ:332343423");
//        createUserProfileIfAbsent(appTestProfile);
//
//        UserProfile userProfile = new UserProfile();
//        userProfile.setAccount(savedTestUser1);
//        userProfile.setSecondaryEmail("Hantsy Bai<hantsy@gmail.com>bai@tom.com");
//        userProfile.setSecondaryMobileNumber("138001380000");
//        userProfile.setInstantMessager("QQ:332343423");
//        createUserProfileIfAbsent(userProfile);
//
//        UserAccount admin = userRepository.findByUsername("admin");
//
//        Message msg = new Message("test title", "test message");
//        msg.setTo(admin);
//        messageRepository.save(msg);
//
//        Message msg2 = new Message("test title 2 ", "test message 2");
//        msg2.setTo(admin);
//        messageRepository.save(msg2);
//
//        StaffProfile staffProfile = new StaffProfile("IT", "", "", "", savedTestUser1);
//        StaffProfile staffProfile1 = new StaffProfile("IT1", "", "", "", saved2);
//
//        createStaffProfileIfAbsent(staffProfile);
//        createStaffProfileIfAbsent(staffProfile1);
//
//        // create bank data
//        Bank bank = new Bank();
//
//        Address addr = new Address();
//        addr.setCity("上海");
//        addr.setStreet("LUJIAZUI");
//        addr.setZipcode("200050");
//        bank.setAddress(addr);
//        bank.setCode("SW33003");
//        bank.setContactPerson("P Lee");
//        bank.setLandlineNumber("020-333343433");
//        bank.setName("中国建设银行上海分行");
//        bank.setNotes("notes test");
//        bank.setProvince("上海");
//        bank.setType(Bank.Type.NATIONAL);
//
//        bank = createBankIfAbsent(bank);
//
//        Bank bank2 = new Bank();
//
//        Address addr2 = new Address();
//        addr2.setCity("上海");
//        addr2.setStreet("LUJIAZUI");
//        addr2.setZipcode("200050");
//        bank2.setAddress(addr);
//        bank2.setCode("SW33003");
//        bank2.setContactPerson("P Lee");
//        bank2.setLandlineNumber("020-333343433");
//        bank2.setName("上海银行");
//        bank2.setNotes("notes test");
//        bank2.setProvince("上海");
//        bank2.setType(Bank.Type.NATIONAL);
//
//        bank2 = createBankIfAbsent(bank2);
//
//        Post post1 = new Post();
//        post1.setContent("2015年会出股票新产品推出，敬请关注！");
//        post1.setTitle("股票");
//        post1.setType(Post.Type.ANNOUNCEMENT);
//        post1.setStatus(Post.Status.PUBLISHED);
//        post1 = createPostIfAbsent(post1);
//        // postRepository.save(post1);
//
//        Post post2 = new Post();
//        post2.setContent("2015年会出基金新产品敬请关注！");
//        post2.setTitle("基金");
//        post2.setType(Post.Type.LICENSE);
//        post2.setStatus(Post.Status.PUBLISHED);
//        post2 = createPostIfAbsent(post2);
//
//        Post post3 = new Post();
//        post3.setContent("2015年会出保险新产品敬请关注！");
//        post3.setTitle("保险");
//        post3.setType(Post.Type.LICENSE);
//        post3.setStatus(Post.Status.PUBLISHED);
//        post3 = createPostIfAbsent(post3);
//
//        Post post4 = new Post();
//        post4.setContent("2015年会出银行承兑票据新产品敬请关注！");
//        post4.setTitle("银行承兑票据");
//        post4.setType(Post.Type.LICENSE);
//        post4.setStatus(Post.Status.PUBLISHED);
//        post4 = createPostIfAbsent(post4);
//
//        Enterprise enterprise
//                = new Enterprise("alibaba", "001", "13472858302", "02188889999", "00100", "", "上海银行", "", "", "", "", "",
//                        Enterprise.Type.PERNAS, "", "430621199908147715", "02111112222", "yangkuimsg@hotmail.com",
//                        Enterprise.AccountTypes.BANK_BOOK, "", addr, addr, addr, true, "SHBANK",
//                        Enterprise.VerifyStatus.APPROVED, "", "88888", null, null, "123456");
//        enterprise.setUserAccount(savedroy);
//        enterprise = createEnterpriseIfAbsent(enterprise);
//
//        Enterprise enterprise1
//                = new Enterprise("microsoft", "002", "18572858302", "02188889999", "00200", "", "上海建设银行", "", "", "", "",
//                        "", Enterprise.Type.PERNAS, "", "430621199908147715", "02111112222", "yangkuimsg@hotmail.com",
//                        Enterprise.AccountTypes.BANK_BOOK, "", addr2, addr2, addr2, true, "ICBC",
//                        Enterprise.VerifyStatus.PENDING, "", "88888", saved2, null, "654321");
//        enterprise1.setUserAccount(saveyang);
//        enterprise1 = createEnterpriseIfAbsent(enterprise1);
//
//        Bill bill = new Bill();
//        bill.setSerialNumber("11112222");
//        bill.setIssuingDate(LocalDate.now());
//        bill.setIssuer("张三");
//        bill.setPayee("李四");
//        bill.setIssuerBankAccount("6225881002158383");
//        bill.setPayeeBankAccount("6225881002158383");
//        bill.setPayeeBank(bank);
//        bill.setAcceptingBank(bank2);
//        bill.setDenomination(new BigDecimal(1000000.00));
//        bill.setExpirationDate(LocalDate.now().plusDays(50));
//        bill.setAcceptanceAgreementNumber("1101");
//        bill.setInvoiceDate(LocalDate.now().plusDays(40));
//        bill.setUsance(10);
//        bill.setInvoiceRate(10.22f);
//        bill.setInvoiceInterestAmount(new BigDecimal(1000000.00));
//        bill.setEnterprise(enterprise);
//        bill.setFinancingPurposes("建厂");
//        bill.setStatus(Bill.Status.APPROVED);
//        bill = createBillIfAbsent(bill);
//
//        Bill bill1 = new Bill();
//        bill1.setSerialNumber("11113333");
//        bill1.setIssuingDate(LocalDate.now());
//        bill1.setIssuer("张三");
//        bill1.setPayee("李四");
//        bill1.setIssuerBankAccount("6225881002158383");
//        bill1.setPayeeBankAccount("6225881002158383");
//        bill1.setPayeeBank(bank2);
//        bill1.setAcceptingBank(bank);
//        bill1.setDenomination(new BigDecimal(1000000.00));
//        bill1.setExpirationDate(LocalDate.now().plusDays(50));
//        bill1.setAcceptanceAgreementNumber("1101");
//        bill1.setInvoiceDate(LocalDate.now().plusDays(40));
//        bill1.setUsance(10);
//        bill1.setInvoiceRate(10.22f);
//        bill1.setInvoiceInterestAmount(new BigDecimal(1000000.00));
//        bill1.setEnterprise(enterprise);
//        bill1.setFinancingPurposes("建厂");
//        bill1.setStatus(Bill.Status.APPROVED);
//        bill1 = createBillIfAbsent(bill1);
//
//        Bill bill2 = new Bill();
//        bill2.setSerialNumber("11114444");
//        bill2.setIssuingDate(LocalDate.now());
//        bill2.setIssuer("张三");
//        bill2.setPayee("李四");
//        bill2.setIssuerBankAccount("6225881002158383");
//        bill2.setPayeeBankAccount("6225881002158383");
//        bill2.setPayeeBank(bank);
//        bill2.setAcceptingBank(bank2);
//        bill2.setDenomination(new BigDecimal(1000000.00));
//        bill2.setExpirationDate(LocalDate.now().plusDays(50));
//        bill2.setAcceptanceAgreementNumber("1101");
//        bill2.setInvoiceDate(LocalDate.now().plusDays(40));
//        bill2.setUsance(10);
//        bill2.setInvoiceRate(10.22f);
//        bill2.setInvoiceInterestAmount(new BigDecimal(1000000.00));
//        bill2.setEnterprise(enterprise);
//        bill2.setFinancingPurposes("建厂");
//        bill2.setStatus(Bill.Status.APPROVED);
//        bill2 = createBillIfAbsent(bill2);
//
//        Bill bill3 = new Bill();
//        bill3.setSerialNumber("11115555");
//        bill3.setIssuingDate(LocalDate.now());
//        bill3.setIssuer("张三");
//        bill3.setPayee("李四");
//        bill3.setIssuerBankAccount("6225881002158383");
//        bill3.setPayeeBankAccount("6225881002158383");
//        bill3.setPayeeBank(bank2);
//        bill3.setAcceptingBank(bank);
//        bill3.setDenomination(new BigDecimal(1000000.00));
//        bill3.setExpirationDate(LocalDate.now().plusDays(50));
//        bill3.setAcceptanceAgreementNumber("1101");
//        bill3.setInvoiceDate(LocalDate.now().plusDays(40));
//        bill3.setUsance(10);
//        bill3.setInvoiceRate(10.22f);
//        bill3.setInvoiceInterestAmount(new BigDecimal(1000000.00));
//        bill3.setEnterprise(enterprise);
//        bill3.setFinancingPurposes("建厂");
//        bill3.setStatus(Bill.Status.APPROVED);
//        bill3 = createBillIfAbsent(bill3);
//
//        Bill bill4 = new Bill();
//        bill4.setSerialNumber("11116666");
//        bill4.setIssuingDate(LocalDate.now());
//        bill4.setIssuer("张三");
//        bill4.setPayee("李四");
//        bill4.setIssuerBankAccount("6225881002158383");
//        bill4.setPayeeBankAccount("6225881002158383");
//        bill4.setPayeeBank(bank);
//        bill4.setAcceptingBank(bank2);
//        bill4.setDenomination(new BigDecimal(1000000.00));
//        bill4.setExpirationDate(LocalDate.now().plusDays(50));
//        bill4.setAcceptanceAgreementNumber("1101");
//        bill4.setInvoiceDate(LocalDate.now().plusDays(40));
//        bill4.setUsance(10);
//        bill4.setInvoiceRate(10.22f);
//        bill4.setInvoiceInterestAmount(new BigDecimal(1000000.00));
//        bill4.setEnterprise(enterprise);
//        bill4.setFinancingPurposes("建厂");
//        bill4.setStatus(Bill.Status.APPROVED);
//        bill4 = createBillIfAbsent(bill4);
//
//        Bill bill5 = new Bill();
//        bill5.setSerialNumber("11117777");
//        bill5.setIssuingDate(LocalDate.now());
//        bill5.setIssuer("张三");
//        bill5.setPayee("李四");
//        bill5.setIssuerBankAccount("6225881002158383");
//        bill5.setPayeeBankAccount("6225881002158383");
//        bill5.setPayeeBank(bank);
//        bill5.setAcceptingBank(bank2);
//        bill5.setDenomination(new BigDecimal(1000000.00));
//        bill5.setExpirationDate(LocalDate.now().plusDays(50));
//        bill5.setAcceptanceAgreementNumber("1101");
//        bill5.setInvoiceDate(LocalDate.now().plusDays(40));
//        bill5.setUsance(10);
//        bill5.setInvoiceRate(10.22f);
//        bill5.setInvoiceInterestAmount(new BigDecimal(1000000.00));
//        bill5.setEnterprise(enterprise);
//        bill5.setFinancingPurposes("建厂");
//        bill5.setStatus(Bill.Status.APPROVED);
//        bill5 = createBillIfAbsent(bill5);
//
//        BackLog backLog = new BackLog();
//        backLog = backLogRepository.save(backLog);
//
//        BackLogItem backLogItem = new BackLogItem();
//        backLogItem.setBill(bill);
//        backLogItem.setStatus(BackLogItem.Status.UNASSIGNED);
//        backLogItem.setBackLog(backLog);
//        backLogItem.setType(BackLogItem.Type.DEMAND);
//        backLogItem.setAnnualInterestRate(10.22f);
//        backLogItem.setCompletedDate(LocalDate.now());
//        backLogItem.setFinancingAmount(new BigDecimal(1000000.00));
//        backLogItem = createBackLogItemIfAbsent(backLogItem);
//
//        BackLogItem backLogItem1 = new BackLogItem();
//        backLogItem1.setBill(bill1);
//        backLogItem1.setStatus(BackLogItem.Status.UNASSIGNED);
//        backLogItem1.setBackLog(backLog);
//        backLogItem1.setType(BackLogItem.Type.DEMAND);
//        backLogItem1.setAnnualInterestRate(10.22f);
//        backLogItem1.setCompletedDate(LocalDate.now());
//        backLogItem1.setFinancingAmount(new BigDecimal(1000000.00));
//        backLogItem1 = createBackLogItemIfAbsent(backLogItem1);
//
//        BackLogItem backLogItem2 = new BackLogItem();
//        backLogItem2.setBill(bill2);
//        backLogItem2.setStatus(BackLogItem.Status.UNASSIGNED);
//        backLogItem2.setBackLog(backLog);
//        backLogItem2.setType(BackLogItem.Type.FIXED);
//        backLogItem2.setAnnualInterestRate(10.22f);
//        backLogItem2.setCompletedDate(LocalDate.now());
//        backLogItem2.setFinancingAmount(new BigDecimal(1000000.00));
//        backLogItem2 = createBackLogItemIfAbsent(backLogItem2);
//
//        BackLogItem backLogItem3 = new BackLogItem();
//        backLogItem3.setBill(bill3);
//        backLogItem3.setStatus(BackLogItem.Status.UNASSIGNED);
//        backLogItem3.setBackLog(backLog);
//        backLogItem3.setType(BackLogItem.Type.FIXED);
//        backLogItem3.setAnnualInterestRate(10.22f);
//        backLogItem3.setCompletedDate(LocalDate.now());
//        backLogItem3.setFinancingAmount(new BigDecimal(1000000.00));
//        backLogItem3 = createBackLogItemIfAbsent(backLogItem3);
//
//        BackLogItem backLogItem4 = new BackLogItem();
//        backLogItem4.setBill(bill4);
//        backLogItem4.setStatus(BackLogItem.Status.UNASSIGNED);
//        backLogItem4.setBackLog(backLog);
//        backLogItem4.setType(BackLogItem.Type.SINGLE);
//        backLogItem4.setAnnualInterestRate(10.22f);
//        backLogItem4.setCompletedDate(LocalDate.now());
//        backLogItem4.setFinancingAmount(new BigDecimal(1000000.00));
//        backLogItem4 = createBackLogItemIfAbsent(backLogItem4);
//
//        BackLogItem backLogItem5 = new BackLogItem();
//        backLogItem5.setBill(bill5);
//        backLogItem5.setStatus(BackLogItem.Status.UNASSIGNED);
//        backLogItem5.setBackLog(backLog);
//        backLogItem5.setType(BackLogItem.Type.SINGLE);
//        backLogItem5.setAnnualInterestRate(10.22f);
//        backLogItem5.setCompletedDate(LocalDate.now());
//        backLogItem5.setFinancingAmount(new BigDecimal(1000000.00));
//        backLogItem5 = createBackLogItemIfAbsent(backLogItem5);
//
//        // PurchaseOrder order = new PurchaseOrder();
//        // order.setProduct(product);
//        // order.setAmount(new BigDecimal(1200));
//        // order.setUser(savedTestUser1);
//        // order.setSerialNumber("001");
//        // order.setStatus(PurchaseOrder.Status.PENDING_PAYMENT);
//        // createOrderIfAbsent(order);
//        // fixed Product
//        Product product = new Product();
//        product.setName("银票定期99999期");
//        product.setTotalAmount(new BigDecimal(99999999));
//        product.setOnSaleDate(LocalDateTime.now());
//        product.setActive(true);
//        product.setCreatedBy(userAccount2);
//        product.setAnnualPercentageRate(6.8f);
//        product.setDiscription("银票定期99999期测试数据");
//        product.setDuration(60);
//        product.setInterestAccrualType(Product.InterestAccrualType.T_PLUS_ONE);
//        product.setInterestSettledDate(LocalDateTime.now());
//        // product.setIssuedDateRange(new DateRange());
//        product.setLicense(post1);
//        product.setStatus(Status.FOR_SALE);
//        product.setType(Product.Type.FIXED);
//        product.setUnitPrice(new BigDecimal(1));
//        // product.setBill(bill);
//        IntRange purchaseLimit = new IntRange();
//        purchaseLimit.setMin(1);
//        purchaseLimit.setMin(5000);
//        product.setPurchaseLimit(purchaseLimit);
//        product.setRepaymentDeadline(LocalDateTime.now().plusDays(60));
//        DateRange issuedDateRange = new DateRange();
//        issuedDateRange.setFrom(LocalDateTime.now().plusDays(3));
//        issuedDateRange.setTo(LocalDateTime.now().plusDays(30));
//        // product.setIssuedDateRange(issuedDateRange);
//        product = createProductIfAbsent(product);
//
//        // demand Product
//        Product product1 = new Product();
//        product1.setName("银票活期99999期");
//        product1.setTotalAmount(new BigDecimal(666666666));
//        product1.setOnSaleDate(LocalDateTime.now());
//        product1.setActive(true);
//        product1.setCreatedBy(userAccount2);
//        product1.setAnnualPercentageRate(6.8f);
//        product1.setDiscription("银票活期99999期测试数据");
//        product1.setDuration(7);
//        product1.setInterestAccrualType(Product.InterestAccrualType.T);
//        product1.setInterestSettledDate(LocalDateTime.now());
//        // product1.setIssuedDateRange(new DateRange());
//        product1.setStatus(Status.FOR_SALE);
//        product1.setType(Product.Type.DEMAND);
//        product1.setUnitPrice(new BigDecimal(1));
//        // product1.setBill(bill1);
//        product1.setLicense(post2);
//        IntRange purchaseLimit1 = new IntRange();
//        purchaseLimit1.setMin(1);
//        purchaseLimit1.setMin(7000);
//        product1.setPurchaseLimit(purchaseLimit1);
//        product1.setRepaymentDeadline(LocalDateTime.now().plusDays(7));
//        DateRange issuedDateRange1 = new DateRange();
//        issuedDateRange1.setFrom(LocalDateTime.now().plusDays(10));
//        issuedDateRange1.setTo(LocalDateTime.now().plusDays(20));
//        // product1.setIssuedDateRange(issuedDateRange1);
//        product1 = createProductIfAbsent(product1);
//
//        // newbie Product
//        Product product2 = new Product();
//        product2.setName("新手99999期");
//        product2.setTotalAmount(new BigDecimal(30000));
//        product2.setOnSaleDate(LocalDateTime.now());
//        product2.setActive(true);
//        product2.setCreatedBy(userAccount2);
//        product2.setAnnualPercentageRate(6.8f);
//        product2.setDiscription("新手99999期测试数据");
//        product2.setDuration(7);
//        product2.setInterestAccrualType(Product.InterestAccrualType.T);
//        product2.setInterestSettledDate(LocalDateTime.now());
//        // product2.setIssuedDateRange(new DateRange());
//        product2.setStatus(Status.FOR_SALE);
//        product2.setType(Product.Type.NEWBIE);
//        product2.setUnitPrice(new BigDecimal(1));
//        product2.setBill(bill2);
//        product2.setLicense(post3);
//        IntRange purchaseLimit2 = new IntRange();
//        purchaseLimit2.setMin(1);
//        purchaseLimit2.setMin(4000);
//        product2.setPurchaseLimit(purchaseLimit2);
//        product2.setRepaymentDeadline(LocalDateTime.now().plusDays(7));
//        DateRange issuedDateRange2 = new DateRange();
//        issuedDateRange2.setFrom(LocalDateTime.now().plusDays(5));
//        issuedDateRange2.setTo(LocalDateTime.now().plusDays(70));
//        // product2.setIssuedDateRange(issuedDateRange2);
//        product2 = createProductIfAbsent(product2);
//
//        // demand Product
//        Product product3 = new Product();
//        product3.setName("爆款99999期");
//        product3.setTotalAmount(new BigDecimal(888888888));
//        product3.setOnSaleDate(LocalDateTime.now());
//        product3.setActive(true);
//        product3.setCreatedBy(userAccount2);
//        product3.setAnnualPercentageRate(6.8f);
//        product3.setDiscription("爆款99999期测试数据");
//        product3.setDuration(7);
//        product3.setInterestAccrualType(Product.InterestAccrualType.T);
//        product3.setInterestSettledDate(LocalDateTime.now());
//        // product3.setIssuedDateRange(new DateRange());
//        product3.setStatus(Status.FOR_SALE);
//        product3.setType(Product.Type.HOT);
//        product3.setUnitPrice(new BigDecimal(1));
//        product3.setBill(bill3);
//        product3.setLicense(post4);
//        IntRange purchaseLimit3 = new IntRange();
//        purchaseLimit3.setMin(1);
//        purchaseLimit3.setMin(6000);
//        product3.setPurchaseLimit(purchaseLimit3);
//        product3.setRepaymentDeadline(LocalDateTime.now().plusDays(7));
//        DateRange issuedDateRange3 = new DateRange();
//        issuedDateRange3.setFrom(LocalDateTime.now().plusDays(1));
//        issuedDateRange3.setTo(LocalDateTime.now().plusDays(66));
//        // product3.setIssuedDateRange(issuedDateRange3);
//        product3 = createProductIfAbsent(product3);
//
//        // /////////////////////////////////////////////////////////////////////
//        // loading purchase order data
//        // /////////////////////////////////////////////////////////////////////
//        PurchaseOrder order1 = new PurchaseOrder();
//        order1.setProduct(product);
//        order1.setAmount(new BigDecimal(1300));
//        order1.setUser(savedTestUser1);
//        order1.setSerialNumber("002");
//        order1.setStatus(PurchaseOrder.Status.PENDING_PAYMENT);
//        createOrderIfAbsent(order1);
//
//        PurchaseOrder order2 = new PurchaseOrder();
//        order2.setProduct(product2);
//        order2.setAmount(new BigDecimal(1200));
//        order2.setSerialNumber("003");
//        order2.setStatus(PurchaseOrder.Status.PAID);
//        order2.setUser(savedTestUser1);
//        order2.setAccruedStartDate(LocalDate.now());
//        order2.setAccruedInterestAmount(new BigDecimal(12.0));
//
//        createOrderIfAbsent(order2);
//
//        PurchaseOrder order3 = new PurchaseOrder();
//        order3.setProduct(product2);
//        order3.setAmount(new BigDecimal(1000));
//        order3.setSerialNumber("004");
//        order3.setStatus(PurchaseOrder.Status.INTEREST_ACCRUED);
//        order3.setUser(savedTestUser1);
//        order3.setAccruedStartDate(LocalDate.now());
//        order3.setAccruedInterestAmount(new BigDecimal(19.0));
//        createOrderIfAbsent(order3);
//
//        PurchaseOrder order4 = new PurchaseOrder();
//        order4.setProduct(product2);
//        order4.setAmount(new BigDecimal(900));
//        order4.setSerialNumber("005");
//        order4.setStatus(PurchaseOrder.Status.CANCELED);
//        order4.setUser(savedTestUser1);
//        order4.setAccruedStartDate(LocalDate.now());
//        order4.setAccruedInterestAmount(new BigDecimal(11.0));
//        createOrderIfAbsent(order4);
//
//        PurchaseOrder order5 = new PurchaseOrder();
//        order5.setProduct(product2);
//        order5.setAmount(new BigDecimal(800));
//        order5.setSerialNumber("006");
//        order5.setStatus(PurchaseOrder.Status.IN_REPAYMENT);
//        order5.setUser(savedTestUser1);
//        order5.setAccruedStartDate(LocalDate.now());
//        order5.setAccruedInterestAmount(new BigDecimal(10.0));
//        createOrderIfAbsent(order5);
//
//        PurchaseOrder order6 = new PurchaseOrder();
//        order6.setProduct(product2);
//        order6.setAmount(new BigDecimal(70));
//        order6.setSerialNumber("007");
//        order6.setStatus(PurchaseOrder.Status.PAYMENT_FAILED);
//        order6.setUser(savedTestUser1);
//        order6.setAccruedStartDate(LocalDate.now());
//        order6.setAccruedInterestAmount(new BigDecimal(17.0));
//        createOrderIfAbsent(order6);
//
//        PurchaseOrder order7 = new PurchaseOrder();
//        order7.setProduct(product2);
//        order7.setAmount(new BigDecimal(1000));
//        order7.setSerialNumber("008");
//        order7.setStatus(PurchaseOrder.Status.REPAYMENT_COMPLETED);
//        order7.setUser(savedTestUser1);
//        order7.setAccruedStartDate(LocalDate.now());
//        order7.setAccruedInterestAmount(new BigDecimal(17.0));
//        createOrderIfAbsent(order7);
//    }
//
    private Post createPostIfAbsent(Post post) {
        final Post existedPost = postRepository.findByTitle(post.getTitle());
        if (existedPost == null) {
            return postRepository.save(post);
        }
        return existedPost;
    }

    private UserProfile createUserProfileIfAbsent(UserProfile profile) {
        if (log.isDebugEnabled()) {
            log.debug("findByAccountId @" + profile.getAccount().getId());
        }
        final UserProfile existedProfile = userProfileRepository.findByAccountId(profile.getAccount().getId());
        if (existedProfile == null) {
            return userProfileRepository.save(profile);
        }
        return existedProfile;
    }

//    private BankCardInfo createBankCardInfoIfAbsent(BankCardInfo bankCardInfo) {
//        if (log.isDebugEnabled()) {
//            log.debug("findByUserIdAndCardId @ userId  {}  openAccId  {}", bankCardInfo.getUser().getId(), bankCardInfo.getCardId());
//        }
//        final BankCardInfo existedBankCardInfo = bankCardInfoRepository.findByUserIdAndCardId(bankCardInfo.getUser().getId(), bankCardInfo.getCardId());
//        if (existedBankCardInfo == null) {
//            return bankCardInfoRepository.save(bankCardInfo);
//        }
//        return existedBankCardInfo;
//    }

//    private StaffProfile createStaffProfileIfAbsent(StaffProfile profile) {
//        final StaffProfile existedProfile
//                = staffProfileRepository.findByUserAccountId(profile.getUserAccount().getId());
//        if (existedProfile == null) {
//            return staffProfileRepository.save(profile);
//        }
//        return existedProfile;
//    }

//    private PurchaseOrder createOrderIfAbsent(PurchaseOrder order) {
//        final PurchaseOrder orderFindBySerialNumber = orderRepository.findBySerialNumber(order.getSerialNumber());
//        if (orderFindBySerialNumber == null) {
//            return orderRepository.save(order);
//        }
//        return orderFindBySerialNumber;
//    }

    private void createGrantedPermissionIfAbsent(GrantedPermission grantedPermission) {
        if (this.grantedPermissionRepository.findByRoleAndPermission(grantedPermission.getRole(),
                grantedPermission.getPermission()) == null) {
            grantedPermissionRepository.save(grantedPermission);
        }
    }

//    private Bank createBankIfAbsent(Bank bank) {
//        final Bank bankFindByName = this.bankRepository.findByName(bank.getName());
//        if (bankFindByName == null) {
//            return this.bankRepository.save(bank);
//        }
//
//        return bankFindByName;
//    }
//
//    private Enterprise createEnterpriseIfAbsent(Enterprise enterprise) {
//        final Enterprise entFindByName = this.enterpriseRepository.findByName(enterprise.getName());
//        if (entFindByName == null) {
//            return this.enterpriseRepository.save(enterprise);
//        }
//        return entFindByName;
//    }
//
//    private Bill createBillIfAbsent(Bill bill) {
//        final Bill billFindBySerialNumber = this.billRepository.findBySerialNumber(bill.getSerialNumber());
//        if (billFindBySerialNumber == null) {
//            return this.billRepository.save(bill);
//        }
//        return billFindBySerialNumber;
//    }
//
//    private BackLogItem createBackLogItemIfAbsent(BackLogItem backLogItem) {
//        final BackLogItem backLogItemCheck
//                = this.backLogItemRepository.checkBackLogItemExistTheBill(backLogItem.getBill().getId());
//        if (backLogItemCheck == null) {
//            return this.backLogItemRepository.save(backLogItem);
//        }
//        return backLogItemCheck;
//    }

    // private void loadReservedProductData() {
    //
    // if (log.isInfoEnabled()) {
    // log.info("loading reserved enterprise...");
    // }
    //
    // Address address = new Address();
    // address.setCity("上海");
    // address.setStreet("浦东");
    // address.setZipcode("200023");
    // UserAccount userAccount2 = new UserAccount("test3", passwordEncoder.encode("test123"),
    // "Test User 3", true,
    // "TEST");
    //
    // userAccount2.setRoles(Arrays.asList("TEST"));
    //
    // UserAccount saved2 = createUserAccountIfAbsent(userAccount2);
    //
    // Product product = new Product();
    // product.setName("");
    // product.setActive(true);
    //
    // // this.createProductIfAbsent(product);
    // }
    //
//    private Product createProductIfAbsent(Product product) {
//        final Product existedProduct = this.productRepository.findByName(product.getName());
//        if (existedProduct == null) {
//            return this.productRepository.save(product);
//        }
//        return existedProduct;
//    }

    private void initialzePostDatas() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(resourceLoader.getResource("classpath:/post_data.sql"));
        populator.setContinueOnError(true);
        DatabasePopulatorUtils.execute(populator, dataSource);
    }
}
