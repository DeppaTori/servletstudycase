package com.mitrais.rms.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.model.User;
import com.mitrais.rms.tools.UserValidator;
import com.mitrais.rms.tools.ValidatorResult;

public class TestJunit {
	
	@Test
	public void testSetup() {
		String str = "I am learning";
		assertEquals("I am learning",str);
	}
	
	@Test
	public void testValidate() {
		UserValidator userValidator = new UserValidator();
		User user = new User(0L,"Alan","123");
		ValidatorResult resultExpected = new ValidatorResult();
		resultExpected.message = "Username already taken";
		resultExpected.result = false;
		ValidatorResult result = UserValidator.validateEditedRecord(user,  UserDaoImpl.getInstance());
		assertEquals(resultExpected.message,result.message);
		
	}
}
