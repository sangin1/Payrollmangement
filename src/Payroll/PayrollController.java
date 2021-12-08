package Payroll;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Emp.EmpVO;
import Login.LoginVO;

 

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/Payroll/*")
public class PayrollController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	PayrollDAO payDAO;
	
	public void init(){
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
		int i;
		String check = "1";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo(); 
		HttpSession session = request.getSession();
		
		if (action == null || action.equals("/Pay.do")) {
			LoginVO masterVO = new LoginVO();
			masterVO = (LoginVO) session.getAttribute("msg");	
			LocalDate now = LocalDate.now();
			String yearNow = Integer.toString(now.getYear());
			String monNow = Integer.toString(now.getMonthValue());
			List<EmpVO> empInfo;
			if(masterVO.getMaster().equals("0")) {
				empInfo = payDAO.Members(yearNow,monNow,session,"0");
				i = payDAO.check(masterVO.getIndex(),masterVO.getC_id());
				if(i==0) {
					check="0";
				}				
			}else {
				empInfo = payDAO.Members(yearNow,monNow,session,"1");
			}
			
			request.setAttribute("year", yearNow);
			request.setAttribute("mon", monNow);
			
			
			List<PayrollVO> payInfo = new ArrayList<PayrollVO>();
			
		    payInfo=payDAO.payMembers(yearNow,monNow,session,empInfo);		    		    
			request.setAttribute("payInfo", payInfo);
			
			request.setAttribute("check", check);
			
			nextPage = "/Pay/Paysearch.jsp";
		}
		else if(action.equals("/search.do")) {
			String yearNow;
			String monNow;
			LoginVO masterVO = new LoginVO();
			masterVO = (LoginVO) session.getAttribute("msg");	
			
			yearNow = request.getParameter("yearS");
			monNow = request.getParameter("monS");
			request.setAttribute("year", yearNow);
			request.setAttribute("mon", monNow); 
			List<EmpVO> empInfo;
			if(masterVO.getMaster().equals("0")) {
				empInfo = payDAO.Members(yearNow,monNow,session,"0");
				i = payDAO.check(masterVO.getIndex(),masterVO.getC_id());
				if(i==0) {
					check="0";
				}				
			}else {
				empInfo = payDAO.Members(yearNow,monNow,session,"1");
			}
			List<PayrollVO> payInfo = new ArrayList<PayrollVO>();
			
		    payInfo=payDAO.payMembers(yearNow,monNow,session,empInfo);		    		    
			request.setAttribute("payInfo", payInfo);
			request.setAttribute("check", check);
			
			nextPage = "/Pay/Paysearch.jsp";
		}
		else if(action.equals("/update.do")) {
			String yearNow = request.getParameter("pageYear");
			String monNow = request.getParameter("pageMon");
			String pay_id = request.getParameter("pay_id");
			String emp_id = request.getParameter("emp_id");
			String pay = request.getParameter("pay");
			String tax = request.getParameter("tax");
			String incometax = request.getParameter("incometax");
			String outpay = request.getParameter("outpay");
			String bonus = request.getParameter("bonus");
			String insurance = request.getParameter("insurance");
			String subsidy = request.getParameter("subsidy");	
			if(pay.equals(""))
				pay = "0";
			if(tax.equals(""))
				tax = "0";
			if(incometax.equals(""))
				incometax = "0";
			if(outpay.equals(""))
				outpay = "0";
			if(bonus.equals(""))
				bonus = "0";
			if(insurance.equals(""))
				insurance = "0";
			if(subsidy.equals(""))
				subsidy = "0";
			int totalInt = Integer.parseInt(pay)-Integer.parseInt(tax)-Integer.parseInt(incometax)+Integer.parseInt(outpay)+Integer.parseInt(bonus)-
						Integer.parseInt(insurance)+Integer.parseInt(subsidy);
			String total = Integer.toString(totalInt);
			String date = yearNow+"-"+monNow+"-01";
			
			LoginVO masterVO = new LoginVO();
			masterVO = (LoginVO) session.getAttribute("msg");
			
			PayrollVO payVO = new PayrollVO(pay_id,masterVO.getC_id(),emp_id,date,pay,tax,incometax,outpay,bonus,insurance,subsidy,total);
			payDAO.addPay(payVO, yearNow, monNow);
			
			request.setAttribute("year", yearNow);
			request.setAttribute("mon", monNow); 
			List<EmpVO> empInfo = payDAO.Members(yearNow,monNow,session,"1");
			List<PayrollVO> payInfo = new ArrayList<PayrollVO>();
			
		    payInfo=payDAO.payMembers(yearNow,monNow,session,empInfo);		    		    
			request.setAttribute("payInfo", payInfo);
			
			nextPage = "/Pay/Paysearch.jsp";
		}
		else if(action.equals("/xlsx.do")) { 
		         	    
			nextPage = "/Pay/Paysearch.jsp";
		}
		else if(action.equals("/severance.do")) { 
			LoginVO masterVO = new LoginVO();
			masterVO = (LoginVO) session.getAttribute("msg");	
			
			List<EmpVO> empInfo;
			if(masterVO.getMaster().equals("0")) {
				empInfo = payDAO.serMembers(session,"0");
				i = payDAO.check(masterVO.getIndex(),masterVO.getC_id());
				if(i==0) {
					check="0";
				}				
			}else {
				empInfo = payDAO.serMembers(session,"1");
			}
			List<PayrollVO> payInfo = new ArrayList<PayrollVO>();
			
		    payInfo=payDAO.serPayMembers(session,empInfo);		    		    
			request.setAttribute("payInfo", payInfo);
			request.setAttribute("check", check);
			nextPage = "/Pay/severancePay.jsp";
		}
		else {
			LoginVO masterVO = new LoginVO();
			masterVO = (LoginVO) session.getAttribute("msg");	
			LocalDate now = LocalDate.now();
			String yearNow = Integer.toString(now.getYear());
			String monNow = Integer.toString(now.getMonthValue());
			List<EmpVO> empInfo;
			
			if(masterVO.getMaster().equals("0")) {
				empInfo = payDAO.Members(yearNow,monNow,session,"0");
				i = payDAO.check(masterVO.getIndex(),masterVO.getC_id());
				if(i==0) {
					check="0";
				}				
			}else {
				empInfo = payDAO.Members(yearNow,monNow,session,"1");
			}
			
			request.setAttribute("year", yearNow);
			request.setAttribute("mon", monNow);
			
			
			List<PayrollVO> payInfo = new ArrayList<PayrollVO>();
			
		    payInfo=payDAO.payMembers(yearNow,monNow,session,empInfo);		    		    
			request.setAttribute("payInfo", payInfo);
			
			request.setAttribute("check", check);
			
			nextPage = "/Pay/Paysearch.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}