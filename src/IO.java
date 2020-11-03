import java.time.LocalDateTime;
import java.util.Scanner;

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
            menuChoice = returnValidInt();

            sc.nextLine();
            
           
            switch(menuChoice)
            {
                
                case 1:
                    System.out.println("\n*********** ADD EVENT *********\n");
                    System.out.println("User Information:");
                    System.out.print("\tName:");
                    String userName = sc.nextLine();
                    System.out.print("\tDOB:");
                    String userDOB = sc.nextLine();
                    System.out.print("\tEmail:");
                    String userEmail = sc.nextLine();
                    System.out.print("\tPhone Number:");
                    String userPhoneNumber = sc.nextLine();
                    
                    System.out.print("Party Size:");
                    int partySize = sc.nextInt();
                    
                    System.out.println("Establishment Information:");
                    System.out.print("\tName:");
                    String establishmentName = sc.nextLine();
                    System.out.println("\tFirst Line Address:");
                    String establishmentFirstLineAddress = sc.nextLine();
                    System.out.println("\tPostCode: ");
                    String establishmentPostCode = sc.nextLine();
                    System.out.println("\tMax Occupancy: ");
                    int maxOccupancy = sc.nextInt();
                    
                    
                    c.addEvent(new Event(new User(userName, userDOB, userEmail,userPhoneNumber), 
                                LocalDateTime.now(), partySize, 
                                new Establishment(establishmentName, establishmentFirstLineAddress, 
                                establishmentPostCode, maxOccupancy)));
                    break;

                case 2:
                    System.out.println("\n*********** ADD ESTABLISHMENT *********\n");
                    
                    c.addEstablishment(createEstablishment());
                    // System.out.print("Enter Name: ");
                    // String nameOut = sc.nextLine();

                    // System.out.print("Enter First Line of Address: ");
                    // String firstLineAddressOut = sc.nextLine();

                    // System.out.print("PostCode: ");
                    // String postCodeOut = sc.nextLine();
                    
                    // System.out.print("Max Occupancy: ");
                    // int maxOccupancyOut = returnValidInt();

                    // c.addEstablishment(new Establishment(nameOut, firstLineAddressOut, postCodeOut, maxOccupancyOut));

                    break;
                case 3:
                    
                    break;
                         
                
                case 4:
                    System.out.println("\n*********** Events *********\n");
                    System.out.println(c.getEvents());
                    System.out.println("\n"); 
                    break;
                
                case 5:
                    System.out.println("\n*********** Establishments *********\n");
                    System.out.println(c.getEstablishments());
                    System.out.println("\n");
                    break;
         
                
                case 6:
                    System.out.println("************ Thanks For Using ************");
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
    public int returnValidInt()
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
        int maxOccupancy = sc.nextInt();

        return new Establishment(establishmentName, establishmentFirstLineAddress, 
        establishmentPostCode, maxOccupancy);
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