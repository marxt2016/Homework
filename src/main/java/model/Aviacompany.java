package model;

import exceptions.FileNotFoundException;

import java.util.*;

/**
 * Created by OM on 02.10.2016.
 */
public class Aviacompany implements Calculations {

    private String name;
    public Aviacompany() {    }

    public Aviacompany(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Aviacompany{" +
                "name='" + name + '\'' +
                '}';
    }


    public int calculateNumberOfPassengers(List<Plane> passengerPlanesList){
        int numberOfSeats = 0;
        for (Plane plane:passengerPlanesList) {
            PassengerPlane pp= (PassengerPlane)plane;
            numberOfSeats += pp.getNumberOfSeats();
        }
        System.out.println("Total number of seats = " +numberOfSeats);
        return numberOfSeats;
    }

    public int calculateCargoVolume(List<Plane> planesList){
        int cargoVolume = 0;
        for (Plane plane:planesList) {
            cargoVolume += plane.getCargoVolume();
        }
        System.out.println("Total cargo volume = " +cargoVolume);
        return cargoVolume;
    }


    public static List<Plane> generatePlanesList(String pathtofile, String planeType){
        List <Plane> planesList = null;
        switch(planeType){
            case "cargo":
                planesList=new CargoPlane().createPlanesList(pathtofile, "c");
                break;
            case "passenger":
                planesList=new PassengerPlane().createPlanesList(pathtofile, "p");
                break;
            default:
                System.out.println("Incorrect plane type");
        }

        return planesList;

    }

    public static List<Plane> generateAllPlanesList(String pathtofile){
        List <Plane> cargoPlanesList = generatePlanesList(pathtofile, "cargo");
        List <Plane> passengerPlanesList = generatePlanesList(pathtofile, "passenger");
        List<Plane> allPlanesList = new ArrayList<Plane>(passengerPlanesList);
        allPlanesList.addAll(cargoPlanesList);
        return allPlanesList;
    }

    public void printAllPlanesList(String pathtofile) throws FileNotFoundException{
        if (pathtofile.length()>0) {
            List<Plane> cargoPlanesList = generatePlanesList(pathtofile, "cargo");
            List<Plane> passengerPlanesList = generatePlanesList(pathtofile, "passenger");
            List<Plane> allPlanesList = new ArrayList<Plane>(passengerPlanesList);
            allPlanesList.addAll(cargoPlanesList);
            for (Plane plane : allPlanesList) {
                System.out.println(plane.toString());
            }
        } else{
            throw new FileNotFoundException();
        }
    }

    public void sortPlanesByDistance(List<Plane> planesList) {
        Collections.sort(planesList, new Comparator<Plane>() {
            @Override
            public int compare(Plane p1, Plane p2) {
                if (p1.getAverageFlyingDistance() > p2.getAverageFlyingDistance())
                    return 1;
                if (p1.getAverageFlyingDistance() < p2.getAverageFlyingDistance())
                    return -1;
                return 0;
            }
        });
        for (Plane plane:planesList  ) {
            System.out.println(plane.toString());
        }



    }
}
