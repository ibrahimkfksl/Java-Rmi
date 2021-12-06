package com.Badgers.Rmi.client;


import com.Badgers.Rmi.entity.Car;
import com.Badgers.Rmi.entity.Recipent;
import com.Badgers.Rmi.rmi.ICar;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Client {
    private static ArrayList<ICar> serverList;

    public static void main(String[] args) {
        try {
            //ICar car = (ICar) Naming.lookup("rmi://127.0.0.1:" + port + "/" + url);
            startClient();
            menu();

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public static void startClient() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        String[] serverNameList = registry.list();
        serverList = new ArrayList<>();
        for (String serverName : serverNameList) {
            ICar server = (ICar) registry.lookup(serverName);
            serverList.add(server);
        }
        System.out.println(serverList.size());
    }

    public static void menu() {

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("1- Yeni Araba Ekle");
            System.out.println("2- Yeni Fatura Ekle");
            System.out.println("3- Arabayi Seri Numarasina Gore Goster");
            System.out.println("4- Arabayi Markasina Gore Goster");
            System.out.println("5- ID ye gore fatura goster");
            System.out.println("6- Faturayi Alicinin Adina Gore Goster");
            System.out.println("0- Cikis");
            System.out.print("Menuden Bir Islem Secin: ");
            int choose = sc.nextInt();
            try {
                switch (choose) {
                    case 1:
                        newCar();
                        break;
                    case 2:
                        newReceipt();
                        break;
                    case 3:
                        findCarBySN();
                        break;
                    case 4:
                        findCarByBrand();
                        break;
                    case 5:
                        findReceiptById();
                        break;
                    case 6:
                        findReceiptByCustomerName();
                        break;
                    case 0:
                        System.out.println("Uygulamadan Cikiliyor...");
                        System.exit(0);
                    default:
                        System.out.println("Hatali Tuslama Yaptiniz Tekrar Giriniz");
                }

                System.out.println("----------------------------------------------------------------");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


    }


    public static void newCar() throws RemoteException {

        Scanner sc = new Scanner(System.in);
        Car newCar = new Car();
        System.out.println("----------------------------------------------------------------");
        System.out.println();
        System.out.println("Yeni Araba Kayıt Oluştur");
        System.out.println();
        System.out.print("Arabanın Seri Numarası:   ");
        newCar.setSerialNumber(sc.nextInt());
        sc.nextLine();
        System.out.println();
        System.out.print("Arabanın Markası:   ");
        newCar.setBrand(sc.nextLine());
        System.out.println();
        System.out.print("Arabanın Modeli:   ");
        newCar.setModel(sc.nextLine());
        System.out.println();
        System.out.print("Arabanın Rengi:   ");
        newCar.setColor(sc.nextLine());
        System.out.println();
        System.out.print("Arabanın Model Yılı:   ");
        newCar.setModelYear(sc.nextLine());
        System.out.println();
        System.out.print("Arabanın Ağırlığı:   ");
        newCar.setWeight(sc.nextFloat());
        sc.nextLine();
        System.out.println();
        System.out.print("Arabanın Fiyatı:   ");
        newCar.setPrice(sc.nextFloat());
        sc.nextLine();
        System.out.println();


        serverList.forEach(server -> {
            try {
                server.newCar(newCar);
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
            }
        });
        System.out.println("Araba Kayıt tamamlandı");

    }

    public static void newReceipt() throws RemoteException {
        Scanner sc = new Scanner(System.in);
        Recipent newRecipent = new Recipent();
        System.out.println("----------------------------------------------------------------");
        System.out.println();
        System.out.println("Yeni Fatura Kayıt Oluştur");
        System.out.println();
        System.out.print("Fatura Id Numarası:   ");
        newRecipent.setId(sc.nextInt());
        sc.nextLine();
        System.out.println();
        System.out.print("Alıcı Adı:   ");
        newRecipent.setCustomerName(sc.nextLine());
        System.out.println();
        System.out.print("Arabanın Markası:   ");
        newRecipent.setCarBrand(sc.nextLine());
        System.out.println();
        System.out.print("Arabanın Modeli:   ");
        newRecipent.setCarModel(sc.nextLine());
        System.out.println();
        System.out.print("Arabanın Seri Numarası:   ");
        newRecipent.setCarSerialNumber(sc.nextInt());
        sc.nextLine();
        System.out.println();
        System.out.print("Arabanın Fiyatı:   ");
        newRecipent.setCarPrice(sc.nextFloat());
        sc.nextLine();
        System.out.println();


        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = format.format(new Date());
            newRecipent.setDate(dateString);

            serverList.forEach(server -> {
                try {
                    server.newReceipt(newRecipent);
                } catch (RemoteException e) {
                    System.out.println(e.getMessage());
                }
            });
            System.out.println("Fatura Kayıt tamamlandı");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void findCarBySN() throws RemoteException {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------");
        System.out.print("Arabanın seri numarasını giriniz :  ");
        Car findedCar = serverList.get(0).findCarBySN(sc.nextInt());
        sc.nextLine();
        if (findedCar == null) {
            System.out.println("Araba bulunamadı!!");
        } else {
            System.out.println(findedCar);
        }
    }


    public static void findCarByBrand() throws RemoteException {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------");
        System.out.print("Arabanın markasını giriniz :  ");
        List<Car> findedCar = serverList.get(0).findCarByBrand(sc.nextLine());
        if (!findedCar.isEmpty()) {
            System.out.println("Arama Sonucu:  \n");
            findedCar.stream().forEach(car1 -> System.out.println(car1));
        }

    }

    public static void findReceiptById() throws RemoteException {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------");
        System.out.print("Faturanın id'sini giriniz :  ");
        Recipent recipent = serverList.get(0).findReceiptById(sc.nextInt());
        sc.nextLine();
        if (recipent == null) {
            System.out.println("Fatura bulunamadı!!");
        } else {
            System.out.println(recipent);
        }
    }

    public static void findReceiptByCustomerName() throws RemoteException {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------");
        System.out.print("Fatura Alıcısının adı giriniz :  ");
        List<Recipent> recipent = serverList.get(0).findReceiptByCustomerName(sc.nextLine());
        if (!recipent.isEmpty()) {
            System.out.println("Arama Sonucu:  \n");
            recipent.stream().forEach(recipent1 -> System.out.println(recipent1));
        }
    }
}
