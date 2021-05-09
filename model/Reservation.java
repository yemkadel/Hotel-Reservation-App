package model;

import java.util.Date;

public class Reservation {
    private Customer customer;
    private IRoom room;
    private Date checkInDate,checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer(){
        return customer;
    }
    public Date getCheckInDate() {
        return checkInDate;
    }
    public Date getCheckOutDate() {
        return checkOutDate;
    }
    public String getRoomNumber(){
        return room.getRoomNumber();
    }

    public IRoom getRoom(){
        return room;
    }

    public String toString(){
        return "Reservation Details:"
                + "\nCustomer: "+ customer.toString()
                + "\nRoom Number: "+ room.getRoomNumber()
                + "\nCheck in date: "+ checkInDate
                + "\nCheck out date: "+checkOutDate
                + "\nPrice per night: N"+ room.getRoomPrice();
    }

}
