package enums;

import UtilFiles.PairFromFile;
import servlets.AccessResourcePath;

import java.io.*;
import java.util.*;

public enum TestCoffEnum {
    UFA_COFF, Kazan_COFF, Moscow_COFF, Gorn_COFF;
    private double value;
    private static final String PATH = "coffForAdmin";

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public static void FillFromFile() {
        PairFromFile files = new PairFromFile();
        //FIXME  - 26 строка для сервлетов.
        String fullPath = "./src/main/webapp/resources/"+PATH;
        //AccessResourcePath.PATH_resources.getPath()+PATH;

        LinkedHashMap<String, String> readPair = new LinkedHashMap<>(files.readFileAsPair((fullPath)));
        for (var LogInFile : readPair.entrySet()) {
            String Key = (LogInFile.getKey());
            double Value = Double.parseDouble(LogInFile.getValue());
            TestCoffEnum.valueOf(Key).setValue(Value);
        }
    }
    //TODO это нужно админу
    public static void changeCoffADMIN(double Ufa_Coff,double Kazan_coff,double Moscow_coff,double Gorn_coff) {
        FillFromServletAdmin(Ufa_Coff, Kazan_coff,Moscow_coff,Gorn_coff);

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
    private static void FillFromServletAdmin(double Ufa_Coff,double Kazan_coff,double Moscow_coff,double Gorn_coff){
        double[] newValueEn={Ufa_Coff,Kazan_coff,Moscow_coff,Gorn_coff};
        for (int i = 0; i <TestCoffEnum.values().length ; i++) {
            values()[i].setValue(newValueEn[i]);
        }
    }

    //TODO - Пример как вызывать.
    //Сначала Заполняется из файла на сервлет админа?
    //Потом при нажатии на кнопку вызывается метод changeCoff. куда передаются данные из формы
    public static void main(String[] args) {
        TestCoffEnum.FillFromFile();
        TestCoffEnum.changeCoffADMIN(1.0,2.0,3.0,5.0);
        for (var x: TestCoffEnum.values()) {
            System.out.println(x.getValue());
        }
    }
}
