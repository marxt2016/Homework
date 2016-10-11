package model;

import compare.flyingDistanceComparator;
import exceptions.FindByParametersException;
import xmlParser.JaxbParser;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.File;
import java.util.*;


/**
 * Created by OM on 02.10.2016.
 */

@XmlRootElement(name = "aviacompany")
@XmlType(propOrder ={"name","planes"})
public class Aviacompany implements Calculations {
    public static final String PATH_TO_FILE = "./src/main/resources/aviacompany.xml";
    private String name;
    private List <Plane> planes;

    public Aviacompany(String name, List<Plane> planes) {
        this.name = name;
        this.planes = planes;
    }
    public Aviacompany() {    }

    public String getName() {
        return name;
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    @XmlElement(name="plane")
    @XmlElementWrapper
    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }

    @Override
    public String toString() {
        String result ="Aviacompany{" +
                "name='" + name + '\'' +
                ", planes {";
        if(planes!=null){
            for (Plane p:planes){
                result+=p.toString();
            }
        }
        result+="}}";
        return result;
    }


    public Aviacompany loadPlanesfromSource() throws Exception{
        JaxbParser parser = new JaxbParser();
        File file = new File(PATH_TO_FILE);
        return (Aviacompany) parser.getObject(file, Aviacompany.class);
    }

    public void printAllPlanes(){
        try {
            for (Plane plane: this.getPlanes()){
                System.out.println(plane.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  List <Plane> searchPlaneByModelAndCargovolume() throws FindByParametersException {
        System.out.println("Please provide plane model: ");
        String value1 = new Scanner(System.in).nextLine();
        int value;
        int counter = 0;
        List<Plane> results = new ArrayList<>();
        boolean repeatForCargoInput = true;
        do {
            System.out.println("Please provide cargo volume: ");
            try {
                value = new Scanner(System.in).nextInt();
                repeatForCargoInput = false;
                break;
            } catch (InputMismatchException e) {
                System.out.println(" Incorrect value was provided.\n Please try one more time");
            }
        } while (true);
        int value2 = value;


        try {
            for (Plane plane : this.getPlanes()) {
                if (value1.equalsIgnoreCase(plane.getModel()) | (value2 == plane.getCargoVolume())) {
                    counter++;
                    results.add(plane);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(counter == 0) {
            throw new FindByParametersException();
        }

        if (results.size() > 0) {
            for (Plane plane : results) {
                System.out.println(plane);
            }

        }
        return results;

    }
    @Override
    public int calculateNumberOfPassengers(){
        int numberOfSeats = 0;
        for (Plane plane:this.getPlanes()) {
            numberOfSeats += plane.getNumberOfSeats();
        }
        System.out.println("Total number of passengers = " +numberOfSeats);
        return numberOfSeats;
    }
    @Override
    public int calculateCargoVolume(){
        int cargoVolume = 0;
        for (Plane plane:this.getPlanes()) {
            cargoVolume += plane.getCargoVolume();
        }
        System.out.println("Total cargo volume = " +cargoVolume);
        return cargoVolume;
    }


    public void sortPlanesByDistance() {
        Collections.sort(this.getPlanes(), new flyingDistanceComparator());
        for(Plane plane: this.getPlanes()){
            System.out.println(plane.toString());
        }

    }
    public void sortCompareTo (){
        Collections.sort(this.getPlanes());
        for(Plane plane: this.getPlanes()){
            System.out.println(plane.toString());
        }
    }

}
