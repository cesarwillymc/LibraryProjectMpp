package com.cesarwillymc.libraryprojectmpp.ui.di;

import com.cesarwillymc.libraryprojectmpp.data.local.DataAccessFacade;
import com.cesarwillymc.libraryprojectmpp.data.source.account.MemberFactoryDataSource;
import com.cesarwillymc.libraryprojectmpp.data.source.login.LoginFactoryDataSource;
import com.cesarwillymc.libraryprojectmpp.data.source.memberRecord.MemberRecordFactoryDataSource;
import com.cesarwillymc.libraryprojectmpp.ui.home.controller.HomeController;
import com.cesarwillymc.libraryprojectmpp.ui.login.controller.LoginController;
import com.cesarwillymc.libraryprojectmpp.ui.login.controller.LoginControllerImpl;
import com.cesarwillymc.libraryprojectmpp.ui.profile.controller.ProfileController;
import com.cesarwillymc.libraryprojectmpp.usecase.member.GetAllMembersUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.memberRecord.GetAllMembersRecordUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.user.GetUserLoggedUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.user.SignInUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.user.SignOutUseCase;

public class DIControllers {
    public static LoginController createLoginController() {
        return new LoginControllerImpl(new SignInUseCase(LoginFactoryDataSource.getLoginDataSource(new DataAccessFacade())));
    }
    public static HomeController createHomeController() {
        var dao = new DataAccessFacade();
        var useCase = new GetAllMembersUseCase(MemberFactoryDataSource.getMemberDataSource(dao));
        var useCase2 = new GetAllMembersRecordUseCase(MemberRecordFactoryDataSource.getMemberRecordDataSource(dao));

        return new HomeController(useCase,useCase2);
    }

    public static ProfileController createProfileController(){
        var dao = new DataAccessFacade();
        var useCase1= new GetUserLoggedUseCase(LoginFactoryDataSource.getLoginDataSource(dao));
        var useCase2= new SignOutUseCase(LoginFactoryDataSource.getLoginDataSource(dao));
        return new ProfileController(useCase1,useCase2);
    }
}
