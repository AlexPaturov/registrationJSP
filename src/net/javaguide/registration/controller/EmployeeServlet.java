package net.javaguide.registration.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javaguide.registration.dao.EmployeeDao;
import net.javaguide.registration.model.Employee;

/**
 * Servlet implementation class EmployeeServlet
 */
//@WebServlet("/EmployeeServlet") // не понятно, какую роль здесь играет аннотация ?
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private EmployeeDao employeeDao = new EmployeeDao(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// здесь мы получаем данные с сервера
		response.getWriter().append("Served at: ").append(request.getContextPath()); // пишем что-то в ответ клиенту
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/employeeregister.jsp"); // передаём адрес на который мы хотим перевести пользователя
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String contact = request.getParameter("contact");
		
		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setUsername(username);
		employee.setPassword(password);
		employee.setAddress(address);
		employee.setContact(contact);
		
		try {
			employeeDao.registerEmployee(employee);
		} catch (ClassNotFoundException e) {
			// при ошибке сохранения - сделать форму с информацией об ошибке и делать возврат на форму ввода 
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/employeedetail.jsp"); // это сейчас вызывается всегда, а должно только при успешном сохранении
		dispatcher.forward(request, response);
	
	}

}
