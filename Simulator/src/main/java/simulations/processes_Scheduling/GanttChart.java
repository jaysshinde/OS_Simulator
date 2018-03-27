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


//package com.sun.javafx.charts;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

import com.sun.javafx.charts.Legend;
import javafx.beans.NamedArg;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;

public class GanttChart<X,Y> extends XYChart<X,Y> {


    private int noOfProcesses;

    public int getNoOfProcesses() {
        return noOfProcesses;
    }

    public void setNoOfProcesses(int noOfProcesses) {
        this.noOfProcesses = noOfProcesses;
    }

    public static class Legend extends TilePane {

        private static final int GAP = 5;

        private ListChangeListener<Legend.LegendItem> itemsListener = c -> {
            getChildren().clear();
            for (Legend.LegendItem item : getItems()) getChildren().add(item.label);
            if(isVisible()) requestLayout();
        };

        private BooleanProperty vertical = new BooleanPropertyBase(false) {
            @Override protected void invalidated() {
                setOrientation(get() ? Orientation.VERTICAL : Orientation.HORIZONTAL);
            }

            @Override
            public Object getBean() {
                return Legend.this;
            }

            @Override
            public String getName() {
                return "vertical";
            }
        };
        public final boolean isVertical() { return vertical.get(); }
        public final void setVertical(boolean value) { vertical.set(value); }
        public final BooleanProperty verticalProperty() { return vertical; }

        private ObjectProperty<ObservableList<Legend.LegendItem>> items = new ObjectPropertyBase<ObservableList<Legend.LegendItem>>() {
            ObservableList<Legend.LegendItem> oldItems = null;
            @Override protected void invalidated() {
                if(oldItems!=null) oldItems.removeListener(itemsListener);
                getChildren().clear();
                ObservableList<Legend.LegendItem> newItems = get();
                if(newItems != null) {
                    newItems.addListener(itemsListener);
                    for(Legend.LegendItem item: newItems) getChildren().add(item.label);
                }
                oldItems = get();
                requestLayout();
            }

            @Override
            public Object getBean() {
                return Legend.this;
            }

            @Override
            public String getName() {
                return "items";
            }
        };
        public final void setItems(ObservableList<Legend.LegendItem> value) {itemsProperty().set(value);}
        public final ObservableList<Legend.LegendItem> getItems() { return items.get();}
        public final ObjectProperty<ObservableList<Legend.LegendItem>> itemsProperty() {return items;}


        public Legend() {
            super(GAP, GAP);
            setTileAlignment(Pos.CENTER_LEFT);
            setItems(FXCollections.<Legend.LegendItem>observableArrayList());
            getStyleClass().setAll("chart-label");
        }


        @Override
        protected double computePrefWidth(double forHeight) {
            // Legend prefWidth is zero if there are no legend items
            return (getItems().size() > 0) ? super.computePrefWidth(forHeight) : 0;
        }

        @Override
        protected double computePrefHeight(double forWidth) {
            // Legend prefHeight is zero if there are no legend items
            return (getItems().size() > 0) ? super.computePrefHeight(forWidth) : 0;
        }

        public static class LegendItem {

            private Label label = new Label();

            private StringProperty text = new StringPropertyBase() {
                @Override protected void invalidated() {
                    label.setText(get());
                }

                @Override
                public Object getBean() {
                    return Legend.LegendItem.this;
                }

                @Override
                public String getName() {
                    return "text";
                }
            };
            public final String getText() { return text.getValue(); }
            public final void setText(String value) { text.setValue(value); }
            public final StringProperty textProperty() { return text; }

            private ObjectProperty<Node> symbol = new ObjectPropertyBase<Node>(new Region()) {
                @Override protected void invalidated() {
                    Node symbol = get();
                    if(symbol != null) symbol.getStyleClass().setAll("chart-legend-item-symbol");
                    label.setGraphic(symbol);
                }

                @Override
                public Object getBean() {
                    return Legend.LegendItem.this;
                }

                @Override
                public String getName() {
                    return "symbol";
                }
            };
            public final Node getSymbol() { return symbol.getValue(); }
            public final void setSymbol(Node value) { symbol.setValue(value); }
            public final ObjectProperty<Node> symbolProperty() { return symbol; }

            public LegendItem(String text, int count) {
                setText(text);
                label.getStylesheets().add("/styles/ganttchart.css");
                label.getStyleClass().add("status-"+Integer.toString(count));
                label.setAlignment(Pos.CENTER_LEFT);
                label.setContentDisplay(ContentDisplay.LEFT);
                label.setGraphic(getSymbol());
            }

            public LegendItem(String text, Node symbol, int count) {
                this(text,count);
                setSymbol(symbol);

            }
        }
    }




    public static class ExtraData {

        public long length;
        public String styleClass;


        public ExtraData(long lengthMs, String styleClass) {
            super();
            this.length = lengthMs;
            this.styleClass = styleClass;
        }
        public long getLength() {
            return length;
        }
        public void setLength(long length) {
            this.length = length;
        }
        public String getStyleClass() {
            return styleClass;
        }
        public void setStyleClass(String styleClass) {
            this.styleClass = styleClass;
        }


    }

    private double blockHeight = 10;
    private Legend legend = new Legend();
    private final BitSet colorBits = new BitSet(8);
    static String DEFAULT_COLOR = "default-color";



    public GanttChart(@NamedArg("xAxis") Axis<X> xAxis, @NamedArg("yAxis") Axis<Y> yAxis) {
        this(xAxis, yAxis, FXCollections.<Series<X, Y>>observableArrayList());
    }

