package abstracts;

/**
 * Класс-наследник от абстрактного класса InputText, отвечающий за проверку введенных пользователем
 * значений в поле "размер льготы"
 * @author group2
 * @version 1.0
 */
public final class InputExemption extends InputText {
    /**
     * Вызов конструктора родительского класса InputText
     * @param text - текст, введенный пользователем в проверяемое поле
     */
    public InputExemption(String text) {
        super(text);
    }
    /**
     * Переопределенный абстрактный метод абстрактного класса InputText
     * реализующий в классе-наследнике проверку вводимых значений на соответствие допустимым.
     *
     * @return value - корректно введенное значение
     * @throws Exception - исключение, выбрасывающееся при некорректных введенных параметрах
     * Текстом которого являются, непосредственно требования к допустимым значениям поля
     */
    @Override
    protected double parse() throws Exception {
        double value;
        try {
            value = Double.parseDouble(super.getText());
        } catch (Exception error) {
            throw new Exception("введите вещественное число");
        }

        
        if (value < 0 ) {
            throw new Exception("значение не может быть меньше нуля");
        }

        if (value > 100) {
            throw new Exception("значение не может быть больше 100");
        }

        return value;
    }
    /**
     * Получение имени данного текстового поля
     * @return the field name - возвращает имя текстового поля
     */
    @Override
    public String getFieldName() {
        return "Льгота";
    }
}
