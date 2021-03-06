package service;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;
import java.util.regex.Pattern;

public class MainMenu {

    public static void mainMenu(){
        boolean keepRunning = true;
        try (Scanner scanner = new Scanner(System.in)){
            while(keepRunning){
                try {
                    System.out.println("Welcome to the Hotel Reservation Application");
                    System.out.println("----------------------------------------------");
                    System.out.println("1. Find and reserve a room." +
                            "\n2. See my reservations" +
                            "\n3. Create an account." +
                            "\n4. Admin." +
                            "\n5. Exit");
                    System.out.println("----------------------------------------------");
                    System.out.println("Select a number for the menu option");
                    int userSelection = Integer.parseInt(scanner.next());
                    switch (userSelection){
                        case 1:
                            keepRunning = false;
                            findRoom();
                            break;
                        case 2:
                            keepRunning = false;
                            seeMyReservations();
                            break;
                        case 3:
                            keepRunning = false;
                            createAccount();
                            break;
                        case 4:
                            keepRunning = false;
                            AdminMenu.adminMenu();
                            break;
                        case 5:
                            keepRunning = false;
                            break;
                        default:
                            System.out.println("Enter a value between 1 - 5");
                            break;
                    }
                }catch (Exception ex){
                    System.out.println("Enter a valid value");
                }
            }
        }
    }

    public static void findRoom(){
        String dateRegEx = "^[\\d][\\d]/[\\d][\\d]/[\\d][\\d][\\d][\\d]$";
        Pattern datePattern = Pattern.compile(dateRegEx);
        Scanner userInput = new Scanner(System.in);
        boolean firstDateCorrect = false;
        boolean secondDateCorrect = false;
        while (!firstDateCorrect){
            System.out.println("Enter Check in date mm/dd/yyyy example 02/30/1999");
            String checkInDate = userInput.nextLine();
            if (datePattern.matcher(checkInDate).matches()){
                firstDateCorrect = true;
                while(!secondDateCorrect){
                    System.out.println("Enter Check out date mm/dd/yyyy example 02/30/1999");
                    String checkOutDate = userInput.nextLine();
                    if (datePattern.matcher(checkOutDate).matches()){
                        secondDateCorrect = true;
                        Collection<IRoom> availableRooms = HotelResource.findARoom(setDate(checkInDate),setDate(checkOutDate));
                        if (!availableRooms.isEmpty()){
                            System.out.println("Available Rooms");
                            for (IRoom room: availableRooms){
                                System.out.println(room);
                                System.out.println("CheckInDate: "+setDate(checkInDate));
                                System.out.println("checkOutDate: "+setDate(checkOutDate));
                                System.out.println("------------------------------------");
                            }
                            if (yesOrNo("Would you like to book a room? Y/N").equalsIgnoreCase("y")){
                                bookARoom(setDate(checkInDate),setDate(checkOutDate),availableRooms);
                            }else{
                                mainMenu();
                                break;
                            }
                        }else {
                            int extraDays = 1;
                            while (extraDays <=7){
                                Collection<IRoom> recommendedRooms = HotelResource.findARoom(setDate(checkInDate,extraDays),setDate(checkOutDate,extraDays));
                                if (!recommendedRooms.isEmpty()) {
                                    System.out.println("Recommended Room");
                                    for (IRoom room : recommendedRooms) {
                                        System.out.println(room);
                                        System.out.println("CheckInDate: " + setDate(checkInDate, extraDays));
                                        System.out.println("checkOutDate: " + setDate(checkOutDate, extraDays));
                                        System.out.println("------------------------------------");
                                    }
                                    if (yesOrNo("Would you like to book a room? Y/N").equalsIgnoreCase("y")) {
                                        bookARoom(setDate(checkInDate, extraDays), setDate(checkOutDate, extraDays), availableRooms);
                                        break;
                                    } else {
                                        mainMenu();
                                        break;
                                    }
                                }
                                extraDays = extraDays + 1;
                            }
                            System.out.println("No rooms available at the moment...try again later");
                            mainMenu();
                        }
                    }else {
                        System.out.println("Invalid Date format!!!");
                    }
                }
            }else {
                System.out.println("Invalid Date format!!!");
            }
        }

    }

