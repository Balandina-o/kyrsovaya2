package abstracts;

import UtilFiles.ClearRes;

import java.util.*;

public class Validation implements ClearRes {
    private InputNumber cadastralValueErr, inventoryTaxErr, squareErr;
    private InputPortion portionErr;
    private InputHoldingPeriodRatio holdingPeriodRatioErr;
    private InputChildrenCount childrenCountErr;
    private InputExemption exemptionErr;
    private List<InputError> errors;
    private String squareText, result; //TODO squareText не используется птмч он в init
    private double cadastralValue, inventoryTax, square, portion, holdingPeriodRatio, childrenCount, exemption;

//TODO Вместо индексов
    /**
     * @see RegionProperty
     */

    /**
     * Метод, утверждающий введенные пользователем значения.
     * В нем создаются экземпляры абстрактных классов, куда передаются параметры, введенные пользователем.
     * Затем корректные значения, возвращаемые общим методом классов-наследников, записываются в числовые переменные, которые
     * и участвуют в расчетах.
     * Кроме того в данном методе создается список из экземпляров InputError, который возвращается в класс ControllerMain и
     * используется для определения, в каком поле возникла ошибка.
     *
     * @return the list - список из экземпляров InputError
     */
    public Validation(String cadastralValueText, String inventoryTaxText, String squareText,
                      String portionText, String holdingPeriodRatioText, String childrenCountText,
                      String exemptionText) {
        this.squareText = squareText;
        init(cadastralValueText, inventoryTaxText, portionText, holdingPeriodRatioText, childrenCountText, exemptionText);
    }
//        for (var inpErr:samples) {
//        testTry(inpErr);
//    }
    public final String validate()  {
        ArrayList<InputText> samples = new ArrayList(List.of(cadastralValueErr, inventoryTaxErr, squareErr, portionErr,
                holdingPeriodRatioErr, childrenCountErr, exemptionErr));
        String messages = "";
        errors = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            testTry(i, samples.get(i));
        }
        if (cadastralValue <= inventoryTax & cadastralValue != 0) {//если кадастр меньше или = налогу
            errors.add(new InputError(inventoryTaxErr.getFieldName(), "Налог от инвентаре. стоимости должен быть меньше кадастровой стоимости"));
        }
        //correlate(); // вызывается метод-установщик значений, чтобы могла выполнится проверка ниже
        //TODO squareText пустой теперь??
        double deduction= RegionProperty.getInstance().getDeduction();
        if (deduction >= square & !Objects.equals(squareText, "")) { //если площадь меньше вычета(по тиму имущества), то все норм
            String message = "";

            if (deduction == 10) {// если коэфф = 10 - выбрана комната
                message = "комнаты";

            } else if (deduction == 20) {
                message = "квартиры";

            } else if (deduction == 50) {
                message = "жилого дома";
            }

            errors.add(new InputError(squareErr.getFieldName(),
                    String.format(Locale.ROOT, "Площадь для %s должна быть больше %.1f м", message, deduction)));
        }
        if (errors.size() > 0) {
            for (int i = 0; i < errors.size(); i++) {
                messages += errors.get(i).getName() + ": " + errors.get(i).getMessage() + "\n";
            }
        } else {
            TaxAmount tax = new TaxAmount(//
                    cadastralValue, //кадастровая стоимость
                    inventoryTax, //инвентаризационный налог
                    square, //площадь
                    portion, //доля в собственности
                    holdingPeriodRatio, //период владения
                    childrenCount, //кол-во детей
                    (cadastralValue <= 300000000) ? exemption : 0); //льгота
            tax.calculate();
            result = ((tax.getResult()).toString());
        }

        return messages;
    }
//TODO Вместо correlate - RegionProperty

    /**
     * @see RegionProperty
     */

    public String getResult() {
        return result;
    }

    /**
     * Метод для инициализации Текста из сервлета. ЧТО ДЕЛАТЬ С squareText хз.
     **/
    private void init(String cadastralValueText, String inventoryTaxText, String portionText,
                      String holdingPeriodRatioText, String childrenCountText, String exemptionText) {

        cadastralValueErr = new InputNumber("Кадастровая стоимость", cadastralValueText);
        inventoryTaxErr = new InputNumber("Инвентаризационный налог", inventoryTaxText);
        squareErr = new InputNumber("Площадь", squareText);
        portionErr = new InputPortion(portionText);
        holdingPeriodRatioErr = new InputHoldingPeriodRatio(holdingPeriodRatioText);
        childrenCountErr = new InputChildrenCount(childrenCountText);
        exemptionErr = new InputExemption(exemptionText);
    }

    //Совмещение try из validate()
    private void testTry(int index, InputText rightSide) {
        try {
            Check(index, rightSide);
        } catch (Exception e) {
            errors.add(new InputError(rightSide.getFieldName(), e.getMessage()));
        }
    }

    // Индекс из цикла для понимания что сейчас будет провериться. Вызывает testTry
    private void Check(int index, InputText rightSide) throws Exception {
        switch (index) {
            case 0:
                cadastralValue = rightSide.getValue();
                break;
            case 1:
                inventoryTax = rightSide.getValue();
                break;
            case 2:
                square = rightSide.getValue();
                break;
            case 3:
                portion = rightSide.getValue();
                break;
            case 4:
                holdingPeriodRatio = rightSide.getValue();
                break;
            case 5:
                childrenCount = rightSide.getValue();
                break;
            case 6:
                exemption = rightSide.getValue();
                break;
        }
    }

    @Override
    public void clearEntity() {
        cadastralValueErr=null;
        inventoryTaxErr=null;
        squareErr=null;
        portionErr=null;
        holdingPeriodRatioErr=null;
        childrenCountErr=null;
        exemptionErr=null;
        squareText=null;
        result=null;
        errors=null;
    }
}