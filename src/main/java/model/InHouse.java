package model;

/**
 * This class is one of the 2 part types. It has int Machine ID stored instead of Company Name.
 */
public class InHouse extends Part {
    private int machineId;

    /**
     * This method is for creating an InHouse Part with the appropriate parameters
     * @param id The ID of the Part
     * @param name The Name of the Part
     * @param price The Price of the Part
     * @param stock The Stock of the Part
     * @param min The Min inventory of the Part
     * @param max The Min inventory of the Part
     * @param machineId The Machine ID of the Part
     */
   public InHouse(int id, String name, double price, int stock, int min, int max, int machineId)
    {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;

    }

    /**
     * Set the Parts Machine ID
     * @param machineId The Machine ID of the Part
     */
    public void setMachineId(int machineId)
    {
        this.machineId = machineId;
    }

    /**
     * The return method to get the Company's Name. Used in Modifying the Part
     * @return The machineID
     */
    public int getMachineId()
    {
        return machineId;
    }
}
