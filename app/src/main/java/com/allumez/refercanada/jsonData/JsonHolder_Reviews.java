package com.allumez.refercanada.jsonData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonHolder_Reviews {
    public static String[] id;
    public static String[] listing_id;
    public static String[] business_id;
    public static String[] name;
    public static String[] email;
    public static String[] city;
    public static String[] mobile_number;
    public static String[] comment;
    public static String[] rating;
    public static String[] created_date;



    public static final String JSON_ARRAY                  = "data";
    public static final String KEY_ID                      = "id";
    public static final String KEY_listing_id              = "listing_id";
    public static final String KEY_business_id             = "business_id";
    public static final String KEY_name                    = "name";
    public static final String KEY_email                   = "email";
    public static final String KEY_city                    = "city";
    public static final String KEY_mobile_number           = "mobile_number";
    public static final String KEY_comment                 = "comment";
    public static final String KEY_rating                  = "rating";
    public static final String KEY_created_date            = "created_date";

    private String json;

    public JsonHolder_Reviews(String json) {
        this.json = json;
    }

    public void parseJSON() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            JSONArray users = jsonObject.getJSONArray(JSON_ARRAY);

           id                   = new String[users.length()];
           listing_id           = new String[users.length()];
           business_id          = new String[users.length()];
           name                 = new String[users.length()];
           email                = new String[users.length()];
           city                 = new String[users.length()];
           mobile_number        = new String[users.length()];
           comment              = new String[users.length()];
           rating               = new String[users.length()];
           created_date         = new String[users.length()];


            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);

                name             [i]     = jo.getString(KEY_name);
                comment          [i]     = jo.getString(KEY_comment);
                rating           [i]     = jo.getString(KEY_rating);
                created_date     [i]     = jo.getString(KEY_created_date);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}