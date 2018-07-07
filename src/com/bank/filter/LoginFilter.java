package com.bank.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{


	private FilterConfig filterConfig;
	private ArrayList<String> urlList;
    private int totalURLS;
	 public void destroy() {
	    }
	 
	  public void init(FilterConfig config) throws ServletException {
	        String urls = config.getInitParameter("avoid-urls");
	        StringTokenizer token = new StringTokenizer(urls, ",");
	 
	        urlList = new ArrayList<String>();
	 
	        while (token.hasMoreTokens()) {
	            urlList.add(token.nextToken());
	 
	        }
	        totalURLS = urlList.size();
	    }
	  public void doFilter (ServletRequest req,
	             ServletResponse res,
	             FilterChain chain)
	  {
	        HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;
	        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.     
	        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.     
	        response.setDateHeader("Expires", 0); // Proxies.  
	        String url = request.getServletPath();
	        boolean allowedRequest = false;
	        
	   	   try
	   	    {
	        for(int i=0; i<totalURLS; i++) {
	            if(url.contains(urlList.get(i))) {
	                allowedRequest = true;
	                break;
	            }
	        }
	 
	            if (!allowedRequest) {
	                HttpSession session = request.getSession(false);
	                if (null == session) {
	                	request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
	                    //response.sendRedirect("index.jsp");
	                }
	            }else{
	            	chain.doFilter(req, res);
	            }
	  } catch (ServletException se) {
	      System.out.println ("ServletException raised in SimpleFilter");
	  } catch (IOException io) {
	      System.out.println ("IOException raised in SimpleFilter");
	     }
	  /*    
	      
	    	 HttpServletRequest httpRequest = (HttpServletRequest)request;
			  System.out.println("doFilter method of LoginFilter");
				HttpSession session = httpRequest.getSession();
					
	     
	      if(session.getAttribute("loggedInUser") == null ){
	    	  HttpServletResponse httpResponse = (HttpServletResponse)response;
	    	  httpRequest.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
	    	 // httpResponse.sendRedirect("login.jsp");
	      }else{
	    	  chain.doFilter (request, response);
	      }

	    } catch (IOException io) {
	      System.out.println ("IOException raised in SimpleFilter");
	    */
	  }

	  public FilterConfig getFilterConfig()
	  {
	    return this.filterConfig;
	  }

	  public void setFilterConfig (FilterConfig filterConfig)
	  {
	    this.filterConfig = filterConfig;
	  }

	

}
