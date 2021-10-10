package com.Badgers.Rmi.server;

import com.Badgers.Rmi.rmi.ImpCar;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    private static final int port=1099;
    private static final String url="dealer";

    public Server() {
        try {
            Registry reg= LocateRegistry.createRegistry(port);
            reg=LocateRegistry.getRegistry();
            reg.rebind(url,new ImpCar());
            System.out.println("Server Calisiyor...");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
