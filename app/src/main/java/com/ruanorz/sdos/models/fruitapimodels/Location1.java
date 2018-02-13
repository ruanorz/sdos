
package com.ruanorz.sdos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Location1 extends RealmObject {

    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("human_address")
    @Expose
    private String humanAddress;
    @SerializedName("needs_recoding")
    @Expose
    private Boolean needsRecoding;
    @SerializedName("longitude")
    @Expose
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Location1 withLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getHumanAddress() {
        return humanAddress;
    }

    public void setHumanAddress(String humanAddress) {
        this.humanAddress = humanAddress;
    }

    public Location1 withHumanAddress(String humanAddress) {
        this.humanAddress = humanAddress;
        return this;
    }

    public Boolean getNeedsRecoding() {
        return needsRecoding;
    }

    public void setNeedsRecoding(Boolean needsRecoding) {
        this.needsRecoding = needsRecoding;
    }

    public Location1 withNeedsRecoding(Boolean needsRecoding) {
        this.needsRecoding = needsRecoding;
        return this;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Location1 withLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

}
