package servlets;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abstracts.RegionProperty;
import abstracts.Validation;
import document.GeneratePdfWeb;

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
		request.setCharacterEncoding("UTF-8");
		
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
		

		//Новая функция региона /TODO зачем parseInt
		RegionProperty.getInstance().setInitRegionPropertyIndex(Integer.parseInt(regionIndex),Integer.parseInt(propertyIndex));

		Validation valid = new Validation(//
				kadastr,
				tax,
				square,
				part,
				period,
				childrens != "" ? childrens : "0",
						benefit != "" ? benefit : "0");

		if (valid.validate() != "") { // если строка ошибок не пуста
			//request.setAttribute("warnings", valid.validate()); // установить их на форму

		}else { // иначе получить посчитанный результат и поставить его на форму
			valid.getResult();
			request.setAttribute("result", valid.getResult());

		}
		
		if (request.getParameter("pdfButton") != null) { // если нажата кнопка генерации док-та
			if(request.getParameter("result") != "") { // если поле результата не пусто
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment; filename=DocumentGroup2.pdf");

				try (OutputStream out = response.getOutputStream()) {
					out.write(GeneratePdfWeb.generate(
							kadastr,
							tax,
							square,
							part,
							period,
							childrens != "" ? childrens : "0",
							benefit != "" ? benefit : "0",
							regionIndex,
							propertyIndex,
							valid.getResult()
					));

					response.flushBuffer();

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				return;
				
			}else { // иначе закинуть вместо данных строки "----"
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment; filename=DocumentGroup2.pdf");

				try (OutputStream out = response.getOutputStream()) {
					out.write(GeneratePdfWeb.generate(
							"---",
							"---",
							"---",
							"---",
							"---",
							"---",
							"---",
							"---",
							"---",
							"0 Руб."
					));

					response.flushBuffer();

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				return;
			}
		}
		
		if (request.getParameter("exitButton") != null) { // если нажата кнопка генерации док-та
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Aut.jsp");
			requestDispatcher.forward(request, response);
			return;
		}
			
		//перенаправление, чтобы юзер остался на той же форме
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Calc.jsp");
		requestDispatcher.forward(request, response);
		return;

	}
}
