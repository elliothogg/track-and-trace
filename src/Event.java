import java.time.*;
import java.time.format.DateTimeFormatter;

public class Event {

    private User user;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private int partyNumber;
    private Establishment establishment;

    Event(User user, LocalDateTime date, int partyNumber, Establishment establishment)
    {
        this.user = user;
        this.eventDate = date.toLocalDate();
        this.eventTime = date.toLocalTime();
        this.partyNumber = partyNumber;
        this.establishment = establishment;
    }

    Event(User user, int partyNumber, Establishment establishment)
    {
        this.user = user;
        this.partyNumber = partyNumber;
        this.establishment = establishment;
    }

    public User getUser()
    {
        return this.user;
    }

    public LocalDate getEventDate()
    {
        return this.eventDate;
    }

    public String getEventDateAsString()
    {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.eventDate.format(myFormatObj);
    }

    public LocalTime getEventTime()
    {
        return this.eventTime;
    }

    public String getEventTimeAsString()
    {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm");
        return this.eventTime.format(myFormatObj);
    }

    public int getPartyNumber()
    {
        return this.partyNumber;
    }

    public Establishment getEstablishment()
    {
        return this.establishment;
    }

    @Override
    public String toString()
    {
        return "Event [user=" + user + ", eventDate=" + eventDate + ", eventTime=" + eventTime + 
                ", partyNumber=" + partyNumber + ", establishment=" + establishment + "]";
    }

/*     public String getEventInfo()
    {
        return user.getUserInfo() + "\nDate " + getEventDateAsString() + "\nTime " + getEventTimeAsString() +
                "\nParty Size " + partyNumber + "\nEstablishment \n\t" + establishment.getEstablishmentInfo();
    } */
    
    
    public String getEventInfo()
    {
        return String.format("\tName %s \n\tDate of Birth %s \n\tEmail %s \n\tContact Number %s \n\tAge %d \nDate %s \nTime %s \nParty Size %d \nEstablishment \n\tName: %s \n\tAddress: %s",
                            user.getName(), user.getDateOfBirthAsString(), user.getEmail(), user.getPhoneNumber(), 
                            user.getAge(), getEventDateAsString(), getEventTimeAsString(), partyNumber, establishment.getName(), establishment.getAddress());
    }
    

    public static void main(String[] args) {

        User me = new User("Elliot Hogg", LocalDate.of(1992, 8, 8), "elliothogg@live.com", "07548377122");
        Establishment e1 = new Establishment("Wembley Arena", "1 Wembley Road", "WM1 4AS", 90000);

        Event ev1 = new Event(me, LocalDateTime.now(), 2, e1);

        System.out.println(ev1.getEventInfo());
        System.out.println();
        System.out.println(ev1.getPartyNumber());
        System.out.println(ev1.getEstablishment());
        System.out.println(ev1.getEventTime());
        System.out.println(ev1.getEventDate());
        System.out.println(ev1.getUser());

    }
}
