package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static Map<String,IRoom> rooms = new HashMap<>();
    private static Collection<Reservation> reservations = new ArrayList<>();

    public static void addRoom(IRoom room){
       rooms.put(room.getRoomNumber(),room);
    }
    public static IRoom getARoom(String roomId){
        return rooms.get(roomId);
    }

    public static Reservation reserveARoom(Customer customer,IRoom room, Date checkInDate,Date checkOutDate){
        Reservation newReservation = new Reservation(customer,room,checkInDate,checkOutDate);
        return newReservation;
    }

    public static Collection<IRoom> findRooms(Date checkInDate,Date checkOutDate){
        Set<IRoom> availableRooms = new HashSet<>();
        Set<IRoom> reservedRooms = getReservedRooms(checkInDate,checkOutDate);
        for(IRoom room : rooms.values()){
            if(!reservedRooms.contains(room)){
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public static Set<IRoom> getReservedRooms (Date checkInDate,Date checkOutDate){
        Set<IRoom> reservedRooms = new HashSet<>();
        for (Reservation reservation : reservations){
            IRoom room = reservation.getRoom();
            if (checkInDate.compareTo(reservation.getCheckInDate()) * reservation.getCheckInDate().compareTo(checkOutDate) >= 0){
                reservedRooms.add(room);
            }
        }
        return reservedRooms;
    }

    public static void addReservations(Reservation reservation){
        reservations.add(reservation);
    }
    public static Collection<Reservation> getCustomersReservation(Customer customer){
        Collection<Reservation> customersReservations = new ArrayList<>();
        for (Reservation reservation : reservations){
            if (customer == reservation.getCustomer()){
                customersReservations.add(reservation);
            }
        }
        return customersReservations;
    }
    public static void printAllReservation(){
        if (reservations.isEmpty()){
            System.out.println("There is no reservation yet");
        }else {
            for(Reservation reservation : reservations){
                System.out.println(reservation);
            }
        }
    }
    public static Collection<IRoom> getAllRooms(){
        return rooms.values();
    }

}
