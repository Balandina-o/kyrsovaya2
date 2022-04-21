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
 *
 */
@WebServlet("/AuthoServlet")
public class AuthoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String login, password, page = "/Aut.jsp";
		response.setContentType("text/html");

		login = request.getParameter("login");
		password = request.getParameter("password"); //.trim();

		//1 раз поставил в самой 1 форме и все
		if (!login.contains(" ") & !password.contains(" ")) { //Проверять на ";"? /TODO уже ;
			//Установка пути
			this.setPath(request);

			String messageAuthZ = ManagerClient.apiAuthZ(login, password);
			if ("Вы вошли".equals(messageAuthZ)) {
				page = "/Calc.jsp"; //Форма, на которую будет перенаправление. Калькулятор
			}else{
				request.setAttribute("errorsAut", messageAuthZ);
			}
		}else {
			request.setAttribute("errorsAut", "Логи и пароль не могут содержать пробелы!");
		}
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
			getServletContext().getRequestDispatcher("/Reg.jsp").forward(request, response);
			return;
		}

		//FIXME вынести в интерфейс- --> дублируется ?
		getServletContext().getRequestDispatcher(page).forward(request, response);//код перенаправления
		return;
	}

	private void setPath(HttpServletRequest request){
		String path = "/resources/";

		AccessResourcePath.PATH_resources.setPath(request.getServletContext().getRealPath(path)); // .PropertyTaxWebApp/
		System.out.println(AccessResourcePath.PATH_resources.toString());

	}
}
