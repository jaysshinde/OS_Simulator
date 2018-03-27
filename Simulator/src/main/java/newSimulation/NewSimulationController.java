/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newSimulation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author praveen
 */
public class NewSimulationController implements Initializable{

    /**
     * Initializes the controller class.
     */

    @FXML
    private BorderPane scenePane;

    @FXML
    private AnchorPane anchor;

    @FXML
    private VBox vbox;

    @FXML
    private JFXHamburger hamburger;
    HamburgerSlideCloseTransition transition;

    @FXML
    private JFXNodesList socket;

    @FXML
    private JFXNodesList process;

    @FXML
    private JFXNodesList memory;

    @FXML
    private JFXNodesList disk;

    @FXML
    private JFXNodesList rtos;

    @FXML
    private JFXButton back;

    @FXML
    void hamburgerHandler(MouseEvent event) {
        if(event.getSource() == hamburger){
            transition.setRate(transition.getRate()*-1);
            transition.play();
        }
    }

    @FXML
    void actionHandler(ActionEvent event) throws IOException {
        if(event.getSource()==back){
            Stage window = (Stage) scenePane.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/home/home.fxml"));
            window.setScene(new Scene(root));
            window.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        transition = new HamburgerSlideCloseTransition(hamburger);
        transition.setRate(-1);

        JFXButton socBtn = new JFXButton("Socket Programming");
        socBtn.setButtonType(JFXButton.ButtonType.FLAT);
        socBtn.getStylesheets().add("/styles/styles.css");
        socBtn.getStyleClass().add("node-list-button-1");
        FontAwesomeIconView arrow = new FontAwesomeIconView(FontAwesomeIcon.CHEVRON_CIRCLE_RIGHT);
        arrow.setSize("25");
        arrow.setFill(Paint.valueOf("#ffffff"));
        socBtn.setGraphic(arrow);
        socBtn.setGraphicTextGap(5);
        socBtn.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, e->{
            if(arrow.getFill().equals(Paint.valueOf("#ffffff"))){
                arrow.setFill(Paint.valueOf("#005a31"));
            }
            else if(arrow.getFill().equals(Paint.valueOf("#005a31")))
                arrow.setFill(Paint.valueOf("#ffffff"));


        });

        socBtn.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, e->{
            if(arrow.getFill().equals(Paint.valueOf("#ffffff"))){
                arrow.setFill(Paint.valueOf("#005a31"));
            }
            else if(arrow.getFill().equals(Paint.valueOf("#005a31")))
                arrow.setFill(Paint.valueOf("#ffffff"));


        });

//        socBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
//            if(socBtn.getGraphic().equals(new FontAwesomeIconView(FontAwesomeIcon.CHEVRON_CIRCLE_RIGHT))){
//                socBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.CHEVRON_CIRCLE_LEFT));
//            }
//            else{
//                socBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.CHEVRON_CIRCLE_RIGHT));
//
//            }
//
//        });


        JFXButton socBtn1 = new JFXButton("TCP");
        socBtn1.setButtonType(JFXButton.ButtonType.RAISED);
        socBtn1.getStylesheets().add("/styles/styles.css");
        socBtn1.getStyleClass().add("node-list-button-2");
        socBtn1.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
            try {
                Stage window = (Stage) scenePane.getScene().getWindow();

                Parent root = FXMLLoader.load(getClass().getResource("/simulations/sockets_TCP/tcp.fxml"));
                window.setScene(new Scene(root));
                window.show();

            } catch (IOException ex) {
                Logger.getLogger(NewSimulationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });


