package fis.his.co.batach.process;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fis.his.co.ed.model.EligibilityModel;

public  class Pdf {
	
	static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
	public static void pdfGenForPlanAp(EligibilityModel model) throws FileNotFoundException, DocumentException {
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("D:\\HIS_Plan_PDF\\"+model.getPlanStatus()+model.getCaseNumber()+".pdf"));
		
		//open the document
		document.open();
		
		//create a paragraph
		Paragraph parh = new Paragraph();
		parh.add("Approved Plan\n");
		parh.setAlignment(Element.ALIGN_CENTER);
		
		//Add paragraph to document
		
		document.add(parh);
		
		//create a table to mention all the details
		PdfPTable table = new PdfPTable(2);
		//add 1st row
		table.addCell(new Paragraph("Case Number :"));
		table.addCell(new Paragraph(model.getCaseNumber().toString()));
		
		//add 2nd row 
		table.addCell(new Paragraph("Plan name :"));
		table.addCell(new Paragraph(model.getPlanName()));
		
		//add 3rd row
		table.addCell(new Paragraph("Plan Status :"));
		table.addCell(new Paragraph("Approved"));
		
		//add 4th row
		table.addCell(new Paragraph("Plan start date :"));
		table.addCell(new Paragraph(sdf.format(model.getPlanStartDate())));
		
		//add 5th row
		table.addCell(new Paragraph("Plan End date :"));
		table.addCell(new Paragraph(sdf.format(model.getPlanEndDate())));
		
		//add 6th row
		table.addCell(new Paragraph("Benifit Amount :"));
		table.addCell(new Paragraph("$"+model.getBenifitAmount().toString()));
		
		//add table to document
		document.add(table);
		
		//document closed
		document.close();
	}
	
	public static void pdfGenForPlanDn(EligibilityModel model) throws FileNotFoundException, DocumentException {
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("D:/HIS_Plan_PDF"+model.getPlanStatus()+model.getCaseNumber()+".pdf"));
		
		//open the document
		document.open();
		
		//create a paragraph
		Paragraph parh = new Paragraph();
		parh.add("Approved Plan");
		parh.setAlignment(Element.ALIGN_CENTER);
		
		//Add paragraph to document
		
		document.add(parh);
		
		//create a table to mention all the details
		PdfPTable table = new PdfPTable(2);
		//add 1st row
		table.addCell(new Paragraph("Case Number :"));
		table.addCell(new Paragraph(model.getCaseNumber().toString()));
		
		//add 2nd row 
		table.addCell(new Paragraph("Plan name :"));
		table.addCell(new Paragraph(model.getPlanName()));
		
		//add 3rd row
		table.addCell(new Paragraph("Plan Status :"));
		table.addCell(new Paragraph("Denied"));
		
		//add 4th row
		table.addCell(new Paragraph("Plan start date :"));
		table.addCell(new Paragraph(model.getDeniedReason()));
		
		//add table to document
		document.add(table);
		
		//document closed
		document.close();
	}
	
}
