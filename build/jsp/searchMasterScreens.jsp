<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
  <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Maintenance</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/js/searchMasterScreens.js"></script>
<script type="text/javascript">
function showSelectedSearchOptions()
{
	if(document.getElementById('searchOptions').value == 'N' ){
		document.getElementById("normalSearch1").style.display = "block";
		document.getElementById("normalSearch2").style.display = "block";
		document.getElementById("normalSearch3").style.display = "block";
		document.getElementById("dateDiffSearch1").style.display = "none";		
		document.getElementById("dateDiffSearch2").style.display = "none";		
		document.getElementById("dateDiffSearch3").style.display = "none";
	}else if(document.getElementById('searchOptions').value == 'D' ){
		document.getElementById("normalSearch1").style.display = "none";
		document.getElementById("normalSearch2").style.display = "none";
		document.getElementById("normalSearch3").style.display = "none";
		document.getElementById("dateDiffSearch1").style.display = "block";		
		document.getElementById("dateDiffSearch2").style.display = "block";		
		document.getElementById("dateDiffSearch3").style.display = "block";
	}	
}
</script>
</head>
<body onload="bodyOnLoad('<%=request.getContextPath()%>');showSelectedSearchOptions();">

<html:form action="searchMasterScreen" method="POST">
<div align="center" class="shopDetails" ><bean:write name="searchMasterScreenForm" property="shopDetails"/> </div>
<div align="right">User : <bean:write name="searchMasterScreenForm" property="userLoggedIn"/> </div>

<jsp:include page="htmlError.jsp"></jsp:include>

<html:hidden name="searchMasterScreenForm" property="searchTableDetails" 
 styleId="searchTableDetails"/>
<table WIDTH="100%" class="searchOutput" >
<tr  >
		<td WIDTH="16%">
		 <div id="normalSearch1"><bean:write name="searchMasterScreenForm" property="searchLabel1" /></div>
		 <div id="dateDiffSearch1"><logic:equal name="searchMasterScreenForm" property="searchTableDetails" value="B">Date From </logic:equal></div>  
		</td>
		<td WIDTH="16%">
		<html:text name="searchMasterScreenForm" property="searchInput1" size="15" style="size:15" maxlength="15">
		</html:text></td>	
		<td WIDTH="16%">
		<div id="normalSearch2"><bean:write name="searchMasterScreenForm" property="searchLabel2"/></div>
		 <div id="dateDiffSearch2"><logic:equal name="searchMasterScreenForm" property="searchTableDetails" value="B">Date To </logic:equal></div>  
		 </td>
		<td WIDTH="16%">
		
		<!-- Load Customer Details for customer so set style id for searchInput2  -->
		<logic:equal name="searchMasterScreenForm" property="searchTableDetails" value="C">		
			<html:text name="searchMasterScreenForm" property="searchInput2" styleId="customerNames"  size="15" style="size:15"></html:text>		
		</logic:equal>
		<logic:equal name="searchMasterScreenForm" property="searchTableDetails" value="B">		
			<html:text name="searchMasterScreenForm" property="searchInput2" size="15" style="size:15"></html:text>		
		</logic:equal>  		
		</td>
		
		<td WIDTH="16%">
		<div id="dateDiffSearch3">
		<logic:equal name="searchMasterScreenForm" property="searchTableDetails" value="B">Customer[Rel.]</logic:equal> 
		</div>  
		<div id="normalSearch3"><bean:write name="searchMasterScreenForm" property="searchLabel3" /></div> </td>
		<td WIDTH="16%" colspan="3">
		
		<!-- Load Customer Details for customer so set style id for searchInput3  -->
		<logic:equal name="searchMasterScreenForm" property="searchTableDetails" value="B">	
			<html:text name="searchMasterScreenForm" property="searchInput3" styleId="customerNames" size="30" style="size:30"></html:text>	
		</logic:equal>
		<logic:equal name="searchMasterScreenForm" property="searchTableDetails" value="C">		
			<html:text name="searchMasterScreenForm" property="searchInput3" size="30" style="size:30"></html:text>
		</logic:equal>
		</td>
			