        JFXButton socBtn2 = new JFXButton("UDP");
        socBtn2.setButtonType(JFXButton.ButtonType.RAISED);
        socBtn2.getStylesheets().add("/styles/styles.css");
        socBtn2.getStyleClass().add("node-list-button-2");
        socBtn2.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
            try {
                Stage window = (Stage) scenePane.getScene().getWindow();

                Parent root = FXMLLoader.load(getClass().getResource("/simulations/sockets_UDP/udp.fxml"));
                window.setScene(new Scene(root));
                window.show();

            } catch (IOException ex) {
                Logger.getLogger(NewSimulationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        socket = new JFXNodesList();
        socket.addAnimatedNode(socBtn);
        socket.addAnimatedNode(socBtn1);
        socket.addAnimatedNode(socBtn2);

        socket.setSpacing(100);
        socket.setRotate(-90);


        vbox.getChildren().add(socket);



        JFXButton proBtn = new JFXButton("Processes");
        proBtn.setButtonType(JFXButton.ButtonType.FLAT);
        proBtn.getStylesheets().add("/styles/styles.css");
        proBtn.getStyleClass().add("node-list-button-1");
        FontAwesomeIconView arrow1 = new FontAwesomeIconView(FontAwesomeIcon.CHEVRON_CIRCLE_RIGHT);
        arrow1.setSize("25");
        arrow1.setFill(Paint.valueOf("#ffffff"));
        proBtn.setGraphic(arrow1);
        proBtn.setGraphicTextGap(5);
        proBtn.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, e->{
            if(arrow1.getFill().equals(Paint.valueOf("#ffffff"))){
                arrow1.setFill(Paint.valueOf("#005a31"));
            }
            else if(arrow1.getFill().equals(Paint.valueOf("#005a31")))
                arrow1.setFill(Paint.valueOf("#ffffff"));


        });

        proBtn.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, e->{
            if(arrow1.getFill().equals(Paint.valueOf("#ffffff"))){
                arrow1.setFill(Paint.valueOf("#005a31"));
            }
            else if(arrow1.getFill().equals(Paint.valueOf("#005a31")))
                arrow1.setFill(Paint.valueOf("#ffffff"));


        });


        JFXButton proBtn1 = new JFXButton("Process Scheduling");
        proBtn1.setButtonType(JFXButton.ButtonType.RAISED);
        proBtn1.getStylesheets().add("/styles/styles.css");
        proBtn1.getStyleClass().add("node-list-button-2");
        proBtn1.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
            try {
                Stage window = (Stage) scenePane.getScene().getWindow();

                Parent root = FXMLLoader.load(getClass().getResource("/simulations/processes_Scheduling/scheduling.fxml"));
                window.setScene(new Scene(root));
                window.show();

            } catch (IOException ex) {
                Logger.getLogger(NewSimulationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        JFXButton proBtn2 = new JFXButton("Process Synchronisation");
        proBtn2.setButtonType(JFXButton.ButtonType.RAISED);
        proBtn2.getStylesheets().add("/styles/styles.css");
        proBtn2.getStyleClass().add("node-list-button-2");
        proBtn2.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
            try {
                Stage window = (Stage) scenePane.getScene().getWindow();

                Parent root = FXMLLoader.load(getClass().getResource("/simulations/processes_Synchronisation/synchronisation.fxml"));
                window.setScene(new Scene(root));
                window.show();

            } catch (IOException ex) {
                Logger.getLogger(NewSimulationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });


        process = new JFXNodesList();
        process.addAnimatedNode(proBtn);
        process.addAnimatedNode(proBtn1);
        process.addAnimatedNode(proBtn2);

        process.setSpacing(100);
        process.setRotate(-90);

        vbox.getChildren().add(process);




        JFXButton memBtn = new JFXButton("Memory Management");
        memBtn.setButtonType(JFXButton.ButtonType.FLAT);
        memBtn.getStylesheets().add("/styles/styles.css");
        memBtn.getStyleClass().add("node-list-button-1");
        FontAwesomeIconView arrow2 = new FontAwesomeIconView(FontAwesomeIcon.CHEVRON_CIRCLE_RIGHT);
        arrow2.setSize("25");
        arrow2.setFill(Paint.valueOf("#ffffff"));
        memBtn.setGraphic(arrow2);
        memBtn.setGraphicTextGap(5);
        memBtn.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, e->{
            if(arrow2.getFill().equals(Paint.valueOf("#ffffff"))){
                arrow2.setFill(Paint.valueOf("#005a31"));
            }
            else if(arrow2.getFill().equals(Paint.valueOf("#005a31")))
                arrow2.setFill(Paint.valueOf("#ffffff"));


        });

        memBtn.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, e->{
            if(arrow2.getFill().equals(Paint.valueOf("#ffffff"))){
                arrow2.setFill(Paint.valueOf("#005a31"));
            }
            else if(arrow2.getFill().equals(Paint.valueOf("#005a31")))
                arrow2.setFill(Paint.valueOf("#ffffff"));


        });


