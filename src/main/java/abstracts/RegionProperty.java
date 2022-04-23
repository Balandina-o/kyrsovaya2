package abstracts;

import UtilFiles.ClearRes;
import enums.CoffRegionAdmin;

/**
 * Класс в который из сервлета передаются значения ComboBox
 * Так же тут инициализируются во внутреннем классе deduction, evaporater, reductionFactor
 * /FIXME используется для:
 *
 * @see enums.EnumSwitch
 * @see abstracts.Validation
 * 
 * * @see document.GeneratePDFWeb
 */
public class RegionProperty implements ClearRes {
    private int regionIndex = -10000, propertyIndex = -10000;
    private static RegionProperty Instance = null;
    private double ADMIN_FACTOR = 1.0;
    private String regionName;
    private String PropertyName;
    private RegionProperty() {
    }

    /**
     * Инициализация индекс Региона и параметров Deduction Evaporater зависящих от него.
     * Инициализация индекса Настроек и Параметра ReductionFactor зависящего от него.
     *
     * @param regionIndex   -  - индекса региона
     * @param propertyIndex - propertyIndex - индекс Настроек
     */
    public void setInitRegionPropertyIndex(int regionIndex, int propertyIndex) {
        this.regionIndex = regionIndex;
        Dependencies.setReductionFactor(regionIndex);

        setAdminFactor_regionName(regionIndex);

        this.propertyIndex = propertyIndex;
       PropertyName = Dependencies.setDeductionEvaporater_propertyName(propertyIndex);
    }
    private void setAdminFactor_regionName(int regionIndex) {
        CoffRegionAdmin.FillFromFile();
        if (regionIndex == 10) {
            ADMIN_FACTOR = CoffRegionAdmin.UFA_COFF.getValue();
            regionName = "г. Уфа";
        } else if (regionIndex == 20) {
            ADMIN_FACTOR = CoffRegionAdmin.Kazan_COFF.getValue();
            regionName = "г. Казань";
        } else if (regionIndex == 30) {
            ADMIN_FACTOR = CoffRegionAdmin.Moscow_COFF.getValue();
            regionName = "г. Москва";
        } else if (regionIndex == 40) {
            ADMIN_FACTOR = CoffRegionAdmin.Gorn_COFF.getValue();
            regionName = "г. Горно-Алтайск";
        }
    }
    public String getRegionName() {
        return regionName;
    }

    public String getPropertyName() {
        return PropertyName;
    }

    public double getADMIN_FACTOR() {
        return ADMIN_FACTOR;
    }

    public int getRegionIndex() {
        return regionIndex;
    }

    public int getPropertyIndex() {
        return propertyIndex;
    }

    public static RegionProperty getInstance() {
        if (Instance == null) {
            Instance = new RegionProperty();
        }
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
     * Очистка экземпляра перед выходом
     */
    @Override
    public void clearEntity() {
        Instance = null;
    }

    /**
     * Вложенный класс TODO пока для удобства.
     * Здесь хранятся параметры зависящие от ComboBox
     */
    private static class Dependencies {
        private static int deduction = -5, evaporater = -5;
        private static double reductionFactor = -5;

        private static String setDeductionEvaporater_propertyName(int propertyIndex) {
            String namePr="иное здание / сооружение";
            deduction = 0;
            evaporater = 0;
            if (propertyIndex ==0){
                deduction = 10 ;
                evaporater = 5;
                namePr = "комната";
            }else if (propertyIndex ==1){
                deduction = 20 ;
                evaporater = 5;
                namePr = "квартира";
            }else if (propertyIndex ==2){
                deduction = 50;
                evaporater = 7;
                namePr = "жилой дом";
            }else if (propertyIndex ==3){
                namePr = "машино-место";
            }
            return namePr;
        }

        private static void setReductionFactor(int regionIndex) {
            reductionFactor = (regionIndex == 40 ? 0.6 : 1.0);
        }
    }
}
