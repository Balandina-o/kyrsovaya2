package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abstracts.Validation;
import document.GeneratePdf;

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
		

		request.setAttribute("regionIndex", regionIndex);	
		request.setAttribute("propertyIndex", propertyIndex);	
		
		//РЕШИТЬ, КАК УСТАНАВЛИВАТЬ ЗНАЧЕНИЯ СПИСКОВ ОБРАТНО
		//ПОЛУЧИЛОСЬ!
		//И ОЧИЩАТЬ НЕПРАВИЛЬНО ЗАПОЛНЕННЫЕ ПОЛЯ

		Validation valid = new Validation(//
				kadastr,
				tax,
				square,
				part,
				period,
				childrens != "" ? childrens : "0",
						benefit != "" ? benefit : "0",
								Integer.parseInt(regionIndex),
								Integer.parseInt(propertyIndex)
				);

		if (valid.validate() != "") { // если строка ошибок не пуста
			request.setAttribute("warnings", valid.validate()); // установить их на форму

		}else { // иначе получить посчитанный результат и поставить его на форму
			valid.getResult();
			request.setAttribute("result", valid.getResult());

		}
		
		if (request.getParameter("button").equals("pdfButton")) { // если нажата кнопка генерации док-та
			if(valid.getResult() != null) { // если результат посчитался
				GeneratePdf genPdf = new GeneratePdf (); 
				System.out.print("Нормальный Новый документ"); // сгенерировать документ
				
			}else { // иначе закинуть вместо данных строки "----"
				// GeneratePdf genPdf = new GeneratePdf ("---", "0"); 
				System.out.print("Документ с ------");
			}
		}
		
		if (request.getParameter("button").equals("exitButton")) { // если нажата кнопка генерации док-та
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Authorisator.jsp");
			requestDispatcher.forward(request, response);
		}
			
		//перенаправление, чтобы юзер остался на той же форме
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Calculator.jsp");
		requestDispatcher.forward(request, response);

	}
}
