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
<title>Bill Redemption Maintenance</title>
<script type="text/javascript">
$(function() {
    $( "#redemptionDate" ).datepicker
    ({
    	dateFormat: "dd/mm/yy",
    		changeMonth: true,
    	      changeYear: true
      });
  });
  
$(document).ready(function() {
	$("#form1").validationEngine()
});

$(document).keydown(function(e){                                 
     //alert(code);
     if(e.ctrlKey){
      var code = e.which || e.keyCode;
      switch ( code )
     {
                    case 114:
                   	  $('form#form1').attr({action: 'billRedemption.do?method=REDEM'});                                              
                         $('form#form1').submit();
                      return false;
                      case 82:
                   	   $('form#form1').attr({action: 'billRedemption.do?method=REDEM'});                                              
                          $('form#form1').submit();
                      return false;
                    default:
                      break;
      }
     } 
         });
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/js/bill.js"></script>
<script>



var DateDiff = {
		   
	    inDays: function(d1, d2) {
	        var t2 = d2.getTime();
	        var t1 = d1.getTime();
	 
	        return parseInt((t2-t1)/(24*3600*1000));
	    },
	 
	    inWeeks: function(d1, d2) {
	        var t2 = d2.getTime();
	        var t1 = d1.getTime();
	 
	        return parseInt((t2-t1)/(24*3600*1000*7));
	    },
	 
	    inMonths: function(d1, d2) {
	        var d1Y = d1.getFullYear();
	        var d2Y = d2.getFullYear();
	        var d1M = d1.getMonth();
	        var d2M = d2.getMonth();
		
	        return (d2M+12*d2Y)-(d1M+12*d1Y);
	    },
	 
	    inYears: function(d1, d2) {
	        return d2.getFullYear()-d1.getFullYear();
	    }
	}

function loadRedemInterestAndTotal() {	
				principal = document.getElementById("amount").value;
				rateOfInterest = 1.33;//document.getElementById("rateOfInterest").value;	
				var year1, year2;			
				var day1, day2;
				var month1, month2;				
				value1 = document.getElementById("billDate").value;
				value2 = document.getElementById("redemptionDate").value;				
				day1 = value1.substring (0, value1.indexOf ("/"));
				month1 = value1.substring (value1.indexOf ("/")+1, value1.lastIndexOf ("/"));
				year1 = value1.substring (value1.lastIndexOf ("/")+1, value1.length);
				day2 = value2.substring (0, value2.indexOf ("/"));
				month2 = value2.substring (value2.indexOf ("/")+1, value2.lastIndexOf ("/"));
				year2 = value2.substring (value2.lastIndexOf ("/")+1, value2.length); 				
				start = new Date(month1+"/"+day1+"/"+year1)
				end = new Date(month2+"/"+day2+"/"+year2)
				var diffMonths = DateDiff.inMonths(start,end);
				if(( (day1 == day2 && month1 != month2 && year1 == year2) || (day1 == day2 && month1 == month2 && year1 != year2) 
						|| (day1 == day2 && month1 != month2 && year1 != year2)				)){
					//alert("sameDate");
					diffMonths = diffMonths -1;
				}
				noOfMonths = diffMonths;
				if(noOfMonths > 0){
					interest = (parseInt(principal * noOfMonths * rateOfInterest ) / 100)  ;
					totalRedemption = ( parseInt(principal) +  (parseInt(principal * noOfMonths * rateOfInterest ) / 100) );
					
				}else{
					interest  = 0;
					totalRedemption = principal;
				}
				document.getElementById("redemptionInterest").value = interest;
				document.getElementById("redemptionTotal").value = totalRedemption; 
				
            }
</script>

</head>
<body onload="billOnBodyLoad('<%=request.getContextPath()%>','BR');">
<html:form action="billRedemption" method="POST" styleId="form1">
<div align="center" class="shopDetails" ><bean:write name="billForm" property="shopDetails"/> </div>
<div align="right">User : <bean:write name="billForm" property="userLoggedIn"/> </div>

<div align="right">
	<html:submit property="method" ><bean:message key="button.search" /></html:submit> 
	<html:submit property="method" ><bean:message key="button.clear" /></html:submit>
	<html:submit property="method" ><bean:message key="button.redem" /></html:submit>
</div>
<jsp:include page="htmlError.jsp"></jsp:include>
<table>
<tr>
	<td><h3>Bill Redemption Details</h3></td>
</tr>
<tr>
	<td> Redemption Date</td>
	<td>
		<html:text name="billForm" property="billHeaderBO.redemptionDate" 
		styleClass="validate[required,custom[dateIndianFormat]]"
		onfocus="showCalendarControl(this);" onblur="checkdate(this);loadRedemInterestAndTotal();" maxlength="10" styleId="redemptionDate"></html:text>
	</td>

	<td>Redemption Interest</td>
	<td>
		<html:text name="billForm"  property="billHeaderBO.redemptionInterest" styleId="redemptionInterest" ></html:text>
	</td>
</tr>
<tr>
	<td> Redemption Total</td>
	<td>
		<html:text name="billForm" property="billHeaderBO.redemptionTotal" styleId="redemptionTotal"></html:text>
	</td>

	<td>Redemption Status</td>
	<td>
		<html:select name="billForm" property="billHeaderBO.redemptionStatus" styleId="redemptionStatus">
			<html:option value="O">OPEN</html:option>
			<html:option value="R">REDEMED</html:option>
			<html:option value="C">CANCEL</html:option>
			<html:option value="A">AUCTION</html:option>
		</html:select>		
	</td>
	
</tr>
<tr>
	<td>Bill Redemption No</td>
	<td>
	<html:text property="billHeaderBO.billRedemSerial" size="1" styleId="billRedemSerial" maxlength="1"/>
		<html:text property="billHeaderBO.billRedemNumber" styleId="billRedemNumber" maxlength="4" size="14"> </html:text>	
	</td>

</tr>
</table>

<jsp:include page="billCommon.jsp"></jsp:include>

</html:form>

</body>
</html>