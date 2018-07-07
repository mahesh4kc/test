package com.bank.util.auction;


		/*
	 * This class is part of the book "iText in Action - 2nd Edition"
	 * written by Bruno Lowagie (ISBN: 9781935182610)
	 * For more info, go to: http://itextpdf.com/examples/
	 * This example only works with the AGPL version of iText.
	 */


	import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class AuctionHeaderFooter {
/*
		public List<String> columnNames;
		
		public List<List> noOfRecords;
		
	    *//** The resulting PDF file. *//*
	    public static final String RESULT = "header_footer_1.pdf";

	    *//**
	     * Creates a PDF document.
	     * @param filename the path to the new PDF document
	     * @throws    DocumentException 
	     * @throws    IOException
	     * @throws    SQLException
	     *//*
	    public void createPdf(String filename)
	        throws SQLException, DocumentException, IOException {
	    	// create a database connection
//	        DatabaseConnection connection = new HsqldbConnection("filmfestival");
	        // step 1
	        Document document = new Document(PageSize.A4.rotate());
	        // step 2
	        PdfWriter.getInstance(document, new FileOutputStream(filename));
	        // step 3
	        document.open();
	        // step 4
	        List<String> header=new ArrayList<String>();
	        header.add(new String("Customer Details"));        
	        for (String header1 : header) {
	            document.add(getTable(header1));
	            document.newPage();
	        }
	        // step 5
	        document.close();
	        // close the database connection
	        //connection.close();

	    }

	    *//**
	     * Creates a table with screenings.
	     * @param connection the database connection
	     * @param day a film festival day
	     * @return a table with screenings
	     * @throws SQLException
	     * @throws DocumentException
	     * @throws IOException
	     *//*
	    public PdfPTable getTable(String header)
	        throws SQLException, DocumentException, IOException {
	    	// Create a table with 7 columns
	        PdfPTable table = new PdfPTable(new float[] { 2, 1, 2, 4, 2, 2, 2 });
	        table.setWidthPercentage(100f);
	        table.getDefaultCell().setUseAscender(true);
	        table.getDefaultCell().setUseDescender(true);        
	        PdfPCell cell = new PdfPCell(new Phrase(header, getFont()));        
	        //cell.setBackgroundColor(Color.BLACK);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        
	        cell.setColspan(7);
	        table.addCell(cell);
	        // Add the second header row twice
	        //table.getDefaultCell().setBackgroundColor(Color.BLACK);
	        getFont();
	        
	        setColumnNames();
	        for (int i = 0; i < 2; i++) {
	        	for(String columnHeaders : this.columnNames){
	            	table.addCell(getPhrase(columnHeaders)); 
	            }	
	        }
	       
	        table.getDefaultCell().setBackgroundColor(null);
	        // There are three special rows 1. Customer Details  2. Two times it displays customer column names one at the header and other in the footer
	        table.setHeaderRows(3);
	        // One of them is a footer
	        table.setFooterRows(1);
	        
	        
	        setColumnValues();
	        // Now let's loop over the customer details    
	        for(List<String> customerList : this.noOfRecords){        	
	        	for(String value: customerList){
	        	    table.addCell(getPhrase(value)); 
	            }        	        
	        }
	        return table;
	    }

	    *//**
	     * Main method.
	     * @param args no arguments needed
	     * @throws DocumentException 
	     * @throws IOException
	     * @throws SQLException
	     *//*
	    public static void main(String[] args)
	        throws SQLException, DocumentException, IOException {
	        //new AuctionAddress().createPdf("c:\\header_footer_1.pdf");
	    }
	    
	    private Font getFont() throws DocumentException, IOException{
		// Add the first header row        
		    Font f = new Font(BaseFont.createFont(BaseFont.TIMES_ROMAN,BaseFont.CP1252, false));
		    //Font f = new Font();
		    f.setSize(8);
		    f.setColor(BaseColor.BLACK);
		    return f;
	    }
	    
	    private Phrase getPhrase(String value)
	    throws SQLException, DocumentException, IOException {
	    Phrase text = new Phrase();
	    text.add(new Chunk(value,getFont()));
	    return text;
	    }
	    
	    private void setColumnNames(){
	    	List<String> columnNames = new ArrayList<String>();
	    	columnNames.add("Name");
	    	columnNames.add("Address");
	    	columnNames.add("Area");
	    	columnNames.add("District");
	    	columnNames.add("Mobile");
	    	columnNames.add("Email");
	    	columnNames.add("Misc");
	    	this.columnNames = columnNames;
	    }

		public List<String> getColumnNames() {
			return this.columnNames;
		}

		public void setColumnNames(List<String> columnNames) {
			this.columnNames = columnNames;
		}
	    
		private void setColumnValues(){
			// add the no of rows that will retrieve from the database
			this.noOfRecords = new ArrayList<List>();
			for(int i=0;i<2;i++){
				List<String> columnValues = new ArrayList<String>();
		    	columnValues.add("Name1");
		    	columnValues.add("Address1");
		    	columnValues.add("Area1");
		    	columnValues.add("District1");
		    	columnValues.add("Mobile1");
		    	columnValues.add("Email1");
		    	columnValues.add("Misc1");
		    	this.noOfRecords.add(columnValues);
			}    	
	    	
	    }

		public List<List> getNoOfRecords() {
			return this.noOfRecords;
		}

		public void setNoOfRecords(List<List> noOfRecords) {
			this.noOfRecords = noOfRecords;
		}*/
	}