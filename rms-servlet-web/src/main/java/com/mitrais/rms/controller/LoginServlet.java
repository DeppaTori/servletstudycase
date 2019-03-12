package com.mitrais.rms.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.model.User;
import com.mitrais.rms.tools.UserValidator;
import com.mitrais.rms.tools.ValidatorResult;

@WebServlet("/login")
public class LoginServlet extends AbstractController
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String path = getTemplatePath(req.getServletPath());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
       // super.doPost(req, resp);
    	String username = req.getParameter("username");
    	String password = req.getParameter("userpass");
    	UserDao userDao = UserDaoImpl.getInstance();
    	Optional<User> findUser=  userDao.findByUserName(username);
    	
    	UserValidator userValidator = new UserValidator();
    	ValidatorResult validatorResult = userValidator.validate(findUser,password);
    	
    	if(validatorResult.result) {
    		HttpSession session = req.getSession();
    		session.setAttribute("userSession", findUser.get());
    		resp.sendRedirect(req.getContextPath() + "/");
    		return;
        	
    	}
    	
    	req.setAttribute("message",validatorResult.message);
    	
    	String path = getTemplatePath(req.getServletPath());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }
}
