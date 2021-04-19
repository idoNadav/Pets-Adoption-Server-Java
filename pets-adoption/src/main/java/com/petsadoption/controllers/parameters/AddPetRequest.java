package com.petsadoption.controllers.parameters;

import java.util.Objects;

public class AddPetRequest {

    private String name;
    private int age;
    private String color;
    private double weight;
    private boolean availability;
    private String description;
    private String category;
    private String picture_link;
    private String userId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPicture_link() {
        return picture_link;
    }

    public void setPicture_link(String picture_link) {
        this.picture_link = picture_link;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddPetRequest that = (AddPetRequest) o;
        return age == that.age &&
                Double.compare(that.weight, weight) == 0 &&
                availability == that.availability &&
                Objects.equals(name, that.name) &&
                Objects.equals(color, that.color) &&
                Objects.equals(description, that.description) &&
                Objects.equals(category, that.category) &&
                Objects.equals(picture_link, that.picture_link) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, color, weight, availability, description, category, picture_link, userId);
    }

    @Override
    public String toString() {
        return "AddPetRequest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                ", availability=" + availability +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", picture_link='" + picture_link + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}