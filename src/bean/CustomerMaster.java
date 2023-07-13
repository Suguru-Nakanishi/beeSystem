package bean;

import java.io.Serializable;

public class CustomerMaster implements Serializable{
private String customer_no;
private String customer_name;
private String branch_name;
private String zip_no;
private String address_1;
private String address_2;
private String address_3;
private String tel;
private String fax;
private String manager;
private int delivery_leadtime;
private String etc;
private String registdate;
private String registuser;
public String getCustomer_no() {
	return customer_no;
}
public void setCustomer_no(String customer_no) {
	this.customer_no = customer_no;
}
public String getCustomer_name() {
	return customer_name;
}
public void setCustomer_name(String customer_name) {
	this.customer_name = customer_name;
}
public String getBranch_name() {
	return branch_name;
}
public void setBranch_name(String branch_name) {
	this.branch_name = branch_name;
}
public String getZip_no() {
	return zip_no;
}
public void setZip_no(String zip_no) {
	this.zip_no = zip_no;
}
public String getAddress_1() {
	return address_1;
}
public void setAddress_1(String address_1) {
	this.address_1 = address_1;
}
public String getAddress_2() {
	return address_2;
}
public void setAddress_2(String address_2) {
	this.address_2 = address_2;
}
public String getAddress_3() {
	return address_3;
}
public void setAddress_3(String address_3) {
	this.address_3 = address_3;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public String getFax() {
	return fax;
}
public void setFax(String fax) {
	this.fax = fax;
}
public String getManager() {
	return manager;
}
public void setManager(String manager) {
	this.manager = manager;
}
public int getDelivery_leadtime() {
	return delivery_leadtime;
}
public void setDelivery_leadtime(int delivery_leadtime) {
	this.delivery_leadtime = delivery_leadtime;
}
public String getEtc() {
	return etc;
}
public void setEtc(String etc) {
	this.etc = etc;
}
public String getRegistdate() {
	return registdate;
}
public void setRegistdate(String registdate) {
	this.registdate = registdate;
}
public String getRegistuser() {
	return registuser;
}
public void setRegistuser(String registuser) {
	this.registuser = registuser;
}

}