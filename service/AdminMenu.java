package service;

import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void adminMenu(){
        boolean keepRunning = true;
            while(keepRunning){
                try {
                    System.out.println("Admin Menu");
                    System.out.println("----------------------------------------------");
                    System.out.println("1. See all Customers" +
                            "\n2. See all rooms" +
                            "\n3. See all reservations." +
                            "\n4. Add a room." +
                            "\n5. Back to MainMenu");
                    System.out.println("----------------------------------------------");
                    System.out.println("Select a number for the menu option");
                    int userSelection = Integer.parseInt(scanner.next());
                    switch (userSelection){
                        case 1:
                            seeAllCustomers();
                            break;
                        case 2:
                            showAllRooms();
                            break;
                        case 3:
                            AdminResource.displayAllReservations();
                            break;
                        case 4:
                            addRoom();
                            break;
                        case 5:
                            MainMenu.mainMenu();
                            break;
                        default:
                            System.out.println("Enter a valid Value between 1 - 5");
                            break;
                    }
                }catch (Exception ex){
                    System.out.println(ex);
                }
            }
    }

    public static void showAllRooms(){
        Collection<IRoom> rooms = AdminResource.getAllRooms();
        if (rooms.isEmpty()){
            System.out.println("No Rook has been added yet");
        }else {
            for (IRoom room : rooms){
                System.out.println(room);
                System.out.println("---------------------------");
            }
        }
    }

    public static void addRoom(){
        boolean keepRunning = true;
        Scanner scanner = new Scanner(System.in);
        List<IRoom> newRooms = new ArrayList<>();
        while (keepRunning){
            try {
                System.out.println("Enter room number");
                String roomNumber = scanner.next();
                System.out.println("Enter price per night");
                Double price = scanner.nextDouble();
                System.out.println("Enter room type: 1 for single bed, 2 for double bed");
                int roomType = scanner.nextInt();
                RoomType type = RoomType.SINGLE;
                if (roomType == 1){
                    type = RoomType.SINGLE;
                }else if (roomType == 2){
                    type = RoomType.DOUBLE;
                }
                Room newRoom = new Room(roomNumber,price,type);
                newRooms.add(newRoom);
                System.out.println("Press y to add another room or n to go back to main Menu");
                Scanner scanner1 = new Scanner(System.in);
                String answer = scanner1.nextLine();
                switch (answer.toLowerCase()){
                    case "y":
                        keepRunning = true;
                        break;
                    case "n":
                        keepRunning = false;
                        AdminResource.addRoom(newRooms);
                        break;
                    default:
                        System.out.println("Invalid Selection");
                        break;
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
        adminMenu();
    }

    public static void seeAllCustomers(){
        Collection<Customer> customers = AdminResource.getAllCustomers();
        if (customers.isEmpty()){
            System.out.println("There is no registered Customer for now");
        }else {
            for (Customer customer : customers){
                System.out.println(customer);
                System.out.println("--------------------------------");
            }
        }
    }
}
