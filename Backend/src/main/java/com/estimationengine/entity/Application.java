package com.estimationengine.entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appId;
    private String appName;
    @OneToMany(mappedBy = "application",cascade = CascadeType.ALL)
    private List<Resource> resourceList;
    private double conversion;
    private String uplift;
    private double total;

    public Application(int appId, String appName, double conversion, String uplift, double total) {
        this.appId = appId;
        this.appName = appName;
        this.conversion = conversion;
        this.uplift = uplift;
        this.total = total;
    }

    public Application() {

    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    public double getConversion() {
        return conversion;
    }

    public void setConversion(double conversion) {
        this.conversion = conversion;
    }

    public String getUplift() {
        return uplift;
    }

    public void setUplift(String uplift) {
        this.uplift = uplift;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Application{" +
                "appId=" + appId +
                ", appName='" + appName + '\'' +
                ", conversion=" + conversion +
                ", uplift='" + uplift + '\'' +
                ", total=" + total +
                '}';
    }
}
