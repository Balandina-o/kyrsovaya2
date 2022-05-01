package document;
@FunctionalInterface
public interface GenerateChoiceDoc {
    byte[] generate(String cadastralValue, String inventoryTax, String square,
                    String portion, String holdingPeriodRatio, String childrenCount,
                    String exemption, String result);
}
