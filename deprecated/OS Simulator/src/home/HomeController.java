/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
 * @author praveen
 */
public class HomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
   
    @FXML
    private BorderPane scenePane;
    
     @FXML
    private JFXHamburger hamburger;
    HamburgerSlideCloseTransition transition; 

    @FXML
    private JFXButton newSimulation;

    @FXML
    private JFXButton team;

    @FXML
    private JFXButton exit;

    @FXML
    void actionHandler(ActionEvent event) throws IOException {
        Stage window = (Stage) scenePane.getScene().getWindow();
        if(event.getSource() == exit){
           window.close();
           System.out.println("Programme Terminated");
        }
        
//        else if(event.getSource() == team){
//            Parent root = FXMLLoader.load(getClass().getResource("/home/home.fxml"));            
//            window.setScene(new Scene(root));
//            window.show();
//
//        }
//        
        else if(event.getSource() == newSimulation){
            Parent root = FXMLLoader.load(getClass().getResource("/newSimulation/newSimulation.fxml"));
            window.setScene(new Scene(root));
            window.show();
        }

    }
    
   

    
    @FXML
    void hamburgerHandler(MouseEvent event) {
        if(event.getSource() == hamburger){
            transition.setRate(transition.getRate()*-1);
            transition.play();
        }
         
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        transition = new HamburgerSlideCloseTransition(hamburger);
        transition.setRate(-1);
    }    
    
}