    private static String yesOrNo(String question) {
        String answer = "";
        Scanner userInput = new Scanner(System.in);
        boolean keepAsking = true;
        while (keepAsking){
            System.out.println(question);
            answer = userInput.nextLine();
            if (answer.equalsIgnoreCase("y")){
                keepAsking = false;
            }else if (answer.equalsIgnoreCase("n")){
                keepAsking = false;
            }else {
                System.out.println("Invalid Selection, Enter Y for Yes or N for NO");
            }
        }
        return answer;
    }

    public static void bookARoom(Date checkIn,Date checkOut,Collection<IRoom> availableRooms){
        if (yesOrNo("Do you have an account with us? Y/N").equalsIgnoreCase("y")){
            reserveARoom(checkIn,checkOut,availableRooms);
            mainMenu();
        }else{
            //come back
            mainMenu();
        }
    }

    public static void reserveARoom(Date checkIn,Date checkOut,Collection<IRoom> availableRooms){
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter Email");
        String email = userInput.nextLine();
        Customer customer = AdminResource.getCustomer(email);
        if (customer != null){
            System.out.println("What room would you like to reserve");
            String roomNumber = userInput.nextLine();
            IRoom room = HotelResource.getARoom(roomNumber);
            if (room != null && availableRooms.contains(room)) {
               Reservation newReservation = HotelResource.bookARoom(email,room,checkIn,checkOut);
               System.out.println(newReservation);
            }else {
                System.out.println("Room number "+ roomNumber +" is not available.");
            }
        }else{
            System.out.println("Customer with email "+ email +" does not exist.");
        }

    }

    public static Date setDate(String dateEntered){
        Calendar calender = Calendar.getInstance();
        String[] dateArray = new String[3];
        dateArray = dateEntered.split("/");
        calender.set(Integer.parseInt(dateArray[2]),Integer.parseInt(dateArray[0]),Integer.parseInt(dateArray[1]));
        calender.set(Calendar.HOUR_OF_DAY,0);
        calender.set(Calendar.MINUTE,0);
        calender.set(Calendar.SECOND,0);
        calender.set(Calendar.MILLISECOND,0);
        Date date = calender.getTime();
        return date;
    }

    public static Date setDate(String dateEntered,int addedDays){
        Calendar calender = Calendar.getInstance();
        String[] dateArray = new String[3];
        dateArray = dateEntered.split("/");
        calender.set(Integer.parseInt(dateArray[2]),Integer.parseInt(dateArray[0]),Integer.parseInt(dateArray[1]));
        calender.add(Calendar.DAY_OF_MONTH,addedDays);
        calender.set(Calendar.HOUR_OF_DAY,0);
        calender.set(Calendar.MINUTE,0);
        calender.set(Calendar.SECOND,0);
        calender.set(Calendar.MILLISECOND,0);
        Date date = calender.getTime();
        return date;
    }

    public static void seeMyReservations(){
        Scanner userInput = new Scanner(System.in);
        try {
            System.out.println("Enter Customer Email");
            String email = userInput.nextLine();
            Collection<Reservation> customersReservations = HotelResource.getCustomersReservations(email);
            if (customersReservations.isEmpty()){
                System.out.println("Customer does not have any reservations");
            }else {
                for(Reservation reservation : customersReservations){
                    System.out.println(reservation);
                    System.out.println("--------------------------------------");
                }
            }
            mainMenu();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public static void createAccount(){
        Scanner userInput = new Scanner(System.in);
        try {
            System.out.println("Enter Email");
            String email = userInput.nextLine();
            System.out.println("Enter First Name");
            String firstName = userInput.nextLine();
            System.out.println("Enter Last Name");
            String lastName = userInput.nextLine();
            HotelResource.createACustomer(email,firstName,lastName);
            mainMenu();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}
