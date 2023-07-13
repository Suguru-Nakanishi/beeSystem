package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Outsourcing;
import tool.DateUtils;

public class OutsourcingDAO extends DAO {

	//ID検索
	public Outsourcing idSearch(String order_no)
			throws Exception {
		//戻り値用のosを用意
		Outsourcing os = null;
		try {
			Connection con = getConnection();
			PreparedStatement st;
			st = con.prepareStatement(

					"SELECT OM.ORDER_NO ,OM.ORDERDATE, OM.PRODUCT_NO, PM.PRODUCT_NAME,  OM.ORDER_QTY, OM.DUE_QTY"
							+ " FROM ORDER_MASTER OM"
							+ " INNER JOIN PRODUCT_MASTER PM"
							+ " ON OM.PRODUCT_NO = PM.PRODUCT_NO WHERE OM.ORDER_NO = ? AND FIN_FLG = 0");

			st.setString(1, order_no);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				//用意したOutsourcingビーン osをインスタンス化
				os = new Outsourcing();
				os.setOrder_no(rs.getString("order_no"));
				os.setOrderdate(rs.getString("orderdate"));
				os.setProduct_no(rs.getString("product_no"));
				os.setProduct_name(rs.getString("product_name"));
				os.setOrder_qty(rs.getInt("order_qty"));
				os.setDue_qty(rs.getInt("due_qty"));

				//1件もなければNULLが返る
			}
			st.close();
			con.close();
		} catch (Exception e) {
			System.out.println("SQLでエラーが発生しました。");
			e.printStackTrace();
		}
		return os;
	}

	//更新 発注テーブル(ORDER_MASTER) ・在庫テーブル(PRODUCT_STOCK）

	public int updateOutsourcing(Outsourcing os) {
		int result1 = 0;
		int result2 = 0;
		try {

			Connection con = getConnection();
			con.setAutoCommit(false); //トランザクション コミットを無効化
			PreparedStatement st;
			//発注テーブル更新
			st = con.prepareStatement(
					"update order_master set due_date=?, due_qty=?, fin_flg=1, outsourcinguser=? where order_no=?");
			st.setString(1, DateUtils.getSystemDateWithSlash()); //システム日付
			st.setInt(2, os.getDue_qty()); //画面より
			st.setString(3, os.getOutsourcinguser()); //ログインID
			st.setString(4, os.getOrder_no()); //0→1
			result1 = st.executeUpdate();

			//在庫テーブル更新
			st = con.prepareStatement(
					"update product_stock set stock_qty=?, t_nyuko=?, up_date=?  where stock_info_date=? and product_no=?");
			st.setInt(1, os.getStock_qty());
			st.setInt(2, os.getT_nyuko());
			st.setString(3, os.getUp_date());
			st.setString(4, os.getStock_info_date());
			st.setString(5, os.getProduct_no());


			result2 = st.executeUpdate();

			//発注テーブルと在庫テーブルの更新がともに完了時はコミット、失敗時はロールバック
			if (result1 > 0 && result2 > 0) {
				con.commit();
			} else {
				con.rollback();
			}
			con.setAutoCommit(true);
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result1 + result2;
	}



}
