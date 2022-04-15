package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abstracts.RequestGenerator;

/**
 * The Class CalcServlet.
 */
/**
 * @author balandina-o
 *
 */
@WebServlet("/CalcServlet")
public class CalcServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String kadastr, tax, square, part, period, childrens, benefit, regionIndex, propertyIndex;
		response.setContentType("text/html");

		regionIndex = request.getParameter("regionIndex");//получение данных из .джсп
		propertyIndex = request.getParameter("propertyIndex");
		kadastr = request.getParameter("kadastr");
		tax = request.getParameter("tax");
		square = request.getParameter("square");
		part = request.getParameter("part");
		period = request.getParameter("period");
		childrens = request.getParameter("childrens");
		benefit = request.getParameter("benefit");

		request.setAttribute("kadastr", kadastr);//установка обратно на форму того, что ввел пользователь
		request.setAttribute("tax", tax);
		request.setAttribute("square", square);
		request.setAttribute("part", part);
		request.setAttribute("period", period);
		request.setAttribute("childrens", childrens);
		request.setAttribute("benefit", benefit);

		request.setAttribute("kadastr", kadastr);
		request.setAttribute("tax", tax);
		request.setAttribute("square", square);
		request.setAttribute("part", part);
		request.setAttribute("period", period);
		request.setAttribute("childrens", childrens);
		request.setAttribute("benefit", benefit);
		
		RequestGenerator reqGen = new RequestGenerator(//УБЕРУ этот класс впоследствии
				kadastr,
				tax,
				square,
				part,
				period,
				childrens != "" ? childrens : "0",
						benefit != "" ? benefit : "0",
								Double.parseDouble(regionIndex),
								Double.parseDouble(propertyIndex)
				);
		
		if(reqGen.check() != null) {//если строка, возвращаемая методом reqGen не пуста - 
									//отобразить список ошибок на форме
			request.setAttribute("warnings", reqGen.check());
			
		}else {//иначе запустить метод, отвечающий за получение и передачу результата
			request.setAttribute("result", reqGen.count());
			
		}
		//перенаправление, чтобы юзер остался на той же форме
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Calculator.jsp");
		requestDispatcher.forward(request, response);

	}
	
	
	
}
