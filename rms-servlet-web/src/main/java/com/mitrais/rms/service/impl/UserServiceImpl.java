package com.mitrais.rms.service.impl;

import javax.servlet.http.HttpServletRequest;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.model.User;
import com.mitrais.rms.service.UserService;
import com.mitrais.rms.tools.UserValidator;
import com.mitrais.rms.tools.ValidatorResult;

public class UserServiceImpl implements UserService{
	
	private String warningMessage;
	private Boolean queryResult;
	private User user;

	private static class SingletonHelper
    {
        private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    }

    public static UserService getInstance()
    {
        return SingletonHelper.INSTANCE;
    }
	
	@Override
	public void saveUpdate(String id, String username, String password) {
		queryResult = false;
		user = new User(Long.parseLong(id),username,password);
    	
    	boolean validInput = false;
    	
    	UserDao userDao = UserDaoImpl.getInstance();
    	
    	UserValidator userValidator = new UserValidator();
    	ValidatorResult validatorResult = UserValidator.validateEditedRecord(user, userDao);
    	
    	
    	// store user information
    	if(validatorResult.result) {
    		 
    		 
    		 
    		 if(user.getId()>0) {
    			 queryResult = userDao.update(user);

    		 }else {
    			 queryResult  = userDao.save(user);

    		 }
    		 
    		 if(queryResult) {
    		//	 redirectToUserList(req, resp);return;
    			 queryResult = true;
    		 }else {
    			 warningMessage = "Something problem when processing your request.";
    		 }
    		 
    	}else {
    		warningMessage = validatorResult.message;
    	}
    	
    
		
	}

	@Override
	public String getWarningMessage() {
		// TODO Auto-generated method stub
		return warningMessage;
	}

	@Override
	public Boolean queryResult() {
		// TODO Auto-generated method stub
		return queryResult;
	}

	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return user;
	}

	@Override
	public void delete(String id) {
		Integer userId = Integer.parseInt(id==null?"0":id);
		queryResult = false;
    	if(userId>0) {
   		 	UserDao userDao = UserDaoImpl.getInstance();
   		 	boolean delete = userDao.delete(userId.longValue());
   	
   		 	if(delete) {
   		 		queryResult = true;
   		 	}else {
   		 		warningMessage="Something problem when deleting user.";
   		 	}
   		 //	redirectToUserList(req, resp);return;
    	}else {
    		warningMessage = "Invalid user.";
    	}
		
	}

}
