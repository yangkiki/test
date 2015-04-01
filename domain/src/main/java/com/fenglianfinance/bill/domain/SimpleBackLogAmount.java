package com.fenglianfinance.bill.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fenglianfinance.bill.domain.BackLogItem.Type;

public class SimpleBackLogAmount implements Serializable {
    private static final long serialVersionUID = 1L;

    private BigDecimal amount = BigDecimal.ZERO;

    private BackLogItem.Type type;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BackLogItem.Type getType() {
        return type;
    }

    public void setType(BackLogItem.Type type) {
        this.type = type;
    }

    public SimpleBackLogAmount() {
        super();
    }

    public SimpleBackLogAmount(BigDecimal amount, Type type) {
        super();
        this.amount = amount;
        this.type = type;
    }

    @Override
    public String toString() {
        return "SimpleBackLogAmount [amount=" + amount + ", type=" + type + "]";
    }

}
