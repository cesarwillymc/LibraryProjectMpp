package com.cesarwillymc.libraryprojectmpp.data.source.login.mapper;

import com.cesarwillymc.libraryprojectmpp.data.source.login.entity.EmployeeResponse;
import com.cesarwillymc.libraryprojectmpp.data.source.mapper.Mapper;
import com.cesarwillymc.libraryprojectmpp.domain.entities.User;

public class LoginMapper extends Mapper<User, EmployeeResponse> {
    @Override
    public EmployeeResponse domainToData(User data) {
        return new EmployeeResponse(
                data.id(),
                data.password(),
                data.authorization()
        );
    }

    @Override
    public User dataToDomain(EmployeeResponse data) {
        return new User(data.id(), null, data.authorization());
    }
}
