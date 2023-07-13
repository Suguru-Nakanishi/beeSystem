package bean;

public class SupplierMaster implements java.io.Serializable {

	private String supplier_no, supplier_name, branch_name, zipno, address_1, address_2, address_3, tel, fax, manager, etc, registdate, registuser;

	public void setSupplier_no(String supplier_no) {this.supplier_no = supplier_no;}

	public void setSupplier_name(String supplier_name) {this.supplier_name = supplier_name;}

	public void setBranch_name(String branch_name) {this.branch_name = branch_name;}

	public void setZipno(String zipno) {this.zipno = zipno;}

	public void setAddress_1(String address_1) {this.address_1 = address_1;}

	public void setAddress_2(String address_2) {this.address_2 = address_2;}

	public void setAddress_3(String address_3) {this.address_3 = address_3;}

	public void setTel(String tel) {this.tel = tel;}

	public void setFax(String fax) {this.fax = fax;}

	public void setManager(String manager) {this.manager = manager;}

	public void setEtc(String etc) {this.etc = etc;}

	public void setRegistdate(String registdate) {this.registdate = registdate;}

	public void setRegistuser(String registuser) {this.registuser = registuser;}

	public String getSupplier_no() {return this.supplier_no;}

	public String getSupplier_name() {return this.supplier_name;}

	public String getBranch_name() {return this.branch_name;}

	public String getZipno() {return this.zipno;}

	public String getAddress_1() {return this.address_1;}

	public String getAddress_2() {return this.address_2;}

	public String getAddress_3() {return this.address_3;}

	public String getTel() {return this.tel;}

	public String getFax() {return this.fax;}

	public String getManager() {return this.manager;}

	public String getEtc() {return this.etc;}

	public String getRegistdate() {return this.registdate;}

	public String getRegistuser() {return this.registuser;}


	private String login;

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}


	}

