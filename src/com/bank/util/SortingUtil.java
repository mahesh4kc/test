package com.bank.util;

import java.util.Comparator;

import com.bank.bo.SearchMasterScreenBO;

public class SortingUtil {

	public static Comparator<Object> sortByCustomerNameInSearchMasterScreenBO =  new Comparator<Object>(){
		String customerName , anotherCustomerName;
		public int compare(Object searchMasterScreenBO ,	Object anotherSearchMasterScreenBO)
        {			
			if(searchMasterScreenBO != null && ((SearchMasterScreenBO) searchMasterScreenBO).getSearch3() != null 
					&& anotherSearchMasterScreenBO != null && ((SearchMasterScreenBO) anotherSearchMasterScreenBO).getSearch3() != null){
				 customerName = ((SearchMasterScreenBO) searchMasterScreenBO).getSearch3().toUpperCase();
	 		     anotherCustomerName = ((SearchMasterScreenBO) anotherSearchMasterScreenBO).getSearch3().toUpperCase();
			}		      
            return customerName.compareTo(anotherCustomerName);
        }
		      
    };
    
    public static Comparator<Object> sortByBillSerialInSearchMasterScreenBO =  new Comparator<Object>(){
		String serialNo , anotherSerialNo;
		Integer billNo , anotherBillNo;
		public int compare(Object searchMasterScreenBO ,	Object anotherSearchMasterScreenBO)
        {			
			if(searchMasterScreenBO != null && ((SearchMasterScreenBO) searchMasterScreenBO).getBillHeaderBO().getBillSerial() != null 
					&& anotherSearchMasterScreenBO != null && ((SearchMasterScreenBO) anotherSearchMasterScreenBO).getBillHeaderBO().getBillSerial() != null){
				 serialNo = ((SearchMasterScreenBO) searchMasterScreenBO).getBillHeaderBO().getBillSerial().toUpperCase();
	 		     anotherSerialNo = ((SearchMasterScreenBO) anotherSearchMasterScreenBO).getBillHeaderBO().getBillSerial().toUpperCase();	 		     
			}
			return serialNo.compareTo(anotherSerialNo);
        }
		      
    };
    
    public static Comparator<Object> sortByBillSerialNoInSearchMasterScreenBO =  new Comparator<Object>(){
		String serialNo , anotherSerialNo;
		Integer billNo , anotherBillNo;
		public int compare(Object searchMasterScreenBO ,	Object anotherSearchMasterScreenBO)
        {			
			/*if(searchMasterScreenBO != null && ((SearchMasterScreenBO) searchMasterScreenBO).getBillHeaderBO().getBillSerial() != null 
					&& anotherSearchMasterScreenBO != null && ((SearchMasterScreenBO) anotherSearchMasterScreenBO).getBillHeaderBO().getBillSerial() != null){
				 serialNo = ((SearchMasterScreenBO) searchMasterScreenBO).getBillHeaderBO().getBillSerial().toUpperCase();
	 		     anotherSerialNo = ((SearchMasterScreenBO) anotherSearchMasterScreenBO).getBillHeaderBO().getBillSerial().toUpperCase();
	 		     return serialNo.compareTo(anotherSerialNo);
			}else*/ if(searchMasterScreenBO != null && ((SearchMasterScreenBO) searchMasterScreenBO).getBillHeaderBO() != null 
					&& ((SearchMasterScreenBO) searchMasterScreenBO).getBillHeaderBO().getBillNumber() != null 
					&& anotherSearchMasterScreenBO != null &&  ((SearchMasterScreenBO) anotherSearchMasterScreenBO).getBillHeaderBO() != null 
					&& ((SearchMasterScreenBO) anotherSearchMasterScreenBO).getBillHeaderBO().getBillNumber() != null){
				billNo = ((SearchMasterScreenBO) searchMasterScreenBO).getBillHeaderBO().getBillNumber();
				anotherBillNo = ((SearchMasterScreenBO) anotherSearchMasterScreenBO).getBillHeaderBO().getBillNumber();
	 		    
			}		      
			 return billNo.compareTo(anotherBillNo);
        }
		      
    };
    
    public static Comparator<Object> sortByBillRedemptionSerialInSearchMasterScreenBO =  new Comparator<Object>(){
		String serialNo , anotherSerialNo;
		Integer billNo , anotherBillNo;
		public int compare(Object searchMasterScreenBO ,	Object anotherSearchMasterScreenBO)
        {			
			if(searchMasterScreenBO != null && ((SearchMasterScreenBO) searchMasterScreenBO).getBillHeaderBO().getBillRedemSerial() != null 
					&& anotherSearchMasterScreenBO != null && ((SearchMasterScreenBO) anotherSearchMasterScreenBO).getBillHeaderBO().getBillRedemSerial() != null){
				 serialNo = ((SearchMasterScreenBO) searchMasterScreenBO).getBillHeaderBO().getBillRedemSerial().toUpperCase();
	 		     anotherSerialNo = ((SearchMasterScreenBO) anotherSearchMasterScreenBO).getBillHeaderBO().getBillRedemSerial().toUpperCase();	 		     
			}
			return serialNo.compareTo(anotherSerialNo);
        }
		      
    };
    
    public static Comparator<Object> sortByBillRedemptionSerialNoInSearchMasterScreenBO =  new Comparator<Object>(){
		String serialNo , anotherSerialNo;
		Integer billNo , anotherBillNo;
		public int compare(Object searchMasterScreenBO ,	Object anotherSearchMasterScreenBO)
        {			
			/*if(searchMasterScreenBO != null && ((SearchMasterScreenBO) searchMasterScreenBO).getBillHeaderBO().getBillSerial() != null 
					&& anotherSearchMasterScreenBO != null && ((SearchMasterScreenBO) anotherSearchMasterScreenBO).getBillHeaderBO().getBillSerial() != null){
				 serialNo = ((SearchMasterScreenBO) searchMasterScreenBO).getBillHeaderBO().getBillSerial().toUpperCase();
	 		     anotherSerialNo = ((SearchMasterScreenBO) anotherSearchMasterScreenBO).getBillHeaderBO().getBillSerial().toUpperCase();
	 		     return serialNo.compareTo(anotherSerialNo);
			}else*/ if(searchMasterScreenBO != null && ((SearchMasterScreenBO) searchMasterScreenBO).getBillHeaderBO() != null 
					&& ((SearchMasterScreenBO) searchMasterScreenBO).getBillHeaderBO().getBillRedemNumber()!= null 
					&& anotherSearchMasterScreenBO != null &&  ((SearchMasterScreenBO) anotherSearchMasterScreenBO).getBillHeaderBO() != null 
					&& ((SearchMasterScreenBO) anotherSearchMasterScreenBO).getBillHeaderBO().getBillRedemNumber() != null){
				billNo = ((SearchMasterScreenBO) searchMasterScreenBO).getBillHeaderBO().getBillRedemNumber();
				anotherBillNo = ((SearchMasterScreenBO) anotherSearchMasterScreenBO).getBillHeaderBO().getBillRedemNumber();
	 		    
			}		      
			 return billNo.compareTo(anotherBillNo);
        }
		      
    };
  /*  
  //Sort by Insitution Name for the InstitutionList
  	public static Comparator<Object> sortByInstitutionNameForInstituionList = new Comparator<Object>(){
  		  public int compare(Object institution, Object anotherInstitution) {
  		      String institutionName = ((Institution) institution).getName().toUpperCase();
  		      String anotherInstitutionName  = ((Institution) anotherInstitution).getName().toUpperCase();
  		  return institutionName.compareTo(anotherInstitutionName);
  		}
  	};*/
}
