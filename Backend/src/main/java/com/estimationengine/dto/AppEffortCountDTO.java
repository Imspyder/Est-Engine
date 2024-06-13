package com.estimationengine.dto;

import com.estimationengine.entity.AppSize;

public class AppEffortCountDTO {

    private String appSize;
    private int appSizeEffort;
    private int appCount;

    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    public int getAppSizeEffort() {
        return appSizeEffort;
    }

    public void setAppSizeEffort(int appSizeEffort) {
        this.appSizeEffort = appSizeEffort;
    }

    public int getAppCount() {
        return appCount;
    }

    public void setAppCount(int appCount) {
        this.appCount = appCount;
    }

    public AppEffortCountDTO(String appSize, int appSizeEffort, int appCount) {
        this.appSize = appSize;
        this.appSizeEffort = appSizeEffort;
        this.appCount = appCount;
    }

    public AppEffortCountDTO(){}
}
