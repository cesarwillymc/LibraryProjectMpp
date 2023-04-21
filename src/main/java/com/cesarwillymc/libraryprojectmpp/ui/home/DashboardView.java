package com.cesarwillymc.libraryprojectmpp.ui.home;

import com.almasb.fxgl.core.View;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DashboardView implements View {
    private final Node node;

    public DashboardView() {
        // Create chart nodes and add them to a parent node
        PieChart pieChart = new PieChart();
        // TODO: Populate the pie chart with data
        BarChart<String, Number> barChart = new BarChart<>(new CategoryAxis(), new NumberAxis());
        // TODO: Populate the bar chart with data
        VBox chartsBox = new VBox(pieChart, barChart);
        chartsBox.setSpacing(10);

        // Create a parent node and add the charts and labels to it
        Label titleLabel = new Label("Dashboard");
        VBox parent = new VBox(titleLabel, chartsBox);
        parent.setAlignment(Pos.TOP_CENTER);
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 10px;");

        this.node = parent;
    }
    @Override
    public Node getNode() {
        return node;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void onUpdate(double tpf) {

    }
}
