package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.sql.SQLOutput;
import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static final CustomerService customerService = CustomerService.getInstance();
    private static final ReservationService reservationService = ReservationService.getInstance();
    public static Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }
    public static void createACustomer(String email,String firstName, String lastName){
        customerService.addCustomer(email,firstName,lastName);

    }
    public static IRoom getARoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    }
    public static Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        Customer customer = getCustomer(customerEmail);
        return reservationService.reserveARoom(customer,room,checkInDate,checkOutDate);
    }
    public static Collection<Reservation> getCustomersReservations(String customerEmail){
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.getCustomersReservation(customer);
    }
    public static Collection<IRoom> findARoom(Date checkInDate,Date checkOutDate){
        return reservationService.findRooms(checkInDate,checkOutDate);
    }

}
