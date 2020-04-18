package com.bank.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import com.bank.form.BillForm;
import com.bank.helper.ParameterHelper;
import com.bank.util.pdf.BillPdf;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFUtility extends ResponseUtility {

	public static final Font underlineFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.UNDERLINE);
	public static final Font subtitleFont = FontFactory.getFont(Font.FontFamily.TIMES_ROMAN.toString(), 12, BaseColor.BLACK);
	public static final Font smallFont = FontFactory.getFont(Font.FontFamily.TIMES_ROMAN.toString(), 9, BaseColor.BLACK);
	public static final Font mainTitleFont = FontFactory.getFont(Font.FontFamily.TIMES_ROMAN.toString(), 16, BaseColor.BLACK);
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

	public void setByteArrayOutputStream(ByteArrayOutputStream byteArrayOutputStream) throws DocumentException {
		setPdfWriter(PdfWriter.getInstance(getDocument(), byteArrayOutputStream));
		this.byteArrayOutputStream = byteArrayOutputStream;
	}

	public ByteArrayOutputStream getByteArrayOutputStream() {
		return byteArrayOutputStream;
	}

	protected void createDocument() throws DocumentException, FileNotFoundException {
		// step 1
		Document document = new Document(com.itextpdf.text.PageSize.A4.rotate());
		// step 2
		setDocument(document);

		setByteArrayOutputStream(new ByteArrayOutputStream());

		// return getDocument();
	}

	protected void openDocument() {
		// step 3
		getDocument().open();
	}

	protected void newPageInDocument() {
		// step 3
		getDocument().newPage();
	}

	// document.newPage();

	protected void addPdfPTableToDocument(PdfPTable table, String filename)
			throws DocumentException, FileNotFoundException {

		// step 4
		getDocument().add(table);
		// document.newPage();

		// step 1
		// Document document = new
		// Document(com.itextpdf.text.PageSize.A4.rotate());
		// step 2
		// PdfWriter.getInstance(document, new
		// java.io.FileOutputStream(filename));
		// step 3
		// document.open();
		// step 5

		// return document;
	}

	protected void getBodyCell(PdfPCell bodyCell) {
		Font f = new Font();
		f.setSize(7);
		f.setColor(BaseColor.BLACK);
		// PdfPCell headerCell = new PdfPCell(new Phrase(f));
		bodyCell.setBackgroundColor(null);
		bodyCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		// headerTitleCell.setColspan(noofHeaders);
		// return headerTitleCell;
	}

	/**
	 * private void renderRecords(PdfPTable table , List<String> allRecords){ //
	 * Now let's loop over the customer details //for(List<String> customerList
	 * : allRecords){ String record[]; for(String recordWithDelimitter:
	 * allRecords){ record = recordWithDelimitter.split(BankConstant.DELIMITER);
	 * for(String columnvalues: record){ table.addCell(columnvalues); }
	 * 
	 * } //} }
	 */
	/**
	 * Main method.
	 * 
	 * @param args
	 *            no arguments needed
	 * @throws DocumentException
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException, DocumentException, IOException {
		/*
		 * PDFUtility utility = new PDFUtility(searchMasterScreenForm);
		 * utility.getPDFInByteArrayOutputStream("CUSTOMER DETAILS",RESULT, new
		 * float[] { 2, 1, 2, 4, 2, 2, 2 },
		 * utility.columnHeaders.size(),utility.columnHeaders,utility.
		 * noOfRecords);
		 */
		BillPdf utility = new BillPdf();
		ParameterHelper parameterHelper = new ParameterHelper(null, null);
		BillForm billForm = new BillForm();
		
		utility.getBillInPDF();

	}

	/*
	 * private void setColumnValues(){ // add the no of rows that will retrieve
	 * from the database this.records = new ArrayList<String>(); for(int
	 * i=0;i<2;i++){ // List<String> columnValues = new ArrayList<String>();
	 * StringBuffer record = new StringBuffer();
	 * record.append("Name1"+BankConstant.DELIMITER);
	 * record.append("Address1"+BankConstant.DELIMITER);
	 * record.append("Area1"+BankConstant.DELIMITER);
	 * record.append("District1"+BankConstant.DELIMITER);
	 * record.append("Mobile1"+BankConstant.DELIMITER);
	 * record.append("Email1"+BankConstant.DELIMITER); record.append("Misc1");
	 * this.records.add(record.toString()); }
	 * 
	 * }
	 */

	

	public static void createLineItem(Document document, String[] text, int[] textAlignment, int fontSize)
			throws DocumentException {
		
		PdfPTable table = new PdfPTable(text.length);
		for (int j = 0; j < text.length; j++) {
			PdfPCell cell = new PdfPCell(new Phrase(text[j], fontSize == 9 ? smallFont : subtitleFont));
			cell.setHorizontalAlignment(textAlignment[j]);
			cell.setBorder(PdfPCell.NO_BORDER);
			table.addCell(cell);
		}
		document.add(table);
	}

	public static void createLineItem(Document document, int fontSize, int alignmentType, String text)
			throws DocumentException {
		
		PdfPTable table = new PdfPTable(1);
		PdfPCell cell;
		cell = new PdfPCell(new Phrase(text, fontSize == 16 ? mainTitleFont : subtitleFont));
		cell.setHorizontalAlignment(alignmentType);
		cell.setBorder(PdfPCell.NO_BORDER);
		table.addCell(cell);
		document.add(table);
	}

	public static void createEmptyLines(Document document, int numberOfLines) throws DocumentException {
		for (int i = 0; i < numberOfLines; i++) {
			document.add(new Paragraph("\n"));	
		}
		
	}
}
