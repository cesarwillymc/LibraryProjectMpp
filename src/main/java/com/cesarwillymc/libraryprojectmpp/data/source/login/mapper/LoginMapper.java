package com.cesarwillymc.libraryprojectmpp.data.source.login.mapper;

import com.cesarwillymc.libraryprojectmpp.data.source.account.entity.AddressResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.login.entity.UserResponse;
import com.cesarwillymc.libraryprojectmpp.data.utils.mapper.Mapper;
import com.cesarwillymc.libraryprojectmpp.domain.entities.Address;
import com.cesarwillymc.libraryprojectmpp.domain.entities.User;

public class LoginMapper extends Mapper<User, UserResponse> {
    @Override
    public UserResponse domainToData(User data) {
        return new UserResponse(
                data.getFirstName(),
                data.getLastName(),
                data.getTelephone(),
                from(data.getAddress()),
                data.getId(),
                data.getPassword(),
                data.getAuthorization(),
                data.getImageUrl()
        );
    }

    private AddressResponse from(Address address) {
        return new AddressResponse(
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZip()
        );
    }

    private Address from(AddressResponse address) {
        return new Address(
                address.street(),
                address.city(),
                address.state(),
                address.zip()
        );
    }

    @Override
    public User dataToDomain(UserResponse data) {
        return new User(
                data.getFirstName(),
                data.getLastName(),
                data.getTelephone(),
                from(data.getAddress()),
                data.getId(),
                data.getPassword(),
                data.getAuthorization(),
                data.getImageUrl()
        );
    }
}
