package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static controller.MainFormController.getModifiedProductID;


/**
 * This class modifies the selected product with the appropriate information and associated parts.
 */
public class ModifyProductController implements Initializable {

    Stage stage;
    Parent scene;
   private Product product;
   private Product productToModify;




    @FXML
    private TableView<Product> ProductTable;
    @FXML
    private TableView<Part> ModifyProductAllPartsTableView;
    @FXML
    private TableColumn<Part, Integer> ModifyProductAllPartID;
    @FXML
    private TableColumn<Part, String> ModifyProductAllPartName;
    @FXML
    private TableColumn<Part, Integer> ModifyProductAllPartInventoryLevel;
    @FXML
    private TableColumn<Part, Integer> ModifyProductAllPartPricePerUnit;
    @FXML
    private Button ModifyProductCancelButton;
    @FXML
    private TableView<Part> ModifyProductAssociatedPartsTable;
    @FXML
    private TableColumn<Part, Integer> ModifyProductAssociatedPartsID;
    @FXML
    private TableColumn<Part, String> ModifyProductAssociatedPartsName;
    @FXML
    private TableColumn<Part, Integer> ModifyProductAssociatedPartsInventoryLevel;
    @FXML
    private TableColumn<Part, Integer> ModifyProductAssociatedPartsPricePerUnit;

    @FXML
    private TextField ModifyProductSearch;

    @FXML
    private Button AddProductAddAssociatedPartButton;

    @FXML
    private Button AddProductAddPartButton;

    @FXML
    private Label AddProductInv;
    

    @FXML
    private AnchorPane AddProductMax;

    @FXML
    private Label AddProductMin;

    @FXML
    private Label AddProductName;

    @FXML
    private Label AddProductPrice;

    @FXML
    private Button AddProductRemovePartButton;

    @FXML
    private Button AddProductSaveButton;

    @FXML
    private TextField AddProductSearch;

    @FXML
    private Label ModifyProductID;

    @FXML
    private TextField ModifyProductIDTextField;

    @FXML
    private TextField ModifyProductInvTextField;

    @FXML
    private Label ModifyProductLabel;

    @FXML
    private TextField ModifyProductMaxTextField;

    @FXML
    private TextField ModifyProductMinTextField;

    @FXML
    private TextField ModifyProductNameTextField;

    @FXML
    private TextField ModifyProductPriceTextField;

    /**
     * This integer is the id of the selected product
     */
    private int modifyProductid = getModifiedProductID();


    /**
     * This method pulls the information from the selected product into the Modify Product fields.
     *
     * @param modifyProduct The product that has been selected to be modified
     */
    public void getProduct(Product modifyProduct)
    {
        productToModify = modifyProduct;


        ModifyProductIDTextField.setText(String.valueOf(productToModify.getId()));
        ModifyProductNameTextField.setText(productToModify.getName());
        ModifyProductInvTextField.setText(String.valueOf(productToModify.getStock()));
        ModifyProductPriceTextField.setText(String.valueOf(productToModify.getPrice()));
        ModifyProductMinTextField.setText(String.valueOf(productToModify.getMin()));
        ModifyProductMaxTextField.setText(String.valueOf(productToModify.getMax()));

        product.getAssociatedParts().addAll(productToModify.getAssociatedParts());


        setAssociatedPartsTableView(product.getAssociatedParts());

    }

    /**
     * The method updates/refreshes the Associated Parts Table
     *
     * @param associatedPartsList The list of parts that are associated with the product
     */
    public void setAssociatedPartsTableView(ObservableList<Part> associatedPartsList)
    {
        ModifyProductAssociatedPartsID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ModifyProductAssociatedPartsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ModifyProductAssociatedPartsInventoryLevel.setCellValueFactory(new PropertyValueFactory<>(("stock")));
        ModifyProductAssociatedPartsPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
        ModifyProductAssociatedPartsTable.setItems(associatedPartsList);
    }


