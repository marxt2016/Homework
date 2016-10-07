package model;


import java.util.ArrayList;

/**
 * Created by OM on 02.10.2016.
 */

public class PlaneJaxb {


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
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getAverageRidingSpeed() {
        return averageRidingSpeed;
    }

    public void setAverageRidingSpeed(float averageRidingSpeed) {
        this.averageRidingSpeed = averageRidingSpeed;
    }

    public float getAverageFlyingSpeed() {
        return averageFlyingSpeed;
    }

    public void setAverageFlyingSpeed(float averageFlyingSpeed) {
        this.averageFlyingSpeed = averageFlyingSpeed;
    }
    @Override
    public String toString() {
        return "PlaneJaxb{" +
                "model='" + model + '\'' +
                ", cargoVolume=" + cargoVolume +
                ", averageRidingSpeed=" + averageRidingSpeed +
                ", averageFlyingSpeed=" + averageFlyingSpeed +
                ", averageFlyingDistance=" + averageFlyingDistance +
                '}';
    }



}
