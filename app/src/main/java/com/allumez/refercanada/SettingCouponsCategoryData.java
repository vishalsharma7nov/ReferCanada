package com.allumez.refercanada;

public class SettingCouponsCategoryData {

    String id;
    String name;
    String coupon_icon;

    public SettingCouponsCategoryData(String id, String name, String coupon_icon) {
        this.id = id;
        this.name = name;
        this.coupon_icon = coupon_icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoupon_icon() {
        return coupon_icon;
    }

    public void setCoupon_icon(String coupon_icon) {
        this.coupon_icon = coupon_icon;
    }
}
