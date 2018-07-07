<%@page import="com.bank.util.BankConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body  onload="bodyOnLoad('paramID');">

<html:form action="parameters" method="POST" >
<div align="center" class="shopDetails" ><bean:write name="parameterForm" property="shopDetails"/> </div>
<div align="right">User : <bean:write name="parameterForm" property="userLoggedIn"/> </div>


<jsp:include page="htmlError.jsp"></jsp:include>

<table>
<tr>
		
	<td><label><bean:message key="label.parameters.paramID" />  </label></td>
	<td>
		<html:text property="paramID" maxlength="500" size="78" styleId="paramID"></html:text>
	</td>
</tr>

</table>

<div align="center">	
	<html:submit property="method" ><bean:message key="button.search" /></html:submit>
	<html:submit property="method" ><bean:message key="button.clear" /></html:submit>
	<html:submit property="method" ><bean:message key="button.save" /></html:submit>
</div>
<nested:notEmpty name="parameterForm" property="parameterList" scope="session"  >
<table width="70%" >
<tr >
	<th width="2%"><label><bean:message key="label.parameters.del" />  </label> </th>
	<th width="30%" colspan="2"><label><bean:message key="label.parameters.paramID" />  </label> </th>
	<th width="58%"><label><bean:message key="label.parameters.paramValue" />  </label> </th>
	<th width="10%"><label><bean:message key="label.parameters.paramExample" />  </label> </th>		
</tr>
</table>
<table id="dataTable">
<nested:iterate id="parameters" name="parameterForm" property="parameterList" type="com.bank.bo.ParametersBO">

<tr >
	<td ><nested:checkbox property="checked" ></nested:checkbox></td>		
	<td ><nested:text property="paramID"  size="25" readonly="true"></nested:text></td>
	<td>		
		
		<nested:notEqual name="parameters" property="paramID"  value="<%= BankConstant.AUCTION_DETAILS %>">	
			<nested:text property="paramValue" maxlength="500" size="54" ></nested:text>
		</nested:notEqual>
		<nested:equal name="parameters" property="paramID" value="<%= BankConstant.AUCTION_DETAILS %>">
			<nested:textarea  property="paramValue" rows="8" cols="41"></nested:textarea>			
		</nested:equal>
	</td>
	<td ><nested:hidden property="paramSequence"></nested:hidden><nested:write property="paramExample" ></nested:write></td>
</tr>

</nested:iterate>	

</table>

</nested:notEmpty>	
</html:form>
</body>
</html>