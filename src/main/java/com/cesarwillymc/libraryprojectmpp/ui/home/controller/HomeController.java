package com.cesarwillymc.libraryprojectmpp.ui.home.controller;


import com.cesarwillymc.libraryprojectmpp.domain.entities.MemberRecord;
import com.cesarwillymc.libraryprojectmpp.domain.enums.TypeStatusReturnBook;
import com.cesarwillymc.libraryprojectmpp.usecase.member.GetAllMembersUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.memberRecord.GetAllMembersRecordUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class HomeController {
    final GetAllMembersUseCase getAllMembersUseCase;
    final GetAllMembersRecordUseCase getAllMembersRecordUseCase;

    public HomeController(GetAllMembersUseCase getAllMembersUseCase, GetAllMembersRecordUseCase getAllMembersRecordUseCase) {
        this.getAllMembersUseCase = getAllMembersUseCase;
        this.getAllMembersRecordUseCase = getAllMembersRecordUseCase;

    }

    public ObservableList<PieChart.Data> getDataForMembersAndHistory() {
        var membersAll = getAllMembersUseCase.run();
        var membersRecordUserWithBooks = getAllMembersRecordUseCase.run();
        var membersAllLength = membersAll.isSuccess() ? membersAll.getData().size() : 0;
        int membersWithBook = membersRecordUserWithBooks.isSuccess() ? (int) membersRecordUserWithBooks.getData().stream().map(MemberRecord::getMemberResponse).distinct().count():0;
        return FXCollections.observableArrayList(
                new PieChart.Data("Library Members", membersAllLength),
                new PieChart.Data("Library Members with history", membersWithBook)
        );
    }

    public ObservableList<PieChart.Data> getDataForBooksAndStatus() {
        var memberRecord = getAllMembersRecordUseCase.run();
        if (!memberRecord.isSuccess())
            return FXCollections.observableArrayList();
        int bookOutTime = (int) memberRecord.getData().stream().filter(x -> x.getStatusReturnBook() == TypeStatusReturnBook.OUT_TIME).count();
        int bookReturned = (int) memberRecord.getData().stream().filter(x -> x.getStatusReturnBook() == TypeStatusReturnBook.FINISH).count();
        int bookInProcess = (int) memberRecord.getData().stream().filter(x -> x.getStatusReturnBook() == TypeStatusReturnBook.PROCESS).count();

        return FXCollections.observableArrayList(
                new PieChart.Data("Unreturned books", bookOutTime),
                new PieChart.Data("Books returned", bookReturned),
                new PieChart.Data("Borrowed books", bookInProcess)
        );
    }
}
