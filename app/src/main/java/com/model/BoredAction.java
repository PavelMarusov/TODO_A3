package com.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class BoredAction {
    @SerializedName("activity")
    private String activity;
    @SerializedName("type")
    private String type;
    @SerializedName("accessibility")
    private Float accessibility;
    @SerializedName("participants")
    private Integer participants;
    @SerializedName("price")
    private String price;
    @SerializedName("link")
    private String link;
    @SerializedName("key")
    private String key;

    public BoredAction(String activity, String type, Float accessibility, Integer participants, String price, String link, String key) {
        this.activity = activity;
        this.type = type;
        this.accessibility = accessibility;
        this.participants = participants;
        this.price = price;
        this.link = link;
        this.key = key;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(Float accessibility) {
        this.accessibility = accessibility;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    @Override
    public String toString() {
        return "BoredAction{" +
                "activity='" + activity + '\'' +
                ", type='" + type + '\'' +
                ", accessibility=" + accessibility +
                ", participants=" + participants +
                ", price='" + price + '\'' +
                ", link='" + link + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
