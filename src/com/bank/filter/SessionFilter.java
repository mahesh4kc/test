package com.bank.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import com.bank.util.BankConstant;
import com.bank.util.PropertyUtil;

public class SessionFilter implements Filter {

	private ArrayList<String> urlList;
	private int totalURLS;

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		setRequestEncoding(request);
		setResponseHeader(response);
		String url = request.getServletPath();
		boolean allowedRequest = false;
		try{
			if(PropertyUtil.getProperties() == null){
				PropertyUtil.loadPropertiesOutsideWar();
			}

			for(int i=0; i<totalURLS; i++) {
				if(url.contains(urlList.get(i))) {
					allowedRequest = true;
					break;
				}
			}

			if (!allowedRequest) {
				HttpSession session = request.getSession(false);
				if (null == session) {
					response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
					//request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
				}
			}
			chain.doFilter(req, res);
		}catch(IllegalStateException ex){
			response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
		}


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

	private void setResponseHeader(HttpServletResponse response){
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.     
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.     
		response.setDateHeader("Expires", 0); // Proxies.

		/**
		 * Set the default response content type and encoding
		 */
		response.setContentType("text/html; charset=" + BankConstant.CHARACTER_ENCODING);
		response.setCharacterEncoding(BankConstant.CHARACTER_ENCODING);

	}


	private void setRequestEncoding(HttpServletRequest request) throws UnsupportedEncodingException{

		/**
		 * Set the default request content type and encoding
		 */

		request.setCharacterEncoding(BankConstant.CHARACTER_ENCODING);




	}
}