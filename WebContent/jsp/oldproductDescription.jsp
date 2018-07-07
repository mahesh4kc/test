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

<title><bean:message key="label.productDescription.title" />  </title>
</head>
<body  onload="bodyOnLoad('productCode');">

<html:form action="productDescription" >
<div align="right">User : <bean:write name="productDescriptionForm" property="userLoggedIn"/> </div>
<jsp:include page="menu.jsp"></jsp:include>
<div id="javascripterror">

</div>
<html:errors />
<table>
<tr>
		<td><label><bean:message key="label.productDescription.productCode" />  </label></td>
		<td><html:text property="productCode" maxlength="4" styleId="productCode"></html:text>	</td>	

	<td><label><bean:message key="label.productDescription.productDescriptions" />  </label></td>
	<td>
		<html:text property="productDescription" maxlength="15" ></html:text>
	</td>
</tr>

</table>

<div align="center">	
	<html:submit property="method" ><bean:message key="button.search" /></html:submit>
	<html:submit property="method" ><bean:message key="button.clear" /></html:submit>
</div>
<nested:notEmpty name="productDescriptionForm" property="productDescriptionList" scope="session"  >
<table>
<tr>
	<th><label><bean:message key="label.productDescription.del" />  </label> </th>
		<th><label><bean:message key="label.productDescription.productCode" />  </label></th>
		<th><label><bean:message key="label.productDescription.productDescriptions" />  </label></th>
</tr>
</table>
<table id="dataTable">
		
<nested:iterate id="products" name="productDescriptionForm" property="productDescriptionList" type="com.bank.bo.ProductDescriptionBO">

<tr>
	<td><nested:checkbox property="checked" ></nested:checkbox></td>		
	<td>
		<nested:hidden property="productNo"></nested:hidden>
		<nested:text property="productCode" maxlength="15" ></nested:text>
	</td>	
	<td><nested:text property="productDescription" maxlength="50" size="25" ></nested:text></td>
</tr>

</nested:iterate>	

</table>


<div align="center">
	<input type="button" value="Add Row" onclick="addRow('dataTable')"/>
	<input type="button" value="Delete Row" onclick="deleteRow('dataTable')"/>

	<html:submit property="method" ><bean:message key="button.save" /></html:submit>
</div>
</nested:notEmpty>	
</html:form>
</body>
</html>