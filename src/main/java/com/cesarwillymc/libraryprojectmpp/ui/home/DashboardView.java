package com.cesarwillymc.libraryprojectmpp.ui.home;

import com.almasb.fxgl.core.View;
import com.cesarwillymc.libraryprojectmpp.ui.di.DIControllers;
import com.cesarwillymc.libraryprojectmpp.ui.home.controller.HomeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DashboardView implements View {
    private final Node node;
    private HomeController homeController = DIControllers.createHomeController();

    public DashboardView() {
        // Create chart nodes and add them to a parent node
        // create data for first chart
        var booksAndStatus = homeController.getDataForBooksAndStatus();
        var membersAndHistory = homeController.getDataForMembersAndHistory();
        // create first chart
        PieChart chart1 = new PieChart(booksAndStatus);
        chart1.setTitle("Books and Status");

        // create data for second chart

        // create second chart
        PieChart chart2 = new PieChart(membersAndHistory);
        chart2.setTitle("Library Members");

        // create layout for charts
        HBox chartsLayout = new HBox();
        chartsLayout.getChildren().addAll(chart1, chart2);


        this.node = chartsLayout;
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
