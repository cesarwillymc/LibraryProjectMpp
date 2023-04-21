package com.cesarwillymc.libraryprojectmpp.data.source.login;

import com.cesarwillymc.libraryprojectmpp.data.local.DataAccessDao;
import com.cesarwillymc.libraryprojectmpp.data.source.login.mapper.LoginMapper;
import com.cesarwillymc.libraryprojectmpp.domain.entities.User;
import com.cesarwillymc.libraryprojectmpp.domain.exception.LoginException;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;

import java.util.Optional;

class LoginRepository implements LoginDataSource {
    private final DataAccessDao dataAccess;
    private final LoginMapper mapper;

    LoginRepository(DataAccessDao dataAccess, LoginMapper mapper) {
        this.dataAccess = dataAccess;
        this.mapper = mapper;
    }

    @Override
    public Resource<User> login(String id, String password) {
        try {
            var data = dataAccess.signIn(id, password);
            dataAccess.saveLoggedUser(data);
            return Resource.Sucess(mapper.dataToDomain(data));
        } catch (LoginException e) {
            return Resource.Error(e);
        }
    }

    @Override
    public Resource<Void> logout() {
        dataAccess.signOut();
        return Resource.Sucess(null);
    }

    @Override
    public Resource<User> getAuth() {
        var data = Optional.ofNullable(dataAccess.getUserLogged()).map(mapper::dataToDomain);
        if (data.isPresent())
            return Resource.Sucess(mapper.dataToDomain(dataAccess.getUserLogged()));
        else return Resource.Sucess(null);
    }

    @Override
    public Resource<Boolean> isLoggedIn() {
        return Resource.Sucess(Optional.ofNullable(dataAccess.getUserLogged()).map(mapper::dataToDomain).isPresent());
    }
}
