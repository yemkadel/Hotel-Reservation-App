package model;

import java.util.Objects;

public class Room implements IRoom {
    private final String roomNumber;
    private Double price;
    RoomType roomType;

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

    public boolean equals(Object o){
        if (this == o)
            return true;
        if (!(o instanceof Room))
            return false;
        Room other = (Room) o;
        boolean roomNumberEquals = (this.roomNumber == null && other.roomNumber == null) || (this.roomNumber != null && this.roomNumber.equals(other.roomNumber));
        boolean priceEquals = (this.price == null && other.price == null) || (this.price != null && this.price == other.price);
        boolean roomTypeEquals = (this.roomType == null && other.roomType == null) || (this.roomType != null && this.roomType == other.roomType);
        return (roomNumberEquals && priceEquals && roomTypeEquals);
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (roomNumber != null){
            result = 31 * result + roomNumber.hashCode();
        }
        if (price != null){
            result = 31 * result + price.hashCode();
        }
        if (roomType != null){
            result = 31 * result + roomType.hashCode();
        }
        return result;
    }

    public String toString(){
        return "Room Number: "+ roomNumber +
                "\nRoom Type: "+ roomType +
                "\nPrice Per Night: "+ price;
    }
}
