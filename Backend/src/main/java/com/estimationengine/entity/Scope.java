package com.estimationengine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Scope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scopeId;
    private String scope;
    @ManyToMany(mappedBy = "scope",cascade = CascadeType.ALL)
    private List<Customer> customer;

    public int getScopeId() {
        return scopeId;
    }

    public void setScopeId(int scopeId) {
        this.scopeId = scopeId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }
}

