package model;

import compare.flyingDistanceComparator;
import exceptions.FindByParametersException;
import xmlParser.JaxbParser;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    public void addPlaneToFile() throws Exception{
        JaxbParser parser = new JaxbParser();
        File file = new File(PATH_TO_FILE);
        parser.saveObject(file, this);
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
                if (value1.equalsIgnoreCase(plane.getModel()) && (value2 == plane.getCargoVolume())) {
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
    public void  sortPlanesByModel (){
        Collections.sort(this.getPlanes());
        for(Plane plane: this.getPlanes()){
            System.out.println(plane.toString());
        }
    }

    public void addNewPlane(){

        Plane newPlane = new Plane();

        System.out.println("Please, provide model name.");
        String model = new Scanner(System.in).nextLine();
        newPlane.setModel(model);

        while(true) {
            System.out.println("Please, provide cargo volume.");
            try {
                int cargoVolume = new Scanner(System.in).nextInt();
                newPlane.setCargoVolume(cargoVolume);
                break;
            } catch (InputMismatchException e) {
                System.out.println(" Incorrect value was provided.\n Please try one more time");
            }

        }
        while(true) {
            System.out.println("Please, provide number of seats.");
            try {
                int numberOfSeats = new Scanner(System.in).nextInt();
                newPlane.setNumberOfSeats(numberOfSeats);
                break;
            } catch (InputMismatchException e) {
                System.out.println(" Incorrect value was provided.\n Please try one more time");
            }
        }

        while(true) {
            System.out.println("Please, provide flying distance.");
            try {
                float flyingDistance = new Scanner(System.in).nextFloat();
                newPlane.setAverageFlyingDistance(flyingDistance);
                break;
            } catch (InputMismatchException e) {
                System.out.println(" Incorrect value was provided.\n Please try one more time");
            }
        }

        this.planes.add(newPlane);
        this.setPlanes(planes);

        BufferedWriter bw = null;
        try {
            this.addPlaneToFile();
            bw = new BufferedWriter(new FileWriter("./src/main/resources/addedPlanes.txt", true));
            bw.write(newPlane.toString());
            bw.newLine();
            bw.flush();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (bw!=null) try{
                bw.close();

            }catch(IOException e){
                e.printStackTrace();
            }
        }


        System.out.println("New plane was added: "+newPlane.toString());
        this.printAllPlanes();

    }


    public void deletePlane(){
        try {
            List <Plane> planesToDelete = this.searchPlaneByModelAndCargovolume();
            System.out.println(planesToDelete.size());
            List <Plane> result = this.getPlanes();
            result.removeAll(planesToDelete);
        }catch (FindByParametersException e){
            e.getMessage();
        }

        try {
            this.addPlaneToFile();
        }catch(Exception e){
            e.getMessage();
        }
        this.printAllPlanes();
    }

}
