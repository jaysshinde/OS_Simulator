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
import com.jfoenix.controls.JFXHamburger;
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

public class DinPhilController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton back;
    @FXML
    private BorderPane scenePane;
    @FXML
    private JFXButton phil0;
    @FXML
    private JFXButton phil1;
    @FXML
    private JFXButton phil2;
    @FXML
    private JFXButton phil3;
    @FXML
    private JFXButton phil4;
    @FXML
    private JFXButton fork0;
    @FXML
    private JFXButton fork1;
    @FXML
    private JFXButton fork2;
    @FXML
    private JFXButton fork3;
    @FXML
    private JFXButton fork4;
    @FXML
    private JFXHamburger hamburger;


    @FXML
    void hamburgerHandler(MouseEvent event) {
        //DO NOTHING

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

        if (event.getSource() == phil0)
        {
            //writer0.setText("Full");
            if (phil0.getText().equals("Philosopher 0"))
            {
                phil0.setText("Hungry");
                phil0.setStyle("-fx-background-color: #c9d009");
            } else if (phil0.getText().equals("Hungry"))
            {
                if (fork0.getText().equals("Using") || fork4.getText().equals("Using"))
                {
                    {
                        //
                    }
                } else
                    {
                    phil0.setText("Eating");
                    phil0.setStyle("-fx-background-color: #fe0000");
                    fork0.setText("Using");
                    fork4.setText("Using");
                    fork0.setStyle("-fx-background-color: #fe0000");
                    fork4.setStyle("-fx-background-color: #fe0000");


                    }
            }
            else if (phil0.getText().equals("Eating"))
            {
                phil0.setText("Philosopher 0");
                phil0.setStyle("-fx-background-color: #89da59");
                fork0.setText("Fork 0");
                fork0.setStyle("-fx-background-color: #c9d009");
                fork4.setText("Fork 4");
                fork4.setStyle("-fx-background-color: #c9d009");
            }

        }
        else if (event.getSource() == phil1)
        {
            //writer0.setText("Full");
            if (phil1.getText().equals("Philosopher 1")) {
                phil1.setText("Hungry");
                phil1.setStyle("-fx-background-color: #c9d009");
            } else if (phil1.getText().equals("Hungry")) {
                if (fork1.getText().equals("Using") || fork0.getText().equals("Using")) {
                    {
                        //
                    }
                } else {
                    phil1.setText("Eating");
                    phil1.setStyle("-fx-background-color: #fe0000");
                    fork1.setText("Using");
                    fork0.setText("Using");
                    fork1.setStyle("-fx-background-color: #fe0000");
                    fork0.setStyle("-fx-background-color: #fe0000");


                }
            } else if (phil1.getText().equals("Eating")) {
                phil1.setText("Philosopher 1");
                phil1.setStyle("-fx-background-color: #89da59");
                fork0.setText("Fork 0");
                fork0.setStyle("-fx-background-color: #c9d009");
                fork1.setText("Fork 1");
                fork1.setStyle("-fx-background-color: #c9d009");
            }

        }
        else if (event.getSource() == phil2) {
            //writer0.setText("Full");
            if (phil2.getText().equals("Philosopher 2")) {
                phil2.setText("Hungry");
                phil2.setStyle("-fx-background-color: #c9d009");
            } else if (phil2.getText().equals("Hungry")) {
                if (fork2.getText().equals("Using") || fork1.getText().equals("Using")) {
                    {
                        //
                    }
                } else {
                    phil2.setText("Eating");
                    phil2.setStyle("-fx-background-color: #fe0000");
                    fork2.setText("Using");
                    fork1.setText("Using");
                    fork2.setStyle("-fx-background-color: #fe0000");
                    fork1.setStyle("-fx-background-color: #fe0000");


                }
            } else if (phil2.getText().equals("Eating")) {
                phil2.setText("Philosopher 2");
                phil2.setStyle("-fx-background-color: #89da59");
                fork2.setText("Fork 2");
                fork2.setStyle("-fx-background-color: #c9d009");
                fork1.setText("Fork 1");
                fork1.setStyle("-fx-background-color: #c9d009");
            }

        }
        else if (event.getSource() == phil3) {
            //writer0.setText("Full");
            if (phil3.getText().equals("Philosopher 3")) {
                phil3.setText("Hungry");
                phil3.setStyle("-fx-background-color: #c9d009");
            } else if (phil3.getText().equals("Hungry")) {
                if (fork3.getText().equals("Using") || fork2.getText().equals("Using")) {
                    {
                        //
                    }
                } else {
                    phil3.setText("Eating");
                    phil3.setStyle("-fx-background-color: #fe0000");
                    fork3.setText("Using");
                    fork2.setText("Using");
                    fork3.setStyle("-fx-background-color: #fe0000");
                    fork2.setStyle("-fx-background-color: #fe0000");


                }
            } else if (phil3.getText().equals("Eating")) {
                phil3.setText("Philosopher 3");
                phil3.setStyle("-fx-background-color: #89da59");
                fork3.setText("Fork 3");
                fork3.setStyle("-fx-background-color: #c9d009");
                fork2.setText("Fork 2");
                fork2.setStyle("-fx-background-color: #c9d009");
            }

        }
        else if (event.getSource() == phil4)
        {

            //writer0.setText("Full");
            if (phil4.getText().equals("Philosopher 4")) {
                phil4.setText("Hungry");
                phil4.setStyle("-fx-background-color: #c9d009");
            } else if (phil4.getText().equals("Hungry")) {
                if (fork4.getText().equals("Using") || fork3.getText().equals("Using")) {
                    {
                        //
                    }
                } else {
                    phil4.setText("Eating");
                    phil4.setStyle("-fx-background-color: #fe0000");
                    fork3.setText("Using");
                    fork4.setText("Using");
                    fork3.setStyle("-fx-background-color: #fe0000");
                    fork4.setStyle("-fx-background-color: #fe0000");


                }
            } else if (phil4.getText().equals("Eating")) {
                phil4.setText("Philosopher 4");
                phil4.setStyle("-fx-background-color: #89da59");
                fork4.setText("Fork 4");
                fork3.setStyle("-fx-background-color: #c9d009");
                fork3.setText("Fork 3");
                fork4.setStyle("-fx-background-color: #c9d009");
            }

        }

    }







    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // scenePane.getStylesheets().add(getClass().getResource("/main/resources/styles/styles.css")).toExternalForm();
    }

}
