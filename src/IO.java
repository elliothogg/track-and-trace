/*
 * This class runs the program. There is a debug method that populates the system with data to facilitate menu testing. 
 * The debug method also features some limited testing.
 * 
 * CSV establishment data can be parsed and stored via a constructor.
 * 
 * This project was written as a University project.
 * 
 * @author	Elliot Hogg
 * @version 1.14  (09 Nov 2020)
 * 
 */

import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.*;
import java.util.ArrayList;
import java.io.IOException;

public class IO {

    private final Controller c;
    
    Scanner sc = new Scanner(System.in);

    IO()
    {
        this.c = new Controller();
    }

    //This constructor allows CSV Establishment data to pre-populate the program "DB"
    IO(String establishmentCSVFileURI) throws IOException
    {
        this.c = new Controller(establishmentCSVFileURI);
    }


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
            menuChoice = returnValidInt();

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
                
                //Passes control to filterMenu
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
                        establishmentPrinter(c.getEstablishments());
                    }
                    break;
         
                case 6:
                    System.out.println("\n************ Thanks For Using ************\n");
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


    public void runFilterMenu(Controller controllerIn)
    {
        boolean filterMenuRunning= true;
        int menuChoice;
        
        printFilterMenuOptions();

        do {
            System.out.print("Please select a menu option (1-4) (to see the options again press 5): ");
            menuChoice = returnValidInt();

            sc.nextLine(); // Read the leftover new line 
            
            switch(menuChoice)   
            {
                case 1:
                    System.out.println("\n*********** EVENTS BY ESTABLISHMENT *********\n");
                    String establishmentName = returnNonEmptyString("Enter Establishment Name");
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
                    String eventDate = returnValidDate();
                    System.out.println();
                    if (!controllerIn.filterEventsByDate(eventDate).isEmpty())
                    {
                        eventPrinter(controllerIn.filterEventsByDate(eventDate));
                    }
                    else System.out.println("No Events found on the specified day.\n");
                    break;

                case 3:
                    System.out.println("\n*********** EVENTS BY USER NAME & EMAIL *********\n");
                    String userName = returnNonEmptyString("Enter Users Name");
                    System.out.print("\tEnter Users Email: ");
                    String userEmail = returnValidEmail();
                    System.out.println();
                    if (!controllerIn.filterEventsByUser(userName, userEmail).isEmpty())
                    {
                        eventPrinter(controllerIn.filterEventsByUser(userName, userEmail));
                    }
                    else System.out.println("No Events found under that name and email.\n");
                    break;
                
                //Passes control back to mainMenu
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

    //Loops through all items in passed ArrayList and neatly prints them
    public void eventPrinter(ArrayList<Event> events)
    {
        for (int i = 0; i < events.size(); i++)
        {
            System.out.println(events.get(i).getEventInfo());
            System.out.println(); 
            System.out.println(); 
        }
    }

    //Loops through all items in passed ArrayList and neatly prints them
    public void establishmentPrinter(ArrayList<Establishment> establishments)
    {
        for (int i = 0; i < establishments.size(); i++)
        {
            System.out.println(establishments.get(i).getEstablishmentInfo());
            System.out.println();
            System.out.println();
        }
    }
    
    
    public int returnPositiveInt()
    {
        int input = returnValidInt();
        
        if (input < 1)
        {
            System.out.print("Invalid input! Please enter a positive integer: ");
            return returnPositiveInt();
        }
        return input;
    }


    public int returnValidInt()
    {
        while (!sc.hasNextInt())
            {
                System.out.print("Invalid input! Please enter an integer: ");
                sc.next();
            }
        return sc.nextInt();
    }

    //Prints "field" to prompt for value, then ensures Scanner input String != null
    public String returnNonEmptyString(String field)
    {
        System.out.print("\t" + field + ": ");
        String input = sc.nextLine();
        if (input.length() < 1)
        {
            System.out.print("\tInvalid input! ");
            return returnNonEmptyString(field);
        }
        return input;
    }


    public String returnValidDate()
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
                return returnValidDate();
            }
            else return date;
        }   
        catch (DateTimeParseException e)
        {
            System.out.print("\tInvalid input/format! (Format dd/MM/yyyy): ");
            return returnValidDate();
        }
    }


    public String returnValidEmail()
    {
        String email = sc.nextLine();

        //Regex - String must contain 1 "@" followed by at least 1 "."
        if (email.matches(".*@.*\\..*"))
        {
            return email;
        }
        else 
        {
            System.out.print("\tInvalid input! Email: ");
            return returnValidEmail();
        }
    }


    public String returnValidPhoneNumber()
    {
        String mobileNumber = sc.nextLine();

        //Regex - String must start with "07" and contain exactly 11 characters(0-9 only)
        if (mobileNumber.matches("^(07)[0-9]{9}"))
        {
            return mobileNumber;
        }
        else 
        {
            System.out.print("\tInvalid input! Must be a UK Mobile Number (11 numbers long & start with \"07\"). Phone Number: ");
            return returnValidPhoneNumber();
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

    //Prompts and gathers data via scanner obj and returns Establishment obj
    public Establishment createEstablishment()
    {
        System.out.println("Establishment Information: \n");
        String establishmentName = returnNonEmptyString("Name");
        String establishmentFirstLineAddress = returnNonEmptyString("First Line Address");
        String establishmentPostCode = returnNonEmptyString("PostCode");
        System.out.print("\tMax Occupancy: ");
        int maxOccupancy = returnPositiveInt();
        sc.nextLine(); // Read the leftover new line

        return new Establishment(establishmentName, establishmentFirstLineAddress, 
        establishmentPostCode, maxOccupancy);
    }

    //Prompts and gathers data via scanner obj and returns User obj
    public User createUser()
    {
        System.out.println("User Information:\n");
        String userName = returnNonEmptyString("Name");
        System.out.print("\tDOB (Format dd/MM/yyyy): ");
        String userDOB = returnValidDate();
        System.out.print("\tEmail: ");
        String userEmail = returnValidEmail();
        System.out.print("\tPhone Number: ");
        String userPhoneNumber = returnValidPhoneNumber();

        return new User(userName, userDOB, userEmail, userPhoneNumber);
    }

    //Prompts and gathers data via scanner obj and returns Event obj
    public Event createEvent()
    {
        User user = createUser();
        
        System.out.print("\nParty Size: ");
        int partySize = returnPositiveInt();
        System.out.println();
        sc.nextLine(); // Read the leftover new line

        Establishment establishment = createEstablishment();

        return new Event(user, LocalDateTime.now(), partySize, establishment);
    }

    //Prints the result of attemping to add a new Establishment obj to the establishment ArrayList
    public void printResultOfAddingEstablishment(Boolean result)
    {
        if (result)
        {
            System.out.println("\nSucess! Establishment added to DB.\n");
        }
        else System.out.println("\nError! Establishment with that Name & PostCode already exists!\n" +
                            "Select option 5 to view all stored Establishments.\n");
    }

    //Prints the result of attemping to add a new Event obj to the event ArrayList
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

    //Some simple manual unit tests checking valid, boundary, erroneous, & extreme values
    public void debugMethod()
    {
        // ## Object instantiation testing


        // ### User

        //Two valid User object instantiations
        User me = new User("Elliot Hogg", "08/08/1992", "elliothogg@live.com", "07548377122");
        User me1 = new User("Daniel", "08/08/1992", "Daniel@live.com", "07548377122");
        
        //User invalidDOB = new User("Daniel", 08/08/1992, "Daniel@live.com", "07548377122");
        

        // ### Establishment

        //Three valid Establishment object instantiations
        Establishment e1 = new Establishment("elli", "sdfsf", "FA1 d3KE", 5);
        Establishment e2 = new Establishment("eyll", "Street", "FA1 3KE", 5);
        Establishment e3 = new Establishment("ellf", "1 King", "FA1 ds3KE", 5);

        //Establishment invalidOccupancy = new Establishment("ellf", "1 King", "FA1 ds3KE", "invalid");
        //Establishment extremeValue1 = new Establishment(null, "1 King", "FA1 ds3KE", "invalid");


        // ### Event

        //Four valid Event Instantiations
        Event e = new Event(me,LocalDateTime.of(2020, 11, 02, 12, 12, 12), 2, e1);
        Event d = new Event(me1,LocalDateTime.now(), 4, e1);
        Event f = new Event(me,LocalDateTime.now(), 4, e2);
        Event g = new Event(me1,LocalDateTime.now(), 4, e3);
        
        //Event invalidPartyNumber1 = new Event(me1,LocalDateTime.now(), 0, e3);
        //Event invalidPartyNumber2 = new Event(me1,LocalDateTime.now(), -10000 e3);
        //Event invalidPartyNumber3 = new Event(me1,LocalDateTime.now(), "hello" e3);
        

        // ## IO Individual Method Testing

        //returnValidEmail();
        //Invalid Strings: "elliothogglive.com", "elliothogg@livecom"
        //Valid String: elliothogg@live.com


        //returnValidPhoneNumber();
        //Invalid Strings: "90948574859", "089586758493", "08938473643", "059"
        //Valid String: "07869586758"


        //returnNonEmptyString();
        //Invalid Strings: "", null
        //valid Strings: "Ell", "BK", "GBK", "Mo"


        //returnValidInt();
        //Invalid input: "hello", 23.5, 23f
        //Valid input: -9999999, 1, 99999999999


        //returnPositiveInt();
        //Invalid input: -1, 0, -99999999999
        //Valid input: 1, 999999999999


        //returnValidDate();
        //Invalid input: "08/08/2030", "32/04/1992", "08.08.1992" , "hello", 99999999999
        //valid input: "08/08/1992", "08/11/2020"
        

        //pre-populate event & establishment ArrayLists for manual menu testing
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

        new IO().debugMethod(); //Pre-populates the event and establishment "DB" to faciliate manual menu testing, then runs the program

        //new IO().runProgram();

        //new IO("establishments.csv").runProgram(); //Runs program & passes CSV Establishment data to establishment ArrayList
                                                   //Some IDE code runners want "src/establishments.csv"
    }
}