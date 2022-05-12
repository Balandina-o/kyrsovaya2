package document;

/**
 * The Class ChoiceOfFormat.
 * Класс, отвечающий за передачу параметров в метод генерации док-та определенного формата, в зависимости от того, что выбрал пользователь
 * 
 * @author balandina-o
 * @author Artyom
 * @version 2.0
 */
public class ChoiceOfFormat {
	 
    /** Строковые переменные, хранящие пользовательские значения, поступившие из сервлета */
 	private String cadastralValue, inventoryTax, square, portion, holdingPeriodRatio,
     childrenCount, exemption, result;
			 
	/**
	 * Конструктор, в котором передаются параметры
     *
     * @param cadastralValue - кадастровая стоимость объекта
     * @param inventoryTax - инвентаризационный налог
     * @param square - площадь объекта
     * @param portion - доля в праве
     * @param holdingPeriodRatio - период владения
     * @param childrenCount - кол-во детей
     * @param exemption - размер льготы
     * @param result - исчисленный результат
     * @return the byte[] - документ в виде массива байтов
     */
 	
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
 
	/**
	 * Distribution - возвращает в сервлет документ, возвращаемым определенным методом generate,
	 * в который передались пользовательские параметры
	 *
	 * @param generateChoiceDoc - задействование интерфейса generateChoiceDoc
	 * @return the byte[] - документ в виде потока байтов
	 */
	public byte[] distribution (GenerateChoiceDoc generateChoiceDoc){
		return 	generateChoiceDoc.generate(cadastralValue, inventoryTax, square, portion,
				holdingPeriodRatio, childrenCount, exemption, result);

	}
}
