package abstracts;

/**
 * Класс в который из сервлета передаются значения ComboBox
 * Так же тут инициализируются во внутреннем классе deduction, evaporater, reductionFactor
 * /FIXME используется для:
 *
 * @see enums.EnumSwitch
 * @see abstracts.Validation
 */
public class RegionProperty {
    private int regionIndex = -10000, propertyIndex = -10000;
    private static final RegionProperty Instance = new RegionProperty();

    private RegionProperty() {
    }
    /**
     * Инициализация индекс Региона и параметров Deduction Evaporater зависящих от него.
     * Инициализация индекса Настроек и Параметра ReductionFactor зависящего от него.
     * @param regionIndex -  - индекса региона
     * @param propertyIndex - propertyIndex - индекс Настроек
     */
    public void setInitRegionPropertyIndex(int regionIndex, int propertyIndex){
        this.regionIndex = regionIndex;
        Dependencies.setReductionFactor(regionIndex);

        this.propertyIndex = propertyIndex;
        Dependencies.setDeductionEvaporater(propertyIndex);
    }

    public int getRegionIndex() {
        return regionIndex;
    }

    public int getPropertyIndex() {
        return propertyIndex;
    }

    public static RegionProperty getInstance() {
        return Instance;
    }

    public int getDeduction() {
        return Dependencies.deduction;
    }

    public int getEvaporater() {
        return Dependencies.evaporater;
    }

    public double getReductionFactor() {
        return Dependencies.reductionFactor;
    }

    /**
     * Вложенный класс TODO пока для удобства.
     * Здесь хранятся параметры зависящие от ComboBox
     */
    private static class Dependencies {
        private static int deduction = -5, evaporater = -5;
        private static double reductionFactor = -5;

        private static void setDeductionEvaporater(int propertyIndex) {
            int x = propertyIndex;
            x++;
            if (x < 3) {
                deduction = 10 * x;
                evaporater = 5;
            } else if (x == 3) {
                deduction = 50;
                evaporater = 7;
            } else {
                deduction = 0;
                evaporater = 0;
            }
        }

        private static void setReductionFactor(int regionIndex) {
            reductionFactor = (regionIndex == 40 ? 0.6 : 1.0);
        }
    }
}
