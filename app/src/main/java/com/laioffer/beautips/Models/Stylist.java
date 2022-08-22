package com.laioffer.beautips.Models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Stylist extends User {


    int numOfFollowers;
    int numOfCustomers;
    int numOfReviews;
    String title;


    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    String profileImageUrl;

    List<String> Availability = new ArrayList<>();
    final boolean isValidate = true;

    public Stylist(String name) {
       super(name);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stylist stylist = (Stylist) o;
        return numOfFollowers == stylist.numOfFollowers &&
                numOfCustomers == stylist.numOfCustomers &&
                numOfReviews == stylist.numOfReviews &&
                title == stylist.title &&
                isValidate == stylist.isValidate &&
                Objects.equals(name, stylist.name) &&
                Objects.equals(Availability, stylist.Availability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numOfFollowers, numOfCustomers, numOfReviews, title, Availability, isValidate);
    }

    @Override
    public String toString() {
        return "Stylist{" +
                "name='" + name + '\'' +
                ", numOfFollowers=" + numOfFollowers +
                ", numOfCustomers=" + numOfCustomers +
                ", numOfReviews=" + numOfReviews +
                ", title=" + title +
                ", Availability=" + Availability +
                ", isValidate=" + isValidate +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumOfFollowers(int numOfFollowers) {
        this.numOfFollowers = numOfFollowers;
    }

    public void setNumOfCustomers(int numOfCustomers) {
        this.numOfCustomers = numOfCustomers;
    }

    public void setNumOfReviews(int numOfReviews) {
        this.numOfReviews = numOfReviews;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAvailability(List<String> availability) {
        Availability = availability;
    }

    public String getName() {
        return name;
    }

    public int getNumOfFollowers() {
        return numOfFollowers;
    }

    public int getNumOfCustomers() {
        return numOfCustomers;
    }

    public int getNumOfReviews() {
        return numOfReviews;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAvailability() {
        return Availability;
    }

    public boolean isValidate() {
        return isValidate;
    }
}
