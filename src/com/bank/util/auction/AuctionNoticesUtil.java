package com.bank.util.auction;



	import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bank.bo.CustomerBO;
import com.bank.bo.SearchMasterScreenBO;
import com.bank.form.SearchMasterScreenForm;
import com.bank.helper.ParameterHelper;
import com.bank.util.BankConstant;
import com.bank.util.PDFTableUtility;
import com.bank.util.SortingUtil;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

	
	/**
	 * Strings are objects which represent immutable arrays of
	 * characters.
	 *
	 * @author 		 MaheshKumar Bhawarlal
	 * @version		
	 *
	 * @see			
	 */
	public class AuctionNoticesUtil extends PDFTableUtility{
	    /** The log variable, gets the log details */
	    
		private List<List<SearchMasterScreenBO>>  searchMasterScreenBOs; 		
		
		public List<List<SearchMasterScreenBO>> getSearchMasterScreenBOs() {
			return searchMasterScreenBOs;
		}

		public void setSearchMasterScreenBOs(
				List<List<SearchMasterScreenBO>> searchMasterScreenBOs) {
			this.searchMasterScreenBOs = searchMasterScreenBOs;
			
		}

		public void setSearchMasters(List<SearchMasterScreenBO> searchMasterScreenBOs) {					
			Collections.sort(searchMasterScreenBOs,SortingUtil.sortByCustomerNameInSearchMasterScreenBO);
			List<SearchMasterScreenBO> groupByCustomerList = new ArrayList<SearchMasterScreenBO>();
			List<List<SearchMasterScreenBO>> completeSearchMasterScreenBoList = new ArrayList();
			String customerName=null;
			for(SearchMasterScreenBO searchMasterScreenBO : searchMasterScreenBOs){				
				if( (customerName==null) || (customerName!=null && searchMasterScreenBO.getSearch3().equalsIgnoreCase(customerName))){
					groupByCustomerList.add(searchMasterScreenBO);
					//System.out.println("if:"+searchMasterScreenBO.getSearch3());
				}else{
					completeSearchMasterScreenBoList.add(groupByCustomerList);
					groupByCustomerList = new ArrayList<SearchMasterScreenBO>();
					groupByCustomerList.add(searchMasterScreenBO);
					//System.out.println("else:"+searchMasterScreenBO.getSearch3());
				}
				customerName = searchMasterScreenBO.getSearch3();
			}
			completeSearchMasterScreenBoList.add(groupByCustomerList);
			setSearchMasterScreenBOs(completeSearchMasterScreenBoList);            
		}
		
		public AuctionNoticesUtil(SearchMasterScreenForm searchMasterScreenForm){
			super(searchMasterScreenForm);		
			setSearchMasters(searchMasterScreenForm.getSearchMasterScreenBOList());
		}
		
		public ByteArrayOutputStream getPDFAuctionNotices(String header,String filename,float columnWidth[],
				int noOfColumns,List<String> columnHeaders,List<List<String>> allRecords,CustomerBO customerBO, 
				ParameterHelper parameters)
		    throws SQLException, DocumentException, IOException { 
			
			createDocument();	
			
			openDocument();
			
			
			//System.out.println("searchMasterScreenBO.getSearch3()size:"+this.searchMasterScreenBOs.size());
			for(List list : this.searchMasterScreenBOs){
				List<SearchMasterScreenBO> searchMasterList = list;
				System.out.println("searchMasterList:size:"+searchMasterList.size());
				allRecords = new ArrayList<List<String>>();
				newPageInDocument();
				for(SearchMasterScreenBO searchMasterScreenBO : searchMasterList){
					List<String> customerList = new ArrayList<String>();
					customerList.add(searchMasterScreenBO.getSearch1());
					customerList.add(searchMasterScreenBO.getSearch2());
					customerList.add(searchMasterScreenBO.getSearch3());
					customerList.add(searchMasterScreenBO.getSearch4());
					customerList.add(searchMasterScreenBO.getSearch5());
					customerList.add(searchMasterScreenBO.getSearch6());
					customerList.add(searchMasterScreenBO.getSearch9());
					customerList.add(searchMasterScreenBO.getSearch10());
					customerList.add(searchMasterScreenBO.getSearch7());
					customerList.add(searchMasterScreenBO.getSearch8());
					allRecords.add(customerList);	
					
					//System.out.println("searchMasterScreenBO.getSearch3():"+searchMasterScreenBO.getSearch3());
					
					customerBO = searchMasterScreenBO.getCustomerBO();
					/*customerBO.setName(searchMasterScreenBO.getSearch3());
					String address = searchMasterScreenBO.getSearch4();
					
					customerBO.setAddress(address.i);
					customerBO.setArea(searchMasterScreenBO.getSearch3());
					customerBO.setStreet(searchMasterScreenBO.getSearch3());
					customerBO.setPincode(new Integer("600039"));*/
				}				
			
				setNoticeHeader(parameters);
				
				getDocument().add(  new Chunk(new String(parameters.getAuctionDescription().getBytes(), BankConstant.CHARACTER_ENCODING)));	
				
				PdfPTable pdfTable = createPdfTable(header, filename, columnWidth, noOfColumns, columnHeaders, allRecords);
				
				addPdfPTableToDocument(pdfTable, filename);
				
				//document.add(new Paragraph("Hello World!"));		
				
				newPageInDocument();
								
				setAddressInBottom(getPdfWriter(), customerBO, parameters);
								
			}
			
			newPageInDocument();
			
			getDocument().close();
			//ByteArrayOutputStream byteArrayOutputStream = documentToOutputStream(document);
			
			
		    return getByteArrayOutputStream();
		}
		
		private void setNoticeHeader(ParameterHelper parameters) throws DocumentException{
			//Paragraph shopName = new Paragraph(new Phrase(new Chunk("கட்டற்ற கலைக்களஞ்சியமான விக்கிப்பீடியாவில் இருந்து")));
			Paragraph shopName = new Paragraph(parameters.getShopName());
			shopName.setAlignment(Element.ALIGN_CENTER);			
			getDocument().add(shopName);
						
			String businessTypes = new String(parameters.getShopType());
			Paragraph businessType = new Paragraph(businessTypes);
			businessType.setAlignment(Element.ALIGN_CENTER);			
			getDocument().add(businessType);
			
			Paragraph shopStreet = new Paragraph(parameters.getShopNumber() + parameters.getShopStreet());
			shopStreet.setAlignment(Element.ALIGN_CENTER);			
			getDocument().add(shopStreet);			
			
			Paragraph shopArea = new Paragraph(parameters.getShopArea());
			shopArea.setAlignment(Element.ALIGN_CENTER);			
			getDocument().add(shopArea);		
			
			Paragraph shopCity = new Paragraph(parameters.getShopCity() + " - " + parameters.getShopPinCode());
			shopCity.setAlignment(Element.ALIGN_CENTER);			
			getDocument().add(shopCity);
            
		}

	    /**
	     * @param filename
	     * @throws DocumentException
	     * @throws IOException
	     * @throws SQLException 
	     */
	    public static void createPdf(String filename)
	    throws  DocumentException, IOException, SQLException {
		//  // step 1
	        Document document = new Document();
	        // step 2
	        PdfWriter writer = PdfWriter.getInstance(document,
	                new FileOutputStream(filename));
	        // step 3
	        document.open();
	        // step 4
	      //  document.add(new HeaderFooter1().getTable("Customer Details"));
	        
	        document.newPage();
	        
	        //setAddressInBottom(writer);
	        
	        
	        // step 5        
	        document.close();

	}
	    
	  
	    
	    private static void setAddressInBottom( PdfWriter writer ,CustomerBO customerBO , ParameterHelper parameters)
	    		throws DocumentException, IOException{
		  // draw helper lines
	        PdfContentByte cb = writer.getDirectContent();
	         cb.setLineWidth(0f);
	        BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN,BaseFont.CP1252, false);
	        cb.beginText();
	        int fontsize = 20;
	        int bottomMargin = 100;
	        int leftMargin = 50;
	        int rightMargin = 430;
	        cb.setFontAndSize(bf, fontsize);
	        
	        //while(){
	            //From Address
	            cb.setTextMatrix(leftMargin, bottomMargin + (fontsize * 1));cb.showText(parameters.getShopCity() + " - " + parameters.getShopPinCode());
	            cb.setTextMatrix(leftMargin, bottomMargin + (fontsize * 2));cb.showText(parameters.getShopArea());
	            cb.setTextMatrix(leftMargin, bottomMargin + (fontsize * 3));cb.showText(parameters.getShopNumber() + parameters.getShopStreet());
	            cb.setTextMatrix(leftMargin, bottomMargin + (fontsize * 4));cb.showText(parameters.getShopName());
	            cb.setTextMatrix(leftMargin, bottomMargin + (fontsize * 5));cb.showText(BankConstant.FROM);    
	       
	         
	            //To Address
	            cb.setTextMatrix(rightMargin, bottomMargin + (fontsize * 1));cb.showText(customerBO.getDistrict() + " - "+ customerBO.getPincode());
	            cb.setTextMatrix(rightMargin, bottomMargin + (fontsize * 2));cb.showText(customerBO.getArea());
	            cb.setTextMatrix(rightMargin, bottomMargin + (fontsize * 3));cb.showText(customerBO.getAddress() + ", "+ customerBO.getStreet());
	            cb.setTextMatrix(rightMargin, bottomMargin + (fontsize * 4));cb.showText(customerBO.getName());
	            cb.setTextMatrix(rightMargin, bottomMargin + (fontsize * 5));cb.showText(BankConstant.TO);
	           
	        //}
	                
	        
	        
	        //cb.setTextMatrix(50, 800);cb.showText("Top line");
	        
	        /*cb.showTextAligned(PdfContentByte.ALIGN_CENTER, text + " Center", 150, 760, 0);
	        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, text + " Right", 150, 700, 0);
	        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, text + " Left", 150, 640, 0);
	        cb.showTextAlignedKerned(PdfContentByte.ALIGN_LEFT, text + " Left", 150, 628, 0);
	        cb.setTextMatrix(0, 1, -1, 0, 300, 600);
	        cb.showText("Position 300,600, rotated 90 degrees.");
	        for (int i = 0; i < 360; i += 30) {
	            cb.showTextAligned(PdfContentByte.ALIGN_LEFT, text, 400, 700, i);
	        }*/
	        cb.endText();
	    }
	    /** The resulting PDF file. */
	    public static final String RESULT = "C:\\address.pdf";

	    
	    public static void main(String args[]) throws DocumentException, IOException, SQLException{
		createPdf(RESULT);
	    }
	    
}