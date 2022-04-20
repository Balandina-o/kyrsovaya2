package servlets;

import authorization.ManagerClient;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Class AuthoServlet.
 */

/**
 * @author balandina-o
 */
@WebServlet("/AuthoServlet")
public class AuthoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login, password, page = "/Authorisator.jsp";
        response.setContentType("text/html");

        login = request.getParameter("login"); //.trim(); <-- оно работает
        password = request.getParameter("password"); //.trim();

        if (!login.contains(" ") & !password.contains(" ")) { //Проверять на ";"? /TODO уже ;

            //Здесь
            //передача логина и пароля в Менеджер
            //Тут будет условие: если админ - на форму админа, юзер - в калькулятор

            String path = "/resources/";

            AccessResourcePath.PATH_resources.setPath(request.getServletContext().getRealPath(path)); // .PropertyTaxWebApp/
            System.out.println(AccessResourcePath.PATH_resources.toString());

            if ("Вы вошли".equals(ManagerClient.apiAuthZ(login, password))) {
                page = "/Calculator.jsp"; //Форма, на которую будет перенаправление. Калькулятор
            }
        } else {
            //Авторизация,пользователь останется на той же странице
            request.setAttribute("error", "Логин и пароль не должны содержать пробелы!");

        }

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(page);
        requestDispatcher.forward(request, response);//код перенаправления

    }
}
