package com.estimationengine.entity;

import javax.persistence.*;

@Entity
@Table(name="appsize_scopes_tbl")
public class AppSizeScopeTBL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="scope_id")
    private Scope scope;
    @ManyToOne
    @JoinColumn(name="app_size_id")
    private AppSize appSize;

    private int appSizeEffort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Scope getScope() {
        return scope;
    }

    public void setScopes(Scope scopes) {
        this.scope = scope;
    }

    public AppSize getAppSize() {
        return appSize;
    }

    public void setAppSize(AppSize appSize) {
        this.appSize = appSize;
    }

    public int getAppSizeEffort() {
        return appSizeEffort;
    }

    public void setAppSizeEffort(int appSizeEffort) {
        this.appSizeEffort = appSizeEffort;
    }

}
