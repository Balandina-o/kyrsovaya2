package abstracts;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Класс-наследник от абстрактного класса InputText, отвечающий за проверку введенных пользователем
 * значений в поле "Коэффициент периода владения"
 * @author group2
 * @version 1.0
 */
public final class InputPortion extends InputText {
    /**
     * Вызов конструктора родительского класса InputText
     * @param text - текст, введенный пользователем в проверяемое поле
     */
    public InputPortion(String text) {
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
        final Pattern pattern = Pattern.compile("(1[//]\\d{1,4})|(1)"); //размер доли (1/2 или 1)
        final Matcher matcher = pattern.matcher(super.getText());


        if (!matcher.matches()) {
            throw new Exception("введите корректную обыкновенную дробь или 1");
        }

        double portion = super.getText().equals("1") ? Double.parseDouble(super.getText()) : Double.parseDouble((super.getText().substring(2)));

        if (portion == 0) {
            throw new Exception("введите корректную обыкновенную дробь или 1");
        } else {
            return portion;
        }
    }
    /**
     * Получение имени данного текстового поля
     * @return the field name - возвращает имя текстового поля
     */
    @Override
    public String getFieldName() {
        return "Размер доли";
    }

}
