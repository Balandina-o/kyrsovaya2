import abstracts.RegionProperty;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * Тест для проверки RegionProperty
 * а именно deduction, evaporater, reductionFactor.
 */
public class TestRegionProperty {
    static RegionProperty testLink = RegionProperty.getInstance();
    String message;
    String messageGorn;

    @Before
    public void fill() { //когда тут были запятые, тест руинился, я поменяла на точки
        message = "deduction: 10 evaporater: 5 reductionFactor: 1.0 \n" +
                "deduction: 20 evaporater: 5 reductionFactor: 1.0 \n" +
                "deduction: 50 evaporater: 7 reductionFactor: 1.0 \n" +
                "deduction: 0 evaporater: 0 reductionFactor: 1.0 \n" +
                "deduction: 0 evaporater: 0 reductionFactor: 1.0 \n";
        messageGorn = "deduction: 10 evaporater: 5 reductionFactor: 0.6 \n" +
                "deduction: 20 evaporater: 5 reductionFactor: 0.6 \n" +
                "deduction: 50 evaporater: 7 reductionFactor: 0.6 \n" +
                "deduction: 0 evaporater: 0 reductionFactor: 0.6 \n" +
                "deduction: 0 evaporater: 0 reductionFactor: 0.6 \n";
    }

    @Test
    public void checkUfa() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            builder.append(correlate(10, i));
        }
        assertEquals(message, builder.toString());
    }

    @Test
    public void checkKazan() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            builder.append(correlate(20, i));
        }
        assertEquals(message, builder.toString());
    }

    @Test
    public void checkMoscow() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            builder.append(correlate(30, i));
        }
        assertEquals(message, builder.toString());
    }

    @Test
    public void checkGorn() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            builder.append(correlate(40, i));
        }
        assertEquals(messageGorn, builder.toString());
    }


    public static String correlate(int regionIndex, int propertyIndex) {
        RegionProperty.getInstance().setInitRegionPropertyIndex(regionIndex,propertyIndex);
        return ss(testLink.getDeduction(), testLink.getEvaporater(), testLink.getReductionFactor());

    }

    public static String ss(int deduction, int evaporater, double Factor) {
        return String.format(Locale.ROOT,"deduction: %d evaporater: %d reductionFactor: %.1f \n", deduction, evaporater, Factor);
    }
}
