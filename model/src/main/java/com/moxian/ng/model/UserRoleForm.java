package com.moxian.ng.model;

import java.io.Serializable;
import java.util.List;

public class UserRoleForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Long> roleIds;

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public String toString() {
        return "UserRoleForm [roleIds=" + roleIds + "]";
    }

}
