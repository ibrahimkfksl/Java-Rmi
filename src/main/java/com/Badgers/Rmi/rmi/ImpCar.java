package com.Badgers.Rmi.rmi;

import com.Badgers.Rmi.entity.Car;
import com.Badgers.Rmi.entity.Recipent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ImpCar extends UnicastRemoteObject implements ICar {
    private List<Car> carList = new ArrayList<>();
    private List<Recipent> recipentList = new ArrayList<>();

    public ImpCar() throws RemoteException {
        super();
        this.getAllCarInFile();
        this.getAllRecipentInFile();
    }

    @Override
    public void newCar(Car newCar) throws RemoteException {
        String filepath = "./data/cars.dat";
        try {
            FileWriter fileWriter = new FileWriter(filepath, true);

            fileWriter.write("{\n");
            fileWriter.write("\"serialNumber\":\"" + newCar.getSerialNumber() + "\",\n");
            fileWriter.write("\"brand\":\"" + newCar.getBrand() + "\",\n");
            fileWriter.write("\"model\":\"" + newCar.getModel() + "\",\n");
            fileWriter.write("\"color\":\"" + newCar.getColor() + "\",\n");
            fileWriter.write("\"modelYear\":\"" + newCar.getModelYear() + "\",\n");
            fileWriter.write("\"weight\":\"" + newCar.getWeight() + "\",\n");
            fileWriter.write("\"price\":\"" + newCar.getPrice() + "\",\n");
            fileWriter.write("},\n");

            fileWriter.close();
            carList.add(newCar);
            System.out.println("Araba Başarı İle Eklendi");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void newReceipt(Recipent recipent) throws RemoteException {
        String filepath = "./data/recipent.dat";
        try {
            FileWriter fileWriter = new FileWriter(filepath, true);

            fileWriter.write("{\n");
            fileWriter.write("\"id\":\"" + recipent.getId() + "\",\n");
            fileWriter.write("\"date\":\"" + recipent.getDate() + "\",\n");
            fileWriter.write("\"customerName\":\"" + recipent.getCustomerName() + "\",\n");
            fileWriter.write("\"carBrand\":\"" + recipent.getCarBrand() + "\",\n");
            fileWriter.write("\"carModel\":\"" + recipent.getCarModel() + "\",\n");
            fileWriter.write("\"carSerialNumber\":\"" + recipent.getCarSerialNumber() + "\",\n");
            fileWriter.write("\"carPrice\":\"" + recipent.getCarPrice() + "\",\n");
            fileWriter.write("},\n");

            fileWriter.close();
            recipentList.add(recipent);
            System.out.println("Fatura Başarı İle Eklendi");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Car findCarBySN(int serialNumber) throws RemoteException {
        return carList.stream().filter(car -> car.getSerialNumber() == serialNumber).findAny().get();
    }

    @Override
    public List<Car> findCarByBrand(String brand) throws RemoteException {
        return carList.stream().filter(car -> car.getBrand().equals(brand)).collect(Collectors.toList());

    }

    @Override
    public Recipent findReceiptById(int id) throws RemoteException {
        return recipentList.stream().filter(recipent -> recipent.getId() == id).findAny().get();
    }

    @Override
    public List<Recipent> findReceiptByCustomerName(String name) throws RemoteException {
        return recipentList.stream().filter(recipent -> recipent.getCustomerName().equals(name)).collect(Collectors.toList());
    }

    private void getAllCarInFile() {
        String filepath = "./data/cars.dat";
        StringBuilder data = new StringBuilder();
        try {
            File carsFile = new File(filepath);
            Scanner myReader = new Scanner(carsFile);
            while (myReader.hasNextLine()) {
                data.append(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(filepath + " bulunamadi");
            e.printStackTrace();
        }

        List<Object> list = new ArrayList<>();
        String[] split1 = data.toString().split("},");
        if(split1.length==1 && split1[0].equals("")){
            return;
        }
        for (String split : split1) {
            split = split.replaceAll("\\{\n", "");
            split = split.replaceAll("\"", "");
            String[] split2 = split.split(",");
            for (String splitSplit : split2) {
                list.add(splitSplit.split(":")[1]);
            }
            Car car = new Car();
            car.setSerialNumber(Integer.parseInt((String) list.get(0)));
            car.setBrand((String) list.get(1));
            car.setModel((String) list.get(2));
            car.setColor((String) list.get(3));
            car.setModelYear((String) list.get(4));
            car.setWeight(Float.parseFloat((String) list.get(5)));
            car.setPrice(Float.parseFloat((String) list.get(6)));
            carList.add(car);
            list.clear();
        }
    }

    private void getAllRecipentInFile() {
        String filepath = "./data/recipent.dat";
        StringBuilder data = new StringBuilder();
        try {
            File carsFile = new File(filepath);
            Scanner myReader = new Scanner(carsFile);
            while (myReader.hasNextLine()) {
                data.append(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(filepath + " bulunamadi");
            e.printStackTrace();
        }


        List<Object> list = new ArrayList<>();
        String[] split1 = data.toString().split("},");
        if(split1.length==1 && split1[0].equals("")){
            return;
        }
        for (String split : split1) {
            split = split.replaceAll("\\{\n", "");
            split = split.replaceAll("\"", "");
            String[] split2 = split.split(",");
            for (String splitSplit : split2) {
                list.add(splitSplit.split(":")[1]);
            }
            Recipent recipent = new Recipent();
            recipent.setId(Integer.parseInt((String) list.get(0)));
            recipent.setDate((String) list.get(1));
            recipent.setCustomerName((String) list.get(2));
            recipent.setCarBrand((String) list.get(3));
            recipent.setCarModel((String) list.get(4));
            recipent.setCarSerialNumber(Integer.parseInt((String) list.get(5)));
            recipent.setCarPrice(Float.parseFloat((String) list.get(6)));
            recipentList.add(recipent);
            list.clear();
        }

    }
}
