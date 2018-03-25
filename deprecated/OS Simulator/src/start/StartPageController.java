/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author praveen
 */
public class StartPageController implements Initializable {
    
    @FXML
    private BorderPane scenePane;
    
    @FXML
    private JFXButton home;

    @FXML
    private JFXButton newSimulation;

    @FXML
    private JFXButton exit;

    @FXML
    void actionHandler(ActionEvent event) throws IOException {
        Stage window = (Stage) scenePane.getScene().getWindow();
        if(event.getSource() == exit){
           window.close();
           System.out.println("Programme Terminated");
        }
        
        else if(event.getSource() == home){
            Parent root = FXMLLoader.load(getClass().getResource("/home/home.fxml"));            
            window.setScene(new Scene(root));
            window.show();

        }
        
        else if(event.getSource() == newSimulation){
            Parent root = FXMLLoader.load(getClass().getResource("/newSimulation/newSimulation.fxml"));
            window.setScene(new Scene(root));
            window.show();
        }

    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
