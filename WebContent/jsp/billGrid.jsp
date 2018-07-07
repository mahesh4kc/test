 <%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<logic:equal name="searchMasterScreenForm" property="searchTableDetails" value="B">


<table WIDTH="100%">
<tr  >
		<td WIDTH="25%"><bean:write name="searchMasterScreenForm" property="searchLabel1"/> </td>
		<td WIDTH="25%"><html:text name="searchMasterScreenForm" property="searchInput1" ></html:text></td>	
		<td WIDTH="25%"><bean:write name="searchMasterScreenForm" property="searchLabel2"/> </td>
		<td WIDTH="25%"><html:text name="searchMasterScreenForm" property="searchInput2" ></html:text></td>
		<td WIDTH="25%"><bean:write name="searchMasterScreenForm" property="searchLabel3"/> </td>
		<td WIDTH="25%"><html:text name="searchMasterScreenForm" property="searchInput3" ></html:text></td>	
		<td WIDTH="25%"><bean:write name="searchMasterScreenForm" property="searchLabel4"/> </td>
		<td WIDTH="25%"><html:text name="searchMasterScreenForm" property="searchInput4" ></html:text></td>
		<td WIDTH="25%">   <bean:write name="searchMasterScreenForm" property="searchLabel5"/> </td>
		<td WIDTH="25%">  
			<html:select name="searchMasterScreenForm" property="searchInput5">
				<html:option value="O">OPEN</html:option>
				<html:option value="R">REDEMED</html:option>
				<html:option value="C">CANCEL</html:option>
			</html:select>	
		</td>

</tr>
</table>
<div align="center">
	<html:submit property="method" ><bean:message key="button.search" /></html:submit>
	<html:submit property="method" ><bean:message key="button.clear" /></html:submit>
	</div>
<table>
<nested:present name="searchMasterScreenForm" property="searchMasterScreenBOList">
	<tr>
	<td><bean:write name="searchMasterScreenForm" property="searchLabel1"/></td>	
	<td><bean:write name="searchMasterScreenForm" property="searchLabel2"/></td>	
	<td><bean:write name="searchMasterScreenForm" property="searchLabel3"/></td>	
	<td><bean:write name="searchMasterScreenForm" property="searchLabel4"/></td>
	<td><bean:write name="searchMasterScreenForm" property="searchLabel5"/></td>
</tr>	
</nested:present>
<nested:iterate id="searchMasterScreenDetail" name="searchMasterScreenForm" property="searchMasterScreenBOList" type="com.bank.bo.SearchMasterScreenBO">
<tr>
<td > <a href="#" 
			onClick="closeBillChildWindow(
				'<nested:write property="searchPrimaryKey" />','<nested:write property="search1" />'
				,'<nested:write property="search2" />','<nested:write property="search3" />',
				'<nested:write property="search4" />'
			);"> 
			<nested:write property="search1" /></a></td>	
	<td><nested:write property="search2" ></nested:write> </td>	
	<td><nested:write property="search3" ></nested:write></td>	
	<td><nested:write property="search4" ></nested:write></td>
	<td><nested:write property="search5" ></nested:write></td>
	
</tr>
</nested:iterate>	
</table>
</logic:equal>