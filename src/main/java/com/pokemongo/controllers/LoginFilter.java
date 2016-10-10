package com.pokemongo.controllers;

import com.pokemongo.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    
    private static Logger logger = LogManager.getLogger(LoginFilter.class);
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        
        logger.debug("Filtering user: " + session.getAttribute("loggedInUser") + " with path " + req.getRequestURI());
        
        if (session.getAttribute("loggedInUser") == null && req.getRequestURI().endsWith("/user/")) {
            
            logger.debug("User is not logged in, redirecting to home page.");
            
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect(req.getContextPath() + "/");
        } else {
            
            logger.debug("User is authenticated, proceeding...");
            
            chain.doFilter(request, response);
        }
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }
    
    @Override
    public void destroy() {
        
    }
}
