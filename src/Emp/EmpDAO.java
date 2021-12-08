package Emp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import Login.LoginVO;

public class EmpDAO {
	public EmpDAO() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e1) { 
			e1.printStackTrace();
		}
	}
	public void add(EmpVO L) {
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3307/paydb","root","1234");
			Statement stmt = conn.createStatement();
		){ 		
			stmt.executeUpdate(String.format("insert into employee(c_id, name, pos_name, emp_date) value (%s, '%s', '%s', '%s')",
					L.getC_id(),L.getName(),L.getPos_name(),L.getDate()));
			System.out.println(L.getC_id()+L.getName()+L.getPos_name()+L.getDate());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void del(String c_id,String name) {
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3307/paydb","root","1234");
				Statement stmt = conn.createStatement();
				
		){ 		
			stmt.execute(String.format("delete from employee where c_id = %s and name = '%s'",
					c_id,name));
			/*PreparedStatement pstmt = conn.prepareStatement(String.format("detele from employee where c_id = %s and name = '%s'",
					c_id,name));
			pstmt.executeUpdate();  */
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public List<LoginVO> listMembers(HttpSession session) {
		String index = "0";
		String id1 = "0";
		String pw1 = "0";
		String name = "0";
		String c_id = "0";
		String master="0";
		LoginVO masterVO = new LoginVO();
		masterVO = (LoginVO) session.getAttribute("msg");		
		List<LoginVO>  membersList = new ArrayList();
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3307/paydb","root","1234");
			Statement stmt = conn.createStatement();
			 
			ResultSet rs = stmt.executeQuery(String.format("select * from login where c_id = %s and master = 0",
					masterVO.getC_id()));
		){
			while(rs.next()){ 
				index = rs.getString("id_index");
				id1 = rs.getString("id"); 
				pw1 = rs.getString("pw"); 
				name = rs.getString("name"); 
				c_id = rs.getString("c_id"); 
				master = rs.getString("master"); 
				LoginVO memberVO = new LoginVO(index,id1,pw1,name,c_id,master); 
				membersList.add(memberVO); 				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return membersList;
	}
	public List<EmpVO> empMembers(HttpSession session) {
		String index = "0";
		String id_index = "0";
		String c_id = "0";
		String name = "0";
		String pos_name = "0";
		String date = "0"; 
		LoginVO masterVO = new LoginVO();
		masterVO = (LoginVO) session.getAttribute("msg");		
		List<EmpVO>  membersList = new ArrayList();
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3307/paydb","root","1234");
			Statement stmt = conn.createStatement();
			 
			ResultSet rs = stmt.executeQuery(String.format("select * from employee where c_id = %s",
					masterVO.getC_id()));
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
		return membersList;
	}
	public void modEmp(String emp_id,String id_index ,String name ,String pos_name) {
		if(id_index!="") {
			try(Connection conn = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3307/paydb","root","1234");
					//Statement stmt = conn.createStatement();					
					PreparedStatement pstmt = conn.prepareStatement(String.format("update employee set id_index = %s, name = '%s', pos_name = '%s' where emp_id = %s",
							id_index,name,pos_name,emp_id));				
			){ 		
				/*stmt.execute(String.format("update employee set id_index = %s, name = '%s', pos_name = '%s' where emp_id = %s",
						id_index,name,pos_name,emp_id));*/
				pstmt.executeUpdate();   
				 
			}catch(Exception e){
				e.printStackTrace();
			}
		}else {
			try(Connection conn = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3307/paydb","root","1234");
					//Statement stmt = conn.createStatement();					
					PreparedStatement pstmt = conn.prepareStatement(String.format("update employee set name = '%s', pos_name = '%s' where emp_id = %s",
							name,pos_name,emp_id));				
			){ 		
				/*stmt.execute(String.format("update employee set id_index = %s, name = '%s', pos_name = '%s' where emp_id = %s",
						id_index,name,pos_name,emp_id));*/
				pstmt.executeUpdate();   
				 
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
