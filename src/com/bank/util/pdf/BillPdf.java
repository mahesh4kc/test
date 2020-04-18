 package com.bank.util.pdf;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.bank.bo.BillDetailBO;
import com.bank.form.BillForm;
import com.bank.helper.ParameterHelper;
import com.bank.util.PDFUtility;
import com.bank.util.ResponseUtility;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class BillPdf extends ResponseUtility{

	public BillPdf(){}
	public BillPdf(BillForm billForm, ParameterHelper parameterHelper){
		this.billForm = billForm;
		this.parameterHelper = parameterHelper;
	}

	public BillForm billForm;
	public ParameterHelper parameterHelper;
	
	/**
	 * @return
	 * @throws DocumentException
	 * @throws FileNotFoundException
	 */
	public ByteArrayOutputStream getBillInPDF() throws DocumentException, FileNotFoundException {

		// step 1
		Document document = new Document(com.itextpdf.text.PageSize.A4.rotate());
		// step 2
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		PdfWriter.getInstance(document, baos);
		//PdfWriter.getInstance(document, new FileOutputStream("e:/abcd.pdf"));
		// step 3
		document.open();
		
		createBill(document);
		// step 7
		document.newPage();
		//Rules & Regulations
		createDeliveryNote(document);
		document.newPage();
		createBill(document);
		
		
		// document.add(new Phrase(new
		// Chunk("fyufruyfyuà®•à®Ÿà¯�à®Ÿà®±à¯�à®±")));
		document.newPage();
		createDeliveryNote(document);

		// step 5
		// document.close();

		document.close();
		return baos;
	}

	public void createBill(Document document) throws DocumentException{
		PDFUtility.createEmptyLines(document,1);
		// step 4
		createBillTopHeader(document);
		
		PDFUtility.createEmptyLines(document,1);
		createBillHeader(document);
		PDFUtility.createEmptyLines(document,1);
		// step 5
		PdfPTable createBillDetailTable = createBillDetail();
		document.add(createBillDetailTable);
		PDFUtility.createEmptyLines(document,1);
		// Step 6
		createBillFooter(document);
		createBillSignatureSymbol(document);
		PDFUtility.createEmptyLines(document,2);
		createBillSignature(document);


	}
	public void createDeliveryNote(Document document) throws DocumentException{
		PDFUtility.createEmptyLines(document,2);
		
		PDFUtility.createLineItem(document, 16, Element.ALIGN_CENTER, "FORM - D");
		PDFUtility.createLineItem(document, 12, Element.ALIGN_LEFT, "I am receiving the articles without the pawn ticket" );
		PDFUtility.createLineItem(document, 12, Element.ALIGN_LEFT, "in full satisfaction ( Bill No. "+ 
		this.billForm.getBillHeaderBO().getBillSerial() + " " + 
				this.billForm.getBillHeaderBO().getBillNumber() + ")");
		PDFUtility.createEmptyLines(document,4);
		PDFUtility.createLineItem(document, 14, Element.ALIGN_RIGHT, "Signature of Pawner");
		PDFUtility.createEmptyLines(document,2);
		
		Paragraph deliveryNoteHeaderParagraph = new Paragraph("DELIVERY NOTE",PDFUtility.underlineFont);
		deliveryNoteHeaderParagraph.setAlignment(Element.ALIGN_CENTER);
		document.add(deliveryNoteHeaderParagraph);	
		PDFUtility.createEmptyLines(document,2);
		String deliveryNoteMessage[] = {"I have this day Paid Rs. ............... ", 
				"towards Principal and Interest Rs. ................",
		"have recieved the article / articles mentioned overleaf in full satisfaction"};
		for (int i = 0; i < deliveryNoteMessage.length; i++) {
			PDFUtility.createLineItem(document, 12, Element.ALIGN_LEFT, deliveryNoteMessage[i]);
			//document.add(new Paragraph(new Chunk(deliveryNoteMessage[i])));	
		}
		
		PDFUtility.createEmptyLines(document,4);
		
		String[] deliveryDate = { "Date: ...............", "Total Rs. ................"};
		int[] deliveryDatealignment= { Element.ALIGN_LEFT, Element.ALIGN_RIGHT };

		PDFUtility.createLineItem(document, deliveryDate, deliveryDatealignment, 12);
		PDFUtility.createEmptyLines(document,4);
		PDFUtility.createLineItem(document, 12, Element.ALIGN_RIGHT, "Signature or L.T.I. of Pawner");
		

	}

	
	public void createBillTopHeader(Document document) throws DocumentException {
		String[] arr = { "Form F(Sec.7 & Rule 8)", "PAWN TICKET", this.parameterHelper.getShopLicence() };
		int[] allignmentArr = { Element.ALIGN_LEFT, Element.ALIGN_CENTER, Element.ALIGN_RIGHT };

		PDFUtility.createLineItem(document, arr, allignmentArr, 9);
		PDFUtility.createLineItem(document, 16, Element.ALIGN_CENTER, this.parameterHelper.getShopName());
		PDFUtility.createLineItem(document, 14, Element.ALIGN_CENTER, this.parameterHelper.getShopBusiness());
		PDFUtility.createLineItem(document, 12, Element.ALIGN_CENTER, this.parameterHelper.getShopAddress());
		PDFUtility.createLineItem(document, 12, Element.ALIGN_CENTER, "(" + this.parameterHelper.getShopDistrictState() + ")" );
	}

	public void createBillHeader(Document document) throws DocumentException {
		String billNo= this.billForm.getBillHeaderBO().getBillSerial() + "  " + this.billForm.getBillHeaderBO().getBillNumber();
		String billSerial[] = { "Bill No: "+ billNo, "Date: "+ this.billForm.getBillHeaderBO().getBillDate() };
		int alignment[] = { Element.ALIGN_LEFT, Element.ALIGN_RIGHT };

		PDFUtility.createLineItem(document, billSerial, alignment, 12);
		PDFUtility.createEmptyLines(document, 1);
		PDFUtility.createLineItem(document, 12, Element.ALIGN_LEFT, "Name of the Pawner......."+this.billForm.getCustomerName());
		PDFUtility.createLineItem(document, 12, Element.ALIGN_LEFT, "Full Address......."+this.billForm.getCustomerAddress());
		PDFUtility.createEmptyLines(document, 1);
		PDFUtility.createLineItem(document, 12, Element.ALIGN_LEFT, "Amount of Principal Loan.... Rs. "+this.billForm.getBillHeaderBO().getAmount());
		PDFUtility.createLineItem(document, 12, Element.ALIGN_LEFT, "Rupees in words.....One Thousand Rupees Only"+this.billForm.getBillHeaderBO().getAmountInWords());
		PDFUtility.createEmptyLines(document, 1);
		PDFUtility.createLineItem(document, 12, Element.ALIGN_LEFT, "The Rate of Interest charged at 16% per annum");
		PDFUtility.createLineItem(document, 16, Element.ALIGN_LEFT, "The following article / articles is /are pawned with me : 				TIME 1 YEAR");

	}

	public PdfPTable createBillDetail() throws DocumentException {
		PdfPTable table = new PdfPTable(3);
//		//table.setWidthPercentage(288 / 5.23f);
		table.setWidths(new int[] { 1, 2, 1 });
		PdfPCell cell;
		cell = new PdfPCell(new Phrase("Particulars",PDFUtility.mainTitleFont));
		cell.setColspan(3);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("S.No",PDFUtility.subtitleFont));
		// cell.setColspan(3);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("Product Description",PDFUtility.subtitleFont));
		// cell.setColspan(3);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("Weight in Gms.",PDFUtility.subtitleFont));
		// cell.setColspan(3);
		table.addCell(cell);	
		for (int i = 0; i < this.billForm.getBillDetailList().size(); i++) {
			BillDetailBO billDetailBO = this.billForm.getBillDetailList().get(i);
			cell = new PdfPCell(new Phrase(billDetailBO.getProductNumber().toString(),PDFUtility.subtitleFont));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(billDetailBO.getProductQuantity()+billDetailBO.getProductDescription(),PDFUtility.subtitleFont));
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(this.billForm.getBillHeaderBO().getGrams().toString(),PDFUtility.subtitleFont));
			// cell.setRowspan(2);
			table.addCell(cell);	
		}
		
		return table;
	}

	public void createBillFooter(Document document) throws DocumentException {
		PDFUtility.createLineItem(document, 12, Element.ALIGN_LEFT, "Present Value Rs......"+this.billForm.getBillHeaderBO().getPresentValue());
		PDFUtility.createLineItem(document, 12, Element.ALIGN_LEFT, "My Family Monthly Income Rs....."+this.billForm.getBillHeaderBO().getMonthlyIncome());
	}

	public void createBillSignatureSymbol(Document document) throws DocumentException {
		PDFUtility.createLineItem(document, 20, Element.ALIGN_CENTER, "==>>");
	}
	
	public void createBillSignature(Document document) throws DocumentException {
		String billSignature[] = { "Sign. of Pawn Broker/Agent", "Sign. of L.T.I. of the Pawner" };
		int alignment[] = { Element.ALIGN_LEFT, Element.ALIGN_RIGHT };
		PDFUtility.createLineItem(document, billSignature, alignment, 12);
		String billSignatureFooter[] = { "", "Interests to be paid once in every 3 months" };
		PDFUtility.createLineItem(document, billSignatureFooter, alignment, 12);
		//PDFUtility.createLineItem(document, 12, Element.ALIGN_LEFT, "Interests to be paid once in every 3 months.");
	}



}
