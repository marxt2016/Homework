package runner;

/**
 * Created by OM on 02.10.2016.
 */
import model.Aviacompany;

import java.io.File;

public class Runner {

    public static final String PATHTOFILE = "//D:\\workspace\\Homework\\resources\\planes.xml";


    public static void main (String args[]){
        Aviacompany aviacompany = new Aviacompany();
        aviacompany.setName("MyCompany");
        System.out.println(aviacompany.toString());
        aviacompany.printAllPlanesList(PATHTOFILE);
        aviacompany.calculateNumberOfPassengers(Aviacompany.generatePlanesList(PATHTOFILE, "passenger"));
        aviacompany.calculateCargoVolume(aviacompany.generateAllPlanesList(PATHTOFILE));
        aviacompany.sortPlanesByDistance(aviacompany.generateAllPlanesList(PATHTOFILE));

    }
}
