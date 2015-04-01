package com.moxian.ng.domain;

import com.moxian.ng.domain.support.AuditableEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

//import java.util.ListIterator;

@Entity
@Table(//
        name = "user_view_statistics"
)
@Access(AccessType.FIELD)
public class UserViewStatistic extends AuditableEntity<UserViewStatistic, Long> {


    private static final long serialVersionUID = 1L;

    private String ipAddr;

    private String user;
    private String url;

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
