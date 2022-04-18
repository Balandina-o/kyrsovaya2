import UtilFiles.CipherText;
import UtilFiles.CryptLine;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тест для класса
 *
 * @author Artyom
 * @see CryptLine
 */
public class TestCryptLine {
    CipherText cryptLine;

    /**
     * Инициализация
     */
    @Before
    public void TestInitCryptLine() {
        cryptLine = new CryptLine();
    }

    /**
     * Тест Шифрования строки
     */
    @Test
    public void TestEncodingLine() {
        String x = "";
        try {
            x = cryptLine.encrypt("hello");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("KKIrf9ym8d0AdftWEQZUpw==", x);
    }

    /**
     * Тест расшифровки строки
     */
    @Test
    public void TestDecodingLine() {
        String x = "";
        try {
            x = cryptLine.decrypt("KKIrf9ym8d0AdftWEQZUpw==");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("hello", x);
    }
}
