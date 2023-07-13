package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.EntryExitInfo;
import tool.DateUtils;
import tool.NumberManager;

public class EntryExitInfoDAO extends DAO {

	@SuppressWarnings("resource")
	public int insertEntryUpdatePStock(EntryExitInfo eei, String stock_info_date, String product_no) {
		int insertResult = 0;
		int updateResult = 0;
		String update_date = DateUtils.getSystemDateWithSlash();
		NumberManager numMan = null;
		PreparedStatement ps;
		try {
			Connection con = getConnection();
			con.setAutoCommit(false);

			 ps = con.prepareStatement("SELECT PRODUCT_NO.NEXTVAL FROM DUAL");
			ResultSet rs = ps.executeQuery();
			rs.next();
			String nextval = rs.getString("nextval");
			numMan = new NumberManager(nextval, 8);
			ps = con.prepareStatement(
					"INSERT INTO ENTRY_EXIT_INFO VALUES(?,?,?,?,?,?,?,?)");
			ps.setString(1, numMan.padding());
			ps.setString(2, eei.getEn_ex_date());
			ps.setString(3, eei.getProduct_no());
			ps.setInt(4, eei.getNyuko_qty());
			ps.setInt(5, eei.getSyuko_qty());
			ps.setString(6, eei.getReason());
			ps.setString(7, eei.getRegistdate());
			ps.setString(8, eei.getRegistuser());

			insertResult = ps.executeUpdate();

			if (eei.getNyuko_qty() > 0) {
				ps = con.prepareStatement(
						"UPDATE PRODUCT_STOCK SET STOCK_QTY=(STOCK_QTY+?),T_NYUKO=(T_NYUKO+?),UP_DATE=?  "
								+ "WHERE STOCK_INFO_DATE=? AND PRODUCT_NO=?");
				ps.setInt(1, eei.getNyuko_qty());
				ps.setInt(2, eei.getNyuko_qty());
				ps.setString(3, update_date);
				ps.setString(4, stock_info_date);
				ps.setString(5, product_no);
				updateResult = ps.executeUpdate();
			}else {
				PreparedStatement ps2 = con.prepareStatement(
					"UPDATE PRODUCT_STOCK SET STOCK_QTY=(STOCK_QTY-?),T_SYUKO=(T_SYUKO+?),UP_DATE=? "
								+ "WHERE STOCK_INFO_DATE=? AND PRODUCT_NO=?");
				ps2.setInt(1, eei.getSyuko_qty());
				ps2.setInt(2, eei.getSyuko_qty());
				ps2.setString(3, update_date);
				ps2.setString(4, stock_info_date);
				ps2.setString(5, product_no);
				updateResult = ps2.executeUpdate();
				ps2.close();
			}
			if (insertResult > 0 && updateResult > 0) {
				con.commit();
			} else {
				con.rollback();
			}
			con.setAutoCommit(true);
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return insertResult + updateResult;
	}
}
