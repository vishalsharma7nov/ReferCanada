package com.allumez.refercanada.jsonData;

import com.allumez.refercanada.GetterAndSetter.Setting_Deals_And_Offers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonHolder_DealsAndOffers {
    public static List<Setting_Deals_And_Offers> jsonDataList = new ArrayList<>();
    public static final String JSON_ARRAY                  = "data";
    public static final String KEY_id                      = "id";
    public static final String KEY_title                   = "title";
    public static final String KEY_listingId               = "listingId";
    public static final String KEY_description             = "description";
    public static final String KEY_image                   = "image";
    public static final String KEY_state_id                = "state_id";
    public static final String KEY_city_id                 = "city_id";
    public static final String KEY_category_id             = "category_id";
    public static final String KEY_business_listing_id     = "business_listing_id";
    public static final String KEY_actual_price            = "actual_price";
    public static final String KEY_discounted_price        = "discounted_price";
    public static final String KEY_total_number_of_coupons = "total_number_of_coupons";
    public static final String KEY_status                  = "status";
    public static final String KEY_created_at              = "created_at";
    private String json;
    public JsonHolder_DealsAndOffers(String json) {
        this.json = json;
    }
    public List<Setting_Deals_And_Offers>parseJSON() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            JSONArray users = jsonObject.getJSONArray(JSON_ARRAY);
            jsonDataList = new ArrayList<>();
            String[]id                          = new String[users.length()];
            String[]title                       = new String[users.length()];
            String[]listingId                   = new String[users.length()];
            String[]description                 = new String[users.length()];
            String[]image                       = new String[users.length()];
            String[]state_id                    = new String[users.length()];
            String[]city_id                     = new String[users.length()];
            String[]category_id                 = new String[users.length()];
            String[]business_listing_id         = new String[users.length()];
            String[]actual_price                = new String[users.length()];
            String[]discounted_price            = new String[users.length()];
            String[]total_number_of_coupons     = new String[users.length()];
            String[]status                      = new String[users.length()];
            String[]created_at                  = new String[users.length()];
            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);
                id                          [i]     = jo.getString(KEY_id);
                title                       [i]     = jo.getString(KEY_title);
                listingId                   [i]     = jo.getString(KEY_listingId);
                description                 [i]     = jo.getString(KEY_description);
                image                       [i]     = jo.getString(KEY_image);
                state_id                    [i]     = jo.getString(KEY_state_id);
                city_id                     [i]     = jo.getString(KEY_city_id);
                category_id                 [i]     = jo.getString(KEY_category_id);
                business_listing_id         [i]     = jo.getString(KEY_business_listing_id);
                actual_price                [i]     = jo.getString(KEY_actual_price);
                discounted_price            [i]     = jo.getString(KEY_discounted_price);
                total_number_of_coupons     [i]     = jo.getString(KEY_total_number_of_coupons);
                status                      [i]     = jo.getString(KEY_status);
                created_at                  [i]     = jo.getString(KEY_created_at);
                jsonDataList.add(new Setting_Deals_And_Offers(jo.getString(KEY_id),jo.getString(KEY_title),jo.getString(KEY_listingId),jo.getString(KEY_description),jo.getString(KEY_image),jo.getString(KEY_state_id),jo.getString(KEY_city_id),jo.getString(KEY_category_id),jo.getString(KEY_business_listing_id),jo.getString(KEY_actual_price),jo.getString(KEY_discounted_price),jo.getString(KEY_total_number_of_coupons),jo.getString(KEY_status),jo.getString(KEY_created_at)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonDataList;
    }
}