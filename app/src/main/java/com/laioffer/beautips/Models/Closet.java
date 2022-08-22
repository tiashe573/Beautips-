package com.laioffer.beautips.Models;

import java.util.Objects;

public class Closet {
    String imageName;
    String imageUrl;
    int score;
    String bottomSize;
    String dressCode;
    String occasion;
    String topSize;
    int bottomPrice;
    int topPrice;
    String topUrl;
    String topName;
    String bottomUrl;
    String bottomName;
    String bodyShape;

    public Closet() {

    }
    public Closet(String imageName){
        this.imageName = imageName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getBottomSize() {
        return bottomSize;
    }

    public void setBottomSize(String bottomSize) {
        this.bottomSize = bottomSize;
    }

    public String getDressCode() {
        return dressCode;
    }

    public void setDressCode(String dressCode) {
        this.dressCode = dressCode;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public String getTopSize() {
        return topSize;
    }

    public void setTopSize(String topSize) {
        this.topSize = topSize;
    }

    public int getBottomPrice() {
        return bottomPrice;
    }

    public void setBottomPrice(int bottomPrice) {
        this.bottomPrice = bottomPrice;
    }

    public int getTopPrice() {
        return topPrice;
    }

    public void setTopPrice(int topPrice) {
        this.topPrice = topPrice;
    }

    public String getTopUrl() {
        return topUrl;
    }

    public void setTopUrl(String topUrl) {
        this.topUrl = topUrl;
    }

    public String getTopName() {
        return topName;
    }

    public void setTopName(String topName) {
        this.topName = topName;
    }

    public String getBottomUrl() {
        return bottomUrl;
    }

    public void setBottomUrl(String bottomUrl) {
        this.bottomUrl = bottomUrl;
    }

    public String getBottomName() {
        return bottomName;
    }

    public void setBottomName(String bottomName) {
        this.bottomName = bottomName;
    }

    public String getBodyShape() {
        return bodyShape;
    }

    public void setBodyShape(String bodyShape) {
        this.bodyShape = bodyShape;
    }

    @Override
    public String toString() {
        return "Closet{" +
                "imageName='" + imageName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", score=" + score +
                ", bottomSize='" + bottomSize + '\'' +
                ", dressCode='" + dressCode + '\'' +
                ", occasion='" + occasion + '\'' +
                ", topSize='" + topSize + '\'' +
                ", bottomPrice=" + bottomPrice +
                ", topPrice=" + topPrice +
                ", topUrl='" + topUrl + '\'' +
                ", topName='" + topName + '\'' +
                ", bottomUrl='" + bottomUrl + '\'' +
                ", bottomName='" + bottomName + '\'' +
                ", bodyShape='" + bodyShape + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Closet closet = (Closet) o;
        return score == closet.score &&
                bottomPrice == closet.bottomPrice &&
                topPrice == closet.topPrice &&
                Objects.equals(imageName, closet.imageName) &&
                Objects.equals(imageUrl, closet.imageUrl) &&
                Objects.equals(bottomSize, closet.bottomSize) &&
                Objects.equals(dressCode, closet.dressCode) &&
                Objects.equals(occasion, closet.occasion) &&
                Objects.equals(topSize, closet.topSize) &&
                Objects.equals(topUrl, closet.topUrl) &&
                Objects.equals(topName, closet.topName) &&
                Objects.equals(bottomUrl, closet.bottomUrl) &&
                Objects.equals(bottomName, closet.bottomName) &&
                Objects.equals(bodyShape, closet.bodyShape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageName, imageUrl, score, bottomSize, dressCode, occasion, topSize, bottomPrice, topPrice, topUrl, topName, bottomUrl, bottomName, bodyShape);
    }
}
