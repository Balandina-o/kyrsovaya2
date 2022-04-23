package document;

import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.stream.Stream;

import abstracts.RegionProperty;
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

import servlets.AccessResourcePath;

public class GeneratePdfWeb {
    private static String cadastralValue, inventoryTax, square, portion, holdingPeriodRatio,
            childrenCount, exemption, result;

    private static String fullPath1, fullPath2, regionName, propertyName;
    private static BaseFont times;
    public static byte[] generate(String cadastralValue, String inventoryTax, String square,
                                  String portion, String holdingPeriodRatio, String childrenCount,
                                  String exemption, String result, String fullPath1, String fullPath2) {

        GeneratePdfWeb.cadastralValue = cadastralValue;//кадастровая стоимость
        GeneratePdfWeb.inventoryTax = inventoryTax;//инвентаризационный налог
        GeneratePdfWeb.square = square;//площадь
        GeneratePdfWeb.portion = portion;//доля в собственности
        GeneratePdfWeb.holdingPeriodRatio = holdingPeriodRatio;//период владения
        GeneratePdfWeb.childrenCount = childrenCount;//кол-во детей
        GeneratePdfWeb.exemption = exemption;//льгота
        GeneratePdfWeb.result = result;

        GeneratePdfWeb.fullPath1 = fullPath1;
        GeneratePdfWeb.fullPath2 = fullPath2;

        try {
            Document document = new Document();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, stream);

            document.open();

            String string_pdf = "\n" + "\n" + "\n" + " Расчет налога на имущество для физических лиц";
            Paragraph paragraph = new Paragraph();
            paragraph.add(new Paragraph(string_pdf, new Font(GeneratePdfWeb.getFont(),18)));

            String string_pdf2 = "В таблице 1, расположенной ниже, можно увидеть характеристики и соответствующие вводимые параметры.";
            paragraph.add(new Paragraph(string_pdf2, new Font(GeneratePdfWeb.getFont(),14)));


            String string_pdf3 = "\n" + " Таблица 1. Основные данные для вывода";
            paragraph.add(new Paragraph(string_pdf3, new Font(GeneratePdfWeb.getFont(),14)));

            try {
                document.add(paragraph);
            } catch (DocumentException e1) {
                e1.printStackTrace();
            }

            paragraph.clear();
            String string_pdf4 = " ";
            paragraph.add(new Paragraph(string_pdf4, new Font(GeneratePdfWeb.getFont(),14)));

            try {
                document.add(paragraph);
            } catch (DocumentException e1) {
                e1.printStackTrace();
            }

            try { document.add(GeneratePdfWeb.getImage());
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


            String string_pdf5 = "\n" + " Участники группы: Баландина Ольга, Гареева Диана, Злыгостев Артем, Байбурин Марат.";
            paragraph.add(new Paragraph(string_pdf5, new Font(GeneratePdfWeb.getFont(),14)));

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

    static BaseFont getFont() {
        try {
            times = BaseFont.createFont(fullPath1, "cp1251", BaseFont.EMBEDDED, true);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }

        return times;
    }

    static Image getImage() {

        Image img = null;
        try {
            img = Image.getInstance(fullPath2);

        } catch (BadElementException e2) {

            e2.printStackTrace(); } catch (MalformedURLException e2) {

            e2.printStackTrace(); } catch (IOException e2) {

            e2.printStackTrace();
        }

        img.setAbsolutePosition(430, 724);

        return img;

    }

    private static void addRows(PdfPTable table) {
        String[] cell = {"Муниципальное образование: ", GeneratePdfWeb.getRegionPDF(),
                "Тип недвижимости: ", GeneratePdfWeb.getPropertyPDF(),
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

    private static void addHeader(PdfPTable table) {
        Stream.of("Характеристики", " Вводимые параметры")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle, new Font(times,14)));
                    table.addCell(header);
                });
    }

    private static String getRegionPDF() {
        int indexReg = RegionProperty.getInstance().getRegionIndex();

        switch (indexReg) {
            case (10):
                regionName = "г. Уфа";
                break;
            case (20):
                regionName = "г. Казань";
                break;
            case (30):
                regionName = "г. Москва";
                break;
            case (40):
                regionName = "г. Горно-Алтайск";
                break;
        }

        return regionName;

    }

    private static String getPropertyPDF() {
        int propReg = RegionProperty.getInstance().getPropertyIndex();

        switch (propReg) {
            case (0):
                propertyName = "комната";
                break;
            case (1):
                propertyName = "квартира";
                break;
            case (2):
                propertyName = "жилой дом";
                break;
            case (3):
                propertyName = "машино-место";
                break;
            case (4):
                propertyName = "иное здание / сооружение";
                break;
        }

        return propertyName;

    }
}





