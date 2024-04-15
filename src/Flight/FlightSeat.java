/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Flight;

/**
 *
 * @author spark
 */
public class FlightSeat implements Comparable<FlightSeat> {
	// add access stuff
    int floor;
    char roomNum;
    double price;

    public FlightSeat(int floor, char roomNum) {
        this.floor = floor;
        this.roomNum = roomNum;
        this.price = calculatePrice();
    }

    /**
     * Used to calculate the price of the room.
     *
     * For assignment 1, all rooms will share the same common price of 100.
     * @return Returns the price of the room (100).
     */
    private double calculatePrice() { return 100d; }

    /**
     * Gets the room number ("{floor}{roomNum}") as a string
     * @return Returns the room number string
     */
    public String getRoomNumber() { return floor + "" + roomNum; }

    // Getter functions for the floor, and roomNum
    public int getFloor() { return floor; }
    public char getRoomNum() { return roomNum; }

    /**
     * Compares two rooms. Rooms are compared based on their floor numbers.
     * @param o the object to be compared.
     * @return Returns -1 if the floor number is less than o's floor number, 0 if the floor number is equal to o's floor number, 1 if the floor number is greater than o's floor number
     */
    @Override
    public int compareTo(FlightSeat o) {
        if (floor < o.floor) return -1;
        else if (floor > o.floor) return 1;
        else return 0;
    }

    /**
     * Returns whether the room is the same as o
     * @param o the object to be compared.
     * @return Returns true if the floor and floor of o, AND the roomNum and roomNum of o are equal. Otherwise, false is returned.
     */
    public boolean equals(FlightSeat o) { return floor == o.floor && roomNum == o.roomNum; }
}
