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
import com.mitrais.rms.service.LoginService;
import com.mitrais.rms.service.impl.LoginServiceImpl;
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

    	LoginService loginService = LoginServiceImpl.getInstance();
    	loginService.run(req.getParameter("username"), req.getParameter("userpass"), req);
    	
    	if(loginService.getResult().result) {
    		resp.sendRedirect(req.getContextPath() + "/");
    		return;
    	}
    	
    	req.setAttribute("message",loginService.getResult().message);
    	
    	String path = getTemplatePath(req.getServletPath());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }
}
