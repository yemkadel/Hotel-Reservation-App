package model;

public class Room implements IRoom {
    private final String roomNumber;
    private Double price;
    RoomType roomType;
    boolean isFree;

    public Room(String roomNumber,Double price,RoomType roomType){
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    public String getRoomNumber(){
        return roomNumber;
    }

    public Double getRoomPrice(){
        return price;
    }
    public RoomType getRoomType(){
        return roomType;
    }
    public boolean isFree(){
        return false;
    }

    public String toString(){
        return "Room Number: "+ roomNumber +
                "\nRoom Type: "+ roomType +
                "\nPrice Per Night: "+ price;
    }
}
