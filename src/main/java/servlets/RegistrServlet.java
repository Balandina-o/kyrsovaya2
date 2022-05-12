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
 * Класс-сервлет, обслуживающий форму Reg.jsp
 * 
 * @author balandina-o
 * @version 1.0
 */
@WebServlet(name = "RegistrServlet", urlPatterns = {"/reg"})
public class RegistrServlet extends HttpServlet {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
	
	/**
	 * Метод DoGet() вызывается всегда при запуске страницы.
	 * Устанавливает тип данных, поступаемых из связанного с сервлетом файла, в данном случае - text/html
	 * и их кодировку 
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException - исключение, которое может быть выброшено сервлетом
	 * @throws IOException сообщающее, что возникло I/O исключение
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        getServletContext().getRequestDispatcher("/WEB-INF/Reg.jsp").forward(request, response);//код перенаправления
	}
    
	/**
	 * Метод doPost() вызывается при взаимодействии с jsp-формой Reg - кнопами, выполняющими функцию submit, передачу данных в сервлет
	 * Связывется с методом, проверяющим введенные пользователем логин и пароль на корректность 
	 * Осуществляет перенаправление пользователя на страницу с калькулятором и передает пользовательские данные дальше для регистрации их в системе
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ervletException - исключение, которое может быть выброшено сервлетом
	 * @throws IOException сообщающее, что возникло I/O исключение
	 */
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		UtilServlets.setPathToResources(request); 
		String login, password, page = "/WEB-INF/Reg.jsp";
		login = request.getParameter("login");
		password = request.getParameter("password");
		
		if (request.getParameter("regButton") != null) {// если нажата кнопка 

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
        		request.setAttribute("messageReg", "Логин и пароль - обязательные поля, которые могут содержать только буквы и цифры");
        	}
        }
        
        if (request.getParameter("exitButton") != null) { // если нажата кнопка выхода
            response.sendRedirect(request.getContextPath() + "/autho");
            return;
        }
        getServletContext().getRequestDispatcher(page).forward(request, response);//код перенаправления
    }
}
