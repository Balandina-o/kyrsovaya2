package enums;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;

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
                TestCoffEnum.values()[i].setValue(Double.parseDouble(tempArr[1]));
                i++;
            }
        } catch (NumberFormatException e) {
            System.out.println("TestCoffEnum навернулся из-за преобразования в число");

        } catch (IOException e) {
            System.out.println("Нет файла для чтения ReaderCoff");
        }
    }
    public static void changeCoffADMIN(String testCoffEnum, double coff){
        cMIN.setValue(3.0); //реализация без файла
    }
    public static void main(String[] args) {
        TestCoffEnum.fill();
        System.out.println(cMIN.getValue());
        System.out.println(cMAX.getValue());
        System.out.println(cMIN.getValue());
        System.out.println("------------------");

        changeCoffADMIN(cMIN.name(),666.0);
        System.out.println(cMIN.getValue());
    }
}
