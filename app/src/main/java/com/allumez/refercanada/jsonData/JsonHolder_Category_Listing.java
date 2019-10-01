package com.allumez.refercanada.jsonData;

import com.allumez.refercanada.SettingData.Setting_Data_RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonHolder_Category_Listing {

    public static List<Setting_Data_RecyclerView> jsonDataList = new ArrayList<>();

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

    public List<Setting_Data_RecyclerView> parseJSON() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            JSONArray users = jsonObject.getJSONArray(JSON_ARRAY);

            jsonDataList = new ArrayList<>();

            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);

                String id            = jo.getString(KEY_ID);
                String business_id   = jo.getString(KEY_business_id);
                String listing_name  = jo.getString(KEY_listing_name);
                String landmark      = jo.getString(KEY_landmark);
                String cover_image   = jo.getString(KEY_cover_image);
                String phone         = jo.getString(KEY_phone);
                String email         = jo.getString(KEY_email);
                String address       = jo.getString(KEY_address);
                String image         = jo.getString(KEY_cover_image);
                jsonDataList.add(new Setting_Data_RecyclerView(id, business_id, listing_name, landmark, cover_image, phone, email, address, image));
//                Log.e("===coverImage", jsonDataList.get(i).getCover_image());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonDataList;
    }

}