    public GanttChart(@NamedArg("xAxis") Axis<X> xAxis, @NamedArg("yAxis") Axis<Y> yAxis, @NamedArg("data") ObservableList<Series<X,Y>> data) {
        super(xAxis, yAxis);
        if (!(xAxis instanceof ValueAxis && yAxis instanceof CategoryAxis)) {
            throw new IllegalArgumentException("Axis type incorrect, X and Y should both be NumberAxis");
        }
        setData(data);
        setLegend(legend);

    }

    private static String getStyleClass( Object obj) {
        return ((ExtraData) obj).getStyleClass();
    }

    private static double getLength( Object obj) {
        return ((ExtraData) obj).getLength();
    }

    @Override protected void layoutPlotChildren() {

        for (int seriesIndex=0; seriesIndex < getData().size(); seriesIndex++) {

            Series<X,Y> series = getData().get(seriesIndex);

            Iterator<Data<X,Y>> iter = getDisplayedDataIterator(series);
            while(iter.hasNext()) {
                Data<X,Y> item = iter.next();
                double x = getXAxis().getDisplayPosition(item.getXValue());
                double y = getYAxis().getDisplayPosition(item.getYValue());
                if (Double.isNaN(x) || Double.isNaN(y)) {
                    continue;
                }
                Node block = item.getNode();
                Rectangle ellipse;
                if (block != null) {
                    if (block instanceof StackPane) {
                        StackPane region = (StackPane)item.getNode();
                        if (region.getShape() == null) {
                            ellipse = new Rectangle( getLength( item.getExtraValue()), getBlockHeight());
                        } else if (region.getShape() instanceof Rectangle) {
                            ellipse = (Rectangle)region.getShape();
                        } else {
                            return;
                        }
                        ellipse.setWidth( getLength( item.getExtraValue()) * ((getXAxis() instanceof NumberAxis) ? Math.abs(((NumberAxis)getXAxis()).getScale()) : 1));
                        ellipse.setHeight(getBlockHeight() * ((getYAxis() instanceof NumberAxis) ? Math.abs(((NumberAxis)getYAxis()).getScale()) : 1));
                        y -= getBlockHeight() / 2.0;

                        // Note: workaround for RT-7689 - saw this in ProgressControlSkin
                        // The region doesn't update itself when the shape is mutated in place, so we
                        // null out and then restore the shape in order to force invalidation.
                        region.setShape(null);
                        region.setShape(ellipse);
                        region.setScaleShape(false);
                        region.setCenterShape(false);
                        region.setCacheShape(false);

                        block.setLayoutX(x);
                        block.setLayoutY(y);
                    }
                }
            }
        }
    }

    public double getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight( double blockHeight) {
        this.blockHeight = blockHeight;
    }

    @Override protected void dataItemAdded(Series<X,Y> series, int itemIndex, Data<X,Y> item) {
        Node block = createContainer(series, getData().indexOf(series), item, itemIndex);
        getPlotChildren().add(block);
    }

    @Override protected  void dataItemRemoved(final Data<X,Y> item, final Series<X,Y> series) {
        final Node block = item.getNode();
        getPlotChildren().remove(block);
        removeDataItemFromDisplay(series, item);
    }

    @Override protected void dataItemChanged(Data<X, Y> item) {
    }

    @Override protected  void seriesAdded(Series<X,Y> series, int seriesIndex) {
        for (int j=0; j<series.getData().size(); j++) {
            Data<X,Y> item = series.getData().get(j);
            Node container = createContainer(series, seriesIndex, item, j);
            getPlotChildren().add(container);
        }
    }

    @Override protected  void seriesRemoved(final Series<X,Y> series) {
        for (XYChart.Data<X,Y> d : series.getData()) {
            final Node container = d.getNode();
            getPlotChildren().remove(container);
        }
        removeSeriesFromDisplay(series);

    }


    private Node createContainer(Series<X, Y> series, int seriesIndex, final Data<X,Y> item, int itemIndex) {

        Node container = item.getNode();

        if (container == null) {
            container = new StackPane();
            item.setNode(container);
        }

        container.getStyleClass().add( getStyleClass( item.getExtraValue()));

        return container;
    }

    @Override protected void updateAxisRange() {
        final Axis<X> xa = getXAxis();
        final Axis<Y> ya = getYAxis();
        List<X> xData = null;
        List<Y> yData = null;
        if(xa.isAutoRanging()) xData = new ArrayList<X>();
        if(ya.isAutoRanging()) yData = new ArrayList<Y>();
        if(xData != null || yData != null) {
            for(Series<X,Y> series : getData()) {
                for(Data<X,Y> data: series.getData()) {
                    if(xData != null) {
                        xData.add(data.getXValue());
                        xData.add(xa.toRealValue(xa.toNumericValue(data.getXValue()) + getLength(data.getExtraValue())));
                    }
                    if(yData != null){
                        yData.add(data.getYValue());
                    }
                }
            }
            if(xData != null) xa.invalidateRange(xData);
            if(yData != null) ya.invalidateRange(yData);
        }
    }




    @Override protected void updateLegend() {
        legend.getItems().clear();
        if (getData() != null) {
            for (int seriesIndex=0; seriesIndex < getData().size(); seriesIndex++) {
                Series<X,Y> series = getData().get(seriesIndex);
                String legendName = "P";
                int count = 1;
                for(int i = 0;i<getNoOfProcesses();i++) {

                    Legend.LegendItem legenditem = new Legend.LegendItem(legendName + Integer.toString(count),count);

                    legend.getItems().add(legenditem);
                    count++;

                }
            }
        }
        if (legend.getItems().size() > 0) {

            if (getLegend() == null) {
                setLegend(legend);
            }
        } else {
            setLegend(null);
        }
    }




//    int nextClearBit = colorBits.nextClearBit(0);
//                colorBits.set(nextClearBit, true);
//    s.defaultColorStyleClass = DEFAULT_COLOR+(nextClearBit%8);

}
