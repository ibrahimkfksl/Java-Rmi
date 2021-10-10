package com.Badgers.Rmi.rmi;

import com.Badgers.Rmi.entity.Car;
import com.Badgers.Rmi.entity.Recipent;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ICar extends Remote {
    void newCar(Car newCar) throws RemoteException;

    void newReceipt(Recipent recipent) throws RemoteException;

    Car findCarBySN(int serialNumber) throws RemoteException;

    List<Car> findCarByBrand(String brand) throws RemoteException;

    Recipent findReceiptById(int id) throws RemoteException;

    List<Recipent>  findReceiptByCustomerName(String name) throws RemoteException;
}
