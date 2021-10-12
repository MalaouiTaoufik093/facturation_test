package com.matu.mvc.services;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.border.Border;
import javax.swing.text.TabExpander;

import org.apache.poi.hssf.record.Margin;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.matu.mvc.models.Details_Flotte_Avec_Retrait;
import com.matu.mvc.models.Details_facture;
import com.matu.mvc.models.Details_facture_flotte;
import com.matu.mvc.models.Details_facture_globale;
import com.matu.mvc.models.Edition_Facture_Flotte_Globale;
import com.matu.mvc.models.Facturation_Details_facture_Rd;
import com.matu.mvc.models.Facturation_EditerFactureDetails_Flotte;
import com.matu.mvc.models.Facturation_EditerFactureDetails_Mono;
import com.matu.mvc.models.Facturation_Quittances_Clients_Flotte_Mono;
import com.matu.mvc.models.Facturation_Quittances_Rd;
//import com.matu.mvc.models.Edition_Facture_Flotte_Globale;

import com.matu.mvc.models.Info_Client_Facture;
import com.matu.mvc.models.NtsSites;
import com.matu.mvc.models.OtoFatctureMono;

import com.matu.mvc.repositories.Info_Client_Detail_Repository;
import com.matu.mvc.repositories.OtoFactureMono_Repository;

@Service
@Transactional
@CrossOrigin("*")
public class Facture {

	// *** facture flotte avec retrait ***
	public boolean createPdf_flotte_avec_retrait(List<Facturation_EditerFactureDetails_Flotte> quittances_flotte,List<Details_Flotte_Avec_Retrait> details_flotte_avec_retrait, 
			Info_Client_Facture infoclient, Integer exercice,
			NtsSites infos_site, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		Document document = new Document(PageSize.A4, 15, 15, 45, 30);
		try {
			String filePath = context.getRealPath("/resources/report");
			File file = new File(filePath);
			boolean exists = new File(filePath).exists();
			if (!exists) {
				new File(filePath).mkdirs();
			}
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "employees" + ".pdf"));
			document.open();
			
			PdfContentByte canvas = writer.getDirectContentUnder();
			
			canvas.saveState();
			PdfGState state = new PdfGState();
			state.setFillOpacity(0.6f);
			canvas.setGState(state);
			
			canvas.restoreState();
			Font mainFont = FontFactory.getFont("Calibri", 18, BaseColor.BLUE);
			Font mainFont2 = FontFactory.getFont("Calibri", 11, BaseColor.BLACK);
			Font mainFont3 = FontFactory.getFont("Arial", 18, BaseColor.BLACK);
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy ");
			LocalDateTime now = LocalDateTime.now();
			//LocalDateTime datecreation =  infoclient.getDatecreation();

			
			Paragraph paragraphee = new Paragraph(
					"Facture N°: " + infoclient.getNumfacture() + "\n"
				   + " Date : " + dtf.format(now) , mainFont2);
			paragraphee.setAlignment(Element.ALIGN_RIGHT);
			paragraphee.setIndentationLeft(10);
			paragraphee.setIndentationRight(20);
			
