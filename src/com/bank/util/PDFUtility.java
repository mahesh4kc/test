package com.bank.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class PDFUtility extends ResponseUtility
{
    
/** The resulting PDF file. */
public static final String RESULT = "c:/header_footer_1.pdf";

private ByteArrayOutputStream byteArrayOutputStream;

private Document document;

private PdfWriter pdfWriter;


	public PdfWriter getPdfWriter() {
	return pdfWriter;
}


public void setPdfWriter(PdfWriter pdfWriter) {
	this.pdfWriter = pdfWriter;
}


	public Document getDocument() {
	return document;
}


public void setDocument(Document document) {
	this.document = document;
}


	public void setByteArrayOutputStream(ByteArrayOutputStream byteArrayOutputStream) throws DocumentException{				
		setPdfWriter(PdfWriter.getInstance(getDocument(), byteArrayOutputStream));
		this.byteArrayOutputStream = byteArrayOutputStream;
}


	public ByteArrayOutputStream getByteArrayOutputStream() {		
		return byteArrayOutputStream;
}
	
	
	protected void createDocument() throws DocumentException,FileNotFoundException{
		  // step 1
        Document document = new Document(com.itextpdf.text.PageSize.A4.rotate());
        // step 2
        setDocument(document);
        
        setByteArrayOutputStream(new ByteArrayOutputStream());
        
        //return getDocument();
	}
	
	protected void openDocument(){
		 // step 3
        getDocument().open();
	}
	
	protected void newPageInDocument(){
		 // step 3
       getDocument().newPage();
	}
	
	 //document.newPage();
	
	protected void addPdfPTableToDocument(PdfPTable table,String filename) throws DocumentException,FileNotFoundException{
		
        
       
        // step 4
        getDocument().add(table);
	    //document.newPage();
      
		
		// step 1
	    //Document document = new Document(com.itextpdf.text.PageSize.A4.rotate());
	    // step 2
	    //PdfWriter.getInstance(document, new java.io.FileOutputStream(filename));
	    // step 3
	    //document.open();
	 // step 5
	   
	   
	    
	    //return document;
	}
	
	
	
	
	protected void getBodyCell(PdfPCell bodyCell){
		Font f = new Font();
		f.setSize(7);
	    f.setColor(BaseColor.BLACK);
		//PdfPCell headerCell = new PdfPCell(new Phrase(f));        
	    bodyCell.setBackgroundColor(null);
	    bodyCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    //headerTitleCell.setColspan(noofHeaders);
	    //return headerTitleCell;
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
	PDFUtility utility = new PDFUtility();
	utility.getBillInPDF();
	
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
	
	/**
	 * @return
	 * @throws DocumentException
	 * @throws FileNotFoundException
	 */
	private ByteArrayOutputStream getBillInPDF() throws DocumentException,FileNotFoundException{
	
		// step 1
      Document document = new Document(com.itextpdf.text.PageSize.A4.rotate());
      // step 2
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      
      //PdfWriter.getInstance(document, baos);
      PdfWriter.getInstance(document, new FileOutputStream("c:/abcd.pdf"));
      // step 3
      document.open();
      
   // step 4
      PdfPTable createBillTopHeaderTable = createBillTopHeader();
      document.add(createBillTopHeaderTable);
      
      PdfPTable createBillSerialDateTable = createBillSerialDate();
      document.add(createBillSerialDateTable);
      
      
      PdfPTable createBillHeaderTable = createBillHeader();
      document.add(createBillHeaderTable);
      
      PdfPTable createBillDetailTable = createBillDetail();
      document.add(createBillDetailTable);
      
      PdfPTable createBillFooterTable = createBillFooter();
      document.add(createBillFooterTable);
      
      PdfPTable createBillSignatureTable = createBillSignature();
      document.add(createBillSignatureTable);
      
      
      //table.setSpacingBefore(5);
     // table.setSpacingAfter(5);
     // document.add(table);
      
      // step 4
     // document.add(table);
	    document.newPage();
	    document.add(new Phrase(new Chunk("fyufruyfyuகட்டற்ற")));
	    document.newPage();
      // step 5
      //document.close();

		
		// step 1
	    //Document document = new Document(com.itextpdf.text.PageSize.A4.rotate());
	    // step 2
	    //PdfWriter.getInstance(document, new java.io.FileOutputStream(filename));
	    // step 3
	    //document.open();
	 // step 5
	   
	    document.close(); 
	    return baos;
	}
	
	 public PdfPTable createBillTopHeader() throws DocumentException {
	        PdfPTable table = new PdfPTable(1);
	        table.setWidthPercentage(288 / 5.23f);
	        table.setWidths(new int[]{4});
	        PdfPCell cell;
	        cell = new PdfPCell(new Phrase("D.Bhawarlal Choudhary"));
	        //cell.setColspan(3);        
	        table.addCell(cell);
	        return table;
	    } 
	 
	 public PdfPTable createBillSerialDate() throws DocumentException {
		 PdfPTable table = new PdfPTable(4);
	        table.setWidthPercentage(288 / 5.23f);
	        table.setWidths(new int[]{1, 1, 1, 1});
	        PdfPCell cell;
	        cell = new PdfPCell(new Phrase("Bill No"));
	        //cell.setColspan(3);        
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("F:1234"));
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Date"));
	        //cell.setColspan(3);        
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("29/04/2011"));
	        table.addCell(cell);
	        return table;
	 }
	 
	 public PdfPTable createBillHeader() throws DocumentException {		 
		 
		 	PdfPTable table = new PdfPTable(2);
	        table.setWidthPercentage(288 / 5.23f);
	        table.setWidths(new int[]{1, 3});
	        PdfPCell cell;
	       
	        cell = new PdfPCell(new Phrase("Customer Name"));
	        //cell.setColspan(3);        
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Mery"));
	        //cell.setRowspan(2);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Amount"));
	        //cell.setColspan(3);        
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Rs.1000"));
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Amount in words"));
	        //cell.setColspan(3);        
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("One Thousand Rupees only"));
	        table.addCell(cell);
	        return table;
	    } 
	 
	 public PdfPTable createBillDetail() throws DocumentException {
	        PdfPTable table = new PdfPTable(3);
	        table.setWidthPercentage(288 / 5.23f);
	        table.setWidths(new int[]{1, 2, 1});
	        PdfPCell cell;
	        cell = new PdfPCell(new Phrase("S.No"));
	        //cell.setColspan(3);        
	        table.addCell(cell);	        
	        cell = new PdfPCell(new Phrase("Product Desc"));
	        //cell.setColspan(3);        
	        table.addCell(cell);	        
	        cell = new PdfPCell(new Phrase("Gms"));
	        //cell.setColspan(3);        
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("1"));
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("One Gold Plain Ring"));
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("12"));
	        //cell.setRowspan(2);
	        table.addCell(cell);
	        return table;
	    } 
	 
	 public PdfPTable createBillFooter() throws DocumentException {
	        PdfPTable table = new PdfPTable(2);
	        table.setWidthPercentage(288 / 5.23f);
	        table.setWidths(new int[]{1, 3});
	        PdfPCell cell;
	        cell = new PdfPCell(new Phrase("Amount"));
	        //cell.setColspan(3);        
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Rs.1000"));
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Present Value"));
	        //cell.setColspan(3);        
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Rs.1200"));
	        table.addCell(cell);
	        return table;
	    } 
	 
	 public PdfPTable createBillSignature() throws DocumentException {
	        PdfPTable table = new PdfPTable(4);
	        table.setWidthPercentage(288 / 5.23f);
	        table.setWidths(new int[]{1, 1, 1, 1});
	        PdfPCell cell;
	        cell = new PdfPCell(new Phrase("Sign. of Pawner"));
	        //cell.setColspan(3);        
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase(""));
	        //cell.setRowspan(2);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase(new Chunk("கட்டற்ற")));
	        //cell.setColspan(3);        
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("ணோ"));
	        //cell.setRowspan(2);
	        table.addCell(cell);
	        return table;
	    } 
	 
	
	 
}


