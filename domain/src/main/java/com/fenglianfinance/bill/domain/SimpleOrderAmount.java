package com.fenglianfinance.bill.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fenglianfinance.bill.domain.BackLogItem.Type;

public class SimpleOrderAmount implements Serializable {
    private static final long serialVersionUID = 1L;

    private BigDecimal amount = BigDecimal.ZERO;

    private Product.Type type;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Product.Type getType() {
        return type;
    }

    public void setType(Product.Type type) {
        this.type = type;
    }

    public SimpleOrderAmount() {
        super();
    }

    public SimpleOrderAmount(BigDecimal amount, Product.Type type) {
        this.amount = amount;
        this.type = type;
    }
    
    public String getConvertType(){
        if(this.type.equals(Product.Type.HOT)||this.type.equals(Product.Type.NEWBIE)){
            return BackLogItem.Type.SINGLE.toString();
        }else{
            return this.type.toString();
        }
    }

    @Override
    public String toString() {
        return "SimpleOrderAmount [amount=" + amount + ", type=" + type + "]";
    }

}
