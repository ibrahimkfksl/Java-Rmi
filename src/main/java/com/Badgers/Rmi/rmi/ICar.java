package com.Badgers.Rmi.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICar extends Remote {
    public void newCar() throws RemoteException;
    public void newReceipt() throws RemoteException;
    public void findCarBySN(int serialNumber) throws RemoteException;
    public void findCarByBrand(String brand) throws RemoteException;
    public void findReceiptById(int id) throws RemoteException;
    public void findReceiptByCustomerName(String name) throws RemoteException;
}
