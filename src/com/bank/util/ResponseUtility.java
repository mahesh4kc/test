package com.bank.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public abstract class ResponseUtility{

	 public void setResponseFormat(HttpServletResponse response,ByteArrayOutputStream baos			 
			 ,String contentType, String fileName) throws IOException{
		 try {
		  // setting some response headers
	        response.setHeader("Expires", "0");
	        response.setHeader("Cache-Control",
	            "must-revalidate, post-check=0, pre-check=0");
	        response.setHeader("Pragma", "public");
	        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
	        // setting the content type
	        response.setContentType(contentType+"; charset=UTF-8");
	        
	        /**
			 * Set the default response content type and encoding
			 */
			//response.setContentType("text/html; charset=" + BankConstant.CHARACTER_ENCODING);
			response.setCharacterEncoding(BankConstant.CHARACTER_ENCODING);

	        // the contentlength
	        response.setContentLength(baos.size());
	       
	        // write ByteArrayOutputStream to the ServletOutputStream
	        OutputStream os = response.getOutputStream();
	        
	        baos.writeTo(os);
	        os.flush();
	        os.close();
			 } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 }
}
