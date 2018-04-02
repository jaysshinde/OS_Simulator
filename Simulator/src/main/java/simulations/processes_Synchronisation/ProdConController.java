/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulations.processes_Synchronisation;


import java.awt.*;
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
public class ProdConController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton back;
    @FXML
    private BorderPane scenePane;
    @FXML
    private JFXButton produce;
    @FXML
    private JFXButton consume;
    @FXML
    private JFXButton buffer0;
    @FXML
    private JFXButton buffer1;
    @FXML
    private JFXButton buffer2;
    @FXML
    private JFXButton buffer3;
    @FXML
    private JFXButton buffer4;
    //@FXML
    //private JFXButton buttons[5];

    //@FXML
   /*void hamburgerHandler(MouseEvent event) {
        System.out.println("Hamburger Button pressed");

    }*/


    @FXML
    void actionHandler(ActionEvent event) throws IOException {
        if (event.getSource() == back) {
            Stage window = (Stage) scenePane.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/newSimulation/newSimulation.fxml"));
            window.setScene(new Scene(root));
            window.show();
        }
    }
    int counter=-1;
    @FXML
    void buttonEventHandler(ActionEvent event) throws IOException {
        if (event.getSource() == produce) {
            System.out.println("Producer button\n");
            //counter=(counter+1);
            if(counter==4)
                System.out.println("OK");
            else
                counter=(counter+1);
            if (counter<5){
                if (counter==0) {
                    //change button0 text
                    buffer0.setText("Full");
                    buffer0.setStyle("-fx-background-color: #ff0000; ");
                }
                else  if (counter==1){
                        //change button1 text
                    buffer1.setText("Full");
                    buffer1.setStyle("-fx-background-color: #ff0000; ");
                }
                else if (counter==2) {
                    //change button2 text
                    buffer2.setText("Full");
                    buffer2.setStyle("-fx-background-color: #ff0000; ");
                }
                else if (counter==3) {
                    //change button3 text
                    buffer3.setText("Full");
                    buffer3.setStyle("-fx-background-color: #ff0000; ");
                }
                else if (counter==4) {
                    //change button4 text
                    buffer4.setText("Full");
                    buffer4.setStyle("-fx-background-color: #ff0000; ");
                }


            }
            else{
                System.out.println("No producing");
            }

            }


         else if (event.getSource() == consume) {
            System.out.println("Consumer button pressed\n");
            //counter = (counter - 1) % 5;
            if (counter>=0){
                if (counter==0) {
                    //change button0 text
                    buffer0.setText("Empty");
                    buffer0.setStyle("-fx-background-color: #89da59; ");
                    //System.out.println("COK");

                }
                else  if (counter==1){
                    //change button1 text
                    buffer1.setText("Empty");
                    buffer1.setStyle("-fx-background-color: #89da59; ");
                }
                else if (counter==2) {
                    //change button2 text
                    buffer2.setText("Empty");
                    buffer2.setStyle("-fx-background-color: #89da59; ");
                }
                else if (counter==3) {
                    //change button3 text
                    buffer3.setText("Empty");
                    buffer3.setStyle("-fx-background-color: #89da59; ");
                }
                else if (counter==4) {
                    //change button4 text
                    buffer4.setText("Empty");
                    buffer4.setStyle("-fx-background-color: #89da59; ");
                }
                if(counter==-1){
                    System.out.println("COK");

                }
                else {

                    counter = (counter - 1);
                }
            }
            else{
                System.out.println("No consuming");
            }


        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // scenePane.getStylesheets().add(getClass().getResource("/main/resources/styles/styles.css")).toExternalForm();
    }

}
