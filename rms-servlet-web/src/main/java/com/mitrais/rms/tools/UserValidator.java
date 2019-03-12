package com.mitrais.rms.tools;

import java.util.Optional;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.model.User;

public class UserValidator {
	
	public static ValidatorResult validate(Optional<User> opUser,String password) {
		//String message = "";
		ValidatorResult validatorResult = new ValidatorResult();
		
		if(opUser.isPresent()) {
    		User user = opUser.get();
    		if(user.getPassword().equals(password)) {
    			
    			validatorResult.message = "Valid user";
    			validatorResult.result = true;
    		}else {
    			
    			validatorResult.message = "Username or password is incorret";
    		}
    	}else {
    	
    		//validatorResult.message = "Username not found";
    		validatorResult.message = "Username or password is incorret";
    	}
		
		return validatorResult;
	}
	
	public static ValidatorResult validateEditedRecord(User user,UserDao userDao) {
		ValidatorResult validatorResult = new ValidatorResult();
		
		if(user.getUserName().length() < 3) {
    		validatorResult.message = "Username minimum 3 characters";
    	}else if(user.getPassword().length() < 3) {
    		validatorResult.message = "Password minimum 3 characters";
    	}else{
    		
    		
    		Optional<User> userExist = userDao.findByUserName(user.getUserName());
    		//check if username is not registered yet.
    		if(userExist.isPresent()) {
    			
    			if(!userExist.get().getId().equals(user.getId())) {
    				validatorResult.message = "Username already taken";
    			}else {
    				validatorResult.message = "Passed";
        			validatorResult.result = true;
    			}
    			
    		}else {
    			validatorResult.message = "Passed";
    			validatorResult.result = true;
    		}
    		
    	}
		return validatorResult;

	}
}
