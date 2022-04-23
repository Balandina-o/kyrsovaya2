package servlets;

import abstracts.RegionProperty;
import enums.ReaderCoff;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    //TODO это вместо условий проверки логина
    protected static boolean checkLine(String str) {
        Pattern pattern = Pattern.compile("^[A-Za-zА-Яа-яЁё0-9]+$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * @param request - Запрос сервлета
     * @return Path Абсолютный путь до папки resources
     */
    //TODO это вместо setPath вставлять в сервлеты
    public static String setPathToResources(HttpServletRequest request) {
        String path = "/resources";
        String PathRes = request.getServletContext().getRealPath(path);
        AccessResourcePath.PATH_resources.setPath(PathRes);
        return PathRes;
    }

    //TODO Перенаправление шаблон - использовать или нет. Раньше код Дублировался c этим
    protected static void redirect(ServletContext servlet, String page, HttpServletRequest request, HttpServletResponse response) {
        // Пример: RequestDispatcher requestDispatcher = servlet.getRequestDispatcher("/Aut.jsp");
        RequestDispatcher requestDispatcher = servlet.getRequestDispatcher(page);
        try {
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    
    protected String PathToResPDF(String str) { // для пдф документа
    	var fullPath = AccessResourcePath.PATH_resources.getPath();
    	System.out.print(fullPath + str);
    	return fullPath + str;
    	}
}
