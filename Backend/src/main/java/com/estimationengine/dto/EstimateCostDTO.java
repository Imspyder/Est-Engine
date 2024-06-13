package com.estimationengine.dto;

import java.util.List;

public class EstimateCostDTO {
    private String customerName;
    private List<AppSizeEffortDTO> scopes;
    private int definedDurationInYears;

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

    public int getDefinedDurationInYears() {
        return definedDurationInYears;
    }

    public void setDefinedDurationInYears(int definedDurationInYears) {
        this.definedDurationInYears = definedDurationInYears;
    }

    public EstimateCostDTO(String customerName, List<AppSizeEffortDTO> scopes, int definedDurationInYears) {
        this.customerName = customerName;
        this.scopes = scopes;
        this.definedDurationInYears = definedDurationInYears;
    }

    public EstimateCostDTO(){}
}
