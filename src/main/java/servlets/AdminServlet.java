package servlets;

import enums.CoffRegionAdmin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Class AdminServlet.
 */
/**
 * @author balandina-o
 *
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	/**
	 * @see CoffRegionAdmin#main(String[])
	 * 
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		if (request.getSession().getAttribute("role") == null) {
			response.sendRedirect(request.getContextPath() + "/autho");
			return;
		} else if(!request.getSession().getAttribute("role").equals("ADMIN")){ 
			response.sendRedirect(request.getContextPath() + "/autho");
			return;
		}

			CoffRegionAdmin.FillFromFile();
			request.setAttribute("coeffUfa", CoffRegionAdmin.UFA_COFF.getValue());//установка на форму
			request.setAttribute("coeffKazan", CoffRegionAdmin.Kazan_COFF.getValue());
			request.setAttribute("coeffMoscow", CoffRegionAdmin.Moscow_COFF.getValue());
			request.setAttribute("coeffGorn", CoffRegionAdmin.Gorn_COFF.getValue());

		if (request.getParameter("changeButton") != null) { // если нажата кнопка "change and save"
			String coeffUfa = request.getParameter("coeffUfa");
			String coeffKazan = request.getParameter("coeffKazan");
			String coeffMoscow = request.getParameter("coeffMoscow");
			String coeffGorn = request.getParameter("coeffGorn");

			CoffRegionAdmin.changeCoffADMIN(coeffUfa,coeffKazan,coeffMoscow,coeffGorn);
			
			CoffRegionAdmin.FillFromFile();
			request.setAttribute("coeffUfa", CoffRegionAdmin.UFA_COFF.getValue());//установка на форму
			request.setAttribute("coeffKazan", CoffRegionAdmin.Kazan_COFF.getValue());
			request.setAttribute("coeffMoscow", CoffRegionAdmin.Moscow_COFF.getValue());
			request.setAttribute("coeffGorn", CoffRegionAdmin.Gorn_COFF.getValue());

		}

		if (request.getParameter("exitButton") != null) { // если нажата кнопка выхода из аккаунта
			request.getSession().removeAttribute("role");
			//UtilServlets.clearAll();
			response.sendRedirect(request.getContextPath() + "/autho");
			return;
		}

		//перенаправление, чтобы юзер остался на той же форме
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Dashboard.jsp");
		requestDispatcher.forward(request, response);
		return;

	}
}
