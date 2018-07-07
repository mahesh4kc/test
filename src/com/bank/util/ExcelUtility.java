package com.bank.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

import com.bank.bo.SearchMasterScreenBO;
import com.bank.form.SearchMasterScreenForm;

public class ExcelUtility extends ResponseUtility {

	public HSSFWorkbook workbook;
	public HSSFSheet sheet;
	//public HSSFCellStyle style;
	public Map<String,HSSFCellStyle> styles;
	
	public HSSFFont font;
	 
	 public List<String> columnHeaders;
	   
	    public List<List<String>> noOfRecords;
	    
	    public List<String> records;
	   
	    private static final int RowStartIndex = 2;
	    
	    private static final int ColumnStartIndex = 0;
	    
	    private static final int ColumnTotalIndex = 4;
	    
	    public String searchTableDetails;
	    
	    public String getSearchTableDetails() {
			return this.searchTableDetails;
		}


		public void setSearchTableDetails(String searchTableDetails) {
			this.searchTableDetails = searchTableDetails;
		}


		public ExcelUtility(String sheetName,SearchMasterScreenForm searchMasterScreenForm){
			this.searchTableDetails = searchMasterScreenForm.getSearchTableDetails();
			setcolumnHeaders(searchMasterScreenForm);
	    	setColumnValues(searchMasterScreenForm);
	    	workbook = new HSSFWorkbook();	
	    	//workbook.setPrintArea(0, startColumn, endColumn, startRow, endRow)
		    sheet = workbook.createSheet(sheetName);
		    //sheet = workbook.getSheetAt(0);	
		   
		    //wb.setRepeatingRowsAndColumns(0,0,2,-1,-1);                
            //sheet.setAutobreaks(true);
            sheet.setDisplayGridlines(true);
            //sheet.setFitToPage(true);
            sheet.setGridsPrinted(true);  
            
		    sheet.setMargin(HSSFSheet.TopMargin, (double) .50);
		    sheet.setMargin(HSSFSheet.BottomMargin, (double) .50);
		    sheet.setMargin(HSSFSheet.LeftMargin, (double) .50);
		    sheet.setMargin(HSSFSheet.RightMargin, (double) .50);        
		    HSSFPrintSetup ps = sheet.getPrintSetup();
		    ps.setFitWidth((short)1);
		    ps.setFitHeight((short)9999);
		    ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);        
		    ps.setLandscape(false);
		    ps.setHeaderMargin((double) .35);
		    ps.setFooterMargin((double) .35);


	        
		    this.styles = new HashMap<String, HSSFCellStyle>();
		    setHeaderCellStyle(workbook);
		    setBodyCellStyle(workbook);
		    
		    
	    }
	    
	
	 //
    // Create an instance of workbook and sheet
    //
	 
	 public static void main(String args[]) throws Exception{
		 SearchMasterScreenForm searchMasterScreenForm=null;
		 ExcelUtility excelCreation = new ExcelUtility("Customer Details",searchMasterScreenForm);
		 //excelCreation.setFontAndStyle();
		 //excelCreation.renderColumns(searchMasterScreenBO, row);
		// MySQLConnection.databaseName ="OMPRAKASHKAG";
		// BillHeaderDAO billHeaderDAO = new BillHeaderDAO();
		 //List<String> columnNames= new ArrayList<String>();
		// columnNames.add("Bill S.No");
		// columnNames.add("Bill Date");
		 //List<BillHeaderBO> billHeaderBOList=billHeaderDAO.executeAllBillHeaders();
		 //new ArrayList<BillHeaderBO>();
		 excelCreation.createExcelFile("c:/CustomerDetails.xls",excelCreation.columnHeaders.size(),
				 excelCreation.columnHeaders, excelCreation.noOfRecords);
	 }
	
	 private void setHeaderCellStyle(HSSFWorkbook workbook){
         HSSFCellStyle headerStyle = workbook.createCellStyle();
         
     // We also define the font that we are going to use for displaying the
     // data of the cell. We set the font to ARIAL with 20pt in size and
     // make it BOLD and give blue as the color.
     //
      font = workbook.createFont();
     font.setFontName(HSSFFont.FONT_ARIAL);
     font.setFontHeightInPoints((short) 10);
     font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
     font.setColor(HSSFColor.GREY_80_PERCENT.index);
     headerStyle.setFont(font);
     
     //Borders for the Body
     headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
     headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
     headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
     headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
     
     //Border Colors for the Body
     headerStyle.setRightBorderColor(HSSFColor.GREY_25_PERCENT.index);
     headerStyle.setLeftBorderColor(HSSFColor.GREY_25_PERCENT.index);
     headerStyle.setTopBorderColor(HSSFColor.GREY_25_PERCENT.index);
     headerStyle.setBottomBorderColor(HSSFColor.GREY_25_PERCENT.index);
     
     headerStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
     headerStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
     headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
     //setBorders(headerStyle);
    
     this.styles.put("headerStyle", headerStyle);
     }
	

	 private void setBodyCellStyle(HSSFWorkbook workbook){
         HSSFCellStyle bodyStyle = workbook.createCellStyle();
         setBorders(bodyStyle);
     //Font for the Body
     HSSFFont bodyFont = workbook.createFont();
     bodyFont.setFontHeightInPoints((short)8);
     //bodyFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
     bodyFont.setColor(HSSFColor.GREY_80_PERCENT.index);
     bodyStyle.setFont(bodyFont);
     
     //Borders for the Body
     bodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
     bodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
     bodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
     bodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
     
     /*//Border Colors for the Body
     bodyStyle.setRightBorderColor(HSSFColor.LIGHT_BLUE.index);
     bodyStyle.setLeftBorderColor(HSSFColor.LIGHT_BLUE.index);
     bodyStyle.setTopBorderColor(HSSFColor.LIGHT_BLUE.index);
     bodyStyle.setBottomBorderColor(HSSFColor.LIGHT_BLUE.index);
     
    
     bodyStyle.setFillBackgroundColor(HSSFColor.SKY_BLUE.index);
     bodyStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
     bodyStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);    */ 
     bodyStyle.setWrapText(true);
     this.styles.put("bodyStyle", bodyStyle);
     }
	
	 private void setAutoFitColumns(HSSFSheet sheet){	
		 /*
		 sheet.setColumnWidth(0, 2);
		 sheet.setColumnWidth(0, 2);
		 sheet.setColumnWidth(0, 2);
		 sheet.setColumnWidth(0, 2);
		 sheet.setColumnWidth(0, 2);
		 sheet.setColumnWidth(0, 2);
		 sheet.setColumnWidth(0, 2);
		   */
	        sheet.autoSizeColumn(0); //adjust width of the first column
		    sheet.autoSizeColumn(1); //adjust width of the second column
		    sheet.autoSizeColumn(2); //adjust width of the first column
		    sheet.autoSizeColumn(3); //adjust width of the second column
		    sheet.autoSizeColumn(4); //adjust width of the first column
		    sheet.autoSizeColumn(5); //adjust width of the second column
		    sheet.autoSizeColumn(6); //adjust width of the second column
		  
		}
	
	 //To print the records such as customer or bill
	public void renderRows(List<List<String>> allRecords){
		//
        // We create a simple cell, set its value and apply the cell style.
        //
		 // Now let's loop over the customer details  
		int rowCounter =RowStartIndex;
		Long totalAmount=0L;
	    for(List<String> customerList : allRecords){
	    	HSSFRow row = sheet.createRow(rowCounter);
	    	int columnCounter = ColumnStartIndex;
	            for(String value: customerList){
	            	renderColumns(value, row,columnCounter); 
	            	 if(this.searchTableDetails.equalsIgnoreCase("B")){
		            	if(columnCounter == ColumnTotalIndex){
		            		totalAmount = totalAmount + Integer.parseInt(value);
		            	}
	            	 }
	            	columnCounter++;
	            	
	        } 
	            rowCounter++;
	    }
	    if(this.searchTableDetails.equalsIgnoreCase("B")){
	    	 //To print total finally
		    HSSFRow row = sheet.createRow(rowCounter);
		    renderColumns("Total Amount : ", row,ColumnTotalIndex - 1); 
		    renderColumns(totalAmount.toString(), row,ColumnTotalIndex); 
	    }
	   
	    
	    //Auto fit the content after it is rendered
        setAutoFitColumns(sheet);
	}
	
	//To print the headers such as customer details or bill details
	public void renderTitle(String title){
		//
        // We create a simple cell, set its value and apply the cell style.
        //
		int rowCounter=0;
		int columnCounter = ColumnStartIndex;
		HSSFRow row = sheet.createRow(rowCounter);					
		renderColumnHeaders(title, row,columnCounter);
		 
	     Region region = new Region();
	     region.setColumnFrom((short)0);
	     region.setColumnTo((short)6);
	     sheet.addMergedRegion(region);
	     
	}
	
	//To print the headers such as customer details or bill details
	public void renderRowHeaders(List<String> columnHeaders){
		//
        // We create a simple cell, set its value and apply the cell style.
        //
		int rowCounter=1;
		int columnCounter = ColumnStartIndex;
		HSSFRow row = sheet.createRow(rowCounter);
		for(String columnHeader : columnHeaders){			
			renderColumnHeaders(columnHeader, row,columnCounter);
			columnCounter++;
		}	     
	}
	
	
	private void renderColumnHeaders(String ColumnName,HSSFRow row,int columnIndex){
		 HSSFCell cell = row.createCell(columnIndex);
	        cell.setCellValue(new HSSFRichTextString(ColumnName));
	        cell.setCellStyle(this.styles.get("headerStyle"));
	        
     }
	private void renderColumns(String ColumnValue,HSSFRow row,int columnIndex){
		 HSSFCell cell1 = row.createCell(columnIndex);
        cell1.setCellValue(new HSSFRichTextString(ColumnValue));
        cell1.setCellStyle(this.styles.get("bodyStyle"));
	}
	
	public void createExcelFile(String fileName, int noOfColumns,
			List<String> columnHeaders,List<List<String>> allRecords){
		  //
        // Finally we write out the workbook into an excel file.
        //
        FileOutputStream fos = null;
        try {
        	renderTitle(fileName);
        	renderRowHeaders(columnHeaders);        
        	renderRows(allRecords);
            fos = new FileOutputStream(new File(fileName));
           
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
	public  ByteArrayOutputStream getXLSInByteArrayOutputStream(String fileName, int noOfColumns){
		  //
        // Finally we write out the workbook into an excel file.
        //
		ByteArrayOutputStream baos= new ByteArrayOutputStream() ;
        FileOutputStream fos = null;
        try {        	
        	renderRowHeaders(this.columnHeaders);       	
        	renderRows(this.noOfRecords);
           // fos = new FileOutputStream(new File(fileName));
           // workbook.write(fos);
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
       return baos; 
	}
	private void setcolumnHeaders(SearchMasterScreenForm searchMasterScreenForm){
		 	this.columnHeaders = new ArrayList<String>();
	        this.columnHeaders.add(searchMasterScreenForm.getSearchLabel1());
	        this.columnHeaders.add(searchMasterScreenForm.getSearchLabel2());
	        this.columnHeaders.add(searchMasterScreenForm.getSearchLabel3());
	        this.columnHeaders.add(searchMasterScreenForm.getSearchLabel4());
	        this.columnHeaders.add(searchMasterScreenForm.getSearchLabel5());
	        this.columnHeaders.add(searchMasterScreenForm.getSearchLabel6());
	       
	        if(this.searchTableDetails.equalsIgnoreCase("B")){
	        	this.columnHeaders.add(searchMasterScreenForm.getSearchLabel9());
	             this.columnHeaders.add(searchMasterScreenForm.getSearchLabel10());
	        	 this.columnHeaders.add(searchMasterScreenForm.getSearchLabel7());
	             this.columnHeaders.add(searchMasterScreenForm.getSearchLabel8());
	             
	        }
	}
/*	private void setcolumnHeaders(){
        this.columnHeaders = new ArrayList<String>();
        this.columnHeaders.add("Name");
        this.columnHeaders.add("Address");
        this.columnHeaders.add("Area");
        this.columnHeaders.add("District");
        this.columnHeaders.add("Mobile");
        this.columnHeaders.add("Email");
        this.columnHeaders.add("Misc");
}*/

   /*private void setColumnValues(){
            // add the no of rows that will retrieve from the database
            this.records = new ArrayList<String>();
            for(int i=0;i<2;i++){
                   // List<String> columnValues = new ArrayList<String>();
            	StringBuffer record = new StringBuffer();
            	record.append("Name1"+BankConstant.DELIMITER);
            	record.append("Address1"+BankConstant.DELIMITER);
            	record.append("Area1"+BankConstant.DELIMITER);
            	record.append("District1"+BankConstant.DELIMITER);
            	record.append("Mobile1"+BankConstant.DELIMITER);
            	record.append("Email1"+BankConstant.DELIMITER);
            	record.append("Misc1");
                this.records.add(record.toString());
    }            
       
}*/

	private void setColumnValues(SearchMasterScreenForm searchMasterScreenForm){
	    // add the no of rows that will retrieve from the database
		this.noOfRecords = new ArrayList<List<String>>();
		for(SearchMasterScreenBO searchMasterScreenBO : searchMasterScreenForm.getSearchMasterScreenBOList()){
			List<String> columnValues = new ArrayList<String>();
			columnValues.add(searchMasterScreenBO.getSearch1());
			columnValues.add(searchMasterScreenBO.getSearch2());
			columnValues.add(searchMasterScreenBO.getSearch3());
			columnValues.add(searchMasterScreenBO.getSearch4());
			columnValues.add(searchMasterScreenBO.getSearch5());
			columnValues.add(searchMasterScreenBO.getSearch6());
			
			 if(this.searchTableDetails.equalsIgnoreCase("B")){
				 columnValues.add(searchMasterScreenBO.getSearch9());
				 columnValues.add(searchMasterScreenBO.getSearch10());
				 columnValues.add(searchMasterScreenBO.getSearch7());
					columnValues.add(searchMasterScreenBO.getSearch8());
	        }	
			
			this.noOfRecords.add(columnValues);
		}
		
/*private void setColumnValues(){
    // add the no of rows that will retrieve from the database
    this.noOfRecords = new ArrayList<List<String>>();
    for(int i=0;i<2;i++){
            List<String> columnValues = new ArrayList<String>();
    	//StringBuffer record = new StringBuffer();
            columnValues.add("Name1");
            columnValues.add("Address1");
            columnValues.add("Area1");
            columnValues.add("District1");
            columnValues.add("Mobile1");
            columnValues.add("Email1");
            columnValues.add("Misc1");
        this.noOfRecords.add(columnValues);
}      */      

}

    public List<List<String>> getNoOfRecords() {
            return this.noOfRecords;
    }

    public void setNoOfRecords(List<List<String>> noOfRecords) {
            this.noOfRecords = noOfRecords;
    }

	public List<String> getRecords() {
		return records;
	}

	public void setRecords(List<String> records) {
		this.records = records;
	}


	public Map<String, HSSFCellStyle> getStyles() {
		return this.styles;
	}


	public void setStyles(Map<String, HSSFCellStyle> styles) {
		this.styles = styles;
	}
	
		// Set borders
		/**
		 * 
		 * @param cellStyle
		 */
		private void setBorders(HSSFCellStyle cellStyle)
		{
			if(cellStyle != null)
			{
				cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			}		
		}
		
		/**
		 * 
		 * @param workbook
		 * @return
		 */
		private HSSFCellStyle getHeaderStyle(HSSFWorkbook workbook, short align)
		{
			if(workbook != null)
			{
				HSSFCellStyle headerStyle = workbook.createCellStyle();			
				headerStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				headerStyle.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
				headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				headerStyle.setAlignment(align);
				/* set FontWeight*/
				HSSFFont font = workbook.createFont();
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				headerStyle.setFont(font);			
				setBorders(headerStyle);		
				return headerStyle;
			}
			
			return null;
		}
}
