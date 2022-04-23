package document;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;

/**
 * Класс GeneratePdfWebTest - класс, тестирующий класс GeneratePdfWeb.
 *
 * @author разработчик 4
 * @version 1.0
 */

public class GeneratePdfWebTest {
    /** The full path 2. */
    private static BaseFont times;
    private static String fullPath1, fullPath2;

    @Before
    public final void setUp() throws DocumentException, IOException {
        this.fullPath1 = "./src/main/webapp/resources/fonts/times.ttf";
        this.fullPath2 = "./src/main/webapp/resources/picture/usatu.png";

        times = BaseFont.createFont(fullPath1, "cp1251", BaseFont.EMBEDDED, true);

    }

    /**
     * Тест, проверяющий, успешно ли отрабатывает метод создания шрифта .getFont() в GeneratePdfWeb:
     * не равляется ли он по итогу Null и совпадает ли получившийся в итоге шрифт с ожидаемым
     */
    @Test
    public void positiveFontCheck() {

        GeneratePdfWeb generPDF = new GeneratePdfWeb();
        @SuppressWarnings("static-access")
        byte[] document = generPDF.generate("100", "33", "20", "30", "0", "5", "10", "0.0", fullPath1,fullPath2);
        BaseFont fontTest = generPDF.getFont();

        assertNotNull(fontTest);
        assertTrue(fontTest == times);
    }

    /**
     * Тест, проверяющий, успешно ли отрабатывает метод создания документа .generate(...) в GeneratePdfWeb
     * Создается ли в итоге документ
     */
    @Test
    public void positiveDocumentCheck() {
        GeneratePdfWeb generPDF = new GeneratePdfWeb();
        byte[] document = generPDF.generate("100", "33", "20", "30", "0", "5", "10", "0.0", fullPath1,fullPath2);

        assertTrue(document.length > 0);
    }

}