package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.EntryExitInfoList;

public class EntryExitInfoListDAO extends DAO {

	public List<EntryExitInfoList> selectEeiView(String product_no) {
		List<EntryExitInfoList> eeiList = new ArrayList<>();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ENTRY_EXIT_INFO_LIST WHERE PRODUCT_NO=?  ORDER BY EN_EX_DATE");
			ps.setString(1, product_no);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				EntryExitInfoList eeil = new EntryExitInfoList();
				eeil.setProduct_no(rs.getString("product_no"));
				eeil.setProduct_name(rs.getString("product_name"));

				eeil.setEb_ex_id(rs.getString("eb_ex_id"));
				eeil.setEn_ex_date(rs.getString("en_ex_date"));
				if (rs.getInt("nyuko_qty") > 0) {
					eeil.setCount(rs.getInt("nyuko_qty"));
					eeil.setSituation("入");
				}
				if (rs.getInt("syuko_qty") > 0) {
					eeil.setCount(rs.getInt("syuko_qty"));
					eeil.setSituation("出");
				}
				eeil.setReason(rs.getString("reason"));
				eeiList.add(eeil);
			}
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eeiList;
	}
}
