package abstracts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Класс-наследник от абстрактного класса InputText, отвечающий за проверку введенных пользователем
 * значений в поле "Коэффициент периода владения"
 * @author group2
 * @version 1.0
 */
public final class InputHoldingPeriodRatio extends InputText {
    /**
     * Вызов конструктора родительского класса InputText
     * @param text - текст, введенный пользователем в проверяемое поле
     */
    public InputHoldingPeriodRatio(String text) {
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
        Pattern pattern = Pattern.compile("1|2|3|4|5|6|7|8|9|10|11|12");
        Matcher matcher = pattern.matcher(super.getText());

        if (!matcher.matches()) {
            throw new Exception("введите от 1 до 12");
        }

        return Double.parseDouble(super.getText());
    }
    /**
     * Получение имени данного текстового поля
     * @return the field name - возвращает имя текстового поля
     */
    @Override
    public String getFieldName() {
        return "Период владения";
    }
}

