package com.allumez.refercanada;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonHolderBlogListing {

     public static String[] id;
     public static String[] title;
     public static String[] description;
     public static String[] image;
     public static String[] meta_key;
     public static String[] meta_description;


        public static final String JSON_ARRAY                  = "data";
        public static final String KEY_ID                      = "id";
        public static final String KEY_TITLE                   = "title";
        public static final String KEY_DESCRIPTION             = "description";
        public static final String KEY_IMAGE                   = "image";
        public static final String KEY_META_KEY                = "meta_key";
        public static final String KEY_META_DESCRIPTION        = "meta_description";

        private String json;

        public JsonHolderBlogListing(String json) {
            this.json = json;
        }

        protected void parseJSON() {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(json);
                JSONArray users = jsonObject.getJSONArray(JSON_ARRAY);

                id                  = new String[users.length()];
                title        = new String[users.length()];
                description        = new String[users.length()];
                image        = new String[users.length()];
                meta_key        = new String[users.length()];
                meta_description        = new String[users.length()];


                for (int i = 0; i < users.length(); i++) {
                    JSONObject jo = users.getJSONObject(i);

                    id               [i]     = jo.getString(KEY_ID);
                    title            [i]     = jo.getString(KEY_TITLE);
                    description      [i]     = jo.getString(KEY_DESCRIPTION);
                    image            [i]     = jo.getString(KEY_IMAGE);
                    meta_key         [i]     = jo.getString(KEY_META_KEY);
                    meta_description [i]     = jo.getString(KEY_META_DESCRIPTION);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
}
