package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.SupplierMaster;
import tool.DateUtils;

public class SupplierMasterDAO extends DAO {
	//ID検索
	public SupplierMaster idSearch(String supplier_no)
			throws Exception {
		//戻り値用のsm
		SupplierMaster sm = null;
		try {
		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement(
				"select * from supplier_master where supplier_no = ?");
		st.setString(1, supplier_no);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			//用意したsmをインスタンス化
			sm = new SupplierMaster();
			sm.setSupplier_no(rs.getString("supplier_no"));
			sm.setSupplier_name(rs.getString("supplier_name"));
			sm.setBranch_name(rs.getString("branch_name"));
			sm.setZipno(rs.getString("zipno"));
			sm.setAddress_1(rs.getString("address_1"));
			sm.setAddress_2(rs.getString("address_2"));
			sm.setAddress_3(rs.getString("address_3"));
			sm.setTel(rs.getString("tel"));
			sm.setFax(rs.getString("fax"));
			sm.setManager(rs.getString("manager"));
			sm.setEtc(rs.getString("etc"));
			sm.setRegistdate(rs.getString("registdate"));
			sm.setRegistuser(rs.getString("registuser"));
			//1件もなければNULLが返る
		}
		st.close();
		con.close();
		}catch(Exception e) {
			System.out.println("SQLでエラーが発生しました。");
			e.printStackTrace();
		}
		return sm;

	}

	//更新
	public int updateSupplierMaster(SupplierMaster sm) {
		int result = 0;

		try {

			Connection con = getConnection();
			PreparedStatement st;
			st = con.prepareStatement(
					"update supplier_master set supplier_name=?,branch_name=?,zipno=?,address_1=?,address_2=?,address_3=?,tel=?,fax=?,manager=?,etc=?,registdate=?,registuser=? where supplier_no=?");
			st.setString(1, sm.getSupplier_name());
			st.setString(2, sm.getBranch_name());
			st.setString(3, sm.getZipno());
			st.setString(4, sm.getAddress_1());
			st.setString(5, sm.getAddress_2());
			st.setString(6, sm.getAddress_3());
			st.setString(7, sm.getTel());
			st.setString(8, sm.getFax());
			st.setString(9, sm.getManager());
			st.setString(10, sm.getEtc());
			//なぜ日付がエラー？bean.formatはbeanの中に無いので使えない　
			st.setString(11, DateUtils.getSystemDateWithSlash());
			st.setString(12, sm.getRegistuser());
			//1件もなければNULLが返る
			st.setString(13, sm.getSupplier_no());
			result =st.executeUpdate();
			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	//デリート (supplier_no はStringのままで良い？)
	public int deleteSupplierMaster(String supplier_no) {
		int result = 0;
		try {

			Connection con = getConnection();
			PreparedStatement st;
			st = con.prepareStatement(
					"delete from supplier_master where supplier_no= ?");
			//int getSupplier_no = Integer.parseInt(request.getParameter("supplier_no"));
			st.setString(1, supplier_no);
			result = st.executeUpdate();
			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	//新規登録

	public int insertSupplierMaster(SupplierMaster sm) {
		int result = 0;

		try {

			//SUPPLIER_NOはシーケンスから採番のため↓の1番はSupplier_nameとなる
			Connection con = getConnection();
			PreparedStatement st;
			st = con.prepareStatement(
					"insert into supplier_master VALUES(LPAD(supplier_no.NEXTVAL,6,'0'),?,?,?,?,?,?,?,?,?,?,?,?)");

			st.setString(1, sm.getSupplier_name());
			st.setString(2, sm.getBranch_name());
			st.setString(3, sm.getZipno());
			st.setString(4, sm.getAddress_1());
			st.setString(5, sm.getAddress_2());
			st.setString(6, sm.getAddress_3());
			st.setString(7, sm.getTel());
			st.setString(8, sm.getFax());
			st.setString(9, sm.getManager());
			st.setString(10, sm.getEtc());
			//なぜ日付がエラー？

			st.setString(11, DateUtils.getSystemDateWithSlash());
			st.setString(12, sm.getRegistuser());
			result = st.executeUpdate();

			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	//一覧取得
	public List<SupplierMaster> SupplierMasterList() {
		List<SupplierMaster> SupplierMasterDataList = new ArrayList<>();
		try {
			Connection con = getConnection();
			PreparedStatement st;
			st = con.prepareStatement(
					"SELECT *FROM SUPLIER_MASTER ORDER BY supplier_no");
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				//用意したsmをインスタンス化
				SupplierMaster sm = new SupplierMaster();
				sm.setSupplier_no(rs.getString("supplier_no"));
				sm.setSupplier_name(rs.getString("supplier_name"));
				sm.setBranch_name(rs.getString("branch_name"));
				sm.setZipno(rs.getString("zipno"));
				sm.setAddress_1(rs.getString("address_1"));
				sm.setAddress_2(rs.getString("address_2"));
				sm.setAddress_3(rs.getString("address_3"));
				sm.setTel(rs.getString("tel"));
				sm.setFax(rs.getString("fax"));
				sm.setManager(rs.getString("manager"));
				sm.setEtc(rs.getString("etc"));
				sm.setRegistdate(rs.getString("registdate"));
				sm.setRegistuser(rs.getString("registuser"));

				SupplierMasterDataList.add(sm);
				//1件もなければNULLが返る
			}
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SupplierMasterDataList;
	}

}
