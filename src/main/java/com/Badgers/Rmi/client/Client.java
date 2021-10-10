package com.Badgers.Rmi.client;


import com.Badgers.Rmi.entity.Car;
import com.Badgers.Rmi.entity.Recipent;
import com.Badgers.Rmi.rmi.ICar;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Client {
    private static final int port = 1099;
    private static final String url = "dealer";

    public static void main(String[] args) {
        try {
            ICar car = (ICar) Naming.lookup("rmi://127.0.0.1:" + port + "/" + url);
            menu(car);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public static void menu(ICar car) {

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
                        newCar(car);
                        break;
                    case 2:
                        newReceipt(car);
                        break;
                    case 3:
                        findCarBySN(car);
                        break;
                    case 4:
                        findCarByBrand(car);
                        break;
                    case 5:
                        findReceiptById(car);
                        break;
                    case 6:
                        findReceiptByCustomerName(car);
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


    public static void newCar(ICar car) throws RemoteException {

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


        try {
            car.newCar(newCar);
            System.out.println("Araba Kayıt tamamlandı");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void newReceipt(ICar car) throws RemoteException {
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

            car.newReceipt(newRecipent);
            System.out.println("Fatura Kayıt tamamlandı");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void findCarBySN(ICar car) throws RemoteException {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------");
        System.out.print("Arabanın seri numarasını giriniz :  ");
        Car findedCar = car.findCarBySN(sc.nextInt());
        sc.nextLine();
        if (findedCar == null) {
            System.out.println("Araba bulunamadı!!");
        } else {
            System.out.println(findedCar);
        }
    }


    public static void findCarByBrand(ICar car) throws RemoteException {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------");
        System.out.print("Arabanın markasını giriniz :  ");
        List<Car> findedCar = car.findCarByBrand(sc.nextLine());
        if(!findedCar.isEmpty()){
            System.out.println("Arama Sonucu:  \n");
            findedCar.stream().forEach(car1 -> System.out.println(car1));
        }

    }

    public static void findReceiptById(ICar car) throws RemoteException {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------");
        System.out.print("Faturanın id'sini giriniz :  ");
        Recipent recipent = car.findReceiptById(sc.nextInt());
        sc.nextLine();
        if (recipent == null) {
            System.out.println("Fatura bulunamadı!!");
        } else {
            System.out.println(recipent);
        }
    }

    public static void findReceiptByCustomerName(ICar car) throws RemoteException {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------");
        System.out.print("Fatura Alıcısının adı giriniz :  ");
        List<Recipent> recipent = car.findReceiptByCustomerName(sc.nextLine());

        if(!recipent.isEmpty()){
            System.out.println("Arama Sonucu:  \n");
            recipent.stream().forEach(recipent1 -> System.out.println(recipent1));
        }
    }
}
