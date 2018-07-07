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

<title><bean:message key="label.productType.title" /></title>
</head>
<body  onload="bodyOnLoad('productTypeNo');">

<html:form action="productType" method="POST">
<div align="center" class="shopDetails" ><bean:write name="productTypeForm" property="shopDetails"/> </div>
<div align="right">User : <bean:write name="productTypeForm" property="userLoggedIn"/> </div>

<jsp:include page="htmlError.jsp"></jsp:include>
<table>
<tr>
		<td><label><bean:message key="label.productType.productTypeNo" />  </label></td>
		<td><html:text property="productTypeNo" maxlength="4" styleId="productTypeNo"></html:text>	</td>	

	<td><label><bean:message key="label.productType.productTypeCode" />  </label></td>
	<td>
		<html:text property="productTypeCode" maxlength="15"></html:text>
	</td>
</tr>

</table>

<div align="center">	
	<html:submit property="method" ><bean:message key="button.search" /></html:submit>
	<html:submit property="method" ><bean:message key="button.clear" /></html:submit>
</div>
<nested:notEmpty name="productTypeForm" property="productTypeList" scope="session"  >
<table>
<tr>
	<th><label><bean:message key="label.productType.del" />  </label></th>
	<th><label><bean:message key="label.productType.productTypeNo" />  </label></th>
		<th><label><bean:message key="label.productType.productTypeCode" />  </label></th>
		<th><label><bean:message key="label.productType.productTypeDescriptioin" />  </label></th>
	<th><label><bean:message key="label.productType.rateOfInterest" />  </label></th>
</tr>
</table>
<table id="dataTable">
		
<nested:iterate id="productTypes" name="productTypeForm" property="productTypeList" type="com.bank.bo.ProductTypeBO">

<tr>
	<td><nested:checkbox property="checked" ></nested:checkbox></td>	
	<td><nested:text property="productTypeNo"  maxlength="4" readonly="true"></nested:text></td>	
	<td><nested:text property="productTypeCode" maxlength="15"></nested:text></td>	
	<td><nested:text property="productTypeDescription" maxlength="50" size="25" ></nested:text></td>
	<td><nested:text property="productTypeRateOfInterest"  maxlength="6" ></nested:text></td>
</tr>

</nested:iterate>	

</table>


<div align="center">
	<button type="button" onclick="addRow('dataTable')"><bean:message key="addRow" /></button>
	<button type="button" onclick="deleteRow('dataTable')"><bean:message key="deleteRow" /></button>

	<html:submit property="method" ><bean:message key="button.save" /></html:submit>
</div>
</nested:notEmpty>	
</html:form>
</body>
</html>