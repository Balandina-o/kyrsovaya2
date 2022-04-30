package document;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import servlets.AccessResourcePath;

/**
 * Класс GeneratePdfWebTest - класс, тестирующий класс GeneratePdfWeb.
 *
 * @author разработчик 4
 * @version 1.0
 */

public class GeneratePdfWebTest {
    /** The full path 2. */
    private BaseFont times;
    private final String pathFont ="/fonts/times.ttf";

    @Before
    public final void setUp() throws DocumentException, IOException {
        String fullPath1 = AccessResourcePath.PATH_resources.getPath()+ pathFont;

        times = BaseFont.createFont(fullPath1, "cp1251", BaseFont.EMBEDDED, true);
    }

    /**
     * Тест, проверяющий, успешно ли отрабатывает метод создания шрифта .getFont() в GeneratePdfWeb:
     * не равляется ли он по итогу Null и совпадает ли получившийся в итоге шрифт с ожидаемым
     */
    @Test
    public void positiveFontCheck() {

        GeneratePdfWeb generPDF = new GeneratePdfWeb();
        byte[] document = generPDF.generate("100", "33", "20", "30", "0", "5", "10", "0.0");
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
        byte[] document = generPDF.generate("100", "33", "20", "30", "0", "5", "10", "0.0");

        assertTrue(document.length > 0);
    }

}