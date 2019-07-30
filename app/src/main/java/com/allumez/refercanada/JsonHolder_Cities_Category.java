package com.allumez.refercanada;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class JsonHolder_Cities_Category {
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

    public JsonHolder_Cities_Category(String json) {
        this.json = json;
    }

    protected List<Setting_Category_Data> parseJSON(){
        JSONObject jsonObject = null;
        List<Setting_Category_Data> list = new ArrayList<>();
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
                Setting_Category_Data sd = new Setting_Category_Data(jo.getString(KEY_ID), jo.getString(KEY_CITYNAME),jo.getString(KEY_CITYICON));
                list.add(sd);
                id[i]              = jo.getString(KEY_ID);
                name[i]            = jo.getString(KEY_CITYNAME);
                image[i]           = jo.getString(KEY_CITYIMAGE);
                icon[i]            = jo.getString(KEY_CITYICON);
                coupon_icon[i]     = jo.getString(KEY_CITY_COUPON_ICON);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}