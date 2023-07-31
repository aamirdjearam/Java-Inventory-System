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
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controller.MainFormController.getModifiedPart;

/**
 * The Modify Part Controller class has the program logic for modifying the selected part from the All Parts Table.
 *
 **/
public class ModifyPartController implements Initializable {

    @FXML
    private Text NameorIDLabel;


    @FXML
    private Button ModifyPartCancelButton;


    @FXML
    private Text ModifyPartIDLabel;

    @FXML
    private TextField ModifyPartIDTextField;

    @FXML
    private RadioButton ModifyPartInHouseButton;

    @FXML
    private Text ModifyPartInvLabel;

    @FXML
    private TextField ModifyPartInvTextField;

    @FXML
    private Label ModifyPartLabel;

    @FXML
    private Text ModifyPartMachineIDLabel;

    @FXML
    private TextField ModifyPartMachineIDTextField;

    @FXML
    private Text ModifyPartMaxLabel;

    @FXML
    private TextField ModifyPartMaxTextField;

    @FXML
    private Text ModifyPartMinLabel;

    @FXML
    private TextField ModifyPartMinTextField;

    @FXML
    private Text ModifyPartNameLabel;

    @FXML
    private TextField ModifyPartNameTextField;

    @FXML
    private RadioButton ModifyPartOutsourcedButton;

    @FXML
    private Text ModifyPartPriceLabel;

    @FXML
    private TextField ModifyPartPriceTextField;

    @FXML
    private Button ModifyPartSaveButton;

    Stage stage;
    Parent scene;


    /**
     *  This method loops through the list of parts until the correct part is found and then updates
     *  it with the new information.
     *
     * @param id The ID of the part to update.
     * @param updatePart The selected part to update.
     * @return Boolean returns either false or true depending on if the part has updated
     */

    public boolean update(int id, Part updatePart)
    {
        int index = -1;

        for(Part updatedPart : Inventory.getAllParts())
        {
            index++;

            if(updatedPart.getId() == id)
            {
                System.out.println("I have updated");
                Inventory.updatePart(index, updatePart);

                return true;
            }

        }
        return false;
    }

    /**
     * Cancels the Add Part screen and returns to the Main Form
     *
     * @param actionEvent The method executes on button click
     * @throws IOException IOException from changing FXML pages (load)
     */
    @FXML
    public void ModifyPartCancelButtonClicked(ActionEvent actionEvent) throws IOException
    {
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Changes the label of the last item to Machine ID indicating it is an InHouse Part
     *
     * @param actionEvent The method occurs on Button Click
     */
    public void ModifyPartInHouseButtonClicked(ActionEvent actionEvent) {
        NameorIDLabel.setText("Machine ID");
    }

    /** Changes the label of the last item to Company Name indicating it is an OutSourced Part
     *
     * @param actionEvent The method occurs on Button Click
     */
    public void ModifyPartOutsourcedButtonClicked(ActionEvent actionEvent) {
        NameorIDLabel.setText("Company Name");
    }


    /**
     * Verifies that all entered information is the correct data type
     and that the inventory number is between the min/max values before updating the part using the update method.
     *
     * If the part is successfully created it loads the Main Form or else it will throw the
     appropriate error.
     *
     * @param actionEvent This method is executed on button click
     * @throws IOException IOException from changing FXML pages (load)
     */
    public void ModifyPartSaveButtonClicked(ActionEvent actionEvent) throws IOException{
        try{
            int id = Integer.parseInt(ModifyPartIDTextField.getText());
            String name = ModifyPartNameTextField.getText();
            int stock = Integer.parseInt(ModifyPartInvTextField.getText());
            double price = Double.parseDouble(ModifyPartPriceTextField.getText());
            int max = Integer.parseInt(ModifyPartMaxTextField.getText());
            int min = Integer.parseInt(ModifyPartMinTextField.getText());

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
                alert.setContentText("Max must be greater than or equal to min");
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
                if (ModifyPartInHouseButton.isSelected())
                {
                    InHouse newPart = new InHouse(0, "", 0, 0, 0, 0, 0);

                    newPart.setId(id);
                    newPart.setName(name);
                    newPart.setPrice(price);
                    newPart.setStock(stock);
                    newPart.setMin(min);
                    newPart.setMax(max);

                    int machineID = Integer.parseInt(ModifyPartMachineIDTextField.getText());
                    newPart.setMachineId(machineID);

                    update(id, newPart);
                }

                else if (ModifyPartOutsourcedButton.isSelected())
                {
                    String companyName = ModifyPartMachineIDTextField.getText();
                    Outsourced newPart = new Outsourced(0, "", 0, 0, 0, 0, "");

                    newPart.setId(id);;
                    newPart.setName(name);
                    newPart.setPrice(price);
                    newPart.setStock(stock);
                    newPart.setMin(min);
                    newPart.setMax(max);

                    newPart.setCompanyName(companyName);

                    update(id, newPart);
                }

                stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                stage.setScene(new Scene((Parent)(scene)));
                stage.show();
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("This is an error");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setTitle("Modify Part");
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

       Part modifiedPart = getModifiedPart();

        ModifyPartIDTextField.setText(Integer.toString((modifiedPart.getId())));
        ModifyPartNameTextField.setText(modifiedPart.getName());
        ModifyPartInvTextField.setText(Integer.toString(modifiedPart.getStock()));
        ModifyPartPriceTextField.setText(Double.toString(modifiedPart.getPrice()));
        ModifyPartMinTextField.setText(Integer.toString(modifiedPart.getMin()));
        ModifyPartMaxTextField.setText(Integer.toString(modifiedPart.getMax()));
        ModifyPartIDTextField.setDisable(true);

        if (modifiedPart instanceof InHouse)
        {
            ModifyPartInHouseButton.setSelected(true);
            NameorIDLabel.setText("Machine ID");
            ModifyPartMachineIDTextField.setText(Integer.toString(((InHouse) modifiedPart).getMachineId()));

        }
        else if (modifiedPart instanceof Outsourced)
        {
            ModifyPartOutsourcedButton.setSelected(true);
            NameorIDLabel.setText("Company Name");
            ModifyPartMachineIDTextField.setText(((Outsourced) modifiedPart).getCompanyName());
        }
    }

}
