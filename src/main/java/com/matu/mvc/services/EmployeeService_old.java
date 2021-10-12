//package com.matu.mvc.services;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.List;
//
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.poi.hssf.record.Margin;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Chunk;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.Image;
//import com.itextpdf.text.PageSize;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.matu.mvc.models.Employer;
//import com.matu.mvc.models.WAFAIMA_Contrats;
//import com.matu.mvc.repositories.EmployerRpository;
//import com.matu.mvc.repositories.WAFAIMA_ContratsRpository;
//
//@Service
//@Transactional
//public class WAFAIMA_ContratsService {
//	private final WAFAIMA_ContratsRpository contrarrepository;
//
//	@Autowired
//	public WAFAIMA_ContratsService(WAFAIMA_ContratsRpository contrarrepository) {
//		this.contrarrepository = contrarrepository;
//	}
//
//	public List<WAFAIMA_Contrats> getAllEmployee() {
//		return contrarrepository.clients_ayant_offers();
//	}
//	
//	public WAFAIMA_Contrats get_client_by_ida_ffaire() {
//		return contrarrepository.affaire_formule(1377539);
//	}
//	
//	
//	
//
//	
//	
//	public boolean createPdf(List<WAFAIMA_Contrats> employees, ServletContext context, HttpServletRequest request,
//			HttpServletResponse response) {
//		Document document = new Document(PageSize.A3.rotate(), 10, 10, 10, 10);
//		try {
//			String filePath = context.getRealPath("/resources/report");
//			File file = new File(filePath);
//			boolean exists = new File(filePath).exists();
//			if (!exists) {
//				new File(filePath).mkdirs();
//			}
//			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "employees" + ".pdf"));
//			document.open();
//
//			Image img = Image.getInstance("https://www.wafaassurance.ma/sites/default/files/x2fichier_23x.png");
//			// img.setAbsolutePosition(450f, 10f);
//			img.setAlignment(Element.ALIGN_RIGHT);
//			img.scaleAbsolute(200, 50);
//			
//			Font mainFont = FontFactory.getFont("Arial", 12, BaseColor.BLACK);
//			Font mainFont2 = FontFactory.getFont("Arial", 12, BaseColor.RED);
//			Font mainFont3 = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
//
//			Paragraph paragraph = new Paragraph("EXTRAIT DES CONDITIONS PARTICULIERES DU CONTRAT", mainFont);
//			paragraph.setAlignment(Element.ALIGN_CENTER);
//			paragraph.setIndentationLeft(50);
//			paragraph.setIndentationRight(50);
//			paragraph.setPaddingTop(500);
//			paragraph.setSpacingBefore(-20);
//			// paragraph.setSpacingAfter(10);
//
//			Paragraph paragraph2 = new Paragraph(
//					"VEHICULE TOURISME & C1 - CONTRAT ASSASS ASSISTANCE 1 an (RC + BGD et/ou INC et/ou VOL)",
//					mainFont2);
//			paragraph2.setAlignment(Element.ALIGN_CENTER);
//			paragraph2.setIndentationLeft(50);
//			paragraph2.setIndentationRight(50);
//			paragraph2.setPaddingTop(-50);
//			paragraph2.setSpacingAfter(10);
//
//			Paragraph paragraph3 = new Paragraph(
//					"  Wafa Ima Assistance - Entreprise régie par loi N° 17-99 Portant Code des Assurances,Sa au capital de 50 000 000 DH"
//							+ "\n - Adresse : Casablanca Business Centre - lot n°2 lotissement mandaouna,sidi Maarouf "
//							+ " - Tél : 05 22 58 29 00-R.C/CASA N°119935"
//							+ "\n Le présent contrat,objet de la décision de l'autorité de controle des assurances et de la prévoyance sociale n°...."
//							+ " en date du ............ \n est composé des présentes conditions particuliéres et des conditions générales ci-jointes."
//							+ "\n N° de contrat : .............................................................. ",
//					mainFont3);
////            public static final Font BLUE_BOLD =
////            	    new Font("Arial", 12, Font.BOLD, BaseColor.BLUE);
////            public static final Chunk chunk = new Chunk("11993", mainFont3);
//			paragraph3.setAlignment(Element.ALIGN_LEFT);
////            paragraph3.setIndentationLeft(50);
////            paragraph3.setIndentationRight(50);
//			paragraph3.setPaddingTop(100);
//			paragraph3.setSpacingAfter(10);
//
//			document.add(img);
//			document.add(paragraph);
//			document.add(paragraph2);
//			document.add(paragraph3);
//
//			PdfPTable table = new PdfPTable(2);// column amount
//			table.setWidthPercentage(50);
//			table.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table.setSpacingBefore(10f);
//			table.setSpacingAfter(10);
//
//			Font tableHeader = FontFactory.getFont("Arial", 12, BaseColor.BLACK);
//			Font tableBody = FontFactory.getFont("Arial", 9, BaseColor.BLACK);
//
//			float[] columnWidths = { 2, 2 };
//
//			table.setWidths(columnWidths);
//
//			PdfPCell name = new PdfPCell(new Paragraph("Souscripteur", tableHeader));
//			name.setBorderColor(BaseColor.BLACK);
//			name.setPaddingLeft(5);
//			name.setHorizontalAlignment(Element.ALIGN_CENTER);
//			name.setVerticalAlignment(Element.ALIGN_CENTER);
//			name.setBackgroundColor(BaseColor.ORANGE);
//			name.setExtraParagraphSpace(2f);
//			table.addCell(name);
//
//			PdfPCell email = new PdfPCell(new Paragraph("Assuré", tableHeader));
//			email.setBorderColor(BaseColor.BLACK);
//			email.setPaddingLeft(5);
//			email.setHorizontalAlignment(Element.ALIGN_CENTER);
//			email.setVerticalAlignment(Element.ALIGN_CENTER);
//			email.setBackgroundColor(BaseColor.ORANGE);
//			email.setExtraParagraphSpace(5f);
//			table.addCell(email);
//
////            for (Employer employee : employees){
//			PdfPCell nameValue = new PdfPCell(new Paragraph(
//					"Mutelle d'assurance des transports Unis 215,Bd Mohamed Zerktouni Casablanca", tableHeader));
//			nameValue.setBorderColor(BaseColor.BLACK);
//			nameValue.setPaddingTop(20);
//			nameValue.setHorizontalAlignment(Element.ALIGN_CENTER);
//			nameValue.setVerticalAlignment(Element.ALIGN_CENTER);
//			nameValue.setBackgroundColor(BaseColor.WHITE);
//			nameValue.setExtraParagraphSpace(7f);
//			table.addCell(nameValue);
//
//			PdfPCell emailValue = new PdfPCell(new Paragraph(
//					"" + " Nom et Prénom / RS : ..........................................................."
//							+ " \n Date de commerce : ................/............../............................."
//							+ "\n Adresse domicile : ................................................................"
//							+ "\n Code postale/Ville : .............................................................."
//							+ "\n Téléphone : .......................... Email :....................................",
//					tableBody));
//			emailValue.setBorderColor(BaseColor.BLACK);
////                emailValue.setPaddingLeft(10);
//			emailValue.setHorizontalAlignment(Element.ALIGN_LEFT);
//			emailValue.setVerticalAlignment(Element.ALIGN_RIGHT);
//			emailValue.setBackgroundColor(BaseColor.WHITE);
//			emailValue.setExtraParagraphSpace(5f);
//			table.addCell(emailValue);
//
//			PdfPTable table_garanties = new PdfPTable(3);
//
//			table_garanties.setWidthPercentage(50);
//			table_garanties.setSpacingBefore(-50);
//			table_garanties.setHorizontalAlignment(Element.ALIGN_RIGHT);
//			table_garanties.setWidthPercentage(250 / 5.23f);
//
//			table_garanties.setSpacingBefore(10f);
//			table_garanties.setSpacingAfter(10);
//
//			// On créer l'objet cellule.
//			// PdfPCell cell;
//
////			PdfPCell prestation = new PdfPCell(
////					new Paragraph("Principales prestations d'assistance garanties", tableHeader));
////			prestation.setBorderColor(BaseColor.BLACK);
////			prestation.setPaddingLeft(5);
////
////			prestation.setHorizontalAlignment(Element.ALIGN_CENTER);
////			prestation.setVerticalAlignment(Element.ALIGN_CENTER);
////			prestation.setBackgroundColor(BaseColor.ORANGE);
////			prestation.setExtraParagraphSpace(2f);
////			prestation.setColspan(4);
////			table_garanties.addCell(prestation);
////
//////                PdfPCell nnn = new PdfPCell(new Paragraph("Souscripteur", tableHeader));
//////                nnn.setRowspan(2);
//////                table2.addCell(nnn);
////			// contenu du tableau.
//////                for(int aw=0;aw<1 ; aw++){   
////			table_garanties.addCell("");
////			table_garanties.addCell("Accident");
////			table_garanties.addCell("Panne");
////			table_garanties.addCell("Aide de Costat");
////			table_garanties.addCell("Illimité");
////			table_garanties.addCell("Non");
////			table_garanties.addCell("Remorquage");
////			table_garanties.addCell("Garage au choix,dan un rayon maximum de 250 km."
////					+ "\n - Maximm 2 fois par an");
////			table_garanties.addCell(" - Garage le plus proche du domicile dans un rayon maximum 250 km  "
////					+ "\n - Maximum 1 fois par an");
////			
//               
////			}
////                PdfPCell mobileValue = new PdfPCell(new Paragraph(employee.getService(), tableHeader));
////                mobileValue.setBorderColor(BaseColor.BLACK);
////                mobileValue.setPaddingLeft(10);
////                mobileValue.setHorizontalAlignment(Element.ALIGN_CENTER);
////                mobileValue.setVerticalAlignment(Element.ALIGN_CENTER);
////                mobileValue.setBackgroundColor(BaseColor.WHITE);
////                mobileValue.setExtraParagraphSpace(5f);
////                table.addCell(mobileValue);
//
////                PdfPCell addressValue = new PdfPCell(new Paragraph(employee.getDirection(), tableHeader));
////                addressValue.setBorderColor(BaseColor.BLACK);
////                addressValue.setPaddingLeft(10);
////                addressValue.setHorizontalAlignment(Element.ALIGN_CENTER);
////                addressValue.setVerticalAlignment(Element.ALIGN_CENTER);
////                addressValue.setBackgroundColor(BaseColor.WHITE);
////                addressValue.setExtraParagraphSpace(5f);
////                table.addCell(addressValue);
////            }
////                On créer un objet table dans lequel on intialise ça taille.
//			PdfPTable table2 = new PdfPTable(4);
//
//			table2.setWidthPercentage(50);
//			table2.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table2.setSpacingBefore(10f);
//			table2.setSpacingAfter(10);
//
//			// On créer l'objet cellule.
////			PdfPCell cell;
//
//			PdfPCell vehicule = new PdfPCell(new Paragraph("Véhicule Assuré", tableHeader));
//			vehicule.setBorderColor(BaseColor.BLACK);
//			vehicule.setPaddingLeft(5);
//
//			vehicule.setHorizontalAlignment(Element.ALIGN_CENTER);
//			vehicule.setVerticalAlignment(Element.ALIGN_CENTER);
//			vehicule.setBackgroundColor(BaseColor.ORANGE);
//			vehicule.setExtraParagraphSpace(2f);
//			vehicule.setColspan(4);
//			table2.addCell(vehicule);
//
////                PdfPCell nnn = new PdfPCell(new Paragraph("Souscripteur", tableHeader));
////                nnn.setRowspan(2);
////                table2.addCell(nnn);
//			// contenu du tableau.
////                for(int aw=0;aw<1 ; aw++){   
//			table2.addCell("Marque");
//			table2.addCell("....................");
//			table2.addCell("Model");
//			table2.addCell("....................");
//			table2.addCell("Immatriculation");
//			table2.addCell(".....................");
//			table2.addCell("Energie");
//			table2.addCell("......................");
//			table2.addCell("Usage");
//			table2.addCell(".......................");
//			table2.addCell("Formule");
//			table2.addCell(".......................");
//			table2.addCell("Date de mise en circulation");
//			table2.addCell(".......................");
//			table2.addCell("Catégoriede véhicule");
//			table2.addCell(".......................");
//			table2.addCell("Nombre de places");
//			table2.addCell(".......................");
//			table2.addCell("PTC");
//			table2.addCell(".......................");
////                }
//
//			PdfPTable table3 = new PdfPTable(1);
//
//			table3.setWidthPercentage(50);
//			table3.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table3.setSpacingBefore(10f);
//			table3.setSpacingAfter(10);
////                
//
//			PdfPCell contrat = new PdfPCell(new Paragraph(
//					"Autres contrats d'assurance / d'assistance couvrant les memes risques", tableHeader));
//			contrat.setBorderColor(BaseColor.BLACK);
//			contrat.setPaddingLeft(5);
//			contrat.setHorizontalAlignment(Element.ALIGN_CENTER);
//			contrat.setVerticalAlignment(Element.ALIGN_CENTER);
//			contrat.setBackgroundColor(BaseColor.ORANGE);
//			contrat.setExtraParagraphSpace(2f);
//			contrat.setColspan(4);
//			table3.addCell(contrat);
//
//			PdfPCell contrat1 = new PdfPCell(new Paragraph("Contrat N° :", tableBody));
//			contrat1.setBorderColor(BaseColor.BLACK);
////                emailValue.setPaddingLeft(10);
//			contrat1.setHorizontalAlignment(Element.ALIGN_LEFT);
//			contrat1.setVerticalAlignment(Element.ALIGN_LEFT);
//			contrat1.setBackgroundColor(BaseColor.WHITE);
//			contrat1.setExtraParagraphSpace(5f);
//			table3.addCell(contrat1);
//			PdfPCell contrat2 = new PdfPCell(new Paragraph("Contrat N° :", tableBody));
//			contrat2.setBorderColor(BaseColor.BLACK);
////                emailValue.setPaddingLeft(10);
//			contrat2.setHorizontalAlignment(Element.ALIGN_LEFT);
//			contrat2.setVerticalAlignment(Element.ALIGN_LEFT);
//			contrat2.setBackgroundColor(BaseColor.WHITE);
//			contrat2.setExtraParagraphSpace(5f);
//			table3.addCell(contrat2);
//
//			Paragraph paragraph4 = new Paragraph(
//					"Les circonstances susceptibles d'aggraver les risques sont : ........................................................",
//					mainFont3);
//
//			paragraph4.setPaddingTop(100);
//			paragraph4.setSpacingAfter(10);
//
//			PdfPTable table4 = new PdfPTable(1);
//
//			table4.setWidthPercentage(50);
//			table4.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table4.setSpacingBefore(10f);
//			table4.setSpacingAfter(10);
////                
//
//			PdfPCell effet = new PdfPCell(new Paragraph(
//					"Effet de contrat : ..../..../..........                    Date d'expiration de contrat : ..../..../..........",
//					tableHeader));
//			effet.setBorderColor(BaseColor.BLACK);
//			effet.setPaddingLeft(5);
//			effet.setHorizontalAlignment(Element.ALIGN_CENTER);
//			effet.setVerticalAlignment(Element.ALIGN_CENTER);
//			effet.setBackgroundColor(BaseColor.ORANGE);
//			effet.setExtraParagraphSpace(2f);
//			effet.setColspan(4);
//			table4.addCell(effet);
//
//			PdfPCell loi_effet = new PdfPCell(new Paragraph(
//					"Les garanties sont acquises aux personnes assurées pour la période allant de la date d'effet"
//							+ "j'ausqu'à la date d'éxpération indiquées aux conditions particuliéres du présent contrat."
//							+ "\n les prestations d'assistance sont acquise, en cas de panne, au delà de quinze (15) jours suivant la date d'effer du contrat."
//							+ "\n la souscripteur certifie l'exactitude et la sincérité des déclarations ci dessus."
//							+ "\n le souscripteur déclare quee l'assuré a lu et accepté la clausee relative au<<PROTECTION DES DONNEES A CARACTERE PERSONNEL >>"
//							+ "	prévue au paragraphe 6 du titre [ VII dispositions particuliéres ] des conditions générales. :",
//					tableBody));
//			loi_effet.setBorderColor(BaseColor.BLACK);
////                emailValue.setPaddingLeft(10);
//			loi_effet.setHorizontalAlignment(Element.ALIGN_LEFT);
//			loi_effet.setVerticalAlignment(Element.ALIGN_LEFT);
//			loi_effet.setBackgroundColor(BaseColor.WHITE);
//			loi_effet.setExtraParagraphSpace(5f);
//			table4.addCell(loi_effet);
//
//			PdfPTable table5 = new PdfPTable(1);
//
//			table5.setWidthPercentage(50);
//			table5.setHorizontalAlignment(Element.ALIGN_LEFT);
//			table5.setSpacingBefore(10f);
//			table5.setSpacingAfter(10);
////                
//
//			PdfPCell sinistre = new PdfPCell(new Paragraph("Déclaration de sinistre", tableHeader));
//			sinistre.setBorderColor(BaseColor.BLACK);
//			sinistre.setPaddingLeft(5);
//			sinistre.setHorizontalAlignment(Element.ALIGN_CENTER);
//			sinistre.setVerticalAlignment(Element.ALIGN_CENTER);
//			sinistre.setBackgroundColor(BaseColor.ORANGE);
//			sinistre.setExtraParagraphSpace(2f);
//			sinistre.setColspan(4);
//			table5.addCell(sinistre);
//
//			PdfPCell loi_sinistre = new PdfPCell(new Paragraph(
//					" Dés la survenance du sinitre et sauf cas fortuit ou de force majeure,l'assuré doit déclarer"
//							+ ",sous peine de déchéance au plus tar dans les (5) cinque jours"
//							+ "\n de suvenance,aupré de Wafa Ima assistance,afin qu'elle puisse le faire bénéficier des prestations garanties par le contrat. ",
//					tableBody));
//			loi_sinistre.setBorderColor(BaseColor.BLACK);
////                emailValue.setPaddingLeft(10);
//			loi_sinistre.setHorizontalAlignment(Element.ALIGN_LEFT);
//			loi_sinistre.setVerticalAlignment(Element.ALIGN_LEFT);
//			loi_sinistre.setBackgroundColor(BaseColor.WHITE);
//			loi_sinistre.setExtraParagraphSpace(5f);
//			table5.addCell(loi_sinistre);
//
//			document.add(table_garanties);
//			document.add(table);
//			document.add(table2);
//			document.add(table3);
//			document.add(paragraph4);
//			document.add(table4);
//			document.add(table5);
//			document.close();
//			writer.close();
//			return true;
//
//		} catch (Exception e) {
//			return false;
//		}
//	}
//
//	public boolean createExcell(List<Employer> employees, ServletContext context, HttpServletRequest request,
//			HttpServletResponse response) {
//
//		String filePath = context.getRealPath("/resources/report");
//		File file = new File(filePath);
//		boolean exists = new File(filePath).exists();
//		if (!exists) {
//			new File(filePath).mkdirs();
//		}
//		try {
//			FileOutputStream outputStream = new FileOutputStream(file + "/" + "employees" + ".xls");
//			HSSFWorkbook workbook = new HSSFWorkbook();
//			HSSFSheet workSheet = workbook.createSheet("employees");
//			workSheet.setDefaultColumnWidth(30);
//
//			HSSFCellStyle headerCellStyle = workbook.createCellStyle();
//			headerCellStyle.setFillBackgroundColor(HSSFColor.BLUE.index);
////            headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//
//			HSSFRow headerRow = workSheet.createRow(0);
//
//			HSSFCell name = headerRow.createCell(0);
//			name.setCellStyle(headerCellStyle);
//
//			HSSFCell email = headerRow.createCell(1);
//			email.setCellStyle(headerCellStyle);
//
////            HSSFCell mobile = headerRow.createCell(2);
////            mobile.setCellStyle(headerCellStyle);
////
////            HSSFCell address = headerRow.createCell(3);
////            address.setCellStyle(headerCellStyle);
//
//			Integer i = 1;
//
//			for (Employer employee : employees) {
//				HSSFRow bodyRow = workSheet.createRow(i);
//
//				HSSFCellStyle bodyCellStyle = workbook.createCellStyle();
//				bodyCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
//
//				HSSFCell nameValue = bodyRow.createCell(0);
//				nameValue.setCellValue(employee.getNom());
//				nameValue.setCellStyle(bodyCellStyle);
//
//				HSSFCell emailValue = bodyRow.createCell(1);
//				emailValue.setCellValue(employee.getDirection());
//				emailValue.setCellStyle(bodyCellStyle);
//
//				HSSFCell mobileValue = bodyRow.createCell(2);
//				mobileValue.setCellValue(employee.getService());
//				mobileValue.setCellStyle(bodyCellStyle);
//
//				HSSFCell addressValue = bodyRow.createCell(3);
//				addressValue.setCellValue(employee.getDirection());
//				addressValue.setCellStyle(bodyCellStyle);
//
//				i++;
//			}
//
//			workbook.write(outputStream);
//			outputStream.flush();
//			outputStream.close();
//			return true;
//
//		} catch (Exception e) {
//			return false;
//		}
//	}
//}
