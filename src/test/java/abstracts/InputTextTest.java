package abstracts;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Класс InputTextTest - класс, тестирующий класс InputText
 * @author разработчик 1
 * @version 1.0
 */
public class InputTextTest {
	/**
	 * Тест, проверяющий является ли заявленный класс наследником абстрактного
	 */
	@Test
	public void positiveAbstractCheck() {
		InputError in2 = new InputError("Площадь", "10000");
		InputNumber in = new InputNumber("Кадастровая стоимость", "4000000");

		assertTrue(InputText.class.isAssignableFrom(in.getClass()));
	}
}
