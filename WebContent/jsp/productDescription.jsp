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

<title><bean:message key="label.productDescription.title" />  </title>
<script type="text/javascript">
$(document).keydown(function(e){                                 
    //alert(code);
    if(e.ctrlKey){
     var code = e.which || e.keyCode;
     switch ( code )
    {
                   case 115:
                  	  $('form#form1').attr({action: 'productDescription.do?method=SAVE'});                                              
                        $('form#form1').submit();
                     return false;
                     case 83:
                  	   $('form#form1').attr({action: 'productDescription.do?method=SAVE'});                                              
                         $('form#form1').submit();
                     return false;
                     case 99:
                     	  $('form#form1').attr({action: 'productDescription.do?method=CLEAR'});                                              
                           $('form#form1').submit();
                        return false;
   					case 67:
                     	   $('form#form1').attr({action: 'productDescription.do?method=CLEAR'});                                              
                            $('form#form1').submit();
                        return false;
                   default:
                     break;
     }
    } 
        });
</script>
</head>
<body  onload="bodyOnLoad('productDescription');">

<html:form action="productDescription" method="POST" styleId="form1">
<div align="center" class="shopDetails" ><bean:write name="productDescriptionForm" property="shopDetails"/> </div>
<div align="right">User : <bean:write name="productDescriptionForm" property="userLoggedIn"/> </div>

<jsp:include page="htmlError.jsp"></jsp:include>

<table>
<tr>
		
	<td><label><bean:message key="label.productDescription.productDescriptions" />  </label></td>
	<td>
		<html:text property="productDescription" maxlength="500" size="78" styleId="productDescription"></html:text>
	</td>
</tr>

</table>

<div align="center">	
	<html:submit property="method" ><bean:message key="button.search" /></html:submit>
	<html:submit property="method" ><bean:message key="button.clear" /></html:submit>
</div>
<nested:notEmpty name="productDescriptionForm" property="productDescriptionList" scope="session"  >
<table>
<tr>
	<th><label><bean:message key="label.productDescription.del" />  </label> </th>
		
		<th><label><bean:message key="label.productDescription.productDescriptions" />  </label></th>
</tr>
</table>
<table id="dataTable">
		
<nested:iterate id="products" name="productDescriptionForm" property="productDescriptionList" type="com.bank.bo.ProductDescriptionBO">

<tr>
	<td><nested:checkbox property="checked" ></nested:checkbox></td>		
	<td>
		<nested:hidden property="productNo"></nested:hidden>
	</td>	
	<td><nested:text property="productDescription" maxlength="500" size="94" ></nested:text></td>
</tr>

</nested:iterate>	

</table>


<div align="center">
	<button type="button" onclick="addRow('dataTable')"><bean:message key="addRow" /></button>
	<button type="button" onclick="deleteRow('dataTable')"><bean:message key="deleteRow" /></button>
	<html:submit property="method" ><bean:message key="button.save" /></html:submit>
</div>
</nested:notEmpty>	
</html:form>
</body>
</html>