package runner;

import exceptions.FindByParametersException;
import model.Aviacompany;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by qwer on 07.10.2016.
 */
public class Runner {


    public static void main(String args[]) {


        boolean repeat = true;

        while (repeat) {
            try{
                System.out.println("Please select an option: \n" +
                        "1 - print all planes in the company; \n" +
                        "2 - sort planes depending on distance; \n" +
                        "3 - search by model name and cargo volume;\n" +
                        "4 - provide total available number of passengers;\n" +
                        "5 - provide total available cargo capacity;\n" +
                        "6 - add a new plane.\n"+
                        "7 - delete a plane.\n"+
                        "0 - exit.");

                Scanner scanner = new Scanner(System.in);
                int action = scanner.nextInt();
                try {
                    Aviacompany aviacompany = new Aviacompany().loadPlanesfromSource();
                    switch (action) {
                        case 1:
                            System.out.println(aviacompany.getName());
                            aviacompany.printAllPlanes();
                            break;
                        case 2:
                            //aviacompany.sortPlanesByDistance();
                            aviacompany.sortPlanesByModel();
                            break;
                        case 3:
                            try {
                                aviacompany.searchPlaneByModelAndCargovolume();
                            }catch(FindByParametersException e) {
                               System.out.println(e.getMessage());
                            }
                            break;
                        case 4:
                            aviacompany.calculateNumberOfPassengers();
                            break;
                        case 5:
                            aviacompany.calculateCargoVolume();
                            break;
                        case 6:
                            aviacompany.addNewPlane();
                            break;
                        case 7:
                            aviacompany.deletePlane();
                            break;
                        case 0:
                            repeat = false;
                            break;
                        default:
                            break;

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }catch(InputMismatchException e){
                System.out.println("Incorrect value was provided \n Please try one more time.");
            }
        }
    }
}