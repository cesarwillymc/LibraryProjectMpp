package com.cesarwillymc.libraryprojectmpp.ui.di;

import com.cesarwillymc.libraryprojectmpp.data.local.DataAccessFacade;
import com.cesarwillymc.libraryprojectmpp.data.source.account.MemberFactoryDataSource;
import com.cesarwillymc.libraryprojectmpp.data.source.customer.BookFactoryDataSource;
import com.cesarwillymc.libraryprojectmpp.data.source.login.LoginFactoryDataSource;
import com.cesarwillymc.libraryprojectmpp.data.source.memberRecord.MemberRecordFactoryDataSource;
import com.cesarwillymc.libraryprojectmpp.ui.books.controller.AddBookController;
import com.cesarwillymc.libraryprojectmpp.ui.books.controller.BookController;
import com.cesarwillymc.libraryprojectmpp.ui.books.controller.BookDetailController;
import com.cesarwillymc.libraryprojectmpp.ui.home.controller.HomeController;
import com.cesarwillymc.libraryprojectmpp.ui.login.controller.LoginController;
import com.cesarwillymc.libraryprojectmpp.ui.login.controller.LoginControllerImpl;
import com.cesarwillymc.libraryprojectmpp.ui.members.controller.AddMemberController;
import com.cesarwillymc.libraryprojectmpp.ui.members.controller.DetailMemberController;
import com.cesarwillymc.libraryprojectmpp.ui.members.controller.MemberController;
import com.cesarwillymc.libraryprojectmpp.ui.profile.controller.ProfileController;
import com.cesarwillymc.libraryprojectmpp.usecase.book.AddBookUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.book.GetAllBooksUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.book.UpdateBookUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.member.AddMemberUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.member.GetAllMembersUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.member.GetMemberByIdUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.memberRecord.GetAllMembersRecordUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.memberRecord.GetMemberRecordByBookIdUseCase;
import com.cesarwillymc.libraryprojectmpp.usecase.memberRecord.GetMemberRecordByUserIdUseCase;
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
    public static MemberController createMemberController(){
        var dao = new DataAccessFacade();
        var useCase = new GetAllMembersUseCase(MemberFactoryDataSource.getMemberDataSource(dao));

        return new MemberController(useCase);
    }
    public static AddMemberController createAddMemberController(){
        var dao = new DataAccessFacade();
        var useCase = new AddMemberUseCase(MemberFactoryDataSource.getMemberDataSource(dao));

        return new AddMemberController(useCase);
    }
    public static DetailMemberController createDetailMemberController(){
        var dao = new DataAccessFacade();
        var useCase = new GetMemberByIdUseCase(MemberFactoryDataSource.getMemberDataSource(dao));
        var useCase2 = new GetMemberRecordByUserIdUseCase(MemberRecordFactoryDataSource.getMemberRecordDataSource(dao));

        return new DetailMemberController(useCase,useCase2);
    }
    public static BookController createBookController(){
        var dao = new DataAccessFacade();
        var useCase = new GetAllBooksUseCase(BookFactoryDataSource.getBookDataSource(dao));

        return new BookController(useCase);
    }
    public static AddBookController createAddBookController(){
        var dao = new DataAccessFacade();
        var useCase = new AddBookUseCase(BookFactoryDataSource.getBookDataSource(dao));

        return new AddBookController(useCase);
    }

    public static BookDetailController createDetailBookController(){
        var dao = new DataAccessFacade();
        var useCase = new UpdateBookUseCase(BookFactoryDataSource.getBookDataSource(dao));
        var useCase2 = new GetMemberRecordByBookIdUseCase(MemberRecordFactoryDataSource.getMemberRecordDataSource(dao));

        return new BookDetailController(useCase,useCase2);
    }
}
