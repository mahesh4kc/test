package com.bank.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.bo.SearchMasterScreenBO;
import com.bank.form.SearchMasterScreenForm;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;


public class PDFTableUtility  extends PDFUtility
{

    public List<String> columnHeaders;
   
    public List<List<String>> noOfRecords;
    
    public List<String> records;
    
    public String searchTableDetails;
   
    public PDFTableUtility(SearchMasterScreenForm searchMasterScreenForm){
    	this.searchTableDetails = searchMasterScreenForm.getSearchTableDetails();
    	setcolumnHeaders(searchMasterScreenForm);
    	setColumnValues(searchMasterScreenForm);    	
    }
    
   


public PdfPTable constructPDFTable(String header,String filename,float columnWidth[],
		int noOfColumns,List<String> columnHeaders,List<List<String>> allRecords){
	 // Create a table with 7 columns
    PdfPTable table = new PdfPTable(columnWidth);
   // Element element = element.
       
    setTable(table);
        
    //add the top most header    
    PdfPCell headerCell = getFirstHeaderCell( header, noOfColumns);   
    table.addCell(headerCell);    
    // Add the second header row twice
    table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
    getHeaderTitleCell(table.getDefaultCell());
    renderHeaders(table, columnHeaders);
    // There are three special rows 1. Customer Details  2. Two times it displays customer column names one at the header and other in the footer
    table.setHeaderRows(2);
    // One of them is a footer
    //table.setFooterRows(1);  
    
    //set the style for the body    
    getBodyCell(table.getDefaultCell());    
   // renderRecords(table, this.records);
    renderRecords(table, allRecords);
    
    return table;
}
/**
 * Creates a table with screenings.
 * @param connection the database connection
 * @param day a film festival day
 * @return a table with screenings
 * @throws SQLException
 * @throws DocumentException
 * @throws IOException
 */
public ByteArrayOutputStream getPDFInByteArrayOutputStream(String header,String filename,float columnWidth[],
		int noOfColumns,List<String> columnHeaders,List<List<String>> allRecords)
    throws SQLException, DocumentException, IOException { 
	
	
	
	createDocument();	
	
	openDocument();
	
	PdfPTable pdfTable = createPdfTable(header, filename, columnWidth, noOfColumns, columnHeaders, allRecords);
	
	addPdfPTableToDocument(pdfTable, filename);
	
	getDocument().close();
	
    return getByteArrayOutputStream();
}

	
public PdfPTable createPdfTable(String header,String filename,float columnWidth[],
		int noOfColumns,List<String> columnHeaders,List<List<String>> allRecords)
    throws SQLException, DocumentException, IOException { 
    // step 4 
	PdfPTable table = constructPDFTable(header, filename, columnWidth, noOfColumns, columnHeaders, allRecords);		   
	
	return table;
}

	protected void setTable(PdfPTable table){
		  //settings for table
	    table.setWidthPercentage(100f);
	    table.getDefaultCell().setUseAscender(true);
	    table.getDefaultCell().setUseDescender(true);
	}
	
	protected PdfPCell getFirstHeaderCell(String header,int noofHeaders){
		Font f = new Font();
		f.setColor(BaseColor.BLACK);
		f.setSize(8);
		//f.setFamily(FontFamily.TIMES_ROMAN);
		PdfPCell headerCell = new PdfPCell(new Phrase(header, f));        
		headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		headerCell.setColspan(noofHeaders);
	    return headerCell;
	}
	
	protected void getHeaderTitleCell(PdfPCell headerTitleCell){
		Font f = new Font();
		f.setSize(7);
	    f.setColor(BaseColor.BLACK);
		//PdfPCell headerCell = new PdfPCell(new Phrase(f));        
	    headerTitleCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    headerTitleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    //headerTitleCell.setColspan(noofHeaders);
	    //return headerTitleCell;
	}

	private void renderHeaders(PdfPTable table , List<String> columnHeaderNames){
		for (int i = 0; i < 1; i++) {	
			for(String columnHeaders : columnHeaderNames){
				table.addCell(columnHeaders);
			}        
		}
	}
/**
 * renderRecords method.
 * PdfPTable ars no arguments needed
 * @throws DocumentException
 * @throws IOException
 * @throws SQLException
 */
private void renderRecords(PdfPTable table , List<List<String>> allRecords){
	 // Now let's loop over the customer details    
    for(List<String> customerList : allRecords){                
            for(String value: customerList){
                            table.addCell(value);        
        }                        
    }
}
	/**
	private void renderRecords(PdfPTable table , List<String> allRecords){
		 // Now let's loop over the customer details    
	    //for(List<String> customerList : allRecords){  
		String  record[];
	            for(String recordWithDelimitter: allRecords){
	            	record = recordWithDelimitter.split(BankConstant.DELIMITER);
	            	for(String columnvalues: record){
	            		 table.addCell(columnvalues);
	            	}
	                                   
	        }                        
	    //}
	}*/
/**
 * Main method.
 * @param args no arguments needed
 * @throws DocumentException
 * @throws IOException
 * @throws SQLException
 */
public static void main(String[] args)
    throws SQLException, DocumentException, IOException {
	/*PDFUtility utility = new PDFUtility(searchMasterScreenForm);
	utility.getPDFInByteArrayOutputStream("CUSTOMER DETAILS",RESULT,
			new float[] { 2, 1, 2, 4, 2, 2, 2 },
			utility.columnHeaders.size(),utility.columnHeaders,utility.noOfRecords);*/
	//PDFTableUtility utility = new PDFTableUtility();
	//utility.getBillInPDF();
	
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
       
       /* this.columnHeaders.add("Name");
        this.columnHeaders.add("Address");
        this.columnHeaders.add("Area");
        this.columnHeaders.add("District");
        this.columnHeaders.add("Mobile");
        this.columnHeaders.add("Email");
        this.columnHeaders.add("Misc");*/
}

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
		
		  /* for(int i=0;i<2;i++){
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
}            */

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

	public String getSearchTableDetails() {
		return this.searchTableDetails;
	}

	public void setSearchTableDetails(String searchTableDetails) {
		this.searchTableDetails = searchTableDetails;
	}	
	 
}


