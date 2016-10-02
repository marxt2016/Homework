package model;

import java.util.List;

/**
 * Created by OM on 02.10.2016.
 */
public interface Actions {

    int calculateNumberOfPassengers(List<Plane> passengerPlanesList);
    int calculateCargoVolume(List<Plane> planesList);

}