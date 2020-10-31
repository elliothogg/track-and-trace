public class Establishment {

    private String name, firstLineAddress, postCode;
    private int maxOccupancy;

    Establishment(String name, String firstLineAddress, String postCode, int maxOccupancy)
    {
        this.name = name;
        this.firstLineAddress = firstLineAddress;
        this.postCode = postCode;
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

    @Override
    public String toString()
    {
        return "Establishment [name=" + name + ", firstLineAddress=" + firstLineAddress + ", postCode=" + 
                                postCode + ", maximumOccupancy=" + maxOccupancy + "]";
    }

    public String getEstablishmentInfo()
    {
        return String.format("Name: %s\nAddress: %s %s", name, firstLineAddress, postCode);
    }


    public static void main(String[] args) {

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
