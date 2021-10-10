package com.Badgers.Rmi.client;


import com.Badgers.Rmi.rmi.ICar;
import com.Badgers.Rmi.rmi.ImpCar;

import java.rmi.Naming;
import java.util.Scanner;

public class Client {
    private static final int port=1099;
    private static final String url="dealer";

    public static void main(String[] args) {
        try {
            ICar car=(ICar)Naming.lookup("rmi://127.0.0.1:"+port+"/"+url);
            menu(car);

        }catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }

    public static void menu(ICar car){

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
        try{
            switch (choose){
                case 1: car.newCar(); break;
                case 2: car.newReceipt(); break;
                case 3: car.findCarBySN(123); break;
                case 4: car.findCarByBrand("omb jetta"); break;
                case 5: car.findReceiptById(123); break;
                case 6: car.findReceiptByCustomerName("omb omb omb omb omb"); break;
                case 0: System.exit(0);
                default:
                    System.out.println("Hatali Tuslama Yaptiniz Tekrar Giriniz");
                    menu(car);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



    }
}
