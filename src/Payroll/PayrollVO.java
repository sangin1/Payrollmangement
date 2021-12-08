package Payroll;

public class PayrollVO {
	private String pay_id;
	private String c_id;
	private String emp_id;
	private String date;
	private String pay;
	private String tax;
	private String incometax;
	private String outpay;
	private String bonus;
	private String insurance;
	private String subsidy;
	private String code;
	private String name;
	private String pos;
	private String total;
	private String ser;
	
	
	public PayrollVO(String pay_id,String c_id,String emp_id, String date,String pay,String tax,String incometax,String outpay,
			String bonus,String insurance,String subsidy,String code,String name,String pos,String total) {
		this.pay_id = pay_id;
		this.c_id = c_id;
		this.emp_id = emp_id;
		this.date = date;
		this.pay = pay;
		this.tax = tax;
		this.incometax = incometax;
		this.outpay = outpay;
		this.bonus = bonus;
		this.insurance = insurance;
		this.subsidy = subsidy;
		this.code = code;
		this.name = name;
		this.pos = pos;
		this.total = total;
	}
	public PayrollVO(String pay_id,String c_id,String emp_id, String date,String pay,String tax,String incometax,String outpay,
			String bonus,String insurance,String subsidy,String total) {
		this.pay_id = pay_id;
		this.c_id = c_id;
		this.emp_id = emp_id;
		this.date = date;
		this.pay = pay;
		this.tax = tax;
		this.incometax = incometax;
		this.outpay = outpay;
		this.bonus = bonus;
		this.insurance = insurance;
		this.subsidy = subsidy;
		this.total = total;
	}
	public PayrollVO(String c_id,String emp_id,String code,String name,String pos,String ser) {
		this.c_id = c_id;
		this.emp_id = emp_id;
		this.code = code;
		this.name = name;
		this.pos = pos;
		this.ser=ser;
	}
	public PayrollVO(String pay_id,String c_id,String emp_id, String date,String pay,String tax,String incometax,String outpay,
						String bonus,String insurance,String subsidy) {
		this.pay_id = pay_id;
		this.c_id = c_id;
		this.emp_id = emp_id;
		this.date = date;
		this.pay = pay;
		this.tax = tax;
		this.incometax = incometax;
		this.outpay = outpay;
		this.bonus = bonus;
		this.insurance = insurance;
		this.subsidy = subsidy;
	}
	public PayrollVO(String code,String name,String pos) {
		this.code = code;
		this.name = name;
		this.pos = pos;
	}
	public PayrollVO() {
		
	}
	
	public String getSer() {
		return ser;
	}
	public void setSer(String ser) {
		this.ser = ser;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getPay_id() {
		return pay_id;
	}
	public void setPay_id(String pay_id) {
		this.pay_id = pay_id;
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
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getIncometax() {
		return incometax;
	}
	public void setIncometax(String incometax) {
		this.incometax = incometax;
	}
	public String getOutpay() {
		return outpay;
	}
	public void setOutpay(String outpay) {
		this.outpay = outpay;
	}
	public String getBonus() {
		return bonus;
	}
	public void setBonus(String bonus) {
		this.bonus = bonus;
	}
	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	public String getSubsidy() {
		return subsidy;
	}
	public void setSubsidy(String subsidy) {
		this.subsidy = subsidy;
	}
}
