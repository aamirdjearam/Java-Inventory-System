package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is the Main Form Controller Class.
 * This displays the main page of the GUI and allows for deletion of parts/products
 * and links to creation/modification for parts/products
 *
 **/
public class MainFormController implements Initializable {

    @FXML
    private Button ExitButton;

    @FXML
    private Label InventoryManagementSystemTitle;

    @FXML
    private TableView<Part> PartTable;

    @FXML
    private Button PartsAddButton;

    @FXML
    private AnchorPane PartsBox;

    @FXML
    private Button PartsDeleteButton;

    @FXML
    private TableColumn<Part, Integer> PartsInventoryLevel;

    @FXML
    private Button PartsModifyButton;

    @FXML
    private TableColumn<Part, Integer> PartsPartID;

    @FXML
    private TableColumn<Part, String> PartsPartName;

    @FXML
    private TableColumn<Part, Double> PartsPricePerUnit;

    @FXML
    private TextField PartsSearch;

    @FXML
    private Label PartsTableLabel;

    @FXML
    private Button ProductAddButton;

    @FXML
    private Button ProductDeleteButton;

    @FXML
    private TableColumn<Product, Integer> ProductInventoryLevel;

    @FXML
    private Button ProductModifyButton;

    @FXML
    private TableColumn<Product, Double> ProductPricePerUnit;

    @FXML
    private TableColumn<Product, Integer> ProductProductID;

    @FXML
    private TableColumn<Product, String> ProductProductName;

    @FXML
    private TextField ProductSearch;

    @FXML
    private Label ErrorMessage;

    @FXML
    private TableView<Product> ProductTable;

    @FXML
    private AnchorPane ProductsBox;

    @FXML
    private Label ProductsLabel;

    private static Part modifiedPart;


    /**
     * This method returns the part to be modified
     * @return The part to be returned
     */
    public static Part getModifiedPart()
    {
        return modifiedPart;
    }

    private static int modifiedProductID;


    /**
     * This method returns the Modified Product ID
     * that will be used in the Modify Part Controller
     * @return The Modified Product ID
     */
    public static int getModifiedProductID()
    {
        return modifiedProductID;
    }

    Stage stage;
    Parent scene;


