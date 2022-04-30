package document;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Класс GenerateDocWebTest - класс, тестирующий класс GenerateDocWeb.
 *
 * @author balandina-o
 * @version 1.0
 */

public class GenerateDocWebTest {

    /**
     * Тест, проверяющий, успешно ли отрабатывает метод создания документа .generate(...) в GenerateDocWeb
     * Создается ли в итоге документ
     */
	  @Test
	    public void positiveDocumentCheck() {
	        GenerateDocWeb generDoc = new GenerateDocWeb();
	        byte[] document = generDoc.generate("1234", "123", "123", "12", "1", "1", "2", "0.0");

	        assertTrue(document.length > 0);
	    }
}
