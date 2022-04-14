package abstracts;

import java.math.BigDecimal;
import java.util.List;

public final class RequestGenerator{
	private TaxAmount taxamount;
	
	public RequestGenerator(String cadastralValueText, String inventoryTaxText, String squareText, String portionText, String holdingPeriodRatioText, String childrenCountText, String exemptionText, double town, double property) {
		
		TaxAmount taxamount = new TaxAmount(//передача данных в TaxAmount, где они крутятся в validate()
				cadastralValueText,
				inventoryTaxText,
				squareText,
				portionText,
				holdingPeriodRatioText,
				childrenCountText != null ? childrenCountText : "0",
						exemptionText != null ? exemptionText : "0",
								town,
								property
				);
	}

	public String check() {//метод вызывает validate() из TaxAmount
		List<InputError> errors = taxamount.validate();
		String messages = "";
		
		if (errors.size() > 0) {//если массив ошибок не пуст
			
			for (int i = 0; i < errors.size(); i++) {
				messages += errors.get(i).getName() + ": " + errors.get(i).getMessage() + "\n";
			}
			
			return messages;//послать сервлету строку с ошибками
			
		} else {
			
			return null;//иначе null
			
		}
	}
	
	
	public String count() {//метод вызывает calculate() из TaxAmount
		String stringResult = "";
		
		BigDecimal bigDecimalResult = taxamount.calculate();
		stringResult = bigDecimalResult.toString();
		
		return stringResult;//послать сервлету результат
	}
	
}
