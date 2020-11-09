/*
 * This class is used to store and retrieve information about a user (the lead party member of a group visiting an establishment).
 * The class features standard getter and setter methods. All data is encapsulated.
 * 
 * This project was written as a University project.
 * 
 * @author	Elliot Hogg
 * @version 1.14  (09 Nov 2020)
 * 
 */

import java.time.*;
import java.time.format.DateTimeFormatter;

public class User {

    private String name, email, phoneNumber, dateOfBirthString;
    private LocalDate dateOfBirth;
    private int age;

    //DOB is passed as a String but stored in LocalDate format
    User(String name, String dateOfBirth, String email, String phoneNumber)
    {
        this.name = name;
        this.dateOfBirth = StringToLocalDate(dateOfBirth); //converts String to LocalDate format
        this.dateOfBirthString = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = Period.between(this.dateOfBirth,LocalDate.now()).getYears();
    }


    public LocalDate StringToLocalDate(String DOB)
    {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(DOB, myFormatObj);
    }


    public String getName()
    {
        return this.name;
    }


    public LocalDate getDateOfBirth()
    {
        return this.dateOfBirth;
    }


    public String getDateOfBirthAsString()
    {
        return this.dateOfBirthString;
    }


    public String getEmail()
    {
        return this.email;
    }


    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }


    public int getAge()
    {
        return this.age;
    }


    @Override
    public String toString()
    {
        return "User [name=" + name + ", dateOfBirth=" + dateOfBirth + ", email=" + email + 
                ", phoneNumber=" + phoneNumber + ", age=" + age + "]";
    }


    public String getNameAndNumber()
    {
        return name + " | Contact Number " + phoneNumber;
    }

    //This method neatly presents instance variables
    public String getUserInfo()
    {
        return String.format("Name %s\nDate of Birth %s\nEmail %s\nContact Number %s\nAge %d", 
                            name, getDateOfBirthAsString(), email, phoneNumber, age);
    }

    
    public static void main(String[] args) {
       
    //some very simple manual unit testing (ignore)

        User me = new User("Elliot Hogg", "28/08/1993", "elliothogg@live.com", "07548377122");

        System.out.println(me.getName());
        System.out.println(me.getEmail());
        System.out.println(me.getPhoneNumber());
        System.out.println(me.getNameAndNumber());
        System.out.println(me.getPhoneNumber());
        System.out.println(me.getDateOfBirth());
        System.out.println(me.getAge());
        System.out.println();
        System.out.println();
        System.out.println(me.getUserInfo());
        System.out.println();
        System.out.println();
        System.out.println(me);     
    }
}