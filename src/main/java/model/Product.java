package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Product class defines methods for adding/modifying/removing products. It has both a full and empty constructor as well as the private
  observable associatedParts list.
 */
public class Product
{

   private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * The full constructor for making a product
     *
     * @param id The id for the Product
     * @param name The name for the Product
     * @param price The price of the Product
     * @param stock The number of stock for the Product
     * @param min The minimum stock for the Product
     * @param max The maximum stock for the Product
     */
    public Product(int id, String name, double price, int stock, int min, int max)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.associatedParts.addAll(associatedParts);
    }

    /**
     * The empty constructor for the Product
     */
    public Product()
    {

    }

    /**
     * Sets the id for the Product
     * @param id The ID for the Product
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Sets the name for the Product
     * @param name The name of the Product
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Sets the Price for the product
     * @param price The Price of the product
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * Sets the stock for the product
     * @param stock The stock of the product
     */
    public void setStock(int stock)
    {
        this.stock = stock;
    }

    /**
     * Sets the minimum stock for the product
     * @param min The minimum stock for the product
     */
    public void setMin(int min)
    {
        this.min = min;
    }

    /**
     * Sets the maximum stock for the product
     * @param max The maximum stock for the product
     */
    public void setMax(int max)
    {
        this.max = max;
    }

    /**
     * Gets the ID of the product
     * @return The ID of the product
     */
    public int getId()
    {
        return id;
    }

    /**
     * Gets the name of the product
     * @return The name of the product
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the price of the product
     * @return The price of the product
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Gets the stock of the Product
     * @return The stock of the Product
     */
    public int getStock()
    {
        return stock;
    }

    /**
     * Gets the minimum stock of the product
     * @return The minimum stock of the product
     */
    public int getMin()
    {
        return min;
    }

    /**
     * Gets the maximum stock of the product
     * @return The maximum stock of the product
     */
    public int getMax()
    {
        return max;
    }

    /**
     * Returns the associatedParts list
     * @return The associated Parts list
     */
    public ObservableList<Part> getAssociatedParts()
    {
        return associatedParts;
    }

    /**
     * Adds an item to the associated Parts list
     * @param part The part to add to the associated Parts list
     */
    public void addAssociatedPart(Part part)
    {
        associatedParts.add(part);
    }

    /**
     * Deletes the part from the Associated Parts list
     * @param selectedAssociatedPart The part to delete
     * @return The method returns the removal of the selectedAssociatedPart from the associated parts list
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart)
    {
        return associatedParts.remove(selectedAssociatedPart);
    }


}
