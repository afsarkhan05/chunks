package com.bigdata.chunks.controller;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;



public class AuthorizedAccessFilter implements Filter
{
	protected FilterConfig filterConfig = null;
	private ActionErrors errors;
	private String[] roleNames;
	private String onErrorUrl;
	private String loginPath;
	private String loginPage;
	private String unsecurePages;
	private String securePages;
	private static String isResource;
	private HttpSession session;
	HttpServletRequest req;
	HttpServletResponse res;

	public void init(FilterConfig filterConfig) throws ServletException 
	{
		this.filterConfig = filterConfig;
		System.out.println("Filter initialized successfully");

		onErrorUrl = filterConfig.getInitParameter("onError");
		loginPath = filterConfig.getInitParameter("loginPath");
		loginPage = filterConfig.getInitParameter("loginPage");
		unsecurePages = filterConfig.getInitParameter("unsecurePages");
		isResource =  filterConfig.getInitParameter("isResource");
		securePages =  filterConfig.getInitParameter("securePages");
		
		if (onErrorUrl == null || "".equals(onErrorUrl))
		{
			onErrorUrl = "/home.jsp";
		}

	}

	public void destroy()
	{
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws java.io.IOException, ServletException
	{



		req = (HttpServletRequest) request;
		res = (HttpServletResponse) response;
		errors = new ActionErrors();

		try{
			session = req.getSession(true);
			String servletPath = req.getServletPath();
			
			System.out.println("Auth Filter:: loginPath "+ loginPath);
			System.out.println("Auth Filter:: loginPage "+ loginPage);
			System.out.println("Auth Filter:: servletPath "+ servletPath);
			String sessionUser = (String) session.getAttribute("userName");

			System.out.println("User is Session is::: "+ sessionUser);
			if(isResource(servletPath)){
				if(isUnsecurePage(unsecurePages,servletPath)){
					chain.doFilter(req, res);
					return;
				}else if(null != sessionUser){ 
					//user is logged in to the session
					chain.doFilter(req, res);
				}else{  
					session.setAttribute("togoURL", servletPath);
					req.setAttribute(Globals.ERROR_KEY, errors);
					req.getRequestDispatcher(onErrorUrl).forward(req, res);	
				}  
			}else{
				req.setAttribute(Globals.ERROR_KEY, errors);
				req.getRequestDispatcher(onErrorUrl).forward(req, res);	
			}

		}catch (Exception e){
			System.out.println("Error "+e);	
		}
	}

	private boolean isUnsecurePage(String unsecurePages2, String servletPath) {

		boolean isUnsecurePage = false;
		if(servletPath.contains(securePages)){
			isUnsecurePage = false; 
		}else if((unsecurePages !=null && unsecurePages.indexOf(servletPath) != -1) || (servletPath.lastIndexOf(".") != -1 && unsecurePages.indexOf(servletPath.substring(servletPath.lastIndexOf("."))) != -1)){
			isUnsecurePage = true; 
		}
		
		return isUnsecurePage;
		
	}

	public static boolean isResource(String url)
	{
		
		boolean isValidResource = false;
		if( (url.lastIndexOf(".") != -1 && isResource.indexOf(url.substring(url.lastIndexOf("."))) != -1) || url.endsWith("Servlet") )
		{
			isValidResource = true;
		}

		return isValidResource;

	}


}
