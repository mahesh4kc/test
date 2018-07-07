package com.bank.form;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.bank.bo.BillDetailBO;
import com.bank.bo.BillHeaderBO;
import com.bank.bo.ProductTypeBO;
import com.bank.business.validation.BillBusinessValidation;
import com.bank.dao.ProductTypeDAO;
import com.bank.helper.BillDetailHelper;
import com.bank.helper.BillHeaderHelper;
import com.bank.helper.ParameterHelper;

public class BillForm extends BaseForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BillHeaderBO billHeaderBO;
	private List<BillDetailBO> billDetailList;
	private List<ProductTypeBO> productTypeBOList;
	private String customerName;
	private String customerAddress;
	private String billDeleteButtonFlag;			//To enable the delete button or not
	private boolean canUpdateBill;					//On save bill will be changed to open from cancel/redem/auction
	

	public boolean isCanUpdateBill() {
		return canUpdateBill;
	}


	public void setCanUpdateBill(boolean canUpdateBill) {
		this.canUpdateBill = canUpdateBill;
	}


	public String getBillDeleteButtonFlag() {
		return this.billDeleteButtonFlag;
	}


	public void setBillDeleteButtonFlag(String billDeleteButtonFlag) {
		this.billDeleteButtonFlag = billDeleteButtonFlag;
	}


	public BillForm(){	
	}
	

	public List<ProductTypeBO> getProductTypeBOList() {
		return this.productTypeBOList;
	}


	public void setProductTypeBOList(List<ProductTypeBO> productTypeBOList) {
		this.productTypeBOList = productTypeBOList;
	}


	public String getCustomerName() {
		return this.customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return this.customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public BillHeaderBO getBillHeaderBO() {
		return this.billHeaderBO;
	}
	public void setBillHeaderBO(BillHeaderBO billHeaderBO) {
		this.billHeaderBO = billHeaderBO;
	}
	
	
	public List<BillDetailBO> getBillDetailList() {
		return billDetailList;
	}
	public void setBillDetailList(List<BillDetailBO> billDetailList) {
		this.billDetailList = billDetailList;
	}
	public void reset(ActionMapping mapping,HttpServletRequest request) {
		try{
			HttpSession session = request.getSession();
			 String jndiName = (String) session.getAttribute("databaseName");
			if (session.getAttribute("loggedInUser") == null)
			 mapping.findForward("failure");
			
			ParameterHelper parameterHelper = new ParameterHelper(jndiName,request);
			this.billDeleteButtonFlag = parameterHelper.getBillDelete();
			ProductTypeDAO productTypeDAO = new ProductTypeDAO(jndiName);
			if(productTypeDAO.isConnectionExists(jndiName)){
				this.productTypeBOList  = productTypeDAO.executeAllProductType();
			}else {
				this.productTypeBOList  = new ArrayList<ProductTypeBO>();
			}
			
		     session.setAttribute( "productTypeBOList", productTypeBOList);
		    
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		billHeaderBO = new BillHeaderBO();
		 this.billDetailList = ListUtils.lazyList(new ArrayList<BillDetailBO>(),
			        new Factory() {
			            public Object create() {
			                return new BillDetailBO();
			            }
			        });
		 this.billDetailList.add(new BillDetailBO());
		 setUserLoggedIn(request);
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)  {
		String action = request.getParameter("method");
		
	    ActionErrors errors = new ActionErrors();
	    HttpSession session = request.getSession();
	    String jndiName = (String) session.getAttribute("databaseName");
	    BillBusinessValidation  billBusinessValidation = new BillBusinessValidation(jndiName);
	    String billSerial ="",billserialNo="";
	    String billSerials[];
	    ParameterHelper parameterHelper = new ParameterHelper(jndiName,request);
	  try{
		   if(action.equals("SEARCH")){
			   if(request.getParameter("billSerialNo")!=null && request.getParameter("billSerialNo").length() > 0){				   
				   billserialNo = request.getParameter("billSerialNo");
				   billSerials = billserialNo.split(":");
				   this.billHeaderBO.setBillSerial(billSerials[0]);
				   this.billHeaderBO.setBillNumber(Integer.parseInt(billSerials[1]));
			   }		
			  billBusinessValidation.validateBillDetailsForSearch(errors, this,jndiName);
			 if (errors!= null && errors.size() > 0 ){
				 BillHeaderHelper billHeaderHelper = new BillHeaderHelper(parameterHelper);			 
				 billHeaderHelper.clearBillHeadBillFormOtherThanBillKey(this,jndiName);
					 BillDetailHelper.clearBillDetailsBillForm(this); 
			  }
		    }else if(action.equals("SAVE")){
		    	this.setCanUpdateBill(parameterHelper.getBillUpdate().equalsIgnoreCase("Y") ? true : false);
		    	billBusinessValidation.validateBillDetailsForSave(errors, this);	
		    }else if(action.equals("REDEM")){
			  billBusinessValidation.validateBillRedemptionDetails(errors, this);	
		    }
		    else if(action.equals("DELETE")){		    	
		    	billBusinessValidation.validateBillDetailsForSearch(errors, this,jndiName);
		    }
	  }catch(SQLException exception){
		  exception.printStackTrace();
	  }catch(Exception exception1){
		  exception1.printStackTrace();
	  }
	   /*else if(action.equals("CREATE")){
	    	CustomerValidation.validateCreateCustomer(errors, this);
	    }*/
	   /* if ((this.name == null)  || (this.customerID == null))
		      errors.add("error.customerDetailsNotFound", new ActionError("error.customerDetailsNotFound"));
	    else if ((this.name.length() == 0) || (this.customerID.length() == 0))
	      errors.add("error.customerDetailsNotFound", new ActionError("error.customerDetailsNotFound"));*/
	    return errors;
	  }
}
