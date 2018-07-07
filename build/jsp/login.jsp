<%@page import="com.bank.util.BankConstant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="googleSearchKeywords.jsp"></jsp:include>
<jsp:include page="includeCSS.jsp"></jsp:include>
<jsp:include page="includeJS.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function() {
	$("#form1").validationEngine()
});
function bodyOnLoad(){
	document.getElementById("loginID").focus();
}
</script>
<title><bean:message key="label.login.title" /></title>
</head>
<body onload="bodyOnLoad();ResetAllValue();">
<jsp:include page="htmlError.jsp"></jsp:include>
<html:form styleId="form1" action="login"  method="POST">


<table style="width: 100%; ">
<tr >
<td style="width: 50%; ">
<jsp:include page='/include/html/interestCalculator.html'></jsp:include>
</td>

<td style="width: 50%; ">

<!-- Login Screen Starts -->
<div style="width: 75%; " align="right" >
<div align="right">	&nbsp;</div><!-- adding new empty line -->
<div align="right">	&nbsp;</div><!-- adding new empty line -->
<div align="center" style="width: 50%; "> <label><B>Registered User</B></label> </div>
<table  >
	<tr>
		<td WIDTH="80%"></td>
		<td WIDTH="10%"><label><bean:message key="label.loginID" />  </label></td>
		<td WIDTH="10%">
			<html:text property="loginID" size="25" maxlength="15" styleId="loginID" 
			  
			onfocus="true"></html:text>		
		</td>
	</tr>
	<tr>
		<td WIDTH="80%"></td>
		<td WIDTH="10%"><label><bean:message key="label.password" /></label> </td>
		<td WIDTH="10%">
			<html:password property="password" size="25" maxlength="15" 
			 styleId="password"></html:password>		
		</td>
	</tr>
</table>
<div align="right" style="width: 86%;">
	<html:submit property="method" ><bean:message key="button.login" /></html:submit>
	<html:submit property="method" ><bean:message key="button.clear" /></html:submit>&nbsp;
</div>
<div align="right"  style="width: 86%;">
	<html:link action="/registration.do?method=CLEAR">New User</html:link>	&nbsp;
	<html:link action="/forgot.do?method=CLEAR">Forgot Password</html:link>
</div>
<div align="right">	&nbsp;</div>
<div align="right">	<label>To see demo, use below Login ID and Password</label></div>
<div align="right">	<label>Login ID:jewelbankers &nbsp;&nbsp;Password:jewelbankers</label>	&nbsp;</div>
</div>
<!-- Login Screen Ends -->

</table>
</html:form>
<table width="100%" >
<tr></tr>
<tr><td style="font: bold 25px MS Sans Serif;"><B><label>Play the Demo for Jewelbankers</label></B></td></tr>
	<tr>
		
		<td><video width="420" height="320" controls>
  <source src="<bean:message key="videoPath" />" type="video/mp4">
  <object data="<bean:message key="videoPath" />" width="420" height="320">
    <embed src="<bean:message key="videoPath" />" width="420" height="320">
  </object> 
</video>

		</td>
		<td>
		<!-- 
		<h1><a href="./jsp/tabletree.htm" >
	<p>Vyasarpadi Team lifted </p>
	<p>Seervi Samaj Cup</p>
	<p> 2nd consecutive time</p>
	<p> at Utthukotai on Feb, 12 2013</p></a></h1>
	 -->
	</td>
	</tr>
	</table>
</body>
</html>