package search;

import exceptions.FindByParametersException;
import model.Aviacompany;
import model.Plane;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OM on 02.10.2016.
 */
public class SearchByParameters {

    public static List<Plane> findByParametes(String value1, int value2, String pathtofile) throws FindByParametersException {
        int counter = 0;
        List <Plane> planesList = Aviacompany.generateAllPlanesList(pathtofile);
        List <Plane> results = new ArrayList<>();
        for (Plane plane : planesList) {
                if (value1.equalsIgnoreCase(plane.getModel()) | (value2 == plane.getCargoVolume())) {
                    counter ++;
                    results.add(plane);
                }else{
                    results.isEmpty();
                }
        }
        if(counter == 0) {
            throw new FindByParametersException();
        }
        return results;

    }

}
