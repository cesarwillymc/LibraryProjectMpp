package com.cesarwillymc.libraryprojectmpp.ui.members.entity;

import com.cesarwillymc.libraryprojectmpp.domain.enums.TypeStatusReturnBook;
import javafx.scene.paint.Color;

import java.time.LocalDate;

public class LibraryMemberUI {
    final Integer idCopyBook;
    final String title;
    final String isbn;
    LocalDate date;
    final LocalDate dateBorrow;
    final LocalDate dateDue;
    final LocalDate dateReturned;
    final Color status;
    final TypeStatusReturnBook typeStatus;
    final ParamsTable paramsTable;


    public LibraryMemberUI(Integer idCopyBook,String title, String isbn,  LocalDate dateBorrow, LocalDate dateDue, LocalDate dateReturned, Color status, TypeStatusReturnBook typeStatus, String idMemberCheckout) {
        this.title = title;
        this.isbn = isbn;
        this.idCopyBook = idCopyBook;

        this.dateBorrow = dateBorrow;
        this.dateDue = dateDue;
        this.dateReturned = dateReturned;
        this.status = status;
        this.typeStatus = typeStatus;
        this.paramsTable = new ParamsTable(typeStatus,idMemberCheckout);
    }

    public ParamsTable getParamsTable() {
        return paramsTable;
    }

    public Integer getIdCopyBook() {
        return idCopyBook;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDate getDateBorrow() {
        return dateBorrow;
    }

    public LocalDate getDateDue() {
        return dateDue;
    }

    public LocalDate getDateReturned() {
        return dateReturned;
    }

    public Color getStatus() {
        return status;
    }

    public TypeStatusReturnBook getTypeStatus() {
        return typeStatus;
    }

    public static Color getColor(TypeStatusReturnBook typeStatus){
        Color color = Color.ALICEBLUE;
        switch (typeStatus){
            case OUT_TIME -> {
                color= Color.DARKRED;
                break;
            }
            case PROCESS -> {
                color= Color.LIGHTYELLOW;
                break;
            }
            case FINISH -> {
                color= Color.GREEN;
                break;
            }
        }
        return color;
    }

}
