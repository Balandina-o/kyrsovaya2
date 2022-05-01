package document;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import abstracts.RegionProperty;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import servlets.UtilServlets;

public class GenerateDocWeb {

	private static String fullPath2 = "/picture/usatu.png";

	public byte[] generate(String cadastralValue, String inventoryTax, String square,
			String portion, String holdingPeriodRatio, String childrenCount,
			String exemption, String result) {
		byte[] DOCX ;
		try {
			XWPFDocument document = new XWPFDocument();
			
			XWPFParagraph image = document.createParagraph();
			image.setAlignment(ParagraphAlignment.RIGHT);
			
			XWPFRun imageRun = image.createRun();
			imageRun.setTextPosition(20);

			Path imagePath = Paths.get(UtilServlets.PathToResPDF(fullPath2));
			imageRun.addPicture(Files.newInputStream(imagePath),
			  XWPFDocument.PICTURE_TYPE_PNG, imagePath.getFileName().toString(),
			  Units.toEMU(160), Units.toEMU(160));
			
			XWPFParagraph title = document.createParagraph();
			title.setAlignment(ParagraphAlignment.CENTER);
				
			XWPFRun titleRun = title.createRun();
			titleRun.setText("Расчет налога на имущество для физических лиц");
			titleRun.setFontFamily("Times New Roman");
			titleRun.setFontSize(20);
			
			XWPFParagraph paragraph1 = document.createParagraph();
			XWPFRun run1 = paragraph1.createRun();
			run1.setFontFamily("Times New Roman");
			run1.setFontSize(14);
			run1.setText("В таблице 1, расположенной ниже, можно увидеть характеристики и "
					  + "соответствующие вводимые параметры.");

			XWPFParagraph emptyParagraph1 = document.createParagraph();
			XWPFRun run2 = emptyParagraph1.createRun();
			run2.setFontSize(14);
			run2.setText(" ");

			XWPFParagraph tableTitleParagraph = document.createParagraph();
			XWPFRun run3 = tableTitleParagraph.createRun();
			run3.setFontFamily("Times New Roman");
			run3.setFontSize(14);
			run3.setText("Таблица 1. Основные данные для вывода");
		
			XWPFTable table = document.createTable();
			table.setWidth(1000);
			

			XWPFTableRow tableRowOne = table.getRow(0);
	        tableRowOne.getCell(0).setText("Муниципальное образование: ");
	        tableRowOne.addNewTableCell().setText(RegionProperty.getInstance().getRegionName());
		    
	        XWPFParagraph addParagraph = tableRowOne.getCell(0).addParagraph();
	        XWPFRun run = addParagraph.createRun();
	        run.setFontFamily("Times New Roman");
	        run.setFontSize(14);

		    XWPFTableRow tableRowTwo = table.createRow();
		    tableRowTwo.getCell(0).setText("Тип недвижимости: ");
		    tableRowTwo.getCell(1).setText(RegionProperty.getInstance().getPropertyName());

		    XWPFTableRow tableRowThree = table.createRow();
		    tableRowThree.getCell(0).setText("Кадастровая стоимость объекта: ");
		    tableRowThree.getCell(1).setText(cadastralValue);

		    XWPFTableRow tableRowFour = table.createRow();
		    tableRowFour.getCell(0).setText("Налог от инвентар. стоимости: ");
		    tableRowFour.getCell(1).setText(inventoryTax);

		    XWPFTableRow tableRowFive = table.createRow();
		    tableRowFive.getCell(0).setText( "Площадь объекта: ");
		    tableRowFive.getCell(1).setText(square);

		    XWPFTableRow tableRowSix = table.createRow();
		    tableRowSix.getCell(0).setText("Размер доли в праве: ");
		    tableRowSix.getCell(1).setText(portion);

		    XWPFTableRow tableRowSeven = table.createRow();
		    tableRowSeven.getCell(0).setText("Период владения: ");
		    tableRowSeven.getCell(1).setText(holdingPeriodRatio);

		    XWPFTableRow tableRowEight = table.createRow();
		    tableRowEight.getCell(0).setText("Число несовершеннолетних детей: ");
		    tableRowEight.getCell(1).setText(childrenCount);

		    XWPFTableRow tableRowNine = table.createRow();
		    tableRowNine.getCell(0).setText("Размер льготы: ");
		    tableRowNine.getCell(1).setText(exemption);

		    XWPFTableRow tableRowTen = table.createRow();
		    tableRowTen.getCell(0).setText(" ");
		    tableRowTen.getCell(1).setText(" ");

		    XWPFTableRow tableRowEleven = table.createRow();
		    tableRowEleven.getCell(0).setText("Сумма к уплате: ");
		    tableRowEleven.getCell(1).setText(result);

		    XWPFParagraph emptyParagraph2 = document.createParagraph();
			XWPFRun run4 = emptyParagraph2.createRun();
			run4.setFontSize(14);
			run4.setText(" ");

			XWPFParagraph finalParagraph = document.createParagraph();
			XWPFRun run5 = finalParagraph.createRun();
			run5.setFontFamily("Times New Roman");
			run5.setFontSize(14);
			run5.setText(" Участники группы: Баландина Ольга, Гареева Диана, "
					  + "Злыгостев Артем, Байбурин Марат.");
			
		    try(ByteArrayOutputStream out = new ByteArrayOutputStream()) {
				document.write(out);
				//document.close(); // байтовый поток по цепочке закроет документ.
				DOCX = out.toByteArray();
			}
		}catch (Exception e) {
				e.printStackTrace();
				DOCX= new byte[0];
			}
		return  DOCX;
	}
}