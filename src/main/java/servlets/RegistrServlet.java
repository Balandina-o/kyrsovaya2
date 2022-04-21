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

		login = request.getParameter("login");
		password = request.getParameter("password");//.trim();

		if (!login.contains(" ") & !password.contains(" ") ) {
			//TODO все будет работать. только на кнопку не нажимается
			//TODO сейчас все нормально. Удали, пожалуйста, новые учетки

			String messageAuthZ = ManagerClient.apiReg(login, password);
			//System.out.println(messageAuthZ);
			
			if ("Зарегистрирован".equals(messageAuthZ)) {
				request.setAttribute("messageReg", "sucess");
				page = "/Calc.jsp";
				
			}else{
				request.setAttribute("messageReg", messageAuthZ);
			}
		}else {
			request.setAttribute("messageReg", "Логин и пароль не могут содержать пробелы!");
		}
		
		if (request.getParameter("exitButton") != null) { // если нажата кнопка выхода 
			page = "/Aut.jsp";
		}
		
		//FIXME вынести в интерфейс- --> дублируется ?
		//FIXME сейчас оно компактнее выглядит
		getServletContext().getRequestDispatcher(page).forward(request, response);//код перенаправления
		return;
	}



}
