package servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abstracts.RegionProperty;
import abstracts.Validation;
import document.ChoiceOfFormat;
/**
 * The Class CalcServlet.
 */
/**
 * @author balandina-o
 *
 */
@WebServlet(name = "CalcServlet", urlPatterns = {"/calc"})
public class CalcServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String resultat;
	private Boolean emptyOrNot;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
	
	    if (request.getSession().getAttribute("role") == null) {
			response.sendRedirect(request.getContextPath() + "/autho");
			return;
		} else if(request.getSession().getAttribute("role").equals("EMPTY")){ 
			response.sendRedirect(request.getContextPath() + "/autho");
			return;
		} else {
			getServletContext().getRequestDispatcher("/WEB-INF/Calc.jsp").forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String kadastr, tax, square, part, period, childrens, benefit, regionIndex, propertyIndex;

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

		String errorsList = valid.validate();
		if (errorsList != "") { // если строка ошибок не пуста
			request.setAttribute("errorsCalc", errorsList); // установить их на форму

		}else { // иначе получить посчитанный результат и поставить его на форму
			resultat = valid.getResult();
			request.setAttribute("result", resultat + " руб.");
			request.setAttribute("errorsCalc", "noMessage"); 
		}
		}

		if (request.getParameter("pdfButton") != null) { // если нажата кнопка генерации док-та
			byte[] docInBytes;
			emptyOrNot = request.getParameter("result") != "";
			ChoiceOfFormat choice = new ChoiceOfFormat(
							emptyOrNot ? kadastr : "---",
							emptyOrNot ? tax : "---",
							emptyOrNot ? square : "---",
							emptyOrNot ? part : "---",
							emptyOrNot ? period : "---",
							emptyOrNot ? childrens : "---",
							emptyOrNot ? benefit : "---",
							emptyOrNot ? resultat : "0 руб."
						);
		
			if (formatDoc.equals(".pdf")) {
				docInBytes = choice.generatePDF(formatDoc);
				
			}else {
				docInBytes = choice.generateDOC(formatDoc);
			}
			
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment; filename=DocumentGroup2" + formatDoc);
				
				try (OutputStream out = response.getOutputStream()) {
					out.write(docInBytes);
					response.flushBuffer();

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				return;
		}
		
		if (request.getParameter("exitButton") != null) { // если нажата кнопка выхода из аккаунта
			request.setAttribute("errorsCalc", "noMessage");
			
			if (!request.getSession().getAttribute("role").equals("ADMIN")) {
				UtilServlets.clearAll();
				request.getSession().removeAttribute("role");
				request.getSession().invalidate();
			}
			response.sendRedirect(request.getContextPath() + "/autho");
			return;
		}
		getServletContext().getRequestDispatcher("/WEB-INF/Calc.jsp").forward(request, response);
	}
}

	
