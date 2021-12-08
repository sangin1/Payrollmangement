package Annual;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import Emp.EmpVO;
import Login.LoginVO;


public class AnnualDAO {
	public AnnualDAO() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e1) { 
			e1.printStackTrace();
		}
	}
	public void add(AnnualVO A) {
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3307/paydb","root","1234");
			Statement stmt = conn.createStatement();
		){ 		
			stmt.executeUpdate(String.format("insert into annual(c_id, emp_id, date, reason, checks) value (%s, %s, '%s', '%s',%s)",
					A.getC_id(),A.getEmp_id(),A.getDate(),A.getReason(),A.getCheck()));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String searchEmp(String c_id,String index) {
		String emp_id = "0";
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3307/paydb","root","1234");
			Statement stmt = conn.createStatement();
			 
			ResultSet rs = stmt.executeQuery(String.format("select * from employee where c_id = %s and id_index = %s",
					c_id,index));
		){
			while(rs.next()){ 
				emp_id = rs.getString("emp_id");			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return emp_id;
	}
	public List<AnnualVO> annlist (HttpSession session,String emp_id){
		String ann_id="";
		String date="";
		String reason="";
		String check="";
		String name="";
		String pos_name="";
		LoginVO masterVO = new LoginVO();
		masterVO = (LoginVO) session.getAttribute("msg");
		AnnualVO annVO;
		List<AnnualVO> annlist = new ArrayList<AnnualVO>();
		if(masterVO.getMaster().equals("0")) {
			try(Connection conn = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3307/paydb","root","1234");
				Statement stmt = conn.createStatement();
				 
				ResultSet rs = stmt.executeQuery(String.format("select employee.name, employee.pos_name, annual.date, annual.reason, annual.checks,annual.ann_id  "
						+ "from annual inner join employee on annual.emp_id = employee.emp_id "
						+ "where annual.emp_id = %s and annual.c_id = %s ORDER BY ann_id desc",
						emp_id, masterVO.getC_id()));
			){
				while(rs.next()){ 
					ann_id = rs.getString("ann_id");	
					date = rs.getString("date");
					reason = rs.getString("reason");
					check = rs.getString("checks");
					name = rs.getString("name");
					pos_name = rs.getString("pos_name");
					annVO = new AnnualVO(ann_id,masterVO.getC_id(),date,reason,check,name,pos_name);
					annlist.add(annVO);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else {
			try(Connection conn = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3307/paydb","root","1234");
				Statement stmt = conn.createStatement();
				 
				ResultSet rs = stmt.executeQuery(String.format("select employee.name, employee.pos_name, annual.date, annual.reason, annual.checks,annual.ann_id "
						+ "from annual inner join employee on annual.emp_id = employee.emp_id "
						+ "where annual.c_id = %s ORDER BY ann_id desc",
						masterVO.getC_id(),emp_id));
			){
				while(rs.next()){ 
					ann_id = rs.getString("ann_id");	
					date = rs.getString("date");
					reason = rs.getString("reason");
					check = rs.getString("checks");
					name = rs.getString("name");
					pos_name = rs.getString("pos_name");
					annVO = new AnnualVO(ann_id,masterVO.getC_id(),date,reason,check,name,pos_name);
					annlist.add(annVO);			
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return annlist;
	}
	public void enrollAnn(String ann_id,String check) {
		if(check.equals("비승인")) {
			try(Connection conn = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3307/paydb","root","1234"); 			
					PreparedStatement pstmt = conn.prepareStatement(String.format("update annual set checks = 1 where ann_id = %s",
							ann_id));				
			){ 		 
				pstmt.executeUpdate();   
				 
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public String checkAnnNum(EmpVO emp_id,String year,String mon,String day) {
		int num = 0;
		int num2 = 0;
		String[] emp_date = emp_id.getDate().split("-");
		int empday=(Integer.parseInt(emp_date[1])*100)+Integer.parseInt(emp_date[2]);
		int checkday = (Integer.parseInt(mon)*100)+Integer.parseInt(day);
		int checkyear = Integer.parseInt(year);
		int totalyear=0;
		int totalann=0,i;
		if(empday > checkday) {
			try(Connection conn = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3307/paydb","root","1234");
				Statement stmt = conn.createStatement();
				 
				ResultSet rs = stmt.executeQuery(String.format("select * from annual where emp_id = %s "
						+ "and date >= str_to_date('%s-%s-%s', '%%Y-%%m-%%d') and date < str_to_date('%s-%s-%s', '%%Y-%%m-%%d') "
						+ "and (checks = 1 or checks = 2)",
						emp_id,Integer.toString(checkyear-1),emp_date[1],emp_date[2],year,emp_date[1],emp_date[2]));
			){
				while(rs.next()){ 
					num++;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else {
			try(Connection conn = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3307/paydb","root","1234");
				Statement stmt = conn.createStatement();
				 
				ResultSet rs = stmt.executeQuery(String.format("select * from annual where emp_id = %s "
						+ "and date >= str_to_date('%s-%s-%s', '%%Y-%%m-%%d') and date < str_to_date('%s-%s-%s', '%%Y-%%m-%%d') "
						+ "and (checks = 1 or checks = 2)",
						emp_id.getIndex(),year,emp_date[1],emp_date[2],Integer.toString(checkyear+1),emp_date[1],emp_date[2]));
			){
				while(rs.next()){ 
					num++;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		totalyear = Integer.parseInt(year) - Integer.parseInt(emp_date[0]);
		if(totalyear==0) {
			totalann=11;
		}else {
			totalann=15;
			if(totalyear>=3) {
				for(i=3;i<totalyear;i+=2) {
					totalann+=1;
				}
			}
		}
		if(totalyear>25)
			totalyear=25;
			
		num2 = totalann-num;
		
		if(num2<0)
			num2=0;
		
		return Integer.toString(num2);
	}
}
