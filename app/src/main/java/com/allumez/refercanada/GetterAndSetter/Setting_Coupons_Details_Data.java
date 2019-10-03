package com.allumez.refercanada.GetterAndSetter;

public class Setting_Coupons_Details_Data {

    String id;
    String title;
    String description;
    String image;
    String category_id;
    String business_listing_id;
    String actual_price;
    String discounted_price;
    String total_number_of_coupons;
    String created_at;

    public Setting_Coupons_Details_Data(String id, String title, String description, String image, String category_id, String business_listing_id, String actual_price, String discounted_price, String total_number_of_coupons, String created_at) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.category_id = category_id;
        this.business_listing_id = business_listing_id;
        this.actual_price = actual_price;
        this.discounted_price = discounted_price;
        this.total_number_of_coupons = total_number_of_coupons;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getBusiness_listing_id() {
        return business_listing_id;
    }

    public void setBusiness_listing_id(String business_listing_id) {
        this.business_listing_id = business_listing_id;
    }

    public String getActual_price() {
        return actual_price;
    }

    public void setActual_price(String actual_price) {
        this.actual_price = actual_price;
    }

    public String getDiscounted_price() {
        return discounted_price;
    }

    public void setDiscounted_price(String discounted_price) {
        this.discounted_price = discounted_price;
    }

    public String getTotal_number_of_coupons() {
        return total_number_of_coupons;
    }

    public void setTotal_number_of_coupons(String total_number_of_coupons) {
        this.total_number_of_coupons = total_number_of_coupons;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
