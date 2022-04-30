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
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		getServletContext().getRequestDispatcher("/WEB-INF/Aut.jsp").forward(request, response);//код перенаправления
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilServlets.setPathToResources(request);
		String login, password, page = "/WEB-INF/Aut.jsp";
		login = request.getParameter("login");
		password = request.getParameter("password");
		
		if (request.getParameter("enterButton") != null) {//если нажата кнопка входа
			//0 - message , 1 - role (индексы)
			List<String> clientData = ManagerClient.apiAuthZ(login.trim(), password.trim());//передаем логин и пароль для проверки их на корректность и определения роли пользователя

			String messageAuthZ = clientData.get(0);//Вы вошли - Неверные данные для входа
			String roleAuZ = clientData.get(1);//EMPTY - USER - ADMIN

			if ("Вы вошли".equals(messageAuthZ) & ("USER".equals(roleAuZ))) {//если аккаунт существует и имеет тип "пользователь"
				request.getSession().setAttribute("role", roleAuZ);

				response.sendRedirect(request.getContextPath() + "/calc");
				return;
			} else if ("Вы вошли".equals(messageAuthZ) & ("ADMIN".equals(roleAuZ))) {//если аккаунт существует и имеет тип "администратор"
				request.getSession().setAttribute("role", roleAuZ);

				response.sendRedirect(request.getContextPath() + "/admin");
				return;
			} else{
				request.setAttribute("errorsAut", messageAuthZ);}//передаем ошибку для последующего вывода ее на экран с помощью скрипта
		
		}

		if (request.getParameter("regButton") != null) { // если нажата кнопка регистрации
			response.sendRedirect(request.getContextPath() + "/reg");
			return;
		}
		
		getServletContext().getRequestDispatcher(page).forward(request, response);//код перенаправления
	}
}
