package com.fenglianfinance.bill.domain;

import java.time.LocalDateTime;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.fenglianfinance.bill.domain.support.PersistableEntity;


@Entity
@Table(name = "login_history")
@Access(AccessType.FIELD)
public class LoginHistory extends PersistableEntity<Long> {

    private static final long serialVersionUID = 1L;

    private String ip;

    private LocalDateTime createTime;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

}