        JFXButton memBtn1 = new JFXButton("Multi Programming");
        memBtn1.setButtonType(JFXButton.ButtonType.RAISED);
        memBtn1.getStylesheets().add("/styles/styles.css");
        memBtn1.getStyleClass().add("node-list-button-2");
        memBtn1.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
            try {
                Stage window = (Stage) scenePane.getScene().getWindow();

                Parent root = FXMLLoader.load(getClass().getResource("/simulations/memory_management_Multiprogramming/multiprogramming.fxml"));
                window.setScene(new Scene(root));
                window.show();

            } catch (IOException ex) {
                Logger.getLogger(NewSimulationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });


        JFXButton memBtn2 = new JFXButton("Page Replacement");
        memBtn2.setButtonType(JFXButton.ButtonType.RAISED);
        memBtn2.getStylesheets().add("/styles/styles.css");
        memBtn2.getStyleClass().add("node-list-button-2");
        memBtn2.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
            try {
                Stage window = (Stage) scenePane.getScene().getWindow();

                Parent root = FXMLLoader.load(getClass().getResource("/simulations/memory_management_Page_replacement/page_replacement.fxml"));
                window.setScene(new Scene(root));
                window.show();

            } catch (IOException ex) {
                Logger.getLogger(NewSimulationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

//        JFXButton memBtn3 = new JFXButton("Page Replacement");
//        memBtn3.setButtonType(JFXButton.ButtonType.RAISED);
//        memBtn3.getStylesheets().add("/styles/styles.css");
//        memBtn3.getStyleClass().add("node-list-button");

        memory = new JFXNodesList();
        memory.addAnimatedNode(memBtn);
        memory.addAnimatedNode(memBtn1);
        memory.addAnimatedNode(memBtn2);
//        memory.addAnimatedNode(memBtn3);

        memory.setSpacing(100);
        memory.setRotate(-90);

        vbox.getChildren().add(memory);



        JFXButton diskBtn = new JFXButton("Disk Management");
        diskBtn.setButtonType(JFXButton.ButtonType.FLAT);
        diskBtn.getStylesheets().add("/styles/styles.css");
        diskBtn.getStyleClass().add("node-list-button-1");
        FontAwesomeIconView arrow3 = new FontAwesomeIconView(FontAwesomeIcon.CHEVRON_CIRCLE_RIGHT);
        arrow3.setSize("25");
        arrow3.setFill(Paint.valueOf("#ffffff"));
        diskBtn.setGraphic(arrow3);
        diskBtn.setGraphicTextGap(5);
        diskBtn.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, e->{
            if(arrow3.getFill().equals(Paint.valueOf("#ffffff"))){
                arrow3.setFill(Paint.valueOf("#005a31"));
            }
            else if(arrow3.getFill().equals(Paint.valueOf("#005a31")))
                arrow3.setFill(Paint.valueOf("#ffffff"));


        });

        diskBtn.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, e->{
            if(arrow3.getFill().equals(Paint.valueOf("#ffffff"))){
                arrow3.setFill(Paint.valueOf("#005a31"));
            }
            else if(arrow3.getFill().equals(Paint.valueOf("#005a31")))
                arrow3.setFill(Paint.valueOf("#ffffff"));


        });


