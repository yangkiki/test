/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.fenglianfinance.bill.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.fenglianfinance.bill.DTOUtils;
import com.fenglianfinance.bill.captcha.CaptchaService;
import com.fenglianfinance.bill.domain.BankCardInfo;
import com.fenglianfinance.bill.domain.GrantedPermission;
import com.fenglianfinance.bill.domain.Message;
import com.fenglianfinance.bill.domain.Permission;
import com.fenglianfinance.bill.domain.PurchaseOrder;
import com.fenglianfinance.bill.domain.Role;
import com.fenglianfinance.bill.domain.StaffProfile;
import com.fenglianfinance.bill.domain.TransactionLog;
import com.fenglianfinance.bill.domain.TransactionType;
import com.fenglianfinance.bill.domain.UserAccount;
import com.fenglianfinance.bill.domain.UserProfile;
import com.fenglianfinance.bill.exception.CaptchaMismatchedException;
import com.fenglianfinance.bill.exception.MobileNumberExistedException;
import com.fenglianfinance.bill.exception.PasswordMismatchedException;
import com.fenglianfinance.bill.exception.ResourceNotFoundException;
import com.fenglianfinance.bill.exception.RoleNameExistedException;
import com.fenglianfinance.bill.exception.UsernameExistedException;
import com.fenglianfinance.bill.model.BankCardInfoDetails;
import com.fenglianfinance.bill.model.CategorizedPermission;
import com.fenglianfinance.bill.model.MessageDetails;
import com.fenglianfinance.bill.model.NewPasswordForm;
import com.fenglianfinance.bill.model.OrderStatistics;
import com.fenglianfinance.bill.model.OrderStatisticsItem;
import com.fenglianfinance.bill.model.PasswordForm;
import com.fenglianfinance.bill.model.PermissionDetails;
import com.fenglianfinance.bill.model.PermissionForm;
import com.fenglianfinance.bill.model.PurchaseOrderModel;
import com.fenglianfinance.bill.model.RegisterForm;
import com.fenglianfinance.bill.model.RoleDetails;
import com.fenglianfinance.bill.model.RoleForm;
import com.fenglianfinance.bill.model.SignupForm;
import com.fenglianfinance.bill.model.SystemUserDetails;
import com.fenglianfinance.bill.model.SystemUserForm;
import com.fenglianfinance.bill.model.TransactionLogDetails;
import com.fenglianfinance.bill.model.TransactionLogStatistics;
import com.fenglianfinance.bill.model.UserAccountDetails;
import com.fenglianfinance.bill.model.UserAcctView;
import com.fenglianfinance.bill.model.UserProfileDetails;
import com.fenglianfinance.bill.model.UserSearchCriteria;
import com.fenglianfinance.bill.repository.BankCardInfoRepository;
import com.fenglianfinance.bill.repository.EnterpriseRepository;
import com.fenglianfinance.bill.repository.GrantedPermissionRepository;
import com.fenglianfinance.bill.repository.MessageRepository;
import com.fenglianfinance.bill.repository.MessageSpecifications;
import com.fenglianfinance.bill.repository.OrderRepository;
import com.fenglianfinance.bill.repository.PermissionRepository;
import com.fenglianfinance.bill.repository.RoleRepository;
import com.fenglianfinance.bill.repository.StaffProfileRepository;
import com.fenglianfinance.bill.repository.TransactionLogRepository;
import com.fenglianfinance.bill.repository.TransactionLogSpecifications;
import com.fenglianfinance.bill.repository.UserProfileRepository;
import com.fenglianfinance.bill.repository.UserRepository;
import com.fenglianfinance.bill.repository.UserSpecifications;
import java.time.LocalDateTime;

/**
 *
 * @author hantsy
 */
@Service
@Transactional
public class UserService {

    public final static Logger log = LoggerFactory.getLogger(UserService.class);

    @Inject
    private UserRepository userRepository;

    @Inject
    private RoleRepository roleRepository;

    @Inject
    private PermissionRepository permissionRepository;

    @Inject
    private GrantedPermissionRepository grantedPermissionRepository;

