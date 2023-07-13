package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.CustomerMaster;

public class CustomerMasterDAO extends DAO {

	//CUSTOMER_MASTERテーブルに情報があるかどうかを調べるためのメソッド
	public CustomerMaster searchCustomerNo(String customer_no) throws Exception {
		CustomerMaster customer_master = new CustomerMaster();

		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(
				"SELECT * FROM CUSTOMER_MASTER WHERE CUSTOMER_NO=?");
		ps.setString(1, customer_no);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			customer_master.setCustomer_no(rs.getString("customer_no"));
			customer_master.setCustomer_name(rs.getString("customer_name"));
			customer_master.setBranch_name(rs.getString("branch_name"));
			customer_master.setZip_no(rs.getString("zip_no"));
			customer_master.setAddress_1(rs.getString("address_1"));
			customer_master.setAddress_2(rs.getString("address_2"));
			customer_master.setAddress_3(rs.getString("address_3"));
			customer_master.setTel(rs.getString("tel"));
			customer_master.setFax(rs.getString("fax"));
			customer_master.setManager(rs.getString("manager"));
			customer_master.setDelivery_leadtime(rs.getInt("delivery_leadtime"));
			customer_master.setEtc(rs.getString("etc"));
			customer_master.setRegistdate(rs.getString("registdate"));
			customer_master.setRegistuser(rs.getString("registuser"));
		}
		ps.close();
		con.close();
		return customer_master;
	}

	//CUSTOMER_MASTERテーブルを更新するメソッド
	public int updateCustomerMaster(CustomerMaster customer_master) {
		int result = 0;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(
					"UPDATE CUSTOMER_MASTER SET "
							+ "CUSTOMER_NAME=?,BRANCH_NAME=?,ZIP_NO=?,"
							+ "ADDRESS_1=?,ADDRESS_2=?,ADDRESS_3=?,TEL=?,FAX=?,MANAGER=?,"
							+ "DELIVERY_LEADTIME=?,ETC=? WHERE CUSTOMER_NO=?");
			ps.setString(1, customer_master.getCustomer_name());
			ps.setString(2, customer_master.getBranch_name());
			ps.setString(3, customer_master.getZip_no());
			ps.setString(4, customer_master.getAddress_1());
			ps.setString(5, customer_master.getAddress_2());
			ps.setString(6, customer_master.getAddress_3());
			ps.setString(7, customer_master.getTel());
			ps.setString(8, customer_master.getFax());
			ps.setString(9, customer_master.getManager());
			ps.setInt(10, customer_master.getDelivery_leadtime());
			ps.setString(11, customer_master.getEtc());
			ps.setString(12, customer_master.getCustomer_no());

			result = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	//CUSTOMER_MASTERテーブルに新規登録するメソッド
	public int insertCustomerMaster(CustomerMaster customer_master) {
		int result = 0;
		try {
			//			Calendar cl = Calendar.getInstance();
			//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO CUSTOMER_MASTER "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, customer_master.getCustomer_no());
			ps.setString(2, customer_master.getCustomer_name());
			ps.setString(3, customer_master.getBranch_name());
			ps.setString(4, customer_master.getZip_no());
			ps.setString(5, customer_master.getAddress_1());
			ps.setString(6, customer_master.getAddress_2());
			ps.setString(7, customer_master.getAddress_3());
			ps.setString(8, customer_master.getTel());
			ps.setString(9, customer_master.getFax());
			ps.setString(10, customer_master.getManager());
			ps.setInt(11, customer_master.getDelivery_leadtime());
			ps.setString(12, customer_master.getEtc());
			ps.setString(13, customer_master.getRegistdate());
			ps.setString(14, customer_master.getRegistuser());
			result = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteCustomerMaster(String customer_no) {
		int result = 0;
		try {
			Connection con = getConnection();
			PreparedStatement ps;
			ps = con.prepareStatement("DELETE FROM CUSTOMER_MASTER WHERE CUSTOMER_NO=?");
			ps.setString(1, customer_no);
			result = ps.executeUpdate();

			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getSequence(String sequence) throws Exception {
		//シーケンスの値を取得し、その値を保存する変数の宣言
		String result = null;
		//データベースにつなぐ
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT " + sequence + ".NEXTVAL AS GETNO FROM DUAL");
		//SQL実行結果をrsにセット
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			result = rs.getString("getno");//上記で宣言した変数にシーケンス番号を代入
		}
		rs.close();
		con.close();
		return result;//値を戻す。
	}
}
