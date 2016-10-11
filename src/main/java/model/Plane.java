package model;


/**
 * Created by OM on 02.10.2016.
 */

public class Plane implements Comparable<Plane> {


    private String model;
    private int cargoVolume;
    private int numberOfSeats;
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

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Plane(){};

    public Plane(String model, int cargoVolume, int numberOfSeats, float averageRidingSpeed, float averageFlyingSpeed, float averageFlyingDistance) {
        this.model = model;
        this.cargoVolume = cargoVolume;
        this.numberOfSeats = numberOfSeats;
        this.averageRidingSpeed = averageRidingSpeed;
        this.averageFlyingSpeed = averageFlyingSpeed;
        this.averageFlyingDistance = averageFlyingDistance;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "model='" + model + '\'' +
                ", cargoVolume=" + cargoVolume +
                ", numberOfSeats=" + numberOfSeats +
                ", averageRidingSpeed=" + averageRidingSpeed +
                ", averageFlyingSpeed=" + averageFlyingSpeed +
                ", averageFlyingDistance=" + averageFlyingDistance +
                '}';
    }


    @Override
    public int compareTo(Plane p) {
        int nameDiff = this.model.compareToIgnoreCase(p.model);
        if(nameDiff!=0){
            return nameDiff;
        }
        return 0;
        //return Float.compare(this.getAverageFlyingDistance(),p.getAverageFlyingDistance());
        //return (this.cargoVolume<p.cargoVolume)? -1 : (this.cargoVolume>p.cargoVolume)? 1:0;
    }
}
