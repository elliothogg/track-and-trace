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
        System.out.println("\n*********** FILTERS *********" + "\n\n" +
        "1. Filter Records by Establishment" + "\n" +
        "2. Filter Records by Date" + "\n" +
        "3. Filter Records by Name & Email" + "\n" +
        "4. Return to Main Menu\n");
    }
    
    public void runMainMenu()
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
                    printResultOfAddingEvent(c.addEvent(event), c.addEstablishment(event.getEstablishment()));
                    
                    break;

                case 2:
                    System.out.println("\n*********** ADD ESTABLISHMENT *********\n");
                    
                    printResultOfAddingEstablishment(c.addEstablishment(createEstablishment()));
                    break;
                case 3:
                    runFilterMenu(c);
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
                    System.out.println("\n*********** RECORDS BY ESTABLISHMENT *********\n");
                    System.out.print("Enter Establishment Name: ");
                    String establishmentName = sc.nextLine();
                    System.out.println(controllerIn.filterEventByEstablishment(establishmentName));
                    System.out.println("\n");
                
                break;

                case 2:
                    System.out.println("\n*********** RECORDS BY DATE *********\n");
                    System.out.print("\tEnter Event Date: ");
                    String eventDate = getValidDate();
                    System.out.println("\n");
                    System.out.println(controllerIn.filterEventByDate(eventDate));
                    System.out.println("\n");
                    break;
                case 3:
                    System.out.println("\n*********** RECORDS BY USER NAME & EMAIL *********\n");
                    System.out.print("\tEnter Users Name: ");
                    String userName = sc.nextLine();
                    System.out.print("\tEnter Users Email: ");
                    String userEmail = getValidEmail();
                    System.out.println("\n");
                    System.out.println(controllerIn.filterEventByUser(userName, userEmail));
                    System.out.println("\n");
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

        if (mobileNumber.matches("[0-9]{11}"))
        {
            return mobileNumber;
        }
        else 
        {
            System.out.print("\tInvalid input! Must be a UK Mobile Number (11 numbers long). Phone Number: ");
            return getValidPhoneNumber();
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

    public void printResultOfAddingEvent(Boolean... result)
    {
    if (result[0] && result[1])
    {
        System.out.println("\nSucess! Event & Establishment added to DB.\n");
    }
    else if (result[0] && !result[1])
    {
        System.out.println("\nSucess! Event added to DB! (Establishment already exists)\n");
    } 
    else System.out.println("\nError! Event already exists!");
    }


    public void run()
    {
        
        runMainMenu();
    
        
    }

    public static void main(String[] args)  {

        IO io1 = new IO();

        io1.run();
    }
}