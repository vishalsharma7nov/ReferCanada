package com.allumez.refercanada;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JsonHolderFullListing {
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

    public JsonHolderFullListing(String json) {
        this.json = json;
    }

    protected void parseJSON() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            JSONArray users = jsonObject.getJSONArray(JSON_ARRAY);

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

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}