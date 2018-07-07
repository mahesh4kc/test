<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><bean:message key="label.backUpandRestoreMySql.backUp" /></title>
</head>
<body onload="">
<div align="center" class="shopDetails" ><bean:write name="backUpandRestoreMySqlForm" property="shopDetails"/> </div>
<div align="right">User : <bean:write name="backUpandRestoreMySqlForm" property="userLoggedIn"/> </div>
<div align="center">	
<html:link action="/fileDownloadAction.do?method=DOWNLOAD">
<bean:message key="label.backUpandRestoreMySql.backUp" />
</html:link>
	<html:form action="fileDownloadAction" method="post" enctype="multipart/form-data">
	
	<!-- 
	<bean:message key="label.backUpandRestoreMySql.file" /><html:file property="file" /> <br/>
		<html:submit property="method" ><bean:message key="button.upload" /></html:submit>
	 -->
	 </html:form>
</div>
</body>
</html>