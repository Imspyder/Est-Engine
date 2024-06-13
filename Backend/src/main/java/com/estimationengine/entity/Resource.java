package com.estimationengine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
@Table(name = "resource")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resourceId;
    private String skillSet;
    private String grade;
    private int count;
    private double hourlyRateCard;
    private double ratePerHour;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "app_id")
    private Application application;




    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(String skillSet) {
        this.skillSet = skillSet;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getHourlyRateCard() {
        return hourlyRateCard;
    }

    public void setHourlyRateCard(double hourlyRateCard) {
        this.hourlyRateCard = hourlyRateCard;
    }

    public double getRatePerHour() {
        return ratePerHour;
    }

    public void setRatePerHour(double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Resource(int resourceId, String skillSet, String grade, int count, double hourlyRateCard, double ratePerHour) {
        this.resourceId = resourceId;
        this.skillSet = skillSet;
        this.grade = grade;
        this.count = count;
        this.hourlyRateCard = hourlyRateCard;
        this.ratePerHour = ratePerHour;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceId=" + resourceId +
                ", skillSet='" + skillSet + '\'' +
                ", grade='" + grade + '\'' +
                ", count=" + count +
                ", hourlyRateCard=" + hourlyRateCard +
                ", ratePerHour=" + ratePerHour +
                '}';
    }

    public Resource() {

    }
}
