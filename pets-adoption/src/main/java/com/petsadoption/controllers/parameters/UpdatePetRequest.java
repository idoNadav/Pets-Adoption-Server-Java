package com.petsadoption.controllers.parameters;

import java.util.Objects;

public class UpdatePetRequest {

    private String name;
    private int age;
    private double weight;
    private boolean availability;
    private String description;
    private String category;
    private String picture_link;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPicture_link() {
        return picture_link;
    }

    public void setPicture_link(String picture_link) {
        this.picture_link = picture_link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdatePetRequest)) return false;
        UpdatePetRequest that = (UpdatePetRequest) o;
        return getAge() == that.getAge() &&
                Double.compare(that.getWeight(), getWeight()) == 0 &&
                isAvailability() == that.isAvailability() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getCategory(), that.getCategory()) &&
                Objects.equals(getPicture_link(), that.getPicture_link());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getWeight(), isAvailability(), getDescription(), getCategory(), getPicture_link());
    }

    @Override
    public String toString() {
        return "UpdatePetRequest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", availability=" + availability +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", picture_link='" + picture_link + '\'' +
                '}';
    }
}