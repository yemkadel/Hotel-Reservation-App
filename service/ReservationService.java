package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public final class ReservationService {
    private static Map<String,IRoom> rooms = new HashMap<>();
    private static Collection<Reservation> reservations = new ArrayList<>();
    private static ReservationService INSTANCE;

    private ReservationService(){
    }

    public static ReservationService getInstance(){
        if (INSTANCE == null){
            INSTANCE = new ReservationService();
        }
        return INSTANCE;
    }

    public void addRoom(IRoom room){
       rooms.put(room.getRoomNumber(),room);
    }
    public IRoom getARoom(String roomId){
        return rooms.get(roomId);
    }

    public Reservation reserveARoom(Customer customer,IRoom room, Date checkInDate,Date checkOutDate){
        Reservation newReservation = new Reservation(customer,room,checkInDate,checkOutDate);
        addReservations(newReservation);
        return newReservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate,Date checkOutDate){
        Collection<IRoom> availableRooms = new ArrayList<>();
        Collection<IRoom> reservedRooms = getReservedRooms(checkInDate,checkOutDate);
        for(IRoom room : rooms.values()){
            if(!reservedRooms.contains(room)){
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Collection<IRoom> getReservedRooms (Date checkInDate,Date checkOutDate){
        Collection<IRoom> reservedRooms = new ArrayList<>();
        for (Reservation reservation : reservations){
            IRoom room = reservation.getRoom();
            if ((checkInDate.compareTo(reservation.getCheckInDate()) * reservation.getCheckInDate().compareTo(checkOutDate) >= 0) || (checkInDate.compareTo(reservation.getCheckOutDate()) * reservation.getCheckOutDate().compareTo(checkOutDate) >= 0)) {
                reservedRooms.add(room);
            }
        }
        return reservedRooms;
    }

    public void addReservations(Reservation reservation){
        reservations.add(reservation);
    }
    public Collection<Reservation> getCustomersReservation(Customer customer){
        Collection<Reservation> customersReservations = new ArrayList<>();
        for (Reservation reservation : reservations){
            if (customer == reservation.getCustomer()){
                customersReservations.add(reservation);
            }
        }
        return customersReservations;
    }
    public void printAllReservation(){
        if (reservations.isEmpty()){
            System.out.println("There is no reservation yet");
        }else {
            for(Reservation reservation : reservations){
                System.out.println(reservation);
            }
        }
    }
    public Collection<IRoom> getAllRooms(){
        return rooms.values();
    }

}
