package runner;

import exceptions.FileNotFoundException;
import model.Aviacompany;
import model.AviacompanyJaxb;

import java.util.Scanner;

/**
 * Created by qwer on 07.10.2016.
 */
public class RunnerJaxb {


    public static void main(String args[]) {


        boolean repeat = true;

        while (repeat) {

            System.out.println("Please select an option: \n" +
                    "1 - print all planes in the company; \n" +
                    "2 - sort planes depending on distance; \n" +
                    "3 - search by model name and cargo volume;\n" +
                    "4 - provide total available number of passengers;\n" +
                    "5 - provide total available cargo capacity;\n" +
                    "0 - exit.");

            Scanner scanner = new Scanner(System.in);
            int action = scanner.nextInt();
            try {
                AviacompanyJaxb aviacompany = new AviacompanyJaxb().loadPlanesfromSource();

                switch (action) {
                    case 1:
                        System.out.println(aviacompany.getName());
                        aviacompany.printAllPlanes();
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
        }
    }
}