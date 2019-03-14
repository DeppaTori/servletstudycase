package com.mitrais.rms.service;

import javax.servlet.http.HttpServletRequest;

import com.mitrais.rms.tools.ValidatorResult;

public interface LoginService {
	public void run(String username,String password,HttpServletRequest request);
	public ValidatorResult getResult();
}
