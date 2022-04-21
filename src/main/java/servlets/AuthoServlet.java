package servlets;

import authorization.ManagerClient;

import java.io.IOException;
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
 *
 */
@WebServlet("/AuthoServlet")
public class AuthoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.getSession().setAttribute("logged", null);
		
		String login, password, page = "/Aut.jsp";
		response.setContentType("text/html");

		login = request.getParameter("login");
		password = request.getParameter("password");

/*		if ((!login.contains(" ") & !password.contains(" ")) | (login != "" & password != "") | 
				(login != ";" & password != ";")) {*/

			//Установка пути
			this.setPath(request);
			System.out.print(request.getSession().getAttribute("logged"));
			
			String messageAuthZ = ManagerClient.apiAuthZ(login, password);
			if ("Вы вошли".equals(messageAuthZ)) {
				request.getSession().setAttribute("logged", true);
				page = "/Calc.jsp"; //Форма, на которую будет перенаправление. Калькулятор
			}else{
				request.setAttribute("errorsAut", messageAuthZ);
			}
			
			/*
			 * }else { request.setAttribute("errorsAut",
			 * "Логин и пароль - обязательные поля, которые не могут " +
			 * "содержать пробелы и символ ;");}
			 */

		//			// /Aut.jsp уже присвоено в 29 строке
		//			if (login.equals("admin") & password.equals("admin")) {
		//				page = "/Dashboard.jsp";
		//			} else {
		//				page = "/Calc.jsp"; //Форма, на которую будет перенаправление. Калькулятор
		//			}

		//		}else {
		//			page = "/Aut.jsp";//Авторизация,пользователь останется на той же странице			
		//
		//		}
		if (request.getParameter("regButton") != null) { // если нажата кнопка выхода registracii
			page = "/Reg.jsp";
		}
		getServletContext().getRequestDispatcher(page).forward(request, response);//перенаправление
		return;
	}

	private void setPath(HttpServletRequest request){
		String path = "/resources/";

		AccessResourcePath.PATH_resources.setPath(request.getServletContext().getRealPath(path)); // .PropertyTaxWebApp/
		System.out.println(AccessResourcePath.PATH_resources.toString());

	}
}
