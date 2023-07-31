package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import model.InHouse;
import model.Outsourced;
import model.Inventory;
import model.Part;

/**
 * The AddPartController Class has the logic and variables to add Parts into the AllPartsList and subsequently into product AssociatedPartsLists.
 */
public class AddPartController implements Initializable {

    /**
     * The label for Company Name or Machine ID
     */
    @FXML
    private Text NameorIDLabel;
    /**
     * The Button to cancel out of this screen back to the Main Screen
     */
    @FXML
    private Button AddPartCancelButton;

    /**
     * The label that shows where to input ID. No ID can be input however since the
     * textfield is disabled
     */
    @FXML
    private Text AddPartIDLabel;

    /**
     * The Textfield to add Part ID. This will be disabled in the initialize method, however.
     */
    @FXML
    private TextField AddPartIDTextField;

    /**
     * The Textfield to add Maximum Inventory for the part.
     */
    @FXML
    private TextField AddPartIMaxTextField;

    /**
     * The Textfield to add Maximum Inventory for the part.
     */
    @FXML
    private TextField AddPartMinTextField;

    /**
     * The RadioButton allows users to select if the part is InHouse.
     */
    @FXML
    private RadioButton AddPartInHouseButton;

    /**
     * The label that shows users where to input the stock of the part.
     */
    @FXML
    private Text AddPartInvLabel;

    @FXML
    private TextField AddPartInvTextField;

    /**
     * The label that shows users they are on the Add Part page.
     */
    @FXML
    private Label AddPartLabel;

    /**
     * The label that shows users where they can enter MachineID.
     */
    @FXML
    private Text AddPartMachineIDLabel;

    /**
     * The TextField where users can enter either the Machine ID or the Company Name.
     */
    @FXML
    private TextField AddPartMachineIDTextField;

    /**
     * The label that indicates users where to enter the max inventory for the part.
     */
    @FXML
    private Text AddPartMaxLabel;

    /**
     * The label that indicates users where to enter the min inventory for the part.
     */
    @FXML
    private Text AddPartMinLabel;

    /**
     * The label that indicates users where to enter the name for the part.
     */
    @FXML
    private Text AddPartNameLabel;

    /**
     * The TextField where users can enter the Name of the Part.
     */
    @FXML
    private TextField AddPartNameTextField;

    /**
     * The RadioButton allows users to select if the part is OutSourced.
     */
    @FXML
    private RadioButton AddPartOutsourcedButton;

    /**
     * The label that indicates users where to enter the price for the part.
     */
    @FXML
    private Text AddPartPriceLabel;

    /**
     * The TextField where users can enter the Price of the Part.
     */
    @FXML
    private TextField AddPartPriceTextField;

    /**
     * The Button to save the part and go back to the main screen.
     */
    @FXML
    private Button AddPartSaveButton;

    Stage stage;
    Parent scene;

    /** Cancels the Add Part screen and returns to the Main Form
     *
     * @param actionEvent The method executes on button click
     * @throws IOException IOException from changing FXML pages (load)
     */
    @FXML
    public void AddPartCancelButtonClicked(ActionEvent actionEvent) throws IOException
    {
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** Changes the label of the last item to Machine ID indicating it is an InHouse Part
     *
     * @param actionEvent The method occurs on button click
     */
    @FXML
    public void OnInHouseButtonClicked(ActionEvent actionEvent) {
        NameorIDLabel.setText("Machine ID");
    }

    /** Changes the label of the last item to Company Name indicating it is an OutSourced Part
     *
     * @param actionEvent This event occurs on button click
     */
    @FXML
    public void OnOutsourcedButtonClicked(ActionEvent actionEvent) {
        NameorIDLabel.setText("Company Name");
    }

    /**
     * Verifies that all entered information is the correct data type
     * and that the inventory number is between the min/max values before saving the part
     *
     * If the part is successfully created it loads the Main Form or else it will throw the
     * appropriate error.
     * @param actionEvent The method is executed when the button is clicked
     * @throws IOException IOException from changing FXML pages (load)
     **/
    public void AddPartSaveButtonClicked(ActionEvent actionEvent) throws IOException {
        try
        {
            int id = 1;
            String name = AddPartNameTextField.getText();
            int stock = Integer.parseInt(AddPartInvTextField.getText());
            double price = Double.parseDouble(AddPartPriceTextField.getText());
            int max = Integer.parseInt(AddPartIMaxTextField.getText());
            int min = Integer.parseInt(AddPartMinTextField.getText());

            if (min < 0)
            {
                System.out.println("This is an error");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setTitle("Add Part");
                alert.setContentText("Min must be greater or equal to 0");
                alert.showAndWait();
            }
            else if (max < min)
            {
                System.out.println("This is an error");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setTitle("Add Part");
                alert.setContentText("Max must be greater top or equal than min");
                alert.showAndWait();
            }
            else if (min > stock || stock > max)
            {
                System.out.println("This is an error");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR");
                alert.setTitle("Add Part");
                alert.setContentText("The stock must be equal to or less than the max and greater than or equal to the min");
                alert.showAndWait();
            }
            else
            {
                if (AddPartInHouseButton.isSelected())
                {


                    InHouse newPart = new InHouse(0, "", 0, 0, 0, 0, 0);

                    newPart.setId(Inventory.currentPartId());
                    newPart.setName(name);
                    newPart.setPrice(price);
                    newPart.setStock(stock);
                    newPart.setMin(min);
                    newPart.setMax(max);


                    int machineID = Integer.parseInt(AddPartMachineIDTextField.getText());
                    newPart.setMachineId(machineID);

                    Inventory.addPart(newPart);
                }
                else if (AddPartOutsourcedButton.isSelected())
                {
                    String companyName = AddPartMachineIDTextField.getText();

                    if (companyName == "")
                    {
                        System.out.println("This is an error");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("ERROR");
                        alert.setTitle("Add Part");
                        alert.setContentText("Please Ensure that the Company Name Text Field is not blank");
                        alert.showAndWait();
                    }
                    Outsourced newPart = new Outsourced(0, "", 0, 0, 0, 0, "");

                    newPart.setId(Inventory.currentPartId());;
                    newPart.setName(name);
                    newPart.setPrice(price);
                    newPart.setStock(stock);
                    newPart.setMin(min);
                    newPart.setMax(max);

                    newPart.setCompanyName(companyName);

                    Inventory.addPart(newPart);
                }
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
            alert.setTitle("Add Part");
            alert.setContentText("Please Ensure that all Text Fields are filled in with the correct types");
            alert.showAndWait();
        }
    }

    /**
     * This method initializes the controller. It also disables the PartID text field and enters text.
     *
     * @param url n/a
     * @param resourceBundle n/a
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        AddPartIDTextField.setText("Auto Gen Disabled");
        AddPartIDTextField.setDisable(true);
    }
}