<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function bodyOnLoad(){
	document.getElementById("browseID").focus();
}
	
$(document).ready(function() {
	$("#form1").validationEngine()
});


</script>	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><bean:message key="label.fileUpload.title" /></title>
</head>
<body>
<jsp:include page="htmlError.jsp"></jsp:include> 
<html:form action="uploadFile" method="post" enctype="multipart/form-data" styleId="form1">
<br />
	<bean:message key="label.fileUpload.selectFile" /> : 
	<html:file property="file" size="50" styleId="browseID" />
<br />
<br />
<html:submit property="method" ><bean:message key="button.save" /></html:submit>
 
</html:form>
 
</body>
</html>