    @Inject
    private StaffProfileRepository staffProfileRepository;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private CaptchaService captchaService;

    @Inject
    private SmsService smsService;

    @Inject
    private TokenService tokenService;

    @Inject
    private MessageRepository messageRepository;

    @Inject
    private UserProfileRepository userProfileRepository;

    @Inject
    private EnterpriseRepository enterpriseRepository;

    @Inject
    private BankCardInfoRepository cardRepository;

    @Inject
    private TransactionLogRepository transactionLogRepository;

    @Inject
    private OrderRepository orderRepository;

    public Page<SystemUserDetails> findUsers(String keyword, String role, String activeStatus, String locked, Pageable page) {

        if (log.isDebugEnabled()) {
            log.debug("findUsers by keyword@" + keyword + ", role:" + role + ", locked@" + locked + ", page@" + page);
        }

        Page<StaffProfile> users
                = staffProfileRepository.findAll(
                        UserSpecifications.filterUserAccountsByKeywordAndRole(keyword, role, activeStatus, locked), page);

        if (log.isDebugEnabled()) {
            log.debug("total elements@" + users.getTotalElements());
        }

        return DTOUtils.mapPage(users, SystemUserDetails.class);
    }

    public Page<UserAccountDetails> findUserAccounts(UserSearchCriteria criteria, Pageable page) {

        if (log.isDebugEnabled()) {
            log.debug("findUsers by criteria@" + criteria + ", page@" + page);
        }

        Page<UserAccount> users
                = userRepository.findAll(
                        UserSpecifications.filterUserAccountsByKeyword(
                                criteria.getQ(),
                                UserAccount.Type.USER,
                                Role.ROLE_USER,
                                criteria.getActive(),
                                criteria.getLocked(),
                                criteria.getMin(),
                                criteria.getMax()
                        ), page);

        if (log.isDebugEnabled()) {
            log.debug("total elements@" + users.getTotalElements());
        }

        return DTOUtils.mapPage(users, UserAccountDetails.class);
    }

