package servlets;

import abstracts.RegionProperty;
import abstracts.TaxAmount;

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
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	/**
	 * @see enums.TestCoffEnum#main(String[])
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String coeffUfa, coeffKazan, coeffMoscow, coeffGorn;
		response.setContentType("text/html");

		coeffUfa = request.getParameter("coeffUfa");//получение данных из .джсп
		coeffKazan = request.getParameter("coeffKazan");
		coeffMoscow = request.getParameter("coeffMoscow");
		coeffGorn = request.getParameter("coeffGorn");

		request.setAttribute("coeffUfa", coeffUfa);//установка обратно на форму того, что ввел пользователь
		request.setAttribute("coeffKazan", coeffKazan);
		request.setAttribute("coeffMoscow", coeffMoscow);
		request.setAttribute("coeffGorn", coeffGorn);		
		
		//Тут запись в файл.. возможно, чтение оттуда
		//24 строка пример записи и чтения. Нужен Сервлет для теста этого всего
		
		if (request.getParameter("exitButton") != null) { // если нажата кнопка выхода из аккаунта
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Aut.jsp");
			requestDispatcher.forward(request, response);
			//RegionProperty.getInstance().setInitRegionPropertyIndex(-5,-5);
			//FIXME вынести в интерфейс- --> дублируется ?
			return;
		}
		//FIXME вынести в интерфейс- --> дублируется ?
		//перенаправление, чтобы юзер остался на той же форме
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Dashboard.jsp");
		requestDispatcher.forward(request, response);
		return;
		
	}
}
