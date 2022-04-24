package servlets;

import authorization.ManagerClient;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Class RegistrServlet.
 */

/**
 * @author balandina-o
 */
@WebServlet(name = "RegistrServlet", urlPatterns = {"/reg"})
public class RegistrServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String login, password, page = "/Reg.jsp";
        response.setContentType("text/html");

        login = request.getParameter("login");
        password = request.getParameter("password");

        
        if (request.getParameter("regButton") != null) {// если нажата кнопка registracii
        UtilServlets.setPathToResources(request); //тест

        if ((UtilServlets.checkLine(login.trim())) & (UtilServlets.checkLine(password.trim()))) {
            String messageAuthZ = ManagerClient.apiReg(login.trim(), password.trim());

            if ("Зарегистрирован".equals(messageAuthZ)) {
                request.getSession().setAttribute("role", "USER");
                response.sendRedirect(request.getContextPath() + "/calc");
                return;

            } else {
                request.setAttribute("messageReg", messageAuthZ);
            }
        } else {
            request.setAttribute("messageReg", "Логин и пароль - обязательные поля, которые не могут содержать пробелы и символ ;");
        }}
        
        if (request.getParameter("exitButton") != null) { // если нажата кнопка выхода
            response.sendRedirect(request.getContextPath() + "/autho");
            return;
        }

        getServletContext().getRequestDispatcher("/WEB-INF/Reg.jsp").forward(request, response);//код перенаправления
        return;
    }


}
