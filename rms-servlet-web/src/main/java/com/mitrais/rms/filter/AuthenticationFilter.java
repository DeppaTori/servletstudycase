package com.mitrais.rms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter{

	private ServletContext context;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		boolean isStaticResource = ((HttpServletRequest) request).getRequestURI().startsWith("/resources/");
        boolean isLoggedIn = req.getSession().getAttribute("userSession") != null;

        if(isLoggedIn || isStaticResource){
        	chain.doFilter(request, response);
        } else {
            req.getRequestDispatcher("/login")
                    .forward(request, response);
        }
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
