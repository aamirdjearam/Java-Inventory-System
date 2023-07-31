package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class is the basis for the inventory system. It has the All Parts and All Products lists.
 * It also has methods relating to Part/Product actions.
 */
public class Inventory
{

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    private static int partID = 0;

    private static int productID = 0;

    /**
     * This method adds a new part to the All Parts List
     * @param newPart The part to be added to the All Parts List
     */
    public static void addPart(Part newPart)
    {
        allParts.add(newPart);
    }

    /**
     * This method creates a unique Part ID
     * @return Returns a new Part ID
     */
    public static int currentPartId()
    {
        partID = partID + 1;
        return partID;
    }

    /**
     * This method creates a unique Product ID
     * @return Returns a new Product ID
     */
    public static int currentProductId()
    {
        productID = productID + 1;
        return productID;
    }

    /**
     * This method get all parts in the All Part List
     * @return The list of All Parts
     */
    public static ObservableList<Part> getAllParts()
    {
        return allParts;
    }

    /**
     * This method looks up a part based on the part ID
     * @param partId The ID of the part to be searched
     * @return returns the searched part or null if no part was found
     */
    public static Part lookupPart (int partId)
    {
        for (Part searchedPart : allParts)
        {
            if (searchedPart.getId() == partId)
            {
                return searchedPart;
            }
        }
        return null;
    }

    /**
     * This method looks up a product based on the product ID
     * @param productId The ID of the Product to lookup
     * @return returns the searched product or null if no product was found
     */
    public static Product lookupProduct (int productId)
    {
        for (Product searchedProducts : allProducts)
        {
            if (searchedProducts.getId() == productId)
            {
                return searchedProducts;
            }
        }
        return null;
    }

    /**
     * Returns the All Products List
     * @return The All Products List
     */
    public static ObservableList<Product> getAllProducts()
    {
        return allProducts;
    }

    /**
     * The method for adding a new Product
     * @param newProduct the new Product to be added
     */
    public static void addProduct(Product newProduct)
    {
        allProducts.add(newProduct);
    }

    /**
     * This method deletes the product form the All Parts Table View
     * @param selectedProduct The product to delete
     * @return The removal of the product or false
     */
    public static boolean deleteProduct (Product selectedProduct)
    {
        if (allProducts.contains(selectedProduct))
        {
            return allProducts.remove(selectedProduct);
        }
        else
        {
            return false;
        }
    }

    /**
     * This method deletes the part form the All Part Table View
     * @param selectedPart The part to delete
     * @return The removal of the part or false
     */
    public static boolean deletePart(Part selectedPart)
    {
        if (allParts.contains(selectedPart))
        {
            return allParts.remove(selectedPart);
        }
        else
        {
            return false;
        }
    }

    /**
     * The method to update parts by part ID
     *
     * @param index The index of the part to update
     * @param selectedPart The part to update the old part with
     */
    public static void updatePart(int index, Part selectedPart)
    {
        allParts.set(index, selectedPart);
    }

    /**
     * The method to update product by product index
     * @param index The index of the product to update
     * @param newProduct The product to update the old product with
     */
    public static void updateProduct(int index, Product newProduct)
    {
        allProducts.set(index, newProduct);
    }

    /**
     * The method to search products in a list
     * @param productName The search term as a string
     * @return a list of products that match the search term
     */
    public static ObservableList lookupProduct(String productName) {

        ObservableList<Product> namedProducts = FXCollections.observableArrayList();

        for (Product Sp : allProducts)
        {
            if (Sp.getName().contains(productName))
            {
                namedProducts.add(Sp);
            }
        }
        return namedProducts;
    }

    /**
     * The method to search parts in a list
     * @param partName The search term as a string
     * @return a list of parts that match the search term
     */
    public static ObservableList<Part> lookupPart(String partName)
    {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();

        for (Part Sp : allParts)
        {
            if (Sp.getName().contains(partName))
            {
                namedParts.add(Sp);
            }
        }
        return namedParts;
    }
}
