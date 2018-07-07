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
<title><bean:message key="label.changePassword.title" /></title>
</head>
<body onload="bodyOnLoad('oldPassword');">
<html:form styleId="form1" action="changePassword"  method="POST">
<div align="center" class="shopDetails" ><bean:write name="loginForm" property="shopDetails"/> </div>
<div align="right">User : <bean:write name="loginForm" property="userLoggedIn"/> </div>

<jsp:include page="htmlError.jsp"></jsp:include>
<table WIDTH="100%">
	<!-- 
	<tr>
		<td WIDTH="80%"></td>
		<td WIDTH="10%"><label><bean:message key="label.loginID" />  </label></td>
		<td WIDTH="10%">
			<%--
			<html:text property="loginID" size="25" maxlength="15" styleId="loginID" onfocus="true"></html:text>
			--%>		
		</td>
	</tr>
	 -->
	<tr>
		<td WIDTH="80%"></td>
		<td WIDTH="10%"><label><bean:message key="label.oldPassword" /></label> </td>
		<td WIDTH="10%">
			<html:password property="oldPassword" size="25" maxlength="15" styleId="oldPassword"></html:password>		
		</td>
	</tr>
	<tr>
		<td WIDTH="80%"></td>
		<td WIDTH="10%"><label><bean:message key="label.newPassword" /></label> </td>
		<td WIDTH="10%">
			<html:password property="password" size="25" maxlength="15" styleId="password"></html:password>		
		</td>
	</tr>
	<tr>
		<td WIDTH="80%"></td>
		<td WIDTH="10%"><label><bean:message key="label.renenterPassword" /></label> </td>
		<td WIDTH="10%">
			<html:password property="duplicatePassword" size="25" maxlength="15" styleId="duplicatePassword"></html:password>		
		</td>
	</tr>
	<!-- 
	<tr>
		<td WIDTH="80%"></td>
		<td WIDTH="10%"><label><bean:message key="label.databaseName" /></label> </td>
		<td WIDTH="10%">
			<%-- 
			<html:text property="databaseName" size="25" maxlength="25" styleId="databaseName"></html:text>
			--%>		
		</td>
	</tr>
	 
	<tr>
		<td WIDTH="80%"></td>
		<td WIDTH="10%"><label><bean:message key="label.proprietor" /></label> </td>
		<td WIDTH="10%">
			<html:text property="userName" size="25" maxlength="25" styleId="userName"></html:text>		
		</td>
	</tr>
	-->
</table>
<div align="right">
	
	<html:submit property="method" ><bean:message key="button.apply" /></html:submit>	
	<html:submit property="method" ><bean:message key="button.clear" /></html:submit>
	<!-- 
	<html:submit property="method" ><bean:message key="button.create" /></html:submit>
	-->
	
	
</div>
</html:form>
</body>
</html>