    public UserAccountDetails saveUser(SystemUserForm form) {
        Assert.notNull(form, " @@ SystemUserForm is null");
        Assert.notNull(form.getUserAccount(), " @@ SystemUserForm.getUserAccount is null");

        if (log.isDebugEnabled()) {
            log.debug("saving user@" + form);
        }

        if (userRepository.findByUsername(form.getUserAccount().getUsername()) != null) {
            throw new UsernameExistedException(form.getUserAccount().getUsername());
        }

        UserAccount user = DTOUtils.map(form.getUserAccount(), UserAccount.class);

        if (StringUtils.hasText(form.getUserAccount().getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(passwordEncoder.encode("test@123456"));
        }

        user.setType(UserAccount.Type.STAFF);
        user.setActive(true);
        user.setLocked(false);

        UserAccount saved = userRepository.save(user);

        StaffProfile sysUser = DTOUtils.map(form, StaffProfile.class);

        sysUser.setUserAccount(saved);

        staffProfileRepository.save(sysUser);

        if (log.isDebugEnabled()) {
            log.debug("saved user @" + saved);
        }

        return DTOUtils.map(saved, UserAccountDetails.class);
    }

    public void updateUser(Long id, SystemUserForm form) {
        Assert.notNull(id, "user id can not be null");
        Assert.notNull(form.getUserAccount(), " @@ SystemUserForm.getUserAccount is null");

        if (log.isDebugEnabled()) {
            log.debug("find user by id @" + id);
        }

        UserAccount user = userRepository.findOne(form.getUserAccount().getId());

        if (user == null) {
            throw new ResourceNotFoundException(id);
        }
        DTOUtils.mapTo(form.getUserAccount(), user);

        UserAccount userSaved = userRepository.save(user);

        StaffProfile systemUser = staffProfileRepository.findOne(id);

        if (systemUser == null) {
            throw new ResourceNotFoundException(id);
        }

        DTOUtils.mapTo(form, systemUser);

        systemUser.setUserAccount(userSaved);

        StaffProfile saved = staffProfileRepository.save(systemUser);

        if (log.isDebugEnabled()) {
            log.debug("updated user @" + saved);
        }
    }

    public void updatePassword(Long id, PasswordForm form) {
        Assert.notNull(id, "user id can not be null");
        if (log.isDebugEnabled()) {
            log.debug("find user by id @" + id);
        }

        UserAccount user = userRepository.findOne(id);

        if (!passwordEncoder.matches(form.getOldPassword(), user.getPassword())) {
            throw new PasswordMismatchedException();
        }

        user.setPassword(passwordEncoder.encode(form.getNewPassword()));

        UserAccount saved = userRepository.save(user);

        if (log.isDebugEnabled()) {
            log.debug("updated user @" + saved);
        }
    }

    public void deactivateUser(Long id) {
        Assert.notNull(id, "user id can not be null");
        userRepository.updateActiveStatus(id, false);
    }

    public void activateUser(Long id) {
        Assert.notNull(id, "user id can not be null");
        userRepository.updateActiveStatus(id, true);
    }

    public SystemUserDetails findUserById(Long id) {
        Assert.notNull(id, "user id can not be null");
        if (log.isDebugEnabled()) {
            log.debug("find user by id @" + id);
        }

        StaffProfile user = staffProfileRepository.findOne(id);

        if (user == null) {
            throw new ResourceNotFoundException(id);
        }

        return DTOUtils.map(user, SystemUserDetails.class);
    }

    public UserAccountDetails findUserAccountById(Long id) {
        Assert.notNull(id, "user id can not be null");
        if (log.isDebugEnabled()) {
            log.debug("find user by id @" + id);
        }

        UserAccount user = userRepository.findOne(id);

        if (user == null) {
            throw new ResourceNotFoundException(id);
        }

        return DTOUtils.map(user, UserAccountDetails.class);
    }

    public UserAccountDetails findUserByUsername(String username) {
        Assert.notNull(username, "username can not be null");
        if (log.isDebugEnabled()) {
            log.debug("find user by id @" + username);
        }

        UserAccount user = userRepository.findByUsername(username);

        if (user == null) {
            throw new ResourceNotFoundException(username + " not found");
        }

        return DTOUtils.map(user, UserAccountDetails.class);
    }

    public void assignRoles(Long userId, String... roles) {
        Assert.notNull(userId, "user id can not be null");
        Assert.notEmpty(roles, "roles be can empty");

        if (log.isDebugEnabled()) {
            log.debug(">>>assign roles @" + roles + " to user@" + userId);
        }

        UserAccount account = userRepository.findOne(userId);

        account.getRoles().clear();
        account.setRoles(Arrays.asList(roles));

        userRepository.saveAndFlush(account);
    }

    public void grantPermissions(String roleName, String... perms) {
        Assert.notNull(roleName, "roleName can not be null");
        Assert.notEmpty(perms, "perms be can empty");

        if (log.isDebugEnabled()) {
            log.debug(">>>grant permissions @" + perms + "  to role @" + roleName);
        }

        for (String p : perms) {
            createGrantedPermissionIfAbsent(roleName, p);
        }
    }

    public void revokePermissions(String roleName, String[] perms) {
        Assert.notNull(roleName, "roleName can not be null");
        Assert.notEmpty(perms, "perms be can empty");

        if (log.isDebugEnabled()) {
            log.debug(">>>revoke permissions @" + perms + "  of role @" + roleName);
        }

        for (String p : perms) {
            grantedPermissionRepository.removeByRoleNameAndPermission(roleName, p);
        }
    }

    public List<String> findGrantedPermissionNamesByRoleName(String role) {
        if (log.isDebugEnabled()) {
            log.debug("find granted permissions by role @" + role);
        }

        List<GrantedPermission> perms = grantedPermissionRepository.findByRole(role);

        List<String> results = new ArrayList<>();

        for (GrantedPermission perm : perms) {

            results.add(perm.getPermission());
        }

        return results;
    }

    public List<CategorizedPermission> findAllCategorizedPermissions() {
        if (log.isDebugEnabled()) {
            log.debug("find all active permission names categorized by Category @");
        }

        List<Permission> permissions = permissionRepository.findByActiveIsTrue();

        Map<String, List<String>> categorizedPermissions = new HashMap<>();

        for (Permission p : permissions) {
            List<String> permNames = categorizedPermissions.get(p.getCategory().name());
            if (permNames == null) {
                permNames = new ArrayList<>();
                categorizedPermissions.put(p.getCategory().name(), permNames);
            }
            if (!permNames.contains(p.getName())) {
                permNames.add(p.getName());
            }
        }

        List<CategorizedPermission> catePerms = new ArrayList<>();

        for (String key : categorizedPermissions.keySet()) {
            catePerms.add(new CategorizedPermission(key, categorizedPermissions.get(key)));
        }

        if (log.isDebugEnabled()) {
            log.debug("return categorized permissions@" + categorizedPermissions);
        }

        return catePerms;
    }

    // role
    public RoleDetails saveRole(RoleForm form) {
        if (log.isDebugEnabled()) {
            log.debug("saving role@" + form);
        }

        if (roleRepository.findByName(form.getName()) != null) {
            throw new RoleNameExistedException(form.getName());
        }

        Role role = DTOUtils.map(form, Role.class);

        Role saved = roleRepository.save(role);

        if (log.isDebugEnabled()) {
            log.debug("saved role @" + saved);
        }

        return DTOUtils.map(saved, RoleDetails.class);
    }

    public Page<RoleDetails> findRoles(String name, boolean active, Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("findUsers by name@" + name + ", page@" + page);
        }
        Page<Role> roles = roleRepository.findAll(UserSpecifications.filterRoleByRoleName(name, active), page);

        if (log.isDebugEnabled()) {
            log.debug("total elements@" + roles.getTotalElements());
        }
        return DTOUtils.mapPage(roles, RoleDetails.class);
    }

