<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="includeJS.jsp"></jsp:include>
<script type="text/javascript">
function bodyOnLoad(){
	document.getElementById("loginID").focus();
}
	
$(document).ready(function() {
	$("#form1").validationEngine()
});


</script>	
<jsp:include page="includeCSS.jsp"></jsp:include>

<title><bean:message key="label.registration.title" /></title>
</head>
<body onload="bodyOnLoad();">
<html:form styleId="form1" action="registration"   method="POST">
<jsp:include page="htmlError.jsp"></jsp:include>

<table WIDTH="86%">
	<tr>
		
		<td WIDTH="80%"></td>
		<td WIDTH="10%"><label><bean:message key="label.loginID" />
		<span class="mandatory">*</span> 
		</label></td>
		<td WIDTH="10%">
			<html:text property="loginID" size="25" maxlength="15" styleId="loginID" 
			styleClass="validate[required,length[6,15],custom[noSpecialCharactersForUserId]]" onfocus="true"></html:text>		
		</td>
		
	</tr>
	<tr>
		<td WIDTH="80%"></td>
		<td WIDTH="10%"><label><bean:message key="label.registration.firstName" /><span class="mandatory">*</span>  </label></td>
		<td WIDTH="10%">
			<html:text property="userName" size="25" maxlength="25" styleId="userName" 
			 styleClass="validate[required]" ></html:text>		
		</td>
	</tr>
	<tr>
		<td WIDTH="80%"></td>
		<td WIDTH="10%"><label><bean:message key="label.registration.lastName" />  </label></td>
		<td WIDTH="10%">
			<html:text property="lastName" size="25" maxlength="25" styleId="lastName" 
			styleClass="validate[custom[onlyLetter]]" onfocus="true"></html:text>		
		</td>
	</tr>
	<tr>
		<td WIDTH="80%"></td>
		<td WIDTH="10%"><label><bean:message key="label.password" /></label><span class="mandatory">*</span> </td>
		<td WIDTH="10%">
			<html:password property="password" size="25" maxlength="15" 
			styleId="password" styleClass="validate[required,length[6,15]]"  ></html:password>		
		</td>
	</tr>
	<tr>
		<td WIDTH="80%"></td>
		<td WIDTH="10%"><label><bean:message key="label.renenterPassword" /><span class="mandatory">*</span></label> </td>
		<td WIDTH="10%">
			<html:password property="duplicatePassword" size="25" maxlength="15" 
			styleClass="validate[required,length[6,15]]" styleId="duplicatePassword"></html:password>		
		</td>
	</tr>
	<tr>
		<td WIDTH="80%"></td>
		<td WIDTH="10%"><label><bean:message key="label.registration.shopName" /></label> </td>
		<td WIDTH="10%">
			<html:text property="shopName" size="25" maxlength="25" 
			styleClass="validate[custom[onlyLetter]]"  styleId="shopName"></html:text>		
		</td>
	</tr>
	<tr>
		<td WIDTH="80%"></td>
		<td WIDTH="10%"><label><bean:message key="label.registration.mobileNumber" /><span class="mandatory">*</span></label> </td>
		<td WIDTH="10%">
			<html:text property="phoneNumber" size="25" maxlength="18" 
			styleId="phoneNumber" styleClass="validate[required,custom[onlyNumber]]"></html:text>		
		</td>
	</tr>
	<tr>
		<td WIDTH="80%"></td>
		<td WIDTH="10%"><label><bean:message key="label.registration.mailId" /><span class="mandatory">*</span></label> </td>
		<td WIDTH="10%">
			<html:text property="emailID" size="25" maxlength="50" styleId="emailID"
			styleClass="validate[required,custom[email]]"></html:text>		
		</td>
	</tr>
	<tr>
		<td WIDTH="80%"></td>
		<td WIDTH="10%"><label><bean:message key="label.registration.location" /></label> </td>
		<td WIDTH="10%">
			<html:text property="location" size="25" maxlength="25" 
			styleClass="validate[custom[onlyLetter]]" styleId="location"></html:text>		
		</td>
	</tr>
</table>
<div align="right">
	<html:submit property="method" ><bean:message key="button.register" /></html:submit>
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