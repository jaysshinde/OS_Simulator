/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulations.processes_Scheduling;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author praveen
 */
public class SchedulingController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private BorderPane scenePane;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private VBox vbox;

    @FXML
    private JFXButton next;

    @FXML
    private JFXTextField burst;

    @FXML
    private JFXTextField arrival;

    @FXML
    private JFXTextField priority;

    @FXML
    private JFXButton add;

    @FXML
    private JFXButton back;

    @FXML
    private JFXProgressBar st1;

    @FXML
    private Label prl;

    @FXML
    private JFXProgressBar st2;

    @FXML
    private GridPane grid;


    @FXML
    void hamburgerHandler(MouseEvent event) {

    }

    BufferedWriter br;
    int count = 1;


    @FXML
    void actionHandler(ActionEvent event) throws IOException {
        if(event.getSource()==back){
            Stage window = (Stage) scenePane.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/newSimulation/newSimulation.fxml"));
            window.setScene(new Scene(root));
            window.show();
        }
    }

    @FXML
    void buttonEventHandler(ActionEvent event) throws IOException {
        if(next.isDisable()){
//            System.out.println("@#$@#$@#$@#$@#SDFDFASF\n");
            next.setDisable(false);
            st1.setVisible(true);
            st2.setVisible(true);
            prl.setVisible(true);
            grid.setVisible(true);
        }
        if(event.getSource() == add){
//            System.out.println("add button\n");

            String toBeWritten = Integer.toString(count) + " " + burst.getText() + " " + arrival.getText() + " " + priority.getText() + "\n";
            br.write(toBeWritten);
            Label prlabel = new Label(Integer.toString(count));
            Label burstlabel = new Label(burst.getText());
            Label arrivallabel = new Label(arrival.getText());
            Label prioritylabel = new Label(priority.getText());

            prlabel.getStylesheets().add("/styles/styles.css");
            prlabel.getStyleClass().add("processLabel");
            prlabel.setPrefWidth(544);
            prlabel.setPrefHeight(63);

            burstlabel.getStylesheets().add("/styles/styles.css");
            burstlabel.getStyleClass().add("burstLabel");
            burstlabel.setPrefWidth(544);
            burstlabel.setPrefHeight(63);

            arrivallabel.getStylesheets().add("/styles/styles.css");
            arrivallabel.getStyleClass().add("arrivalLabel");
            arrivallabel.setPrefWidth(544);
            arrivallabel.setPrefHeight(63);

            prioritylabel.getStylesheets().add("/styles/styles.css");
            prioritylabel.getStyleClass().add("priorityLabel");
            prioritylabel.setPrefWidth(544);
            prioritylabel.setPrefHeight(63);

            grid.addRow(count+2, prlabel,burstlabel,arrivallabel,prioritylabel);
            burst.clear();
            arrival.clear();
            priority.clear();
            count++;

        }

        else if(event.getSource() == next){
//            System.out.println("next button\n");

            br.flush();
            br.close();
            Stage window = (Stage) scenePane.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/simulations/processes_Scheduling/actual_schedulling.fxml"));
            window.setScene(new Scene(root));
            window.show();
        }



    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        next.setDisable(true);
        st1.setVisible(false);
        st2.setVisible(false);
        prl.setVisible(false);
        grid.setVisible(false);
        try {
//            System.out.println("File opening");
            br = new BufferedWriter(new FileWriter(new File("process_schedulling.txt")));
        } catch (IOException ex) {
            Logger.getLogger(SchedulingController.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequiredFieldValidator validateRequired1 = new RequiredFieldValidator();
        NumberValidator validateNumber1 = new NumberValidator();
        validateNumber1.setMessage("Please enter a number");

        RequiredFieldValidator validateRequired2 = new RequiredFieldValidator();
        NumberValidator validateNumber2 = new NumberValidator();
        validateNumber2.setMessage("Please enter a number");

        RequiredFieldValidator validateRequired3 = new RequiredFieldValidator();
        NumberValidator validateNumber3 = new NumberValidator();
        validateNumber3.setMessage("Please enter a number");

        burst.getValidators().addAll(validateNumber1,validateRequired1);
        burst.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue){
                if(!newValue){
                    burst.validate();
                }
            }

        });

        arrival.getValidators().addAll(validateNumber2,validateRequired2);
        arrival.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue){
                if(!newValue){
                    arrival.validate();
                }


            }

        });

        priority.getValidators().addAll(validateNumber3,validateRequired3);
        priority.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue){
                if(!newValue){
                    priority.validate();
                }


            }

        });

    }

}
