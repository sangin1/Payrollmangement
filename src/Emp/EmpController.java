package Emp;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Login.LoginVO; 
 

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/Emp/*")
public class EmpController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	EmpDAO empDAO;
	
	public void init(){
		empDAO = new EmpDAO();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		HttpSession session = request.getSession();
		if (action == null || action.equals("/manager.do")) {
			nextPage = "/Empl/empManager.jsp";
		}
		else if (action.equals("/App.do")) {
			String c_id = request.getParameter("c_id");
			String name = request.getParameter("name"); 	
			String pos_name = request.getParameter("pos_name");
			String date = request.getParameter("date"); 
			EmpVO empVO = new EmpVO(c_id,name,pos_name,date);
			empDAO.add(empVO);
			nextPage = "/Empl/empManager.jsp";
		}
		else if (action.equals("/Del.do")) {
			String c_id = request.getParameter("c_id");
			String name = request.getParameter("name"); 
			empDAO.del(c_id,name);
			nextPage = "/Empl/empManager.jsp";
		}
		else if (action.equals("/Check.do")) {
			List<LoginVO> membersList = empDAO.listMembers(session);
			request.setAttribute("membersList", membersList);
			nextPage = "/Empl/empCheck.jsp";
		}
		else if (action.equals("/Enroll.do")) {
			String emp_id = request.getParameter("emp_id");
			String id_index = request.getParameter("id_index");
			String name = request.getParameter("name");
			String pos_name = request.getParameter("pos_name");
			empDAO.modEmp(emp_id, id_index, name, pos_name);
			nextPage = "/Empl/empManager.jsp";
		}
		else if(action.equals("/EnrollApp.do")) {
			nextPage = "/Empl/empApp.jsp";
		}
		else if(action.equals("/AppForm.do")) {
			nextPage = "/Empl/empApp.jsp";
		}
		else if(action.equals("/DelForm.do")) {
			nextPage = "/Empl/empDel.jsp";
		}
		else if(action.equals("/EnrollForm.do")) {			
		    List<EmpVO> empInfo = empDAO.empMembers(session);
		    request.setAttribute("empInfo", empInfo);
			nextPage = "/Empl/empEnroll.jsp";
		}
		else {
			nextPage = "/Empl/empManager.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}
