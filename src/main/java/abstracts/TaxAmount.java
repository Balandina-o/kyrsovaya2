package abstracts;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * ГЛАВНЫЙ КЛАСС
 * @author group2
 * @version 1.0
 */
public final class TaxAmount {

	private static double cadastralValue, inventoryTax, square, portion, holdingPeriodRatio, childrenCount, exemption, deduction, reductionFactor, evaporater;
	private static BigDecimal result;

	public TaxAmount(double cadastralValue, double inventoryTax, double square, double portion,
			double holdingPeriodRatio, double childrenCount, double exemption, double deduction,
			double reductionFactor, double evaporater) {

		TaxAmount.cadastralValue = cadastralValue;//кадастровая стоимость
		TaxAmount.inventoryTax = inventoryTax;//инвентаризационный налог
		TaxAmount.square = square;//площадь
		TaxAmount.portion = portion;//доля в собственности
		TaxAmount.holdingPeriodRatio = holdingPeriodRatio;//период владения
		TaxAmount.childrenCount = childrenCount;//кол-во детей
		TaxAmount.exemption = exemption;//льгота

		TaxAmount.deduction = deduction; //коэфф. типа недвижимости (вычет из площади в зависимости от типа недвижимости)
		TaxAmount.reductionFactor = reductionFactor; //коэфф. муниц. образования (понижающий коэффициент)
		//для всех, кроме Горн. А. = 1
		TaxAmount.evaporater = evaporater; //вычет за ребенка (после 4-го ребенка включительно)
	}


	/**
	 * Метод, отвечающий за непосредственно расчет суммы налога, необходимой к уплате
	 * по заданным в нем формулам
	 *
	 */
	public static void calculate() {
		double finalbid;
		BigDecimal finalbidbig;
		BigDecimal finalDeductionbig=BigDecimal.valueOf(deduction);
		BigDecimal finalExemptionbig=BigDecimal.valueOf(TaxAmount.exemption);
		BigDecimal Deductionbig;
		BigDecimal childrenCountbig;
		BigDecimal evaporaterCountbig;

		if (childrenCount >= 3) {//если детей больше или = 3
			Deductionbig=BigDecimal.valueOf(deduction); //так, если детей меньше 3-х, вычет будет равен 0
			childrenCountbig=BigDecimal.valueOf(childrenCount);
			evaporaterCountbig=BigDecimal.valueOf(evaporater);//присвоить вычет
			finalDeductionbig= Deductionbig.add(childrenCountbig.multiply(evaporaterCountbig));
		}

		/*
		 * if (cadastralValue > 300000000) {//если кадастровая стоимость больше 300000000
		 * finalExemption = 0;//льгота аннулируется finalExemptionbig =
		 * BigDecimal.valueOf(finalExemption); }
		 */
		//finalExemptionbig = BigDecimal.valueOf(finalExemption);



		//EnumSwitch enswitch = new EnumSwitch();

		//enswitch.setPropertyIndex(Integer.toString(combo_region.getSelectionModel().getSelectedIndex()));
		//enswitch.setRegionIndex(Integer.toString(combo_property.getSelectionModel().getSelectedIndex()));
		//enswitch.setCadastralValue(cadastralValue);
		//enswitch.enumuse();

		//finalbid= enswitch.getFinalbid();
		finalbid= 0.11; //ЭТО ЗНАЧЕНИЕ Я ПОСТАВИЛА, ПОКА НЕТ enum, НАДО УБРАТЬ И ИСПОЛЬЗОВАТЬ ВЕРХНУЮ СТРОЧКУ
		finalbidbig = BigDecimal.valueOf(finalbid);

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

		BigDecimal sumbig =
				sumWithoutExemptionbig1.subtract((sumWithoutExemptionbig1).multiply((finalExemptionbig).divide(hundredbbig, 8, RoundingMode.HALF_UP)));

		BigDecimal CheckNull=BigDecimal.valueOf(1);
		if(sumbig.compareTo(CheckNull)==-1){
			sumbig=BigDecimal.valueOf(0);
		}

		result = sumbig.setScale(0, RoundingMode.HALF_UP);

	}

	public BigDecimal getResult() {
		return result;
	}
}