package search;

import exceptions.FindByParametersException;
import model.Plane;

import java.util.List;

/**
 * Created by OM on 02.10.2016.
 */
public class SearchByParameters {

    public static void findByParametes(List<String> searchValue, List<Plane> planesList) {
        // int counter = 0;
        for (Plane plane : planesList) {
            for (String searchvalue : searchValue) {
                if (searchValue.equals(plane.getCargoVolume())) {
                    System.out.println(plane.toString());
                    //counter ++;
                }
            }

        }

    }

}
