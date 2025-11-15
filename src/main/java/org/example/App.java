package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;


public class App 
{

    public static void main( String[] args )
    {
//        TruckService truckService =new TruckService();
//
//        Trucks tata =new Trucks("TATA","2015",12000,"Rajesh");
//        Trucks volvo =new Trucks("VOLVO","2010",15000,"Kamal");
//        Trucks bharatbenz =new Trucks("BHARAT BENZ","2018",15000,"Mohan");
//        Trucks ashokleyland =new Trucks("Ashok Leyland","2010",10000,"Deepak");
//
//        //adding data in db
//        System.out.println("Adding data..........");
//        truckService.addTruck(tata);
//        truckService.addTruck(volvo);
//        truckService.addTruck(bharatbenz);
//        truckService.addTruck(ashokleyland);
//
//        //fetch
//        System.out.println("Fetching data by id............."+1);
//        Trucks truck =truckService.getTruckById(1);
//        System.out.println("Truck data: "+truck);
//
//        //update data
//        System.out.println("Updated data...........");
//        truck.setDriver_name("Ramesh");
//        truckService.updateTruck(truck);
//        System.out.println("Updated data: "+truckService.getTruckById(1));
//
//        //get all trucks
//        System.out.println("Fetching all data............");
//        List<Trucks> allTrucks =truckService.getAllTrucks();
//        System.out.println("All trucks: ");
//        for(Trucks truck1:allTrucks){
//            System.out.println(truck1);
//        }
//
//        //delete truck data
//        System.out.println("Deleting data by id..........."+2);
//        truckService.deleteTruck(2);
//        System.out.println("Deleted trucks by id: "+2);
//
//        System.out.println("Get all data............");
//        allTrucks=truckService.getAllTrucks();
//        System.out.println("All trucks after all operations: ");
//        System.out.println(allTrucks);
//

        TruckService truckService = new TruckService();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n========= TRUCK MANAGEMENT SYSTEM =========");
            System.out.println("1. Add Truck");
            System.out.println("2. Get Truck By ID");
            System.out.println("3. Update Truck");
            System.out.println("4. Delete Truck");
            System.out.println("5. Get All Trucks");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // consumes newline

            switch (choice) {

                case 1:   // ADD TRUCK
                    System.out.println("Enter Truck Company: ");
                    String company = sc.nextLine();

                    System.out.println("Enter Truck Model Year: ");
                    String year = sc.nextLine();

                    System.out.println("Enter Truck Capacity: ");
                    int capacity = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter Driver Name: ");
                    String driver = sc.nextLine();

                    Trucks newTruck = new Trucks(company, year, capacity, driver);
                    truckService.addTruck(newTruck);

                    System.out.println("Truck Added Successfully!");
                    break;

                case 2:   // GET TRUCK BY ID
                    System.out.println("Enter Truck ID: ");
                    int id = sc.nextInt();

                    Trucks t = truckService.getTruckById(id);
                    if (t != null)
                        System.out.println("Truck Found: " + t);
                    else
                        System.out.println("Truck Not Found!");
                    break;

                case 3:   // UPDATE TRUCK
                    System.out.println("Enter Truck ID to Update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    Trucks updateTruck = truckService.getTruckById(updateId);

                    if (updateTruck == null) {
                        System.out.println("Truck Not Found!");
                    } else {
                        System.out.println("Enter New Driver Name: ");
                        String newDriver = sc.nextLine();

                        updateTruck.setDriver_name(newDriver);
                        truckService.updateTruck(updateTruck);

                        System.out.println("Truck Updated: " + updateTruck);
                    }
                    break;

                case 4:   // DELETE TRUCK
                    System.out.println("Enter Truck ID to Delete: ");
                    int deleteId = sc.nextInt();

                    truckService.deleteTruck(deleteId);
                    System.out.println("Truck Deleted Successfully!");
                    break;

                case 5:   // GET ALL TRUCKS
                    List<Trucks> allTrucks = truckService.getAllTrucks();
                    if (allTrucks.isEmpty()) {
                        System.out.println("No Trucks Found!");
                    } else {
                        System.out.println("All Trucks:");
                        for (Trucks truck : allTrucks) {
                            System.out.println(truck);
                        }
                    }
                    break;

                case 6:   // EXIT
                    running = false;
                    System.out.println("Exiting the Program...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }

        sc.close();
    }
}
