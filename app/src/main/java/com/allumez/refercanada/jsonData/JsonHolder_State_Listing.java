package com.allumez.refercanada.jsonData;

import com.allumez.refercanada.GetterAndSetter.Setting_Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonHolder_State_Listing {
    public static String[] id;
    public static String[] name;
    public static String[] image;
    public static String[] status;
    public static final String JSON_ARRAY          = "data";
    public static final String KEY_ID              = "id";
    public static final String KEY_CITYNAME        = "name";
    public static final String KEY_CITYIMAGE       = "image";
    private String json;
    public JsonHolder_State_Listing(String json) {
        this.json = json;
    }
    public List<Setting_Data> parseJSON() {
        List<Setting_Data> list = new ArrayList<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            JSONArray users = jsonObject.getJSONArray(JSON_ARRAY);
            id     = new String[users.length()];
            name   = new String[users.length()];
            image  = new String[users.length()];
            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);
                Setting_Data sd = new Setting_Data(jo.getString(KEY_ID), jo.getString(KEY_CITYNAME));
                list.add(sd);
                id[i]       = jo.getString(KEY_ID);
                name[i]     = jo.getString(KEY_CITYNAME);
                image[i]    = jo.getString(KEY_CITYIMAGE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}