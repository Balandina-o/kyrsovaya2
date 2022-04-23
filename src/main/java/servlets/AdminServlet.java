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
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	/**
	 * @see CoffRegionAdmin#main(String[])
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		
		String coeffUfa = "5"; // Значения из файла в переменные закинуть
		String coeffKazan = "5";//
		String coeffMoscow = "5";
		String coeffGorn = "5";
		
		request.setAttribute("coeffUfa", coeffUfa);//установка обратно на форму
		request.setAttribute("coeffKazan", coeffKazan);
		request.setAttribute("coeffMoscow", coeffMoscow);
		request.setAttribute("coeffGorn", coeffGorn);
	

		if (request.getParameter("changeButton") != null) { // если нажата кнопка "change and save"
			
			coeffUfa = request.getParameter("coeffUfa");//получение данных из .джсп
			coeffKazan = request.getParameter("coeffKazan");
			coeffMoscow = request.getParameter("coeffMoscow");
			coeffGorn = request.getParameter("coeffGorn");
			
			
			//TODO - установка с самого начала
			//TestCoffEnum.FillFromFile();
			//.setAttribute (TestCoffEnum.UFA_COFF)
			//.setAttribute (TestCoffEnum.Kazan_COFF);

			//TODO - изменение при начатии на кнопку "поменять или сохранить"
			//TestCoffEnum.changeCoffADMIN(coeffUfa,coeffKazan,coeffMoscow,coeffGorn);
			
		}
		
		

		if (request.getParameter("exitButton") != null) { // если нажата кнопка выхода из аккаунта

			request.getSession().removeAttribute("role");
			//UtilServlets.clearAll();
			//TODO  -request.getSession().invalidate() ???;
			response.sendRedirect(request.getContextPath() + "/Aut.jsp");
			return;
		}


		//перенаправление, чтобы юзер остался на той же форме
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Dashboard.jsp");
		requestDispatcher.forward(request, response);
		return;
		
	}
}
