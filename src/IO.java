import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.*;
import java.util.ArrayList;
import java.io.IOException;

public class IO {

    private final Controller c;
    
    private final Controller c1;


    IO()
    {
        this.c = new Controller();
        this.c1 = null;
    }

    IO(String establishmentCSVFileURI) throws IOException
    {
        this.c = new Controller();
        this.c1 = new Controller("src/establishments.csv"); //neaten it up - 
        addCSVToDB();
    }

    public void addCSVToDB()
    {
        for (int i = 0; i < c1.getEstablishments().size(); i++)
        {
            c.addEstablishment(c1.getEstablishments().get(i));
        }
    }

    Scanner sc = new Scanner(System.in);

    public void printMainMenuOptions()
    {
        System.out.println("\n" + "*********** MENU *********" + "\n\n" +
        "1. Record an Event" + "\n" +
        "2. Add Establishment" + "\n" +
        "3. Filter Events" + "\n" +
        "4. Print Events" + "\n" +
        "5. Print Establishments" + "\n" +
        "6. Exit the Program\n");
    }


    public void printFilterMenuOptions()
    {
        System.out.println("\n*********** FILTER EVENTS *********" + "\n\n" +
        "1. Filter Events by Establishment" + "\n" +
        "2. Filter Events by Date" + "\n" +
        "3. Filter Events by Name & Email" + "\n" +
        "4. Return to Main Menu\n");
    }
    

    public void runMainMenu()
    {
        boolean programRunning= true;
        int menuChoice;
        
        printMainMenuOptions();
        
        do {
            System.out.print("Please select a menu option (1-6) (to see the options again press 7): ");
            menuChoice = getValidInt();

            sc.nextLine();
            
            switch(menuChoice)
            {                 
                case 1:
                    System.out.println("\n*********** ADD EVENT *********\n");
                    Event event = createEvent();
                    printResultOfAddingEvent(c.addEvent(event), c.addEstablishment(event.getEstablishment()));
                    
                    break;

                case 2:
                    System.out.println("\n*********** ADD ESTABLISHMENT *********\n");
                    
                    printResultOfAddingEstablishment(c.addEstablishment(createEstablishment()));
                    break;

                case 3:
                    if (isEventsArrayEmpty())
                    {
                        System.out.println("\n*********** Error - Cannot Apply Filters as there are no registered Events ***********\n");
                    }
                    else runFilterMenu(c);
                    break;
                         
                case 4:
                    System.out.println("\n*********** EVENTS *********\n");
                    if (isEventsArrayEmpty())
                    {
                        System.out.println("There are no registered Events.\n");
                    }
                    else
                    {
                        eventPrinter(c.getEvents());
                    }
                    break;
                
                case 5:
                    System.out.println("\n*********** ESTABLISHMENTS *********\n");
                    if (isEstablishmentsArrayEmpty())
                    {
                        System.out.println("There are no registered Establishments\n");
                    }
                    else
                    {
                        for (int i = 0; i < c.getEstablishments().size(); i++)
                        {
                            System.out.println(c.getEstablishments().get(i).getEstablishmentInfo());
                            System.out.println();
                            System.out.println();
                        }
                        System.out.println();
                    }
                    break;
         
                
                case 6:
                    System.out.println("\n************ Thanks For Using ************");
                    programRunning = false;
                    break;
                
                case 7:
                    printMainMenuOptions();
                    break;

                default:
                    System.err.print("Please choose a valid option! ");
                    break;
                    }
                } while (programRunning);        
    }


    public void eventPrinter(ArrayList<Event> events)
    {
        for (int i = 0; i < events.size(); i++)
        {
            System.out.println("Event ID: 1638925269 | Recorded User");
            System.out.println(events.get(i).getEventInfo());
            System.out.println(); 
            System.out.println(); 
        }
    }
    