    /**
     * This method links to the add part section of the program
     * @param actionEvent This method executes on button click
     * @throws IOException IOException from changing FXML pages (load)
     */
    @FXML
    public void AddPartButtonClicked(ActionEvent actionEvent) throws IOException
    {
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method links to the modify part section of the program. It verifies that
     * the part is selected and preps it to be sent across to the modify part controller
     * @param actionEvent This method executes on button click
     * @throws IOException IOException from changing FXML pages (load)
     */
    @FXML
    public void ModifyPartButtonClicked(ActionEvent actionEvent) throws IOException
    {

        modifiedPart = null;
        modifiedPart = PartTable.getSelectionModel().getSelectedItem();

        if (modifiedPart == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setTitle("Parts");
            alert.setContentText("No Part was Selected");
            alert.showAndWait();
        }
        else
        {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/ModifyPart.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * This method links to the Add Product screen
     * @param actionEvent This method executes on Button click
     * @throws IOException IOException from changing FXML pages (load)
     */
    @FXML
    public void AddProductButtonClicked(ActionEvent actionEvent) throws IOException
    {
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method links to the modify product screen. It verifies that the product has been
     * selected and preps it to be used in the Modify Product controller
     * @param actionEvent This method executes on button click
     * @throws IOException IOException from changing FXML pages (load)
     */
    @FXML
    public void ModifyProductButtonClicked(ActionEvent actionEvent) throws IOException
    {
        Product product = ProductTable.getSelectionModel().getSelectedItem();
        modifiedProductID = Inventory.getAllProducts().indexOf(product);

        if (product == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setTitle("Products");
            alert.setContentText("No Product was Selected");
            alert.showAndWait();
        }
        else
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyProduct.fxml"));
            loader.load();
            ModifyProductController ProductController = loader.getController();
            ProductController.getProduct(product);

            stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * This method closes the application after displaying a pop-up confirmation message
     * @param actionEvent This method executes on button click
     */
    @FXML
    public void OnExitButtonClicked(ActionEvent actionEvent)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Exit Application");
        alert.setTitle("Exit");
        alert.setContentText("Are you sure you would like to exit the application");
        Optional<ButtonType> confirmation = alert.showAndWait();
        if (confirmation.get() == ButtonType.OK)
        {
            System.exit(0);
        }

    }

    /**
     * This method deletes the selected part after displaying a pop-up confirmation
     * @param actionEvent This method executes on button click
     */
    public void PartsDeleteButtonClicked(ActionEvent actionEvent) {

        Part deletePart = null;
        deletePart = PartTable.getSelectionModel().getSelectedItem();

        if (deletePart == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setTitle("Parts");
            alert.setContentText("No Part was Selected");
            alert.showAndWait();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Delete");
            alert.setTitle("Parts");
            alert.setContentText("Are you sure you would like to delete this part?");
            Optional<ButtonType> confirmation = alert.showAndWait();
            if (confirmation.get() == ButtonType.OK)
            {
                Inventory.deletePart(deletePart);
                PartTable.setItems(Inventory.getAllParts());
            }
        }
    }

    /**
     * This method deletes the selected part after displaying a pop-up confirmation
     * @param actionEvent This method executes on button click
     * @throws IOException IOException from changing FXML pages (load)
     */
    public void ProductDeleteButtonClicked(ActionEvent actionEvent) throws IOException {
        Product deleteProduct = null;
        deleteProduct = ProductTable.getSelectionModel().getSelectedItem();
        if (deleteProduct == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setTitle("Products");
            alert.setContentText("No Product was selected");
            alert.showAndWait();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Delete");
            alert.setTitle("Products");
            alert.setContentText("Are you sure you would like to delete this product");
            Optional<ButtonType> confirmation = alert.showAndWait();
            if (confirmation.get() == ButtonType.OK)
            {
                if (deleteProduct.getAssociatedParts().isEmpty())
                {
                    Inventory.deleteProduct(deleteProduct);
                    ProductTable.setItems(Inventory.getAllProducts());
                }
                else
                {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setHeaderText("ERROR");
                    alert1.setTitle("Products");
                    alert1.setContentText("This Product contains parts. Delete cannot be completed");
                    alert1.showAndWait();;

                    ErrorMessage.setText("This Product has parts");
                }

            }
        }

    }

     /**
      * This method searches for a part and pulls up the results or an error if no parts are found
      * @param actionEvent This method executes on button click
      **/
    public void PartSearch(ActionEvent actionEvent)
    {
        String search = PartsSearch.getText();
        if (search.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setTitle("Parts");
            alert.setContentText("No Part was Searched. Returning all parts list.");
            alert.showAndWait();
            PartTable.setItems(Inventory.getAllParts());
        }
        else
        {
            ObservableList namedParts = Inventory.lookupPart(search);
            try
            {
                int searchInt = Integer.parseInt(search);
                Part searchedIntPart = Inventory.lookupPart(searchInt);
                namedParts.add(searchedIntPart);
            }
            catch (NumberFormatException e)
            {
            }
            if (namedParts.isEmpty() || namedParts.contains(null))
            {
                System.out.println("This is an error");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setTitle("Parts");
                alert.setContentText("No Search results could be found");
                alert.showAndWait();
            }
            else
            {
                PartTable.setItems(namedParts);
            }
        }
    }


     /**
      * This method searches for a part and pulls up the results or an error if no parts are found
      * @param actionEvent This method executes on button click
      *
      **/
    public void ProductSearch(ActionEvent actionEvent)
    {
        String search = ProductSearch.getText();
        if (search.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setTitle("Products");
            alert.setContentText("No Product was Searched. Returning all products list.");
            alert.showAndWait();
            ProductTable.setItems(Inventory.getAllProducts());
        }
        else
        {
            ObservableList namedProducts = Inventory.lookupProduct(search);
            try
            {
                int searchInt = Integer.parseInt(search);
                Product searchedIntProduct = Inventory.lookupProduct(searchInt);
                namedProducts.add(searchedIntProduct);
            }
            catch (NumberFormatException e)
            {
            }
            if (namedProducts.isEmpty() || namedProducts.contains(null))
            {
                System.out.println("This is an error");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setTitle("Products");
                alert.setContentText("No Search results could be found");
                alert.showAndWait();
            }
            else
            {
                ProductTable.setItems(namedProducts);
            }
        }
    }

    /**
     * This method executes on initialization. It sets the parts and product tables
     * as well as any prompt text
     * @param url n/a
     * @param resourceBundle n/a
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //Set both tables

        PartTable.setItems(Inventory.getAllParts());
        PartsPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartsPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartsInventoryLevel.setCellValueFactory(new PropertyValueFactory<>(("stock")));
        PartsPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        ProductTable.setItems(Inventory.getAllProducts());
        ProductProductID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProductProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProductInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ProductPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Error message at the bottom of the window and set the prompt text for the search fields

        ErrorMessage.setText("");
        PartsSearch.setPromptText("Search by Part ID or Name");
        ProductSearch.setPromptText("Search by ProductID or Name");


    }
}
