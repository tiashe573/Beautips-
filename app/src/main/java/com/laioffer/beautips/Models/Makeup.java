package com.laioffer.beautips.Models;

import java.net.URL;
import java.util.List;
import java.util.Objects;

public class Makeup {
    int id;
    String brand;
    String name;
    float price;
    String price_sign;
    String currency;
    String image_link;
    URL product_link;
    URL website_link;
    String description;
    String rating;
    String category;
    String product_type;
    List <String> tag_list;
    String created_at;
    String updated_at;
    URL product_api_url;
    String api_featured_image;
    List <Makeupcolor> product_colors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Makeup makeup = (Makeup) o;
        return id == makeup.id &&
                Float.compare(makeup.price, price) == 0 &&
                Objects.equals(brand, makeup.brand) &&
                Objects.equals(name, makeup.name) &&
                Objects.equals(price_sign, makeup.price_sign) &&
                Objects.equals(currency, makeup.currency) &&
                Objects.equals(image_link, makeup.image_link) &&
                Objects.equals(product_link, makeup.product_link) &&
                Objects.equals(website_link, makeup.website_link) &&
                Objects.equals(description, makeup.description) &&
                Objects.equals(rating, makeup.rating) &&
                Objects.equals(category, makeup.category) &&
                Objects.equals(product_type, makeup.product_type) &&
                Objects.equals(tag_list, makeup.tag_list) &&
                Objects.equals(created_at, makeup.created_at) &&
                Objects.equals(updated_at, makeup.updated_at) &&
                Objects.equals(product_api_url, makeup.product_api_url) &&
                Objects.equals(api_featured_image, makeup.api_featured_image) &&
                Objects.equals(product_colors, makeup.product_colors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, name, price, price_sign, currency, image_link, product_link, website_link, description, rating, category, product_type, tag_list, created_at, updated_at, product_api_url, api_featured_image, product_colors);
    }

    public Makeup() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPrice_sign() {
        return price_sign;
    }

    public void setPrice_sign(String price_sign) {
        this.price_sign = price_sign;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public URL getProduct_link() {
        return product_link;
    }

    public void setProduct_link(URL product_link) {
        this.product_link = product_link;
    }

    public URL getWebsite_link() {
        return website_link;
    }

    public void setWebsite_link(URL website_link) {
        this.website_link = website_link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public List<String> getTag_list() {
        return tag_list;
    }

    public void setTag_list(List<String> tag_list) {
        this.tag_list = tag_list;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public URL getProduct_api_url() {
        return product_api_url;
    }

    public void setProduct_api_url(URL product_api_url) {
        this.product_api_url = product_api_url;
    }

    public String getApi_featured_image() {
        return api_featured_image;
    }

    public void setApi_featured_image(String api_featured_image) {
        this.api_featured_image = api_featured_image;
    }

    public List<Makeupcolor> getProduct_colors() {
        return product_colors;
    }

    public void setProduct_colors(List<Makeupcolor> product_colors) {
        this.product_colors = product_colors;
    }

}
