package servlets;

import authorization.ManagerClient;

import java.io.IOException;
import java.util.List;
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

		login = request.getParameter("login").trim();
		password = request.getParameter("password").trim();

		//Установка пути
		//TODO надо вызывать через -  UtilServlets
			UtilServlets.setPathToResources(request);
			//this.setPath(request);

			//0- message , 1 - role индексы
			List<String> clientData = ManagerClient.apiAuthZ(login, password);
			String messageAuthZ = clientData.get(0); // Вы вошли - Неверные данные для входа
			String roleAuZ = clientData.get(1); //EMPTY - USER - ADMIN

			if ("Вы вошли".equals(messageAuthZ) & ("USER".equals(roleAuZ))) {
				request.getSession().setAttribute("role", roleAuZ);
				
				response.sendRedirect(request.getContextPath() + "/Calc.jsp");//Форма, на которую будет перенаправление. Калькулятор
				return;
			} else if ("Вы вошли".equals(messageAuthZ) & ("ADMIN".equals(roleAuZ))) {
				request.getSession().setAttribute("role", roleAuZ);
				
				response.sendRedirect(request.getContextPath() + "/Dashboard.jsp");//Форма, на которую будет перенаправление. Калькулятор
				return;
				
			} else{
				request.setAttribute("errorsAut", messageAuthZ);
			}
			
		if (request.getParameter("regButton") != null) { // если нажата кнопка registracii
			//UtilServlets.clearAll();
			response.sendRedirect(request.getContextPath() + "/Reg.jsp");
			return;
		}
		getServletContext().getRequestDispatcher(page).forward(request, response);//код перенаправления
		return;
	}
}
