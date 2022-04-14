package abstr;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


/**
 * Класс, в котором создаются экземпряры классов-наследников абстрактного, а корректные значения, полученные из их методов
 * записываются в числовые переменные и далее участвуют в расчетах
 * @author group2
 * @version 1.0
 */
public final class TaxAmount{

    /** Объявление выпадающего списка, позволяющего выбрать муниципальное образование*/
    private static ComboBox<String> combo_region;

    /** Объявление выпадающего списка, позволяющего выбрать тип налогооблагаемого имущества*/
    private static ComboBox<String> combo_property;

    /** Объявление переменной хранящей коэффициент, завиящий от типа налогооблагаемого имущества*/
    private static int evaporater;

    /** Объявление строковых переменных, хранящих введенные пользователем на старте программы значения */
    private static String cadastralValueText, inventoryTaxText, squareText, portionText, holdingPeriodRatioText, childrenCountText, exemptionText;

    /** Объявление числовых переменных, в которые записываются значения, прошедшие проверку на корректность*/
    private static double cadastralValue, inventoryTax, square, portion, holdingPeriodRatio, childrenCount, reductionFactor, deduction, exemption;

    /**
     * Присваивание значений переданных в конструкторе местным переменным
     *
     * @param cadastralValueText - значение введенное пользователем в поле "Кадастровая стоимость"
     * @param inventoryTaxText - значение введенное пользователем в поле "налог от инвентаризационной стоимости"
     * @param squareText - значение введенное пользователем в поле "Площадь налогооблагаемого объекта"
     * @param portionText - значение введенное пользователем в поле "Доля в праве налогооблагаемого объекта"
     * @param holdingPeriodRatioText - значение введенное пользователем в поле "Период владения"
     * @param childrenCountText - значение введенное пользователем в поле "Число несовершеннолетних детей"
     * @param exemptionText - значение введенное пользователем в поле "Льгота"
     * @param reductionFactor - понижающий коэффициент, значение которого получено из выпадающего списка, позволяющего
     * выбрать муниципальное образование
     * @param deduction - налоговый вычет от площади объекта недвижимости, значение которого получено из выпадающего списка, позволяющего
     * выбрать тип налогооблагаемого объекта
     * @param combo_region - выпадающий список, позволяющий выбрать муниципальное образование
     * @param combo_property - выпадающий список, позволяющий выбрать тип налогооблагаемого объекта
     * @param evaporater - коэффициент, завиящий от типа налогооблагаемого имущества
     */
    public TaxAmount(String cadastralValueText, String inventoryTaxText, String squareText, String portionText, String holdingPeriodRatioText, String childrenCountText, String exemptionText, double reductionFactor, double deduction, ComboBox<String> combo_region, ComboBox<String> combo_property, int evaporater) {
        TaxAmount.cadastralValueText = cadastralValueText;
        TaxAmount.inventoryTaxText = inventoryTaxText;
        TaxAmount.squareText = squareText;
        TaxAmount.portionText = portionText;
        TaxAmount.holdingPeriodRatioText = holdingPeriodRatioText;
        TaxAmount.childrenCountText = childrenCountText;
        TaxAmount.exemptionText = exemptionText;
        TaxAmount.reductionFactor = reductionFactor;
        TaxAmount.deduction = deduction;

        TaxAmount.combo_region = combo_region;
        TaxAmount.combo_property = combo_property;
        TaxAmount.evaporater = evaporater;
    }

