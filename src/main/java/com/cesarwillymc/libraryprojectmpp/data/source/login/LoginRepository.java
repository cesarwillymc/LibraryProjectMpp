package com.cesarwillymc.libraryprojectmpp.data.source.login;

import com.cesarwillymc.libraryprojectmpp.data.local.DataAccessDao;
import com.cesarwillymc.libraryprojectmpp.data.source.login.mapper.LoginMapper;
import com.cesarwillymc.libraryprojectmpp.data.source.preference.Preference;
import com.cesarwillymc.libraryprojectmpp.domain.entities.TypeAuth;
import com.cesarwillymc.libraryprojectmpp.domain.entities.User;
import com.cesarwillymc.libraryprojectmpp.domain.exception.LoginException;
import com.cesarwillymc.libraryprojectmpp.domain.resource.Resource;

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
            Preference.TYPE_USER.setUser(data);
            return Resource.Sucess(mapper.dataToDomain(data));
        } catch (LoginException e) {
            return Resource.Error(e);
        }
    }

    @Override
    public Resource<Void> logout() {
        Preference.TYPE_USER.setUser(null);
        return Resource.Sucess(null);
    }

    @Override
    public Resource<TypeAuth> getAuth() {
        return Resource.Sucess(Preference.TYPE_USER.getTypeAuth());
    }

    @Override
    public Resource<Boolean> isLoggedIn() {
        return Resource.Sucess(Preference.TYPE_USER.getUser() == null);
    }
}