    public void runFilterMenu(Controller controllerIn)
    {
        boolean filterMenuRunning= true;
        int menuChoice;
        
        printFilterMenuOptions();
        
        do {
            System.out.print("Please select a menu option (1-4) (to see the options again press 5): ");
            menuChoice = getValidInt();

            sc.nextLine();
            
            switch(menuChoice)   
            {
                case 1:
                    System.out.println("\n*********** EVENTS BY ESTABLISHMENT *********\n");
                    System.out.print("Enter Establishment Name: ");
                    String establishmentName = sc.nextLine();
                    System.out.println();
                    if (!controllerIn.filterEventsByEstablishment(establishmentName).isEmpty())
                    {
                        eventPrinter(controllerIn.filterEventsByEstablishment(establishmentName));
                    }
                    else System.out.println("No Events found on at the specified Establishment.\n");
                    break;

                case 2:
                    System.out.println("\n*********** EVENTS BY DATE *********\n");
                    System.out.print("Enter Event Date: ");
                    String eventDate = getValidDate();
                    System.out.println();
                    if (!controllerIn.filterEventsByDate(eventDate).isEmpty())
                    {
                        eventPrinter(controllerIn.filterEventsByDate(eventDate));
                    }
                    else System.out.println("No Events found on the specified day.\n");
                    break;

                case 3:
                    System.out.println("\n*********** EVENTS BY USER NAME & EMAIL *********\n");
                    System.out.print("Enter Users Name: ");
                    String userName = sc.nextLine();
                    System.out.print("Enter Users Email: ");
                    String userEmail = getValidEmail();
                    System.out.println();
                    if (!controllerIn.filterEventsByUser(userName, userEmail).isEmpty())
                    {
                        eventPrinter(controllerIn.filterEventsByUser(userName, userEmail));
                    }
                    else System.out.println("No Events found under that name and email.\n");
                    break;
                        
                case 4:
                    printMainMenuOptions();
                    filterMenuRunning = false;
                    break;

                case 5:
                    printFilterMenuOptions();
                    break;
                    
                default:
                    System.err.print("Please choose a valid option! ");
                    break;
            }
        } while (filterMenuRunning);     
    }


    //method for validating integers as Scanner input
    public int getValidInt()
    {
        //while loop that continually prompts for input until an integer is received
        while (!sc.hasNextInt())
            {
                System.err.print("Invalid input! Please enter an integer: ");
                sc.next();
            }

        //returns the valid input
        return sc.nextInt();
    }


    public String getValidDate()
    {   
        String date = sc.nextLine();

        try
        {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate realDate = LocalDate.parse(date, formatter);
        LocalDate todaysDate = LocalDate.now();
        
        if (realDate.isAfter(todaysDate))
        {
            System.out.print("\tDate cannot be in future! (Format dd/MM/yyyy): ");
            return getValidDate();
        }
        else return date;
    }   
        catch (DateTimeParseException e)
        {
        System.out.print("\tInvalid input/format! (Format dd/MM/yyyy): ");
        return getValidDate();
        }
    }


    public String getValidEmail()
    {
        String email = sc.nextLine();

        if (email.matches(".*@.*\\..*"))
        {
            return email;
        }
        else 
        {
            System.out.print("\tInvalid input! Email: ");
            return getValidEmail();
        }
    }


    public String getValidPhoneNumber()
    {
        String mobileNumber = sc.nextLine();

        if (mobileNumber.matches("^(07)[0-9]{9}"))
        {
            return mobileNumber;
        }
        else 
        {
            System.out.print("\tInvalid input! Must be a UK Mobile Number (11 numbers long & start with \"07\"). Phone Number: ");
            return getValidPhoneNumber();
        }
    }

    public boolean isEstablishmentsArrayEmpty()
    {
        if (c.getEstablishments().size() < 1)
        {
            return true;
        }
        else return false;
    }

    
    public boolean isEventsArrayEmpty()
    {
        if (c.getEvents().size() < 1)
        {
            return true;
        }
        else return false;
    }


