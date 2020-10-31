import java.time.*;
import java.time.format.DateTimeFormatter;

public class User {

    private String name, email, phoneNumber;
    private LocalDate dateOfBirth;
    private int age;

    User(String name, LocalDate dateOfBirth, String email, String phoneNumber)
    {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = Period.between(dateOfBirth,LocalDate.now()).getYears();
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
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.dateOfBirth.format(myFormatObj);
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

    public String getUserInfo()
    {
        return String.format("Name %s\nDate of Birth %s\nEmail %s\nContact Number %s\nAge %d", 
                            name, getDateOfBirthAsString(), email, phoneNumber, age);
    }

    public static void main(String[] args) {
       
        User me = new User("Elliot Hogg", LocalDate.of(1992, 8, 8), "elliothogg@live.com", "07548377122");


        
    
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
