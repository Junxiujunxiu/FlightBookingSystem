/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Flight;

/**
 *
 * @author spark
 */
public class Flight {
	private String hotelName;
	public String street;
	public String suburb;
	public String city;
	public String postcode; // will handle 0604
	public String country;
	public String phone;
	public String email;

    /***
     * Constructor for hotel, storing details about the hotel
     * 
     * @param hotelName - name of hotel
	 * @param street
	 * @param suburb
	 * @param city
	 * @param postcode
	 * @param country
     * @param phone - contact number
     * @param email - contact email
     */
	public Flight(String hotelName,
			String street, String suburb, String city, String postcode, String country, 
			String phone, String email) {
		this.hotelName = hotelName;
		this.street = street;
		this.suburb = suburb;
		this.city = city;
		this.postcode = postcode;
		this.country = country;
		this.phone = phone;
		this.email = email;
	}

    /***
     * @return formatted string of hotel name, address and contact
     */
	@Override
	public String toString() {
		return getHotelName() + "\n" + getAddress() + "\n" + getContact() + "\n";
	}

    /***
     * @return name of hotel
     */
	public String getHotelName() {
		return this.hotelName;
	}

    /***
     * @return toString location of hotel
     */
	public String getAddress() {
		StringBuilder out = new StringBuilder(this.street + ", \n");
		out.append(this.suburb+", \n");
		out.append(this.city+", ");
		out.append(this.postcode+", \n");
		out.append(this.country);
		return out.toString();
	}

    /***
     * @return formatted string of hotel email and phone
     */
	public String getContact() {
		return this.email + "\n" + this.phone;
	}
}
