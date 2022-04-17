package enums;

import UtilFiles.PairFromFile;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public enum TestCoffEnum {
    cMIN, cMAX, cAV, CA, CB;
    private double value;
    private static final String PATH = "./src/main/resources/coffForAdmin";

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public static void fill() {
        PairFromFile files = new PairFromFile();
        LinkedHashMap<String, String> readPair = new LinkedHashMap<>(files.readFileAsPair(Path.of(PATH)));
        for (var LogInFile : readPair.entrySet()) {
            String Key = (LogInFile.getKey());
            double Value = Double.parseDouble(LogInFile.getValue());
            TestCoffEnum.valueOf(Key).setValue(Value);
        }
    }

    public static void changeCoffADMIN() {
        //FIXME это нужно админу
        //сначала изменяются в java потом ниже заносятся в файл
        //заменить на цикл
        cMIN.setValue(0.1);
        cMAX.setValue(0.2);
        cAV.setValue(0.3);
        CA.setValue(0.4);
        CB.setValue(0.5);
//Считывает константы в 24 строке в файл
        try (PrintWriter writer = new PrintWriter(PATH)) {
            for (int i = 0; i < TestCoffEnum.values().length; i++) {
                String nameCoff = values()[i].name();
                double valueCoff = values()[i].getValue();
                //печать в файл с новой строки
                writer.println(String.format(Locale.ROOT, "%s;%.3f", nameCoff, valueCoff));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestCoffEnum.fill();
        for (var x : TestCoffEnum.values()) {
            System.out.println(x.getValue());
        }
        TestCoffEnum.changeCoffADMIN();
    }
}
