/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.api.user;

import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.CategorizedPermission;
import com.moxian.ng.model.PermissionDetails;
import com.moxian.ng.model.PermissionForm;
import com.moxian.ng.service.UserService;
import java.util.List;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 */
@RestController()
@RequestMapping(value = ApiConstants.URI_API_MGT)
public class PermissionMgtController {

    public static final Logger log = LoggerFactory.getLogger(PermissionMgtController.class);

    @Inject
    private UserService userService;

    @RequestMapping(value = {ApiConstants.URI_PERMISSIONS}, method = RequestMethod.GET, params = "f=CATEGORIZED")
    @ResponseBody
    public ResponseEntity<List<CategorizedPermission>> getPermissionsByCategory() {
        if (log.isDebugEnabled()) {
            log.debug("find categorised permissions @");
        }

        List<CategorizedPermission> permissions = userService.findAllCategorizedPermissions();

        if (log.isDebugEnabled()) {
            log.debug("found permissions@" + permissions);
        }

        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @RequestMapping(value = {ApiConstants.URI_PERMISSIONS}, method = RequestMethod.GET, params = "!f")
    @ResponseBody
    public ResponseEntity<Page<PermissionDetails>> getPermissions(@RequestParam("q") String q, @PageableDefault(page = 0, size = 10) Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("find permissions by filter @" + q);
        }

        Page<PermissionDetails> permissions = userService.findAllPermissions(q, page);

        if (log.isDebugEnabled()) {
            log.debug("found permissions@" + permissions);
        }

        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @RequestMapping(value = {ApiConstants.URI_PERMISSIONS + "/{id}"}, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Void> updatePermission(@PathVariable("id") Long id, @RequestBody PermissionForm form) {
        if (log.isDebugEnabled()) {
            log.debug("udpate user @" + id + " data @" + form);
        }

        userService.updatePermission(id, form);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
