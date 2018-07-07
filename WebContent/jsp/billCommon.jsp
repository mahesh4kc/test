<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/js/dateValidation.js"></script>

<table>
<tr>
	<td><h3>Bill Details</h3></td>
</tr>
<tr>				 
		<td>Bill No </td>
		<td>
		<html:text property="billHeaderBO.billSerial" size="1" styleId="billSerial" maxlength="1"/>
		<html:text property="billHeaderBO.billNumber" styleId="billNumber" maxlength="4" > </html:text>	
		<html:hidden property="billHeaderBO.billSequence" styleId="billSequence" />
		<a href="#" onclick="openBillChildWindow();" >Select</a>	
		</td>	
	<td>Bill Date </td>
	<td>
		<html:text  name="billForm" property="billHeaderBO.billDate" styleId="billDate" 
		styleClass="validate[required]"
		onfocus="showCalendarControl(this);" onblur="checkdate(this);" maxlength="10"></html:text>
	</td>
</tr>
<tr>	
	<td> Customer Name</td>
	<td>	
		<html:text name="billForm"  property="customerName" styleId="customerNames" maxlength="50" 
		onblur="loadCustomerDetailForBillUsingCustomerName(this);" styleClass="textBoxColor"></html:text>			
	</td>	
	<td> Customer Id</td>
	<td>
		<html:text  name="billForm" property="billHeaderBO.customerID" maxlength="10" 
		styleClass="validate[required,custom[onlyNumber]]" styleId="customerID" onblur="loadCustomerDetailForBillUsingCustomerID(this);"/><a href="#" onclick="openCustomerChildWindow();" >Select</a>					
	</td>	
	</tr>
	<tr>
	<td> Customer Address</td>
	<td>		
		<html:textarea name="billForm"  property="customerAddress" rows="4" cols="25" readonly="true" styleId="customerAddress"></html:textarea>			
	</td>
	<td> Care of</td>
	<td>
		<html:text name="billForm"  property="billHeaderBO.careOf" styleId="careOf" maxlength="50"></html:text>			
	</td>
</tr>
<tr>
	<td> Article</td>
	<td><html:select property="billHeaderBO.productTypeNumber">
	<html:option value="">-SELECT-</html:option>
	<html:options collection="productTypeBOList" property="productTypeNo" labelProperty="productTypeCode" />
	</html:select> 					
	</td>
</tr>
<tr>
	<td>Amount</td>
	<td>
		<html:text name="billForm" maxlength="9" property="billHeaderBO.amount" styleId="amount" 
		 
		styleClass="validate[required,custom[onlyNumber]]" 
		onblur="amountInCompleteWords();calculatePresentValue();" ></html:text>
	</td>

	<td>Amount in Words</td>
	<td>
		<html:text name="billForm" maxlength="50" property="billHeaderBO.amountInWords" size="50" styleId="amountInWords"></html:text>
	</td>
</tr>

<tr>
	<td>Total Grams</td>
	<td>
		<html:text name="billForm" maxlength="7" property="billHeaderBO.grams" styleId="grams" onblur="ValNumber()"></html:text>
	</td>

	<td>Present Value</td>
	<td>
		<html:text name="billForm"  maxlength="10"  property="billHeaderBO.presentValue" styleId="presentValue" ></html:text>
	</td>
</tr>
<tr>
	<td> Monthly Income</td>
	<td>
		<html:text name="billForm"  maxlength="10" property="billHeaderBO.monthlyIncome" styleId="monthlyIncome" ></html:text>
	</td>

	<td>Rate Of Interest</td>
	<td>
		<html:text name="billForm"  maxlength="7"  property="billHeaderBO.rateOfInterest" styleId="rateOfInterest"></html:text>
	</td>
</tr>
<tr>
	<td> <h4>Item Description</h4></td>
	<td>
		
	</td>
</tr>
</table>
<table id="dataTable">
<nested:present name="billForm" property="billDetailList">
	<tr>
	<td>Del</td>	
	<td></td>	
	<td>Quantity</td>	
	<td>Product Description</td>
	
</tr>	
</nested:present>
<nested:iterate id="billDetail" name="billForm" property="billDetailList" type="com.bank.bo.BillDetailBO">
<tr>
	<td><nested:checkbox property="checked" ></nested:checkbox></td>	
	<td><nested:hidden property="productNumber" ></nested:hidden></td>	
	<td><nested:text property="productQuantity" maxlength="1" size="7"></nested:text></td>	
	<td><nested:text property="productDescription" maxlength="1000" size="90" styleId="productDescriptions0" styleClass="ac_input"></nested:text></td>
	
</tr>
</nested:iterate>	
</table>
<div id="billSerialDiv" style="display:none"></div>
<div id="billSerialNoDiv" style="display:none"></div>

<div id="customers" style="display:none"> </div>
<div id="productDescriptionsDiv" style="display:none"> </div>