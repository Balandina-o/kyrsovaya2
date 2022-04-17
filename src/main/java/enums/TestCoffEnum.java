package enums;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public enum TestCoffEnum {
    cMIN, cMAX, cAV, CA, CB;
    private double value;
    private static String PATH = "./src/main/resources/coffForAdmin";

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public static void fill() {
        int i = 0;
        try (BufferedReader br = Files.newBufferedReader(Path.of(PATH))) {
            String strLine;
            while ((strLine = br.readLine()) != null) {
                String[] tempArr = strLine.split(";");
                TestCoffEnum.values()[i].setValue(Double.valueOf(tempArr[1]));
                i++;
            }
        } catch (NumberFormatException e) {
            System.out.println("TestCoffEnum навернулся из-за преобразования в число");

        } catch (IOException e) {
            System.out.println("Нет файла для чтения ReaderCoff");
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
//Считывает константы в 11 строке в файл
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
        TestCoffEnum.changeCoffADMIN();
    }
}
