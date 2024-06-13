package com.estimationengine.dto;


import com.estimationengine.entity.Customer;
import com.estimationengine.entity.Scope;

import java.util.List;

public class CustomerScopesDTO {

    private int id;
    private String customerName;
    private List<Integer> scopes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Integer> getScopes() {
        return scopes;
    }

    public void setScopes(List<Integer> scopes) {
        this.scopes = scopes;
    }
}
