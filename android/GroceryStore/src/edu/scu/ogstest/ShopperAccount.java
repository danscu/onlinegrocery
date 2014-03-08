package edu.scu.ogstest;

public class ShopperAccount extends APIData {
	public String userName;
	public String password;
	public String firstName;
	public String lastName;
	public String streetAddress;
	public String city;
	public String state;
	public String zipcode;
	public String phone;
	public String email;
	
	@Override
	public String toString() {
		return "ShopperAccount [id = " + id + ", userName=" + userName + ", password="
				+ password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", streetAddress=" + streetAddress + ", city="
				+ city + ", state=" + state + ", zipcode=" + zipcode
				+ ", phone=" + phone + ", email=" + email + "]";
	}
}
