package model;

/**
 * This class is one of the 2 part types. It has string CompanyName stored instead of Machine ID
 */
public class Outsourced extends Part {
    private String companyName;

    /**
     *
     * This method is for creating an Outsourced Part with the appropriate parameters
     *
     * @param id The ID of the Part
     * @param name The Name of the Part
     * @param price The Price of the Part
     * @param stock The Stock of the Part
     * @param min The Min inventory of the Part
     * @param max The Min inventory of the Part
     * @param companyName The Company Name of the Part
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName)
    {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;

    }

    /**
     * Set the Parts Company Name
     * @param companyName The Companies Name that sells the Part
     */
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    /**
     * The return method to get the Company's Name. Used in Modifying the Part
     * @return returns the Company Name of the Part
     */
    public String getCompanyName()
    {
        return this.companyName;
    }
}
