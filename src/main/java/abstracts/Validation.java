package abstracts;

import java.util.ArrayList;
import java.util.List;

public class Validation {

    private String cadastralValueText, inventoryTaxText, squareText, portionText, holdingPeriodRatioText, childrenCountText, exemptionText, result;
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
    public Validation(String cadastralValueText, String inventoryTaxText, String squareText, String portionText, String holdingPeriodRatioText, String childrenCountText, String exemptionText, int regionIndex, int propertyIndex) {
        this.cadastralValueText = cadastralValueText;//кадастровая стоимость
        this.inventoryTaxText = inventoryTaxText;//инвентаризационный налог
        this.squareText = squareText;//площадь
        this.portionText = portionText;//доля в собственности
        this.holdingPeriodRatioText = holdingPeriodRatioText;//период владения
        this.childrenCountText = childrenCountText;//кол-во детей
        this.exemptionText = exemptionText;//льгота

        this.regionIndex = regionIndex;
        this.propertyIndex = propertyIndex;
    }

    public final String validate() {
        String messages = "";
        List<InputError> errors = new ArrayList<>();

        InputNumber cadastralValueErr = new InputNumber("Кадастровая стоимость", this.cadastralValueText);
        InputNumber inventoryTaxErr = new InputNumber("Инвентаризационный налог", this.inventoryTaxText);
        InputNumber squareErr = new InputNumber("Площадь", this.squareText);
        InputPortion portionErr = new InputPortion(this.portionText);
        InputHoldingPeriodRatio holdingPeriodRatioErr = new InputHoldingPeriodRatio(this.holdingPeriodRatioText);
        InputChildrenCount childrenCountErr = new InputChildrenCount(this.childrenCountText);
        InputExemption exemptionErr = new InputExemption(this.exemptionText);

        //List <Double> values = Arrays.asList(TaxAmount.cadastralValu1e, TaxAmount.inventoryTax, TaxAmount.square, TaxAmount.portion, TaxAmount.holdingPeriodRatio, TaxAmount.childrenCount, TaxAmount.exemption);
        //List <Object> samples = Arrays.asList(cadastralValu1e, inventoryTax, square, portion, holdingPeriodRatio, childrenCount, exemption);

        try {
            this.cadastralValue = cadastralValueErr.getValue();
        } catch (Exception error) {
            errors.add(new InputError(cadastralValueErr.getFieldName(), error.getMessage()));
        }

        try {
            this.inventoryTax = inventoryTaxErr.getValue();
        } catch (Exception error) {
            errors.add(new InputError(inventoryTaxErr.getFieldName(), error.getMessage()));
        }

        try {
            this.square = squareErr.getValue();
        } catch (Exception error) {
            errors.add(new InputError(squareErr.getFieldName(), error.getMessage()));
        }

        try {
            this.portion = portionErr.getValue();
        } catch (Exception error) {
            errors.add(new InputError(portionErr.getFieldName(), error.getMessage()));
        }

        try {
            this.holdingPeriodRatio = holdingPeriodRatioErr.getValue();
        } catch (Exception error) {
            errors.add(new InputError(holdingPeriodRatioErr.getFieldName(), error.getMessage()));
        }

        try {
            this.childrenCount = childrenCountErr.getValue();
        } catch (Exception error) {
            errors.add(new InputError(childrenCountErr.getFieldName(), error.getMessage()));
        }

        try {
            this.exemption = exemptionErr.getValue();
        } catch (Exception error) {
            errors.add(new InputError(exemptionErr.getFieldName(), error.getMessage()));
        }

//		TODO  метод надо вынести отсюда. логика примерна таже. А также какой класс InputText или InputError как тип.
//		ArrayList <InputText> samples = new ArrayList(List.of(cadastralValu1e, inventoryTax, square, portion, holdingPeriodRatio, childrenCount, exemption));
//
//		for (var x: samples) {
//			testTry(errors,x); TODO передавать каждый раз массив не нужно нужно его вынести в переменные класса.
//		}
//		TODO      метод для цикла                    Тут полиморфизм применяется.
//		public static void testTry(List<InputError> error,InputText rightSide){
//			try {
        //     TODO надо  понять что слева поменять <--TaxAmount.exemption-->.
//				TaxAmount.exemption = rightSide.getValue();
//			} catch (Exception e) {
//				error.add(new InputError(rightSide.getFieldName(), e.getMessage()));
//			}
//		}


        if (this.cadastralValue <= this.inventoryTax & this.cadastralValue != 0) {//если кадастр меньше или = налогу
            errors.add(new InputError(inventoryTaxErr.getFieldName(), "Налог от инвентариз. стоимости должен быть меньше кадастровой стоимости"));
        }

        correlate(); // вызывается метод-установщик значений, чтобы могла выполнится проверка ниже

        if (this.deduction >= this.square & this.squareText != "") { //если площадь меньше вычета(по тиму имущества) то все норм
            String message = "";

            if (this.deduction == 10) {// если коэфф = 10 - выбрана комната
                message = "комнаты";

            } else if (this.deduction == 20) {
                message = "квартиры";

            } else if (this.deduction == 50) {
                message = "жилого дома";
            }

            errors.add(new InputError(squareErr.getFieldName(), "Площадь для " + message +
                    " должна быть больше " + this.deduction + " м"));

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

        } else if (this.propertyIndex == 1) {
            this.deduction = 20;

        } else if (this.propertyIndex == 2) {
            this.deduction = 50;

        } else {
            this.deduction = 0;
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

        if (this.deduction == 10) {//если выбрана квартира
            this.evaporater = 5;//вычет за ребенка из площади - 5 (за каждого после 4-х детей)

        } else if (this.deduction == 20) {//если выбрана комната
            this.evaporater = 5;

        } else if (this.deduction == 50) {//если выбран дом
            this.evaporater = 7;

        } else {
            this.evaporater = 0;
        }

    }

    public String getResult() {
        return this.result;

    }

}