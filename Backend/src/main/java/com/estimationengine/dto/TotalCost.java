package com.estimationengine.dto;

import java.util.List;

public class TotalCost {

    private String customerName;
    private List<AppSizeEffortDTO> scopes;
    private int duration ;
    private double cost;
    private int totalNumberOfApplicatinos;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getTotalNumberOfApplicatinos() {
        return totalNumberOfApplicatinos;
    }

    public void setTotalNumberOfApplicatinos(int totalNumberOfApplicatinos) {
        this.totalNumberOfApplicatinos = totalNumberOfApplicatinos;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<AppSizeEffortDTO> getScopes() {
        return scopes;
    }

    public void setScopes(List<AppSizeEffortDTO> scopes) {
        this.scopes = scopes;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    public TotalCost(String customerName, List<AppSizeEffortDTO> scopes, int duration) {
        this.customerName = customerName;
        this.scopes = scopes;
        this.duration = duration;
    }

    public TotalCost(){}
}
