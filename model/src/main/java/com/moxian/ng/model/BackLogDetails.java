package com.moxian.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BackLogDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