</tr>
<tr  >
<div id="normalSearchOthers">	
		<logic:equal name="searchMasterScreenForm" property="searchTableDetails" value="C">
			<td WIDTH="16%"><bean:write name="searchMasterScreenForm" property="searchLabel4"/> </td>
			<td WIDTH="16%"><html:text name="searchMasterScreenForm" property="searchInput4"  size="10" style="size:10"></html:text></td>
			<td WIDTH="16%"><bean:write name="searchMasterScreenForm" property="searchLabel5"/> </td>
			<td WIDTH="16%"><html:text name="searchMasterScreenForm" property="searchInput5"  size="15" style="size:15"></html:text></td>
			<td WIDTH="16%"><bean:write name="searchMasterScreenForm" property="searchLabel6"/> </td>
			<td WIDTH="16%"><html:text name="searchMasterScreenForm" property="searchInput6"  size="15" style="size:15"></html:text></td>		
		</logic:equal>
		
		</div>	
<logic:equal name="searchMasterScreenForm" property="searchTableDetails" value="B">
		<td WIDTH="16%"><bean:write name="searchMasterScreenForm" property="searchLabel5"/> </td>
		<td WIDTH="16%"><html:text name="searchMasterScreenForm" property="searchInput4"  size="15" style="size:15"></html:text></td>		
		<td WIDTH="16%"><bean:write name="searchMasterScreenForm" property="searchLabel6"/> </td>
		<td WIDTH="16%"><html:select name="searchMasterScreenForm" property="searchInput5">
				<html:option value=""></html:option>
				<html:option value="O">OPEN</html:option>
				<html:option value="R">REDEMED</html:option>
				<html:option value="C">CANCEL</html:option>
				<html:option value="A">AUCTION</html:option>
			</html:select>
		</td>	
		<td WIDTH="16%"><bean:write name="searchMasterScreenForm" property="searchLabel7"/> </td>
		<td WIDTH="16%"><html:text name="searchMasterScreenForm" property="searchInput6"  size="15" style="size:15"></html:text></td>
		<td WIDTH="16%"><bean:write name="searchMasterScreenForm" property="searchLabel11"/> </td>
		<td WIDTH="16%">
		<html:select name="searchMasterScreenForm" property="searchInput11">
			<html:option value="">-SELECT-</html:option>
			<html:options collection="productTypeBOList" property="productTypeNo" labelProperty="productTypeCode" />
		</html:select>
		</logic:equal>
		
</tr>
</table >
<div align="center">
	<logic:equal name="searchMasterScreenForm" property="searchTableDetails" value="B">
	Search Options : <html:select styleId="searchOptions" property="searchOptions" onchange="showSelectedSearchOptions()">
		<html:option value="N">Normal</html:option>
		<html:option value="D">Date</html:option>
		<html:option value="R">Red Date</html:option>
	</html:select>
	</logic:equal>
	<html:checkbox property="sortByAscending">Sort by Serial</html:checkbox>
	<html:submit property="method" ><bean:message key="button.search" /></html:submit>
	<html:submit property="method" ><bean:message key="button.clear" /></html:submit>
	
	<html:link action="/searchMasterScreen.do?method=generatePDF" >
		<html:img src="./include/images/pdf_ico.gif"/>	
	</html:link>
	<html:link action="/searchMasterScreen.do?method=generateXLS" >
		<html:img src="./include/images/xls_ico1.gif"/>
	</html:link>
	<html:link action="/searchMasterScreen.do?method=generateAuctionPDF" >
		<html:img src="./include/images/auction_ico.png"/>
	</html:link>
	</div>
	<logic:equal  name="searchMasterScreenForm" property="previousRecordExists" value="true">
		<logic:equal name="searchMasterScreenForm" property="searchTableDetails" value="C">
			<html:link action="/searchMasterScreen.do?method=PREVIOUS&searchTableDetails=C" >				
				Previous	
			</html:link>
		</logic:equal>
		<logic:equal name="searchMasterScreenForm" property="searchTableDetails" value="B">
			<html:link action="/searchMasterScreen.do?method=PREVIOUS&searchTableDetails=B" >				
				Previous	
			</html:link>
		</logic:equal>
	</logic:equal>
	<logic:equal  name="searchMasterScreenForm" property="nextRecordExists" value="true">
		<logic:equal name="searchMasterScreenForm" property="searchTableDetails" value="C">
			<html:link action="/searchMasterScreen.do?method=NEXT&searchTableDetails=C"  >
				Next	
			</html:link>
		</logic:equal>	
		<logic:equal name="searchMasterScreenForm" property="searchTableDetails" value="B">
			<html:link action="/searchMasterScreen.do?method=NEXT&searchTableDetails=B"  >
				Next	
			</html:link>
		</logic:equal>	
	</logic:equal>

