package com.allumez.refercanada;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JsonHolderCities{
    public static String[] id;
    public static String[] name;
    public static String[] image;
    public static String[] status;

    public static final String JSON_ARRAY          = "data";
    public static final String KEY_ID              = "id";
    public static final String KEY_CITYNAME        = "name";
    public static final String KEY_CITYIMAGE       = "image";
    public static final String KEY_CITYSTATUS      = "status";

    private String json;

    public JsonHolderCities(String json) {
        this.json = json;
    }

    protected void parseJSON() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            JSONArray users = jsonObject.getJSONArray(JSON_ARRAY);

            id    = new String[users.length()];
            name  = new String[users.length()];
            image = new String[users.length()];
            status= new String[users.length()];

            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);

                id[i]       = jo.getString(KEY_ID);
                name[i]     = jo.getString(KEY_CITYNAME);
                image[i]    = jo.getString(KEY_CITYIMAGE);
                status[i]    = jo.getString(KEY_CITYSTATUS);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}