    public List<Role> findActiveRoles() {
        if (log.isDebugEnabled()) {
            log.debug("call findActiveRoles ");
        }
        List<Role> roles = roleRepository.findByActiveIsTrue();

        if (log.isDebugEnabled()) {
            log.debug("total elements@" + roles);
        }
        return roles;
    }

    public RoleDetails findRoleById(Long id) {
        Assert.notNull(id, "role id can not be null");
        if (log.isDebugEnabled()) {
            log.debug("find role by id @" + id);
        }

        Role role = roleRepository.findOne(id);

        if (role == null) {
            throw new ResourceNotFoundException(id);
        }

        return DTOUtils.map(role, RoleDetails.class);
    }

    public void updateRole(Long id, RoleForm form) {
        Assert.notNull(id, "role id can not be null");
        if (log.isDebugEnabled()) {
            log.debug("find role by id @" + id);
        }
        Role role = roleRepository.findOne(id);

        if (role == null) {
            throw new ResourceNotFoundException(id);
        }
        DTOUtils.mapTo(form, role);

        Role saved = roleRepository.save(role);

        if (log.isDebugEnabled()) {
            log.debug("updated Role @" + saved);
        }
    }

    public Permission findResourceById(Long id) {
        Assert.notNull(id, "resource id can not be null");
        if (log.isDebugEnabled()) {
            log.debug("find resource by id @" + id);
        }

        Permission resource = permissionRepository.findOne(id);

        if (resource == null) {
            throw new ResourceNotFoundException(id);
        }

        return DTOUtils.map(resource, Permission.class);
    }

    public void createGrantedPermissionIfAbsent(String roleName, String p) {
        if (log.isDebugEnabled()) {
            log.debug("create or update GrantedPermission@ roleName @" + roleName + ", permision @" + p);
        }

        //  GrantedPermissionRepository 
        GrantedPermission grantedPermission = grantedPermissionRepository.findByRoleAndPermission(roleName, p);

        if (grantedPermission == null) {
            log.debug("can not find granted permision@" + p + "to roleName @" + roleName + ", create one now.");

            grantedPermission = new GrantedPermission(roleName, p);

            grantedPermissionRepository.save(grantedPermission);
        }

    }

    public void deactivateRole(Long id) {
        roleRepository.updateActiveStatus(id, false);
    }

    public void activateRole(Long id) {
        roleRepository.updateActiveStatus(id, true);
    }

