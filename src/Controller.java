import java.util.ArrayList;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
public class Controller {

    private ArrayList<Establishment> establishments = new ArrayList<>();
    private ArrayList<Event> events = new ArrayList<>();



    

    Controller()
    {

    }

    Controller(String establishmentCSVFileURI) throws IOException
    {
        BufferedReader csvReader = new BufferedReader(new FileReader(establishmentCSVFileURI));
        String eachLine = "";

        //this ommits the first line of the csv file
        csvReader.readLine();

        while ((eachLine = csvReader.readLine()) != null)
        {
        String[] array = eachLine.split(",");
        establishments.add(new Establishment(array[0], array[1], array[2], Integer.parseInt(array[3])));
        }

        csvReader.close();
    }

    

    public boolean addEstablishment(Establishment establishmentIn) 
    {
        if (establishments.contains(establishmentIn)) 
        {
            return false;
        }
        else return establishments.add(establishmentIn);
    }

    public boolean addEvent(Event eventIn) 
    {
        if (events.contains(eventIn)) 
        {
            return false;
        }
        else return events.add(eventIn);
    }

    public ArrayList<Event> filterEventByEstablishment(String establishmentName)
    {
        ArrayList<Event> filteredEvents = new ArrayList<>();
        for (int i = 0; i < events.size(); i ++)
        {
            if (events.get(i).getEstablishment().getName().equalsIgnoreCase(establishmentName))
            {
                filteredEvents.add(events.get(i));
            }
        }
        return filteredEvents;
    }

    public ArrayList<Event> filterEventByDate(String date)
    {   
        if (!isValidDate(date))
        {
            throw new IllegalArgumentException("Date must be in format \"dd/MM/yyyy\" and must not be in the future!");
        }
        ArrayList<Event> filteredEvents = new ArrayList<>();
        for (int i = 0; i < events.size(); i ++)
        {
            if (events.get(i).getEventDateAsString().equalsIgnoreCase(date))
            {
                filteredEvents.add(events.get(i));
            }
        }
        return filteredEvents;
    }

    public ArrayList<Event> filterEventByUser(String name, String email)
    {
        if (!isValidName(name))
        {
            throw new IllegalArgumentException("Name must only contain \"A-z\" & \"-\" ");
        }
        else if (!isValidEmail(email))
        {
            throw new IllegalArgumentException("Email must be valid email");    
        }
        ArrayList<Event> filteredEvents = new ArrayList<>();
        for (int i = 0; i < events.size(); i ++)
        {
            if (events.get(i).getUser().getName().equalsIgnoreCase(name) && 
                events.get(i).getUser().getEmail().equalsIgnoreCase(email))
            {
                filteredEvents.add(events.get(i));
            }
        }
        return filteredEvents;
    }

    public boolean isValidName(String name)
    {
        return name.matches("^[ A-Za-z-]+$");
    }

    public boolean isValidEmail(String email)
    {
        return email.matches(".*@.*\\..*");
    }

    public boolean isValidDate(String date)
    {   
        try
        {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate realDate = LocalDate.parse(date, formatter);
        LocalDate todaysDate = LocalDate.now();
        if (realDate.isAfter(todaysDate))
        {
            return false;
        }
        else return true;
        }
        catch (DateTimeParseException e)
        {
        return false;
        }
    }



    public static void main(String[] args) throws IOException
        {

        Controller c = new Controller();
        User me = new User("Elliot Hogg", LocalDate.of(1992, 8, 8), "elliothogg@live.com", "07548377122");
        User me1 = new User("Daniel Hogg", LocalDate.of(1990, 8, 8), "elliothogg@live.com", "07548377122");
        Establishment e1 = new Establishment("Wembley Arena", "1 Wembley Road", "WM1 4AS", 90000);
        Establishment e2 = new Establishment("wembley arena", "1 Wembley Road", "WM1 4AS", 90000);
        Establishment e3 = new Establishment("Millenium Stadium", "1 Wembley Road", "WM1 4AS", 90000);
        
        Event e = new Event(me,LocalDateTime.of(2020, 11, 01, 12, 12, 12), 2, e1);
        Event ev1 = new Event(me1,LocalDateTime.now(), 2, e2);
        Event ev2 = new Event(me,LocalDateTime.now(), 2, e3);

        c.addEvent(e);
        c.addEvent(ev1);
        c.addEvent(ev2);
        c.addEstablishment(e1);
        c.addEstablishment(e2);
        c.addEstablishment(e3);

        //System.out.println(c.filterEventByUser("daniel hogg","elliothogg@live.com"));
        
        //System.out.println(c.filterEventByDate("01/11/2020"));
        
        //System.out.println(c.establishments.get(0).getEstablishmentInfo());
        
        Controller c1 = new Controller("establishments.csv");

        System.out.println(c1.establishments);

        
    }
}
