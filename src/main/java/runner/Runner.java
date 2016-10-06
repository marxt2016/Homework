package runner;

/**
 * Created by OM on 02.10.2016.
 */
import exceptions.FileNotFoundException;
import exceptions.FindByParametersException;
import model.Aviacompany;
import model.Plane;
import search.SearchByParameters;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Runner {

    public static final String PATH_TO_FILE = "D:\\workspace\\Homework\\src\\main\\resources\\planes.xml";


    public static void main (String args[]){

        boolean repeat = true;

        while(repeat){
            System.out.println("Please select an option: \n" +
                               "1 - print all planes in the company; \n" +
                               "2 - sort planes depending on distance; \n" +
                               "3 - search by model name and cargo volume;\n" +
                               "4 - provide total available number of passengers;\n" +
                                "5 - provide total available cargo capacity;\n" +
                                "0 - exit.");
            Scanner scanner = new Scanner(System.in);
            int action = scanner.nextInt();
            Aviacompany aviacompany = new Aviacompany();

            switch(action){
                case 1:
                    aviacompany.setName("MyCompany");
                    System.out.println(aviacompany.toString() + "  has the following planes list:\n");
                    try {
                        aviacompany.printAllPlanesList(PATH_TO_FILE);
                    }catch(FileNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Sorted by distance planes list:\n ");
                    aviacompany.sortPlanesByDistance(Aviacompany.generateAllPlanesList(PATH_TO_FILE));
                    break;
                case 3:
                    System.out.println("Please provide plane model: ");
                    String value1 = new Scanner(System.in).nextLine();
                    int value;
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
                    }while(true);
                    int value2 = value;
                    try {
                        List<Plane> result = SearchByParameters.findByParametes(value1, value2, PATH_TO_FILE);
                        if( result.size()>0){
                           for (Plane plane:result){
                               System.out.println(plane);
                           }
                        }
                    }catch (FindByParametersException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Total number of passengers: \n ");
                    aviacompany.calculateNumberOfPassengers(Aviacompany.generatePlanesList(PATH_TO_FILE, "passenger"));
                    break;
                case 5:
                    System.out.println("Total cargo volume: \n ");
                    aviacompany.calculateCargoVolume(Aviacompany.generateAllPlanesList(PATH_TO_FILE));
                    break;
                case 0:
                    System.out.println("Completed!");
                    repeat = false;
                    break;
                default:
                    System.out.println();

            }
        }
    }
}
