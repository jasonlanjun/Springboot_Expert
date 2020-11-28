package com.junlan.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.junlan.domain.BillEntity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class GeneratePDFBill {

    public static ByteArrayInputStream billPrinting(List<BillEntity> itemlist, String customerName)
            throws IOException {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int countNumber = 0;
        double totalAmount = 0.0;

        try {

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            titleFont.setSize(28);
            Paragraph title = new Paragraph("INVOICE", titleFont);

            Font addressFont = FontFactory.getFont(FontFactory.HELVETICA);
            addressFont.setSize(9);
            Paragraph address = new Paragraph(
                    "Johnson Controls Inc., 4th Floor Building No-2" + System.lineSeparator()
                            + "CommerZone IT Park, Yerwada, Pune-411006" + System.lineSeparator()
                            + "Phone: 020 6747 5943" + System.lineSeparator() + "Email: anil.vishvkarma@jci.com",
                    addressFont);

            Font billingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            billingFont.setSize(9);

            Chunk billAlign = new Chunk(new VerticalPositionMark());
            Paragraph billingDetails = new Paragraph("INVOICE NUMBER: " + itemlist.get(0).getBillNumber(), billingFont);
            billingDetails.add(new Chunk(billAlign));
            Chunk billingDate = new Chunk("INVOICE DATE: " + itemlist.get(0).getBillDate(), billingFont);
            billingDetails.add(billingDate);

            Paragraph customerDetails = new Paragraph("CUSTOMER NAME: " + customerName, billingFont);
            customerDetails.add(new Chunk(billAlign));
            Chunk customerMobile = new Chunk("CUSTOMER MOBILE: " + itemlist.get(0).getCustomerMobile(), billingFont);
            customerDetails.add(customerMobile);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{1, 4, 3, 3, 3});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            headFont.setSize(10);
            PdfPCell hcell;

            hcell = new PdfPCell(new Phrase("NO", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            hcell.setFixedHeight(26);
            hcell.setBorder(0);
            hcell.setBorderWidthTop(1);
            hcell.setBorderWidthBottom(1);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("DESCIPTIONS", headFont));
            hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            hcell.setFixedHeight(26);
            hcell.setBorder(0);
            hcell.setBorderWidthTop(1);
            hcell.setBorderWidthBottom(1);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("QUNATITY", headFont));
            hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setFixedHeight(26);
            hcell.setBorder(0);
            hcell.setBorderWidthTop(1);
            hcell.setBorderWidthBottom(1);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("UNIT PRICE", headFont));
            hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            hcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            hcell.setFixedHeight(26);
            hcell.setBorder(0);
            hcell.setBorderWidthTop(1);
            hcell.setBorderWidthBottom(1);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("AMOUNT", headFont));
            hcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            hcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            hcell.setFixedHeight(26);
            hcell.setBorder(0);
            hcell.setBorderWidthTop(1);
            hcell.setBorderWidthBottom(1);
            table.addCell(hcell);

            for (BillEntity oneItem : itemlist) {

                PdfPCell cell;
                countNumber = countNumber + 1;
                totalAmount = totalAmount + oneItem.getNetPrice();

                Font cellFont = FontFactory.getFont(FontFactory.HELVETICA);
                cellFont.setSize(10);

                cell = new PdfPCell(new Phrase(new Integer(countNumber).toString(), cellFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setFixedHeight(28);
                cell.setBorder(0);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(oneItem.getProduct(), cellFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setFixedHeight(28);
                cell.setBorder(0);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(new Integer(oneItem.getQuantity()).toString(), cellFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setFixedHeight(28);
                cell.setBorder(0);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(new Double(oneItem.getSellingPrice()).toString(), cellFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setFixedHeight(28);
                cell.setBorder(0);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(new Double(oneItem.getNetPrice()).toString(), cellFont));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setFixedHeight(28);
                cell.setBorder(0);
                table.addCell(cell);
            }

            headFont.setSize(10);
            PdfPCell fcell;

            fcell = new PdfPCell(new Phrase());
            fcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            fcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            fcell.setFixedHeight(26);
            fcell.setBorder(0);
            fcell.setBorderWidthTop(1);
            fcell.setBorderWidthBottom(1);
            table.addCell(fcell);

            fcell = new PdfPCell(new Phrase());
            fcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            fcell.setFixedHeight(26);
            fcell.setBorder(0);
            fcell.setBorderWidthTop(1);
            fcell.setBorderWidthBottom(1);
            table.addCell(fcell);

            fcell = new PdfPCell(new Phrase());
            fcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            fcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            fcell.setFixedHeight(26);
            fcell.setBorder(0);
            fcell.setBorderWidthTop(1);
            fcell.setBorderWidthBottom(1);
            table.addCell(fcell);

            fcell = new PdfPCell(new Phrase("SUB TOTAL", headFont));
            fcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            fcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            fcell.setFixedHeight(26);
            fcell.setBorder(0);
            fcell.setBorderWidthTop(1);
            fcell.setBorderWidthBottom(1);
            table.addCell(fcell);

            fcell = new PdfPCell(new Phrase(new Double(totalAmount).toString(), headFont));
            fcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            fcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            fcell.setFixedHeight(26);
            fcell.setBorder(0);
            fcell.setBorderWidthTop(1);
            fcell.setBorderWidthBottom(1);
            table.addCell(fcell);

            Paragraph seal = new Paragraph("For - Retail Management System", addressFont);
            seal.setAlignment(Element.ALIGN_RIGHT);

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(title);
            document.add(Chunk.NEWLINE);
            document.add(address);
            document.add(Chunk.NEWLINE);
            document.add(billingDetails);
            document.add(customerDetails);
            document.add(Chunk.NEWLINE);
            document.add(table);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(seal);
            document.close();

        } catch (DocumentException ex) {
            System.out.println("Error Occrued to create PDF Report" + ex.getMessage());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}