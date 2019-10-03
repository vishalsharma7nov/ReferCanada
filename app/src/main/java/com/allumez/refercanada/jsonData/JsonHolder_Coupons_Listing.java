package com.allumez.refercanada.jsonData;

import com.allumez.refercanada.GetterAndSetter.Setting_Coupons_Details_Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonHolder_Coupons_Listing {
    public static final String JSON_ARRAY                  = "data";
    public static final String KEY_ID                      = "id";
    public static final String KEY_title                   = "title";
    public static final String KEY_description             = "description";
    public static final String KEY_image                   = "image";
    public static final String KEY_category_id             = "category_id";
    public static final String KEY_business_listing_id     = "business_listing_id";
    public static final String KEY_actual_price            = "actual_price";
    public static final String KEY_discounted_price        = "discounted_price";
    public static final String KEY_total_number_of_coupons = "total_number_of_coupons";
    public static final String KEY_created_at              = "created_at";
    public static String[] id;
    public static String[] title;
    public static String[] description;
    public static String[] image;
    public static String[] category_id;
    public static String[] business_listing_id;
    public static String[] actual_price;
    public static String[] discounted_price;
    public static String[] total_number_of_coupons;
    public static String[] created_at;
    private String json;
    public JsonHolder_Coupons_Listing(String json) {
        this.json = json;
    }
    public List<Setting_Coupons_Details_Data> parseJSON(){
        JSONObject jsonObject = null;
        List<Setting_Coupons_Details_Data> list = new ArrayList<>();
        try {
            jsonObject = new JSONObject(json);
            JSONArray users = jsonObject.getJSONArray(JSON_ARRAY);
           id                       = new String[users.length()];
           title                    = new String[users.length()];
           description              = new String[users.length()];
           image                    = new String[users.length()];
           category_id              = new String[users.length()];
           business_listing_id      = new String[users.length()];
           actual_price             = new String[users.length()];
           discounted_price         = new String[users.length()];
           total_number_of_coupons  = new String[users.length()];
           created_at               = new String[users.length()];
            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);
                Setting_Coupons_Details_Data sd = new Setting_Coupons_Details_Data(jo.getString(KEY_ID),
                                                                             jo.getString(KEY_title),
                                                                             jo.getString(KEY_description),
                                                                             jo.getString(KEY_image),
                                                                             jo.getString(KEY_category_id),
                                                                             jo.getString(KEY_business_listing_id),
                                                                             jo.getString(KEY_actual_price),
                                                                             jo.getString(KEY_discounted_price),
                                                                             jo.getString(KEY_total_number_of_coupons),
                                                                             jo.getString(KEY_created_at));
                list.add(sd);
                id                         [i]     = jo.getString(KEY_ID);
                title                      [i]     = jo.getString(KEY_title);
                description                [i]     = jo.getString(KEY_description);
                image                      [i]     = jo.getString(KEY_image);
                category_id                [i]     = jo.getString(KEY_category_id);
                business_listing_id        [i]     = jo.getString(KEY_business_listing_id);
                actual_price               [i]     = jo.getString(KEY_actual_price);
                discounted_price           [i]     = jo.getString(KEY_discounted_price);
                total_number_of_coupons    [i]     = jo.getString(KEY_total_number_of_coupons);
                created_at                 [i]     = jo.getString(KEY_created_at);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}