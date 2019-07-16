package com.allumez.refercanada;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class JsonHolderCouponsCategory {
    public static String[] id;
    public static String[] name;
    public static String[] image;
    public static String[] icon;
    public static String[] coupon_icon;



    public static final String JSON_ARRAY           = "data";
    public static final String KEY_ID               = "id";
    public static final String KEY_CITYNAME         = "name";
    public static final String KEY_CITYIMAGE        = "image";
    public static final String KEY_CITYICON         = "icon";
    public static final String KEY_CITY_COUPON_ICON = "coupon_icon";

    private String json;

    public JsonHolderCouponsCategory(String json) {
        this.json = json;
    }

    protected List<SettingCouponsCategoryData> parseJSON(){
        JSONObject jsonObject = null;
        List<SettingCouponsCategoryData> list = new ArrayList<>();
        try {
            jsonObject = new JSONObject(json);
            JSONArray users = jsonObject.getJSONArray(JSON_ARRAY);

            id    = new String[users.length()];
            name  = new String[users.length()];
            image = new String[users.length()];
            icon = new String[users.length()];
            coupon_icon = new String[users.length()];

            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);
                SettingCouponsCategoryData sd = new SettingCouponsCategoryData(jo.getString(KEY_ID), jo.getString(KEY_CITYNAME),jo.getString(KEY_CITY_COUPON_ICON));
                list.add(sd);
                id[i]              = jo.getString(KEY_ID);
                name[i]            = jo.getString(KEY_CITYNAME);
                coupon_icon[i]     = jo.getString(KEY_CITY_COUPON_ICON);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}