<table WIDTH="100%" class="searchOutput" border="1">
<nested:present name="searchMasterScreenForm" property="searchMasterScreenBOList">
	<tr >
	<td ><bean:write name="searchMasterScreenForm" property="searchLabel1"/></td>	
	<td><bean:write name="searchMasterScreenForm" property="searchLabel2"/></td>	
	<td  WIDTH="16%"><bean:write name="searchMasterScreenForm" property="searchLabel3"/></td>	
	<td ><bean:write name="searchMasterScreenForm" property="searchLabel4"/></td>
	<td ><bean:write name="searchMasterScreenForm" property="searchLabel5"/></td>
	<td ><bean:write name="searchMasterScreenForm" property="searchLabel6"/></td>
	<logic:equal name="searchMasterScreenForm" property="searchTableDetails" value="B">
	<td ><bean:write name="searchMasterScreenForm" property="searchLabel9"/></td>
	<td ><bean:write name="searchMasterScreenForm" property="searchLabel10"/></td>
	<td ><bean:write name="searchMasterScreenForm" property="searchLabel7"/></td>
	<td ><bean:write name="searchMasterScreenForm" property="searchLabel8"/></td>
	
	</logic:equal>
</tr>
</nested:present>
<nested:iterate id="searchMasterScreenDetail" name="searchMasterScreenForm" property="searchMasterScreenBOList" type="com.bank.bo.SearchMasterScreenBO">
<nested:notEmpty  property="search8" >
<tr  class="searchRedemBill">
</nested:notEmpty>
<nested:empty  property="search8" >
<tr>
</nested:empty>
	<td >
		<logic:equal name="searchMasterScreenForm" property="searchTableDetails" value="B">
			 <a href="#" 
				onClick="closeBillChildWindow(
					'<nested:write property="searchPrimaryKey" />','<nested:write property="search1" />'
					,'<nested:write property="search2" />','<nested:write property="search3" />',
					'<nested:write property="search4" />'
				);"> <nested:write property="search1" /></a>
		</logic:equal>
		<logic:equal name="searchMasterScreenForm" property="searchTableDetails" value="C">
			<a href="#" 
			onClick="closeCustomerChildWindow(
				<nested:write property="search1" />,'<nested:write property="search2" />'
				,'<nested:write property="search3" />','<nested:write property="search4" />'
			);"> 
			<nested:write property="search1" /></a>
		</logic:equal>			
	</td>	
	<td><nested:write property="search2" ></nested:write></td>	
	<td><nested:write property="search3" ></nested:write></td>	
	<td><nested:write property="search4" ></nested:write></td>
	<td><nested:write property="search5" ></nested:write>		
	</td>
	<td><nested:write property="search6" ></nested:write>
	<logic:equal name="searchMasterScreenForm" property="searchTableDetails" value="B">
			<a href="#" onClick="navigaveBillR('<nested:write property="search1" />','<%=request.getContextPath()%>');">
			Go
			</a>
		</logic:equal>
	</td>
	<logic:equal name="searchMasterScreenForm" property="searchTableDetails" value="B">	
	<td><nested:write property="search9" ></nested:write></td>
	<td><nested:write property="search10" ></nested:write></td>
	<td>
	<nested:write property="search7" ></nested:write>	
	</td>	
	<td><nested:write property="search8" ></nested:write></td>
	
	</logic:equal>
</tr>
</nested:iterate>	
</table>
<html:hidden property="currentRecord"/>
<html:hidden property="nextRecord"/>
<html:hidden property="previousRecord"/>
<div id="customers" style="display:none"> </div>
</html:form>
</body>
</html>