			Font tableBody = FontFactory.getFont("Calibri", 11, BaseColor.BLACK);
			Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11);
	
			
			Paragraph titre1 = new Paragraph("Détaille Flotte de  l'exercice : " + exercice, mainFont);
			titre1.setAlignment(Element.ALIGN_CENTER);
			titre1.setIndentationLeft(50);
			titre1.setIndentationRight(50);
			titre1.setPaddingTop(-200);
			//*** Information de Client ****
			PdfPTable table_infos_client = new PdfPTable(1);
			table_infos_client.setWidthPercentage(60);
			table_infos_client.setHorizontalAlignment(Element.ALIGN_CENTER);
			table_infos_client.setSpacingBefore(10f);
			table_infos_client.setSpacingAfter(10);
			
			PdfPCell titre_infos_client = new PdfPCell(new Paragraph("Information de Client ", boldFont));
			titre_infos_client.setBorderColor(BaseColor.BLACK);
			titre_infos_client.setPaddingLeft(5);
			titre_infos_client.setHorizontalAlignment(Element.ALIGN_CENTER);
			titre_infos_client.setVerticalAlignment(Element.ALIGN_CENTER);
			titre_infos_client.setBackgroundColor(BaseColor.LIGHT_GRAY);
			titre_infos_client.setExtraParagraphSpace(2f);
			titre_infos_client.setColspan(4);
			table_infos_client.addCell(titre_infos_client);
			
			PdfPCell infos_client = new PdfPCell(new Paragraph(
					" Client     : " + infoclient.getClient() + "" + "\n Adresse : " + infoclient.getAdresse()+
					" \n Tél         : " + infoclient.getTelephone() + "\n Email     : " + infoclient.getEmail()+
					" \n Ice         : " + infoclient.getTelephone() ,tableBody));
			infos_client.setBorderColor(BaseColor.BLACK);
			infos_client.setHorizontalAlignment(Element.ALIGN_LEFT);
			infos_client.setVerticalAlignment(Element.ALIGN_LEFT);
			infos_client.setBackgroundColor(BaseColor.WHITE);
			infos_client.setExtraParagraphSpace(5f);
			table_infos_client.addCell(infos_client);
			
			// ***** TABLE Quittances Flottes  (Globale)*****
			PdfPTable table_quittances = new PdfPTable(6);// column amount
			table_quittances.setWidthPercentage(100);
			table_quittances.setSpacingBefore(10f);
			table_quittances.setSpacingAfter(10);
			Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			Font tableBodyy = FontFactory.getFont("Arial", 9, BaseColor.BLACK);
			float[] columnWidths = { 2f, 2f, 2f, 2f, 2f, 2f};
			table_quittances.setWidths(columnWidths);
			//*** titre Header ***
			PdfPCell police = new PdfPCell(new Paragraph("Police", tableHeader));
			police.setBorderColor(BaseColor.BLACK);
			police.setPaddingLeft(10);
			police.setHorizontalAlignment(Element.ALIGN_CENTER);
			police.setVerticalAlignment(Element.ALIGN_CENTER);
			police.setBackgroundColor(BaseColor.LIGHT_GRAY);
			police.setExtraParagraphSpace(5f);
			table_quittances.addCell(police);
			PdfPCell quittance = new PdfPCell(new Paragraph("Quittance", tableHeader));
			quittance.setBorderColor(BaseColor.BLACK);
			quittance.setPaddingLeft(10);
			quittance.setHorizontalAlignment(Element.ALIGN_CENTER);
			quittance.setVerticalAlignment(Element.ALIGN_CENTER);
			quittance.setBackgroundColor(BaseColor.LIGHT_GRAY);
			quittance.setExtraParagraphSpace(5f);
			table_quittances.addCell(quittance);
			PdfPCell emis = new PdfPCell(new Paragraph("Emis", tableHeader));
			emis.setBorderColor(BaseColor.BLACK);
			emis.setPaddingLeft(10);
			emis.setHorizontalAlignment(Element.ALIGN_CENTER);
			emis.setVerticalAlignment(Element.ALIGN_CENTER);
			emis.setBackgroundColor(BaseColor.LIGHT_GRAY);
			emis.setExtraParagraphSpace(5f);
			table_quittances.addCell(emis);
			PdfPCell effet = new PdfPCell(new Paragraph("Effet", tableHeader));
			effet.setBorderColor(BaseColor.BLACK);
			effet.setPaddingLeft(10);
			effet.setHorizontalAlignment(Element.ALIGN_CENTER);
			effet.setVerticalAlignment(Element.ALIGN_CENTER);
			effet.setBackgroundColor(BaseColor.LIGHT_GRAY);
			effet.setExtraParagraphSpace(5f);
			table_quittances.addCell(effet);
			PdfPCell expiration = new PdfPCell(new Paragraph("Expiration", tableHeader));
			expiration.setBorderColor(BaseColor.BLACK);
			expiration.setPaddingLeft(10);
			expiration.setHorizontalAlignment(Element.ALIGN_CENTER);
			expiration.setVerticalAlignment(Element.ALIGN_CENTER);
			expiration.setBackgroundColor(BaseColor.LIGHT_GRAY);
			expiration.setExtraParagraphSpace(5f);
			table_quittances.addCell(expiration);
			
			PdfPCell pttc = new PdfPCell(new Paragraph("Total", tableHeader));
			pttc.setBorderColor(BaseColor.BLACK);
			pttc.setPaddingLeft(10);
			pttc.setHorizontalAlignment(Element.ALIGN_CENTER);
			pttc.setVerticalAlignment(Element.ALIGN_CENTER);
			pttc.setBackgroundColor(BaseColor.LIGHT_GRAY);
			pttc.setExtraParagraphSpace(5f);
			table_quittances.addCell(pttc);
			
			Double total_pht = 0.0;
			Double total_taxe = 0.0;
			Double total_pttc = 0.0;
			String pattern = "###,###.##";
			DecimalFormat decimalFormat = new DecimalFormat(pattern);
			
			for (Facturation_EditerFactureDetails_Flotte quittance_ : quittances_flotte) {
				/*
				 * total_pht += quittance.get(); total_taxe += quittance.getTaxe();
				 */
				 total_pttc += quittance_.getTotal();
				 
				PdfPCell policevalue = new PdfPCell(new Paragraph(quittance_.getPolice().toString(), tableHeader));
				policevalue.setBorderColor(BaseColor.BLACK);
				policevalue.setPaddingLeft(10);
				policevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				policevalue.setVerticalAlignment(Element.ALIGN_CENTER);
				policevalue.setBackgroundColor(BaseColor.WHITE);
				policevalue.setExtraParagraphSpace(5f);
				table_quittances.addCell(policevalue);
				PdfPCell quittancevalue = new PdfPCell(new Paragraph(quittance_.getQuittance().toString(), tableHeader));
				quittancevalue.setBorderColor(BaseColor.BLACK);
				quittancevalue.setPaddingLeft(10);
				quittancevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				quittancevalue.setVerticalAlignment(Element.ALIGN_CENTER);
				quittancevalue.setBackgroundColor(BaseColor.WHITE);
				quittancevalue.setExtraParagraphSpace(5f);
				table_quittances.addCell(quittancevalue);
				PdfPCell emis_value = new PdfPCell(new Paragraph(quittance_.getEmis().toString(), tableHeader));
				emis_value.setBorderColor(BaseColor.BLACK);
				emis_value.setPaddingLeft(10);
				emis_value.setHorizontalAlignment(Element.ALIGN_CENTER);
				emis_value.setVerticalAlignment(Element.ALIGN_CENTER);
				emis_value.setBackgroundColor(BaseColor.WHITE);
				emis_value.setExtraParagraphSpace(5f);
				table_quittances.addCell(emis_value);
				PdfPCell effet_value = new PdfPCell(new Paragraph(quittance_.getEffet().toString(), tableHeader));
				effet_value.setBorderColor(BaseColor.BLACK);
				effet_value.setPaddingLeft(10);
				effet_value.setHorizontalAlignment(Element.ALIGN_CENTER);
				effet_value.setVerticalAlignment(Element.ALIGN_CENTER);
				effet_value.setBackgroundColor(BaseColor.WHITE);
				effet_value.setExtraParagraphSpace(5f);
				table_quittances.addCell(effet_value);
				PdfPCell expiration_value = new PdfPCell(new Paragraph(quittance_.getExpiration().toString(), tableHeader));
				expiration_value.setBorderColor(BaseColor.BLACK);
				expiration_value.setPaddingLeft(10);
				expiration_value.setHorizontalAlignment(Element.ALIGN_CENTER);
				expiration_value.setVerticalAlignment(Element.ALIGN_CENTER);
				expiration_value.setBackgroundColor(BaseColor.WHITE);
				expiration_value.setExtraParagraphSpace(5f);
				table_quittances.addCell(expiration_value);
				PdfPCell pttcvalue = new PdfPCell(new Paragraph(decimalFormat.format(quittance_.getTotal()), tableHeader));
				pttcvalue.setBorderColor(BaseColor.BLACK);
				pttcvalue.setPaddingLeft(10);
				pttcvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				pttcvalue.setVerticalAlignment(Element.ALIGN_CENTER);
				pttcvalue.setBackgroundColor(BaseColor.WHITE);
				pttcvalue.setExtraParagraphSpace(5f);
				table_quittances.addCell(pttcvalue);
			}
			PdfPTable tbl = new PdfPTable(2);
			tbl.setWidthPercentage(40);
			tbl.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl.setSpacingBefore(10f);
			tbl.setSpacingAfter(10);
			PdfPCell cell = new PdfPCell(new Paragraph("Total Prime TTC"));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(5);
			cell.setExtraParagraphSpace(3f);
			cell.setBorder(0);
			
			tbl.addCell(cell);
			cell = new PdfPCell(new Paragraph(decimalFormat.format(total_pttc)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.PINK);
			tbl.addCell(cell);
			tbl.addCell(cell);

			// ***** TABLE Quittances Flottes  (Détails)*****
			Paragraph titre2 = new Paragraph("Quittance globale de l'exercice : " + exercice, mainFont);
			titre2.setAlignment(Element.ALIGN_CENTER);
			titre2.setIndentationLeft(50);
			titre2.setIndentationRight(50);
			titre2.setPaddingTop(-200);
			

			// *** Afféctation au document ***
			document.add(paragraphee);
			document.add(table_infos_client);
			document.add(titre1);
			document.add(table_quittances);
			document.add(tbl);
			//document.add(titre2);
			//document.add(table_quittances_details);
			
			
		
	        document.close();
			writer.close();
			return true;

		} catch (Exception e) {
			return false;
		}
	}
	// *** facture flotte avec retrait (Quittances)  ***
		public boolean createPdf_flotte_avec_retrait_Quittances(List<Details_Flotte_Avec_Retrait> details_flotte_avec_retrait, 
				Info_Client_Facture infoclient, Integer exercice,
				NtsSites infos_site, ServletContext context, HttpServletRequest request,
				HttpServletResponse response) {
			//Document document = new Document(PageSize.A4, 15, 15, 45, 30);
			Document document = new Document(PageSize.A3.rotate(), 30, 30, 10, 10);
			try {
				String filePath = context.getRealPath("/resources/report");
				File file = new File(filePath);
				boolean exists = new File(filePath).exists();
				if (!exists) {
					new File(filePath).mkdirs();
				}
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "employees" + ".pdf"));
				document.open();
				
				PdfContentByte canvas = writer.getDirectContentUnder();
				
				canvas.saveState();
				PdfGState state = new PdfGState();
				state.setFillOpacity(0.6f);
				canvas.setGState(state);
				
				canvas.restoreState();
				Font mainFont = FontFactory.getFont("Calibri", 18, BaseColor.BLUE);
				Font mainFont2 = FontFactory.getFont("Calibri", 11, BaseColor.BLACK);
				Font mainFont3 = FontFactory.getFont("Arial", 18, BaseColor.BLACK);
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy ");
				LocalDateTime now = LocalDateTime.now();
				//LocalDateTime datecreation =  infoclient.getDatecreation();

				
				Paragraph paragraphee = new Paragraph(
						"Facture N°: " + infoclient.getNumfacture() + "\n"
					   + " Date : " + dtf.format(now) , mainFont2);
				paragraphee.setAlignment(Element.ALIGN_RIGHT);
				paragraphee.setIndentationLeft(10);
				paragraphee.setIndentationRight(20);
				
				Font tableBody = FontFactory.getFont("Calibri", 11, BaseColor.BLACK);
				Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11);
		
				
				
				//*** Information de Client ****
				PdfPTable table_infos_client = new PdfPTable(1);
				table_infos_client.setWidthPercentage(60);
				table_infos_client.setHorizontalAlignment(Element.ALIGN_CENTER);
				table_infos_client.setSpacingBefore(10f);
				table_infos_client.setSpacingAfter(10);
				
				PdfPCell titre_infos_client = new PdfPCell(new Paragraph("Information de Client ", boldFont));
				titre_infos_client.setBorderColor(BaseColor.BLACK);
				titre_infos_client.setPaddingLeft(5);
				titre_infos_client.setHorizontalAlignment(Element.ALIGN_CENTER);
				titre_infos_client.setVerticalAlignment(Element.ALIGN_CENTER);
				titre_infos_client.setBackgroundColor(BaseColor.LIGHT_GRAY);
				titre_infos_client.setExtraParagraphSpace(2f);
				titre_infos_client.setColspan(4);
				table_infos_client.addCell(titre_infos_client);
				
				PdfPCell infos_client = new PdfPCell(new Paragraph(
						" Client     : " + infoclient.getClient() + "" + "\n Adresse : " + infoclient.getAdresse()+
						" \n Tél         : " + infoclient.getTelephone() + "\n Email     : " + infoclient.getEmail()+
						" \n Ice         : " + infoclient.getTelephone() ,tableBody));
				infos_client.setBorderColor(BaseColor.BLACK);
				infos_client.setHorizontalAlignment(Element.ALIGN_LEFT);
				infos_client.setVerticalAlignment(Element.ALIGN_LEFT);
				infos_client.setBackgroundColor(BaseColor.WHITE);
				infos_client.setExtraParagraphSpace(5f);
				table_infos_client.addCell(infos_client);
				
				// ***** TABLE Quittances Flottes  (Globale)*****
				PdfPTable table_quittances = new PdfPTable(10);// column amount
				table_quittances.setWidthPercentage(100);
				table_quittances.setSpacingBefore(10f);
				table_quittances.setSpacingAfter(10);
				Font tableHeader = FontFactory.getFont("Calibri", 7, BaseColor.BLACK);
				Font tableBodyy = FontFactory.getFont("Calibri", 7, BaseColor.BLACK);
				float[] columnWidths = { 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f};
				table_quittances.setWidths(columnWidths);
				//*** titre Header ***
				
				
				Double total_pht = 0.0;
				Double total_taxe = 0.0;
				Double total_pttc = 0.0;
				String pattern = "###,###.##";
				DecimalFormat decimalFormat = new DecimalFormat(pattern);
		
			
				// ***** TABLE Quittances Flottes  (Globale)*****
							PdfPTable table_quittances_details = new PdfPTable(20);// column amount
							table_quittances_details.setWidthPercentage(100);
							table_quittances_details.setSpacingBefore(10f);
							table_quittances_details.setSpacingAfter(10);
							
							float[] columnWidths2 = { 2f, 2f, 2f, 2f,2f, 2f,2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f};
							table_quittances.setWidths(columnWidths);
							//*** titre Header ***
							PdfPCell police_details = new PdfPCell(new Paragraph("Attestation", tableHeader));
							police_details.setBorderColor(BaseColor.BLACK);
							police_details.setPaddingLeft(10);
							police_details.setHorizontalAlignment(Element.ALIGN_CENTER);
							police_details.setVerticalAlignment(Element.ALIGN_CENTER);
							police_details.setBackgroundColor(BaseColor.LIGHT_GRAY);
							police_details.setExtraParagraphSpace(5f);
							table_quittances_details.addCell(police_details);
							PdfPCell quittance_details = new PdfPCell(new Paragraph("Quittance", tableHeader));
							quittance_details.setBorderColor(BaseColor.BLACK);
							quittance_details.setPaddingLeft(10);
							quittance_details.setHorizontalAlignment(Element.ALIGN_CENTER);
							quittance_details.setVerticalAlignment(Element.ALIGN_CENTER);
							quittance_details.setBackgroundColor(BaseColor.LIGHT_GRAY);
							quittance_details.setExtraParagraphSpace(5f);
							table_quittances_details.addCell(quittance_details);
							PdfPCell avenant_details = new PdfPCell(new Paragraph("Avenant", tableHeader));
							avenant_details.setBorderColor(BaseColor.BLACK);
							avenant_details.setPaddingLeft(10);
							avenant_details.setHorizontalAlignment(Element.ALIGN_CENTER);
							avenant_details.setVerticalAlignment(Element.ALIGN_CENTER);
							avenant_details.setBackgroundColor(BaseColor.LIGHT_GRAY);
							avenant_details.setExtraParagraphSpace(5f);
							table_quittances_details.addCell(avenant_details);
							PdfPCell matricule = new PdfPCell(new Paragraph("Matricule", tableHeader));
							matricule.setBorderColor(BaseColor.BLACK);
							matricule.setPaddingLeft(10);
							matricule.setHorizontalAlignment(Element.ALIGN_CENTER);
							matricule.setVerticalAlignment(Element.ALIGN_CENTER);
							matricule.setBackgroundColor(BaseColor.LIGHT_GRAY);
							matricule.setExtraParagraphSpace(5f);
							table_quittances_details.addCell(matricule);
							PdfPCell date_effet = new PdfPCell(new Paragraph("Effet", tableHeader));
							date_effet.setBorderColor(BaseColor.BLACK);
							date_effet.setPaddingLeft(10);
							date_effet.setHorizontalAlignment(Element.ALIGN_CENTER);
							date_effet.setVerticalAlignment(Element.ALIGN_CENTER);
							date_effet.setBackgroundColor(BaseColor.LIGHT_GRAY);
							date_effet.setExtraParagraphSpace(5f);
							table_quittances_details.addCell(date_effet);
							PdfPCell date_expiration = new PdfPCell(new Paragraph("Expiration", tableHeader));
							date_expiration.setBorderColor(BaseColor.BLACK);
							date_expiration.setPaddingLeft(10);
							date_expiration.setHorizontalAlignment(Element.ALIGN_CENTER);
							date_expiration.setVerticalAlignment(Element.ALIGN_CENTER);
							date_expiration.setBackgroundColor(BaseColor.LIGHT_GRAY);
							date_expiration.setExtraParagraphSpace(5f);
							table_quittances_details.addCell(date_expiration);
							PdfPCell places = new PdfPCell(new Paragraph("Places", tableHeader));
							places.setBorderColor(BaseColor.BLACK);
							places.setPaddingLeft(10);
							places.setHorizontalAlignment(Element.ALIGN_CENTER);
							places.setVerticalAlignment(Element.ALIGN_CENTER);
							places.setBackgroundColor(BaseColor.LIGHT_GRAY);
							places.setExtraParagraphSpace(5f);
							table_quittances_details.addCell(places);
							
							PdfPCell rc = new PdfPCell(new Paragraph("RC", tableHeader));
							rc.setBorderColor(BaseColor.BLACK);
							rc.setPaddingLeft(10);
							rc.setHorizontalAlignment(Element.ALIGN_CENTER);
							rc.setVerticalAlignment(Element.ALIGN_CENTER);
							rc.setBackgroundColor(BaseColor.LIGHT_GRAY);
							rc.setExtraParagraphSpace(5f);
							table_quittances_details.addCell(rc);
							PdfPCell dr = new PdfPCell(new Paragraph("DR", tableHeader));
							dr.setBorderColor(BaseColor.BLACK);
							dr.setPaddingLeft(10);
							dr.setHorizontalAlignment(Element.ALIGN_CENTER);
							dr.setVerticalAlignment(Element.ALIGN_CENTER);
							dr.setBackgroundColor(BaseColor.LIGHT_GRAY);
							dr.setExtraParagraphSpace(5f);
							table_quittances_details.addCell(dr);
							PdfPCell tierce = new PdfPCell(new Paragraph("Tiérce", tableHeader));
							tierce.setBorderColor(BaseColor.BLACK);
							tierce.setPaddingLeft(10);
							tierce.setHorizontalAlignment(Element.ALIGN_CENTER);
							tierce.setVerticalAlignment(Element.ALIGN_CENTER);
							tierce.setBackgroundColor(BaseColor.LIGHT_GRAY);
							tierce.setExtraParagraphSpace(5f);
							table_quittances_details.addCell(tierce);
							PdfPCell incendie = new PdfPCell(new Paragraph("Incendie", tableHeader));
							incendie.setBorderColor(BaseColor.BLACK);
							incendie.setPaddingLeft(10);
							incendie.setHorizontalAlignment(Element.ALIGN_CENTER);
							incendie.setVerticalAlignment(Element.ALIGN_CENTER);
							incendie.setBackgroundColor(BaseColor.LIGHT_GRAY);
							incendie.setExtraParagraphSpace(5f);
							table_quittances_details.addCell(incendie);
							PdfPCell vol = new PdfPCell(new Paragraph("Vol", tableHeader));
							vol.setBorderColor(BaseColor.BLACK);
							vol.setPaddingLeft(10);
							vol.setHorizontalAlignment(Element.ALIGN_CENTER);
							vol.setVerticalAlignment(Element.ALIGN_CENTER);
							vol.setBackgroundColor(BaseColor.LIGHT_GRAY);
							vol.setExtraParagraphSpace(5f);
							table_quittances_details.addCell(vol);
							PdfPCell bdg = new PdfPCell(new Paragraph("BDG", tableHeader));
							bdg.setBorderColor(BaseColor.BLACK);
							bdg.setPaddingLeft(10);
							bdg.setHorizontalAlignment(Element.ALIGN_CENTER);
							bdg.setVerticalAlignment(Element.ALIGN_CENTER);
							bdg.setBackgroundColor(BaseColor.LIGHT_GRAY);
							bdg.setExtraParagraphSpace(5f);
							table_quittances_details.addCell(bdg);
							PdfPCell dc = new PdfPCell(new Paragraph("DC", tableHeader));
							dc.setBorderColor(BaseColor.BLACK);
							dc.setPaddingLeft(10);
							dc.setHorizontalAlignment(Element.ALIGN_CENTER);
							dc.setVerticalAlignment(Element.ALIGN_CENTER);
							dc.setBackgroundColor(BaseColor.LIGHT_GRAY);
							dc.setExtraParagraphSpace(5f);
							table_quittances_details.addCell(dc);
							PdfPCell autre = new PdfPCell(new Paragraph("Autre", tableHeader));
							autre.setBorderColor(BaseColor.BLACK);
							autre.setPaddingLeft(10);
							autre.setHorizontalAlignment(Element.ALIGN_CENTER);
							autre.setVerticalAlignment(Element.ALIGN_CENTER);
							autre.setBackgroundColor(BaseColor.LIGHT_GRAY);
							autre.setExtraParagraphSpace(5f);
							table_quittances_details.addCell(autre);
							PdfPCell catnat = new PdfPCell(new Paragraph("CantNat", tableHeader));
							catnat.setBorderColor(BaseColor.BLACK);
							catnat.setPaddingLeft(10);
							catnat.setHorizontalAlignment(Element.ALIGN_CENTER);
							catnat.setVerticalAlignment(Element.ALIGN_CENTER);
							catnat.setBackgroundColor(BaseColor.LIGHT_GRAY);
							catnat.setExtraParagraphSpace(5f);
							table_quittances_details.addCell(catnat);
							
							
							PdfPCell taxe = new PdfPCell(new Paragraph("Taxe", tableHeader));
							taxe.setBorderColor(BaseColor.BLACK);
							taxe.setPaddingLeft(10);
							taxe.setHorizontalAlignment(Element.ALIGN_CENTER);
							taxe.setVerticalAlignment(Element.ALIGN_CENTER);
							taxe.setBackgroundColor(BaseColor.LIGHT_GRAY);
							taxe.setExtraParagraphSpace(5f);
							table_quittances_details.addCell(taxe);
							PdfPCell acctim = new PdfPCell(new Paragraph("AccTim", tableHeader));
							acctim.setBorderColor(BaseColor.BLACK);
							acctim.setPaddingLeft(10);
							acctim.setHorizontalAlignment(Element.ALIGN_CENTER);
							acctim.setVerticalAlignment(Element.ALIGN_CENTER);
							acctim.setBackgroundColor(BaseColor.LIGHT_GRAY);
							acctim.setExtraParagraphSpace(5f);
							table_quittances_details.addCell(acctim);
							
							
							PdfPCell cot_ht = new PdfPCell(new Paragraph("Cot_HT", tableHeader));
							cot_ht.setBorderColor(BaseColor.BLACK);
							cot_ht.setPaddingLeft(10);
							cot_ht.setHorizontalAlignment(Element.ALIGN_CENTER);
							cot_ht.setVerticalAlignment(Element.ALIGN_CENTER);
							cot_ht.setBackgroundColor(BaseColor.LIGHT_GRAY);
							cot_ht.setExtraParagraphSpace(5f);
							table_quittances_details.addCell(cot_ht);
							PdfPCell cot_ttc = new PdfPCell(new Paragraph("Cot_TTC", tableHeader));
							cot_ttc.setBorderColor(BaseColor.BLACK);
							cot_ttc.setPaddingLeft(10);
							cot_ttc.setHorizontalAlignment(Element.ALIGN_CENTER);
							cot_ttc.setVerticalAlignment(Element.ALIGN_CENTER);
							cot_ttc.setBackgroundColor(BaseColor.LIGHT_GRAY);
							cot_ttc.setExtraParagraphSpace(5f);
							table_quittances_details.addCell(cot_ttc);
							
							
					
							
							
							  for (Details_Flotte_Avec_Retrait dt : details_flotte_avec_retrait)
							  { 
								   total_pht += dt.getCot_Ht(); 
								   total_taxe += dt.getTaxe();
							       total_pttc += dt.getCotttc(); 
							       
							  PdfPCell policevalue = new PdfPCell(new  Paragraph(dt.getAttestation().toString(), tableHeader));
							  policevalue.setBorderColor(BaseColor.BLACK); policevalue.setPaddingLeft(10);
							  policevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
							  policevalue.setVerticalAlignment(Element.ALIGN_CENTER);
							  policevalue.setBackgroundColor(BaseColor.WHITE);
							  policevalue.setExtraParagraphSpace(5f);
							  table_quittances_details.addCell(policevalue); 
							  PdfPCell quittancevalue = new  PdfPCell(new Paragraph(dt.getQuittance().toString(), tableHeader));
							  quittancevalue.setBorderColor(BaseColor.BLACK);
							  quittancevalue.setPaddingLeft(10);
							  quittancevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
							  quittancevalue.setVerticalAlignment(Element.ALIGN_CENTER);
							  quittancevalue.setBackgroundColor(BaseColor.WHITE);
							  quittancevalue.setExtraParagraphSpace(5f);
							  table_quittances_details.addCell(quittancevalue); 
							  PdfPCell pttcvalue = new PdfPCell(new Paragraph(dt.getNatAvenant().toString(), tableHeader));
							  pttcvalue.setBorderColor(BaseColor.BLACK);
							  pttcvalue.setPaddingLeft(10);
							  pttcvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
							  pttcvalue.setVerticalAlignment(Element.ALIGN_CENTER);
							  pttcvalue.setBackgroundColor(BaseColor.WHITE);
							  pttcvalue.setExtraParagraphSpace(5f);
							  table_quittances_details.addCell(pttcvalue);
							  PdfPCell matricule_value = new PdfPCell(new Paragraph(dt.getMatricule().toString(), tableHeader));
							  matricule_value.setBorderColor(BaseColor.BLACK);
							  matricule_value.setPaddingLeft(10);
							  matricule_value.setHorizontalAlignment(Element.ALIGN_CENTER);
							  matricule_value.setVerticalAlignment(Element.ALIGN_CENTER);
							  matricule_value.setBackgroundColor(BaseColor.WHITE);
							  matricule_value.setExtraParagraphSpace(5f);
							  table_quittances_details.addCell(matricule_value);
							  PdfPCell date_effet_value = new PdfPCell(new Paragraph(dt.getDate_effet().toString(), tableHeader));
							  date_effet_value.setBorderColor(BaseColor.BLACK);
							  date_effet_value.setPaddingLeft(10);
							  date_effet_value.setHorizontalAlignment(Element.ALIGN_CENTER);
							  date_effet_value.setVerticalAlignment(Element.ALIGN_CENTER);
							  date_effet_value.setBackgroundColor(BaseColor.WHITE);
							  date_effet_value.setExtraParagraphSpace(5f);
							  table_quittances_details.addCell(date_effet_value);
							  PdfPCell date_expiration_value = new PdfPCell(new Paragraph(dt.getDate_au().toString(), tableHeader));
							  date_expiration_value.setBorderColor(BaseColor.BLACK);
							  date_expiration_value.setPaddingLeft(10);
							  date_expiration_value.setHorizontalAlignment(Element.ALIGN_CENTER);
							  date_expiration_value.setVerticalAlignment(Element.ALIGN_CENTER);
							  date_expiration_value.setBackgroundColor(BaseColor.WHITE);
							  date_expiration_value.setExtraParagraphSpace(5f);
							  table_quittances_details.addCell(date_expiration_value);
							  PdfPCell places_value = new PdfPCell(new Paragraph(dt.getPlaces().toString(), tableHeader));
							  places_value.setBorderColor(BaseColor.BLACK);
							  places_value.setPaddingLeft(10);
							  places_value.setHorizontalAlignment(Element.ALIGN_CENTER);
							  places_value.setVerticalAlignment(Element.ALIGN_CENTER);
							  places_value.setBackgroundColor(BaseColor.WHITE);
							  places_value.setExtraParagraphSpace(5f);
							  table_quittances_details.addCell(places_value);
							  
							  PdfPCell rc_value = new PdfPCell(new Paragraph(decimalFormat.format(dt.getRc()), tableHeader));
							  rc_value.setBorderColor(BaseColor.BLACK);
							  rc_value.setPaddingLeft(10);
							  rc_value.setHorizontalAlignment(Element.ALIGN_CENTER);
							  rc_value.setVerticalAlignment(Element.ALIGN_CENTER);
							  rc_value.setBackgroundColor(BaseColor.WHITE);
							  rc_value.setExtraParagraphSpace(5f);
							  table_quittances_details.addCell(rc_value);
							  PdfPCell dr_value = new PdfPCell(new Paragraph(decimalFormat.format(dt.getDr()), tableHeader));
							  dr_value.setBorderColor(BaseColor.BLACK);
							  dr_value.setPaddingLeft(10);
							  dr_value.setHorizontalAlignment(Element.ALIGN_CENTER);
							  dr_value.setVerticalAlignment(Element.ALIGN_CENTER);
							  dr_value.setBackgroundColor(BaseColor.WHITE);
							  dr_value.setExtraParagraphSpace(5f);
							  table_quittances_details.addCell(dr_value);
							  PdfPCell tierce_value = new PdfPCell(new Paragraph(decimalFormat.format(dt.getTierce()), tableHeader));
							  tierce_value.setBorderColor(BaseColor.BLACK);
							  tierce_value.setPaddingLeft(10);
							  tierce_value.setHorizontalAlignment(Element.ALIGN_CENTER);
							  tierce_value.setVerticalAlignment(Element.ALIGN_CENTER);
							  tierce_value.setBackgroundColor(BaseColor.WHITE);
							  tierce_value.setExtraParagraphSpace(5f);
							  table_quittances_details.addCell(tierce_value);
							  PdfPCell incendie_value = new PdfPCell(new Paragraph(decimalFormat.format(dt.getIncendieinc()), tableHeader));
							  incendie_value.setBorderColor(BaseColor.BLACK);
							  incendie_value.setPaddingLeft(10);
							  incendie_value.setHorizontalAlignment(Element.ALIGN_CENTER);
							  incendie_value.setVerticalAlignment(Element.ALIGN_CENTER);
							  incendie_value.setBackgroundColor(BaseColor.WHITE);
							  incendie_value.setExtraParagraphSpace(5f);
							  table_quittances_details.addCell(incendie_value);
							  
							  PdfPCell vol_value = new PdfPCell(new Paragraph(decimalFormat.format(dt.getVol()), tableHeader));
							  vol_value.setBorderColor(BaseColor.BLACK);
							  vol_value.setPaddingLeft(10);
							  vol_value.setHorizontalAlignment(Element.ALIGN_CENTER);
							  vol_value.setVerticalAlignment(Element.ALIGN_CENTER);
							  vol_value.setBackgroundColor(BaseColor.WHITE);
							  vol_value.setExtraParagraphSpace(5f);
							  table_quittances_details.addCell(vol_value);
							  PdfPCell bdg_value = new PdfPCell(new Paragraph(decimalFormat.format(dt.getBdg()), tableHeader));
							  bdg_value.setBorderColor(BaseColor.BLACK);
							  bdg_value.setPaddingLeft(10);
							  bdg_value.setHorizontalAlignment(Element.ALIGN_CENTER);
							  bdg_value.setVerticalAlignment(Element.ALIGN_CENTER);
							  bdg_value.setBackgroundColor(BaseColor.WHITE);
							  bdg_value.setExtraParagraphSpace(5f);
							  table_quittances_details.addCell(bdg_value);
							  PdfPCell dc_value = new PdfPCell(new Paragraph(decimalFormat.format(dt.getDc()), tableHeader));
							  dc_value.setBorderColor(BaseColor.BLACK);
							  dc_value.setPaddingLeft(10);
							  dc_value.setHorizontalAlignment(Element.ALIGN_CENTER);
							  dc_value.setVerticalAlignment(Element.ALIGN_CENTER);
							  dc_value.setBackgroundColor(BaseColor.WHITE);
							  dc_value.setExtraParagraphSpace(5f);
							  table_quittances_details.addCell(dc_value);
							  PdfPCell autre_value = new PdfPCell(new Paragraph(decimalFormat.format(dt.getAutre()), tableHeader));
							  autre_value.setBorderColor(BaseColor.BLACK);
							  autre_value.setPaddingLeft(10);
							  autre_value.setHorizontalAlignment(Element.ALIGN_CENTER);
							  autre_value.setVerticalAlignment(Element.ALIGN_CENTER);
							  autre_value.setBackgroundColor(BaseColor.WHITE);
							  autre_value.setExtraParagraphSpace(5f);
							  table_quittances_details.addCell(autre_value);
							  PdfPCell catnat_value = new PdfPCell(new Paragraph(decimalFormat.format(dt.getCatnat()), tableHeader));
							  catnat_value.setBorderColor(BaseColor.BLACK);
							  catnat_value.setPaddingLeft(10);
							  catnat_value.setHorizontalAlignment(Element.ALIGN_CENTER);
							  catnat_value.setVerticalAlignment(Element.ALIGN_CENTER);
							  catnat_value.setBackgroundColor(BaseColor.WHITE);
							  catnat_value.setExtraParagraphSpace(5f);
							  table_quittances_details.addCell(catnat_value);
							  PdfPCell taxe_value = new PdfPCell(new Paragraph(decimalFormat.format(dt.getTaxe()), tableHeader));
							  taxe_value.setBorderColor(BaseColor.BLACK);
							  taxe_value.setPaddingLeft(10);
							  taxe_value.setHorizontalAlignment(Element.ALIGN_CENTER);
							  taxe_value.setVerticalAlignment(Element.ALIGN_CENTER);
							  taxe_value.setBackgroundColor(BaseColor.WHITE);
							  taxe_value.setExtraParagraphSpace(5f);
							  table_quittances_details.addCell(taxe_value);
							  
							  PdfPCell acctim_value = new PdfPCell(new Paragraph(decimalFormat.format(dt.getAcctim()), tableHeader));
							  acctim_value.setBorderColor(BaseColor.BLACK);
							  acctim_value.setPaddingLeft(10);
							  acctim_value.setHorizontalAlignment(Element.ALIGN_CENTER);
							  acctim_value.setVerticalAlignment(Element.ALIGN_CENTER);
							  acctim_value.setBackgroundColor(BaseColor.WHITE);
							  acctim_value.setExtraParagraphSpace(5f);
							  table_quittances_details.addCell(acctim_value);
							  

							  
							  PdfPCell cot_ht_value = new PdfPCell(new Paragraph(decimalFormat.format(dt.getCot_Ht()), tableHeader));
							  cot_ht_value.setBorderColor(BaseColor.BLACK);
							  cot_ht_value.setPaddingLeft(10);
							  cot_ht_value.setHorizontalAlignment(Element.ALIGN_CENTER);
							  cot_ht_value.setVerticalAlignment(Element.ALIGN_CENTER);
							  cot_ht_value.setBackgroundColor(BaseColor.WHITE);
							  cot_ht_value.setExtraParagraphSpace(5f);
							  table_quittances_details.addCell(cot_ht_value);
							  PdfPCell cot_ttc_value = new PdfPCell(new Paragraph(decimalFormat.format(dt.getCotttc()), tableHeader));
							  cot_ttc_value.setBorderColor(BaseColor.BLACK);
							  cot_ttc_value.setPaddingLeft(10);
							  cot_ttc_value.setHorizontalAlignment(Element.ALIGN_CENTER);
							  cot_ttc_value.setVerticalAlignment(Element.ALIGN_CENTER);
							  cot_ttc_value.setBackgroundColor(BaseColor.WHITE);
							  cot_ttc_value.setExtraParagraphSpace(5f);
							  table_quittances_details.addCell(cot_ttc_value);

							  }

							  PdfPTable tbl = new PdfPTable(2);
								tbl.setWidthPercentage(40);
								tbl.setHorizontalAlignment(Element.ALIGN_RIGHT);
								tbl.setSpacingBefore(10f);
								tbl.setSpacingAfter(10);
								PdfPCell cell = new PdfPCell(new Paragraph("Total Prime TTC"));
								cell.setHorizontalAlignment(Element.ALIGN_LEFT);
								cell.setPaddingLeft(5);
								cell.setExtraParagraphSpace(3f);
								cell.setBorder(0);
								
								tbl.addCell(cell);
								cell = new PdfPCell(new Paragraph(decimalFormat.format(total_pttc)));
								cell.setHorizontalAlignment(Element.ALIGN_CENTER);
								cell.setBackgroundColor(BaseColor.PINK);
								tbl.addCell(cell);
								tbl.addCell(cell);	  
							  
				// *** Afféctation au document ***
				document.add(paragraphee);
				document.add(table_infos_client);
				document.add(table_quittances_details);
				document.add(tbl);
				
			
		        document.close();
				writer.close();
				return true;

			} catch (Exception e) {
				return false;
			}
		}
		
	// *** facture flotte et mono ***
	public boolean createPdf_mono_flotte(List<Facturation_EditerFactureDetails_Mono> quittances_mono, 
			List<Facturation_EditerFactureDetails_Flotte> quittances_flot,
			Info_Client_Facture infoclient, Integer exercice,
			NtsSites infos_site, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
		Document document = new Document(PageSize.A4, 15, 15, 45, 30);
		try {
			String filePath = context.getRealPath("/resources/report");
			File file = new File(filePath);
			boolean exists = new File(filePath).exists();
			if (!exists) {
				new File(filePath).mkdirs();
			}
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "employees" + ".pdf"));
			document.open();
			
			PdfContentByte canvas = writer.getDirectContentUnder();
			
			canvas.saveState();
			PdfGState state = new PdfGState();
			state.setFillOpacity(0.6f);
			canvas.setGState(state);
			
			canvas.restoreState();
			Font mainFont = FontFactory.getFont("Calibri", 18, BaseColor.BLUE);
			Font mainFont2 = FontFactory.getFont("Calibri", 11, BaseColor.BLACK);
			Font mainFont3 = FontFactory.getFont("Arial", 18, BaseColor.BLACK);
			Paragraph paragraphe = new Paragraph(" " + "Bureau  : " + infos_site.getAgence() + "\n Tél         : "
					+ infos_site.getTel() + "\n Email     : " + infos_site.getEmail() + "\n Adresse : "
					+ infos_site.getAdresse() + "\n ICE        : 001696879000083 ", mainFont2);
			paragraphe.setAlignment(Element.ALIGN_LEFT);
			paragraphe.setIndentationLeft(0);
			paragraphe.setIndentationRight(100);
			paragraphe.setSpacingBefore(10);
			paragraphe.setSpacingAfter(20);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy ");
			LocalDateTime now = LocalDateTime.now();
			Paragraph paragraphee = new Paragraph(
					"Facture N°: " + infoclient.getNumfacture() + "\n Date : " + dtf.format(now), mainFont2);
			paragraphee.setAlignment(Element.ALIGN_RIGHT);
			paragraphee.setIndentationLeft(10);
			paragraphee.setIndentationRight(20);
			Font tableBody = FontFactory.getFont("Calibri", 11, BaseColor.BLACK);
			Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11);
			PdfPTable table5 = new PdfPTable(1);
			table5.setWidthPercentage(60);
			table5.setHorizontalAlignment(Element.ALIGN_CENTER);
			table5.setSpacingBefore(10f);
			table5.setSpacingAfter(10);
		
			Paragraph paragraph = new Paragraph("Détaille des affaires Mono de L'exercice : " + exercice, mainFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setPaddingTop(-200);

			document.add(paragraphee);
			document.add(paragraphe);
			document.add(table5);
			document.add(paragraph);

			PdfPTable table = new PdfPTable(5);// column amount
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10);
			Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			Font tableBodyy = FontFactory.getFont("Arial", 9, BaseColor.BLACK);
			float[] columnWidths = { 2f, 2f, 2f, 2f, 2f };
			table.setWidths(columnWidths);
			PdfPCell police = new PdfPCell(new Paragraph("Police", tableHeader));
			police.setBorderColor(BaseColor.BLACK);
			police.setPaddingLeft(10);
			police.setHorizontalAlignment(Element.ALIGN_CENTER);
			police.setVerticalAlignment(Element.ALIGN_CENTER);
			police.setBackgroundColor(BaseColor.LIGHT_GRAY);
			police.setExtraParagraphSpace(5f);
			table.addCell(police);
			PdfPCell matricule = new PdfPCell(new Paragraph("Matricule", tableHeader));
			matricule.setBorderColor(BaseColor.BLACK);
			matricule.setPaddingLeft(10);
			matricule.setHorizontalAlignment(Element.ALIGN_CENTER);
			matricule.setVerticalAlignment(Element.ALIGN_CENTER);
			matricule.setBackgroundColor(BaseColor.LIGHT_GRAY);
			matricule.setExtraParagraphSpace(5f);
			table.addCell(matricule);
			PdfPCell effet = new PdfPCell(new Paragraph("Effet", tableHeader));
			effet.setBorderColor(BaseColor.BLACK);
			effet.setPaddingLeft(10);
			effet.setHorizontalAlignment(Element.ALIGN_CENTER);
			effet.setVerticalAlignment(Element.ALIGN_CENTER);
			effet.setBackgroundColor(BaseColor.LIGHT_GRAY);
			effet.setExtraParagraphSpace(5f);
			table.addCell(effet);
			PdfPCell expiration = new PdfPCell(new Paragraph("Expiration", tableHeader));
			expiration.setBorderColor(BaseColor.BLACK);
			expiration.setPaddingLeft(10);
			expiration.setHorizontalAlignment(Element.ALIGN_CENTER);
			expiration.setVerticalAlignment(Element.ALIGN_CENTER);
			expiration.setBackgroundColor(BaseColor.LIGHT_GRAY);
			expiration.setExtraParagraphSpace(5f);
			table.addCell(expiration);
			PdfPCell pttc = new PdfPCell(new Paragraph("Prime TTC", tableHeader));
			pttc.setBorderColor(BaseColor.BLACK);
			pttc.setPaddingLeft(10);
			pttc.setHorizontalAlignment(Element.ALIGN_CENTER);
			pttc.setVerticalAlignment(Element.ALIGN_CENTER);
			pttc.setBackgroundColor(BaseColor.LIGHT_GRAY);
			pttc.setExtraParagraphSpace(5f);
			table.addCell(pttc);
			// *** Boucle dEs affaires Mono ***
			Double total_pht = 0.0;
			Double total_taxe = 0.0;
			Double total_pttc = 0.0;
			String pattern = "###,###.##";
			DecimalFormat decimalFormat = new DecimalFormat(pattern);
			for (Facturation_EditerFactureDetails_Mono quittance : quittances_mono) {
				total_pht += quittance.getPht();
				total_taxe += quittance.getTaxe();
				total_pttc += quittance.getPttc();
				PdfPCell policevalue = new PdfPCell(new Paragraph(quittance.getPolice().toString(), tableHeader));
				policevalue.setBorderColor(BaseColor.BLACK);
				policevalue.setPaddingLeft(10);
				policevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				policevalue.setVerticalAlignment(Element.ALIGN_CENTER);
				policevalue.setBackgroundColor(BaseColor.WHITE);
				policevalue.setExtraParagraphSpace(5f);
				table.addCell(policevalue);
				PdfPCell matriculevalue = new PdfPCell(new Paragraph(quittance.getMatricule().toString(), tableHeader));
				matriculevalue.setBorderColor(BaseColor.BLACK);
				matriculevalue.setPaddingLeft(10);
				matriculevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				matriculevalue.setVerticalAlignment(Element.ALIGN_CENTER);
				matriculevalue.setBackgroundColor(BaseColor.WHITE);
				matriculevalue.setExtraParagraphSpace(5f);
				table.addCell(matriculevalue);
				PdfPCell effet_value = new PdfPCell(new Paragraph(quittance.getEffet().toString(), tableHeader));
				effet_value.setBorderColor(BaseColor.BLACK);
				effet_value.setPaddingLeft(10);
				effet_value.setHorizontalAlignment(Element.ALIGN_CENTER);
				effet_value.setVerticalAlignment(Element.ALIGN_CENTER);
				effet_value.setBackgroundColor(BaseColor.WHITE);
				effet_value.setExtraParagraphSpace(5f);
				table.addCell(effet_value);
				PdfPCell expiration_value = new PdfPCell(new Paragraph(quittance.getExpiration().toString(), tableHeader));
				expiration_value.setBorderColor(BaseColor.BLACK);
				expiration_value.setPaddingLeft(10);
				expiration_value.setHorizontalAlignment(Element.ALIGN_CENTER);
				expiration_value.setVerticalAlignment(Element.ALIGN_CENTER);
				expiration_value.setBackgroundColor(BaseColor.WHITE);
				expiration_value.setExtraParagraphSpace(5f);
				table.addCell(expiration_value);
				PdfPCell pttcvalue = new PdfPCell(new Paragraph(decimalFormat.format(quittance.getPttc()), tableHeader));
				pttcvalue.setBorderColor(BaseColor.BLACK);
				pttcvalue.setPaddingLeft(10);
				pttcvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				pttcvalue.setVerticalAlignment(Element.ALIGN_CENTER);
				pttcvalue.setBackgroundColor(BaseColor.WHITE);
				pttcvalue.setExtraParagraphSpace(5f);
				table.addCell(pttcvalue);
			}
			PdfPTable tbl = new PdfPTable(2);
			tbl.setWidthPercentage(40);
			tbl.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl.setSpacingBefore(10f);
			tbl.setSpacingAfter(10);
			PdfPCell cell = new PdfPCell(new Paragraph("Total Prime TTC"));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(5);
			cell.setExtraParagraphSpace(3f);
			cell.setBorder(0);
			tbl.addCell(cell);
			cell = new PdfPCell(new Paragraph(decimalFormat.format(total_pttc)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.PINK);
			tbl.addCell(cell);
			tbl.addCell(cell);
			
			// test s'ils'agait d'une flotte ou juste mono 
			boolean qt = quittances_flot.isEmpty(); 
	        if (qt != true) {
		    //------------------------ titre de détails de affaires flotte des clients éléctionnées   ------------
					Paragraph titre_affaires_flotte = new Paragraph("Détaille des affaires Flotte  de L'exercice :"  + exercice, mainFont);
					titre_affaires_flotte.setAlignment(Element.ALIGN_CENTER);
					titre_affaires_flotte.setIndentationLeft(50);
					titre_affaires_flotte.setIndentationRight(50);
					titre_affaires_flotte.setPaddingTop(-200);
					//----------------------- Table de détailles flotte -----------------
					PdfPTable tabledetails = new PdfPTable(7);// column amount
					tabledetails.setWidthPercentage(100);
					tabledetails.setSpacingBefore(10f);
					tabledetails.setSpacingAfter(10);
					Font font_header = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
					Font font_body = FontFactory.getFont("Arial", 9, BaseColor.BLACK);
					float[] nombre_colonnes = { 2f, 2f, 2f, 2f, 2f, 2f, 2f };
					tabledetails.setWidths(nombre_colonnes);
					PdfPCell police_ = new PdfPCell(new Paragraph("Police", tableHeader));
					police_.setBorderColor(BaseColor.BLACK);
					police_.setPaddingLeft(10);
					police_.setHorizontalAlignment(Element.ALIGN_CENTER);
					police_.setVerticalAlignment(Element.ALIGN_CENTER);
					police_.setBackgroundColor(BaseColor.LIGHT_GRAY);
					police_.setExtraParagraphSpace(5f);
					tabledetails.addCell(police_);
					PdfPCell quittance_ = new PdfPCell(new Paragraph("Quittance", tableHeader));
					quittance_.setBorderColor(BaseColor.BLACK);
					quittance_.setPaddingLeft(10);
					quittance_.setHorizontalAlignment(Element.ALIGN_CENTER);
					quittance_.setVerticalAlignment(Element.ALIGN_CENTER);
					quittance_.setBackgroundColor(BaseColor.LIGHT_GRAY);
					quittance_.setExtraParagraphSpace(5f);
					tabledetails.addCell(quittance_);
					PdfPCell effet_ = new PdfPCell(new Paragraph("Effet", tableHeader));
					effet_.setBorderColor(BaseColor.BLACK);
					effet_.setPaddingLeft(10);
					effet_.setHorizontalAlignment(Element.ALIGN_CENTER);
					effet_.setVerticalAlignment(Element.ALIGN_CENTER);
					effet_.setBackgroundColor(BaseColor.LIGHT_GRAY);
					effet_.setExtraParagraphSpace(5f);
					tabledetails.addCell(effet_);
					PdfPCell expiration_ = new PdfPCell(new Paragraph("Expiration", tableHeader));
					
					  expiration_.setBorderColor(BaseColor.BLACK); expiration_.setPaddingLeft(10);
					  expiration_.setHorizontalAlignment(Element.ALIGN_CENTER);
					  expiration_.setVerticalAlignment(Element.ALIGN_CENTER);
					  expiration_.setBackgroundColor(BaseColor.LIGHT_GRAY);
					  expiration_.setExtraParagraphSpace(5f); 
					  tabledetails.addCell(expiration_);
						
					  PdfPCell pttc_ = new PdfPCell(new Paragraph("Prix TTC", tableHeader));
					  pttc_.setBorderColor(BaseColor.BLACK); pttc_.setPaddingLeft(10);
					  pttc_.setHorizontalAlignment(Element.ALIGN_CENTER);
					  pttc_.setVerticalAlignment(Element.ALIGN_CENTER);
					  pttc_.setBackgroundColor(BaseColor.LIGHT_GRAY);
					  pttc_.setExtraParagraphSpace(5f); tabledetails.addCell(pttc_);
					  PdfPCell  avance_ = new PdfPCell(new Paragraph("Avance", tableHeader));
					  avance_.setBorderColor(BaseColor.BLACK); avance_.setPaddingLeft(10);
					  avance_.setHorizontalAlignment(Element.ALIGN_CENTER);
					  avance_.setVerticalAlignment(Element.ALIGN_CENTER);
					  avance_.setBackgroundColor(BaseColor.LIGHT_GRAY);
					  avance_.setExtraParagraphSpace(5f); tabledetails.addCell(avance_);
					  PdfPCell   reste_ = new PdfPCell(new Paragraph("Reste à Payer", tableHeader));
					  reste_.setBorderColor(BaseColor.BLACK); reste_.setPaddingLeft(10);
					  reste_.setHorizontalAlignment(Element.ALIGN_CENTER);
					  reste_.setVerticalAlignment(Element.ALIGN_CENTER);
					  reste_.setBackgroundColor(BaseColor.LIGHT_GRAY);
					  reste_.setExtraParagraphSpace(5f); tabledetails.addCell(reste_);
					  
					  Double total_pht_ = 0.0; Double total_taxe_ = 0.0; Double total_pttc_ = 0.0; 
					  String  pattern_ = "###,###.##";
				      DecimalFormat decimalFormat_ = new DecimalFormat(pattern);
					// *** boucles des affaires flottes *****
					  for (Facturation_EditerFactureDetails_Flotte quittance_flotte :quittances_flot) {
						  //*** calcule somme des aleurs *** //total_pht
					  PdfPCell policevalue_ = new PdfPCell(new
					  Paragraph(quittance_flotte.getPolice().toString(), tableHeader));
					  policevalue_.setBorderColor(BaseColor.BLACK);
					  policevalue_.setPaddingLeft(10);
					  policevalue_.setHorizontalAlignment(Element.ALIGN_CENTER);
					  policevalue_.setVerticalAlignment(Element.ALIGN_CENTER);
					  policevalue_.setBackgroundColor(BaseColor.WHITE);
					  policevalue_.setExtraParagraphSpace(5f); tabledetails.addCell(policevalue_);
					  PdfPCell quittancevalue_ = new PdfPCell(new Paragraph(quittance_flotte.getQuittance().toString(), tableHeader));
					  quittancevalue_.setBorderColor(BaseColor.BLACK);
					  quittancevalue_.setPaddingLeft(10);
					  quittancevalue_.setHorizontalAlignment(Element.ALIGN_CENTER);
					  quittancevalue_.setVerticalAlignment(Element.ALIGN_CENTER);
					  quittancevalue_.setBackgroundColor(BaseColor.WHITE);
					  quittancevalue_.setExtraParagraphSpace(5f);
					  tabledetails.addCell(quittancevalue_); PdfPCell effet_value_= new PdfPCell(new Paragraph(quittance_flotte.getEffet().toString(), tableHeader));
					  effet_value_.setBorderColor(BaseColor.BLACK);
					  effet_value_.setPaddingLeft(10);
					  effet_value_.setHorizontalAlignment(Element.ALIGN_CENTER);
					  effet_value_.setVerticalAlignment(Element.ALIGN_CENTER);
					  effet_value_.setBackgroundColor(BaseColor.WHITE);
					  effet_value_.setExtraParagraphSpace(5f); tabledetails.addCell(effet_value_);
					  PdfPCell expiration_value_ = new PdfPCell(new Paragraph(quittance_flotte.getExpiration().toString(), tableHeader));
					  expiration_value_.setBorderColor(BaseColor.BLACK);
					  expiration_value_.setPaddingLeft(10);
					  expiration_value_.setHorizontalAlignment(Element.ALIGN_CENTER);
					  expiration_value_.setVerticalAlignment(Element.ALIGN_CENTER);
					  expiration_value_.setBackgroundColor(BaseColor.WHITE);
					  expiration_value_.setExtraParagraphSpace(5f);
					  tabledetails.addCell(expiration_value_);
						
					  PdfPCell pttcvalue_ = new PdfPCell(new Paragraph(decimalFormat.format(quittance_flotte.getTot_pttc()),tableHeader));
					  pttcvalue_.setBorderColor(BaseColor.BLACK); pttcvalue_.setPaddingLeft(10);
					  pttcvalue_.setHorizontalAlignment(Element.ALIGN_CENTER);
					  pttcvalue_.setVerticalAlignment(Element.ALIGN_CENTER);
					  pttcvalue_.setBackgroundColor(BaseColor.WHITE);
					  pttcvalue_.setExtraParagraphSpace(5f); tabledetails.addCell(pttcvalue_);
					  PdfPCell avancevalue_ = new PdfPCell(new  Paragraph(decimalFormat.format(quittance_flotte.getAvance()),tableHeader));
					  avancevalue_.setBorderColor(BaseColor.BLACK);
					  avancevalue_.setPaddingLeft(10);
					  avancevalue_.setHorizontalAlignment(Element.ALIGN_CENTER);
					  avancevalue_.setVerticalAlignment(Element.ALIGN_CENTER);
					  avancevalue_.setBackgroundColor(BaseColor.WHITE);
					  pttcvalue_.setExtraParagraphSpace(5f); tabledetails.addCell(avancevalue_);
					  PdfPCell rapvalaue_ = new PdfPCell(new
					  Paragraph(decimalFormat.format(quittance_flotte.getRap()),tableHeader));
					  rapvalaue_.setBorderColor(BaseColor.BLACK); rapvalaue_.setPaddingLeft(10);
					  rapvalaue_.setHorizontalAlignment(Element.ALIGN_CENTER);
					  rapvalaue_.setVerticalAlignment(Element.ALIGN_CENTER);
					  rapvalaue_.setBackgroundColor(BaseColor.WHITE);
					  pttcvalue_.setExtraParagraphSpace(5f); tabledetails.addCell(rapvalaue_);
						 
					  }
					    document.add(table);
						document.add(tbl);
						document.add(titre_affaires_flotte);
						document.add(tabledetails);
						document.close();
	                }else {
	                
	                	document.add(table);
	        			document.add(tbl);
	        			document.close();
	                }
				
			writer.close();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

   // *** facture  détaillé  (MONO) ***
	public boolean createPdf(List<Details_facture> quittances_payyes, Info_Client_Facture infoclient, String exercice,
			NtsSites infos_site, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
		Document document = new Document(PageSize.A4, 15, 15, 45, 30);
		try {
			String filePath = context.getRealPath("/resources/report");
			File file = new File(filePath);
			boolean exists = new File(filePath).exists();
			if (!exists) {
				new File(filePath).mkdirs();
			}
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "employees" + ".pdf"));
			document.open();
			//Image imgg = Image.getInstance("http://beleefmarokko.com/images/Image_001.png");
			//imgg.setAlignment(Element.ALIGN_LEFT);
			//imgg.scaleAbsolute(150, 20);
			PdfContentByte canvas = writer.getDirectContentUnder();
			//Image image = Image.getInstance(imgg);
			//image.scaleAbsolute(PageSize.A4);
			//image.setAbsolutePosition(0, 0);
			//image.setBorderWidthRight(0);
			canvas.saveState();
			PdfGState state = new PdfGState();
			state.setFillOpacity(0.6f);
			canvas.setGState(state);
			//canvas.addImage(image);
			canvas.restoreState();
			Font mainFont = FontFactory.getFont("Calibri", 18, BaseColor.BLUE);
			Font mainFont2 = FontFactory.getFont("Calibri", 11, BaseColor.BLACK);
			Font mainFont3 = FontFactory.getFont("Arial", 18, BaseColor.BLACK);
			Paragraph paragraphe = new Paragraph(" " + "Bureau  : " + infos_site.getAgence() + "\n Tél         : "
					+ infos_site.getTel() + "\n Email     : " + infos_site.getEmail() + "\n Adresse : "
					+ infos_site.getAdresse() + "\n ICE        : 001696879000083 ", mainFont2);
			paragraphe.setAlignment(Element.ALIGN_LEFT);
			paragraphe.setIndentationLeft(0);
			paragraphe.setIndentationRight(100);
			paragraphe.setSpacingBefore(10);
			paragraphe.setSpacingAfter(20);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy ");
			LocalDateTime now = LocalDateTime.now();
			Paragraph paragraphee = new Paragraph(
					"Facture N°: " + infoclient.getNumfacture() + "\n Date : " + dtf.format(now), mainFont2);
			paragraphee.setAlignment(Element.ALIGN_RIGHT);
			paragraphee.setIndentationLeft(10);
			paragraphee.setIndentationRight(20);
			Font tableBody = FontFactory.getFont("Calibri", 11, BaseColor.BLACK);
			Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11);
			PdfPTable table5 = new PdfPTable(1);
			table5.setWidthPercentage(60);
			table5.setHorizontalAlignment(Element.ALIGN_CENTER);
			table5.setSpacingBefore(10f);
			table5.setSpacingAfter(10);
			PdfPCell sinistre = new PdfPCell(new Paragraph("Information de Client ", boldFont));
			sinistre.setBorderColor(BaseColor.BLACK);
			sinistre.setPaddingLeft(5);
			sinistre.setHorizontalAlignment(Element.ALIGN_CENTER);
			sinistre.setVerticalAlignment(Element.ALIGN_CENTER);
			sinistre.setBackgroundColor(BaseColor.LIGHT_GRAY);
			sinistre.setExtraParagraphSpace(2f);
			sinistre.setColspan(4);
			table5.addCell(sinistre);

			PdfPCell loi_sinistre = new PdfPCell(new Paragraph(
					" Client             : " + infoclient.getClient() + "" + "\n Adresse         : " + infoclient.getAdresse()
							+ "" + "\n Tél                 : " + infoclient.getTelephone() + "                    "
							+ "Email:   " + infoclient.getEmail() + "\n Cin / Patente :   " + infoclient.getcin_pat()
							+ "     Ice :   " + infoclient.getIce(),
					tableBody));
			loi_sinistre.setBorderColor(BaseColor.BLACK);
