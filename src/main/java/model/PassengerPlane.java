package model;

import xmlParser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by OM on 02.10.2016.
 */
public class PassengerPlane extends Plane {

    private String model;
    private int numberOfSeats;
    private int cargoVolume;
    private float averageFlyingSpeed;
    private float averageFlyingDistance;


    public PassengerPlane(String model, int numberOfSeats,int cargoVolume,  float averageFlyingSpeed, float averageFlyingDistance) {
        this.model = model;
        this.numberOfSeats = numberOfSeats;
        this.averageFlyingSpeed = averageFlyingSpeed;
        this.averageFlyingDistance = averageFlyingDistance;
        this.cargoVolume = cargoVolume;
    }

    public PassengerPlane() {

    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getNumberOfSeats() {
        return numberOfSeats;
    }
    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
    public float getAverageFlyingSpeed() {
        return averageFlyingSpeed;
    }
    public void setAverageFlyingSpeed(float averageFlyingSpeed) {
        this.averageFlyingSpeed = averageFlyingSpeed;
    }
    public float getAverageFlyingDistance() {
        return averageFlyingDistance;
    }
    public void setAverageFlyingDistance(float averageFlyingDistance) {
        this.averageFlyingDistance = averageFlyingDistance;
    }

    @Override
    public String showModelToConsole(Plane plane){
       // System.out.println("Model = " + this.model);
        return this.model;
    }

    @Override
    public void canFly(){
        System.out.println("This plane can fly with average speed = " + this.averageFlyingSpeed);
    }
    @Override
    public void canRide(){
        System.out.println(new PassengerPlane().toString() + "can ride with average speed = 40 kmph");

    }

    @Override
    public String toString() {
        return "PassengerPlane{" +
                "model='" + model + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", cargoVolume=" + cargoVolume +
                ", averageFlyingSpeed=" + averageFlyingSpeed +
                ", averageFlyingDistance=" + averageFlyingDistance +
                '}';
    }

    @Override
    public ArrayList<Plane> createPlanesList(String pathtofile, String planestype){
        Parser parser = new Parser();
        ArrayList<Plane> passengerPlaneList = new ArrayList<Plane>();
        Map<String,List<String>> valuesPassenger = parser.parseDocument(pathtofile, planestype);
        for (Object variableName: valuesPassenger.keySet()){
            PassengerPlane passengerPlane= new PassengerPlane(
                    valuesPassenger.get(variableName).get(0),
                    new Integer(valuesPassenger.get(variableName).get(1)),
                    new Integer(valuesPassenger.get(variableName).get(2)),
                    new Float(valuesPassenger.get(variableName).get(4)),
                    new Float(valuesPassenger.get(variableName).get(5))
                    );
            passengerPlaneList.add(passengerPlane);
        }
        return passengerPlaneList;
    }


}
