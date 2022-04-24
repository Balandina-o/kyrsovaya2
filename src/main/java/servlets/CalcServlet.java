package servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

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
@WebServlet(name = "CalcServlet", urlPatterns = {"/calc"})
public class CalcServlet extends HttpServlet {
	private String resultat; 
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    
	    if (request.getSession().getAttribute("role") == null) {
			response.sendRedirect(request.getContextPath() + "/autho");
			return;
		} else if(request.getSession().getAttribute("role").equals("EMPTY")){ 
			response.sendRedirect(request.getContextPath() + "/autho");
			return;
		}
		
		String kadastr, tax, square, part, period, childrens, benefit, regionIndex, propertyIndex;
		response.setContentType("text/html");

		String formatDoc = request.getParameter("format");
		regionIndex = request.getParameter("regionIndex");//получение данных
		propertyIndex = request.getParameter("propertyIndex");
		kadastr = request.getParameter("kadastr");
		tax = request.getParameter("tax");
		square = request.getParameter("square");
		part = request.getParameter("part");
		period = request.getParameter("period");
		childrens = request.getParameter("childrens");
		benefit = request.getParameter("benefit");

		request.setAttribute("kadastr", kadastr);//установка обратно на форму того, 
		request.setAttribute("tax", tax);//что ввел пользователь
		request.setAttribute("square", square);
		request.setAttribute("part", part);
		request.setAttribute("period", period);
		request.setAttribute("childrens", childrens);
		request.setAttribute("benefit", benefit);	
		request.setAttribute("format", formatDoc);
		
		request.setAttribute("regionIndex", regionIndex);	
		request.setAttribute("propertyIndex", propertyIndex);

		
		if (request.getParameter("calcButton") != null) {//если нажата кнопка rascheta
		//Новая функция региона 
		//TODO зачем parseInt
		RegionProperty.getInstance().setInitRegionPropertyIndex(Integer.parseInt(regionIndex), Integer.parseInt(propertyIndex));

		childrens = Objects.equals(childrens, "") ? "0" : childrens;
		benefit = Objects.equals(benefit, "") ? "0" : benefit;

		Validation valid = new Validation(//
				kadastr,
				tax,
				square,
				part,
				period,
				childrens,
				benefit);
		//FIXME условия вынести в методы

		if (valid.validate() != "") { // если строка ошибок не пуста
			request.setAttribute("errorsCalc", valid.validate()); // установить их на форму

		}else { // иначе получить посчитанный результат и поставить его на форму
			resultat = valid.getResult();
			request.setAttribute("result", valid.getResult());
			request.setAttribute("errorsCalc", "noMessage"); 
		}
		}

		if (request.getParameter("pdfButton") != null) { // если нажата кнопка генерации док-та
			if(request.getParameter("result") != "") { // если поле результата не пусто
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment; filename=DocumentGroup2" + formatDoc);
				GeneratePdfWeb genPdf = new GeneratePdfWeb ();
				
				try (OutputStream out = response.getOutputStream()) {
					out.write(genPdf.generate(
							kadastr,
							tax,
							square,
							part,
							period,
							childrens,
							benefit,
							resultat
					));

					response.flushBuffer();

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				return;

			}else { // иначе закинуть вместо данных строки "----"
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment; filename=DocumentGroup2" + formatDoc);
				GeneratePdfWeb genPdf = new GeneratePdfWeb ();
				
				try (OutputStream out = response.getOutputStream()) {
					out.write(genPdf.generate(
							"---",
							"---",
							"---",
							"---",
							"---",
							"---",
							"---",
							"0"
					));

					response.flushBuffer();

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				return;}
			}
		
		if (request.getParameter("exitButton") != null) { // если нажата кнопка выхода из аккаунта
			request.setAttribute("errorsCalc", "noMessage");
			//TODO  -request.getSession().invalidate() ???;
			
			if (!request.getSession().getAttribute("role").equals("ADMIN")) {
				//UtilServlets.clearAll();
				request.getSession().removeAttribute("role");
			}
			response.sendRedirect(request.getContextPath() + "/autho");
			return;
		}

		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Calc.jsp");
		requestDispatcher.forward(request, response);
		return;

	}
	
}
