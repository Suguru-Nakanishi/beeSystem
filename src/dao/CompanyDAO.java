package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Company;

public class CompanyDAO extends DAO {
	private String company_no, company_name, branch_name, zip_no, address, tel, fax, manager,sql;
	private List<Company> info = null;
	private Company company = null;
	private PreparedStatement st;
	private Connection con;
	private ResultSet rs;

	public CompanyDAO(String company_no) {setCompany_no(company_no);}

	public void setCompany_no(String company_no) {this.company_no = company_no;}

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

	public List<Company> searchCompany() {
 		sql = "SELECT CUSTOMER_NAME AS COMPANY_NAME,BRANCH_NAME,ZIP_NO,ADDRESS_1||ADDRESS_2||ADDRESS_3 AS ADDRESS,TEL,FAX,MANAGER FROM CUSTOMER_MASTER WHERE CUSTOMER_NO='" + company_no + "'UNION SELECT SUPPLIER_NAME,BRANCH_NAME,ZIPNO,ADDRESS_1||ADDRESS_2||ADDRESS_3,TEL,FAX,MANAGER FROM SUPPLIER_MASTER WHERE SUPPLIER_NO='" + company_no + "'";
		doQuery();
		return info;
	}

	private void doQuery() {
		info = new ArrayList<>();
		try {
			con = getConnection();
			st = con.prepareStatement(sql);
			listAdd();
			st.close();
			con.close();
		} catch (Exception e) {e.printStackTrace();}
	}

	private void listAdd() {
		try {
			rs = st.executeQuery();
			while (rs.next()) {
				company = new Company();
				setCompany_name(rs.getString("company_name"));
				setBranch_name(rs.getString("branch_name"));
				setZip_no(rs.getString("zip_no"));
				setAddress(rs.getString("address"));
				setTel(rs.getString("tel"));
				setFax(rs.getString("fax"));
				setManager(rs.getString("manager"));
				company.setCompany_name(getCompany_name());
				company.setBranch_name(getBranch_name());
				company.setZip_no(getZip_no());
				company.setAddress(getAddress());
				company.setTel(getTel());
				company.setFax(getFax());
				company.setManager(getManager());
				info.add(company);
			}
		} catch (SQLException e) {e.printStackTrace();}
	}
}