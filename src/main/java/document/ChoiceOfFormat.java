package document;

public class ChoiceOfFormat {
	 private String cadastralValue, inventoryTax, square, portion, holdingPeriodRatio,
     childrenCount, exemption, result;
	 byte[] bytes;
			 
	public ChoiceOfFormat(String cadastralValue, String inventoryTax, String square,
            String portion, String holdingPeriodRatio, String childrenCount,
            String exemption, String result) {
		
		this.cadastralValue = cadastralValue;//кадастровая стоимость
        this.inventoryTax = inventoryTax;//инвентаризационный налог
        this.square = square;//площадь
        this.portion = portion;//доля в собственности
        this.holdingPeriodRatio = holdingPeriodRatio;//период владения
        this.childrenCount = childrenCount;//кол-во детей
        this.exemption = exemption;//льгота
        this.result = result;
	}
 
	//TODO Полиморфизм. работает также как 2 метода выше вместе. нужно только добавить куда то
	public byte[] distribution (GenerateChoiceDoc generateChoiceDoc){
		return 	generateChoiceDoc.generate(cadastralValue, inventoryTax, square, portion,
				holdingPeriodRatio, childrenCount, exemption, result);

	}
}
