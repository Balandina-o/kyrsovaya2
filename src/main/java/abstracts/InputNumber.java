package abstracts;

/**
 * Класс-наследник от абстрактного класса InputText, отвечающий за проверку введенных пользователем
 * значений в поля "Кадастровая стоимость", "Налог от инвентаризационной стоимости", "Площадь налогооблагаемого объекта"
 * @author group2
 * @version 1.0
 */
public final class InputNumber extends InputText {
    /**
     * Вызов конструктора родительского класса InputText
     * @param name - имя проверяемого текстового поля
     * @param text - текст, введенный пользователем в проверяемое поле
     */
    public InputNumber(String name, String text) {
        super(name, text);
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

        if (value <= 0 ) {
            throw new Exception("значение не может быть меньше или равно нулю");
        }

        return value;
    }
}