        JFXButton diskBtn1 = new JFXButton("Disk Schdulling");
        diskBtn1.setButtonType(JFXButton.ButtonType.RAISED);
        diskBtn1.getStylesheets().add("/styles/styles.css");
        diskBtn1.getStyleClass().add("node-list-button-2");
        diskBtn1.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
            try {
                Stage window = (Stage) scenePane.getScene().getWindow();

                Parent root = FXMLLoader.load(getClass().getResource("/simulations/disk_management_Scheduling/scheduling.fxml"));
                window.setScene(new Scene(root));
                window.show();

            } catch (IOException ex) {
                Logger.getLogger(NewSimulationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });



        JFXButton diskBtn2 = new JFXButton("File Allocation");
        diskBtn2.setButtonType(JFXButton.ButtonType.RAISED);
        diskBtn2.getStylesheets().add("/styles/styles.css");
        diskBtn2.getStyleClass().add("node-list-button-2");
        diskBtn2.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
            try {
                Stage window = (Stage) scenePane.getScene().getWindow();

                Parent root = FXMLLoader.load(getClass().getResource("/simulations/disk_management_File_allocation/File_allocation.fxml"));
                window.setScene(new Scene(root));
                window.show();

            } catch (IOException ex) {
                Logger.getLogger(NewSimulationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });



//        JFXButton diskBtn3 = new JFXButton("Page Replacement");
//        diskBtn3.setButtonType(JFXButton.ButtonType.RAISED);
//        diskBtn3.getStylesheets().add("/styles/styles.css");
//        diskBtn3.getStyleClass().add("node-list-button");

        disk = new JFXNodesList();
        disk.addAnimatedNode(diskBtn);
        disk.addAnimatedNode(diskBtn1);
        disk.addAnimatedNode(diskBtn2);
//        disk.addAnimatedNode(diskBtn3);

        disk.setSpacing(100);
        disk.setRotate(-90);

        vbox.getChildren().add(disk);


        JFXButton rtosBtn = new JFXButton("Real Time OS");
        rtosBtn.setButtonType(JFXButton.ButtonType.FLAT);
        rtosBtn.getStylesheets().add("/styles/styles.css");
        rtosBtn.getStyleClass().add("node-list-button-1");
        FontAwesomeIconView arrow4 = new FontAwesomeIconView(FontAwesomeIcon.CHEVRON_CIRCLE_RIGHT);
        arrow4.setSize("25");
        arrow4.setFill(Paint.valueOf("#ffffff"));
        rtosBtn.setGraphic(arrow4);
        rtosBtn.setGraphicTextGap(5);
        rtosBtn.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, e->{
            if(arrow4.getFill().equals(Paint.valueOf("#ffffff"))){
                arrow4.setFill(Paint.valueOf("#005a31"));
            }
            else if(arrow4.getFill().equals(Paint.valueOf("#005a31")))
                arrow4.setFill(Paint.valueOf("#ffffff"));


        });

        rtosBtn.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, e->{
            if(arrow4.getFill().equals(Paint.valueOf("#ffffff"))){
                arrow4.setFill(Paint.valueOf("#005a31"));
            }
            else if(arrow4.getFill().equals(Paint.valueOf("#005a31")))
                arrow4.setFill(Paint.valueOf("#ffffff"));


        });

        rtosBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
            try {
                Stage window = (Stage) scenePane.getScene().getWindow();

                Parent root = FXMLLoader.load(getClass().getResource("/simulations/Real_time_OS/real_time_OS.fxml"));
                window.setScene(new Scene(root));
                window.show();

            } catch (IOException ex) {
                Logger.getLogger(NewSimulationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });




        vbox.getChildren().add(rtosBtn);
    }


}