    public Page<PermissionDetails> findAllPermissions(String q, Pageable page) {
        Page<Permission> perms = permissionRepository.findByNameLike("%" + q + "%", page);

        return DTOUtils.mapPage(perms, PermissionDetails.class);
    }

    public void updatePermission(Long id, PermissionForm form) {
        Assert.notNull(id, "id can not be null");
        Assert.notNull(form, "form can not be null");

        Permission permission = permissionRepository.findOne(id);

        DTOUtils.mapTo(form, permission);

        permissionRepository.save(permission);
    }

    public UserAccountDetails registerUser(SignupForm form) {
        Assert.notNull(form, "signup form can not be null");
        Assert.notNull(form.getUsername(), "username can not be null");
        Assert.notNull(form.getCaptchaResponse(), "captcha can not be null");

        if (log.isDebugEnabled()) {
            log.debug("saving user@" + form);
        }

        if (userRepository.findByUsername(form.getUsername()) != null) {
            throw new UsernameExistedException(form.getUsername());
        }

        if (form.getCaptchaResponse() == null || !captchaService.verifyImgCaptcha(form.getCaptchaResponse())) {
            throw new CaptchaMismatchedException();
        }

        UserAccount user = DTOUtils.map(form, UserAccount.class);
        user.setRoles(Arrays.asList("USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserAccount saved = userRepository.save(user);

        if (log.isDebugEnabled()) {
            log.debug("saved user @" + saved);
        }

        return DTOUtils.map(saved, UserAccountDetails.class);
    }
	
	public UserAccountDetails registerUser(RegisterForm form) {
		Assert.notNull(form, "RegisterForm can not be null");
		Assert.notNull(form.getMobileNumber(), "mobileNumber can not be null");

		if (log.isDebugEnabled()) {
			log.debug("saving user@" + form);
		}

		if (userRepository.findByMobileNumber(form.getMobileNumber()) != null) {
			throw new MobileNumberExistedException(form.getMobileNumber());
		}

		UserAccount user = DTOUtils.map(form, UserAccount.class);
		user.setRoles(Arrays.asList("USER"));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// TODO 用户名默认为手机号
    	user.setUsername(form.getMobileNumber());
		UserAccount saved = userRepository.save(user);

		if (log.isDebugEnabled()) {
			log.debug("saved user @" + saved);
		}

		return DTOUtils.map(saved, UserAccountDetails.class);
	}

    public void retrievePasswordByMobileNumber(NewPasswordForm form) {

        Assert.notNull(form, " form can't  be null ");
        Assert.notNull(form.getMobileNumber(), " MobileNumber can't  be null ");

        validateCaptcha(form.getCaptchaValue(), form.getMobileNumber(), true);

        UserAccount user = userRepository.findByMobileNumber(form.getMobileNumber());

        if (null == user) {
            throw new ResourceNotFoundException(" User not found");
        }
        user.setPassword(passwordEncoder.encode(form.getPassword()));

        UserAccount saved = userRepository.save(user);

        if (log.isDebugEnabled()) {
            log.debug("updated user @" + saved);
        }
    }

    private void validateCaptcha(String smsCode, String mobileNumber, boolean isRemoveSmsCode) {
        if (!smsService.validate(mobileNumber, smsCode, isRemoveSmsCode)) {
            throw new CaptchaMismatchedException();
        }
    }

    public Page<MessageDetails> getReceivedMessages(Long userId, Boolean read, Pageable p) {
        Assert.notNull(userId, " userId can't  be null ");
        Page<Message> messages = messageRepository.findAll(MessageSpecifications.filterReceivedMessages(userId, read), p);

        if (log.isDebugEnabled()) {
            log.debug("found mesages@" + messages.getTotalElements());
        }

        return DTOUtils.mapPage(messages, MessageDetails.class);
    }

    public long countUnreadMessages(Long userId) {
        Assert.notNull(userId, " userId can't  be null ");

        long count = messageRepository.count(MessageSpecifications.filterReceivedMessages(userId, Boolean.valueOf(false)));

        if (log.isDebugEnabled()) {
            log.debug("found mesages count@" + count);
        }

        return count;
    }

    public UserAcctView getUserAcctView(UserAccount userAccount) {
        UserAcctView userAcctView = new UserAcctView();
        Assert.notNull(userAccount, " userAccount can't  be null ");
        UserAccountDetails userAccountDetails = new UserAccountDetails();
        DTOUtils.mapTo(userAccount, userAccountDetails);

        Pageable page = new PageRequest(0, 10);

        Page<PurchaseOrder> recentPurchasedOrderPage = orderRepository
                .findHotPaidPurchaseOrdersPaidDateDesByUsername(
                        userAccount.getUsername(), page);
        List<PurchaseOrderModel> recentPurchasedOrderModels = DTOUtils
                .mapList(recentPurchasedOrderPage.getContent(),
                        PurchaseOrderModel.class);

        Page<PurchaseOrder> maturePurchasedOrderPage = orderRepository
                .findHotPaidPurchaseOrdersCompletedDateDesByUsername(
                        userAccount.getUsername(), page);
        List<PurchaseOrderModel> maturePurchasedOrderModels = DTOUtils
                .mapList(maturePurchasedOrderPage.getContent(),
                        PurchaseOrderModel.class);

        // TODO 账户余额
        // 爆款,定期累计收益
        // 活期累计收益
        userAcctView.setUserAccountDetails(userAccountDetails);
        userAcctView.setRecentPurchasedOrders(recentPurchasedOrderModels);
        userAcctView.setMaturePurchasedOrders(maturePurchasedOrderModels);
        return userAcctView;
    }

    public void markAllMessagesAsRead(Long userId) {
        Assert.notNull(userId, " user id can't  be empty ");

        messageRepository.batchUpdateReadStatus(userId);

    }

    public Page<MessageDetails> getSentMessages(Long userId, Pageable page) {
        Assert.notNull(userId, " userId can't  be null ");
        Page<Message> messages = messageRepository.findAll(MessageSpecifications.filterSentMessages(userId), page);

        if (log.isDebugEnabled()) {
            log.debug("found mesages@" + messages.getTotalElements());
        }

        return DTOUtils.mapPage(messages, MessageDetails.class);

    }

    public UserProfileDetails findUserProfileByUserId(Long id) {
        Assert.notNull(id, " account id can't  be null ");

        if (log.isDebugEnabled()) {
            log.debug("fetch user profile by account id@" + id);
        }

        UserProfile profile = userProfileRepository.findByAccountId(id);

        if (profile != null) {
            return DTOUtils.map(profile, UserProfileDetails.class);
        }

        return null;
    }

    public void unlockUser(Long id) {
        Assert.notNull(id, "user id can not be null");
        userRepository.updateLockedStatus(id, false);
    }

    public void lockUser(Long id) {
        Assert.notNull(id, "user id can not be null");
        userRepository.updateLockedStatus(id, true);
    }

    public MessageDetails getMessage(Long id) {
        Assert.notNull(id, "message id can not be null");
        Message msg = messageRepository.findOne(id);

        if (msg == null) {
            throw new ResourceNotFoundException(id);
        }

        return DTOUtils.map(msg, MessageDetails.class);
    }

    public void deleteMessage(Long id) {
        Assert.notNull(id, "message id can not be null");
        messageRepository.delete(id);
    }

    public RoleDetails findRoleByName(String name) {
        Assert.notNull(name, "role name can not be null");
        if (log.isDebugEnabled()) {
            log.debug("find role by name @" + name);
        }

        Role role = roleRepository.findByName(name);

        if (role == null) {
            throw new ResourceNotFoundException(name);
        }

        return DTOUtils.map(role, RoleDetails.class);
    }

//    public AccountingInfoDetails findAccountingInfoyUserId(Long id) {
//        Assert.notNull(id, " account id can't  be null ");
//
//        if (log.isDebugEnabled()) {
//            log.debug("fetch user profile by account id@" + id);
//        }
//
//        AccountingInfo user = .findByUserId(id);
//
//        if (user != null) {
//            return DTOUtils.map(user, AccountingInfoDetails.class);
//        }
//
//        return null;
//    }
    public List<BankCardInfoDetails> findBoundCardsByUserId(Long id) {
        Assert.notNull(id, " account id can't  be null ");

        if (log.isDebugEnabled()) {
            log.debug("fetch user profile by account id@" + id);
        }

        List<BankCardInfo> cards = cardRepository.findByUserId(id);

        if (cards != null && !cards.isEmpty()) {
            return DTOUtils.mapList(cards, BankCardInfoDetails.class);
        }

        return Collections.emptyList();
    }

    public Page<TransactionLogDetails> findTransactionLogsByUserId(Long id, Pageable page) {
        Assert.notNull(id, "user account id can't  be null ");

        if (log.isDebugEnabled()) {
            log.debug("fetch user transaction logs by user id@" + id);
        }

        Page<TransactionLog> logs = transactionLogRepository.findAll(TransactionLogSpecifications.filterByCriteria(id, null), page);

        return DTOUtils.mapPage(logs, TransactionLogDetails.class);
    }

    public TransactionLogStatistics getUserAcctTransactionLogStatisticsByType(
            String username, TransactionType type) {
        Assert.notNull(username, "user account username can't be null ");
        if (log.isDebugEnabled()) {
            log.debug("fetch user transaction logs by username@" + username
                    + " and logs type@" + type);
        }
		BigDecimal sumAmount = transactionLogRepository
				.sumTransactionLogAmount(username, type);
		sumAmount = (sumAmount == null ? BigDecimal.ZERO : sumAmount);
        int times = transactionLogRepository
                .countTransactionLog(username, type);

        TransactionLogStatistics transactionLogStatistics = new TransactionLogStatistics();
        transactionLogStatistics.setSumAmount(sumAmount);
        transactionLogStatistics.setTimes(times);
        return transactionLogStatistics;
    }

    public Page<TransactionLogDetails> getUserAcctTransactionLogsByType(
            String username, TransactionType type, Pageable page) {
        Assert.notNull(username, "user account username can't be null ");
        if (log.isDebugEnabled()) {
            log.debug("fetch user transaction logs by to.username@" + username
                    + " and logs type@" + type);
        }
        Page<TransactionLog> logs = transactionLogRepository
                .findByToUsernameAndType(username, type, page);
        return DTOUtils.mapPage(logs, TransactionLogDetails.class);
    }

    public OrderStatistics calculateOrderStatistics(Long id) {
        Assert.notNull(id, "user id can not be null");
        Map<String, BigDecimal> items = orderRepository.sumOrdersByUser(id);

        BigDecimal totalAmount = BigDecimal.ZERO;
        for (String item : items.keySet()) {
            totalAmount = totalAmount.add(items.get(item));
        }

        Map<String, BigDecimal> activeItems = orderRepository.sumActiveOrdersByUser(id);

        BigDecimal activeAmount = BigDecimal.ZERO;
        for (String activeItem : activeItems.keySet()) {
            activeAmount = activeAmount.add(activeItems.get(activeItem));
        }

        Map<String, BigDecimal> interestItems = orderRepository.sumOrderInterestsByUser(id);

        BigDecimal totalInterestAmount = BigDecimal.ZERO;
        for (String item : interestItems.keySet()) {
            totalInterestAmount = totalInterestAmount.add(interestItems.get(item));
        }

        Map<String, BigDecimal> activeInterestItems = orderRepository.sumActiveOrderInterestsByUser(id);

        BigDecimal activeInterestAmount = BigDecimal.ZERO;
        for (String activeInterestItem : activeInterestItems.keySet()) {
            activeInterestAmount = activeInterestAmount.add(activeInterestItems.get(activeInterestItem));
        }

        OrderStatistics stat = new OrderStatistics();
        stat.setActiveItems(activeItems);
        stat.setItems(items);
        stat.setActiveInterestItems(activeInterestItems);
        stat.setInterestItems(interestItems);
        
        stat.setTotalActiveInterestAmount(activeInterestAmount);
        stat.setTotalInterestAmount(totalInterestAmount);
        stat.setTotalAmount(totalAmount);
        stat.setTotalActiveAmount(activeAmount);

        return stat;

    }
}
