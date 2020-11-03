import java.time.LocalDateTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.*;

public class IO {

    Scanner sc = new Scanner(System.in);

    

    public void printMainMenuOptions()
    {
        System.out.println("\n" + "*********** MENU *********" + "\n\n" +
        "1. Record an Event" + "\n" +
        "2. Add Establishment" + "\n" +
        "3. Filters" + "\n" +
        "4. Print Events" + "\n" +
        "5. Print Establishments" + "\n" +
        "6. Exit the Program\n");
    }

    public void printFilterMenuOptions()
    {
        System.out.println("*********** MENU *********" + "\n" +
        "1. Filter Records by Establishment" + "\n" +
        "2. Filter Records by Date" + "\n" +
        "3. Filter Records by Name & Email" + "\n" +
        "4. Return to Main Menu\n");
    }
    
    public void runMenu()
    {
        Controller c = new Controller();
       

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
                    c.addEvent(event);
                    c.addEstablishment(event.getEstablishment());
                    break;

                case 2:
                    System.out.println("\n*********** ADD ESTABLISHMENT *********\n");
                    
                    printResultOfAddingEstablishment(c.addEstablishment(createEstablishment()));
                    break;
                case 3:
                    
                    break;
                         
                
                case 4:
                    System.out.println("\n*********** EVENTS *********\n");
                    System.out.println(c.getEvents());
                    System.out.println("\n"); 
                    break;
                
                case 5:
                    System.out.println("\n*********** ESTABLISHMENTS *********\n");
                    System.out.println(c.getEstablishments());
                    System.out.println("\n");
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
            return getValidDate();
        }
        else return date;
        }
        catch (DateTimeParseException e)
        {
        return getValidDate();
        }
    }

    public Establishment createEstablishment()
    {
        System.out.println("\nEstablishment Information: \n");
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
        System.out.println("\tUser Information:\n");
        System.out.print("\tName: ");
        String userName = sc.nextLine();
        System.out.print("\tDOB: ");
        String userDOB = sc.nextLine();
        System.out.print("\tEmail: ");
        String userEmail = sc.nextLine();
        System.out.print("\tPhone Number: ");
        String userPhoneNumber = sc.nextLine();

        return new User(userName, userDOB, userEmail, userPhoneNumber);
    }

    public Event createEvent()
    {
        System.out.print("Event Information:\n\n");
        
        User user = createUser();
        
        System.out.print("\nParty Size: ");
        int partySize = getValidInt();
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

    public void printResultOfAddingEvent(Boolean result)
    {
    if (result)
    {
        System.out.println("\nSucess! Event added to DB.\n");
    }
    else System.out.println("\nError! Event already exists!\n" +
                            "Select option 4 to view all stored Establishments.\n");
    }


    public void run()
    {
        
        runMenu();
    
        
    }

    public static void main(String[] args)  {

        IO io1 = new IO();

        io1.run();
    }
}