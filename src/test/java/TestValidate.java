import abstracts.Validation;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestValidate {

    @Test
    public void checkAllNoCorrect(){
        Validation validation = new Validation("а","б","с",
                "а","а","а","а",10,0);
        String temp ="Кадастровая стоимость: введите вещественное число\nИнвентаризационный налог: введите вещественное число\nПлощадь: введите вещественное число\nРазмер доли: введите корректную обыкновенную дробь или 1\nПериод владения: введите от 1 до 12\nКоличество детей: ведите число от 0 до 99\nЛьгота: введите вещественное число\nПлощадь: Площадь для комнаты должна быть больше 10.0 м\n";
        assertEquals(temp,validation.validate());
    }
    @Test
    public void checkCorrect(){
        Validation validation1 = new Validation("335000", "123", "45", "1", "12",
                "1", "1", 10, 0);
        assertEquals("",validation1.validate());
    }
    @Test
    public void  checkSum(){
        Validation validation1 = new Validation("335000", "123", "45", "1", "12",
                "1", "1", 10, 0);
        validation1.validate();
        assertTrue(Objects.equals("284",validation1.getResult()));
    }

}
