package com.laioffer.beautips.Models;

import java.util.Objects;

public class User {

    String bodyShape;
    String name = "";
    String age;
    String topSize;
    String bottomSize;
    int numOfLikes = 0 ;
    String email = "";
    String size;
    String password = "";
    String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setTopSize(String topSize) {
        this.topSize = topSize;
    }

    public void setBottomSize(String bottomSize) {
        this.bottomSize = bottomSize;
    }

    public void setNumOfLikes(int numOfLikes) {
        this.numOfLikes = numOfLikes;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getNumOfLikes() {
        return numOfLikes;
    }




    public User() {}

    public User(String age, String topSize, String bottomSize, String name) {
        this.age =  age;
        this.topSize = topSize;
        this.bottomSize = bottomSize;
        this.name = name;

    }

    public User(String name) {
        this.name = name;
    }
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;

    }


    public void setBodyShape(String bodyShape) {
        this.bodyShape = bodyShape;
    }

    public void setName(String email){
        this.name = name;
    }

    public String getBodyShape() {
        return bodyShape;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getTopSize() {
        return topSize;
    }

    public String getBottomSize(){ return  bottomSize; }

    public String getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +

                "bodyShape='" + bodyShape + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", topSize=" + topSize +
                ", bottomSize=" + bottomSize +
                ", numOfLikes=" + numOfLikes +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                topSize == user.topSize &&
                bottomSize == bottomSize &&
                numOfLikes == user.numOfLikes &&

                Objects.equals(bodyShape, user.bodyShape) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bodyShape, name, age, topSize, bottomSize, numOfLikes, email, password);
    }
}


