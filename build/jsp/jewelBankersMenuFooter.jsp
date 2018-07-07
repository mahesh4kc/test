<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div> 
<table><tr>
	<td width="12%">Favorite Menu: </td>
	<td width="12%"><html:link action="/searchMasterScreen.do?method=SEARCH&searchTableDetails=B"><bean:message key="menu.searchBills"/></html:link></td>
	<td width="12%"><html:link action="/parameters.do?method=SEARCH"><bean:message key="menu.mySettings"/></html:link></td>
	<td width="12%"><html:link action="/customer.do?method=CLEAR"><bean:message key="menu.customer"/></html:link></td>
	<td width="12%"><html:link action="/bill.do?method=CLEAR"><bean:message key="menu.bill"/></html:link></td>
	<td width="12%"><html:link action="/billRedemption.do?method=CLEAR"><bean:message key="menu.billRedempti0n"/></html:link></td>
	<td width="12%"><html:link action="/productDescription.do?method=SEARCH"><bean:message key="menu.productDescriptions"/></html:link></td>
</tr></table>
</div>