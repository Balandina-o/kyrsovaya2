package servlets;

import abstracts.RegionProperty;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Artyom Zlygostev
 * @version 1.0
 */
public class UtilServlets {
    /**
     * @param str - проверяемая строка.
     * @return Содержит ли строка только Буквы - Русс, Англ, Цифры - 0-9
     */
    //TODO это вместо условий проверки логина
    protected static boolean checkLine(String str) {
        Pattern pattern = Pattern.compile("[A-Za-zА-Яа-яЁё0-9]");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * @param request - Запрос сервлета
     * @return Path Абсолютный путь до папки resources
     */
    //TODO это вместо setPath
    public static String getPathToResources(HttpServletRequest request) {
        String path = "/resources/";

        String PathRes = request.getServletContext().getRealPath(path);
        return PathRes;

    }
    //TODO Перенаправление шаблон - использовать или нет хз. Раньше код Дублировался c этим
    protected static void redirect(ServletContext servlet,String page,HttpServletRequest request, HttpServletResponse response){
        // Пример: RequestDispatcher requestDispatcher = servlet.getRequestDispatcher("/Aut.jsp");
        RequestDispatcher requestDispatcher = servlet.getRequestDispatcher(page);
        try {
             requestDispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Очистка всего для "выхода"
    protected void ClearAll(){

    }
    public static void main(String[] args) {
        System.out.println(checkLine(""));
    }
}
