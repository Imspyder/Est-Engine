package com.estimationengine.entity;

import javax.persistence.*;

@Entity
@Table(name="appsize")
public class AppSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appSizeId;
    private String appSize;



    public int getAppSizeId() {
        return appSizeId;
    }

    public void setAppSizeId(int appSizeId) {
        this.appSizeId = appSizeId;
    }

    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    public AppSize(int appSizeId, String appSize) {
        this.appSizeId = appSizeId;
        this.appSize = appSize;
    }

   public AppSize(){

    }
}
