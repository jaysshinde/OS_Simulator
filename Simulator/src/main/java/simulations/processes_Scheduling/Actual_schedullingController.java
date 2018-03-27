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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author praveen
 */
public class Actual_schedullingController implements Initializable {

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
    private JFXButton fcfs;

    @FXML
    private JFXButton sjf;

    @FXML
    private JFXButton sjfp;

    @FXML
    private JFXButton priority;

    @FXML
    private JFXButton priorityp;

    @FXML
    private JFXButton robin;

    @FXML
    private JFXButton multiq;

    @FXML
    private JFXButton multif;

    @FXML
    private JFXProgressBar st1;

    @FXML
    private Label wait;

    @FXML
    private Label tatl;

    @FXML
    private Label prolabel;

    @FXML
    private JFXButton gantt;

    @FXML
    private Label prl;

    @FXML
    private JFXProgressBar st2;

    @FXML
    private GridPane grid;

    @FXML
    private JFXTextField quantum;

    @FXML
    private JFXButton setQuantum;

    @FXML
    private JFXButton back;


    int totalNoOfProcesses = 0;
    int algoButton = -1;

    @FXML
    void actionHandler(ActionEvent event) throws IOException {
        if(event.getSource()==back){
            Stage window = (Stage) scenePane.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/simulations/processes_Scheduling/scheduling.fxml"));
            window.setScene(new Scene(root));
            window.show();
        }
    }

