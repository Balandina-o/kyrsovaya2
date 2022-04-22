package servlets;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilServlets {
    protected static boolean checkLine(String str){
        Pattern pattern = Pattern.compile("[A-Za-zА-Яа-яЁё0-9]");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(checkLine(""));
    }
}
