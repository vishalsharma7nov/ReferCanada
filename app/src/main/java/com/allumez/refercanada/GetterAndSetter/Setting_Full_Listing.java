package com.allumez.refercanada.GetterAndSetter;

public class Setting_Full_Listing {

    protected String id;
    protected String title;
    protected String product_image;
    protected String detail;
    protected String price;
    protected String features;
    protected String show_product;
    protected String show_price;
    protected String discount;

    public Setting_Full_Listing(String id, String title, String product_image, String detail, String price, String features, String show_product, String show_price, String discount) {
        this.id = id;
        this.title = title;
        this.product_image = product_image;
        this.detail = detail;
        this.price = price;
        this.features = features;
        this.show_product = show_product;
        this.show_price = show_price;
        this.discount = discount;
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

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getShow_product() {
        return show_product;
    }

    public void setShow_product(String show_product) {
        this.show_product = show_product;
    }

    public String getShow_price() {
        return show_price;
    }

    public void setShow_price(String show_price) {
        this.show_price = show_price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
