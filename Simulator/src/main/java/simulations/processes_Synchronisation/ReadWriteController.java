/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulations.processes_Synchronisation;

import java.util.Random;
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

public class ReadWriteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton back;
    @FXML
    private BorderPane scenePane;
    @FXML
    private JFXButton next;
    @FXML
    private JFXButton writer0;
    @FXML
    private JFXButton writer1;
    @FXML
    private JFXButton writer2;
    @FXML
    private JFXButton writer3;
    @FXML
    private JFXButton writer4;
    @FXML
    private JFXButton reader0;
    @FXML
    private JFXButton reader1;
    @FXML
    private JFXButton reader2;
    @FXML
    private JFXButton reader3;
    @FXML
    private JFXButton reader4;

    //@FXML
    //private JFXButton buttons[5];

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
    int status=0;
    @FXML
    void buttonEventHandler(ActionEvent event) throws IOException {
        if (event.getSource() == next) {
            //System.out.println("Producer button\n");
        }

         else if (event.getSource() == writer0) {
            //writer0.setText("Full");
            if (writer0.getText().equals ("Writer 0")) {
                writer0.setText("Waiting");
                writer0.setStyle("-fx-background-color: #c9d009");
            } else if (writer0.getText().equals("Waiting")) {
                 if (writer0.getText().equals("Writing") || writer1.getText().equals("Writing") || writer2.getText().equals("Writing") || writer3.getText().equals("Writing") || writer4.getText().equals("Writing")) {
                     {
                         //
                     }
                 }
                else if (reader0.getText().equals("Reading") || reader1.getText().equals("Reading") || reader2.getText().equals("Reading") || reader3.getText().equals("Reading") || reader4.getText().equals("Reading")) {
                    {
                        //
                    }
                }
                 else{
                writer0.setText("Writing");
                writer0.setStyle("-fx-background-color: #fe0000");
                 }
            } else if (writer0.getText().equals( "Writing")) {
                writer0.setText("Writer 0");
                writer0.setStyle("-fx-background-color: #89da59");
            }

        } else if (event.getSource() == writer1) {
            //writer0.setText("Full");
            if (writer1.getText().equals("Writer 1")) {
                writer1.setText("Waiting");
                writer1.setStyle("-fx-background-color: #c9d009");
            } else if (writer1.getText().equals("Waiting")) {
                if (writer0.getText().equals("Writing") || writer1.getText().equals("Writing") || writer2.getText().equals("Writing") || writer3.getText().equals("Writing") || writer4.getText().equals("Writing")) {
                    {
                        //
                    }
                }
                else if (reader0.getText().equals("Reading") || reader1.getText().equals("Reading") || reader2.getText().equals("Reading") || reader3.getText().equals("Reading") || reader4.getText().equals("Reading")) {
                    {
                        //
                    }
                }
                else{
                    writer1.setText("Writing");
                    writer1.setStyle("-fx-background-color: #fe0000");
                }
            } else if (writer1.getText().equals("Writing")) {
                writer1.setText("Writer 1");
                writer1.setStyle("-fx-background-color: #89da59");
            }

        } else if (event.getSource() == writer2) {
            //writer0.setText("Full");
            if (writer2.getText().equals("Writer 2")) {
                writer2.setText("Waiting");
                writer2.setStyle("-fx-background-color: #c9d009");
            } else if (writer2.getText().equals("Waiting")) {
                if (writer0.getText().equals("Writing") || writer1.getText().equals("Writing") || writer2.getText().equals("Writing") || writer3.getText().equals("Writing") || writer4.getText().equals("Writing")) {
                    {
                        //
                    }
                }
                else if (reader0.getText().equals("Reading") || reader1.getText().equals("Reading") || reader2.getText().equals("Reading") || reader3.getText().equals("Reading") || reader4.getText().equals("Reading")) {
                    {
                        //
                    }
                }
                else{
                    writer2.setText("Writing");
                    writer2.setStyle("-fx-background-color: #fe0000");
                }
            } else if (writer2.getText().equals("Writing")) {
                writer2.setText("Writer 2");
                writer2.setStyle("-fx-background-color: #89da59");
            }

        } else if (event.getSource() == writer3) {
            //writer0.setText("Full");
            if (writer3.getText().equals("Writer 3")) {
                writer3.setText("Waiting");
                writer3.setStyle("-fx-background-color: #c9d009");
            } else if (writer3.getText().equals("Waiting")) {
                if (writer0.getText().equals("Writing") || writer1.getText().equals("Writing") || writer2.getText().equals("Writing") || writer3.getText().equals("Writing") || writer4.getText().equals("Writing")) {
                    {
                        //
                    }
                }
                else if (reader0.getText().equals("Reading") || reader1.getText().equals("Reading") || reader2.getText().equals("Reading") || reader3.getText().equals("Reading") || reader4.getText().equals("Reading")) {
                    {
                        //
                    }
                }
                else{
                    writer3.setText("Writing");
                    writer3.setStyle("-fx-background-color: #fe0000");
                }
            } else if (writer3.getText().equals("Writing")) {
                writer3.setText("Writer 3");
                writer3.setStyle("-fx-background-color: #89da59");
            }

        } else if (event.getSource() == writer4) {
            //writer0.setText("Full");
            if (writer4.getText().equals("Writer 4")) {
                writer4.setText("Waiting");
                writer4.setStyle("-fx-background-color: #c9d009");
            } else if (writer4.getText().equals("Waiting")) {
                if (writer0.getText().equals("Writing") || writer1.getText().equals("Writing") || writer2.getText().equals("Writing") || writer3.getText().equals("Writing") || writer4.getText().equals("Writing")) {
                    {
                        //
                    }
                }
                else if (reader0.getText().equals("Reading") || reader1.getText().equals("Reading") || reader2.getText().equals("Reading") || reader3.getText().equals("Reading") || reader4.getText().equals("Reading")) {
                    {
                        //
                    }
                }
                else{
                    writer4.setText("Writing");
                    writer4.setStyle("-fx-background-color: #fe0000");
                }
            } else if (writer4.getText().equals("Writing")) {
                writer4.setText("Writer 4");
                writer4.setStyle("-fx-background-color: #89da59");
            }

        } else if (event.getSource() == reader0) {
            //writer0.setText("Full");
            if (reader0.getText().equals("Reading")) {
                reader0.setText("Reader 0");
                reader0.setStyle("-fx-background-color: #89da59");
            } else if (writer0.getText().equals("Writing") || writer1.getText().equals("Writing") || writer2.getText().equals("Writing") || writer3.getText().equals("Writing") || writer4.getText().equals("Writing")) {
                reader0.setText("Waiting");
                reader0.setStyle("-fx-background-color: #c9d009");

            } else {
                if (reader0.getText().equals("Reader 0")) {
                    reader0.setText("Reading");
                    reader0.setStyle("-fx-background-color: #fe0000");

                }
                else {
                    reader0.setText("Reading");
                    reader0.setStyle("-fx-background-color: #fe0000");

                }

                }

            }

         else if (event.getSource() == reader1)
        {
            //writer0.setText("Full");
            if (reader1.getText().equals("Reading")) {
                reader1.setText("Reader 1");
                reader1.setStyle("-fx-background-color: #89da59");

            } else if (writer0.getText().equals("Writing") || writer1.getText().equals("Writing") || writer2.getText().equals("Writing") || writer3.getText().equals("Writing") || writer4.getText().equals("Writing")) {
                reader1.setText("Waiting");
                reader1.setStyle("-fx-background-color: #c9d009");
            } else {
                if (reader1.getText().equals("Reader 1")) {
                    reader1.setText("Reading");
                    reader1.setStyle("-fx-background-color: #fe0000");

                }
                else {
                    reader1.setText("Reading");
                    reader1.setStyle("-fx-background-color: #fe0000");

                }
            }
        }
        else if (event.getSource() == reader2)
        {
            //writer0.setText("Full");
            if (reader2.getText().equals("Reading")) {
                reader2.setText("Reader 2");
                reader2.setStyle("-fx-background-color: #89da59");

            } else if (writer0.getText().equals("Writing") || writer1.getText().equals("Writing") || writer2.getText().equals("Writing") || writer3.getText().equals("Writing") || writer4.getText().equals("Writing")) {
                reader2.setText("Waiting");
                reader2.setStyle("-fx-background-color: #c9d009");
            } else {
                if (reader2.getText().equals("Reader 2")) {
                    reader2.setText("Reading");
                    reader2.setStyle("-fx-background-color: #fe0000");

                }
                else {
                    reader2.setText("Reading");
                    reader2.setStyle("-fx-background-color: #fe0000");

                }
            }
        }   else if (event.getSource() == reader3)
        {
            //writer0.setText("Full");
            if (reader3.getText().equals("Reading")) {
                reader3.setText("Reader 3");
                reader3.setStyle("-fx-background-color: #89da59");

            } else if (writer0.getText().equals("Writing") || writer1.getText().equals("Writing") || writer2.getText().equals("Writing") || writer3.getText().equals("Writing") || writer4.getText().equals("Writing")) {
                reader3.setText("Waiting");
                reader3.setStyle("-fx-background-color: #c9d009");
            } else {
                if (reader3.getText().equals("Reader 3")) {
                    reader3.setText("Reading");
                    reader3.setStyle("-fx-background-color: #fe0000");

                }
                else {
                    reader3.setText("Reading");
                    reader3.setStyle("-fx-background-color: #fe0000");

                }
            }
        }   else if (event.getSource() == reader4)
        {
            //writer0.setText("Full");
            if (reader4.getText().equals("Reading")) {
                reader4.setText("Reader 4");
                reader4.setStyle("-fx-background-color: #89da59");

            } else if (writer0.getText().equals("Writing") || writer1.getText().equals("Writing") || writer2.getText().equals("Writing") || writer3.getText().equals("Writing") || writer4.getText().equals("Writing")) {
                reader4.setText("Waiting");
                reader4.setStyle("-fx-background-color: #c9d009");
            } else {
                if (reader4.getText().equals("Reader 4")) {
                    reader4.setText("Reading");
                    reader4.setStyle("-fx-background-color: #fe0000");

                }
                else {
                    reader4.setText("Reading");
                    reader4.setStyle("-fx-background-color: #fe0000");

                }
            }
        }

    }





    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // scenePane.getStylesheets().add(getClass().getResource("/main/resources/styles/styles.css")).toExternalForm();
    }

}
