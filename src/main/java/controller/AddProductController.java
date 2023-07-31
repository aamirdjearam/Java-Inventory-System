package controller;

import javafx.collections.FXCollections;
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
import model.*;
import model.Inventory;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class adds products and their associated parts.
 *
 **/
public class AddProductController implements Initializable
{

    @FXML
    private TableView<Part> AddProductAllPartsTableView;
    @FXML
    private TableColumn<Part, Integer> AddProductAllPartID;
    @FXML
    private TableColumn<Part, String> AddProductAllPartName;
    @FXML
    private TableColumn<Part, Integer> AddProductAllPartInventoryLevel;
    @FXML
    private TableColumn<Part, Integer> AddProductAllPartPricePerUnit;
    @FXML
    private TableColumn<Part, Integer> AddProductAssociatedPartID;
    @FXML
    private TableColumn<Part, String> AddProductAssociatedPartName;
    @FXML
    private TableColumn<Part, Integer> AddProductAssociatedPartStock;
    @FXML
    private TableColumn<Part, Integer> AddProductAssociatedPartPrice;
    @FXML
    private TableView<Part> AddProductAssociatedTableView;


    @FXML
    private Button AddProductAddPartButton;

    @FXML
    private Label AddProductID;

    @FXML
    private TextField AddProductIDTextField;

    @FXML
    private Label AddProductInv;

    @FXML
    private TextField AddProductInvTextField;

    @FXML
    private Label AddProductLabel;

    @FXML
    private AnchorPane AddProductMax;

    @FXML
    private TextField AddProductMaxTextField;

    @FXML
    private Label AddProductMin;

    @FXML
    private TextField AddProductMinTextField;

    @FXML
    private Label AddProductName;

    @FXML
    private TextField AddProductNameTextField;

    @FXML
    private Label AddProductPrice;

    @FXML
    private TextField AddProductPriceTextField;

    @FXML
    private Button AddProductRemovePartButton;

    @FXML
    private Button AddProductSaveButton;

    @FXML
    private TextField AddProductSearch;

    @FXML
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();


    Stage stage;
    Parent scene;


    /**
     * The method cancels the Add Product screen and returns to the Main Form.
     *
     * @param actionEvent This method is executed on button click
     * @throws IOException IOException from changing FXML pages (load)
     */
    @FXML
    public void AddProductCancelButtonClicked(ActionEvent actionEvent) throws IOException
    {
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method initializes the controller including setting the All Parts Table and the blank Associated Parts table.
     *
     * It also disabled the ID Text Field and sets text in it.
     *
     * @param url n/a
     * @param resourceBundle n/a
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        AddProductAllPartsTableView.setItems(Inventory.getAllParts());
        AddProductAllPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        AddProductAllPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AddProductAllPartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>(("stock")));
        AddProductAllPartPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        AddProductAssociatedPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        AddProductAssociatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AddProductAssociatedPartStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AddProductAssociatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        AddProductIDTextField.setText("Auto Gen Disabled");
        AddProductIDTextField.setDisable(true);
    }

    /**
     * The method adds parts to the associated parts list of the product.
     *
     * If nothing is selected it will return an error message.
     *
     * @param actionEvent This method executes on button click.
     */
    @FXML
    public void AddPartToProduct(ActionEvent actionEvent) {

        Part partToAdd = null;
        partToAdd = AddProductAllPartsTableView.getSelectionModel().getSelectedItem();

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
            associatedParts.add(partToAdd);
            AddProductAssociatedTableView.setItems(associatedParts);
        }
    }


    /**
     * This method removes associated parts from the products associated parts list.
     *
     * It will give a confirmation screen prior to the part being deleted.
     *
     * @param actionEvent This method executes on button click.
     */
    public void AddProductRemovePartButtonClicked(ActionEvent actionEvent) {

        Part partToAdd = null;

        //Setting a null value to start

        partToAdd = AddProductAllPartsTableView.getSelectionModel().getSelectedItem();

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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Delete");
            alert.setTitle("Parts");
            alert.setContentText("Are you sure you would like to delete this part?");
            Optional<ButtonType> confirmation = alert.showAndWait();
            if (confirmation.get() == ButtonType.OK)
            {
                associatedParts.remove(partToAdd);
                AddProductAssociatedTableView.setItems(associatedParts);
            }

        }

    }

    /**
     * The product will be saved along with its information and associated parts.
     * @param actionEvent This method executes on button click.
     *
     **/
    public void AddProductSaveButtonClicked(ActionEvent actionEvent) {

        try
        {
            int id = 1;
            String name = AddProductNameTextField.getText();
            int stock = Integer.parseInt(AddProductInvTextField.getText());
            double price = Double.parseDouble(AddProductPriceTextField.getText());
            int max = Integer.parseInt(AddProductMaxTextField.getText());
            int min = Integer.parseInt(AddProductMinTextField.getText());

            //Error handling for stock/min/max

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
                //make new product with dummy info and use setter methods to input correct info

                Product newProduct = new Product (0, "", 0, 0, 0, 0);

                newProduct.setId(Inventory.currentProductId());
                newProduct.setName(name);
                newProduct.setStock(stock);
                newProduct.setPrice(price);
                newProduct.setMin(min);
                newProduct.setMax(max);

                for (Part savePart : associatedParts)
                {
                    newProduct.addAssociatedPart(savePart);
                }

                Inventory.getAllProducts().add(newProduct);

                stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();

            }

        }
        catch (NumberFormatException | IOException e)
        {
            System.out.println("This is an error");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setTitle("Add Product");
            alert.setContentText("Please Ensure that all Text Fields are filled in with the correct types");
            alert.showAndWait();

        }
    }

    /**
     * This method searches the for the Part in the All Parts TableView
     * @param actionEvent This method executes on button click
     */
    public void AddProductSearch(ActionEvent actionEvent) {

        String search = AddProductSearch.getText();

        if (search.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setTitle("Parts");
            alert.setContentText("No Part was Searched. Returning all parts list.");
            alert.showAndWait();
            AddProductAllPartsTableView.setItems(Inventory.getAllParts());
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
                AddProductAllPartsTableView.setItems(namedParts);
            }
        }

    }
}
