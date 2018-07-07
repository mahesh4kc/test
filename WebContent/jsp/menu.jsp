<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="googleSearchKeywords.jsp"></jsp:include>
<html>
<head>
	<jsp:include page="includeJS.jsp"></jsp:include>
	<jsp:include page="includeCSS.jsp"></jsp:include>
	<style type="text/css">
	.horizontalcssmenu ul{
margin: 0;
padding: 0;
list-style-type: none;
}

/*Top level list items*/
.horizontalcssmenu ul li{
position: relative;
display: inline;
float: left;

}

/*Top level menu link items style*/
.horizontalcssmenu ul li a{
display: block;
width: 120px; /*Width of top level menu link items*/
padding: 2px 8px;
border: 1px solid #202020;
border-left-width: 0;
text-decoration: none;
background: url(./include/images/menubg.gif) center center repeat-x;
color: black;
font: bold 13px Tahoma;
}
	
/*Sub level menu*/
.horizontalcssmenu ul li ul{
left: 0;
top: 0;
border-top: 1px solid #202020;
position: absolute;
display: block;
visibility: hidden;
z-index: 100;
}

/*Sub level menu list items*/
.horizontalcssmenu ul li ul li{
display: inline;
float: none;
}


/* Sub level menu links style */
.horizontalcssmenu ul li ul li a{
width: 160px; /*width of sub menu levels*/
font-weight: normal;
padding: 2px 5px;
background: #e3f1bd;
border-width: 0 1px 1px 1px;
}

.horizontalcssmenu ul li a:hover{
background: url(./include/images/menubgover.gif) center center repeat-x;
}

.horizontalcssmenu ul li ul li a:hover{
background: #cde686;
}

.horizontalcssmenu .arrowdiv{
position: absolute;
right: 0;
background: transparent url(./include/images/menuarrow.gif) no-repeat center left;
}

* html p#iepara{ /*For a paragraph (if any) that immediately follows menu, add 1em top spacing between the two in IE*/
padding-top: 1em;
}
	
/* Holly Hack for IE \*/
* html .horizontalcssmenu ul li { float: left; height: 1%; }
* html .horizontalcssmenu ul li a { height: 1%; }
/* End */
	</style>
</head>
<body>
<div class="horizontalcssmenu">
<ul id="cssmenu1">
<li style="border-left: 1px solid #202020;"><html:link action="/login.do?action=home&method=HOME"><bean:message key="menu.home"/></html:link></li>		
		<li><a href="#"><bean:message key="menu.master"/></a>
			<ul>				
   				<li><html:link action="/productType.do?method=SEARCH"><bean:message key="menu.productTypes"/></html:link></li>
				<li><html:link action="/productDescription.do?method=SEARCH"><bean:message key="menu.productDescriptions"/></html:link></li>
				<li><html:link action="/customer.do?method=CLEAR"><bean:message key="menu.customer"/></html:link></li>				
   			</ul>
    	</li>
		
		<li><a href="#"><bean:message key="menu.bills"/></a>
			<ul>
   				<li><html:link action="/bill.do?method=CLEAR"><bean:message key="menu.bill"/></html:link></li>		
				<li><html:link action="/billRedemption.do?method=CLEAR"><bean:message key="menu.billRedempti0n"/></html:link></li>		   
   			</ul>
    	</li>
    			
	
		<li><a href="#"><bean:message key="menu.search"/></a>
			<ul>
   				<li><html:link action="/searchMasterScreen.do?method=SEARCH&searchTableDetails=C"><bean:message key="menu.searchCustomer"/></html:link></li>	
   				<li><html:link action="/searchMasterScreen.do?method=SEARCH&searchTableDetails=B"><bean:message key="menu.searchBills"/></html:link></li>   
   			</ul>
    	</li>				
		
		<li><a href="#"><bean:message key="menu.settings"/></a>
			<ul>
   				<!--
   				<li><html:link action="#" onclick="history.go(-1);return false;">Back</html:link></li>				
				-->				
				<li><html:link action="/parameters.do?method=SEARCH"><bean:message key="menu.mySettings"/></html:link></li>		
				<li><html:link action="/changePassword.do?method=CLEAR"><bean:message key="menu.changePassword"/></html:link></li>
				<li><html:link action="/uploadFile.do?method=CLEAR"><bean:message key="menu.uploadFile"/></html:link></li>   
				<!-- <li><html:link action="/fileDownloadAction.do?method=CLEAR"><bean:message key="menu.downloadFile"/></html:link></li>
				 -->				
		   	</ul>
    	</li>
    	
		<li><html:link action="/login.do?action=logout&method=CLEAR"><bean:message key="menu.logout"/></html:link></li>	
		
</ul>
<br style="clear: left;" />
</div>		
</body>
</html>