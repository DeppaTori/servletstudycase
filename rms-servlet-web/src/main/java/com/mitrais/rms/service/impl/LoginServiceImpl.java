package com.mitrais.rms.service.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.model.User;
import com.mitrais.rms.service.LoginService;
import com.mitrais.rms.tools.UserValidator;
import com.mitrais.rms.tools.ValidatorResult;

public class LoginServiceImpl implements LoginService{
	
	private ValidatorResult validatorResult;

	public static class SingletonHelper{
		private static final LoginServiceImpl INSTANCE = new LoginServiceImpl();
	}
	
	public static LoginService getInstance() {
		return SingletonHelper.INSTANCE;
	}

	@Override
	public void run(String username, String password,HttpServletRequest request) {
		UserDao userDao = UserDaoImpl.getInstance();
    	Optional<User> findUser=  userDao.findByUserName(username);
    	
    	UserValidator userValidator = new UserValidator();
    	validatorResult = userValidator.validate(findUser,password);
    	
    	if(validatorResult.result) {
    		HttpSession session = request.getSession();
    		session.setAttribute("userSession", findUser.get());
    		//resp.sendRedirect(req.getContextPath() + "/");
    
        	
    	}
	}

	@Override
	public ValidatorResult getResult() {
		// TODO Auto-generated method stub
		return validatorResult;
	}

}
