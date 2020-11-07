/*
 * This class is used to store and retrieve information about a an establishment (all the venues that have been visited by users in events).
 * The class features standard getter and setter methods. All data is encapsulated.
 * 
 * This project was written as a University project.
 * 
 * @author	Elliot Hogg
 * @version 1.11  (07 Nov 2020)
 * 
 */

public class Establishment {

    private String name, firstLineAddress, postCode;
    private int maxOccupancy;

    Establishment(String name, String firstLineAddress, String postCode, int maxOccupancy)
    {
        this.name = name;
        this.firstLineAddress = firstLineAddress;
        this.postCode = postCode.replaceAll("\\s+",""); //removes all white spaces to allow for simpler comparisons
        this.maxOccupancy = maxOccupancy;
    }

    Establishment(String name, String[] address, int maxOccupancy)
    {
        this.name = name;
        this.firstLineAddress = address[0];
        this.postCode = address[1];
        this.maxOccupancy = maxOccupancy;
    }


    public String getName()
    {
        return this.name;
    }


    public String getAddress()
    {
        return this.firstLineAddress + " " + this.postCode;
    }


    public int getMaximumOccupancy()
    {
        return this.maxOccupancy;
    }


    public String getPostCode()
    {
        return this.postCode;
    }


    @Override
    public String toString()
    {
        return "Establishment [name=" + name + ", firstLineAddress=" + firstLineAddress + ", postCode=" + 
                                postCode + ", maximumOccupancy=" + maxOccupancy + "]";
    }

    //This method neatly presents instance variables (apart from occupancy)
    public String getEstablishmentInfo()
    {
        return String.format("Name: %s\nAddress: %s %s", name, firstLineAddress, postCode);
    }


    public static void main(String[] args) {

        //some very simple manual unit testing (ignore)

        Establishment e1 = new Establishment("Wembley Arena", "1 Wembley Road", "WM1 4AS", 90000);
        
        System.out.println(e1.getName());
        System.out.println(e1.getAddress());
        System.out.println(e1.getMaximumOccupancy());
        System.out.println();
        System.out.println(e1.getEstablishmentInfo());
        System.out.println();
        System.out.println();
        System.out.println(e1);

    }
}
