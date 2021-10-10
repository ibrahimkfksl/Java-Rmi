package com.Badgers.Rmi.client;


import com.Badgers.Rmi.rmi.ICar;
import com.Badgers.Rmi.rmi.ImpCar;

import java.rmi.Naming;

public class Client {
    private static final int port=8080;
    private static final String url="dealer";

    public static void main(String[] args) {
        try {
            ICar car=(ICar)Naming.lookup("rmi://127.0.0.1:"+port+"/"+url);

            car.newCar();
        }catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }
}
