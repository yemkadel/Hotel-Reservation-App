package model;

public class FreeRoom extends Room {
    public FreeRoom(String roomNumber,Double price,RoomType roomType){
        super(roomNumber,0.0,roomType);
    }

    public boolean isFree(){
        return true;
    }

    public String toString(){
        return "Room Number: "+ super.getRoomNumber() +
                "\nRoom Type: "+ super.getRoomType() +
                "\nPrice Per Night: "+ super.getRoomPrice();
    }
}
