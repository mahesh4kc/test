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
<script type="text/javascript" src="<%=request.getContextPath()%>/include/js/bill.js"></script>
<script type="text/javascript">

$(function() {
    $( "#billDate" ).datepicker
    ({
    	dateFormat: "dd/mm/yy",
    		changeMonth: true,
    	      changeYear: true
      });
  });
 
$(document).ready(function() {
	$("#form1").validationEngine()
});
</script>
</head>
<body onload="billOnBodyLoad('<%=request.getContextPath()%>');">
<html:form action="bill" method="POST" styleId="form1" >
<div align="center" class="shopDetails" ><bean:write name="billForm" property="shopDetails"/> </div>
<div align="right">User : <bean:write name="billForm" property="userLoggedIn"/> </div>
<div align="right">
	<html:submit property="method" ><bean:message key="button.search" /></html:submit> 
	<html:submit property="method" ><bean:message key="button.clear" /></html:submit>
	<html:submit property="method" ><bean:message key="button.save" /></html:submit>
	<logic:equal name="billForm" property="billDeleteButtonFlag" value="Y">
	<html:submit property="method" ><bean:message key="button.delete" /></html:submit>
	</logic:equal>
	<button type="button" onclick="addRow('dataTable');loadProductDescriptions();"><bean:message key="addRow" /></button>
	<button type="button" onclick="deleteRow('dataTable');"><bean:message key="deleteRow" /></button>
</div>
<jsp:include page="htmlError.jsp"></jsp:include>
<jsp:include page="billCommon.jsp"></jsp:include>
</html:form>
</body>
</html>