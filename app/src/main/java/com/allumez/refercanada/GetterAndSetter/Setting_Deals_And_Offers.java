package com.allumez.refercanada.GetterAndSetter;

public class Setting_Deals_And_Offers {

    protected String id;
    protected String title;
    protected String listingId;
    protected String description;
    protected String image;
    protected String state_id;
    protected String city_id;
    protected String category_id;
    protected String business_listing_id;
    protected String actual_price;
    protected String discounted_price;
    protected String total_number_of_coupons;
    protected String status;
    protected String created_at;

    public Setting_Deals_And_Offers(String id, String title, String listingId, String description, String image, String state_id, String city_id, String category_id, String business_listing_id, String actual_price, String discounted_price, String total_number_of_coupons, String status, String created_at) {
        this.id = id;
        this.title = title;
        this.listingId = listingId;
        this.description = description;
        this.image = image;
        this.state_id = state_id;
        this.city_id = city_id;
        this.category_id = category_id;
        this.business_listing_id = business_listing_id;
        this.actual_price = actual_price;
        this.discounted_price = discounted_price;
        this.total_number_of_coupons = total_number_of_coupons;
        this.status = status;
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

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
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

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
