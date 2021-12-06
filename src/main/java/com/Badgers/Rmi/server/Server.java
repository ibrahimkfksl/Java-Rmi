package com.Badgers.Rmi.server;

import com.Badgers.Rmi.rmi.ImpCar;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.util.Scanner;

public class Server {
    private static final int port=1099;
    private String url="dealer";

    public Server(String serverName) {
        try {
            if(!serverName.equals("")){
                this.url=serverName;
            }
            Registry reg;
            try{
                reg= LocateRegistry.createRegistry(port);
            }catch(ExportException e){
                reg=LocateRegistry.getRegistry(port);

            }
            reg.rebind(url,new ImpCar(this.url));
            System.out.println(url+"   Server Calisiyor...");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.print("Server Name: ");
        Scanner scanner = new Scanner(System.in);
        String serverName = scanner.nextLine();

        new Server(serverName);
    }
}
