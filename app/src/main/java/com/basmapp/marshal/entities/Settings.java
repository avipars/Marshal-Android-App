package com.basmapp.marshal.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Settings {

    @Expose
    @SerializedName("lastUpdateAt")
    Date lastUpdateAt;

    @Expose
    @SerializedName("minVersion")
    int minVersion;

    public Date getLastUpdateAt() {
        return lastUpdateAt;
    }

    public int getMinVersion() {
        return minVersion;
    }

}
