package com.allumez.refercanada.GetterAndSetter;

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
    public String sponsor;

    public Setting_Data_RecyclerView(String id, String business_id, String listing_name, String landmark, String cover_image, String phone, String email, String address, String img, String sponsor) {
        this.id = id;
        this.business_id = business_id;
        this.listing_name = listing_name;
        this.landmark = landmark;
        this.cover_image = cover_image;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.img = img;
        this.sponsor = sponsor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public String getListing_name() {
        return listing_name;
    }

    public void setListing_name(String listing_name) {
        this.listing_name = listing_name;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }
}
