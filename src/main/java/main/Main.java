package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.*;
import java.io.IOException;

/**
 <b> C482 - Inventory Management System </b>
 <p>
 The Inventory Management system is an application created for the purpose of managing
  parts and products. Parts and Products are added to lists and can be associated. </p>

 <b> JAVADOCS LOCATION </b>
 <p>
 The JavaDocs Folder can be located separately from the code submission as it will be  in a separate upload into the
 submission box.</p>


 <b> FUTURE ENHANCEMENT </b>

 <p> If a new version were to be released, the application should save data after being run for the next use.
  For example, if a part is generated and the program is closed, it should appear the next time the application
 is run. This would be vitally important as inventory systems tend to be used over a long period of time and having data wipe with every run could
  cause serious operational issues. </p>


 <b>RUNTIME ERROR </b>

 <p> Several Runtime Errors occurred when non integers were entered into integer only text-fields.The application would often crash with a NUMBERFORMATEXCEPTION error.
 This was fixed by adding try/catch and catching when this occurs and outputting an error. This prevented the
 application from crashing. By using try/catch, the error was much easier to notice and the user can be informed that something was
 inputted incorrectly. </p>


 <p>This is the main class that runs the application. </p>

 @author Aamir Djearam
 **/
public class Main extends Application {
    /**
     * The start method generates the stage for the application and loads the main
     * form in a pre-determined window size.
     *
     * @param stage n/a
     * @throws IOException IOException from changing FXML pages (load)
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 500);
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method adds the pre-defined products/parts and launches the program.
     *
     * The parts are created prior to the application launching
     *
     * @param args n/a
     */
    public static void main(String[] args) {


        /*StartingPart will determine the index to start with when adding new parts. It takes into account how many parts have been pre-created in the
        main file.
         */


        int startingPart  = Inventory.getAllParts().size();

        for (int i = 0; i < startingPart; i++)
        {
            Inventory.currentPartId();
        }

        /*StartingProduct will determine the index to start with when adding new products. It takes into account how many parts have been pre-created in the
        main file.
         */

        int startingProduct = Inventory.getAllProducts().size();

        for (int i = 0; i < startingProduct; i++)
        {
            Inventory.currentProductId();
        }

        launch();
    }
}