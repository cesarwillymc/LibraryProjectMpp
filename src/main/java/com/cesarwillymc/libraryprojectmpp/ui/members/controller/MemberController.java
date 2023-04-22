package com.cesarwillymc.libraryprojectmpp.ui.members.controller;

import com.cesarwillymc.libraryprojectmpp.domain.entities.LibraryMember;
import com.cesarwillymc.libraryprojectmpp.ui.members.view.MemberCard;
import com.cesarwillymc.libraryprojectmpp.usecase.member.GetAllMembersUseCase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class MemberController {

    GetAllMembersUseCase getAllMembersUseCase;
    private List<LibraryMember> members;

    public MemberController(GetAllMembersUseCase getAllMembersUseCase) {
        this.getAllMembersUseCase = getAllMembersUseCase;

    }

    public void getInitialListMembers(Consumer<List<MemberCard>> data) {
        getAllMembersUseCase.run().apply(x -> {
            members = x;
            data.accept(members.stream().map(this::from).collect(Collectors.toList()));
        }, e -> {
            members = new ArrayList<>();
        });
    }

    public void filterByName(boolean isReverse, Consumer<List<MemberCard>> data) {
        var list = members.stream().sorted((isReverse) ?
                Comparator.comparing(LibraryMember::getFirstName).reversed().thenComparing(LibraryMember::getLastName) :
                Comparator.comparing(LibraryMember::getFirstName).thenComparing(LibraryMember::getLastName)

        ).map(this::from).collect(Collectors.toList());
        data.accept(list);
    }

    public void filterById(boolean isReverse, Consumer<List<MemberCard>> data) {
        var list = members.stream().sorted((isReverse) ?
                Comparator.comparing(LibraryMember::getMemberId).reversed() :
                Comparator.comparing(LibraryMember::getMemberId)

        ).map(this::from).collect(Collectors.toList());
        data.accept(list);
    }

    public void filterByTelephone(boolean isReverse, Consumer<List<MemberCard>> data) {
        var list = members.stream().sorted((isReverse) ?
                Comparator.comparing(LibraryMember::getTelephone).reversed() :
                Comparator.comparing(LibraryMember::getTelephone)
        ).map(this::from).collect(Collectors.toList());
        System.out.println("IsRever telephone " +isReverse);
        data.accept(list);
    }

    public void searchInList(String word, Consumer<List<MemberCard>> data) {
        var list = members.stream().filter(x ->
                x.getFirstName().contains(word) || x.getLastName().contains(word)
        ).map(this::from).collect(Collectors.toList());
        data.accept(list);
    }

    public void cleanFilter(Consumer<List<MemberCard>> data) {
        data.accept(members.stream().map(this::from).collect(Collectors.toList()));
    }

    private MemberCard from(LibraryMember member){
        return new  MemberCard(
                member.getFirstName(),
                member.getLastName(),
                member.getTelephone(),
                member.getMemberId()
        );
    }
}