//			                emailValue.setPaddingLeft(10);
			loi_sinistre.setHorizontalAlignment(Element.ALIGN_LEFT);
			loi_sinistre.setVerticalAlignment(Element.ALIGN_LEFT);
			loi_sinistre.setBackgroundColor(BaseColor.WHITE);
			loi_sinistre.setExtraParagraphSpace(5f);
			table5.addCell(loi_sinistre);

			Paragraph paragraph = new Paragraph("Facture Détaillée Mono (Réglé) De L'exercice : " + exercice, mainFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setPaddingTop(-200);
			// paragraph.setSpacingBefore(200);

			// document.add(img);

			document.add(paragraphee);
			//document.add(paragraphe);
			document.add(table5);
			document.add(paragraph);

			PdfPTable table = new PdfPTable(5);// column amount
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10);
			Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			Font tableBodyy = FontFactory.getFont("Arial", 9, BaseColor.BLACK);
			float[] columnWidths = { 2f, 2f, 2f, 2f, 2f };
			table.setWidths(columnWidths);
			PdfPCell police = new PdfPCell(new Paragraph("Police", tableHeader));
			police.setBorderColor(BaseColor.BLACK);
			police.setPaddingLeft(10);
			police.setHorizontalAlignment(Element.ALIGN_CENTER);
			police.setVerticalAlignment(Element.ALIGN_CENTER);
			police.setBackgroundColor(BaseColor.LIGHT_GRAY);
			police.setExtraParagraphSpace(5f);
			table.addCell(police);
			PdfPCell matricule = new PdfPCell(new Paragraph("Matricule", tableHeader));
			matricule.setBorderColor(BaseColor.BLACK);
			matricule.setPaddingLeft(10);
			matricule.setHorizontalAlignment(Element.ALIGN_CENTER);
			matricule.setVerticalAlignment(Element.ALIGN_CENTER);
			matricule.setBackgroundColor(BaseColor.LIGHT_GRAY);
			matricule.setExtraParagraphSpace(5f);
			table.addCell(matricule);
			PdfPCell effet = new PdfPCell(new Paragraph("Effet", tableHeader));
			effet.setBorderColor(BaseColor.BLACK);
			effet.setPaddingLeft(10);
			effet.setHorizontalAlignment(Element.ALIGN_CENTER);
			effet.setVerticalAlignment(Element.ALIGN_CENTER);
			effet.setBackgroundColor(BaseColor.LIGHT_GRAY);
			effet.setExtraParagraphSpace(5f);
			table.addCell(effet);
			PdfPCell expiration = new PdfPCell(new Paragraph("Expiration", tableHeader));
			expiration.setBorderColor(BaseColor.BLACK);
			expiration.setPaddingLeft(10);
			expiration.setHorizontalAlignment(Element.ALIGN_CENTER);
			expiration.setVerticalAlignment(Element.ALIGN_CENTER);
			expiration.setBackgroundColor(BaseColor.LIGHT_GRAY);
			expiration.setExtraParagraphSpace(5f);
			table.addCell(expiration);
			PdfPCell pttc = new PdfPCell(new Paragraph("Prime TTC", tableHeader));
			pttc.setBorderColor(BaseColor.BLACK);
			pttc.setPaddingLeft(10);
			pttc.setHorizontalAlignment(Element.ALIGN_CENTER);
			pttc.setVerticalAlignment(Element.ALIGN_CENTER);
			pttc.setBackgroundColor(BaseColor.LIGHT_GRAY);
			pttc.setExtraParagraphSpace(5f);
			table.addCell(pttc);
			Double total_pht = 0.0;
			Double total_taxe = 0.0;
			Double total_pttc = 0.0;
			String pattern = "###,###.##";
			DecimalFormat decimalFormat = new DecimalFormat(pattern);
			for (Details_facture quittance : quittances_payyes) {
				total_pht += quittance.getPht();
				total_taxe += quittance.getTaxe();
				total_pttc += quittance.getPttc();
				PdfPCell policevalue = new PdfPCell(new Paragraph(quittance.getPolice().toString(), tableHeader));
				policevalue.setBorderColor(BaseColor.BLACK);
				policevalue.setPaddingLeft(10);
				policevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				policevalue.setVerticalAlignment(Element.ALIGN_CENTER);
				policevalue.setBackgroundColor(BaseColor.WHITE);
				policevalue.setExtraParagraphSpace(5f);
				table.addCell(policevalue);
				PdfPCell matriculevalue = new PdfPCell(new Paragraph(quittance.getMatricule().toString(), tableHeader));
				matriculevalue.setBorderColor(BaseColor.BLACK);
				matriculevalue.setPaddingLeft(10);
				matriculevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				matriculevalue.setVerticalAlignment(Element.ALIGN_CENTER);
				matriculevalue.setBackgroundColor(BaseColor.WHITE);
				matriculevalue.setExtraParagraphSpace(5f);
				table.addCell(matriculevalue);
				PdfPCell effet_value = new PdfPCell(new Paragraph(quittance.getEffet().toString(), tableHeader));
				effet_value.setBorderColor(BaseColor.BLACK);
				effet_value.setPaddingLeft(10);
				effet_value.setHorizontalAlignment(Element.ALIGN_CENTER);
				effet_value.setVerticalAlignment(Element.ALIGN_CENTER);
				effet_value.setBackgroundColor(BaseColor.WHITE);
				effet_value.setExtraParagraphSpace(5f);
				table.addCell(effet_value);
				PdfPCell expiration_value = new PdfPCell(new Paragraph(quittance.getExpiration().toString(), tableHeader));
				expiration_value.setBorderColor(BaseColor.BLACK);
				expiration_value.setPaddingLeft(10);
				expiration_value.setHorizontalAlignment(Element.ALIGN_CENTER);
				expiration_value.setVerticalAlignment(Element.ALIGN_CENTER);
				expiration_value.setBackgroundColor(BaseColor.WHITE);
				expiration_value.setExtraParagraphSpace(5f);
				table.addCell(expiration_value);
				PdfPCell pttcvalue = new PdfPCell(new Paragraph(decimalFormat.format(quittance.getPttc()), tableHeader));
				pttcvalue.setBorderColor(BaseColor.BLACK);
				pttcvalue.setPaddingLeft(10);
				pttcvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				pttcvalue.setVerticalAlignment(Element.ALIGN_CENTER);
				pttcvalue.setBackgroundColor(BaseColor.WHITE);
				pttcvalue.setExtraParagraphSpace(5f);
				table.addCell(pttcvalue);
			}
			PdfPTable tbl = new PdfPTable(2);
			tbl.setWidthPercentage(40);
			tbl.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl.setSpacingBefore(10f);
			tbl.setSpacingAfter(10);
			PdfPCell cell = new PdfPCell(new Paragraph("Total Prime TTC"));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(5);
			cell.setExtraParagraphSpace(3f);
			cell.setBorder(0);
			tbl.addCell(cell);
			cell = new PdfPCell(new Paragraph(decimalFormat.format(total_pttc)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.PINK);
			tbl.addCell(cell);
			tbl.addCell(cell);

			document.add(table);
			document.add(tbl);
			document.close();
			writer.close();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	   // *** facture  Globale  (MONO) ***
	public boolean createPdf_globale(List<Details_facture_globale> globale,List<Details_facture> quittances_details, Info_Client_Facture infoclient,
			String exercicee, NtsSites infos_site, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		Document document = new Document(PageSize.A4, 15, 15, 45, 30);
		// Document document = new Document(PageSize.A3.rotate(), 30, 30, 10, 10);
		try {
			String filePath = context.getRealPath("/resources/report");
			File file = new File(filePath);
			boolean exists = new File(filePath).exists();
			if (!exists) {
				new File(filePath).mkdirs();
			}
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "employees" + ".pdf"));
			document.open();
			//Image imgg = Image.getInstance("http://beleefmarokko.com/images/Image_001.png");
			//imgg.setAlignment(Element.ALIGN_LEFT);
			//imgg.scaleAbsolute(150, 20);

			PdfContentByte canvas = writer.getDirectContentUnder();

			//Image image = Image.getInstance(imgg);
			//image.scaleAbsolute(PageSize.A4);
			//image.setAbsolutePosition(0, 0);
			//image.setBorderWidthRight(0);
			canvas.saveState();
			PdfGState state = new PdfGState();
			state.setFillOpacity(0.6f);
			canvas.setGState(state);
			//canvas.addImage(image);
			canvas.restoreState();

			Font mainFont = FontFactory.getFont("Calibri", 18, BaseColor.BLUE);
			Font mainFont2 = FontFactory.getFont("Calibri", 11, BaseColor.BLACK);

			Paragraph paragraphe = new Paragraph(" " + "Bureau  : " + infos_site.getAgence() + "\n Tél         : "
					+ infos_site.getTel() + "\n Email     : " + infos_site.getEmail() + "\n Adresse : "
					+ infos_site.getAdresse() + "\n ICE        : 001696879000083 ", mainFont2);
			paragraphe.setAlignment(Element.ALIGN_LEFT);
			paragraphe.setIndentationLeft(0);
			paragraphe.setIndentationRight(100);
			paragraphe.setSpacingBefore(10);
			paragraphe.setSpacingAfter(20);

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy ");
			LocalDateTime now = LocalDateTime.now();

			Paragraph paragraphee = new Paragraph(
					"Facture N° : " + infoclient.getNumfacture() + "\n Date : " + dtf.format(now), mainFont2);
			paragraphee.setAlignment(Element.ALIGN_RIGHT);
			paragraphee.setIndentationLeft(10);
			paragraphee.setIndentationRight(20);
			paragraphee.setSpacingBefore(5);

			Font tableBody = FontFactory.getFont("Calibri", 11, BaseColor.BLACK);
			Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11);

			PdfPTable table5 = new PdfPTable(1);

			table5.setWidthPercentage(80);
			table5.setHorizontalAlignment(Element.ALIGN_CENTER);
			table5.setSpacingBefore(10f);
			table5.setSpacingAfter(10);
