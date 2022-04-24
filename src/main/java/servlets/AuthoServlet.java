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
@WebServlet(name = "AuthoServlet", urlPatterns = {"/autho"})
public class AuthoServlet extends HttpServlet {
	private String login;
	private String password;
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		login = request.getParameter("login");
		password = request.getParameter("password");
		UtilServlets.setPathToResources(request);

		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("enterButton") != null) {// если нажата кнопка vhoda
			//0- message , 1 - role индексы
			List<String> clientData = ManagerClient.apiAuthZ(login.trim(), password.trim());
			String messageAuthZ = clientData.get(0); // Вы вошли - Неверные данные для входа
			String roleAuZ = clientData.get(1); //EMPTY - USER - ADMIN

			if ("Вы вошли".equals(messageAuthZ) & ("USER".equals(roleAuZ))) {
				request.getSession().setAttribute("role", roleAuZ);

				response.sendRedirect(request.getContextPath() + "/calc");
				return;
			} else if ("Вы вошли".equals(messageAuthZ) & ("ADMIN".equals(roleAuZ))) {
				request.getSession().setAttribute("role", roleAuZ);

				response.sendRedirect(request.getContextPath() + "/admin");
				return;

			} else{
				request.setAttribute("errorsAut", messageAuthZ);}
		}

		if (request.getParameter("regButton") != null) { // если нажата кнопка registracii
			//UtilServlets.clearAll();
			response.sendRedirect(request.getContextPath() + "/reg");
			return;
		}
		getServletContext().getRequestDispatcher("/Aut.jsp").forward(request, response);//код перенаправления
		return;
	}
}
