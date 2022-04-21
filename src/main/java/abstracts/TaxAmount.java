package abstracts;

import enums.EnumSwitch;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * ГЛАВНЫЙ КЛАСС
 *
 * @author group2
 * @version 1.0
 */
public final class TaxAmount {
    private BigDecimal  finalDeductionBig, evaporaterCountBig, reductionFactorBig;
    private BigDecimal inventoryTaxBig, squareBig, portionBig, holdingPeriodRatioBig, finalExemptionBig;
    BigDecimal DEC_100 = new BigDecimal(100), DEC_12 = new BigDecimal(12), DEC_1 = new BigDecimal(1);

    private double cadastralValue, childrenCount;
    private BigDecimal result;

    public TaxAmount(double cadastralValue, double inventoryTax, double square, double portion,
                     double holdingPeriodRatio, double childrenCount, double exemption) {

        initValue(inventoryTax, square, portion, holdingPeriodRatio, exemption);

        //В параметры calculate()
        this.childrenCount = childrenCount;//кол-во детей
        this.cadastralValue = cadastralValue;//кадастровая стоимость

    }

    /**
     * Метод, отвечающий за непосредственно расчет суммы налога, необходимой к уплате
     * по заданным в нем формулам
     */
    public void calculate() {
        double finalBid;

        BigDecimal finalBidBig;

        //TODO вынести в параметры метода
        BigDecimal childrenCountBig = BigDecimal.valueOf(childrenCount);
        BigDecimal cadastralValueBig = new BigDecimal(cadastralValue);

        //ToDO новое
//		RegionProperty instance =RegionProperty.getInstance();
//		finalBid= EnumSwitch.enumUse(instance.getRegionIndex(), instance.getPropertyIndex(),cadastralValue);

//Todo убрать  -
         finalBid = 0.11;
        finalBidBig = BigDecimal.valueOf(finalBid);


        if (childrenCount >= 3) {
            finalDeductionBig = finalDeductionBig.add(childrenCountBig.multiply(evaporaterCountBig));
        }


        //Считается финальную сумму. Формула из ргрки
        BigDecimal taxBaseBig = cadastralValueBig.subtract((cadastralValueBig.divide(squareBig, 8,
                RoundingMode.HALF_UP)).multiply(finalDeductionBig));

        BigDecimal sumWithoutExemptionBig1 = ((
                taxBaseBig.divide(DEC_100, 8, RoundingMode.HALF_UP)
                        .multiply(finalBidBig)
                        .subtract(inventoryTaxBig)).multiply(reductionFactorBig)
                .add(inventoryTaxBig))
                .multiply(holdingPeriodRatioBig.divide(DEC_12, 8, RoundingMode.HALF_UP))
                .multiply((DEC_1).divide(portionBig, 8, RoundingMode.HALF_UP));

        BigDecimal sumBig =
                sumWithoutExemptionBig1.subtract((
                        sumWithoutExemptionBig1)
                        .multiply((finalExemptionBig)
                                .divide(DEC_100, 8, RoundingMode.HALF_UP)));


        //Проверка
        BigDecimal CheckNull = BigDecimal.valueOf(1);
        if (sumBig.compareTo(CheckNull) == -1) {
            sumBig = BigDecimal.valueOf(0);
        }

        result = sumBig.setScale(0, RoundingMode.HALF_UP);

    }

    private void initValue(double inventoryTax, double square, double portion, double holdingPeriodRatio, double exemption) {
        this.inventoryTaxBig = BigDecimal.valueOf(inventoryTax);
        this.squareBig = BigDecimal.valueOf(square);
        this.holdingPeriodRatioBig = BigDecimal.valueOf(holdingPeriodRatio);
        this.finalExemptionBig = BigDecimal.valueOf(exemption);
        this.portionBig = BigDecimal.valueOf(portion);

        this.finalDeductionBig = BigDecimal.valueOf(RegionProperty.getInstance().getDeduction());
        this.evaporaterCountBig = BigDecimal.valueOf(RegionProperty.getInstance().getEvaporater());
        this.reductionFactorBig = BigDecimal.valueOf(RegionProperty.getInstance().getReductionFactor());
    }

    public BigDecimal getResult() {
        return result;
    }
}