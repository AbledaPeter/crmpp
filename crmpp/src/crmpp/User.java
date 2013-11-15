package crmpp;

public class User {
    
	public int id;
	public String firstName;
	public String lastName;
    public String gender;
    public int age;
    public String email;
    public String address;
    public String sport;

    public User(int id, String firstName, String lastName, String gender, int age, String email, String address, String sport) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.address = address;
        this.sport = sport;
    }
}
