package Annual;

public class AnnualVO {
	private String ann_id;
	private String c_id;
	private String emp_id;
	private String date;
	private String reason;
	private String check;
	private String name;
	private String pos_name;
	
	public AnnualVO(String ann_id,String c_id,String emp_id,String date, String reason,String check) {
		this.ann_id = ann_id;
		this.c_id = c_id;
		this.emp_id = emp_id;
		this.date = date;
		this.reason = reason;
		this.check = check;
	}
	public AnnualVO(String c_id,String emp_id,String date, String reason,String check) {
		this.c_id = c_id;
		this.emp_id = emp_id;
		this.date = date;
		this.reason = reason;
		this.check = check;
	}
	public AnnualVO(String ann_id, String date, String reason,String check) {
		this.ann_id = ann_id;
		this.date = date;
		this.reason = reason;
		this.check = check;
	}
	public AnnualVO(String ann_id, String c_id, String date, String reason,String check,String name, String pos_name) {
		this.ann_id = ann_id;
		this.date = date;
		this.reason = reason;
		this.check = check;
		this.name = name;
		this.pos_name = pos_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPos_name() {
		return pos_name;
	}
	public void setPos_name(String pos_name) {
		this.pos_name = pos_name;
	}
	public String getAnn_id() {
		return ann_id;
	}
	public void setAnn_id(String ann_id) {
		this.ann_id = ann_id;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
}
