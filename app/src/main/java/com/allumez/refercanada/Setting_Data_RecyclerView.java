package com.allumez.refercanada;

public class Setting_Data_RecyclerView {

    public String id;
    public String business_id;
    public String listing_name;
    public String landmark;
    public String cover_image;
    public String phone;
    public String email;
    public String address;
    public String img;

    public Setting_Data_RecyclerView(String id, String business_id, String listing_name, String landmark, String cover_image, String phone, String email, String address, String img) {
        this.id = id;
        this.business_id = business_id;
        this.listing_name = listing_name;
        this.landmark = landmark;
        this.cover_image = cover_image;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public String getListing_name() {
        return listing_name;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getCover_image() {
        return cover_image;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getImg() {
        return img;
    }

}
