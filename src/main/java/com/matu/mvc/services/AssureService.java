//package com.matu.mvc.services;
//
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.List;
//
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
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
////public class Contrat_service {
//    private final WAFAIMA_ContratsRpository contratrepository;
//
//    @Autowired
//    public Contrat_service(WAFAIMA_ContratsRpository contratrepository) {
//        this.contratrepository = contratrepository;
//    }
//
//    public List<WAFAIMA_Contrats> get_clients_wafa_ima() {
//        return contratrepository.findAll();
//    }
//
//    public boolean createPdf(List<WAFAIMA_Contrats> affaires_wafaima, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
//        Document document = new Document(PageSize.A4, 15, 15, 45,30);
//        try {
//            String filePath = context.getRealPath("/resources/report");
//            File file = new File(filePath);
//            boolean exists = new File(filePath).exists();
//            if (!exists){
//                new  File(filePath).mkdirs();
//            }
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file+"/"+"employees"+".pdf"));
//            document.open();
//           
//            Image img = Image.getInstance("https://yt3.ggpht.com/a/AGF-l78nkV03kxj_a3li9PcDjruzJY7QeBug9mnKjg=s900-mo-c-c0xffffffff-rj-k-no");
////            img.setAbsolutePosition(450f, 10f);
//            img.scaleAbsolute(100, 100);
//            Font mainFont = FontFactory.getFont("Arial", 20, BaseColor.BLUE);
//            
//
//            Paragraph paragraph = new Paragraph("Liste des affaires wafaima assisatance", mainFont);
//            paragraph.setAlignment(Element.ALIGN_CENTER);
//            paragraph.setIndentationLeft(50);
//            paragraph.setIndentationRight(50);
//            paragraph.setPaddingTop(-100);
//            paragraph.setSpacingAfter(10);
//            
//            
//            document.add(img);
//            document.add(paragraph);
//          
//
//            PdfPTable table = new PdfPTable(4);//column amount
//            table.setWidthPercentage(100);
//            table.setSpacingBefore(10f);
//            table.setSpacingAfter(10);
//            
//
//            Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
//            Font tableBody = FontFactory.getFont("Arial", 9, BaseColor.BLACK);
//
//            float[] columnWidths = {2f, 2f, 2f, 2f};
//            table.setWidths(columnWidths);
//
//            PdfPCell name = new PdfPCell(new Paragraph("Nom Complet", tableHeader));
//            name.setBorderColor(BaseColor.BLACK);
//            name.setPaddingLeft(10);
//            name.setHorizontalAlignment(Element.ALIGN_CENTER);
//            name.setVerticalAlignment(Element.ALIGN_CENTER);
//            name.setBackgroundColor(BaseColor.GREEN);
//            name.setExtraParagraphSpace(5f);
//            table.addCell(name);
//
//            PdfPCell email = new PdfPCell(new Paragraph("matricule", tableHeader));
//            email.setBorderColor(BaseColor.BLACK);
//            email.setPaddingLeft(10);
//            email.setHorizontalAlignment(Element.ALIGN_CENTER);
//            email.setVerticalAlignment(Element.ALIGN_CENTER);
//            email.setBackgroundColor(BaseColor.GREEN);
//            email.setExtraParagraphSpace(5f);
//            table.addCell(email);
//
//            PdfPCell mobile = new PdfPCell(new Paragraph("effet", tableHeader));
//            mobile.setBorderColor(BaseColor.BLACK);
//            mobile.setPaddingLeft(10);
//            mobile.setHorizontalAlignment(Element.ALIGN_CENTER);
//            mobile.setVerticalAlignment(Element.ALIGN_CENTER);
//            mobile.setBackgroundColor(BaseColor.GREEN);
//            mobile.setExtraParagraphSpace(5f);
//            table.addCell(mobile);
//
//            PdfPCell address = new PdfPCell(new Paragraph("expiration", tableHeader));
//            address.setBorderColor(BaseColor.BLACK);
//            address.setPaddingLeft(10);
//            address.setHorizontalAlignment(Element.ALIGN_CENTER);
//            address.setVerticalAlignment(Element.ALIGN_CENTER);
//            address.setBackgroundColor(BaseColor.GREEN);
//            address.setExtraParagraphSpace(5f);
//            table.addCell(address);
//
//            for (WAFAIMA_Contrats affaire_wafaima : affaires_wafaima){
//                PdfPCell nameValue = new PdfPCell(new Paragraph(affaire_wafaima.getNom_client(), tableHeader));
//                nameValue.setBorderColor(BaseColor.BLACK);
//                nameValue.setPaddingLeft(10);
//                nameValue.setHorizontalAlignment(Element.ALIGN_CENTER);
//                nameValue.setVerticalAlignment(Element.ALIGN_CENTER);
//                nameValue.setBackgroundColor(BaseColor.WHITE);
//                nameValue.setExtraParagraphSpace(5f);
//                table.addCell(nameValue);
//
//                PdfPCell emailValue = new PdfPCell(new Paragraph(affaire_wafaima.getMatricule(), tableHeader));
//                emailValue.setBorderColor(BaseColor.BLACK);
//                emailValue.setPaddingLeft(10);
//                emailValue.setHorizontalAlignment(Element.ALIGN_CENTER);
//                emailValue.setVerticalAlignment(Element.ALIGN_CENTER);
//                emailValue.setBackgroundColor(BaseColor.WHITE);
//                emailValue.setExtraParagraphSpace(5f);
//                table.addCell(emailValue);
//
//                PdfPCell mobileValue = new PdfPCell(new Paragraph(affaire_wafaima.getDate_debut(), tableHeader));
//                mobileValue.setBorderColor(BaseColor.BLACK);
//                mobileValue.setPaddingLeft(10);
//                mobileValue.setHorizontalAlignment(Element.ALIGN_CENTER);
//                mobileValue.setVerticalAlignment(Element.ALIGN_CENTER);
//                mobileValue.setBackgroundColor(BaseColor.WHITE);
//                mobileValue.setExtraParagraphSpace(5f);
//                table.addCell(mobileValue);
//
//                PdfPCell addressValue = new PdfPCell(new Paragraph(affaire_wafaima.getDate_fin(), tableHeader));
//                addressValue.setBorderColor(BaseColor.BLACK);
//                addressValue.setPaddingLeft(10);
//                addressValue.setHorizontalAlignment(Element.ALIGN_CENTER);
//                addressValue.setVerticalAlignment(Element.ALIGN_CENTER);
//                addressValue.setBackgroundColor(BaseColor.WHITE);
//                addressValue.setExtraParagraphSpace(5f);
//                table.addCell(addressValue);
//            }
//
//            document.add(table);
//            document.close();
//            writer.close();
//            return true;
//
//
//        }catch (Exception e){
//        return false;
//        }
//    }
//
//    public boolean createExcell(List<Employer> employees, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
//
//        String filePath = context.getRealPath("/resources/report");
//        File file = new File(filePath);
//        boolean exists = new File(filePath).exists();
//        if (!exists){
//            new File(filePath).mkdirs();
//        }
//        try{
//            FileOutputStream outputStream = new FileOutputStream(file+"/"+"employees"+".xls");
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            HSSFSheet workSheet = workbook.createSheet("employees");
//            workSheet.setDefaultColumnWidth(30);
//
//            HSSFCellStyle headerCellStyle = workbook.createCellStyle();
//            headerCellStyle.setFillBackgroundColor(HSSFColor.BLUE.index);
////            headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//
//            HSSFRow headerRow = workSheet.createRow(0);
//
//            HSSFCell name = headerRow.createCell(0);
//            name.setCellStyle(headerCellStyle);
//
//            HSSFCell email = headerRow.createCell(1);
//            email.setCellStyle(headerCellStyle);
//
//            HSSFCell mobile = headerRow.createCell(2);
//            mobile.setCellStyle(headerCellStyle);
//
//            HSSFCell address = headerRow.createCell(3);
//            address.setCellStyle(headerCellStyle);
//
//            Integer i = 1;
//
//            for (Employer employee : employees){
//                HSSFRow bodyRow = workSheet.createRow(i);
//
//                HSSFCellStyle bodyCellStyle = workbook.createCellStyle();
//                bodyCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
//
//                HSSFCell nameValue = bodyRow.createCell(0);
//                nameValue.setCellValue(employee.getNom());
//                nameValue.setCellStyle(bodyCellStyle);
//
//                HSSFCell emailValue = bodyRow.createCell(1);
//                emailValue.setCellValue(employee.getDirection());
//                emailValue.setCellStyle(bodyCellStyle);
//
//                HSSFCell mobileValue = bodyRow.createCell(2);
//                mobileValue.setCellValue(employee.getService());
//                mobileValue.setCellStyle(bodyCellStyle);
//
//                HSSFCell addressValue = bodyRow.createCell(3);
//                addressValue.setCellValue(employee.getDirection());
//                addressValue.setCellStyle(bodyCellStyle);
//
//                i++;
//            }
//
//                workbook.write(outputStream);
//            outputStream.flush();
//            outputStream.close();
//            return true;
//
//        }catch (Exception e){
//            return false;
//        }
//    }
//}
