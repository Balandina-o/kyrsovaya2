package servlets;

import org.junit.Before;
import org.junit.Test;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import static org.mockito.Mockito.*;

/**
 * Класс CalcServletTest - класс, тестирующий класс CalcServlet
 * С задействованием фреймворка Mockito
 * @author balandina-o
 * @version 1.0
 */
public class CalcServletTest {
	private static HttpServletRequest request;
	private static HttpServletResponse response;
	private static CalcServlet servlet;

	@Before
	public final void setUp() {
		
		request = mock(HttpServletRequest.class); //имитация Request от фреймворка Mockito		
		response = mock(HttpServletResponse.class); //имитация Response
		ServletContext servletContext = mock(ServletContext.class);
		HttpSession session = mock(HttpSession.class); //
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);// Имитация перенаправления

		when(request.getParameter("kadastr")).thenReturn("335000");
		when(request.getParameter("tax")).thenReturn("123");
		when(request.getParameter("square")).thenReturn("45");
		when(request.getParameter("part")).thenReturn("1");
		when(request.getParameter("period")).thenReturn("12");
		when(request.getParameter("childrens")).thenReturn("1");
		when(request.getParameter("benefit")).thenReturn("1");
		when(request.getParameter("regionIndex")).thenReturn("10");
		when(request.getParameter("propertyIndex")).thenReturn("0");

		when(request.getParameter("warnings")).thenReturn("0");
		when(request.getParameter("button")).thenReturn("pdfButton");
		
		when(request.getSession()).thenReturn(session);
		//when(request.getSession()).getAttribute("role").thenReturn(true);
		//when(request.getSession()).getAttribute("logged").thenReturn("admin");
		
		when(servletContext.getRequestDispatcher("/Calc.jsp")).thenReturn(dispatcher);

		servlet = new CalcServlet() {
			private static final long serialVersionUID = 1L;

			public ServletContext getServletContext() {
				return servletContext; // вернуть mock
			}
		};

	}

	/**
	 * Тест проверяет, что метод doPost выбрасывает исключение,
	 * когда ему переданы нулевые аргументы
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test(expected = NullPointerException.class)
	public final void testDoPostPositive() throws ServletException, IOException {
		servlet.doPost(null, null);
	}

	/**
	 * Тест проверяет, что метод doPost, при передаче ему верных параметров, обращается
	 * к необходимым для вычислений полям по одному разу и получает их значения, а значит
	 * выполняет свои функции
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public final void testFrequencyPositive() throws ServletException, IOException {

		servlet.doPost(request, response);
		verify(request, times(1)).getParameter("kadastr");
		verify(request, times(1)).getParameter("tax");
		verify(request, times(1)).getParameter("square");
		verify(request, times(1)).getParameter("part");
		verify(request, times(1)).getParameter("period");
		verify(request, times(1)).getParameter("childrens");
		verify(request, times(1)).getParameter("benefit");

		verify(request, times(1)).getParameter("propertyIndex");
		verify(request, times(1)).getParameter("regionIndex");

	}

}