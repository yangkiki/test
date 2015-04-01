/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.moxian.ng.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.moxian.ng.DTOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.moxian.ng.captcha.CaptchaService;
import com.moxian.ng.domain.GrantedPermission;
import com.moxian.ng.domain.Message;
import com.moxian.ng.domain.Permission;
import com.moxian.ng.domain.Role;
import com.moxian.ng.domain.StaffProfile;
import com.moxian.ng.domain.UserAccount;
import com.moxian.ng.domain.UserProfile;
import com.moxian.ng.exception.CaptchaMismatchedException;
import com.moxian.ng.exception.MobileNumberExistedException;
import com.moxian.ng.exception.PasswordMismatchedException;
import com.moxian.ng.exception.ResourceNotFoundException;
import com.moxian.ng.exception.RoleNameExistedException;
import com.moxian.ng.exception.UsernameExistedException;
import com.moxian.ng.model.CategorizedPermission;
import com.moxian.ng.model.MessageDetails;
import com.moxian.ng.model.NewPasswordForm;
import com.moxian.ng.model.PasswordForm;
import com.moxian.ng.model.PermissionDetails;
import com.moxian.ng.model.PermissionForm;
import com.moxian.ng.model.RegisterForm;
import com.moxian.ng.model.RoleDetails;
import com.moxian.ng.model.RoleForm;
import com.moxian.ng.model.SignupForm;
import com.moxian.ng.model.SystemUserDetails;
import com.moxian.ng.model.SystemUserForm;
import com.moxian.ng.model.UserAccountDetails;
import com.moxian.ng.model.UserProfileDetails;
import com.moxian.ng.model.UserSearchCriteria;

import com.moxian.ng.repository.GrantedPermissionRepository;
import com.moxian.ng.repository.MessageRepository;
import com.moxian.ng.repository.MessageSpecifications;

import com.moxian.ng.repository.PermissionRepository;
import com.moxian.ng.repository.RoleRepository;
import com.moxian.ng.repository.StaffProfileRepository;
import com.moxian.ng.repository.UserProfileRepository;
import com.moxian.ng.repository.UserRepository;
import com.moxian.ng.repository.UserSpecifications;

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
}
