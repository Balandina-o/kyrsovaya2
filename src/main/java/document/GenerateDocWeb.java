package document;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import abstracts.RegionProperty;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import servlets.UtilServlets;

public class GenerateDocWeb implements GenerateChoiceDoc {

	private static final String fullPath2 = "/picture/usatu.png";
	private static final String FONT_TIMES_NEW_ROMAN = "Times New Roman";
	private static final String FONT_ARIAL = "Arial";
	private static final int SIZE_FONT_12 = 12;
	private static final int SIZE_FONT_14 = 14;
	private static final int SIZE_FONT_20 = 20;
	@Override
	public byte[] generate(String cadastralValue, String inventoryTax, String square,
			String portion, String holdingPeriodRatio, String childrenCount,
			String exemption, String result) {

		String RegName = RegionProperty.getInstance().getRegionName();
		String PropName = RegionProperty.getInstance().getPropertyName();

		ArrayList<String> inParam = new ArrayList<>(List.of(
				"Вводимые параметры", RegName, PropName,
				cadastralValue, inventoryTax, square, portion,
				holdingPeriodRatio, childrenCount, exemption, result + " руб."));

		byte[] DOCX ;

		try {
			XWPFDocument document = new XWPFDocument();
			createImage(document);
			XWPFParagraph title = document.createParagraph();
			title.setAlignment(ParagraphAlignment.CENTER);

			//TODO setText_Font этот метод для установки в run
			XWPFRun titleRun = title.createRun();
			String titleText ="Расчет налога на имущество для физических лиц";
			setText_FontSize_Font_Bold(titleRun, titleText,SIZE_FONT_20,FONT_TIMES_NEW_ROMAN,false);

			titleRun.addBreak();

			XWPFParagraph paragraph1 = document.createParagraph();
			XWPFRun run1 = paragraph1.createRun();
			paragraph1.setAlignment(ParagraphAlignment.LEFT);
			String par1Text="В таблице 1, расположенной ниже, можно увидеть характеристики и " +
					"соответствующие вводимые параметры.";
			setText_FontSize_Font_Bold(run1, par1Text,SIZE_FONT_14,FONT_TIMES_NEW_ROMAN,false);

			run1.addCarriageReturn(); //разрыв страницы вместо "\r или \n"
			run1.addBreak();
			String tableText ="Таблица 1. Основные данные для вывода";
			setText_FontSize_Font_Bold(run1, tableText,SIZE_FONT_14,FONT_TIMES_NEW_ROMAN,false);

			//Таблица - создание - заполнение
			XWPFTable table = document.createTable(11, 2);
			table.setTableAlignment(TableRowAlign.LEFT);
			addTable(inParam,table);


			XWPFParagraph finalParagraph = document.createParagraph();
			finalParagraph.setSpacingBefore(200);
			XWPFRun run5 = finalParagraph.createRun();
			String memberText =" Участники группы: Баландина Ольга, Гареева Диана, "
					  + "Злыгостев Артем, Байбурин Марат.";
			setText_FontSize_Font_Bold(run5, memberText,SIZE_FONT_14,FONT_TIMES_NEW_ROMAN,false);
			
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

	/**
	 *  Метод для добавления таблицы в документ
	 * @param inArgs - массив аргументов введенных пользователем
	 * @param table - таблица для заполнения
	 */
	public void addTable(ArrayList<String> inArgs,XWPFTable table) {
		//Массив для 1‑го столбца
		ArrayList<String> Characteristics = new ArrayList<>(List.of("Характеристики", "Муниципальное образование: ",
				"Тип недвижимости: ", "Кадастровая стоимость объекта: ",
				"Налог от инвентар. стоимости: ", "Площадь объекта: ",
				"Размер доли в праве: ", "Период владения: ",
				"Число несовершеннолетних детей: ", "Размер льготы: ", "Сумма к уплате: "));
		//Размер столбцов
		table.getRow(0).getCell(1).setWidth("4000");
		table.getRow(0).getCell(0).setWidth("4200");
		//Цвет ячеек Титульника таблицы - 16xx
		table.getRow(0).getCell(0).setColor("d3d3d3");
		table.getRow(0).getCell(1).setColor("d3d3d3");
		//Цвет ячеек результата - 16xx
		table.getRow(10).getCell(0).setColor("ffe599");
		table.getRow(10).getCell(1).setColor("ffe599");
		// Цикл по первому столбцу
		fillColumnData(table, Characteristics, 0);
		// Цикл по второму столбцу
		fillColumnData(table, inArgs, 1);
	}
	/**
	 * Метод для заполнения переданной таблицы определенного столбца в документе
	 * @param table - таблица для заполнения
	 * @param arg - аргументы которые нужно вставить в таблицу столбца
	 * @param columnNumber - номер столбца для заполнения
	 */
	public  static void fillColumnData(XWPFTable table, ArrayList<String> arg, int columnNumber) {
		for (int i = 0; i < 11; i++) {
			//удаляет знак параграфа
			table.getRow(i).getCell(columnNumber).removeParagraph(0);
			// Строка i - столбец 1. Создание параграфа, выравнивание по центру.
			var paragraph =table.getRow(i).getCell(columnNumber).addParagraph();
			paragraph.setAlignment(ParagraphAlignment.CENTER);
//             установка текста/шрифта/размера в ячейку ИЗ переданных параметров
			var run = paragraph.createRun();
			int titleFontSize = i == 0 ? SIZE_FONT_14 : SIZE_FONT_12;
			setText_FontSize_Font_Bold(run, arg.get(i),titleFontSize,FONT_ARIAL,i == 10);
		}
	}

	/**
	 * Установка Текста и тд......
	 * @param run - вывод текста внутри параграфа
	 * @param text -  Текст  внутри параграфа
	 * @param fontSize - размер текста
	 * @param Font - Шрифт текста
	 * @param Bold - полужирный или простой
	 */
	public static void setText_FontSize_Font_Bold(XWPFRun run,String text,int fontSize,String Font,boolean Bold){
		run.setText(text);
		run.setFontSize(fontSize);
		run.setFontFamily(Font);
		run.setBold(Bold);
	}

	// метод для создания изображения
	public void createImage(XWPFDocument document) throws InvalidFormatException, IOException {
		XWPFParagraph image = document.createParagraph();
		image.setAlignment(ParagraphAlignment.RIGHT);
		XWPFRun imageRun = image.createRun();
		imageRun.setTextPosition(20);

		Path imagePath = Paths.get(UtilServlets.PathToResPDF(fullPath2));
		imageRun.addPicture(Files.newInputStream(imagePath),
		  XWPFDocument.PICTURE_TYPE_PNG, imagePath.getFileName().toString(),
		  Units.toEMU(160), Units.toEMU(160));
	
	}
}