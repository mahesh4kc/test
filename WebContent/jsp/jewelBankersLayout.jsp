<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<title><tiles:getAsString name="title" ignore="true" /></title>
</head>
<body>
<table height="100%" width="100%" >	
		<tr height="5%" ><td width="100%"><tiles:insert attribute="header" /></td></tr>
		<tr height="10%"><td width="100%"><tiles:insert attribute="menu" /></td></tr>
		<tr height="5%"><td width="100%"><tiles:insert attribute="menuFooter" /></td></tr>
		<tr height="80%"><td width="100%"><tiles:insert attribute="body" /></td></tr>	
		<tr height="5%"><td width="100%"><tiles:insert attribute="footer" /></td></tr>
	
</table>
</body>
</html>


