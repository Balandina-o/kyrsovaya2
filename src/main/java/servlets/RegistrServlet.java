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
 * The Class RegistrServlet.
 */
/**
 * @author balandina-o
 *
 */
@WebServlet("/RegistrServlet")
public class RegistrServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String login, password, page="/Reg.jsp";
		response.setContentType("text/html");

		login = request.getParameter("login"); //.trim(); <-- оно работает
		password = request.getParameter("password"); //.trim();

		if (!login.contains(" ") & !password.contains(" ")) { //Проверять на ";"? /TODO уже ;

			//Здесь
			//передача логина и пароля в Менеджер
			//Тут будет условие: если админ - на форму админа, юзер - в калькулятор
			//TODO все будет работать. только на кнопку не нажимается
			String messageAuthZ = ManagerClient.apiReg(login, password);
			System.out.println(messageAuthZ);
			if ("Зарегистрирован".equals(messageAuthZ)) {
				page = "/Calc.jsp"; //Форма, на которую будет перенаправление. Калькулятор
			}else{
				// Тут ставить сообщение в форму ошибки без перенаправления
				//	.set(messageAuthZ) "Логин занят"
			}

//			if (login.equals("admin") & password.equals("admin")) {
//				page = "/Dashboard.jsp";
//			} else {
//				page = "/Calc.jsp"; //Форма, на которую будет перенаправление. Калькулятор
//			}
//
//
//		}else {
//			page = "/Aut.jsp";//Авторизация,пользователь останется на той же странице
//			//request.setAttribute("error", "Логин и пароль не должны содержать пробелы!");

		}
		//FIXME вынести в интерфейс- --> дублируется ?
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(page);
		requestDispatcher.forward(request, response);//код перенаправления
		return;
	}



}
