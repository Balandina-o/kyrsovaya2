package servlets;

import abstracts.RegionProperty;
import enums.ReaderCoff;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version 1.0
 */
public class UtilServlets {
    /**
     * @param str - проверяемая строка.
     * @return Содержит ли строка только Буквы - Русс, Англ, Цифры - 0-9
     */
    // это вместо условий проверки логина
    protected static boolean checkLine(String str) {
        Pattern pattern = Pattern.compile("^[A-Za-zА-Яа-яЁё0-9]+$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * @param request - Запрос сервлета
     * @return Path Абсолютный путь до папки resources
     */
    // это вместо setPath вставлять в сервлеты
    public static String setPathToResources(HttpServletRequest request) {
        String path = "/";
        String PathRes = request.getServletContext().getRealPath(path);
        AccessResourcePath.PATH_resources.setPath(PathRes);
        return PathRes;
    }

    /**
     * Очистка всего для кнопки "выход"
     */
    protected static void clearAll() {
        RegionProperty.getInstance().clearEntity();
        ReaderCoff.Instance().clearEntity();
    }

    // Это чтобы протестить функционал
    public static void main(String[] args) {
        System.out.println(checkLine(""));
        System.out.println(checkLine(";"));
        System.out.println(checkLine(" "));
        clearAll();
    }
    
    public static String PathToResPDF(String str) { // для пдф документа
    	var fullPath = AccessResourcePath.PATH_resources.getPath();
    	return fullPath + str;
    	}

}