    int fillgrid() throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(new File("process_schedulling.txt")));
        String readline;
        int noOfProcesses = 0;
        while((readline = br.readLine())!=null && readline.length()!=0){
            String str[] = readline.split(" ");
            int count = Integer.parseInt(str[0]);
            Label prlabel = new Label(str[0]);
            Label burstlabel = new Label(str[1]);
            Label arrivallabel = new Label(str[2]);
            Label prioritylabel = new Label(str[3]);

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
            noOfProcesses = count;
        }
        return noOfProcesses;
    }

    void executeSchedullingAlgo(int algo) throws InterruptedException, IOException{
        BufferedReader reader = new BufferedReader(new FileReader(new File("process_schedulling.txt")));
        BufferedWriter writerIn = new BufferedWriter(new FileWriter(new File("input.txt")));
        BufferedWriter writerOut = new BufferedWriter(new FileWriter(new File("output.txt")));
        BufferedReader readerOut = new BufferedReader(new FileReader(new File("output.txt")));

        switch(algo){
            case 1:
                int t1 = totalNoOfProcesses;
                writerIn.write(Integer.toString(totalNoOfProcesses) + "\n");
                while(t1-->0){
                    String str[] = reader.readLine().split(" ");
                    writerIn.write(str[1] + " " + str[2] + "\n");
                }
                writerIn.flush();
                writerIn.close();
                String command1 = "scripts/exec_fcfs.exe";
                Process proc1 = Runtime.getRuntime().exec(command1);
                String toWrite1 = Stream.of(proc1.getErrorStream(), proc1.getInputStream()).parallel().map((InputStream isForOutput) -> {
                    StringBuilder output = new StringBuilder();
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(isForOutput))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            output.append(line);
                            output.append("\n");
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return output;
                }).collect(Collectors.joining());

                proc1.waitFor();

                writerOut.write(toWrite1);
                writerOut.flush();
                writerOut.close();

                t1 = totalNoOfProcesses;

                while(t1-->0){
                    readerOut.readLine();
                }

                wait.setText("Average Wait Time = " + readerOut.readLine());
                tatl.setText("Average Turn Around Time = " + readerOut.readLine());

                break;


            case 2:
                int t2 = totalNoOfProcesses;
                writerIn.write(Integer.toString(totalNoOfProcesses) + "\n");
                while(t2-->0){
                    String str[] = reader.readLine().split(" ");
                    writerIn.write(str[1] + " " + str[2] + " " + "\n");
                }
                writerIn.flush();
                writerIn.close();
                String command2 = "scripts/exec_sjf.exe";
                Process proc2 = Runtime.getRuntime().exec(command2);
                String toWrite2 = Stream.of(proc2.getErrorStream(), proc2.getInputStream()).parallel().map((InputStream isForOutput) -> {
                    StringBuilder output = new StringBuilder();
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(isForOutput))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            output.append(line);
                            output.append("\n");
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return output;
                }).collect(Collectors.joining());

                proc2.waitFor();

                writerOut.write(toWrite2);
                writerOut.flush();
                writerOut.close();

                t2 = totalNoOfProcesses;

                while(t2-->0){
                    readerOut.readLine();
                }

                wait.setText("Average Wait Time = " + readerOut.readLine());
                tatl.setText("Average Turn Around Time = " + readerOut.readLine());


                break;


            case 3:
                int t3 = totalNoOfProcesses;
                writerIn.write(Integer.toString(totalNoOfProcesses) + "\n");
                while(t3-->0){
                    String str[] = reader.readLine().split(" ");
                    writerIn.write(str[1] + " " + str[2] + " " + "\n");
                }
                writerIn.flush();
                writerIn.close();
                String command3 = "scripts/exec_sjf_p.exe";
                Process proc3 = Runtime.getRuntime().exec(command3);
                String toWrite3 = Stream.of(proc3.getErrorStream(), proc3.getInputStream()).parallel().map((InputStream isForOutput) -> {
                    StringBuilder output = new StringBuilder();
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(isForOutput))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            output.append(line);
                            output.append("\n");
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return output;
                }).collect(Collectors.joining());

                proc3.waitFor();

                writerOut.write(toWrite3);
                writerOut.flush();
                writerOut.close();

                t3 = totalNoOfProcesses;

                while(true){
                    if(readerOut.readLine().isEmpty())
                        break;
                }

                wait.setText("Average Wait Time = " + readerOut.readLine());
                tatl.setText("Average Turn Around Time = " + readerOut.readLine());


                break;

            case 4:
                int t4 = totalNoOfProcesses;
                writerIn.write(Integer.toString(totalNoOfProcesses) + "\n");
                while(t4-->0){
                    String str[] = reader.readLine().split(" ");
                    writerIn.write(str[1] + " " + str[2] + " " + str[3] + "\n");
                }
                writerIn.flush();
                writerIn.close();
                String command4 = "scripts/exec_priority.exe";
                Process proc4 = Runtime.getRuntime().exec(command4);
                String toWrite4 = Stream.of(proc4.getErrorStream(), proc4.getInputStream()).parallel().map((InputStream isForOutput) -> {
                    StringBuilder output = new StringBuilder();
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(isForOutput))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            output.append(line);
                            output.append("\n");
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return output;
                }).collect(Collectors.joining());

                proc4.waitFor();

                writerOut.write(toWrite4);
                writerOut.flush();
                writerOut.close();

                t4 = totalNoOfProcesses;

                while(t4-->0){
                    readerOut.readLine();
                }

                wait.setText("Average Wait Time = " + readerOut.readLine());
                tatl.setText("Average Turn Around Time = " + readerOut.readLine());


                break;

            case 5:
                int t5 = totalNoOfProcesses;
                writerIn.write(Integer.toString(totalNoOfProcesses) + "\n");
                while(t5-->0){
                    String str[] = reader.readLine().split(" ");
                    writerIn.write(str[1] + " " + str[2] + " " + str[3] + "\n");
                }
                writerIn.flush();
                writerIn.close();
                String command5 = "scripts/exec_priority_p.exe";
                Process proc5 = Runtime.getRuntime().exec(command5);
                String toWrite5 = Stream.of(proc5.getErrorStream(), proc5.getInputStream()).parallel().map((InputStream isForOutput) -> {
                    StringBuilder output = new StringBuilder();
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(isForOutput))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            output.append(line);
                            output.append("\n");
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return output;
                }).collect(Collectors.joining());

                proc5.waitFor();

                writerOut.write(toWrite5);
                writerOut.flush();
                writerOut.close();

                t5 = totalNoOfProcesses;

                while(true){
                    if(readerOut.readLine().isEmpty())
                        break;
                }

                wait.setText("Average Wait Time = " + readerOut.readLine());
                tatl.setText("Average Turn Around Time = " + readerOut.readLine());


                break;

            case 6:
                int t6 = totalNoOfProcesses;
                String q = quantum.getText();
                writerIn.write(Integer.toString(totalNoOfProcesses) + "\n");
                writerIn.write(q + "\n");
                while(t6-->0){
                    String str[] = reader.readLine().split(" ");
                    writerIn.write(str[1] + " " + str[2] + " " + "\n");
                }
                writerIn.flush();
                writerIn.close();
                String command6 = "scripts/exec_round_robin.exe";
                Process proc6 = Runtime.getRuntime().exec(command6);
                String toWrite6 = Stream.of(proc6.getErrorStream(), proc6.getInputStream()).parallel().map((InputStream isForOutput) -> {
                    StringBuilder output = new StringBuilder();
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(isForOutput))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            output.append(line);
                            output.append("\n");
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return output;
                }).collect(Collectors.joining());

                proc6.waitFor();

                writerOut.write(toWrite6);
                writerOut.flush();
                writerOut.close();

                t6 = totalNoOfProcesses;

                while(true){
                    if(readerOut.readLine().isEmpty())
                        break;
                }

                wait.setText("Average Wait Time = " + readerOut.readLine());
                tatl.setText("Average Turn Around Time = " + readerOut.readLine());


                break;

            case 7:
                int t7 = totalNoOfProcesses;
                writerIn.write(Integer.toString(totalNoOfProcesses) + "\n");
                while(t7-->0){
                    String str[] = reader.readLine().split(" ");
                    writerIn.write(str[1] + " " + str[2] + " " + "\n");
                }
                writerIn.flush();
                writerIn.close();
                String command7 = "scripts/exec_multi_queue.exe";
                Process proc7 = Runtime.getRuntime().exec(command7);
                String toWrite7 = Stream.of(proc7.getErrorStream(), proc7.getInputStream()).parallel().map((InputStream isForOutput) -> {
                    StringBuilder output = new StringBuilder();
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(isForOutput))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            output.append(line);
                            output.append("\n");
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return output;
                }).collect(Collectors.joining());

                proc7.waitFor();

                writerOut.write(toWrite7);
                writerOut.flush();
                writerOut.close();

                t7 = totalNoOfProcesses;

                while(t7-->0){
                    readerOut.readLine();
                }

                wait.setText("Average Wait Time = " + readerOut.readLine());
                tatl.setText("Average Turn Around Time = " + readerOut.readLine());


                break;

            case 8:
                int t8 = totalNoOfProcesses;
                writerIn.write(Integer.toString(totalNoOfProcesses) + "\n");
                while(t8-->0){
                    String str[] = reader.readLine().split(" ");
                    writerIn.write(str[1] + " " + str[2] + " " + "\n");
                }
                writerIn.flush();
                writerIn.close();
                String command8 = "scripts/exec_multi_feedback.exe";
                Process proc8 = Runtime.getRuntime().exec(command8);
                String toWrite8 = Stream.of(proc8.getErrorStream(), proc8.getInputStream()).parallel().map((InputStream isForOutput) -> {
                    StringBuilder output = new StringBuilder();
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(isForOutput))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            output.append(line);
                            output.append("\n");
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return output;
                }).collect(Collectors.joining());

                proc8.waitFor();

                writerOut.write(toWrite8);
                writerOut.flush();
                writerOut.close();

                t8 = totalNoOfProcesses;

                while(t8-->0){
                    readerOut.readLine();
                }

                wait.setText("Average Wait Time = " + readerOut.readLine());
                tatl.setText("Average Turn Around Time = " + readerOut.readLine());


                break;


        }

