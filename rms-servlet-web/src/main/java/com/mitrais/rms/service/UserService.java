package com.mitrais.rms.service;

import javax.servlet.http.HttpServletRequest;

import com.mitrais.rms.model.User;
import com.mitrais.rms.tools.ValidatorResult;

public interface UserService {
	public void saveUpdate(String id,String username,String password);
	public void delete(String id);
	public String getWarningMessage();
	public Boolean queryResult();
	public User getUser();
}
