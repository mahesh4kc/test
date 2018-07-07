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
<title>Bill Maintenance</title>
</head>
<body>
<html:form action="bill" >
<div align="right">
	<html:submit property="method" ><bean:message key="button.search" /></html:submit>
	<html:submit property="method" ><bean:message key="button.create" /></html:submit>
	<html:submit property="method" ><bean:message key="button.update" /></html:submit>	
	<html:submit property="method" ><bean:message key="button.delete" /></html:submit>
</div>
<div>
<div id="javascripterror">

</div>
<html:errors />
</div>
<table>
<tr>
		<td>Bill No </td>
		<td><html:text property="billID" ></html:text>	</td>	
	</tr>
<tr>
	<td>Bill Date </td>
	<td>
		<html:text property="billDate"></html:text>
	</td>
</tr>
<tr>
	<td> Customer Name</td>
	<td>
		<html:text property="customerName"></html:text>			
	</td>
</tr>
<tr>
	<td> Relation</td>
	<td>
		<html:text property="relationship"></html:text>			
	</td>
</tr>
<tr>
<tr>
	<td> Relation Name</td>
	<td>
		<html:text property="relationName"></html:text>			
	</td>
</tr>
<tr>
	<td>Amount</td>
	<td>
		<html:text property="amount"></html:text>
	</td>
</tr>
<tr>
<tr>
	<td>Amount in Words</td>
	<td>
		<html:text property="amountInWords" ></html:text>
	</td>
</tr>
<tr>
	<td> Description</td>
	<td>
		<html:textarea property="description"></html:textarea>
	</td>
</tr>
<tr>
	<td>Grams</td>
	<td>
		<html:text property="grams"></html:text>
	</td>
</tr>
<tr>
	<td>Present Value</td>
	<td>
		<html:text property="presentValue"></html:text>
	</td>
</tr>
<tr>
	<td> Monthly Income</td>
	<td>
		<html:text property="monthlyIncome"></html:text>
	</td>
</tr>
<tr>
	<td>Rate Of Interest</td>
	<td>
		<html:text property="rateOfInterest" value="2.5"></html:text>
	</td>
</tr>
</table>

</html:form>

</body>
</html>