    /**
     * This method adds a part to the associated parts list for the product.
     * @param actionEvent The method executes on button click
     */
    @FXML
    public void AddPartToProduct(ActionEvent actionEvent) {

        Part partToAdd = ModifyProductAllPartsTableView.getSelectionModel().getSelectedItem();

        if (partToAdd == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setTitle("Add Products");
            alert.setContentText("No Product was selected");
            alert.showAndWait();
        }
        else
        {
            product.addAssociatedPart(partToAdd);
            setAssociatedPartsTableView(product.getAssociatedParts());
        }

    }

    /**
     * The method removes the selected part from the associated parts list for that product
     * @param actionEvent The method executes on button click
     */
    @FXML
    public void RemoveButtonClicked(ActionEvent actionEvent) {

        Part partToRemove = ModifyProductAssociatedPartsTable.getSelectionModel().getSelectedItem();

        if (partToRemove == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setTitle("Add Products");
            alert.setContentText("No Product was selected");
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
                product.deleteAssociatedPart(partToRemove);
                setAssociatedPartsTableView(product.getAssociatedParts());
            }
        }

    }

    /**
     * The method cancels out of the Modify Product screen and returns the user back to the Main Form
     *
     *
     * @param actionEvent This method executes on Button Click
     * @throws IOException IOException from changing FXML pages (load)
     */
    @FXML
    public void ModifyProductCancelButtonClicked(ActionEvent actionEvent) throws IOException
    {
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method updates the product with the new information and associated parts
     *
     * @param actionEvent This method occurs on button click
     * @throws IOException IOException from changing FXML pages (load)
     */
    @FXML
    public void AddProductSaveButtonClicked(ActionEvent actionEvent) throws IOException
    {
        try
        {
            int max = Integer.parseInt(ModifyProductMaxTextField.getText());
            int min = Integer.parseInt(ModifyProductMinTextField.getText());
            int stock = Integer.parseInt(ModifyProductInvTextField.getText());

            if (min < 0)
            {
                System.out.println("This is an error");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setTitle("Add Product");
                alert.setContentText("Min must be greater or equal to 0");
                alert.showAndWait();
            }
            else if (max < min)
            {
                System.out.println("This is an error");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setTitle("Add Product");
                alert.setContentText("Max must be greater top or equal than min");
                alert.showAndWait();
            }
            else if (min > stock || stock > max)
            {
                System.out.println("This is an error");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setTitle("Add Product");
                alert.setContentText("The stock must be equal to or less than the max and greater than or equal to the min");
                alert.showAndWait();
            }
            else
            {
                productToModify.setName(ModifyProductNameTextField.getText());
                productToModify.setStock(Integer.parseInt(ModifyProductInvTextField.getText()));
                productToModify.setPrice(Double.parseDouble(ModifyProductPriceTextField.getText()));
                productToModify.setMax(Integer.parseInt(ModifyProductMaxTextField.getText()));
                productToModify.setMin(Integer.parseInt(ModifyProductMinTextField.getText()));

                productToModify.getAssociatedParts().clear();
                productToModify.getAssociatedParts().addAll(product.getAssociatedParts());

                Inventory.updateProduct(modifyProductid, productToModify);

                stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("This is an error");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setTitle("Modify Product");
            alert.setContentText("Please Ensure that all Text Fields are filled in with the correct types");
            alert.showAndWait();
        }

    }

    /**
     * This method initializes the controller including setting the All Parts Table
     * It also disables the PartID text field and enters text.
     *
     * @param url n/a
     * @param resourceBundle n/a
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ModifyProductIDTextField.setDisable(true);
        product = new Product();

        ModifyProductAllPartsTableView.setItems(Inventory.getAllParts());
        ModifyProductAllPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ModifyProductAllPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ModifyProductAllPartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>(("stock")));
        ModifyProductAllPartPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


    /**
     * This method searches the all parts list for the looked up part
     * @param actionEvent This method executes on button click
     */
    public void ModifyProductSearchClicked(ActionEvent actionEvent) {

            String search = ModifyProductSearch.getText();
            if (search.isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setTitle("Parts");
                alert.setContentText("No Part was Searched. Returning all parts list.");
                alert.showAndWait();
                ModifyProductAllPartsTableView.setItems(Inventory.getAllParts());
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
                    ModifyProductAllPartsTableView.setItems(namedParts);
                }
            }
        }
}
