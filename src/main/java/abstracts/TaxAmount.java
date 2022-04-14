package abstracts;

import java.math.BigDecimal;
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

	private static String cadastralValueText, inventoryTaxText, squareText, portionText, holdingPeriodRatioText, childrenCountText, exemptionText;
	private static double cadastralValue, inventoryTax, square, portion, holdingPeriodRatio, childrenCount, reductionFactor, deduction, exemption;


	public TaxAmount(String cadastralValueText, String inventoryTaxText, String squareText, String portionText, String holdingPeriodRatioText, String childrenCountText, String exemptionText, double town, double property) {
		TaxAmount.cadastralValueText = cadastralValueText; //кадастровая стоимость
		TaxAmount.inventoryTaxText = inventoryTaxText;//инвентаризационный налог
		TaxAmount.squareText = squareText;//площадь
		TaxAmount.portionText = portionText;//доля в собственности
		TaxAmount.holdingPeriodRatioText = holdingPeriodRatioText;//период владения
		TaxAmount.childrenCountText = childrenCountText;//кол-во детей
		TaxAmount.exemptionText = exemptionText;//льгота (в процентах)

		TaxAmount.reductionFactor = town; // коэфф. муниц. образования (понижающий коэффициент)
		TaxAmount.deduction = property; //коэфф. типа недвижимости (вычет из площади в зависимости от типа имущества, кв. метров)

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

		//List <Double> values = Arrays.asList(TaxAmount.cadastralValue, TaxAmount.inventoryTax, TaxAmount.square, TaxAmount.portion, TaxAmount.holdingPeriodRatio, TaxAmount.childrenCount, TaxAmount.exemption);
		//List <Object> samples = Arrays.asList(cadastralValue, inventoryTax, square, portion, holdingPeriodRatio, childrenCount, exemption);


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



		if (TaxAmount.deduction < TaxAmount.square) { //если коэфф. типа недвижимости больше площади
			//все норм

		} else{//иначе выдавать сообщение об ошибке
			String message = "";
			if((int)TaxAmount.deduction == 10){//если коэфф. = 10, то выбрана комната
				message="комнаты";

			}else if((int)TaxAmount.deduction == 20){
				message = "квартиры";

			}else if((int)TaxAmount.deduction == 50){
				message = "жилого дома";
			}

			errors.add(new InputError(square.getFieldName(), "Площадь для " + message + " должна быть больше "+ TaxAmount.deduction +" м"));
		}


		if(TaxAmount.cadastralValue > TaxAmount.inventoryTax) { //если кадастр больше налога
			//все норм

		}else {//иначе - сообщение об ошибке
			errors.add(new InputError(inventoryTax.getFieldName(), "Налог от инвентариз. стоимости должен быть меньше кадастровой стоимости"));
		
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
		//EnumSwitch enswitch = new EnumSwitch();

		double evaporater = 0;
		double finalbid;
		double finalExemption=TaxAmount.exemption;

		BigDecimal finalbidbig;
		BigDecimal finalDeductionbig=BigDecimal.valueOf(TaxAmount.deduction);
		BigDecimal finalExemptionbig=BigDecimal.valueOf(TaxAmount.exemption);
		BigDecimal Deductionbig;
		BigDecimal childrenCountbig;
		BigDecimal evaporaterCountbig;


		//enswitch.setPropertyIndex(Integer.toString(combo_region.getSelectionModel().getSelectedIndex()));
		//enswitch.setRegionIndex(Integer.toString(combo_property.getSelectionModel().getSelectedIndex()));
		//enswitch.setCadastralValue(cadastralValue);
		//enswitch.enumuse();
		//finalbid= enswitch.getFinalbid();

		finalbid= 0.11;
		finalbidbig = BigDecimal.valueOf(finalbid);


		if((int)TaxAmount.deduction == 10) {//если выбрана квартира
			evaporater = 5;//то вычет за каждого ребенка = 5 кв. метров из площади (с 4 ребенка начиная)

		}else if((int)TaxAmount.deduction == 20) {
			evaporater = 5;

		}else if((int)TaxAmount.deduction == 50) {//то вычет за ребенка = 7 (после 3-х)
			evaporater = 7;

		}else if((int)TaxAmount.deduction == 0) {// если машино-место или другое здание - нет вычета
			evaporater = 0;

		}else {
			evaporater = 0;
		}



		if (childrenCount >= 3) { //если детей больше 3-х
			Deductionbig=BigDecimal.valueOf(deduction);
			childrenCountbig=BigDecimal.valueOf(childrenCount);
			evaporaterCountbig=BigDecimal.valueOf(evaporater);// т.е если детей меньше 3-х, этот коэфф = 0
			finalDeductionbig= Deductionbig.add(childrenCountbig.multiply(evaporaterCountbig));
		}

		if (cadastralValue > 300000000) {// если стоимость имущества больше 300000000
			finalExemption = 0;//льгота аннулируется
			finalExemptionbig = BigDecimal.valueOf(finalExemption);
		}

		BigDecimal cadastralValuebig=BigDecimal.valueOf(cadastralValue);
		BigDecimal squarebig=BigDecimal.valueOf(square);
		BigDecimal inventoryTaxbig=BigDecimal.valueOf(inventoryTax);
		BigDecimal holdingPeriodRatiobig=BigDecimal.valueOf(holdingPeriodRatio);
		BigDecimal portionbig=BigDecimal.valueOf(portion);
		BigDecimal reductionFactorbig = BigDecimal.valueOf(reductionFactor);


		BigDecimal taxBasebig = cadastralValuebig.subtract((cadastralValuebig.divide(squarebig, 8,
				RoundingMode.HALF_UP)).multiply(finalDeductionbig));


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