package com.cesarwillymc.libraryprojectmpp.ui.home;

import com.almasb.fxgl.core.View;
import com.cesarwillymc.libraryprojectmpp.ui.di.DIControllers;
import com.cesarwillymc.libraryprojectmpp.ui.home.controller.HomeController;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.HBox;

public class DashboardView implements View {
    private final Node node;

    public DashboardView() {
        HomeController homeController = DIControllers.createHomeController();
        var booksAndStatus = homeController.getDataForBooksAndStatus();
        var membersAndHistory = homeController.getDataForMembersAndHistory();
        // create first chart
        PieChart chart1 = new PieChart(booksAndStatus);
        chart1.setTitle("Books and Status");

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
