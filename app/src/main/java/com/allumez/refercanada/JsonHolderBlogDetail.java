package com.allumez.refercanada;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonHolderBlogDetail {

     public static String id;
     public static String title;
     public static String description;
     public static String image;
     public static String meta_key;
     public static String meta_description;


        public static final String JSON_ARRAY                  = "data";
        public static final String KEY_ID                      = "id";
        public static final String KEY_TITLE                   = "title";
        public static final String KEY_DESCRIPTION             = "description";
        public static final String KEY_IMAGE                   = "image";
        public static final String KEY_META_KEY                = "meta_key";
        public static final String KEY_META_DESCRIPTION        = "meta_description";

        private String json;

        public JsonHolderBlogDetail(String json) {
            this.json = json;
        }

        protected void parseJSON() {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(json);
                JSONArray users = jsonObject.getJSONArray(JSON_ARRAY);



                for (int i = 0; i < users.length(); i++) {
                    JSONObject jo = users.getJSONObject(i);

                    id                   = jo.getString(KEY_ID);
                    title                = jo.getString(KEY_TITLE);
                    description          = jo.getString(KEY_DESCRIPTION);
                    image                = jo.getString(KEY_IMAGE);
                    meta_key             = jo.getString(KEY_META_KEY);
                    meta_description     = jo.getString(KEY_META_DESCRIPTION);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
}
