package Emp;

public class EmpVO {
	private String index;
	private String id_index;
	private String c_id;
	private String name; 
	private String pos_name;
	private String date;
	private String num;
	
	public EmpVO(String index,String id_index,String c_id,String name,String pos_name,String date) {
		super();
		this.index = index;
		this.id_index = id_index;
		this.c_id = c_id;
		this.name = name;
		this.pos_name = pos_name;
		this.date = date;
	}
	public EmpVO(String index,String id_index,String c_id,String name,String pos_name,String date,String num) {
		super();
		this.index = index;
		this.id_index = id_index;
		this.c_id = c_id;
		this.name = name;
		this.pos_name = pos_name;
		this.date = date;
		this.num = num;
	}
	public EmpVO(String id_index,String c_id,String name,String pos_name,String date) {
		super();
		this.id_index = id_index;
		this.c_id = c_id;
		this.name = name;
		this.pos_name = pos_name;
		this.date = date;
	}
	public EmpVO(String c_id,String name,String pos_name,String date) {
		super();  
		this.c_id = c_id;
		this.name = name;
		this.pos_name = pos_name;
		this.date = date;
	}
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getId_index() {
		return id_index;
	}
	public void setId_index(String id_index) {
		this.id_index = id_index;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
