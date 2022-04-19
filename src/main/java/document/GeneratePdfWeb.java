package document;

import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.stream.Stream;

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

public class GeneratePdfWeb {
    private static String cadastralValue;
    private static String inventoryTax;
    private static String square;
    private static String portion;
    private static String holdingPeriodRatio;
    private static String childrenCount;
    private static String exemption;
    private static String deduction;
    private static String reductionFactor;
    private static String evaporater;
    private static int regionIndex, propertyIndex;

    private static BaseFont times = null;

    public static byte[] generate(String cadastralValue, String inventoryTax, String square,
                                  String portion, String holdingPeriodRatio, String childrenCount,
                                  String exemption, int region, int property) {

        GeneratePdfWeb.cadastralValue = cadastralValue;//кадастровая стоимость
        GeneratePdfWeb.inventoryTax = inventoryTax;//инвентаризационный налог
        GeneratePdfWeb.square = square;//площадь
        GeneratePdfWeb.portion = portion;//доля в собственности
        GeneratePdfWeb.holdingPeriodRatio = holdingPeriodRatio;//период владения
        GeneratePdfWeb.childrenCount = childrenCount;//кол-во детей
        GeneratePdfWeb.exemption = exemption;//льгота
        GeneratePdfWeb.regionIndex = region; //коэфф. типа недвижимости (вычет из площади в зависимости от типа недвижимости)
        GeneratePdfWeb.propertyIndex = property; //вычет за ребенка (после 4-го ребенка включительно)

        try {
            Document document = new Document();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, stream);
//document.open();


            String filepath = new File("").getCanonicalPath();

            String[] parsfilepath = filepath.split("/");
            int lengthpath = parsfilepath.length;
            String abspath="";
            for(int i=0;i<(lengthpath-1);i++) {
                abspath=abspath+parsfilepath[i]+"/";
            }

            abspath = abspath.replaceAll("Desktop", "git");


            filepath=abspath+"/kyrsovaya2/src/main/webapp/Check.pdf";

            String imagepath=abspath+"/kyrsovaya2/src/main/webapp/picture/ugatu.png";

            String fontpath =abspath+"/kyrsovaya2/src/main/webapp/fonts/times.ttf";

            document.open();


            try {
                times = BaseFont.createFont(fontpath, "cp1251", BaseFont.EMBEDDED, true);
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }

            String string_pdf = "Информация по генерации PDF документа.";
            Paragraph paragraph = new Paragraph();
            paragraph.add(new Paragraph(string_pdf, new Font(times,14)));

            String string_pdf2 = "Все работает как надо! Используйте в своих курсовых работах!";
            paragraph.add(new Paragraph(string_pdf2, new Font(times,14)));

            try {
                document.add(paragraph);
            } catch (DocumentException e1) {
                e1.printStackTrace();
            }

            paragraph.clear();
            String string_pdf3 = " ";
            paragraph.add(new Paragraph(string_pdf3, new Font(times,14)));

            try {
                document.add(paragraph);
            } catch (DocumentException e1) {
                e1.printStackTrace();
            }

            Image img = null;

            try { img = Image.getInstance(imagepath);

            } catch (BadElementException e2) {

                e2.printStackTrace(); } catch (MalformedURLException e2) {

                e2.printStackTrace(); } catch (IOException e2) {

                e2.printStackTrace(); }

            img.setAbsolutePosition(90, 500);

            try { document.add(img); } catch (DocumentException e) { e.printStackTrace();
            }

            paragraph.clear();
            paragraph.add(new Paragraph(string_pdf3, new Font(times,14)));

            try {
                document.add(paragraph);
            } catch
            (DocumentException e1) {
                e1.printStackTrace();
            }

            PdfPTable table = new PdfPTable(4);
            addHeader(table);
            addRows(table);

            try {
                document.add(table);
            } catch (DocumentException e) {
                e.printStackTrace();
            }

            document.close();
            return stream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    private static void addRows(PdfPTable table) {

        String cell1 = cadastralValue;
        String cell2 = inventoryTax;
        String cell3 = square;
        String cell4 = childrenCount;

        table.addCell((new Phrase(cell1, new Font(times,14))));
        table.addCell((new Phrase(cell2, new Font(times,14))));
        table.addCell((new Phrase(cell3, new Font(times,14))));
        table.addCell((new Phrase(cell4, new Font(times,14))));

    }

    private static void addHeader(PdfPTable table) {
        Stream.of("Номер", "Группа", "ФИО", "Оценка", "Prevelno?")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle, new Font(times,14)));
                    table.addCell(header);
                });
    }

}