//			                

			PdfPCell sinistre = new PdfPCell(new Paragraph("Information de Client ", boldFont));
			sinistre.setBorderColor(BaseColor.BLACK);
			sinistre.setPaddingLeft(5);
			sinistre.setHorizontalAlignment(Element.ALIGN_CENTER);
			sinistre.setVerticalAlignment(Element.ALIGN_CENTER);
			sinistre.setBackgroundColor(BaseColor.LIGHT_GRAY);
			sinistre.setExtraParagraphSpace(2f);
			sinistre.setColspan(4);
			table5.addCell(sinistre);

			PdfPCell loi_sinistre = new PdfPCell(new Paragraph(" Client           : " + infoclient.getClient() + ""
					+ "\n Adresse       : " + infoclient.getAdresse() + "" + "\n Tél               : "
					+ infoclient.getTelephone() + "                " + "Email :   " + infoclient.getEmail()
					+ "\n Cin/Patente :   " + infoclient.getcin_pat()
					+ "     Ice :   " + infoclient.getIce()
					, tableBody));
			loi_sinistre.setBorderColor(BaseColor.BLACK);
			loi_sinistre.setHorizontalAlignment(Element.ALIGN_LEFT);
			loi_sinistre.setVerticalAlignment(Element.ALIGN_LEFT);
			loi_sinistre.setBackgroundColor(BaseColor.WHITE);
			loi_sinistre.setExtraParagraphSpace(5f);
			table5.addCell(loi_sinistre);

			Paragraph paragraph = new Paragraph("Etat Globale (Mono) de l'exercice : " + exercicee, mainFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setPaddingTop(-200);
			document.add(paragraphee);
			//document.add(paragraphe);
			document.add(table5);
			document.add(paragraph);

			PdfPTable table = new PdfPTable(4);// column amount
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10);

			Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			Font tableBodyy = FontFactory.getFont("Arial", 9, BaseColor.BLACK);

			float[] columnWidths = { 2f, 2f, 2f, 2f };
			table.setWidths(columnWidths);

			PdfPCell client = new PdfPCell(new Paragraph("Exercice", tableHeader));
			client.setBorderColor(BaseColor.BLACK);
			client.setPaddingLeft(10);
			client.setHorizontalAlignment(Element.ALIGN_CENTER);
			client.setVerticalAlignment(Element.ALIGN_CENTER);
			client.setBackgroundColor(BaseColor.LIGHT_GRAY);
			client.setExtraParagraphSpace(5f);
			table.addCell(client);
			/*
			 * PdfPCell police = new PdfPCell(new Paragraph("Ausage", tableHeader));
			 * police.setBorderColor(BaseColor.BLACK); police.setPaddingLeft(10);
			 * police.setHorizontalAlignment(Element.ALIGN_CENTER);
			 * police.setVerticalAlignment(Element.ALIGN_CENTER);
			 * police.setBackgroundColor(BaseColor.LIGHT_GRAY);
			 * police.setExtraParagraphSpace(5f); table.addCell(police);
			 */
			PdfPCell taxe = new PdfPCell(new Paragraph(" Prime TTC", tableHeader));
			taxe.setBorderColor(BaseColor.BLACK);
			taxe.setPaddingLeft(10);
			taxe.setHorizontalAlignment(Element.ALIGN_CENTER);
			taxe.setVerticalAlignment(Element.ALIGN_CENTER);
			taxe.setBackgroundColor(BaseColor.LIGHT_GRAY);
			taxe.setExtraParagraphSpace(5f);
			table.addCell(taxe);
			

			PdfPCell avance = new PdfPCell(new Paragraph(" Avance", tableHeader));
			avance.setBorderColor(BaseColor.BLACK);
			avance.setPaddingLeft(10);
			avance.setHorizontalAlignment(Element.ALIGN_CENTER);
			avance.setVerticalAlignment(Element.ALIGN_CENTER);
			avance.setBackgroundColor(BaseColor.LIGHT_GRAY);
			avance.setExtraParagraphSpace(5f);
			table.addCell(avance);

			PdfPCell reste = new PdfPCell(new Paragraph(" Reste A Payer", tableHeader));
			reste.setBorderColor(BaseColor.BLACK);
			reste.setPaddingLeft(10);
			reste.setHorizontalAlignment(Element.ALIGN_CENTER);
			reste.setVerticalAlignment(Element.ALIGN_CENTER);
			reste.setBackgroundColor(BaseColor.LIGHT_GRAY);
			reste.setExtraParagraphSpace(5f);
			table.addCell(reste);
			
			

			Double total_pht = 0.0;
			Double total_taxe = 0.0;
			Double total_pttc = 0.0;

			Double total_avance = 0.0;

			Double total_reste = 0.0;

			String pattern = "###,###.##";
			DecimalFormat decimalFormat = new DecimalFormat(pattern);

			for (Details_facture_globale quittance : globale) {

				total_pht += quittance.getTot_pht();
				total_taxe += quittance.getTot_taxe();
				total_pttc += quittance.getTot_pttc();
				total_avance += quittance.getAvance();
				total_reste += quittance.getReste_a_payer();

				PdfPCell exercice = new PdfPCell(new Paragraph(quittance.getExercice(), tableHeader));
				exercice.setBorderColor(BaseColor.BLACK);
				exercice.setPaddingLeft(10);
				exercice.setHorizontalAlignment(Element.ALIGN_CENTER);
				exercice.setVerticalAlignment(Element.ALIGN_CENTER);
				exercice.setBackgroundColor(BaseColor.WHITE);
				exercice.setExtraParagraphSpace(5f);
				table.addCell(exercice);
				/*
				 * PdfPCell nbrelignevalue = new PdfPCell(new Paragraph(quittance.getUsage(),
				 * tableHeader)); nbrelignevalue.setBorderColor(BaseColor.BLACK);
				 * nbrelignevalue.setPaddingLeft(10);
				 * nbrelignevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * nbrelignevalue.setVerticalAlignment(Element.ALIGN_CENTER);
				 * nbrelignevalue.setBackgroundColor(BaseColor.WHITE);
				 * nbrelignevalue.setExtraParagraphSpace(5f); table.addCell(nbrelignevalue);
				 */
				PdfPCell tot_pttcvalue = new PdfPCell(new Paragraph(decimalFormat.format(quittance.getTot_pttc()), tableHeader));
				tot_pttcvalue.setBorderColor(BaseColor.BLACK);
				tot_pttcvalue.setPaddingLeft(10);
				tot_pttcvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				tot_pttcvalue.setVerticalAlignment(Element.ALIGN_CENTER);
				tot_pttcvalue.setBackgroundColor(BaseColor.WHITE);
				tot_pttcvalue.setExtraParagraphSpace(5f);
				table.addCell(tot_pttcvalue);
				PdfPCell avacenvalue = new PdfPCell(new Paragraph(decimalFormat.format(quittance.getAvance()), tableHeader));
				avacenvalue.setBorderColor(BaseColor.BLACK);
				avacenvalue.setPaddingLeft(10);
				avacenvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				avacenvalue.setVerticalAlignment(Element.ALIGN_CENTER);
				avacenvalue.setBackgroundColor(BaseColor.WHITE);
				avacenvalue.setExtraParagraphSpace(5f);
				table.addCell(avacenvalue);
				PdfPCell reste_a_payer = new PdfPCell(new Paragraph(decimalFormat.format(quittance.getReste_a_payer()), tableHeader));
				reste_a_payer.setBorderColor(BaseColor.BLACK);
				reste_a_payer.setPaddingLeft(10);
				reste_a_payer.setHorizontalAlignment(Element.ALIGN_CENTER);
				reste_a_payer.setVerticalAlignment(Element.ALIGN_CENTER);
				reste_a_payer.setBackgroundColor(BaseColor.WHITE);
				reste_a_payer.setExtraParagraphSpace(5f);
				table.addCell(reste_a_payer);
			}
			PdfPTable tbl = new PdfPTable(2);
			tbl.setWidthPercentage(40);
			tbl.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl.setSpacingBefore(10f);
			tbl.setSpacingAfter(10);
			PdfPCell cell = new PdfPCell(new Paragraph("Total Prime TTC"));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(10);
			cell.setExtraParagraphSpace(5f);
			cell.setBorder(0);
			tbl.addCell(cell);
			cell = new PdfPCell(new Paragraph(decimalFormat.format(total_pttc)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.PINK);
			tbl.addCell(cell);
			cell = new PdfPCell(new Paragraph("Avance"));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(10);
			cell.setExtraParagraphSpace(5f);
			cell.setBorder(0);
			tbl.addCell(cell);
			cell = new PdfPCell(new Paragraph(decimalFormat.format(total_avance)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.PINK);
			tbl.addCell(cell);
			cell = new PdfPCell(new Paragraph("Reste à Payé"));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(10);
			cell.setExtraParagraphSpace(5f);
			cell.setBorder(0);
			tbl.addCell(cell);
			cell = new PdfPCell(new Paragraph(decimalFormat.format(total_reste)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.PINK);
			tbl.addCell(cell);
            //------------------------ titre de détails de la l'etat globale ------------
			Paragraph titre_details = new Paragraph("Détails de L'Etat Globale", mainFont);
			titre_details.setAlignment(Element.ALIGN_CENTER);
			titre_details.setIndentationLeft(50);
			titre_details.setIndentationRight(50);
			titre_details.setPaddingTop(-200);
			//----------------------- Table de l'etat globale -----------------
			PdfPTable tabledetails = new PdfPTable(7);// column amount
			tabledetails.setWidthPercentage(100);
			tabledetails.setSpacingBefore(10f);
			tabledetails.setSpacingAfter(10);
			Font font_header = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			Font font_body = FontFactory.getFont("Arial", 9, BaseColor.BLACK);
			float[] nombre_colonnes = { 2f, 2f, 2f, 2f, 2f, 2f, 2f };
			tabledetails.setWidths(nombre_colonnes);
			PdfPCell police_ = new PdfPCell(new Paragraph("Police", tableHeader));
			police_.setBorderColor(BaseColor.BLACK);
			police_.setPaddingLeft(10);
			police_.setHorizontalAlignment(Element.ALIGN_CENTER);
			police_.setVerticalAlignment(Element.ALIGN_CENTER);
			police_.setBackgroundColor(BaseColor.LIGHT_GRAY);
			police_.setExtraParagraphSpace(5f);
			tabledetails.addCell(police_);
			PdfPCell matricule_ = new PdfPCell(new Paragraph("Matricule", tableHeader));
			matricule_.setBorderColor(BaseColor.BLACK);
			matricule_.setPaddingLeft(10);
			matricule_.setHorizontalAlignment(Element.ALIGN_CENTER);
			matricule_.setVerticalAlignment(Element.ALIGN_CENTER);
			matricule_.setBackgroundColor(BaseColor.LIGHT_GRAY);
			matricule_.setExtraParagraphSpace(5f);
			tabledetails.addCell(matricule_);
			PdfPCell effet_ = new PdfPCell(new Paragraph("Effet", tableHeader));
			effet_.setBorderColor(BaseColor.BLACK);
			effet_.setPaddingLeft(10);
			effet_.setHorizontalAlignment(Element.ALIGN_CENTER);
			effet_.setVerticalAlignment(Element.ALIGN_CENTER);
			effet_.setBackgroundColor(BaseColor.LIGHT_GRAY);
			effet_.setExtraParagraphSpace(5f);
			tabledetails.addCell(effet_);
			PdfPCell expiration_ = new PdfPCell(new Paragraph("Expiration", tableHeader));
			expiration_.setBorderColor(BaseColor.BLACK);
			expiration_.setPaddingLeft(10);
			expiration_.setHorizontalAlignment(Element.ALIGN_CENTER);
			expiration_.setVerticalAlignment(Element.ALIGN_CENTER);
			expiration_.setBackgroundColor(BaseColor.LIGHT_GRAY);
			expiration_.setExtraParagraphSpace(5f);
			tabledetails.addCell(expiration_);
			PdfPCell pttc_ = new PdfPCell(new Paragraph("Prix TTC", tableHeader));
			pttc_.setBorderColor(BaseColor.BLACK);
			pttc_.setPaddingLeft(10);
			pttc_.setHorizontalAlignment(Element.ALIGN_CENTER);
			pttc_.setVerticalAlignment(Element.ALIGN_CENTER);
			pttc_.setBackgroundColor(BaseColor.LIGHT_GRAY);
			pttc_.setExtraParagraphSpace(5f);
			tabledetails.addCell(pttc_);
			PdfPCell avance_ = new PdfPCell(new Paragraph("Avance", tableHeader));
			avance_.setBorderColor(BaseColor.BLACK);
			avance_.setPaddingLeft(10);
			avance_.setHorizontalAlignment(Element.ALIGN_CENTER);
			avance_.setVerticalAlignment(Element.ALIGN_CENTER);
			avance_.setBackgroundColor(BaseColor.LIGHT_GRAY);
			avance_.setExtraParagraphSpace(5f);
			tabledetails.addCell(avance_);
			PdfPCell reste_ = new PdfPCell(new Paragraph("Reste à Payer", tableHeader));
			reste_.setBorderColor(BaseColor.BLACK);
			reste_.setPaddingLeft(10);
			reste_.setHorizontalAlignment(Element.ALIGN_CENTER);
			reste_.setVerticalAlignment(Element.ALIGN_CENTER);
			reste_.setBackgroundColor(BaseColor.LIGHT_GRAY);
			reste_.setExtraParagraphSpace(5f);
			tabledetails.addCell(reste_);
			Double total_pht_ = 0.0;
			Double total_taxe_ = 0.0;
			Double total_pttc_ = 0.0;
			String pattern_ = "###,###.##";
			DecimalFormat decimalFormat_ = new DecimalFormat(pattern);
			for (Details_facture quittance_ : quittances_details) {
				total_pht += quittance_.getPht();
				total_taxe += quittance_.getTaxe();
				total_pttc += quittance_.getPttc();
				PdfPCell policevalue_ = new PdfPCell(new Paragraph(quittance_.getPolice().toString(), tableHeader));
				policevalue_.setBorderColor(BaseColor.BLACK);
				policevalue_.setPaddingLeft(10);
				policevalue_.setHorizontalAlignment(Element.ALIGN_CENTER);
				policevalue_.setVerticalAlignment(Element.ALIGN_CENTER);
				policevalue_.setBackgroundColor(BaseColor.WHITE);
				policevalue_.setExtraParagraphSpace(5f);
				tabledetails.addCell(policevalue_);
				PdfPCell matriculevalue_ = new PdfPCell(new Paragraph(quittance_.getMatricule().toString(), tableHeader));
				matriculevalue_.setBorderColor(BaseColor.BLACK);
				matriculevalue_.setPaddingLeft(10);
				matriculevalue_.setHorizontalAlignment(Element.ALIGN_CENTER);
				matriculevalue_.setVerticalAlignment(Element.ALIGN_CENTER);
				matriculevalue_.setBackgroundColor(BaseColor.WHITE);
				matriculevalue_.setExtraParagraphSpace(5f);
				tabledetails.addCell(matriculevalue_);
				PdfPCell effet_value_= new PdfPCell(new Paragraph(quittance_.getEffet().toString(), tableHeader));
				effet_value_.setBorderColor(BaseColor.BLACK);
				effet_value_.setPaddingLeft(10);
				effet_value_.setHorizontalAlignment(Element.ALIGN_CENTER);
				effet_value_.setVerticalAlignment(Element.ALIGN_CENTER);
				effet_value_.setBackgroundColor(BaseColor.WHITE);
				effet_value_.setExtraParagraphSpace(5f);
				tabledetails.addCell(effet_value_);
				PdfPCell expiration_value_ = new PdfPCell(new Paragraph(quittance_.getExpiration().toString(), tableHeader));
				expiration_value_.setBorderColor(BaseColor.BLACK);
				expiration_value_.setPaddingLeft(10);
				expiration_value_.setHorizontalAlignment(Element.ALIGN_CENTER);
				expiration_value_.setVerticalAlignment(Element.ALIGN_CENTER);
				expiration_value_.setBackgroundColor(BaseColor.WHITE);
				expiration_value_.setExtraParagraphSpace(5f);
				tabledetails.addCell(expiration_value_);
				PdfPCell pttcvalue_ = new PdfPCell(new Paragraph(decimalFormat.format(quittance_.getPttc()), tableHeader));
				pttcvalue_.setBorderColor(BaseColor.BLACK);
				pttcvalue_.setPaddingLeft(10);
				pttcvalue_.setHorizontalAlignment(Element.ALIGN_CENTER);
				pttcvalue_.setVerticalAlignment(Element.ALIGN_CENTER);
				pttcvalue_.setBackgroundColor(BaseColor.WHITE);
				pttcvalue_.setExtraParagraphSpace(5f);
				tabledetails.addCell(pttcvalue_);
				PdfPCell avancevalue_ = new PdfPCell(new Paragraph(quittance_.getAvance().toString(), tableHeader));
				avancevalue_.setBorderColor(BaseColor.BLACK);
				avancevalue_.setPaddingLeft(10);
				avancevalue_.setHorizontalAlignment(Element.ALIGN_CENTER);
				avancevalue_.setVerticalAlignment(Element.ALIGN_CENTER);
				avancevalue_.setBackgroundColor(BaseColor.WHITE);
				pttcvalue_.setExtraParagraphSpace(5f);
				tabledetails.addCell(avancevalue_);
				PdfPCell rapvalaue_ = new PdfPCell(new Paragraph(decimalFormat.format(quittance_.getRap()), tableHeader));
				rapvalaue_.setBorderColor(BaseColor.BLACK);
				rapvalaue_.setPaddingLeft(10);
				rapvalaue_.setHorizontalAlignment(Element.ALIGN_CENTER);
				rapvalaue_.setVerticalAlignment(Element.ALIGN_CENTER);
				rapvalaue_.setBackgroundColor(BaseColor.WHITE);
				pttcvalue_.setExtraParagraphSpace(5f);
				tabledetails.addCell(rapvalaue_);
			}
			PdfPTable tbl_ = new PdfPTable(2);
			tbl_.setWidthPercentage(40);
			tbl_.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl_.setSpacingBefore(10f);
			tbl_.setSpacingAfter(10);
			PdfPCell cell_ = new PdfPCell(new Paragraph("Total Prime TTC"));
			cell_.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell_.setPaddingLeft(5);
			cell_.setExtraParagraphSpace(3f);
			cell_.setBorder(0);
			tbl_.addCell(cell_);
			cell_ = new PdfPCell(new Paragraph(decimalFormat.format(total_pttc)));
			cell_.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell_.setBackgroundColor(BaseColor.PINK);
			tbl_.addCell(cell_);
			tbl_.addCell(cell_);

			//----------------------- Insertion dans le document --------------
			document.add(table);
			document.add(tbl);
			document.add(titre_details);
			document.add(tabledetails);
		//	document.add(tbl_);
			document.close();
			writer.close();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	//*** pdf rd ***
	public boolean createPdf_rd(List<Facturation_Details_facture_Rd> quittances_payyes, Info_Client_Facture infoclient, String exercice,
			NtsSites infos_site, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
		Document document = new Document(PageSize.A4, 15, 15, 45, 30);
		try {
			String filePath = context.getRealPath("/resources/report");
			File file = new File(filePath);
			boolean exists = new File(filePath).exists();
			if (!exists) {
				new File(filePath).mkdirs();
			}
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "employees" + ".pdf"));
			document.open();
			//Image imgg = Image.getInstance("http://beleefmarokko.com/images/Image_001.png");
			//imgg.setAlignment(Element.ALIGN_LEFT);
			//imgg.scaleAbsolute(150, 20);
			PdfContentByte canvas = writer.getDirectContentUnder();
			//Image image = Image.getInstance(imgg);
			//image.scaleAbsolute(PageSize.A4);
			//image.setAbsolutePosition(0, 0);
			//image.setBorderWidthRight(0);
			canvas.saveState();
			PdfGState state = new PdfGState();
			state.setFillOpacity(0.6f);
			canvas.setGState(state);
			//canvas.addImage(image);
			canvas.restoreState();

			Font mainFont4 = FontFactory.getFont("Calibri", 14, BaseColor.BLUE);
			Font mainFont = FontFactory.getFont("Calibri", 18, BaseColor.BLUE);
			Font mainFont2 = FontFactory.getFont("Calibri", 11, BaseColor.BLACK);
			Font mainFont3 = FontFactory.getFont("Arial", 18, BaseColor.BLACK);
			Paragraph paragraphe = new Paragraph(" " + "Bureau  : " + infos_site.getAgence() + "\n Tél         : "
					+ infos_site.getTel() + "\n Email     : " + infos_site.getEmail() + "\n Adresse : "
					+ infos_site.getAdresse() + "\n ICE        : 001696879000083 ", mainFont2);
			paragraphe.setAlignment(Element.ALIGN_LEFT);
			paragraphe.setIndentationLeft(0);
			paragraphe.setIndentationRight(100);
			paragraphe.setSpacingBefore(10);
			paragraphe.setSpacingAfter(20);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy ");
			LocalDateTime now = LocalDateTime.now();
			Paragraph paragraphee = new Paragraph(
					"Facture N°: " + infoclient.getNumfacture() + "\n Date : " + dtf.format(now), mainFont2);
			paragraphee.setAlignment(Element.ALIGN_RIGHT);
			paragraphee.setIndentationLeft(10);
			paragraphee.setIndentationRight(20);
			Font tableBody = FontFactory.getFont("Calibri", 11, BaseColor.BLACK);
			Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11);
			PdfPTable table5 = new PdfPTable(1);
			table5.setWidthPercentage(60);
			table5.setHorizontalAlignment(Element.ALIGN_CENTER);
			table5.setSpacingBefore(10f);
			table5.setSpacingAfter(10);
			PdfPCell sinistre = new PdfPCell(new Paragraph("Information de Client ", boldFont));
			sinistre.setBorderColor(BaseColor.BLACK);
			sinistre.setPaddingLeft(5);
			sinistre.setHorizontalAlignment(Element.ALIGN_CENTER);
			sinistre.setVerticalAlignment(Element.ALIGN_CENTER);
			sinistre.setBackgroundColor(BaseColor.LIGHT_GRAY);
			sinistre.setExtraParagraphSpace(2f);
			sinistre.setColspan(4);
			table5.addCell(sinistre);

			PdfPCell loi_sinistre = new PdfPCell(new Paragraph(
					" Client             : " + infoclient.getClient() + "" + "\n Adresse         : " + infoclient.getAdresse()
							+ "" + "\n Tél                 : " + infoclient.getTelephone() + "                    "
							+ "Email:   " + infoclient.getEmail() + "\n Cin / Patente :   " + infoclient.getcin_pat(),
					tableBody));
			loi_sinistre.setBorderColor(BaseColor.BLACK);
//			                emailValue.setPaddingLeft(10);
			loi_sinistre.setHorizontalAlignment(Element.ALIGN_LEFT);
			loi_sinistre.setVerticalAlignment(Element.ALIGN_LEFT);
			loi_sinistre.setBackgroundColor(BaseColor.WHITE);
			loi_sinistre.setExtraParagraphSpace(5f);
			table5.addCell(loi_sinistre);

			Paragraph paragraph = new Paragraph("Facture Détaillée Risque Divers (Réglé) De L'exercice : " + exercice, mainFont4);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setPaddingTop(-200);
			// paragraph.setSpacingBefore(200);

			// document.add(img);

			document.add(paragraphee);
			document.add(paragraphe);
			document.add(table5);
			document.add(paragraph);

			PdfPTable table = new PdfPTable(4);// column amount
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10);
			Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			Font tableBodyy = FontFactory.getFont("Arial", 9, BaseColor.BLACK);
			float[] columnWidths = { 2f, 2f, 2f, 2f };
			table.setWidths(columnWidths);
			PdfPCell police = new PdfPCell(new Paragraph("Police", tableHeader));
			police.setBorderColor(BaseColor.BLACK);
			police.setPaddingLeft(10);
			police.setHorizontalAlignment(Element.ALIGN_CENTER);
			police.setVerticalAlignment(Element.ALIGN_CENTER);
			police.setBackgroundColor(BaseColor.LIGHT_GRAY);
			police.setExtraParagraphSpace(5f);
			table.addCell(police);
			/*
			 * PdfPCell matricule = new PdfPCell(new Paragraph("Matricule", tableHeader));
			 * matricule.setBorderColor(BaseColor.BLACK); matricule.setPaddingLeft(10);
			 * matricule.setHorizontalAlignment(Element.ALIGN_CENTER);
			 * matricule.setVerticalAlignment(Element.ALIGN_CENTER);
			 * matricule.setBackgroundColor(BaseColor.LIGHT_GRAY);
			 * matricule.setExtraParagraphSpace(5f);
			 *///table.addCell(matricule);
			PdfPCell effet = new PdfPCell(new Paragraph("Effet", tableHeader));
			effet.setBorderColor(BaseColor.BLACK);
			effet.setPaddingLeft(10);
			effet.setHorizontalAlignment(Element.ALIGN_CENTER);
			effet.setVerticalAlignment(Element.ALIGN_CENTER);
			effet.setBackgroundColor(BaseColor.LIGHT_GRAY);
			effet.setExtraParagraphSpace(5f);
			table.addCell(effet);
			PdfPCell expiration = new PdfPCell(new Paragraph("Expiration", tableHeader));
			expiration.setBorderColor(BaseColor.BLACK);
			expiration.setPaddingLeft(10);
			expiration.setHorizontalAlignment(Element.ALIGN_CENTER);
			expiration.setVerticalAlignment(Element.ALIGN_CENTER);
			expiration.setBackgroundColor(BaseColor.LIGHT_GRAY);
			expiration.setExtraParagraphSpace(5f);
			table.addCell(expiration);
			PdfPCell pttc = new PdfPCell(new Paragraph("Prime TTC", tableHeader));
			pttc.setBorderColor(BaseColor.BLACK);
			pttc.setPaddingLeft(10);
			pttc.setHorizontalAlignment(Element.ALIGN_CENTER);
			pttc.setVerticalAlignment(Element.ALIGN_CENTER);
			pttc.setBackgroundColor(BaseColor.LIGHT_GRAY);
			pttc.setExtraParagraphSpace(5f);
			table.addCell(pttc);
			Double total_pht = 0.0;
			Double total_taxe = 0.0;
			Double total_pttc = 0.0;
			String pattern = "###,###.##";
			DecimalFormat decimalFormat = new DecimalFormat(pattern);
			for (Facturation_Details_facture_Rd quittance : quittances_payyes) {
				total_pht += quittance.getPht();
				total_taxe += quittance.getTaxe();
				total_pttc += quittance.getPttc();
				PdfPCell policevalue = new PdfPCell(new Paragraph(quittance.getPolice().toString(), tableHeader));
				policevalue.setBorderColor(BaseColor.BLACK);
				policevalue.setPaddingLeft(10);
				policevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				policevalue.setVerticalAlignment(Element.ALIGN_CENTER);
				policevalue.setBackgroundColor(BaseColor.WHITE);
				policevalue.setExtraParagraphSpace(5f);
				table.addCell(policevalue);
				/*
				 * PdfPCell matriculevalue = new PdfPCell(new
				 * Paragraph(quittance.getMatricule().toString(), tableHeader));
				 * matriculevalue.setBorderColor(BaseColor.BLACK);
				 * matriculevalue.setPaddingLeft(10);
				 * matriculevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * matriculevalue.setVerticalAlignment(Element.ALIGN_CENTER);
				 * matriculevalue.setBackgroundColor(BaseColor.WHITE);
				 * matriculevalue.setExtraParagraphSpace(5f);
				 */			//	table.addCell(matriculevalue);
				PdfPCell effet_value = new PdfPCell(new Paragraph(quittance.getEffet().toString(), tableHeader));
				effet_value.setBorderColor(BaseColor.BLACK);
				effet_value.setPaddingLeft(10);
				effet_value.setHorizontalAlignment(Element.ALIGN_CENTER);
				effet_value.setVerticalAlignment(Element.ALIGN_CENTER);
				effet_value.setBackgroundColor(BaseColor.WHITE);
				effet_value.setExtraParagraphSpace(5f);
				table.addCell(effet_value);
				PdfPCell expiration_value = new PdfPCell(new Paragraph(quittance.getExpiration() .toString(), tableHeader));
				expiration_value.setBorderColor(BaseColor.BLACK);
				expiration_value.setPaddingLeft(10);
				expiration_value.setHorizontalAlignment(Element.ALIGN_CENTER);
				expiration_value.setVerticalAlignment(Element.ALIGN_CENTER);
				expiration_value.setBackgroundColor(BaseColor.WHITE);
				expiration_value.setExtraParagraphSpace(5f);
				table.addCell(expiration_value);
				PdfPCell pttcvalue = new PdfPCell(new Paragraph(decimalFormat.format(quittance.getPttc()), tableHeader));
				pttcvalue.setBorderColor(BaseColor.BLACK);
				pttcvalue.setPaddingLeft(10);
				pttcvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				pttcvalue.setVerticalAlignment(Element.ALIGN_CENTER);
				pttcvalue.setBackgroundColor(BaseColor.WHITE);
				pttcvalue.setExtraParagraphSpace(5f);
				table.addCell(pttcvalue);
			}
			PdfPTable tbl = new PdfPTable(2);
			tbl.setWidthPercentage(40);
			tbl.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl.setSpacingBefore(10f);
			tbl.setSpacingAfter(10);
			PdfPCell cell = new PdfPCell(new Paragraph("Total Prime TTC"));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(5);
			cell.setExtraParagraphSpace(3f);
			cell.setBorder(0);
			tbl.addCell(cell);
			cell = new PdfPCell(new Paragraph(decimalFormat.format(total_pttc)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.PINK);
			tbl.addCell(cell);
			tbl.addCell(cell);

			document.add(table);
			document.add(tbl);
			document.close();
			writer.close();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	
	
	
	
	public boolean createPdf_flotte(List<Details_facture_flotte> quittances_payyes,Info_Client_Facture infoclient, String exercice,NtsSites infos_site, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
		Document document = new Document(PageSize.A4, 15, 15, 45, 30);
		try {
			String filePath = context.getRealPath("/resources/report");
			File file = new File(filePath);
			boolean exists = new File(filePath).exists();
			if (!exists) {
				new File(filePath).mkdirs();
			}
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "employees" + ".pdf"));
			document.open();

			PdfContentByte canvas = writer.getDirectContentUnder();
	
			canvas.saveState();
			PdfGState state = new PdfGState();
			state.setFillOpacity(0.6f);
			canvas.setGState(state);
			canvas.restoreState();
			Font mainFont = FontFactory.getFont("Calibri", 18, BaseColor.BLUE);
			Font mainFont2 = FontFactory.getFont("Calibri", 11, BaseColor.BLACK);
			Font mainFont3 = FontFactory.getFont("Arial", 18, BaseColor.BLACK);
			Paragraph paragraphe = new Paragraph(" " + "Bureau  : " + infos_site.getAgence() + "\n Tél         : "
					+ infos_site.getTel() + "\n Email     : " + infos_site.getEmail() + "\n Adresse : "
					+ infos_site.getAdresse() + "\n ICE        : 001696879000083 ", mainFont2);
			paragraphe.setAlignment(Element.ALIGN_LEFT);
			paragraphe.setIndentationLeft(0);
			paragraphe.setIndentationRight(100);
			paragraphe.setSpacingBefore(10);
			paragraphe.setSpacingAfter(20);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy ");
			LocalDateTime now = LocalDateTime.now();
			Paragraph paragraphee = new Paragraph(
					"Facture N°: " + infoclient.getNumfacture() + "\n Date : " + dtf.format(now), mainFont2);
			paragraphee.setAlignment(Element.ALIGN_RIGHT);
			paragraphee.setIndentationLeft(10);
			paragraphee.setIndentationRight(20);
			Font tableBody = FontFactory.getFont("Calibri", 11, BaseColor.BLACK);
			Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11);
			PdfPTable table5 = new PdfPTable(1);
			table5.setWidthPercentage(60);
			table5.setHorizontalAlignment(Element.ALIGN_CENTER);
			table5.setSpacingBefore(10f);
			table5.setSpacingAfter(10);
			PdfPCell sinistre = new PdfPCell(new Paragraph("Information de Client ", boldFont));
			sinistre.setBorderColor(BaseColor.BLACK);
			sinistre.setPaddingLeft(5);
			sinistre.setHorizontalAlignment(Element.ALIGN_CENTER);
			sinistre.setVerticalAlignment(Element.ALIGN_CENTER);
			sinistre.setBackgroundColor(BaseColor.LIGHT_GRAY);
			sinistre.setExtraParagraphSpace(2f);
			sinistre.setColspan(4);
			table5.addCell(sinistre);

			PdfPCell loi_sinistre = new PdfPCell(new Paragraph(
					" Client             : " + infoclient.getClient() + "" + "\n Adresse         : " + infoclient.getAdresse()
							+ "" + "\n Tél                 : " + infoclient.getTelephone() + "                    "
							+ "Email:   " + infoclient.getEmail() + "\n Cin / Patente :   " + infoclient.getcin_pat(),
					tableBody));
			loi_sinistre.setBorderColor(BaseColor.BLACK);
			loi_sinistre.setHorizontalAlignment(Element.ALIGN_LEFT);
			loi_sinistre.setVerticalAlignment(Element.ALIGN_LEFT);
			loi_sinistre.setBackgroundColor(BaseColor.WHITE);
			loi_sinistre.setExtraParagraphSpace(5f);
			table5.addCell(loi_sinistre);

			Paragraph paragraph = new Paragraph("Facture Détaillée Flotte (Réglé) De L'exercice : " + exercice, mainFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setPaddingTop(-200);


			document.add(paragraphee);
			document.add(paragraphe);
			document.add(table5);
			document.add(paragraph);

			
			  PdfPTable table = new PdfPTable(5);// column amount
			  table.setWidthPercentage(100); table.setSpacingBefore(10f);
			  table.setSpacingAfter(10); Font tableHeader = FontFactory.getFont("Arial",
			  10, BaseColor.BLACK); Font tableBodyy = FontFactory.getFont("Arial", 9,
			  BaseColor.BLACK); float[] columnWidths = { 2f, 2f, 2f, 2f, 2f };
			  table.setWidths(columnWidths); PdfPCell police = new PdfPCell(new
			  Paragraph("Police", tableHeader)); police.setBorderColor(BaseColor.BLACK);
			  police.setPaddingLeft(10);
			  police.setHorizontalAlignment(Element.ALIGN_CENTER);
			  police.setVerticalAlignment(Element.ALIGN_CENTER);
			  police.setBackgroundColor(BaseColor.LIGHT_GRAY);
			  police.setExtraParagraphSpace(5f); table.addCell(police); PdfPCell matricule
			  = new PdfPCell(new Paragraph("Matricule", tableHeader));
			  matricule.setBorderColor(BaseColor.BLACK); matricule.setPaddingLeft(10);
			  matricule.setHorizontalAlignment(Element.ALIGN_CENTER);
			  matricule.setVerticalAlignment(Element.ALIGN_CENTER);
			  matricule.setBackgroundColor(BaseColor.LIGHT_GRAY);
			  matricule.setExtraParagraphSpace(5f); table.addCell(matricule); PdfPCell
			  effet = new PdfPCell(new Paragraph("Effet", tableHeader));
			  effet.setBorderColor(BaseColor.BLACK); effet.setPaddingLeft(10);
			  effet.setHorizontalAlignment(Element.ALIGN_CENTER);
			  effet.setVerticalAlignment(Element.ALIGN_CENTER);
			  effet.setBackgroundColor(BaseColor.LIGHT_GRAY);
			  effet.setExtraParagraphSpace(5f); table.addCell(effet); PdfPCell expiration =
			  new PdfPCell(new Paragraph("Expiration", tableHeader));
			  expiration.setBorderColor(BaseColor.BLACK); expiration.setPaddingLeft(10);
			  expiration.setHorizontalAlignment(Element.ALIGN_CENTER);
			  expiration.setVerticalAlignment(Element.ALIGN_CENTER);
			  expiration.setBackgroundColor(BaseColor.LIGHT_GRAY);
			  expiration.setExtraParagraphSpace(5f); table.addCell(expiration); PdfPCell
			  pttc = new PdfPCell(new Paragraph("Prime TTC", tableHeader));
			  pttc.setBorderColor(BaseColor.BLACK); pttc.setPaddingLeft(10);
			  pttc.setHorizontalAlignment(Element.ALIGN_CENTER);
			  pttc.setVerticalAlignment(Element.ALIGN_CENTER);
			  pttc.setBackgroundColor(BaseColor.LIGHT_GRAY);
			  pttc.setExtraParagraphSpace(5f); table.addCell(pttc); Double total_pht = 0.0;
			  Double total_taxe = 0.0; Double total_pttc = 0.0; String pattern =
			  "###,###.##"; DecimalFormat decimalFormat = new DecimalFormat(pattern);
			 
			
			  for (Details_facture_flotte quittance : quittances_payyes) {
				 total_pht +=  quittance.getPht(); 
				 total_taxe += quittance.getTaxe();
				 total_pttc += quittance.getPttc(); 
			  PdfPCell policevalue = new PdfPCell(new Paragraph(quittance.getPolice().toString(), tableHeader));
			  policevalue.setBorderColor(BaseColor.BLACK); policevalue.setPaddingLeft(10);
			  policevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
			  policevalue.setVerticalAlignment(Element.ALIGN_CENTER);
			  policevalue.setBackgroundColor(BaseColor.WHITE);
			  policevalue.setExtraParagraphSpace(5f); 
			  
			  table.addCell(policevalue); PdfPCell
			  matriculevalue = new PdfPCell(new
			  Paragraph(quittance.getMatricule().toString(), tableHeader));
			  matriculevalue.setBorderColor(BaseColor.BLACK);
			  matriculevalue.setPaddingLeft(10);
			  matriculevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
			  matriculevalue.setVerticalAlignment(Element.ALIGN_CENTER);
			  matriculevalue.setBackgroundColor(BaseColor.WHITE);
			  matriculevalue.setExtraParagraphSpace(5f); table.addCell(matriculevalue);
			  PdfPCell effet_value = new PdfPCell(new
			  Paragraph(quittance.getEffet().toString(), tableHeader));
			  effet_value.setBorderColor(BaseColor.BLACK); effet_value.setPaddingLeft(10);
			  effet_value.setHorizontalAlignment(Element.ALIGN_CENTER);
			  effet_value.setVerticalAlignment(Element.ALIGN_CENTER);
			  effet_value.setBackgroundColor(BaseColor.WHITE);
			  effet_value.setExtraParagraphSpace(5f); table.addCell(effet_value); PdfPCell
			  expiration_value = new PdfPCell(new
			  Paragraph(quittance.getExpiration().toString(), tableHeader));
			  expiration_value.setBorderColor(BaseColor.BLACK);
			  expiration_value.setPaddingLeft(10);
			  expiration_value.setHorizontalAlignment(Element.ALIGN_CENTER);
			  expiration_value.setVerticalAlignment(Element.ALIGN_CENTER);
			  expiration_value.setBackgroundColor(BaseColor.WHITE);
			  expiration_value.setExtraParagraphSpace(5f); table.addCell(expiration_value);
			  PdfPCell pttcvalue = new PdfPCell(new
			  Paragraph(decimalFormat.format(quittance.getPttc()), tableHeader));
			  pttcvalue.setBorderColor(BaseColor.BLACK); pttcvalue.setPaddingLeft(10);
			  pttcvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
			  pttcvalue.setVerticalAlignment(Element.ALIGN_CENTER);
			  pttcvalue.setBackgroundColor(BaseColor.WHITE);
			  pttcvalue.setExtraParagraphSpace(5f); table.addCell(pttcvalue); }
			 
			
			  PdfPTable tbl = new PdfPTable(2); tbl.setWidthPercentage(40);
			  tbl.setHorizontalAlignment(Element.ALIGN_RIGHT); tbl.setSpacingBefore(10f);
			  tbl.setSpacingAfter(10); PdfPCell cell = new PdfPCell(new
			  Paragraph("Total Prime TTC"));
			  cell.setHorizontalAlignment(Element.ALIGN_LEFT); cell.setPaddingLeft(5);
			  cell.setExtraParagraphSpace(3f); cell.setBorder(0); tbl.addCell(cell); cell =
			  new PdfPCell(new Paragraph(decimalFormat.format(total_pttc)));
			  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  cell.setBackgroundColor(BaseColor.PINK); tbl.addCell(cell);
			  tbl.addCell(cell);
			 

			document.add(table);
			document.add(tbl);
			document.close();
			writer.close();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	
	public boolean createPdf3(List<Edition_Facture_Flotte_Globale> quittances_payyes,
			Edition_Facture_Flotte_Globale info, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		// Document document = new Document(PageSize.A4, 15, 15, 45,30);
		Document document = new Document(PageSize.A3.rotate(), 30, 30, 10, 10);

		try {
			String filePath = context.getRealPath("/resources/report");
			File file = new File(filePath);
			boolean exists = new File(filePath).exists();
			if (!exists) {
				new File(filePath).mkdirs();
			}
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "employees" + ".pdf"));
			document.open();

			Font mainFont = FontFactory.getFont("CALIBRI", 20, BaseColor.BLUE);
			Font mainFont2 = FontFactory.getFont("CALIBRI", 11, BaseColor.BLACK);

			/*
			 * Image img = Image.getInstance("http://beleefmarokko.com/images/logo3.PNG");
			 * img.setAlignment(Element.ALIGN_LEFT); img.scaleAbsolute(150, 60);
			 */

			Image imgg = Image.getInstance("http://beleefmarokko.com/images/Image_001.png");

			PdfContentByte canvas = writer.getDirectContentUnder();

			Image image = Image.getInstance(imgg);
			image.scaleAbsolute(PageSize.A3.rotate());
			image.setAbsolutePosition(0, 0);
			canvas.saveState();
			PdfGState state = new PdfGState();
			state.setFillOpacity(0.6f);
			canvas.setGState(state);
			canvas.addImage(image);
			canvas.restoreState();

			Font tableBody = FontFactory.getFont("CALIBRI", 11, BaseColor.BLACK);
			Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11);

			PdfPTable table5 = new PdfPTable(1);

			table5.setWidthPercentage(100);
			table5.setHorizontalAlignment(Element.ALIGN_LEFT);
			table5.setSpacingBefore(10f);
			table5.setSpacingBefore(100);
			table5.setSpacingAfter(10);
//			                

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy ");
			LocalDateTime now = LocalDateTime.now();

			Paragraph paragraphee = new Paragraph(" Edition Du : " + dtf.format(now), mainFont2);
			paragraphee.setAlignment(Element.ALIGN_RIGHT);
			paragraphee.setIndentationLeft(10);
			paragraphee.setIndentationRight(20);
			paragraphee.setPaddingTop(-300);
			paragraphee.setSpacingAfter(-27);

			/*
			 * Paragraph paragraphe = new
			 * Paragraph(" Siége sociale : 215 Boulvard zerktouni Casablanca, Maroc" +
			 * "\n Tél : 05 22 95 45 00" + "\n Fax : 05 22 95 45 04" +
			 * "\n Email : info@matu-assurance.ma", mainFont2);
			 * paragraphe.setAlignment(Element.ALIGN_LEFT);
			 * paragraphe.setIndentationLeft(0); paragraphe.setIndentationRight(50);
			 * paragraphe.setPaddingTop(-300); paragraphe.setSpacingAfter(10);
			 */

			PdfPCell sinistre = new PdfPCell(new Paragraph("Information de Client ", boldFont));
			sinistre.setBorderColor(BaseColor.BLACK);
			sinistre.setPaddingLeft(5);
			sinistre.setHorizontalAlignment(Element.ALIGN_CENTER);
			sinistre.setVerticalAlignment(Element.ALIGN_CENTER);
			sinistre.setBackgroundColor(BaseColor.LIGHT_GRAY);
			sinistre.setExtraParagraphSpace(2f);
			sinistre.setColspan(4);
			table5.addCell(sinistre);
			Edition_Facture_Flotte_Globale e = new Edition_Facture_Flotte_Globale();

			PdfPCell loi_sinistre = new PdfPCell(new Paragraph(" " + info.getSite() + "" + "\n Client         : "
					+ info.getClient() + "" + "\n Adresse" + "     : " + info.getAdrclient1() + "", tableBody));
			loi_sinistre.setBorderColor(BaseColor.BLACK);
//			                emailValue.setPaddingLeft(10);
			loi_sinistre.setHorizontalAlignment(Element.ALIGN_LEFT);
			loi_sinistre.setVerticalAlignment(Element.ALIGN_LEFT);
			loi_sinistre.setBackgroundColor(BaseColor.WHITE);
			loi_sinistre.setExtraParagraphSpace(5f);
			table5.addCell(loi_sinistre);

			/*
			 * Paragraph paragraph = new Paragraph("Détails De La Facture ", mainFont);
			 * paragraph.setAlignment(Element.ALIGN_CENTER);
			 * paragraph.setIndentationLeft(50); paragraph.setIndentationRight(50);
			 * paragraph.setPaddingTop(-200);
			 */
			// paragraph.setSpacingBefore(200);

			// document.add(img);

			// document.add(paragraphee);
			// document.add(paragraphe);
			document.add(paragraphee);
			// document.add(paragraphe);
			document.add(table5);
			// document.add(paragraph);

			PdfPTable table = new PdfPTable(21);// column amount
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10);

			Font tableHeader = FontFactory.getFont("Arial", 8, BaseColor.BLACK);
			Font tableBodyy = FontFactory.getFont("Arial", 9, BaseColor.BLACK);

			float[] columnWidths = { 3f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 1f, 2f, 1f, 1f, 2f, 2f, 2f, 2f,
					2f };
			table.setWidths(columnWidths);
			PdfPCell police = new PdfPCell(new Paragraph("Nature Avenant", tableHeader));
			police.setBorderColor(BaseColor.BLACK);
			police.setPaddingLeft(10);
			police.setHorizontalAlignment(Element.ALIGN_CENTER);
			police.setVerticalAlignment(Element.ALIGN_CENTER);
			police.setBackgroundColor(BaseColor.LIGHT_GRAY);
			police.setExtraParagraphSpace(5f);
			table.addCell(police);
			PdfPCell attestation = new PdfPCell(new Paragraph("Attestation", tableHeader));
			attestation.setBorderColor(BaseColor.BLACK);
			attestation.setPaddingLeft(10);
			attestation.setHorizontalAlignment(Element.ALIGN_CENTER);
			attestation.setVerticalAlignment(Element.ALIGN_CENTER);
			attestation.setBackgroundColor(BaseColor.LIGHT_GRAY);
			attestation.setExtraParagraphSpace(5f);
			table.addCell(attestation);
			PdfPCell matricule = new PdfPCell(new Paragraph("Matricule", tableHeader));
			matricule.setBorderColor(BaseColor.BLACK);
			matricule.setPaddingLeft(10);
			matricule.setHorizontalAlignment(Element.ALIGN_CENTER);
			matricule.setVerticalAlignment(Element.ALIGN_CENTER);
			matricule.setBackgroundColor(BaseColor.LIGHT_GRAY);
			matricule.setExtraParagraphSpace(5f);
			table.addCell(matricule);

			PdfPCell places = new PdfPCell(new Paragraph("Places", tableHeader));
			places.setBorderColor(BaseColor.BLACK);
			places.setPaddingLeft(10);
			places.setHorizontalAlignment(Element.ALIGN_CENTER);
			places.setVerticalAlignment(Element.ALIGN_CENTER);
			places.setBackgroundColor(BaseColor.LIGHT_GRAY);
			places.setExtraParagraphSpace(5f);
			table.addCell(places);

			PdfPCell pht = new PdfPCell(new Paragraph("Val à Neuf", tableHeader));
			pht.setBorderColor(BaseColor.BLACK);
			pht.setPaddingLeft(10);
			pht.setHorizontalAlignment(Element.ALIGN_CENTER);
			pht.setVerticalAlignment(Element.ALIGN_CENTER);
			pht.setBackgroundColor(BaseColor.LIGHT_GRAY);
			pht.setExtraParagraphSpace(5f);
			table.addCell(pht);

			PdfPCell taxe = new PdfPCell(new Paragraph("Val Vénale", tableHeader));
			taxe.setBorderColor(BaseColor.BLACK);
			taxe.setPaddingLeft(10);
			taxe.setHorizontalAlignment(Element.ALIGN_CENTER);
			taxe.setVerticalAlignment(Element.ALIGN_CENTER);
			taxe.setBackgroundColor(BaseColor.LIGHT_GRAY);
			taxe.setExtraParagraphSpace(5f);
			table.addCell(taxe);

			PdfPCell pttc = new PdfPCell(new Paragraph("Effet", tableHeader));
			pttc.setBorderColor(BaseColor.BLACK);
			pttc.setPaddingLeft(10);
			pttc.setHorizontalAlignment(Element.ALIGN_CENTER);
			pttc.setVerticalAlignment(Element.ALIGN_CENTER);
			pttc.setBackgroundColor(BaseColor.LIGHT_GRAY);
			pttc.setExtraParagraphSpace(5f);
			table.addCell(pttc);
			PdfPCell pttc1 = new PdfPCell(new Paragraph("Expiration", tableHeader));
			pttc1.setBorderColor(BaseColor.BLACK);
			pttc1.setPaddingLeft(10);
			pttc1.setHorizontalAlignment(Element.ALIGN_CENTER);
			pttc1.setVerticalAlignment(Element.ALIGN_CENTER);
			pttc1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			pttc1.setExtraParagraphSpace(5f);
			table.addCell(pttc1);
			PdfPCell glaces = new PdfPCell(new Paragraph("Glaces", tableHeader));
			glaces.setBorderColor(BaseColor.BLACK);
			glaces.setPaddingLeft(10);
			glaces.setHorizontalAlignment(Element.ALIGN_CENTER);
			glaces.setVerticalAlignment(Element.ALIGN_CENTER);
			glaces.setBackgroundColor(BaseColor.LIGHT_GRAY);
			glaces.setExtraParagraphSpace(5f);
			table.addCell(glaces);
			PdfPCell rc = new PdfPCell(new Paragraph("RC", tableHeader));
			rc.setBorderColor(BaseColor.BLACK);
			rc.setPaddingLeft(10);
			rc.setHorizontalAlignment(Element.ALIGN_CENTER);
			rc.setVerticalAlignment(Element.ALIGN_CENTER);
			rc.setBackgroundColor(BaseColor.LIGHT_GRAY);
			rc.setExtraParagraphSpace(5f);
			table.addCell(rc);
			PdfPCell tierce = new PdfPCell(new Paragraph("Tiérce", tableHeader));
			tierce.setBorderColor(BaseColor.BLACK);
			tierce.setPaddingLeft(10);
			tierce.setHorizontalAlignment(Element.ALIGN_CENTER);
			tierce.setVerticalAlignment(Element.ALIGN_CENTER);
			tierce.setBackgroundColor(BaseColor.LIGHT_GRAY);
			tierce.setExtraParagraphSpace(5f);
			table.addCell(tierce);
			PdfPCell inc = new PdfPCell(new Paragraph("Incendie", tableHeader));
			inc.setBorderColor(BaseColor.BLACK);
			inc.setPaddingLeft(10);
			inc.setHorizontalAlignment(Element.ALIGN_CENTER);
			inc.setVerticalAlignment(Element.ALIGN_CENTER);
			inc.setBackgroundColor(BaseColor.LIGHT_GRAY);
			inc.setExtraParagraphSpace(5f);
			table.addCell(inc);
			PdfPCell vol = new PdfPCell(new Paragraph("Vol", tableHeader));
			vol.setBorderColor(BaseColor.BLACK);
			vol.setPaddingLeft(10);
			vol.setHorizontalAlignment(Element.ALIGN_CENTER);
			vol.setVerticalAlignment(Element.ALIGN_CENTER);
			vol.setBackgroundColor(BaseColor.LIGHT_GRAY);
			vol.setExtraParagraphSpace(5f);
			table.addCell(vol);
			PdfPCell bdg = new PdfPCell(new Paragraph("BDG", tableHeader));
			bdg.setBorderColor(BaseColor.BLACK);
			bdg.setPaddingLeft(10);
			bdg.setHorizontalAlignment(Element.ALIGN_CENTER);
			bdg.setVerticalAlignment(Element.ALIGN_CENTER);
			bdg.setBackgroundColor(BaseColor.LIGHT_GRAY);
			vol.setExtraParagraphSpace(5f);
			table.addCell(bdg);
			PdfPCell dc = new PdfPCell(new Paragraph("DC", tableHeader));
			dc.setBorderColor(BaseColor.BLACK);
			dc.setPaddingLeft(10);
			dc.setHorizontalAlignment(Element.ALIGN_CENTER);
			dc.setVerticalAlignment(Element.ALIGN_CENTER);
			dc.setBackgroundColor(BaseColor.LIGHT_GRAY);
			vol.setExtraParagraphSpace(5f);
			table.addCell(dc);
			PdfPCell dr = new PdfPCell(new Paragraph("DR", tableHeader));
			dr.setBorderColor(BaseColor.BLACK);
			dr.setPaddingLeft(10);
			dr.setHorizontalAlignment(Element.ALIGN_CENTER);
			dr.setVerticalAlignment(Element.ALIGN_CENTER);
			dr.setBackgroundColor(BaseColor.LIGHT_GRAY);
			dr.setExtraParagraphSpace(5f);
			table.addCell(dr);
			PdfPCell autre = new PdfPCell(new Paragraph("Autre", tableHeader));
			autre.setBorderColor(BaseColor.BLACK);
			autre.setPaddingLeft(10);
			autre.setHorizontalAlignment(Element.ALIGN_CENTER);
			autre.setVerticalAlignment(Element.ALIGN_CENTER);
			autre.setBackgroundColor(BaseColor.LIGHT_GRAY);
			autre.setExtraParagraphSpace(5f);
			table.addCell(autre);
			PdfPCell cot_ht = new PdfPCell(new Paragraph("Cot.HT", tableHeader));
			cot_ht.setBorderColor(BaseColor.BLACK);
			cot_ht.setPaddingLeft(10);
			cot_ht.setHorizontalAlignment(Element.ALIGN_CENTER);
			cot_ht.setVerticalAlignment(Element.ALIGN_CENTER);
			cot_ht.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cot_ht.setExtraParagraphSpace(5f);
			table.addCell(cot_ht);
			PdfPCell taxes = new PdfPCell(new Paragraph("Taxes", tableHeader));
			taxes.setBorderColor(BaseColor.BLACK);
			taxes.setPaddingLeft(10);
			taxes.setHorizontalAlignment(Element.ALIGN_CENTER);
			taxes.setVerticalAlignment(Element.ALIGN_CENTER);
			taxes.setBackgroundColor(BaseColor.LIGHT_GRAY);
			taxes.setExtraParagraphSpace(5f);
			table.addCell(taxes);
			PdfPCell cnpac = new PdfPCell(new Paragraph("CNPAC", tableHeader));
			cnpac.setBorderColor(BaseColor.BLACK);
			cnpac.setPaddingLeft(10);
			cnpac.setHorizontalAlignment(Element.ALIGN_CENTER);
			cnpac.setVerticalAlignment(Element.ALIGN_CENTER);
			cnpac.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cnpac.setExtraParagraphSpace(5f);
			table.addCell(cnpac);
			PdfPCell cot_ttc = new PdfPCell(new Paragraph("Cot.TTC", tableHeader));
			cot_ttc.setBorderColor(BaseColor.BLACK);
			cot_ttc.setPaddingLeft(10);
			cot_ttc.setHorizontalAlignment(Element.ALIGN_CENTER);
			cot_ttc.setVerticalAlignment(Element.ALIGN_CENTER);
			cot_ttc.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cot_ttc.setExtraParagraphSpace(5f);
			table.addCell(cot_ttc);

			Double total_cot_ttc = 0.0;

			String pattern = "###,###.##";
			DecimalFormat decimalFormat = new DecimalFormat(pattern);

			for (Edition_Facture_Flotte_Globale quittance : quittances_payyes) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

				total_cot_ttc += quittance.getCotttc();

				PdfPCell policevalue = new PdfPCell(new Paragraph(quittance.getNatavenant().toString(), tableHeader));
				policevalue.setBorderColor(BaseColor.BLACK);
				policevalue.setPaddingLeft(10);
				policevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				policevalue.setVerticalAlignment(Element.ALIGN_CENTER);
				policevalue.setBackgroundColor(BaseColor.WHITE);
				policevalue.setExtraParagraphSpace(5f);
				table.addCell(policevalue);
				PdfPCell matriculevalue = new PdfPCell(
						new Paragraph(quittance.getAttestation().toString(), tableHeader));
				matriculevalue.setBorderColor(BaseColor.BLACK);
				matriculevalue.setPaddingLeft(10);
				matriculevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				matriculevalue.setVerticalAlignment(Element.ALIGN_CENTER);
				matriculevalue.setBackgroundColor(BaseColor.WHITE);
				matriculevalue.setExtraParagraphSpace(5f);
				table.addCell(matriculevalue);
				PdfPCell matriculevalue2 = new PdfPCell(
						new Paragraph(quittance.getMatricule().toString(), tableHeader));
				matriculevalue2.setBorderColor(BaseColor.BLACK);
				matriculevalue2.setPaddingLeft(10);
				matriculevalue2.setHorizontalAlignment(Element.ALIGN_CENTER);
				matriculevalue2.setVerticalAlignment(Element.ALIGN_CENTER);
				matriculevalue2.setBackgroundColor(BaseColor.WHITE);
				matriculevalue2.setExtraParagraphSpace(5f);
				table.addCell(matriculevalue2);
				PdfPCell matriculevalue22 = new PdfPCell(new Paragraph(quittance.getPlaces().toString(), tableHeader));
				matriculevalue22.setBorderColor(BaseColor.BLACK);
				matriculevalue22.setPaddingLeft(10);
				matriculevalue22.setHorizontalAlignment(Element.ALIGN_CENTER);
				matriculevalue22.setVerticalAlignment(Element.ALIGN_CENTER);
				matriculevalue22.setBackgroundColor(BaseColor.WHITE);
				matriculevalue22.setExtraParagraphSpace(5f);
				table.addCell(matriculevalue22);
				PdfPCell phtvlaue = new PdfPCell(
						new Paragraph(decimalFormat.format(quittance.getR_val_neuv()), tableHeader));
				phtvlaue.setBorderColor(BaseColor.BLACK);
				phtvlaue.setPaddingLeft(10);
				phtvlaue.setHorizontalAlignment(Element.ALIGN_CENTER);
				phtvlaue.setVerticalAlignment(Element.ALIGN_CENTER);
				phtvlaue.setBackgroundColor(BaseColor.WHITE);
				phtvlaue.setExtraParagraphSpace(5f);
				table.addCell(phtvlaue);
				PdfPCell taxevalue = new PdfPCell(
						new Paragraph(decimalFormat.format(quittance.getV_val_vena()), tableHeader));
				taxevalue.setBorderColor(BaseColor.BLACK);
				taxevalue.setPaddingLeft(10);
				taxevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				taxevalue.setVerticalAlignment(Element.ALIGN_CENTER);
				taxevalue.setBackgroundColor(BaseColor.WHITE);
				taxevalue.setExtraParagraphSpace(5f);
				table.addCell(taxevalue);
				PdfPCell pttcvalue = new PdfPCell(new Paragraph(quittance.getDate_effet(), tableHeader));
				pttcvalue.setBorderColor(BaseColor.BLACK);
				pttcvalue.setPaddingLeft(10);
				pttcvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				pttcvalue.setVerticalAlignment(Element.ALIGN_CENTER);
				pttcvalue.setBackgroundColor(BaseColor.WHITE);
				pttcvalue.setExtraParagraphSpace(5f);
				table.addCell(pttcvalue);
				PdfPCell pttcvalue1 = new PdfPCell(new Paragraph(quittance.getDate_au().toString(), tableHeader));
				pttcvalue1.setBorderColor(BaseColor.BLACK);
				pttcvalue1.setPaddingLeft(10);
				pttcvalue1.setHorizontalAlignment(Element.ALIGN_CENTER);
				pttcvalue1.setVerticalAlignment(Element.ALIGN_CENTER);
				pttcvalue1.setBackgroundColor(BaseColor.WHITE);
				pttcvalue1.setExtraParagraphSpace(5f);
				table.addCell(pttcvalue1);
				PdfPCell glace_value = new PdfPCell(new Paragraph(quittance.getV_val_gla().toString(), tableHeader));
				glace_value.setBorderColor(BaseColor.BLACK);
				glace_value.setPaddingLeft(10);
				glace_value.setHorizontalAlignment(Element.ALIGN_CENTER);
				glace_value.setVerticalAlignment(Element.ALIGN_CENTER);
				glace_value.setBackgroundColor(BaseColor.WHITE);
				glace_value.setExtraParagraphSpace(5f);
				table.addCell(glace_value);
				PdfPCell rc_value = new PdfPCell(new Paragraph(quittance.getRc().toString(), tableHeader));
				rc_value.setBorderColor(BaseColor.BLACK);
				rc_value.setPaddingLeft(10);
				rc_value.setHorizontalAlignment(Element.ALIGN_CENTER);
				rc_value.setVerticalAlignment(Element.ALIGN_CENTER);
				rc_value.setBackgroundColor(BaseColor.WHITE);
				rc_value.setExtraParagraphSpace(5f);
				table.addCell(rc_value);
				PdfPCell tierce_value = new PdfPCell(new Paragraph(quittance.getTierce().toString(), tableHeader));
				tierce_value.setBorderColor(BaseColor.BLACK);
				tierce_value.setPaddingLeft(10);
				tierce_value.setHorizontalAlignment(Element.ALIGN_CENTER);
				tierce_value.setVerticalAlignment(Element.ALIGN_CENTER);
				tierce_value.setBackgroundColor(BaseColor.WHITE);
				tierce_value.setExtraParagraphSpace(5f);
				table.addCell(tierce_value);
				PdfPCell incendie_value = new PdfPCell(new Paragraph(quittance.getInc().toString(), tableHeader));
				incendie_value.setBorderColor(BaseColor.BLACK);
				incendie_value.setPaddingLeft(10);
				incendie_value.setHorizontalAlignment(Element.ALIGN_CENTER);
				incendie_value.setVerticalAlignment(Element.ALIGN_CENTER);
				incendie_value.setBackgroundColor(BaseColor.WHITE);
				incendie_value.setExtraParagraphSpace(5f);
				table.addCell(incendie_value);
				PdfPCell vol_value = new PdfPCell(new Paragraph(quittance.getVol().toString(), tableHeader));
				vol_value.setBorderColor(BaseColor.BLACK);
				vol_value.setPaddingLeft(10);
				vol_value.setHorizontalAlignment(Element.ALIGN_CENTER);
				vol_value.setVerticalAlignment(Element.ALIGN_CENTER);
				vol_value.setBackgroundColor(BaseColor.WHITE);
				vol_value.setExtraParagraphSpace(5f);
				table.addCell(vol_value);
				PdfPCell bdg_value = new PdfPCell(new Paragraph(quittance.getBdg().toString(), tableHeader));
				bdg_value.setBorderColor(BaseColor.BLACK);
				bdg_value.setPaddingLeft(10);
				bdg_value.setHorizontalAlignment(Element.ALIGN_CENTER);
				bdg_value.setVerticalAlignment(Element.ALIGN_CENTER);
				bdg_value.setBackgroundColor(BaseColor.WHITE);
				bdg_value.setExtraParagraphSpace(5f);
				table.addCell(bdg_value);
				PdfPCell dc_value = new PdfPCell(new Paragraph(quittance.getDc().toString(), tableHeader));
				dc_value.setBorderColor(BaseColor.BLACK);
				dc_value.setPaddingLeft(10);
				dc_value.setHorizontalAlignment(Element.ALIGN_CENTER);
				dc_value.setVerticalAlignment(Element.ALIGN_CENTER);
				dc_value.setBackgroundColor(BaseColor.WHITE);
				dc_value.setExtraParagraphSpace(5f);
				table.addCell(dc_value);
				PdfPCell dr_value = new PdfPCell(new Paragraph(quittance.getDr().toString(), tableHeader));
				dr_value.setBorderColor(BaseColor.BLACK);
				dr_value.setPaddingLeft(10);
				dr_value.setHorizontalAlignment(Element.ALIGN_CENTER);
				dr_value.setVerticalAlignment(Element.ALIGN_CENTER);
				dr_value.setBackgroundColor(BaseColor.WHITE);
				dr_value.setExtraParagraphSpace(5f);
				table.addCell(dr_value);
				PdfPCell autre_value = new PdfPCell(new Paragraph(quittance.getAutre().toString(), tableHeader));
				autre_value.setBorderColor(BaseColor.BLACK);
				autre_value.setPaddingLeft(10);
				autre_value.setHorizontalAlignment(Element.ALIGN_CENTER);
				autre_value.setVerticalAlignment(Element.ALIGN_CENTER);
				autre_value.setBackgroundColor(BaseColor.WHITE);
				autre_value.setExtraParagraphSpace(5f);
				table.addCell(autre_value);
				PdfPCell cot_ht_value = new PdfPCell(new Paragraph(quittance.getCot_ht().toString(), tableHeader));
				cot_ht_value.setBorderColor(BaseColor.BLACK);
				cot_ht_value.setPaddingLeft(10);
				cot_ht_value.setHorizontalAlignment(Element.ALIGN_CENTER);
				cot_ht_value.setVerticalAlignment(Element.ALIGN_CENTER);
				cot_ht_value.setBackgroundColor(BaseColor.WHITE);
				cot_ht_value.setExtraParagraphSpace(5f);
				table.addCell(cot_ht_value);
				PdfPCell taxes_value = new PdfPCell(new Paragraph(quittance.getTaxe().toString(), tableHeader));
				taxes_value.setBorderColor(BaseColor.BLACK);
				taxes_value.setPaddingLeft(10);
				taxes_value.setHorizontalAlignment(Element.ALIGN_CENTER);
				taxes_value.setVerticalAlignment(Element.ALIGN_CENTER);
				taxes_value.setBackgroundColor(BaseColor.WHITE);
				taxes_value.setExtraParagraphSpace(5f);
				table.addCell(taxes_value);
				PdfPCell cnpas_value = new PdfPCell(new Paragraph(quittance.getAcctim().toString(), tableHeader));
				cnpas_value.setBorderColor(BaseColor.BLACK);
				cnpas_value.setPaddingLeft(10);
				cnpas_value.setHorizontalAlignment(Element.ALIGN_CENTER);
				cnpas_value.setVerticalAlignment(Element.ALIGN_CENTER);
				cnpas_value.setBackgroundColor(BaseColor.WHITE);
				cnpas_value.setExtraParagraphSpace(5f);
				table.addCell(cnpas_value);
				PdfPCell cotttc_value = new PdfPCell(new Paragraph(quittance.getCotttc().toString(), tableHeader));
				cotttc_value.setBorderColor(BaseColor.BLACK);
				cotttc_value.setPaddingLeft(10);
				cotttc_value.setHorizontalAlignment(Element.ALIGN_CENTER);
				cotttc_value.setVerticalAlignment(Element.ALIGN_CENTER);
				cotttc_value.setBackgroundColor(BaseColor.WHITE);
				cotttc_value.setExtraParagraphSpace(5f);
				table.addCell(cotttc_value);

			}
			PdfPTable tbl = new PdfPTable(2);
			tbl.setWidthPercentage(50);
			tbl.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl.setSpacingBefore(10f);
			tbl.setSpacingAfter(10);
			PdfPCell cell = new PdfPCell(new Paragraph("Total Cot TTC"));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(10);
			cell.setExtraParagraphSpace(5f);
			cell.setBorder(0);
			tbl.addCell(cell);

			/*
			 * Paragraph footer = new
			 * Paragraph("Vueillez libeller tous les chéques à l'odrre de MATU Assurance" +
			 * "\n le relevé détaillé est joint à cette Facture" +
			 * "\n\n\n Nous Vous remercion de votre confiance.", mainFont2);
			 * footer.setAlignment(Element.ALIGN_LEFT); footer.setIndentationLeft(0);
			 * footer.setIndentationRight(50); footer.setPaddingTop(300);
			 * footer.setSpacingAfter(10); footer.setSpacingBefore(200);
			 */

			document.add(table);
			document.add(tbl);
			// document.add(footer);
			document.close();
			writer.close();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

}
