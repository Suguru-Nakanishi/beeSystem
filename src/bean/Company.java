package bean;

public class Company implements java.io.Serializable {

	private String company_name, branch_name, zip_no, address, tel, fax, manager;

	public void setCompany_name(String company_name) {this.company_name = company_name;}

	public void setBranch_name(String branch_name) {this.branch_name = branch_name;}

	public void setZip_no(String zip_no) {this.zip_no = zip_no;}

	public void setAddress(String address) {this.address = address;}

	public void setTel(String tel) {this.tel = tel;}

	public void setFax(String fax) {this.fax = fax;}

	public void setManager(String manager) {this.manager = manager;}

	public String getCompany_name() {return this.company_name;}

	public String getBranch_name() {return this.branch_name;}

	public String getZip_no() {return this.zip_no;}

	public String getAddress() {return this.address;}

	public String getTel() {return this.tel;}

	public String getFax() {return this.fax;}

	public String getManager() {return this.manager;}
}