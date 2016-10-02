package model;
import XMLParser.Parser;

import java.util.*;

/**
 * Created by OM on 02.10.2016.
 */
public class Aviacompany implements Actions {

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

    @Override
    public int calculateNumberOfPassengers(List<Plane> passengerPlanesList){
        int numberOfSeats = 0;
        for (int i=0; i<passengerPlanesList.size();i++ ) {
            PassengerPlane pp= (PassengerPlane)passengerPlanesList.get(i);
            numberOfSeats += pp.getNumberOfSeats();
        }
        System.out.println("Total number of seats = " +numberOfSeats);
        return numberOfSeats;
    }
    @Override
    public int calculateCargoVolume(List<Plane> planesList){
        int cargoVolume = 0;
        for (int i=0; i<planesList.size();i++ ) {
            cargoVolume += planesList.get(i).getCargoVolume();;
        }
        System.out.println("Total cargo volume = " +cargoVolume);
        return cargoVolume;
    }

    public static List generatePlanesList(String pathtofile, String planeType){
        List planesList = null;
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

    public List<Plane> generateAllPlanesList(String pathtofile){
        List cargoPlanesList = generatePlanesList(pathtofile, "cargo");
        List passengerPlanesList = generatePlanesList(pathtofile, "passenger");
        List<Plane> allPlanesList = new ArrayList<Plane>(passengerPlanesList);
        allPlanesList.addAll(cargoPlanesList);
        return allPlanesList;
    }

    public void printAllPlanesList(String pathtofile){
        List cargoPlanesList = generatePlanesList(pathtofile, "cargo");
        List passengerPlanesList = generatePlanesList(pathtofile, "passenger");
        List<Plane> allPlanesList = new ArrayList<Plane>(passengerPlanesList);
        allPlanesList.addAll(cargoPlanesList);
        for (int i=0; i<allPlanesList.size();i++ ) {
            System.out.println(allPlanesList.get(i).toString());
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
        for (int i=0; i<planesList.size();i++ ) {
            System.out.println(planesList.get(i).toString());
        }



    }
}
