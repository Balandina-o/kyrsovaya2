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
		
		String login, password, page = "/Aut.jsp";
		response.setContentType("text/html");

		login = request.getParameter("login");
		password = request.getParameter("password");

			//Установка пути
			this.setPath(request);
			
			String messageAuthZ = ManagerClient.apiAuthZ(login, password);
			if ("Вы вошли".equals(messageAuthZ)) {
				request.getSession().setAttribute("role", "user");
				
				//request.getSession().setAttribute("role", "admin");
				
				response.sendRedirect(request.getContextPath() + "/Calc.jsp");//Форма, на которую будет перенаправление. Калькулятор
				return;
			}else{
				request.setAttribute("errorsAut", messageAuthZ);
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
		if (request.getParameter("regButton") != null) { // если нажата кнопка registracii
			response.sendRedirect(request.getContextPath() + "/Reg.jsp");
			return;
		}
		
		getServletContext().getRequestDispatcher(page).forward(request, response);//код перенаправления
		return;
	}

	private void setPath(HttpServletRequest request){
		String path = "/resources/";

		AccessResourcePath.PATH_resources.setPath(request.getServletContext().getRealPath(path)); // .PropertyTaxWebApp/
		System.out.println(AccessResourcePath.PATH_resources.toString());

	}
}
