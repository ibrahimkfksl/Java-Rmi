package com.Badgers.Rmi.entity;

import java.io.Serializable;

public class Car implements Serializable {
    private int serialNumber;
    private String brand;
    private String model;
    private String color;
    private String modelYear;
    private float weight;
    private float price;

    public Car(int serialNumber, String brand, String model, String color, String modelYear, float weight, float price) {
        this.serialNumber = serialNumber;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.modelYear = modelYear;
        this.weight = weight;
        this.price = price;
    }

    public Car() {
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +"\n"+
                "serialNumber=" + serialNumber +"\n"+
                "brand=" + brand  +"\n"+
                "model=" + model  +"\n"+
                "color=" + color +"\n"+
                "modelYear=" + modelYear  +"\n"+
                "weight=" + weight +"\n"+
                "price=" + price +
                "}\n";
    }
}
