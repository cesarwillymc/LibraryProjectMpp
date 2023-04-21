package com.cesarwillymc.libraryprojectmpp.data.source.account.mapper;

import com.cesarwillymc.libraryprojectmpp.data.source.account.entity.AddressResponse;
import com.cesarwillymc.libraryprojectmpp.data.utils.mapper.Mapper;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Address;

public class AddressMapper extends Mapper<Address, AddressResponse> {
    @Override
    public AddressResponse domainToData(Address data) {
        return new AddressResponse(
                data.getStreet(),
                data.getCity(),
                data.getState(),
                data.getZip()
        );
    }

    @Override
    public Address dataToDomain(AddressResponse data) {
        return new Address(
                data.street(),
                data.city(),
                data.state(),
                data.zip()
        );
    }
}
