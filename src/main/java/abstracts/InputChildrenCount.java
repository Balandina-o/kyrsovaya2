package abstracts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Класс-наследник от абстрактного класса InputText, отвечающий за проверку введенных пользователем
 * значений в поле "число несовершеннолетних детей"
 * @author group2
 * @version 1.0
 */
public final class InputChildrenCount extends InputText {
    /**
     * Вызов конструктора родительского класса InputText
     * @param text - текст, введенный пользователем в проверяемое поле
     */
    public InputChildrenCount(String text) {
        super(text);
    }
    /**
     * Переопределенный абстрактный метод абстрактного класса InputText
     * реализующий в классе-наследнике проверку вводимых значений на соответствие допустимым.
     * Параметр вводимых допустимых значений задан с помощью регулярного выражения
     *
     * @return value - корректно введенное значение
     * @throws Exception - исключение, выбрасывающееся при некорректных введенных параметрах
     * Текстом которого являются, непосредственно требования к допустимым значениям поля
     */
    @Override
    protected double parse() throws Exception {
        Pattern pattern = Pattern.compile("\\d{1,2}");
        Matcher matcher = pattern.matcher(super.getText());

        if (!matcher.matches()) {
            throw new Exception("ведите число от 0 до 99");
        }

        return Double.parseDouble(super.getText());
    }
    /**
     * Получение имени данного текстового поля
     * @return the field name - возвращает имя текстового поля
     */
    @Override
    public String getFieldName() {
        return "Количество детей";
    }

}

