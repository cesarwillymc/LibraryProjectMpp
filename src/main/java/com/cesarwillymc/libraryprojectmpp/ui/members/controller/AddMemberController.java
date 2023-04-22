package com.cesarwillymc.libraryprojectmpp.ui.members.controller;

import com.cesarwillymc.libraryprojectmpp.domain.entities.Address;
import com.cesarwillymc.libraryprojectmpp.domain.entities.LibraryMember;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;
import com.cesarwillymc.libraryprojectmpp.usecase.member.AddMemberUseCase;

public class AddMemberController {
    AddMemberUseCase addMemberUseCase;

    public AddMemberController(AddMemberUseCase addMemberUseCase) {
        this.addMemberUseCase = addMemberUseCase;
    }

    public Resource<Void> saveMember(
            String firstName,
            String lasName,
            String telephone,
            String documentNumber,
            String street,
            String city,
            String state,
            String zipCode
    ) {
        return addMemberUseCase.run(new LibraryMember(
                documentNumber, firstName, lasName, telephone, new Address(street, city, state, zipCode)
        ));
    }
}