    /**
     * Метод, утверждающий введенные пользователем значения.
     * В нем создаются экземпляры абстрактных классов, куда передаются параметры, введенные пользователем.
     * Затем корректные значения, возвращаемые общим методом классов-наследников, записываются в числовые переменные, которые
     * и учавствуют в расчетах.
     * Кроме того в данном методе создается список из экземпляров InputError, который возвращается в класс ControllerMain и
     * используется для определения, в каком поле возникла ошибка.
     *
     * @return the list - список из экземпляров InputError
     */
    public static final List<InputError> validate() {
        List<InputError> errors = new ArrayList<InputError>();
        InputNumber cadastralValue = new InputNumber("Кадастровая стоимость", TaxAmount.cadastralValueText);
        InputNumber inventoryTax = new InputNumber("Инвентаризационный налог", TaxAmount.inventoryTaxText);
        InputNumber square = new InputNumber("Площадь", TaxAmount.squareText);
        InputPortion portion = new InputPortion(TaxAmount.portionText);
        InputHoldingPeriodRatio holdingPeriodRatio = new InputHoldingPeriodRatio(TaxAmount.holdingPeriodRatioText);
        InputChildrenCount childrenCount = new InputChildrenCount(TaxAmount.childrenCountText);
        InputExemption exemption = new InputExemption(TaxAmount.exemptionText);

        try {
            TaxAmount.cadastralValue = cadastralValue.getValue();
        } catch (Exception error) {
            errors.add(new InputError(cadastralValue.getFieldName(), error.getMessage()));
        }

        try {
            TaxAmount.inventoryTax = inventoryTax.getValue();
        } catch (Exception error) {
            errors.add(new InputError(inventoryTax.getFieldName(), error.getMessage()));
        }

        try {
            TaxAmount.square = square.getValue();
        } catch (Exception error) {
            errors.add(new InputError(square.getFieldName(), error.getMessage()));
        }

        try {
            TaxAmount.portion = portion.getValue();
        } catch (Exception error) {
            errors.add(new InputError(portion.getFieldName(), error.getMessage()));
        }


        try {
            TaxAmount.holdingPeriodRatio = holdingPeriodRatio.getValue();
        } catch (Exception error) {
            errors.add(new InputError(holdingPeriodRatio.getFieldName(), error.getMessage()));
        }

        try {
            TaxAmount.childrenCount = childrenCount.getValue();
        } catch (Exception error) {
            errors.add(new InputError(childrenCount.getFieldName(), error.getMessage()));
        }

        try {
            TaxAmount.exemption = exemption.getValue();
        } catch (Exception error) {
            errors.add(new InputError(exemption.getFieldName(), error.getMessage()));
        }

        return errors;
    }
    /**
     * Метод, отвечающий за непосредственно расчет суммы налога, необходимой к уплате
     * по заданным в нем формулам
     *
     * @return the double - возвращаемая сумма налога, необходимая к уплате
     */
    public static BigDecimal calculate() {
        EnumSwitch enswitch = new EnumSwitch();
        double finalbid;
        double finalExemption=TaxAmount.exemption;

        BigDecimal finalbidbig;
        BigDecimal finalDeductionbig=BigDecimal.valueOf(TaxAmount.deduction);
        BigDecimal finalExemptionbig=BigDecimal.valueOf(TaxAmount.exemption);
        BigDecimal Deductionbig;
        BigDecimal childrenCountbig;
        BigDecimal evaporaterCountbig;


        enswitch.setPropertyIndex(Integer.toString(combo_region.getSelectionModel().getSelectedIndex()));
        enswitch.setRegionIndex(Integer.toString(combo_property.getSelectionModel().getSelectedIndex()));
        enswitch.setCadastralValue(cadastralValue);
        enswitch.enumuse();
        finalbid= enswitch.getFinalbid();
        finalbidbig = BigDecimal.valueOf(finalbid);

        if (childrenCount >= 3) {
            Deductionbig=BigDecimal.valueOf(deduction);
            childrenCountbig=BigDecimal.valueOf(childrenCount);
            evaporaterCountbig=BigDecimal.valueOf(evaporater);
            finalDeductionbig= Deductionbig.add(childrenCountbig.multiply(evaporaterCountbig));
        }

        if (cadastralValue > 300000000) {
            finalExemption = 0;
            finalExemptionbig = BigDecimal.valueOf(finalExemption);
        }

        BigDecimal cadastralValuebig=BigDecimal.valueOf(cadastralValue);
        BigDecimal squarebig=BigDecimal.valueOf(square);
        BigDecimal inventoryTaxbig=BigDecimal.valueOf(inventoryTax);
        BigDecimal holdingPeriodRatiobig=BigDecimal.valueOf(holdingPeriodRatio);
        BigDecimal portionbig=BigDecimal.valueOf(portion);
        BigDecimal reductionFactorbig = BigDecimal.valueOf(reductionFactor);


        BigDecimal taxBasebig = cadastralValuebig.subtract((cadastralValuebig.divide(squarebig, 8, RoundingMode.HALF_UP)).multiply(finalDeductionbig));


        BigDecimal hundredbbig = BigDecimal.valueOf(100);
        BigDecimal twelvebig = BigDecimal.valueOf(12);
        BigDecimal onebig = BigDecimal.valueOf(1);


        BigDecimal sumWithoutExemptionbig1=((taxBasebig.divide(hundredbbig, 8, RoundingMode.HALF_UP).multiply(finalbidbig)
                .subtract(inventoryTaxbig)).multiply(reductionFactorbig).add(inventoryTaxbig)).multiply(holdingPeriodRatiobig.divide(twelvebig, 8, RoundingMode.HALF_UP))
                .multiply((onebig).divide(portionbig, 8, RoundingMode.HALF_UP));


        BigDecimal sumbig = sumWithoutExemptionbig1.subtract((sumWithoutExemptionbig1).multiply((finalExemptionbig).divide(hundredbbig, 8, RoundingMode.HALF_UP)));

        BigDecimal CheckNull=BigDecimal.valueOf(1);
        if(sumbig.compareTo(CheckNull)==-1){
            sumbig=BigDecimal.valueOf(0);
        }
        return sumbig.setScale(0, RoundingMode.HALF_UP);

    }
}
