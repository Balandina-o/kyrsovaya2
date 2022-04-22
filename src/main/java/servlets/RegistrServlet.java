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
 *
 */
@WebServlet("/RegistrServlet")
public class RegistrServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String login, password, page="/Reg.jsp";
		response.setContentType("text/html");

		login = request.getParameter("login");
		password = request.getParameter("password");//.trim();

		//TODO надо изменить - UtilServlets - checkLine
		if ((!login.contains(" ") & !password.contains(" ")) & (!login.contains(";") & !password.contains(";"))){
			String messageAuthZ = ManagerClient.apiReg(login, password);

			//TODO установка пути - UtilServlets - setPath

			if ("Зарегистрирован".equals(messageAuthZ)) {
				request.getSession().setAttribute("role", "user");
				
				response.sendRedirect(request.getContextPath() + "/Calc.jsp");
				return;
				
			}else{
				request.setAttribute("messageReg", messageAuthZ);
			}
		}else {
			request.setAttribute("messageReg", "Логин и пароль - обязательные поля, которые не могут содержать пробелы и символ ;");
		}
		
		if (request.getParameter("exitButton") != null) { // если нажата кнопка выхода 
			response.sendRedirect(request.getContextPath() + "/Aut.jsp");
			return;
		}
		
		//FIXME вынести в интерфейс- --> дублируется ?
		//FIXME сейчас оно компактнее выглядит
		getServletContext().getRequestDispatcher(page).forward(request, response);//код перенаправления
		return;
	}



}
