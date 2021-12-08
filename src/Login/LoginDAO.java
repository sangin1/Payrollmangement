package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginDAO {
	private PreparedStatement pstmt;
	public LoginDAO() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e1) { 
			e1.printStackTrace();
		}
	}
	public void addLogin(LoginVO L) {
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3307/paydb","root","1234");
			Statement stmt = conn.createStatement();
		){ 		
			stmt.executeUpdate(String.format("insert into login(id, pw, name, c_id, master) value ('%s', '%s', '%s', %s, %s)",
					L.getId(),L.getPwd(),L.getName(),L.getC_id(),L.getMaster()));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public LoginVO login(String id, String pw) {
		String index = "0";
		String id1 = "0";
		String pw1 = "0";
		String name = "0";
		String c_id = "0";
		String master="0";
		LoginVO login;
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3307/paydb","root","1234");
			Statement stmt = conn.createStatement();
			 
			ResultSet rs = stmt.executeQuery(String.format("select * from login where ID='%s' and PW='%s'",
					id,pw));
		){
			if(rs.next()){ 
				index = rs.getString("id_index");
				id1 = rs.getString("id"); 
				pw1 = rs.getString("pw"); 
				name = rs.getString("name"); 
				c_id = rs.getString("c_id"); 
				master = rs.getString("master"); 
				login = new LoginVO(index,id1,pw1,name,c_id,master); 
				System.out.println(master);
				return login;
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		login = new LoginVO("0");
		return login;
	}
	public CompanyVO check_cp(String c_name) {
		String c_id = "0";
		String name = "0";
		CompanyVO companyVO;
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3307/paydb","root","1234");
			Statement stmt = conn.createStatement();
			 
			ResultSet rs = stmt.executeQuery(String.format("select * from company where c_name='%s'",
					c_name));
		){
			if(rs.next()){ 
				name = rs.getString("c_name");
				c_id = rs.getString("c_id");
				companyVO = new CompanyVO(c_id,name);
				return companyVO;
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		companyVO = new CompanyVO(c_id,name);
		return companyVO;
	}
	
	public String addMember(LoginVO m) {
		String check="0";
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3307/paydb","root","1234");
			Statement stmt = conn.createStatement();
			 
			ResultSet rs = stmt.executeQuery(String.format("select * from login where id='%s'",
					m.getId()));
		){
			if(rs.next()){ 
				check="1";
				return check;
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3307/paydb","root","1234");
			Statement stmt = conn.createStatement();
		){
			String id = m.getId();
			String pwd = m.getPwd();
			String name = m.getName();
			String c_id = m.getC_id();
			String master = m.getMaster();
			stmt.executeUpdate(String.format("insert into login(id, pw, name,c_id,master) value ('%s', '%s', '%s',%s,%s)",
					id,pwd,name,c_id,master));
		}catch(Exception e){
			e.printStackTrace();
		}
		return check;
	}
	
	public void addCompany(CompanyVO m){
		try(Connection conn = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3307/paydb","root","1234");
			Statement stmt = conn.createStatement();
		){
			String name = m.getC_name();
			stmt.executeUpdate(String.format("insert into company(c_name) value ('%s')",
					name));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
