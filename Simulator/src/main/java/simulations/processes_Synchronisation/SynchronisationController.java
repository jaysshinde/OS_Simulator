/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulations.processes_Synchronisation;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jay
 */
public class SynchronisationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton back;
    @FXML
    private BorderPane scenePane;
    @FXML
    private JFXButton prodcons;
    @FXML
    private JFXButton readwrite;
    @FXML
    private JFXButton dinphil;

    @FXML
    void hamburgerHandler(MouseEvent event) {
        System.out.println("Hamburger Button pressed");

    }


    @FXML
    void actionHandler(ActionEvent event) throws IOException {
        if (event.getSource() == back) {
            Stage window = (Stage) scenePane.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/newSimulation/newSimulation.fxml"));
            window.setScene(new Scene(root));
            window.show();
        }
    }

    @FXML
    void buttonEventHandler(ActionEvent event) throws IOException {
        if (event.getSource() == prodcons) {
            //System.out.println("Producer Consumer button pressed\n");
            Stage window = (Stage) scenePane.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/simulations/processes_Synchronisation/prod_cons.fxml"));
            window.setScene(new Scene(root));
            window.show();
        } else if (event.getSource() == readwrite) {
            //System.out.println("Readers Writers button pressed\n");
            Stage window = (Stage) scenePane.getScene().getWindow();
           // System.out.println("New Window Created");

            Parent root = FXMLLoader.load(getClass().getResource("/simulations/processes_Synchronisation/read_write.fxml"));
            //System.out.println("FXML Loaded");

            window.setScene(new Scene(root));
            window.show();
        } else if (event.getSource() == dinphil) {
            //System.out.println("Dining Philosophers button pressed\n");
            Stage window = (Stage) scenePane.getScene().getWindow();
            System.out.println("New Window Created");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/simulations/processes_Synchronisation/din_phil.fxml"));
                //System.out.println("FXML Loaded");
                window.setScene(new Scene(root));
                window.show();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
