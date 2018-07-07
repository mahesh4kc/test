


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
</tr>
</table>

<table>
<nested:present name="searchMasterScreenForm" property="searchMasterScreenBOList">
	<tr>
	<td><bean:write name="searchMasterScreenForm" property="searchLabel1"/></td>	
	<td><bean:write name="searchMasterScreenForm" property="searchLabel2"/></td>	
	<td><bean:write name="searchMasterScreenForm" property="searchLabel3"/></td>	
	<td><bean:write name="searchMasterScreenForm" property="searchLabel4"/></td>
</tr>	
</nested:present>
<nested:iterate id="searchMasterScreenDetail" name="searchMasterScreenForm" property="searchMasterScreenBOList" type="com.bank.bo.SearchMasterScreenBO">
<tr>
	<td > <a href="#" 
			onClick="closeCustomerChildWindow(
				<nested:write property="search1" />,'<nested:write property="search2" />'
				,'<nested:write property="search3" />','<nested:write property="search4" />'
			);"> 
			<nested:write property="search1" /></a></td>	
	<td><nested:write property="search2" ></nested:write> </td>	
	<td><nested:write property="search3" ></nested:write></td>	
	<td><nested:write property="search4" ></nested:write></td>
	
</tr>
</nested:iterate>	

 <display:table   name="searchMasterScreenBOList" >
    <display:column property="search1" title="ID" />
    <display:column property="search2" />
    <display:column property="search3" />
    <display:column property="search4" />
 
  </display:table>



</table>
