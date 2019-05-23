package com.allumez.refercanada;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JsonHolderCitiesCategory {
    public static String[] id;
    public static String[] name;
    public static String[] image;


    public static final String JSON_ARRAY          = "data";
    public static final String KEY_ID              = "id";
    public static final String KEY_CITYNAME        = "name";
    public static final String KEY_CITYIMAGE       = "image";

    private JSONArray users = null;

    private String json;

    public JsonHolderCitiesCategory(String json) {
        this.json = json;
    }

    protected void parseJSON() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            id    = new String[users.length()];
            name  = new String[users.length()];
            image = new String[users.length()];

            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);

                id[i]       = jo.getString(KEY_ID);
                name[i]     = jo.getString(KEY_CITYNAME);
                image[i]    = jo.getString(KEY_CITYIMAGE);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}