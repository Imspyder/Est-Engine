package com.estimationengine.dto;

import java.util.List;

public class AppSizeEffortDTO {

    private String scope;
    private List<AppEffortCountDTO> appEffortCounts;
    private double cost;
    private int duration;
    private int numberOfApplications;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getNumberOfApplications() {
        return numberOfApplications;
    }

    public void setNumberOfApplications(int numberOfApplications) {
        this.numberOfApplications = numberOfApplications;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<AppEffortCountDTO> getAppEffortCounts() {
        return appEffortCounts;
    }

    public void setAppEffortCounts(List<AppEffortCountDTO> appEffortCounts) {
        this.appEffortCounts = appEffortCounts;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public AppSizeEffortDTO(String scope, List<AppEffortCountDTO> appEffortCounts, double cost, int numberOfApplications) {
        this.scope = scope;
        this.appEffortCounts = appEffortCounts;
        this.cost = cost;
        this.numberOfApplications = numberOfApplications;
    }

    public AppSizeEffortDTO(){}
}
