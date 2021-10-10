package com.Badgers.Rmi.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ImpCar extends UnicastRemoteObject implements ICar {
    public ImpCar() throws RemoteException {
        super();
    }

    @Override
    public void newCar() throws RemoteException {
        System.out.println("Hadiiiii");
    }

    @Override
    public void newReceipt() throws RemoteException{

    }

    @Override
    public void findCarBySN(int serialNumber) throws RemoteException{

    }

    @Override
    public void findCarByBrand(String brand) throws RemoteException{

    }

    @Override
    public void findReceiptById(int id) throws RemoteException{

    }

    @Override
    public void findReceiptByCustomerName(String name) throws RemoteException{

    }
}