//      Execution of command
//        String command = null;
//        Process p = Runtime.getRuntime().exec(command);
//        p.waitFor();
    }


    @FXML
    void hamburgerHandler(MouseEvent event) {

    }


    @FXML
    void buttonEventHandler(ActionEvent event) throws IOException, InterruptedException {
        if(!st2.isVisible()){
            st2.setVisible(true);
            prl.setVisible(true);
            gantt.setVisible(true);
            grid.setVisible(true);
            totalNoOfProcesses = fillgrid();
//            System.out.println(totalNoOfProcesses);
        }
        if(event.getSource()==fcfs){
            prolabel.setText("FCFS Schedulling");
            prolabel.setVisible(true);
            tatl.setVisible(true);
            wait.setVisible(true);

            gantt.setDisable(false);
            quantum.setVisible(false);
            setQuantum.setVisible(false);
            quantum.clear();
            executeSchedullingAlgo(1);
            algoButton = 1;
        }

        else if(event.getSource()==sjf){
            prolabel.setText("SJF Schedulling");
            prolabel.setVisible(true);
            tatl.setVisible(true);
            wait.setVisible(true);

            gantt.setDisable(false);
            quantum.setVisible(false);
            setQuantum.setVisible(false);
            quantum.clear();
            executeSchedullingAlgo(2);
            algoButton = 2;

        }

        else if(event.getSource()==sjfp){
            prolabel.setText("SJF Schedulling Preemption");
            prolabel.setVisible(true);
            tatl.setVisible(true);
            wait.setVisible(true);

            gantt.setDisable(false);
            quantum.setVisible(false);
            quantum.clear();
            setQuantum.setVisible(false);
            executeSchedullingAlgo(3);
            algoButton = 3;

        }

        else if(event.getSource()==priority){
            prolabel.setText("Prioirty Schedulling");
            prolabel.setVisible(true);
            tatl.setVisible(true);
            wait.setVisible(true);

            gantt.setDisable(false);
            quantum.setVisible(false);
            quantum.clear();
            setQuantum.setVisible(false);
            executeSchedullingAlgo(4);
            algoButton = 4;

        }

        else if(event.getSource()==priorityp){
            prolabel.setText("Prioirty Schedulling with Preemption");
            prolabel.setVisible(true);
            tatl.setVisible(true);
            wait.setVisible(true);

            gantt.setDisable(false);
            quantum.setVisible(false);
            setQuantum.setVisible(false);
            quantum.clear();
            executeSchedullingAlgo(5);
            algoButton = 5;

        }

        else if(event.getSource()==robin){
            prolabel.setVisible(false);
            tatl.setVisible(false);
            wait.setVisible(false);
            quantum.clear();
            quantum.setVisible(true);
            setQuantum.setVisible(true);
            gantt.setDisable(true);
//            executeSchedullingAlgo(6);
            algoButton = 6;

        }

        else if(event.getSource()==multiq){
            prolabel.setText("Multilevel Queue Schedulling without feedback");
            prolabel.setVisible(true);
            tatl.setVisible(true);
            wait.setVisible(true);

            gantt.setDisable(false);
            quantum.setVisible(false);
            quantum.clear();
            setQuantum.setVisible(false);
            executeSchedullingAlgo(7);
            algoButton = 7;

        }

        else if(event.getSource()==multif){
            prolabel.setText("Multilevel Queue Schedulling with feedback");
            prolabel.setVisible(true);
            tatl.setVisible(true);
            wait.setVisible(true);

            gantt.setDisable(false);
            quantum.setVisible(false);
            quantum.clear();
            setQuantum.setVisible(false);
            executeSchedullingAlgo(8);
            algoButton = 8;

        }

        else if(event.getSource()==gantt){
            PlotGanttChart gantt = new PlotGanttChart("output.txt",algoButton,totalNoOfProcesses);
            Stage ganttWindow = new Stage();
            ganttWindow.setTitle("GanttChart");
            ganttWindow.setResizable(false);
            gantt.start(ganttWindow);
        }

        else if(event.getSource()==setQuantum){
            prolabel.setText("Round Robin Schedulling");
            prolabel.setVisible(true);
            prolabel.setVisible(true);
            prolabel.setVisible(true);
            setQuantum.setVisible(true);
            gantt.setDisable(false);

            executeSchedullingAlgo(6);
            tatl.setVisible(true);
            wait.setVisible(true);
        }


    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        st2.setVisible(false);
        prl.setVisible(false);
        gantt.setVisible(false);
        grid.setVisible(false);
        quantum.setVisible(false);
        setQuantum.setVisible(false);

        RequiredFieldValidator validateRequired = new RequiredFieldValidator();
        NumberValidator validateNumber = new NumberValidator();
        validateNumber.setMessage("Please enter a number");

        quantum.getValidators().addAll(validateNumber,validateRequired);
        quantum.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue){
                if(!newValue){
                    quantum.validate();
                }
            }

        });

    }

}