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
<title>Customer Maintenance</title>
<script type="text/javascript">
$('.keyup-customerID').keyup(function() {
    $('span.error-keyup-2').remove();
    var inputVal = $(this).val();
    var characterReg = /^\s*[a-zA-Z0-9,\s]+\s*$/;
    if(!characterReg.test(inputVal)) {
        $(this).after('<span class="error error-keyup-2">No special characters allowed.</span>');
    }
});
function openCustomerChildWindow(contextName){
//var href = "/bank/jsp/searchMasterScreens.jsp?method=search";
var href = contextName+"/searchMasterScreen.do?method=SEARCH&searchTableDetails=C";
window.open(href,'','target="_parent"');
}</script>
</head>
<body  onload="bodyOnLoad('customerNames');loadCustomer('<%=request.getContextPath()%>');">
<html:form action="customer" method="POST" >
<div align="center" class="shopDetails" ><bean:write name="customerForm" property="shopDetails"/> </div>
<div align="right">User : <bean:write name="customerForm" property="userLoggedIn"/> </div>
<jsp:include page="htmlError.jsp"></jsp:include>
<div align="right">
	<html:submit property="method" ><bean:message key="button.search" /></html:submit>
	<html:submit property="method" ><bean:message key="button.clear" /></html:submit>
	<html:submit property="method" ><bean:message key="button.create" /></html:submit>
<logic:notEmpty name="CUSTOMER_EXISTS" >	
	<html:submit property="method" ><bean:message key="button.update" /></html:submit>	
	<html:submit property="method" ><bean:message key="button.delete" /></html:submit>
</logic:notEmpty>	
	</div>
<table WIDTH="100%">
<tr  >
		
	<td WIDTH="25%">Customer Name </td>
	<td WIDTH="25%">
		<html:text property="name" size="25" maxlength="50" styleId="customerNames" onblur="loadCustomerDetailForCustomerName(this);"></html:text>
	</td>
	<td WIDTH="25%">Customer ID </td>
	<td WIDTH="25%"><html:text property="customerID" styleId="customerID"  readonly="true" ></html:text>
	<a href="#" onclick="openCustomerChildWindow('<%=request.getContextPath()%>');">Select</a>		</td>	
	
</tr>
<!--
<tr  >
<td></td>		
 <td>
		<html:checkbox property="createDuplicateCustomer">Create Duplicate</html:checkbox>
	</td>
	<td WIDTH="25%"> </td>
	<td WIDTH="25%"></td>	

	
</tr>
 -->
</table>


<table WIDTH="100%">
<tr >
	<td WIDTH="25%"> Relation</td>
	<td WIDTH="25%">
		<html:radio property="relationShip" value="W">Wife</html:radio>
		<html:radio property="relationShip" value="S">Son</html:radio>
		<html:radio property="relationShip" value="D">Daughter</html:radio>&nbsp;
		<html:radio property="relationShip" value="H">Husband</html:radio>
		<html:radio property="relationShip" value="F">Father</html:radio>
		<html:radio property="relationShip" value="">No Relation</html:radio>
	</td>
	<td WIDTH="25%">Relation Name</td>
	<td WIDTH="25%">
		<html:text property="relationName" maxlength="50" styleId="relationName"></html:text>
	</td>
</tr>
<tr >
	<td WIDTH="25%"> Address</td>
	<td WIDTH="25%">
		<html:text property="address" maxlength="50" styleId="address"></html:text>
	</td>
	<td WIDTH="25%">Street</td>
	<td WIDTH="25%">
		<html:text property="street" size="50" maxlength="50" styleId="street"></html:text>
	</td>
</tr>
<tr>
	<td WIDTH="25%"> Area</td>
	<td WIDTH="25%">
		<html:text property="area" maxlength="50" styleId="area"></html:text>
	</td>

	<td WIDTH="25%">District</td>
	<td WIDTH="25%">
		<html:text property="district" maxlength="50" styleId="district"></html:text>
	</td>
<tr>
	<td WIDTH="25%"> State</td>
	<td>
		<html:text property="state" maxlength="50" styleId="state"></html:text>
	</td >
	<td WIDTH="25%">Country</td>
	<td WIDTH="25%">
		<html:text property="country" maxlength="50" styleId="country"></html:text>
	</td>
</tr>
<tr>
	<td WIDTH="25%"> Pincode</td>
	<td WIDTH="25%">
		<html:text property="pincode" maxlength="6" styleId="pincode"></html:text>
	</td>
	<td WIDTH="25%">Mail ID </td>
	<td WIDTH="25%">
		<html:text property="mailID" maxlength="50" size="50" styleId="mailID"></html:text>
	</td>
</tr>
<tr>
	<td WIDTH="25%">Phone No</td>
	<td WIDTH="25%">
		<html:text property="phoneNo" maxlength="10" styleId="phoneNo"></html:text>
	</td>
	<td WIDTH="25%"> Mobile No</td>
	<td WIDTH="25%">
		<html:text property="mobileNo" maxlength="10" styleId="mobileNo"></html:text>
	</td>
</tr>
<!--  
<tr>
<td WIDTH="25%">Photo</td>
	<td WIDTH="25%">
		<html:text property="photo"></html:text>
	</td>	
</tr>
-->
</table>

<html:hidden property="customerList"/>

<div id="customers" style="display:none"> </div>

</html:form>
</body>
</html>