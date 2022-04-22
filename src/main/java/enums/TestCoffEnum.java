package enums;

import UtilFiles.PairFromFile;
import servlets.AccessResourcePath;

import java.io.*;
import java.util.*;

public enum TestCoffEnum {
    UFA_COFF, Kazan_COFF, Moscow_COFF, Gorn_COFF;
    private double value;
    private static final String PATH = "/coffForAdmin";
//    static String fullPath="./src/main/webapp/resources/"+PATH;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public static void FillFromFile() {
        PairFromFile files = new PairFromFile();
        //FIXME  - 26 строка для сервлетов.
        String path = AccessResourcePath.PATH_resources.getPath() + PATH;
        LinkedHashMap<String, String> readPair = new LinkedHashMap<>(files.readFileAsPair((path)));
        for (var LogInFile : readPair.entrySet()) {
            String Key = (LogInFile.getKey());
            double Value = Double.parseDouble(LogInFile.getValue());
            TestCoffEnum.valueOf(Key).setValue(Value);
        }
    }

    //TODO это нужно админу
    public static void changeCoffADMIN(String Ufa_Coff, String Kazan_coff, String Moscow_coff, String Gorn_coff) {
        double d_Ufa_Cff, d_Kazan_Cff, d_Moscow_Cff, d_Gorn_Cff;
        d_Ufa_Cff = Double.parseDouble(Ufa_Coff);
        d_Kazan_Cff = Double.parseDouble(Kazan_coff);
        d_Moscow_Cff = Double.parseDouble(Moscow_coff);
        d_Gorn_Cff = Double.parseDouble(Gorn_coff);

        FillFromServletAdmin(d_Ufa_Cff, d_Kazan_Cff, d_Moscow_Cff, d_Gorn_Cff);

//Считывает константы в 24 строке в файл
        String path = AccessResourcePath.PATH_resources.getPath() + PATH;
        try (PrintWriter writer = new PrintWriter(path)) {
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

    private static void FillFromServletAdmin(double Ufa_Coff, double Kazan_coff, double Moscow_coff, double Gorn_coff) {
        double[] newValueEn = {Ufa_Coff, Kazan_coff, Moscow_coff, Gorn_coff};
        for (int i = 0; i < TestCoffEnum.values().length; i++) {
            values()[i].setValue(newValueEn[i]);
        }
    }

    //TODO - Пример как вызывать.
    //Сначала Заполняется из файла на сервлет админа?
    //Потом при нажатии на кнопку вызывается метод changeCoff. куда передаются данные из формы
    public static void main(String[] args) throws IOException {
        TestCoffEnum.FillFromFile();
        TestCoffEnum.changeCoffADMIN("1.0", "3.0", "4.0", "5.0");
        for (var x : TestCoffEnum.values()) {
            System.out.println(x.getValue());
        }
    }
}
