package com.mitrais.rms.controller;

import java.io.IOException;
import java.util.List;
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


@WebServlet("/users/*")
public class UserServlet extends AbstractController
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
    	
    	HttpSession session = req.getSession();
    	User userSession = (User) session.getAttribute("userSession");
    	if(userSession==null) {
    		redirectToLoginPage(req,resp);return;
    	}
    	
        String path = getTemplatePath(req.getServletPath()+req.getPathInfo());
   
      
        if ("/list".equalsIgnoreCase(req.getPathInfo())){
            UserDao userDao = UserDaoImpl.getInstance();
            List<User> users = userDao.findAll();
            req.setAttribute("users", users);
        }
        
        if ("/form".equalsIgnoreCase(req.getPathInfo())){
        	
        	Integer id = Integer.parseInt(req.getParameter("id")==null?"0":req.getParameter("id"));
        	User oldUser = new User(0L,"","");
        	Optional<User> user = Optional.of(oldUser);
        	
        	
    
        		
            	if(id>0) {
            		 UserDao userDao = UserDaoImpl.getInstance();
                     user = userDao.find(id.longValue());
                     
            	}
        	
        	
        	req.setAttribute("user", user.get());
          //  req.setAttribute("text","Test");
        }
     
        
        if ("/delete".equalsIgnoreCase(req.getPathInfo())){
        	
        	Integer id = Integer.parseInt(req.getParameter("id")==null?"0":req.getParameter("id"));
        	
        	if(id>0) {
       		 	UserDao userDao = UserDaoImpl.getInstance();
       		 	boolean delete = userDao.delete(new User(id.longValue(),"",""));
       		 	redirectToUserList(req, resp);return;
        	}
        }
        

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }



	
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String path = getTemplatePath(req.getServletPath()+req.getPathInfo());
       
        boolean saveSuccess = false;
        if ("/form".equalsIgnoreCase(req.getPathInfo())){
        	String username = req.getParameter("username");
        	String password = req.getParameter("userpass");	
        	String id = req.getParameter("id");	
        	User newUser = new User(Long.parseLong(id),username,password);
        	
        	String warningMessage = "";
        	boolean validInput = false;
        	
        	UserDao userDao = UserDaoImpl.getInstance();
        	
        	UserValidator userValidator = new UserValidator();
        	ValidatorResult validatorResult = UserValidator.validateEditedRecord(newUser, userDao);
        	
        	
        	// store user information
        	if(validatorResult.result) {
        		 
        		 boolean queryResult = false;
        		 
        		 if(newUser.getId()>0) {
        			 queryResult = userDao.update(newUser);

        		 }else {
        			 queryResult  = userDao.save(newUser);

        		 }
        		 
        		 if(queryResult) {
        			 redirectToUserList(req, resp);return;
        		 }else {
        			 warningMessage = "Something problem when processing your request.";
        		 }
        		 
        	}else {
        		warningMessage = validatorResult.message;
        	}
        	
            req.setAttribute("warningmessage",warningMessage);
            req.setAttribute("user", newUser);
        }
        
      
        	 RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
             requestDispatcher.forward(req, resp);
        
       
    }
    
    
    public static void redirectToUserList(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		resp.sendRedirect(req.getContextPath() + "/users/list");
	}
    
    public static void redirectToLoginPage(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		resp.sendRedirect(req.getContextPath() + "/login");
	}
}
