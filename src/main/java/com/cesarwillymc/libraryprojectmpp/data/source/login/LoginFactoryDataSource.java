package com.cesarwillymc.libraryprojectmpp.data.source.login;

import com.cesarwillymc.libraryprojectmpp.data.local.DataAccessDao;
import com.cesarwillymc.libraryprojectmpp.data.source.login.mapper.LoginMapper;

public class LoginFactoryDataSource {
    public static LoginDataSource getLoginDataSource(DataAccessDao access) {
        return new LoginRepository(access, new LoginMapper());
    }
}
