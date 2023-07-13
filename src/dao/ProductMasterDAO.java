package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import bean.ProductMaster;
import tool.NumberManager;

public class ProductMasterDAO extends DAO {

	//PRODUCT_MASTERテーブルに情報があるかどうかを調べるためのメソッド
	public ProductMaster searchProductNo(String product_no) throws Exception {
		ProductMaster product_master = null;

		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(
				"SELECT PM.PRODUCT_NO,PM.PRODUCT_NAME,SM.SUPPLIER_NO,SM.SUPPLIER_NAME,\r\n" +
						"PM.UNITPRICE,PM.SELLINGPRICE,PM.LEADTIME,PM.LOT,\r\n" +
						"PM.LOCATION,PM.BASESTOCK,PM.ETC\r\n" +
						"FROM PRODUCT_MASTER PM\r\n" +
						"LEFT OUTER JOIN SUPPLIER_MASTER SM\r\n" +
						"ON PM.SUPPLIER_NO=SM.SUPPLIER_NO\r\n" +
						"WHERE PM.PRODUCT_NO=?\r\n" + "");

		ps.setString(1, product_no);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			product_master = new ProductMaster();
			product_master.setProduct_no(rs.getString("product_no"));
			product_master.setProduct_name(rs.getString("product_name"));
			product_master.setSm_supplier_no(rs.getString("supplier_no"));
			product_master.setSm_supplier_name(rs.getString("supplier_name"));
			product_master.setUnitprice(rs.getInt("unitprice"));
			product_master.setSellingprice(rs.getInt("sellingprice"));
			product_master.setLeadtime(rs.getInt("leadtime"));
			product_master.setLot(rs.getInt("lot"));
			product_master.setLocation(rs.getString("location"));
			product_master.setLocation(rs.getString("basestock"));
			product_master.setEtc(rs.getString("etc"));
		}
		ps.close();
		con.close();
		return product_master;
	}

	public String searchSupplierNo(ProductMaster product) throws Exception {
		String name = null;
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(
				"SELECT SUPPLIER_NO,SUPPLIER_NAME FROM SUPPLIER_MASTER WHERE"
						+ " SUPPLIER_NO=?");

		ps.setString(1, product.getSm_supplier_no());
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			name = rs.getString("supplier_name");

		}
		ps.close();
		con.close();
		return name;
	}

	//CUSTOMER_MASTERテーブルを更新するメソッド
	public int updateProductMaster(ProductMaster product_master) {
		int result = 0;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement(
					"UPDATE PRODUCT_MASTER SET "
							+ "PRODUCT_NAME=?,"
							+ "UNITPRICE=?,SELLINGPRICE=?,LEADTIME=?,LOT=?,"
							+ "LOCATION=?,BASESTOCK=?,ETC=? where PRODUCT_NO=?");
			ps.setString(1, product_master.getProduct_name());
			ps.setInt(2, product_master.getUnitprice());
			ps.setInt(3, product_master.getSellingprice());
			ps.setInt(4, product_master.getLeadtime());
			ps.setInt(5, product_master.getLot());
			ps.setString(6, product_master.getLocation());
			ps.setInt(7, product_master.getBasestock());
			ps.setString(8, product_master.getEtc());
			ps.setString(9, product_master.getProduct_no());
			result = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	//PRODUCT_MASTERテーブルに新規登録するメソッド
	public String insertProductMaster(ProductMaster product_master) {
		int result = 0;
		NumberManager nm = null;
		try {
			Calendar cl = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT PRODUCT_NO.nextval as next from dual");
			ResultSet rs = ps.executeQuery();

			rs.next();
			String nextno = rs.getString("next");
			nm = new NumberManager(nextno, 10);
			ps = con.prepareStatement("INSERT INTO PRODUCT_MASTER "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, nm.padding());
			ps.setString(2, product_master.getProduct_name());
			ps.setString(3, product_master.getSm_supplier_no());
			ps.setInt(4, product_master.getUnitprice());
			ps.setInt(5, product_master.getSellingprice());
			ps.setInt(6, product_master.getLeadtime());
			ps.setInt(7, product_master.getLot());
			ps.setString(8, product_master.getLocation());
			ps.setInt(9, product_master.getBasestock());
			ps.setString(10, product_master.getEtc());
			ps.setString(11, product_master.getRegistdate());
			ps.setString(12, product_master.getRegistuser());
			result = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == 0) {
			nm = null;
		}
		return nm.padding();
	}

	public int deleteProductMaster(String product_no) {
		int result = 0;
		try {
			Connection con = getConnection();

			PreparedStatement st;

			st = con.prepareStatement(
					"DELETE FROM PRODUCT_MASTER WHERE PRODUCT_NO=?");
			st.setString(1, product_no);

			result = st.executeUpdate();
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
