package supplier.bean;
import java.io.Serializable;

public class Supplier implements Serializable{      //供应商实体类
	private static final long serialVersionUID = 1L;
	private int id;
	private String sfzID;       //身份证号码
	private String name;     //姓名
	private String sex;     //性别
	private String age;   //年龄
	private String address;       //住址
	private String phone;     //联系方式
	private String email;     //邮箱
	private String beiz;      //备注
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSfzID() {
		return sfzID;
	}
	public void setSfzID(String sfzID) {
		this.sfzID = sfzID;
	}
	public String getName() {
		return name;
	}
	public void setName(String Name) {
		this.name = Name;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBeiz() {
		return beiz;
	}
	public void setBeiz(String beiz) {
		this.beiz = beiz;
	}
	
}