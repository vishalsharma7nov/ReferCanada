package com.allumez.refercanada;

import android.graphics.Bitmap;

import java.util.List;

public class JsonHolder {

    public JsonHolder(int status, String state_url, String msg, List<DataBean> data) {
        this.status = status;
        this.state_url = state_url;
        this.msg = msg;
        this.data = data;
    }

    /**
     * status : 1
     * state_url : http://refercanada.com/uploads/states_img/
     * msg : Get state list successfully
     * data : [{"id":"1","name":"Ontario","image":"1557061645_ontario.png"},{"id":"3","name":"Manitoba","image":"1557116046_manitoba.png"},{"id":"2","name":"Quebec","image":"1557114705_quebec.png"},{"id":"4","name":"Alberta","image":"1557142638_alberta-min.jpg"},{"id":"5","name":"Nova Scotia","image":"1557143009_nova-min.jpg"},{"id":"6","name":"British Columbia","image":"1557143176_bc-min.jpg"},{"id":"7","name":"Saskatchewan","image":"1557143396_Saskatchewan-min.jpg"},{"id":"8","name":"New Brunswick","image":"1557143560_New_Brunswick-min.jpg"},{"id":"9","name":"Newfoundland and Labrador","image":"1557143827_nn-min.jpg"},{"id":"10","name":"Prince Edward Island","image":"1557144266_pi-min.jpg"},{"id":"11","name":"Northwest Territories","image":"1557144415_nt-min.jpg"},{"id":"12","name":"Yukon","image":"1557144589_yy-min.jpg"},{"id":"13","name":"Nunavut","image":"1557144711_nu-min.jpg"}]
     */

    private int status;
    private String state_url;
    private String msg;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getState_url() {
        return state_url;
    }

    public void setState_url(String state_url) {
        this.state_url = state_url;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : Ontario
         * image : 1557061645_ontario.png
         */

        private String id;
        private String name;
        private String image;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
