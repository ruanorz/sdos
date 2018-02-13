
package com.ruanorz.sdos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class FruitListResponse extends RealmObject {

    @SerializedName("zipcode")
    @Expose
    private String zipcode;
    @SerializedName("location_1")
    @Expose
    private com.ruanorz.sdos.models.Location1 location1;
    @SerializedName("item")
    @Expose
    private String item;
    @SerializedName("business")
    @Expose
    private String business;
    @SerializedName("farmer_id")
    @Expose
    private String farmerId;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("l")
    @Expose
    private String l;
    @SerializedName("farm_name")
    @Expose
    private String farmName;
    @SerializedName("phone1")
    @Expose
    private String phone1;

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public FruitListResponse withZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public com.ruanorz.sdos.models.Location1 getLocation1() {
        return location1;
    }

    public void setLocation1(com.ruanorz.sdos.models.Location1 location1) {
        this.location1 = location1;
    }

    public FruitListResponse withLocation1(com.ruanorz.sdos.models.Location1 location1) {
        this.location1 = location1;
        return this;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public FruitListResponse withItem(String item) {
        this.item = item;
        return this;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public FruitListResponse withBusiness(String business) {
        this.business = business;
        return this;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public FruitListResponse withFarmerId(String farmerId) {
        this.farmerId = farmerId;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public FruitListResponse withCategory(String category) {
        this.category = category;
        return this;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public FruitListResponse withL(String l) {
        this.l = l;
        return this;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public FruitListResponse withFarmName(String farmName) {
        this.farmName = farmName;
        return this;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public FruitListResponse withPhone1(String phone1) {
        this.phone1 = phone1;
        return this;
    }

}
