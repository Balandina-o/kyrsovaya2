package servlets;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Класс AdminServletTest - класс, тестирующий класс AdminServlet
 * @author balandina-o
 * @version 1.0
 */
public class AdminServletTest {
	private static HttpServletRequest request;
	private static HttpServletResponse response;
	private static AdminServlet servlet;

	@Before
	public final void setUp() {

		request = mock(HttpServletRequest.class); //имитация Request от фреймворка Mockito		
		response = mock(HttpServletResponse.class); //имитация Response
		ServletContext servletContext = mock(ServletContext.class);
		HttpSession session = mock(HttpSession.class);
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);// Имитация перенаправления

		when(request.getParameter("coeffUfa")).thenReturn("1.0");
		when(request.getParameter("coeffKazan")).thenReturn("1.0");
		when(request.getParameter("coeffMoscow")).thenReturn("1.0");
		when(request.getParameter("coeffGorn")).thenReturn("1.0");
		when(request.getParameter("changeButton")).thenReturn("changeButton");
		when(request.getParameter("exitButton")).thenReturn("exitButton");

		when(servletContext.getRequestDispatcher("/WEB-INF/Dashboard.jsp")).thenReturn(dispatcher);
		
		when(request.getSession()).thenReturn(session);
		
		servlet = new AdminServlet() {
			private static final long serialVersionUID = 1L;

			public ServletContext getServletContext() {
				return servletContext; // вернуть mock
			}
		};}

	/**
	 * Тест проверяет, что методы doPost и doGet выбрасывают исключение,
	 * когда им переданы нулевые аргументы
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test(expected = NullPointerException.class)
	public final void testDoPostPositive() throws ServletException, IOException {
		servlet.doPost(null, null);

		servlet.doGet(null, null);
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
		verify(request, times(1)).getParameter("coeffUfa"); 
		verify(request, times(1)).getParameter("coeffKazan"); 
		verify(request, times(1)).getParameter("coeffMoscow"); 
		verify(request, times(1)).getParameter("coeffGorn"); 
	}
}