    public Establishment createEstablishment()
    {
        System.out.println("Establishment Information: \n");
        System.out.print("\tName: ");
        String establishmentName = sc.nextLine();
        System.out.print("\tFirst Line Address: ");
        String establishmentFirstLineAddress = sc.nextLine();
        System.out.print("\tPostCode: ");
        String establishmentPostCode = sc.nextLine();
        System.out.print("\tMax Occupancy: ");
        int maxOccupancy = getValidInt();
        sc.nextLine(); // Read the leftover new line

        return new Establishment(establishmentName, establishmentFirstLineAddress, 
        establishmentPostCode, maxOccupancy);
    }


    public User createUser()
    {
        System.out.println("User Information:\n");
        System.out.print("\tName: ");
        String userName = sc.nextLine();
        System.out.print("\tDOB (Format dd/MM/yyyy): ");
        String userDOB = getValidDate();
        System.out.print("\tEmail: ");
        String userEmail = getValidEmail();
        System.out.print("\tPhone Number: ");
        String userPhoneNumber = getValidPhoneNumber();

        return new User(userName, userDOB, userEmail, userPhoneNumber);
    }


    public Event createEvent()
    {
        User user = createUser();
        
        System.out.print("\nParty Size: ");
        int partySize = getValidInt();
        System.out.println();
        sc.nextLine(); // Read the leftover new line

        Establishment establishment = createEstablishment();

        return new Event(user, LocalDateTime.now(), partySize, establishment);
    }


    public void printResultOfAddingEstablishment(Boolean result)
    {
    if (result)
    {
        System.out.println("\nSucess! Establishment added to DB.\n");
    }
    else System.out.println("\nError! Establishment with that Name & PostCode already exists!\n" +
                            "Select option 5 to view all stored Establishments.\n");
    }


    public void printResultOfAddingEvent(Boolean... result)
    {
    if (result[0] && result[1])
    {
        System.out.println("\nSucess! Event & Establishment added to DB.\n");
    }
    else if (result[0] && !result[1])
    {
        System.out.println("\nSucess! Event added to DB! (Establishment already exists).\n");
    } 
    else System.out.println("\nError! Event already exists!");
    }


    public void runProgram()
    {    
        runMainMenu();
    }

    public void debugMethod()
    {
        User me = new User("Elliot Hogg", "08/08/1992", "elliothogg@live.com", "07548377122");
        User me1 = new User("Daniel", "08/08/1992", "Daniel@live.com", "07548377122");
        Establishment e1 = new Establishment("elli", "sdfsf", "FA1 d3KE", 5);
        Establishment e2 = new Establishment("eyll", "Street", "FA1 3KE", 5);
        Establishment e3 = new Establishment("ellf", "1 King", "FA1 ds3KE", 5);
        Event e = new Event(me,LocalDateTime.of(2020, 11, 02, 12, 12, 12), 2, e1);
        Event d = new Event(me1,LocalDateTime.now(), 4, e1);
        Event f = new Event(me,LocalDateTime.now(), 4, e2);
        Event g = new Event(me1,LocalDateTime.now(), 4, e3);
        
        //declare some objects that would fail - 12 number phones etc 

        //object decs that have erronious params - String where int is needed 

        //boundary - can establishmnet have 0 for max occupancy or -1.  number in event party

        //exreme - null in place of String

        c.addEvent(e);
        c.addEvent(d);
        c.addEvent(f);
        c.addEvent(g);
        c.addEstablishment(e1);
        c.addEstablishment(e2);
        c.addEstablishment(e3);

        runProgram();
    }

    public static void main(String[] args) throws IOException
    {

        new IO().debugMethod();

        //new IO().runProgram();

        //new IO("src/establishments.csv").runProgram(); // create an IO object and add a csv file (which is automatically added to ArrayList)

        

        
        
        
    }
}