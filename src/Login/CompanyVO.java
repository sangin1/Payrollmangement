package Login;

public class CompanyVO {
	private String c_id;
	private String c_name;
	
	
	public CompanyVO(String c_name) {
		super();
		this.c_name = c_name;
	}
	public CompanyVO(String c_id,String c_name) {
		super();
		this.c_name = c_name;
		this.c_id = c_id;
	}
	
	
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
}
