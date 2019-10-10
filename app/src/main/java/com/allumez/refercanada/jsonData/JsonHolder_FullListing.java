package com.allumez.refercanada.jsonData;

import com.allumez.refercanada.GetterAndSetter.Setting_Full_Listing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonHolder_FullListing {
    public static List<Setting_Full_Listing> jsonDataList = new ArrayList<>();
    public static String[] id;
    public static String[] title;
    public static String[] product_image;
    public static String[] detail;
    public static String[] price;
    public static String[] features;
    public static String[] show_product;
    public static String[] show_price;
    public static String[] discount;
    public static final String JSON_ARRAY                  = "product_data";
    public static final String KEY_ID                      = "id";
    public static final String KEY_title                   = "title";
    public static final String KEY_product_image           = "product_image";
    public static final String KEY_detail                  = "detail";
    public static final String KEY_price                   = "price";
    public static final String KEY_features                = "features";
    public static final String KEY_show_product            = "show_product";
    public static final String KEY_show_price              = "show_price";
    public static final String KEY_discount                = "discount";
    private String json;
    public JsonHolder_FullListing(String json) {
        this.json = json;
    }
    public List<Setting_Full_Listing>parseJSON() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            JSONArray users = jsonObject.getJSONArray(JSON_ARRAY);
            jsonDataList = new ArrayList<>();
            id                  = new String[users.length()];
            title               = new String[users.length()];
            product_image       = new String[users.length()];
            detail              = new String[users.length()];
            price               = new String[users.length()];
            features            = new String[users.length()];
            show_product        = new String[users.length()];
            show_price          = new String[users.length()];
            discount            = new String[users.length()];
            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);
                id               [i]     = jo.getString(KEY_ID);
                title            [i]     = jo.getString(KEY_title);
                product_image    [i]     = jo.getString(KEY_product_image);
                detail           [i]     = jo.getString(KEY_detail);
                price            [i]     = jo.getString(KEY_price);
                features         [i]     = jo.getString(KEY_features);
                show_product     [i]     = jo.getString(KEY_show_product);
                show_price       [i]     = jo.getString(KEY_show_price);
                discount         [i]     = jo.getString(KEY_discount);
                jsonDataList.add(new Setting_Full_Listing(jo.getString(KEY_ID),jo.getString(KEY_title),jo.getString(KEY_product_image),jo.getString(KEY_detail),jo.getString(KEY_price),jo.getString(KEY_show_product),jo.getString(KEY_features),jo.getString(KEY_show_price),jo.getString(KEY_discount)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonDataList;
    }
}