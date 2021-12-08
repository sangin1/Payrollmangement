package Annual;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Emp.EmpVO;
import Login.LoginVO;
import Payroll.PayrollDAO;

 

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/Ann/*")
public class AnnualController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	AnnualDAO annDAO;
	PayrollDAO payDAO;
	
	public void init(){
		annDAO = new AnnualDAO();
		payDAO = new PayrollDAO();
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
		String check2 = "1";
		int i;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		HttpSession session = request.getSession();
		if (action == null || action.equals("/manager.do")) {
			nextPage = "/ann/annManager.jsp";
		}
		else if (action.equals("/addAnn.do")) {
			nextPage = "/ann/addAnn.jsp";
		}
		else if (action.equals("/addAnnForm.do")) {
			LoginVO masterVO = new LoginVO();
			masterVO = (LoginVO) session.getAttribute("msg");
			String check = "0";
			String c_id =masterVO.getC_id();
			String emp_id="";
			String date = request.getParameter("date");
			String reason = request.getParameter("reason");
			if(masterVO.getMaster().equals("0")) {
				emp_id = annDAO.searchEmp(masterVO.getC_id(), masterVO.getIndex());
			}else {
				emp_id = request.getParameter("emp_id");
			}
			
			if(masterVO.getMaster().equals("0")) {
				check = "0";
			}else {
				check = "2";
			}
			
			AnnualVO annVO = new AnnualVO(c_id,emp_id,date,reason,check);
			annDAO.add(annVO);
			
			nextPage = "/ann/annManager.jsp";
		}
		else if(action.equals("/searchAnn.do")) {
			LoginVO masterVO = new LoginVO();
			masterVO = (LoginVO) session.getAttribute("msg");	
			LocalDate now = LocalDate.now();
			String yearNow = Integer.toString(now.getYear());
			String monNow = Integer.toString(now.getMonthValue());
			String dayNow = Integer.toString(now.getDayOfMonth());
			List<EmpVO> empInfo;
			List<String> num = new ArrayList<String>();
			if(masterVO.getMaster().equals("0")) {
				empInfo = payDAO.Members(yearNow,monNow,session,"0");
				i = payDAO.check(masterVO.getIndex(),masterVO.getC_id());
				if(i==0) {
					check2="0";
				}				
			}else {
				empInfo = payDAO.Members(yearNow,monNow,session,"1");
			}
			for(i=0;i<empInfo.size();i++) {
				num.add(annDAO.checkAnnNum(empInfo.get(i),yearNow,monNow,dayNow));
				empInfo.get(i).setNum(num.get(i));
			}
			request.setAttribute("empList", empInfo);				
			request.setAttribute("ann_num", num);
			
			nextPage = "/ann/searchAnn.jsp";
		}
		else if(action.equals("/Enroll.do")) {
			String ann_id = request.getParameter("ann_id");
			String check = request.getParameter("check");
			annDAO.enrollAnn(ann_id, check);
			nextPage = "/Ann/checkAnn.do";
		}
		else if(action.equals("/checkAnn.do")) {
			LoginVO masterVO = new LoginVO();
			masterVO = (LoginVO) session.getAttribute("msg");
			String emp_id = "0";
			if(masterVO.getMaster().equals("0")) {
				emp_id = annDAO.searchEmp(masterVO.getC_id(), masterVO.getIndex());
			}
			request.setAttribute("annList", annDAO.annlist(session,emp_id));
			nextPage = "/ann/checkAnn.jsp";
		}
		else {
			nextPage = "/ann/annManager.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}