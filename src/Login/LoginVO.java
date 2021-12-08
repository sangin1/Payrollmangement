package Login;

import java.sql.Date;

public class LoginVO {
	private String index;
	private String id;
	private String pwd;
	private String name; 
	private String c_id;
	private String master;
	
	public LoginVO() {
		super();
	}
	public LoginVO(String index) {
		super();
		this.index = index;
	}
	public LoginVO(String id, String pwd, String name, String c_id, String master) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.c_id = c_id;
		this.master = master;
	}
	public LoginVO(String index, String id, String pwd, String name, String c_id, String master) {
		super();
		this.index = index;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.c_id = c_id;
		this.master = master;
	}
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getName() {
		return name;
	} 
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
}
