package com.allumez.refercanada;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JsonHolder_Category_Listing {
    public static String[] id;
    public static String[] business_id;
    public static String[] listing_name;
    public static String[] landmark;
    public static String[] cover_image;
    public static String[] phone;
    public static String[] email;
    public static String[] address;



    public static final String JSON_ARRAY                  = "data";
    public static final String KEY_ID                      = "id";
    public static final String KEY_business_id             = "business_id";
    public static final String KEY_listing_name            = "listing_name";
    public static final String KEY_landmark                = "landmark";
    public static final String KEY_cover_image             = "cover_image";
    public static final String KEY_phone                   = "phone";
    public static final String KEY_email                   = "email";
    public static final String KEY_address                 = "address";

    private String json;

    public JsonHolder_Category_Listing(String json) {
        this.json = json;
    }

    protected void parseJSON() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            JSONArray users = jsonObject.getJSONArray(JSON_ARRAY);

            id                  = new String[users.length()];
            business_id         = new String[users.length()];
            listing_name        = new String[users.length()];
            landmark            = new String[users.length()];
            cover_image         = new String[users.length()];
            phone               = new String[users.length()];
            email               = new String[users.length()];
            address             = new String[users.length()];


            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);

                id               [i]     = jo.getString(KEY_ID);
                business_id      [i]     = jo.getString(KEY_business_id);
                listing_name     [i]     = jo.getString(KEY_listing_name);
                landmark         [i]     = jo.getString(KEY_landmark);
                cover_image      [i]     = jo.getString(KEY_cover_image);
                phone            [i]     = jo.getString(KEY_phone);
                email            [i]     = jo.getString(KEY_email);
                address          [i]     = jo.getString(KEY_address);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}