package Payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import Emp.EmpVO;
import Login.LoginVO;

public class PayrollDAO {
	@SuppressWarnings("unused")
	public PayrollDAO() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e1) { 
			e1.printStackTrace();
		}
	}
	
	public List<PayrollVO> payMembers(String year, String mon, HttpSession session, List<EmpVO> empInfo){
		String pay_id;
		String c_id;
		String emp_id;
		String date;
		String pay;
	    String tax;
		String incometax;
		String outpay;
		String bonus;
		String insurance;
		String subsidy;
		String total;
		int i;
		LoginVO masterVO = new LoginVO();
		masterVO = (LoginVO) session.getAttribute("msg");
		List<PayrollVO>	payVOlist = new ArrayList<PayrollVO>();
		
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3307/paydb","root","1234");
			Statement stmt = conn.createStatement();			
		){			
			for(i=0;i<empInfo.size();i++) {
				ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM payroll WHERE DATE <= STR_TO_DATE('%s-%s-31', '%%Y-%%m-%%d') and DATE >= STR_TO_DATE('%s-%s-01', '%%Y-%%m-%%d') "
						+ "and c_id = %s and emp_id = %s;",year,mon,year,mon,masterVO.getC_id(),empInfo.get(i).getIndex()));
				if(rs.next()) {
					pay_id = rs.getString("pay_id");
					c_id = rs.getString("c_id");
					emp_id = rs.getString("emp_id");
					date = rs.getString("date"); 
					if(rs.getString("pay")==null)
						pay = "0";
					else
						pay = rs.getString("pay");
					if(rs.getString("tax")==null)
						tax = "0";
					else
						tax = rs.getString("tax");
					if(rs.getString("incometax")==null)
						incometax = "0";
					else
						incometax = rs.getString("incometax");
					if(rs.getString("outpay")==null)
						outpay = "0";
					else
						outpay = rs.getString("outpay");
					if(rs.getString("bonus")==null)
						bonus = "0";
					else
						bonus = rs.getString("bonus");
					if(rs.getString("insurance")==null)
						insurance = "0";
					else
						insurance = rs.getString("insurance");
					if(rs.getString("subsidy")==null)
						subsidy = "0";
					else
						subsidy = rs.getString("subsidy");
					if(rs.getString("total")==null)
						total = "0";
					else
						total = rs.getString("total");
					
					PayrollVO payVO = new PayrollVO(pay_id,c_id, emp_id,  date, pay, tax, incometax, outpay, bonus, insurance, subsidy,
							empInfo.get(i).getIndex(),empInfo.get(i).getName(),empInfo.get(i).getPos_name(),total);
					payVOlist.add(payVO);
				}else {
					PayrollVO payVO = new PayrollVO(empInfo.get(i).getIndex(),empInfo.get(i).getName(),empInfo.get(i).getPos_name());
					payVOlist.add(payVO);
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return payVOlist;
	}
	public List<EmpVO> Members(String year,String mon,HttpSession session,String check) {
		String index = "0";
		String id_index = "0";
		String c_id = "0";
		String name = "0";
		String pos_name = "0";
		String date = "0"; 
		LoginVO masterVO = new LoginVO();
		masterVO = (LoginVO) session.getAttribute("msg");		
		List<EmpVO>  membersList = new ArrayList<EmpVO>();
		if(check.equals("1")) {
			try(Connection conn = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3307/paydb","root","1234");
				Statement stmt = conn.createStatement();
					
				ResultSet rs = stmt.executeQuery(String.format("select * from employee where c_id = %s and  emp_date <= str_to_date('%s-%s', '%%Y-%%m')"
						,masterVO.getC_id(),year,mon));
			){
				while(rs.next()){ 
					index = rs.getString("emp_id");
					id_index = rs.getString("id_index");
					c_id = rs.getString("c_id"); 
					name = rs.getString("name"); 
					pos_name = rs.getString("pos_name"); 
					date = rs.getString("emp_date");  
					EmpVO empVO = new EmpVO(index,id_index,c_id,name,pos_name,date); 
					membersList.add(empVO); 				
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else {
			try(Connection conn = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3307/paydb","root","1234");
				Statement stmt = conn.createStatement();
					
				ResultSet rs = stmt.executeQuery(String.format("select * from employee where c_id = %s and  emp_date <= str_to_date('%s-%s', '%%Y-%%m') and id_index = %s"
						,masterVO.getC_id(),year,mon,masterVO.getIndex()));
			){
				while(rs.next()){ 
					index = rs.getString("emp_id");
					id_index = rs.getString("id_index");
					c_id = rs.getString("c_id"); 
					name = rs.getString("name"); 
					pos_name = rs.getString("pos_name"); 
					date = rs.getString("emp_date");  
					EmpVO empVO = new EmpVO(index,id_index,c_id,name,pos_name,date); 
					membersList.add(empVO); 				
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return membersList;
	}
	public void addPay(PayrollVO P,String year,String mon) {
		
		if(P.getPay_id().equals("")||P.getPay_id()==null) {
			try(Connection conn = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3307/paydb","root","1234");
				Statement stmt = conn.createStatement();
			){ 		
				stmt.executeUpdate(String.format("insert into payroll(c_id,emp_id,date,pay,tax,incometax,outpay,bonus,insurance,subsidy,total) "
						+ "value (%s, %s, '%s', '%s', '%s', '%s','%s','%s', '%s', '%s', '%s')",
						P.getC_id(),P.getEmp_id(),P.getDate(),P.getPay(),P.getTax(),P.getIncometax(),P.getOutpay(),P.getBonus(),P.getInsurance(),P.getSubsidy(),P.getTotal()));
			}catch(Exception e){
				e.printStackTrace();
			}
		}else {
			try(Connection conn = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3307/paydb","root","1234");		
					PreparedStatement pstmt = conn.prepareStatement(String.format("update payroll set date = '%s',pay = '%s',tax = '%s',incometax = '%s',outpay = '%s',"
							+ "bonus = '%s',insurance = '%s',subsidy = '%s',total = '%s' where pay_id = %s",
							P.getDate(),P.getPay(),P.getTax(),P.getIncometax(),P.getOutpay(),P.getBonus(),P.getInsurance(),P.getSubsidy(),P.getTotal(),P.getPay_id()));				
			){ 		
				pstmt.executeUpdate();   
				 
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}
	public int check(String index,String c_id) {
		int check=0;
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3307/paydb","root","1234");
			Statement stmt = conn.createStatement();
				
			ResultSet rs = stmt.executeQuery(String.format("select * from employee where c_id = %s and id_index = %s"
					,c_id,index));
		){
			if(rs.next()){ 
				check++;				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return check;
	}
	public List<EmpVO> serMembers(HttpSession session,String check) {
		String index = "0";
		String id_index = "0";
		String c_id = "0";
		String name = "0";
		String pos_name = "0";
		String date = "0"; 
		LoginVO masterVO = new LoginVO();
		masterVO = (LoginVO) session.getAttribute("msg");		
		List<EmpVO>  membersList = new ArrayList<EmpVO>();
		if(check.equals("1")) {
			try(Connection conn = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3307/paydb","root","1234");
				Statement stmt = conn.createStatement();
					
				ResultSet rs = stmt.executeQuery(String.format("select * from employee where c_id = %s"
						,masterVO.getC_id()));
			){
				while(rs.next()){ 
					index = rs.getString("emp_id");
					id_index = rs.getString("id_index");
					c_id = rs.getString("c_id"); 
					name = rs.getString("name"); 
					pos_name = rs.getString("pos_name"); 
					date = rs.getString("emp_date");  
					EmpVO empVO = new EmpVO(index,id_index,c_id,name,pos_name,date); 
					membersList.add(empVO); 				
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else {
			try(Connection conn = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3307/paydb","root","1234");
				Statement stmt = conn.createStatement();
					
				ResultSet rs = stmt.executeQuery(String.format("select * from employee where c_id = %s and id_index = %s"
						,masterVO.getC_id(),masterVO.getIndex()));
			){
				while(rs.next()){ 
					index = rs.getString("emp_id");
					id_index = rs.getString("id_index");
					c_id = rs.getString("c_id"); 
					name = rs.getString("name"); 
					pos_name = rs.getString("pos_name"); 
					date = rs.getString("emp_date");  
					EmpVO empVO = new EmpVO(index,id_index,c_id,name,pos_name,date); 
					membersList.add(empVO); 				
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return membersList;
	}
	public List<PayrollVO> serPayMembers(HttpSession session, List<EmpVO> empInfo){
		String c_id="0";
		String emp_id="0";
		int serInt=0;
		String ser="0";
		String total="0";
		int i,check=0;
		PayrollVO payVO;
		LoginVO masterVO = new LoginVO();
		masterVO = (LoginVO) session.getAttribute("msg");
		List<PayrollVO>	payVOlist = new ArrayList<PayrollVO>();
		
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3307/paydb","root","1234");
			Statement stmt = conn.createStatement();			
		){			
			for(i=0;i<empInfo.size();i++) {
				serInt=0;
				check=0;
				ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM payroll WHERE c_id = %s and emp_id = %s",masterVO.getC_id(),empInfo.get(i).getIndex()));
				while(rs.next()) {
					check++;
					c_id = rs.getString("c_id");
					emp_id = rs.getString("emp_id");
					total = rs.getString("total");
					serInt += ((Integer.parseInt(total))/12);				
				}
				if(check==0){
					payVO = new PayrollVO(empInfo.get(i).getIndex(),empInfo.get(i).getName(),empInfo.get(i).getPos_name());
					
				}else {
					ser=Integer.toString(serInt);
					payVO = new PayrollVO(c_id, emp_id,empInfo.get(i).getIndex(),empInfo.get(i).getName(),empInfo.get(i).getPos_name(),ser);
				}
				payVOlist.add(payVO);				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return payVOlist;
	}
}
