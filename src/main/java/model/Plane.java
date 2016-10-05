package model;

import java.util.ArrayList;

/**
 * Created by OM on 02.10.2016.
 */
public abstract class Plane {

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    private String model;
    private int cargoVolume;
    private float averageRidingSpeed;
    private float averageFlyingSpeed;
    private float averageFlyingDistance;

    public float getAverageFlyingDistance() {
        return averageFlyingDistance;
    }

    public void setAverageFlyingDistance(float averageFlyingDistance) {
        this.averageFlyingDistance = averageFlyingDistance;
    }
    public int getCargoVolume() {
        return cargoVolume;
    }

    public void setCargoVolume(int cargoVolume) {
        this.cargoVolume = cargoVolume;
    }


    public abstract String showModelToConsole(Plane plane);

    public abstract void canFly();

    public abstract void canRide();

    public abstract ArrayList<Plane> createPlanesList(String pathtofile, String planestype);


}
