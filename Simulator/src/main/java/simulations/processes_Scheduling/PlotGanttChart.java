/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulations.processes_Scheduling;

/**
 *
 * @author praveen
 */



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import simulations.processes_Scheduling.GanttChart.ExtraData;

// TODO: use date for x-axis
public class PlotGanttChart extends Application {
    String PATH;
    int algo;
    int totalNoOfProcesses;



    public int getAlgo() {
        return algo;
    }
    public void setAlgo(int algo) {
        this.algo = algo;
    }

    PlotGanttChart(String path,int type,int noOfProcesses){
        PATH = path;
        algo = type;
        totalNoOfProcesses = noOfProcesses;
    }

    @Override public void start(Stage stage) throws FileNotFoundException, IOException {

        BufferedReader br = new BufferedReader(new FileReader(new File(PATH)));



        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();

        final GanttChart<Number,String> chart = new GanttChart<Number,String>(xAxis,yAxis);
        xAxis.setLabel("Time of execution");
        xAxis.setTickLabelFill(Color.CHOCOLATE);
        xAxis.setMinorTickCount(1);

        String YLable = "Gantt Chart";
        yAxis.setLabel("");
        yAxis.setTickLabelFill(Color.CHOCOLATE);
        yAxis.setTickLabelGap(10);
        yAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(YLable)));

        String title[] = {"FCFS","SJF","SJF with Preemption","Priority","Priority with Preemption","Round-Robin","Multilevel Queue","Multilevel Feddback"};
        chart.setTitle("Gantt Chart for " + title[getAlgo()-1] + "Scheduling");
        chart.setLegendVisible(false);
        chart.setBlockHeight( 100);

        XYChart.Series series1 = new XYChart.Series();

        String[] colours = new String[1000];
        chart.setNoOfProcesses(totalNoOfProcesses);
        for(int i=0;i<totalNoOfProcesses;i++)
            colours[i] = "status-"+Integer.toString(i+1);
        while(true){
            String tmp1 = br.readLine();
            if(tmp1.isEmpty())
                break;
            String tmp[] = tmp1.split(" ");
            int arr[] = new int[tmp.length];
            if(tmp.length==1)
                break;
            for(int i = 0;i<tmp.length;i++){
                arr[i] = Integer.parseInt(tmp[i]);
            }

            series1.getData().add(new XYChart.Data(arr[1], YLable, new ExtraData(arr[2],colours[arr[0]-1])));

        }

        chart.getData().addAll(series1);
        chart.setLegendVisible(true);
        chart.setLegendSide(Side.BOTTOM);

        chart.getStylesheets().add(getClass().getResource("/styles/ganttchart.css").toExternalForm());



        Scene scene  = new Scene(chart,700,400);
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}