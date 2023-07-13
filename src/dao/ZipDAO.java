package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ZipData;

public class ZipDAO extends DAO {



	/**
	 * 郵便番号検索
	 * 郵便番号から県、市町村を取得する。
	 * 同じ郵便番号で複数の市町村登録があるので、
	 * 戻り値はList型 ZipDataビーンとしている。
	 * @param zipno
	 * @return List<ZipData>
	 */
	public List<ZipData> searchAllByZipNo(String zipno) {
		List<ZipData> listZip =new ArrayList<>();
		Connection con =null;
		PreparedStatement st= null;
		try {
			 con = getConnection();
			ResultSet rs = null;

			// データベース情報取得
			st = con.prepareStatement(
					"SELECT ZIPNO,PREF, CONCAT(REPLACE(CITY,'　',''),REPLACE(VILLEGE,'　',''))AS CITY FROM ZIP_MASTER WHERE ZIPNO=?");
			st.setString(1, zipno);
			rs = st.executeQuery();

			while (rs.next()) {
				ZipData zd = new ZipData();
				zd.setZipno(rs.getString("zipno"));
				zd.setPref(rs.getString("pref"));
				zd.setCity(rs.getString("city"));
//				zd.setVillege(rs.getString("villege"));
				listZip.add(zd);
			}


		} catch (Exception e) {
			System.out.println("SQLでエラーが発生しました。");
			e.printStackTrace();
		}finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		return listZip;
	}



	/**
	 * 都道府県のみ取り出す。
	 * @return List<String>
	 */
	public List<String> searchOnlyPref() {
		List<String> pref = new ArrayList<>();
		Connection con =null;
		PreparedStatement st= null;
		try {
			con = getConnection();
			ResultSet rs = null;
			// データベース情報取得
			st = con.prepareStatement(
					"SELECT DISTINCT PREF FROM ZIP_MASTER");
			rs = st.executeQuery();
			while (rs.next()) {
				String prefName;
                prefName = rs.getString("PREF");
                pref.add(prefName);
			}
		} catch (Exception e) {
			System.out.println("SQLでエラーが発生しました。");
			e.printStackTrace();
		}finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		return pref;
	}
}
