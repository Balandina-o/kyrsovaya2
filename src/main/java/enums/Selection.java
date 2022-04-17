package enums;

/**
 * Класс-интерфейс, задействованный в классе EnumSwitch
 *
 * @author group2
 * @version 1.0
 */
@FunctionalInterface
public interface Selection {
    /**
     * Метод интерфейса, реализованный в классе EnumSwitch
     *
     * @return the double - возвращает числовое значение ставки
     */
    double changeFinalBid();

}
