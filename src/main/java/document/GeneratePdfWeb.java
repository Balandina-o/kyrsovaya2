package document;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.stream.Stream;

import abstracts.RegionProperty;
import servlets.UtilServlets;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class GeneratePdfWeb implements GenerateChoiceDoc {
    private String cadastralValue, inventoryTax, square, portion, holdingPeriodRatio,
            childrenCount, exemption, result;

    private static String fullPath1 = "/fonts/times.ttf"; 
    private static String fullPath2 = "/picture/usatu.png";
    private String regionName;
    private String propertyName;
    private BaseFont times;
    
    public byte[] generate(String cadastralValue, String inventoryTax, String square,
                                  String portion, String holdingPeriodRatio, String childrenCount,
                                  String exemption, String result) {

        this.cadastralValue = cadastralValue;//кадастровая стоимость
        this.inventoryTax = inventoryTax;//инвентаризационный налог
        this.square = square;//площадь
        this.portion = portion;//доля в собственности
        this.holdingPeriodRatio = holdingPeriodRatio;//период владения
        this.childrenCount = childrenCount;//кол-во детей
        this.exemption = exemption;//льгота
        this.result = result;
        this.regionName = RegionProperty.getInstance().getRegionName();
        this.propertyName = RegionProperty.getInstance().getPropertyName();

        try {
        	
            Document document = new Document();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, stream);
            document.open();

            String string_pdf = "\n" + "\n" + "\n" + "\n" + "\n" + "            Расчет налога на "
            					+ "имущество для физических лиц";
            Paragraph paragraph = new Paragraph();
            paragraph.add(new Paragraph(string_pdf, new Font(getFont(),18)));

            String string_pdf2 = "В таблице 1, расположенной ниже, можно увидеть характеристики и "
            					  + "соответствующие вводимые параметры.";
            paragraph.add(new Paragraph(string_pdf2, new Font(getFont(),14)));

            String string_pdf3 = "\n" + "Таблица 1. Основные данные для вывода";
            paragraph.add(new Paragraph(string_pdf3, new Font(getFont(),14)));

            try {
                document.add(paragraph);
            } catch (DocumentException e1) {
                e1.printStackTrace();
            }

            paragraph.clear();
            String string_pdf4 = " ";
            paragraph.add(new Paragraph(string_pdf4, new Font(getFont(),14)));

            try {
                document.add(paragraph);
            } catch (DocumentException e1) {
                e1.printStackTrace();
            }

            try { document.add(getImage());
            } catch (DocumentException e) {
                e.printStackTrace();
            }

            PdfPTable table = new PdfPTable(2);
            addHeader(table);
            addRows(table);

            try {
                document.add(table);
            } catch (DocumentException e) {
                e.printStackTrace();
            }

            String string_pdf5 = "\n" + " Участники группы: Баландина Ольга, Гареева Диана, "
            					  + "Злыгостев Артем, Байбурин Марат.";
            paragraph.add(new Paragraph(string_pdf5, new Font(getFont(),14)));

            try {
                document.add(paragraph);
            } catch
            (DocumentException e1) {
                e1.printStackTrace();
            }

            document.close();
            return stream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public BaseFont getFont() {
        try {
            times = BaseFont.createFont(UtilServlets.PathToResPDF(fullPath1), "cp1251", BaseFont.EMBEDDED, true);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return times;
    }

    private Image getImage() {
        Image img = null;
        try {
            img = Image.getInstance(UtilServlets.PathToResPDF(fullPath2));

        } catch (BadElementException e2) {

            e2.printStackTrace(); } catch (MalformedURLException e2) {

            e2.printStackTrace(); } catch (IOException e2) {

            e2.printStackTrace();
        }

        img.setAbsolutePosition(430, 657);
        return img;
    }

    private void addRows(PdfPTable table) {
        String[] cell = {"Муниципальное образование: ", regionName,
                "Тип недвижимости: ", propertyName,
                "Кадастровая стоимость объекта: ", cadastralValue,
                "Налог от инвентар. стоимости: ", inventoryTax,
                "Площадь объекта: ", square,
                "Размер доли в праве: ", portion,
                "Период владения: ", holdingPeriodRatio,
                "Число несовершеннолетних детей: ", childrenCount,
                "Размер льготы: ", exemption,
                " ", " ",
                "Сумма к уплате: ", result + " руб.",
        };

        for (int i = 0; i < 22; i++) {
            table.addCell((new Phrase(cell [i], new Font(times,14))));
        }
    }

    private void addHeader(PdfPTable table) {
        Stream.of("Характеристики", " Вводимые параметры")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle, new Font(times,14)));
                    table.addCell(header);
                });
    }
}





