/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Flight;

/**
 *
 * @author spark
 */
public class LoginInfo implements Comparable<LoginInfo> {
    private final String username;
    private final String password;

    /***
     * Constructor to store the account credentials, used for
     * verifying login
     * 
     * @param username - unique username of user
     * @param password - used to secure account
     */
    public LoginInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /***
     * @return username of credentials
     */
	public String getUsername() {
		return username;
	}

	/**
	 * @return password of credentials, not secure.
	 */
	public String getPassword() {
		return username;
	}

    /***
     * 
     * @param o - credentials to compare to
     * @return compareTo values of string username && password
     */
    @Override
    public int compareTo(LoginInfo o) {
        return username.equals(o.username) && password.equals(o.password) ? 0 : -1;
    }

    /***
     * 
     * @param o - credentials to check for string equality
     * @return Boolean of string equality of password && username
     */
    public boolean equals(LoginInfo o) {
        return username.equalsIgnoreCase(o.username) && password.equals(o.password);
    }
}
