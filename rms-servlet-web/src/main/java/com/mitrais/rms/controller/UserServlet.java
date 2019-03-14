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
import com.mitrais.rms.service.UserService;
import com.mitrais.rms.service.impl.UserServiceImpl;
import com.mitrais.rms.tools.UserValidator;
import com.mitrais.rms.tools.ValidatorResult;


@WebServlet("/users/*")
public class UserServlet extends AbstractController
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
    	
    
        String path = getTemplatePath(req.getServletPath()+req.getPathInfo());
        
        UserService userService = UserServiceImpl.getInstance();
   
      
        if ("/list".equalsIgnoreCase(req.getPathInfo())){
        	HttpSession session = req.getSession();
        	
        	
            UserDao userDao = UserDaoImpl.getInstance();
            List<User> users = userDao.findAll();
            req.setAttribute("users", users);
            req.setAttribute("active_user", session.getAttribute("userSession"));
            String warningMessage = (String)session.getAttribute("warningmessage");
            if(warningMessage != null) {
            	req.setAttribute("warningmessage", warningMessage);
            	session.removeAttribute("warningmessage");
            }
        }
        
        if ("/form".equalsIgnoreCase(req.getPathInfo())){
        	
        	Integer id = Integer.parseInt(req.getParameter("id")==null?"0":req.getParameter("id"));
        	User oldUser = new User();
        	Optional<User> user = Optional.of(oldUser);

            	if(id>0) {
            		 UserDao userDao = UserDaoImpl.getInstance();
                     user = userDao.find(id.longValue());
                     
            	}
        	
        	
        	req.setAttribute("user", user.get());
          //  req.setAttribute("text","Test");
        }
     
        
        if ("/delete".equalsIgnoreCase(req.getPathInfo())){
        	
        	userService.delete(req.getParameter("id"));
        	
        	if(!userService.queryResult()) {
        		HttpSession session = req.getSession();
        		session.setAttribute("warningmessage", userService.getWarningMessage());
        	}
        	redirectToUserList(req, resp);return;
        	
        	
        	
        	
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
        	
        	UserService userService = UserServiceImpl.getInstance();
        	userService.saveUpdate(id, username, password);
        	
        	if(userService.queryResult()) {
        		redirectToUserList(req, resp);return;
        	}
        	
        	 req.setAttribute("warningmessage",userService.getWarningMessage());
             req.setAttribute("user", userService.getUser());
             
        }
        
      
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
        
       
    }
    
    
    private void redirectToUserList(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		resp.sendRedirect(req.getContextPath() + "/users/list");
	}
    

}
