package Login;
 
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/Login/*")
public class LoginController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	LoginDAO loginDAO;
	
	public void init(){
		loginDAO = new LoginDAO();
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
		HttpSession session = request.getSession();
		String action = request.getPathInfo();
		if (action == null || action.equals("/loginMain.do")) {
			nextPage = "/main-login.jsp";
		} 
		else if (action.equals("/addMaster.do")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String c_id = request.getParameter("c_id");
			String master = "1";
			LoginVO loginVO = new LoginVO(id, pwd, name, c_id, master);
			String checkID = loginDAO.addMember(loginVO);
			request.setAttribute("check", checkID);
			nextPage = "/Login/loginmain.do";
		} 
		else if (action.equals("/addNoMaster.do")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String c_id = request.getParameter("c_id");
			String master = "0";
			LoginVO loginVO = new LoginVO(id, pwd, name, c_id, master);
			String checkID = loginDAO.addMember(loginVO);
			request.setAttribute("check", checkID);
			nextPage = "/Login/loginmain.do";
		}
		else if (action.equals("/login.do")) { 		
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd"); 	
			LoginVO loginVO = loginDAO.login(id,pwd);						
			session.setAttribute("msg",loginVO);
			nextPage = "/menu.jsp"; 
		} 
		else if(action.equals("/logout.do")) {
			session.removeAttribute("msg");
			nextPage = "/main-login.jsp"; 
		}
		else if (action.equals("/memberForm.do")) {
			nextPage = "/membership/cho_master.jsp";
		}
		else if (action.equals("/masterForm.do")) {
			nextPage = "/membership/masterForm.jsp";
		}
		else if (action.equals("/noMasterForm.do")) {
			nextPage = "/membership/noMasterForm.jsp";
		}
		else if (action.equals("/masterCpCheck.do")) {			
			CompanyVO check = loginDAO.check_cp(request.getParameter("id"));
			if(check.getC_id().equals("0") == true) {
				CompanyVO check2 = new CompanyVO(request.getParameter("id"));
				loginDAO.addCompany(check2);
				CompanyVO com = loginDAO.check_cp(request.getParameter("id"));
				request.setAttribute("check", com);
			}
			request.setAttribute("check2", check);
			nextPage = "/membership/masterForm2.jsp";
		}
		else if (action.equals("/noMasterCpCheck.do")) {			
			CompanyVO check = loginDAO.check_cp(request.getParameter("id"));
			request.setAttribute("check", check);
			System.out.println(check.getC_id() + check.getC_name());
			nextPage = "/membership/noMasterForm2.jsp";
		}
		else {
			nextPage = "/main-login.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}
