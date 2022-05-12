package servlets;

import enums.CoffRegionAdmin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminServlet.
 * Класс-сервлет, обслуживающий форму Dashboard.jsp
 * 
 * @author balandina-o
 * @version 1.0
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Метод DoGet() вызывается всегда при запуске страницы. Осуществляет проверку пользователя на соответствие роли администратора,
	 * а также установку текущих расчетных коэффициентов в поля формы
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ervletException - исключение, которое может быть выброшено сервлетом
	 * @throws IOException сообщающее, что возникло I/O исключение
	 * @see CoffRegionAdmin#main(String[])
	 */
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		if (request.getSession().getAttribute("role") == null) {
			response.sendRedirect(request.getContextPath() + "/autho");//перенаправление на форму авторизации
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
		request.setAttribute("hidden", "noMessage"); 
		
		getServletContext().getRequestDispatcher("/WEB-INF/Dashboard.jsp").forward(request, response);
	}

	/**
	 * Метод doPost() вызывается при взаимодействии с jsp-формой Dashboard - кнопами, выполняющими функцию submit, передачу данных в сервлет
	 * Осуществляет проверку пользователя на соответствие роли администратора, получение коэффициентов из формы и из передачи для последующей записи в файл
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ervletException - исключение, которое может быть выброшено сервлетом
	 * @throws IOException сообщающее, что возникло I/O исключение
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		if (request.getSession().getAttribute("role") == null) {
			response.sendRedirect(request.getContextPath() + "/autho");//перенаправление на форму авторизации
			return;
		} else if(!request.getSession().getAttribute("role").equals("ADMIN")){ 
			response.sendRedirect(request.getContextPath() + "/autho");
			return;
		} 
			
		if (request.getParameter("changeButton") != null) { // если нажата кнопка "change and save"
			request.setAttribute("hidden", "Message"); 
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
		
		if (request.getParameter("calcButton") != null) { // если нажата кнопка 
				request.setAttribute("hidden", "noMessage"); 
				response.sendRedirect(request.getContextPath() + "/calc");
				return;
			}

		if (request.getParameter("exitButton") != null) { // если нажата кнопка выхода из аккаунта
			request.setAttribute("hidden", "noMessage"); 
			UtilServlets.clearAll();
			request.getSession().removeAttribute("role");
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath() + "/autho");
			return;
		}

		//перенаправление, чтобы юзер остался на той же форме
		getServletContext().getRequestDispatcher("/WEB-INF/Dashboard.jsp").forward(request, response);
	}
}
