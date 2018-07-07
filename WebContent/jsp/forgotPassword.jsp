<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
function bodyOnLoad(){
	document.getElementById("loginID").focus();
}
</script>
<jsp:include page="includeCSS.jsp"></jsp:include>
<jsp:include page="includeJS.jsp"></jsp:include>
<title><bean:message key="label.forgotpassword.title" /></title>
</head>
<body onload="bodyOnLoad();">
<html:form styleId="form1" action="forgot"  method="POST">
<jsp:include page="htmlError.jsp"></jsp:include>

<table WIDTH="100%">
	<tr>
		<td WIDTH="70%"></td>
		<td WIDTH="20%"><label><bean:message key="label.loginID" /><span class="mandatory">*</span> </label></td>
		<td WIDTH="10%">
			<html:text property="loginID" size="25" maxlength="15" styleId="loginID" onfocus="true"></html:text>		
		</td>
	</tr>
	<tr>
		<td WIDTH="70%"></td>
		<td WIDTH="20%"><label><bean:message key="label.registration.mailId" /><span class="mandatory">*</span> </label></td>
		<td WIDTH="10%">
			<html:text property="emailID" size="25" maxlength="50" styleId="emailID"></html:text>		
		</td>
	</tr>
</table>
<div align="right">
	<html:submit property="method" ><bean:message key="button.forgot" /></html:submit>
	<html:submit property="method" ><bean:message key="button.clear" /></html:submit>
	&nbsp;
</div>
&nbsp;&nbsp;
<div align="right">
<html:link action="/login.do?action=logout&method=CLEAR">Back to Login Page</html:link>
</div>
</html:form>
</body>
</html>