package abstracts;

import java.util.*;

public class Validation {
    InputNumber cadastralValueErr, inventoryTaxErr, squareErr;
    InputPortion portionErr;
    InputHoldingPeriodRatio holdingPeriodRatioErr;
    InputChildrenCount childrenCountErr;
    InputExemption exemptionErr;
    List<InputError> errors;
    private String  squareText, result; //TODO squareText не используется птмч он в init
    private double cadastralValue, inventoryTax, square, portion, holdingPeriodRatio, childrenCount, exemption, deduction, reductionFactor, evaporater;
    private int regionIndex, propertyIndex;

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
    public Validation(String cadastralValueText, String inventoryTaxText, String squareText,
                      String portionText, String holdingPeriodRatioText, String childrenCountText,
                      String exemptionText, int regionIndex, int propertyIndex) {

        init(cadastralValueText, inventoryTaxText, squareText, portionText, holdingPeriodRatioText, childrenCountText, exemptionText);

        this.regionIndex = regionIndex;
        this.propertyIndex = propertyIndex;
    }

    public final String validate() {
        ArrayList<InputText> samples = new ArrayList(List.of(cadastralValueErr, inventoryTaxErr, squareErr, portionErr,
                holdingPeriodRatioErr, childrenCountErr, exemptionErr));
        String messages = "";
        errors = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            testTry(i, samples.get(i));
        }
        if (this.cadastralValue <= this.inventoryTax & this.cadastralValue != 0) {//если кадастр меньше или = налогу
            errors.add(new InputError(inventoryTaxErr.getFieldName(), "Налог от инвентариз. стоимости должен быть меньше кадастровой стоимости"));
        }
        correlate(); // вызывается метод-установщик значений, чтобы могла выполнится проверка ниже
                                            //TODO squareText пустой теперь??
        if (this.deduction >= this.square & !Objects.equals(this.squareText, "")) { //если площадь меньше вычета(по тиму имущества) то все норм
            String message = "";

            if (this.deduction == 10) {// если коэфф = 10 - выбрана комната
                message = "комнаты";

            } else if (this.deduction == 20) {
                message = "квартиры";

            } else if (this.deduction == 50) {
                message = "жилого дома";
            }

            errors.add(new InputError(squareErr.getFieldName(),
                    String.format(Locale.ROOT,"Площадь для %s должна быть больше %.1f м", message, this.deduction)));
        }
        if (errors.size() > 0) {
            for (int i = 0; i < errors.size(); i++) {
                messages += errors.get(i).getName() + ": " + errors.get(i).getMessage() + "\n";
            }
        } else {
            TaxAmount tax = new TaxAmount(//
                    this.cadastralValue,
                    this.inventoryTax,
                    this.square,
                    this.portion,
                    this.holdingPeriodRatio,
                    this.childrenCount,
                    (this.cadastralValue <= 300000000) ? this.exemption : 0,
                    this.deduction,
                    this.reductionFactor,
                    this.evaporater
            );
            tax.calculate();
            this.result = ((tax.getResult()).toString());
        }

        return messages;
    }

    public final void correlate() {
        //В if else устанавливать значение deduction в зависимости от propertyIndex
        if (this.propertyIndex == 0) {
            this.deduction = 10;
            this.evaporater = 5;//вычет за ребенка из площади - 5 (за каждого после 4-х детей)
        } else if (this.propertyIndex == 1) {
            this.deduction = 20;
            this.evaporater = 5;
        } else if (this.propertyIndex == 2) {
            this.deduction = 50;
            this.evaporater = 7;
        } else {
            this.deduction = 0;
            this.evaporater = 0;
        }
        if (this.regionIndex == 10) {
            this.reductionFactor = 1;
        } else if (this.regionIndex == 20) {//почему 2? По закону тут должен быть 1
            this.reductionFactor = 2;
        } else if (this.regionIndex == 30) {//почему 5? По закону тут должен быть 1
            this.reductionFactor = 5;
        } else if (this.regionIndex == 40) {
            this.reductionFactor = 0.6;
        }
    }

    public String getResult() {
        return this.result;
    }
    /**
     * метод для инициализации Текста из сервлета. ЧТО ДЕЛАТЬ С squareText хз.
    **/
    private void init(String cadastralValueText, String inventoryTaxText, String squareText, String portionText,
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
// индекс из цикла для понимания что сейчас будет проверятся. Вызывает testTry
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
}