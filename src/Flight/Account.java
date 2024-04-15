/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Flight;

/**
 *
 * @author spark
 */
public class Account {
    public final String username;
    public final String firstName;
    public final String lastName;
    public String phone;
    public String email;
    final AccountPermission permissions;

    /***
     *  Constructs a new account used to access the booking system.
     * 
     * @param username - unique username for account
     * @param firstName - users first legal name
     * @param lastName - users last legal name
     * @param phone - contact phone, mobile or landline
     * @param email - contact email address
     * @param permissions - access permissions, user or admin
     */
    public Account(String username, 
			String firstName, String lastName,
			String phone, String email,
			AccountPermission permissions) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.permissions = permissions;
    }

    /***
     * @return a formatted string showing account type and username
     */
	@Override
	public String toString() {
		return "(" + getAccountTypeName() + ") " + getUsername();
	}

    /***
     * @return formatted full name and contact details of the account
     */
	public String getAccountDetails() {
		return getFullName() + "\n" + getContact();
	}

    /***
     * @return username of account
     */
	public String getUsername() {
		return this.username;
	}

    /***
     * @return permission type of account as string
     */
	public String getAccountTypeName() {
		return this.permissions.name();
	}

    /***
     * @return permission type of account as enum
     */
	public AccountPermission getAccountType() {
		return this.permissions;
	}

	public void updateEmail(String newEmail) {
		this.email = newEmail;
	}

	public void updatePhone(String newPhone) {
		this.phone = newPhone;
	}

    /***
     * @return formatted String of first and last name
     */
	public String getFullName() { return getFirstName() + " " + getLastName(); }
	public String getFirstName() { return this.firstName; }
	public String getLastName() { return this.lastName; }


    /***
     * @return formatted contact details of account
     */
	public String getContact() { return getEmail() + "\n" + getPhone(); }
	public String getPhone() { return this.phone; }
	public String getEmail() { return this.email; }

    /***
     * @param acc to compare with
     * @return Boolean of if usernames strings are equal
     */
	public boolean equals(Account acc) {
		return username.equals(acc.username);
	}
}