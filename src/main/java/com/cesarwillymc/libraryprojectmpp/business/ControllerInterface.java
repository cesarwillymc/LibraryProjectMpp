package com.cesarwillymc.libraryprojectmpp.business;


import com.cesarwillymc.libraryprojectmpp.domain.exception.LoginException;

import java.util.List;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	
}
