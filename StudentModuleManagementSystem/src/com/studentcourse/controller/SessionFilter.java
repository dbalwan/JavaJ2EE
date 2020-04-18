package com.studentcourse.controller;

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

/**
 * Servlet Filter implementation class SessionFilter
 */
@WebFilter("/SessionFilter")
public class SessionFilter implements Filter {

	private ServletContext context;
    /**
     * Default constructor. 
     */
    public SessionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		((HttpServletResponse)response).setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		((HttpServletResponse)response).setHeader("Pragma", "no-cache"); // HTTP 1.0.
		((HttpServletResponse)response).setDateHeader("Expires", 0); // Proxies.
	    HttpSession session = ((HttpServletRequest)request).getSession(false);
	    if (session == null) {   //checking whether the session exists
            ((HttpServletResponse)response).sendRedirect(
            		((HttpServletRequest)request).getContextPath()+"/logout/logout.html");
	    } else {
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
	}

}
