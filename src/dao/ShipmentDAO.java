package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.ProductStock;
import bean.Shipment;
import bean.ShipmentUpdate;


public class ShipmentDAO extends DAO {

	public Shipment searchById(String po_no) {
		//戻り値用のShipmentビーンを宣言
		Shipment sh = null;

		try {
			Connection con = getConnection();

			PreparedStatement st = con.prepareStatement(
					"SELECT * FROM SHIPMENT_ACTION_VIEW WHERE PO_NO = ?");
			st.setString(1, po_no);
			//SQLの結果をResultSetの変数に代入
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				//用意したShipmentビーンをインスタンス化
				sh = new Shipment();
				sh.setPo_no(rs.getString("po_no"));
				sh.setOrder_date(rs.getString("order_date"));
				sh.setCustomer_no(rs.getString("customer_no"));
				sh.setCustomer_name(rs.getString("customer_name"));
				sh.setBranch_name(rs.getString("branch_name"));
				sh.setProduct_no(rs.getString("product_no"));
				sh.setProduct_name(rs.getString("product_name"));
				sh.setOrder_qty(rs.getInt("order_qty"));
				sh.setShip_date(rs.getString("ship_date"));
				sh.setFin_flg(rs.getInt("fin_flg"));
			}
			st.close();
			con.close();
		} catch (Exception e) {
			System.out.println("SQLでエラーが発生しました。");
			e.printStackTrace();
		}
		return sh;
	}


	public ProductStock searchProductStock(String stock_info_date, String product_no) {
		//戻り値用のProductStockビーンを宣言
		ProductStock ps = null;

		try {
			Connection con = getConnection();

			//年月と品番が一致するデータを検索し取得する。
			PreparedStatement st = con.prepareStatement(
					"SELECT * FROM PRODUCT_STOCK WHERE STOCK_INFO_DATE=? AND PRODUCT_NO=?");
			st.setString(1, stock_info_date);
			st.setString(2, product_no);
			//SQLの結果をResultSetの変数に代入
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				//ビーンpsをインスタンス化しデータベースの情報を詰め込む。
				ps = new ProductStock();
				ps.setStock_info_date(rs.getString("stock_info_date"));
				ps.setProduct_no(rs.getString("product_no"));
				ps.setStock_qty(rs.getInt("stock_qty"));
				ps.setT_nyuko(rs.getInt("t_nyuko"));
				ps.setT_syuko(rs.getInt("t_syuko"));
				ps.setT_syuka(rs.getInt("t_syuka"));
				ps.setUp_date(rs.getString("up_date"));
			}
			st.close();
			con.close();
		} catch (Exception e) {
			System.out.println("SQLでエラーが発生しました。");
			e.printStackTrace();
		}
		return ps;
	}


	public int updateShipmentUpdate(ShipmentUpdate sud) {
		int result1 = 0;
		int result2 = 0;
		try {
			//接続する
			Connection con = getConnection();
			//オートコミットを外す（複数のSQL文を送信するため）
				con.setAutoCommit(false);
			//SQLセット
			PreparedStatement st;

			//SQL実行①（受注テーブルを更新する）
			st = con.prepareStatement(
					"UPDATE PURCHASE_ORDER SET SHIP_DATE=?, FIN_FLG=1, SHIPMENT_REGISTDATE=?, SHIPMENT_REGISTUSER=? WHERE PO_NO=?");
			st.setString(1, sud.getShip_date().replace("-", "/"));
			st.setString(2, sud.getShipment_registdate());
			st.setString(3, sud.getShipment_registuser());
			st.setString(4, sud.getPo_no());
			//結果を受け取る
			result1 = st.executeUpdate();

			//SQL実行②（在庫テーブルを更新する）
			st = con.prepareStatement(
					"UPDATE PRODUCT_STOCK SET STOCK_QTY=?, T_SYUKA=?, UP_DATE=? WHERE STOCK_INFO_DATE=? AND PRODUCT_NO=?");
			st.setInt(1, sud.getStock_qty());
			st.setInt(2, sud.getT_syuka());
			st.setString(3, sud.getUp_date());
			st.setString(4, sud.getStock_info_date());
			st.setString(5, sud.getProduct_no());
			//結果を受け取る
			result2 = st.executeUpdate();

			//2つのUpdateがともに完了したらコミットし、どちらかだけならロールバックする。
			if(result1 > 0 && result2 > 0) {
				con.commit();
			}else {
				con.rollback();
			}
			con.setAutoCommit(true);
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result1+result2;
	}
}
