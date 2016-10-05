package model;

import xmlParser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by OM on 02.10.2016.
 */
public class CargoPlane extends Plane {


    private String model;
    private int cargoVolume;
    private float averageRidingSpeed;
    private float averageFlyingDistance;

    public CargoPlane(String model, int cargoVolume, float averageRidingSpeed, float averageFlyingDistance) {
        this.model = model;
        this.cargoVolume = cargoVolume;
        this.averageRidingSpeed = averageRidingSpeed;
        this.averageFlyingDistance = averageFlyingDistance;
    }
    public CargoPlane() {

    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getCargoVolume() {
        return cargoVolume;
    }
    public void setCargoVolume(int cargoVolume) {
        this.cargoVolume = cargoVolume;
    }
    public float getAverageRidingSpeed() {
        return averageRidingSpeed;
    }
    public void setAverageRidingSpeed(float averageRidingSpeed) {
        this.averageRidingSpeed = averageRidingSpeed;
    }
    public float getAverageFlyingDistance() {
        return averageFlyingDistance;
    }
    public void setAverageFlyingDistance(float averageFlyingDistance) {
        this.averageFlyingDistance = averageFlyingDistance;
    }

    @Override
    public String showModelToConsole(Plane plane){
        System.out.println("Model = " + this.model);
        return this.model;
    }

    @Override
    public void canFly(){
        System.out.println(new CargoPlane().toString() + "  can fly with average speed = 200 kmph");
    }
    @Override
    public void canRide(){
        System.out.println( "Can ride with average speed = " + this.averageRidingSpeed);

    }

    @Override
    public String toString() {
        return "CargoPlane{" +
                "model='" + model + '\'' +
                ", cargoVolume=" + cargoVolume +
                ", averageRidingSpeed=" + averageRidingSpeed +
                ", averageFlyingDistance=" + averageFlyingDistance +
                '}';
    }

    @Override
    public ArrayList<Plane> createPlanesList(String pathtofile, String planestype){
        Parser parser = new Parser();
        ArrayList<Plane> cargoPlaneList = new ArrayList<Plane>();
        Map<String,List<String>> valuesCargo = parser.parseDocument(pathtofile, planestype);
        for (Object variableName: valuesCargo.keySet()){
            CargoPlane cargoPlane= new CargoPlane(
                    valuesCargo.get(variableName).get(0),
                    new Integer(valuesCargo.get(variableName).get(2)),
                    new Float(valuesCargo.get(variableName).get(3)),
                    new Float(valuesCargo.get(variableName).get(5)));
            cargoPlaneList.add(cargoPlane);
        }
        return cargoPlaneList;
    }
}
