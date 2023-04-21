package com.cesarwillymc.libraryprojectmpp.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data.local.DataAccessDao;
import data.local.DataAccessFacade;
import data.source.login.entity.UserResponse;
import domain.exception.LoginException;

public class SystemController implements ControllerInterface {
	public static TypeAuth currentAuth = null;
	
	public void login(String id, String password) throws LoginException {
		DataAccessDao da = new DataAccessFacade();
		HashMap<String, UserResponse> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		
	}
	@Override
	public List<String> allMemberIds() {
		DataAccessDao da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccessDao da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}
	
	
}
