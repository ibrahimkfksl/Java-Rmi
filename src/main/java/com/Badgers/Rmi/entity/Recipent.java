package com.Badgers.Rmi.entity;

import java.io.Serializable;
import java.util.Date;

public class Recipent implements Serializable {
 private int id;
 private String date;
 private String customerName;
 private String carBrand;
 private String carModel;
 private int carSerialNumber;
 private float carPrice;

    public Recipent(int id, String date, String customerName, String carBrand, String carModel, int carSerialNumber, float carPrice) {
        this.id = id;
        this.date = date;
        this.customerName = customerName;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carSerialNumber = carSerialNumber;
        this.carPrice = carPrice;
    }

    public Recipent() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getCarSerialNumber() {
        return carSerialNumber;
    }

    public void setCarSerialNumber(int carSerialNumber) {
        this.carSerialNumber = carSerialNumber;
    }

    public float getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(float carPrice) {
        this.carPrice = carPrice;
    }

    @Override
    public String toString() {
        return "Recipent{" + "\n"+
                "id= " + id +"\n"+
                "date= " + date +"\n"+
                "customerName= " + customerName +"\n"+
                "carBrand= " + carBrand  +"\n"+
                "carModel= " + carModel  +"\n"+
                "carSerialNumber= " + carSerialNumber  +"\n"+
                "carPrice= " + carPrice +
                "}\n";
    }
}
