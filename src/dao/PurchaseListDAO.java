package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.PurchaseList;

public class PurchaseListDAO extends DAO{
	//①受注日付がどちらも入っていないとき
	public List<PurchaseList> getOrderPlane(String product_no) throws Exception{
		//戻り値用の変数（リスト型）を宣言
		List<PurchaseList> list = new ArrayList<>();
		try {
		//接続する
		Connection con = getConnection();
		//SQLセット
		PreparedStatement st = con.prepareStatement(
				"SELECT * FROM PURCHASE_LIST_VIEW WHERE PRODUCT_NO=? ORDER BY ORDER_DATE");
		st.setString(1, product_no);
		//SQL実行 結果を変数rsで取得
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			PurchaseList pl = new PurchaseList();
			pl.setProduct_no(rs.getString("product_no"));
			pl.setProduct_name(rs.getString("product_name"));
			pl.setOrder_date(rs.getString("order_date"));
			pl.setDelivery_date(rs.getString("delivery_date"));
			pl.setPo_no(rs.getString("po_no"));
			if(rs.getString("ship_date") == null) {
			pl.setShip_date("");
			}else {
			pl.setShip_date(rs.getString("ship_date"));
			}
			pl.setOrder_qty(rs.getInt("order_qty"));
			pl.setCustomer_no(rs.getString("customer_no"));
			pl.setCustomer_name(rs.getString("customer_name"));
			pl.setFin_flg(rs.getInt("fin_flg"));
			list.add(pl);
		}
		st.close();
		con.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
		return list;
	}

	//②受注日付の開始日のみ入力されているとき
	public List<PurchaseList> getOrderDate1(String product_no,String date) throws Exception{
		//戻り値用の変数（リスト型）を宣言
		List<PurchaseList> list = new ArrayList<>();
		try {
		//接続する
		Connection con = getConnection();
		//SQLセット
		PreparedStatement st = con.prepareStatement(
				"SELECT * FROM PURCHASE_LIST_VIEW WHERE PRODUCT_NO=? AND ORDER_DATE >=? ORDER BY ORDER_DATE");
		st.setString(1, product_no);
		st.setString(2, date);
		//SQL実行 結果を変数rsで取得
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			PurchaseList pl = new PurchaseList();
			pl.setProduct_no(rs.getString("product_no"));
			pl.setProduct_name(rs.getString("product_name"));
			pl.setOrder_date(rs.getString("order_date"));
			pl.setDelivery_date(rs.getString("delivery_date"));
			pl.setPo_no(rs.getString("po_no"));
			if(rs.getString("ship_date") == null) {
			pl.setShip_date("");
			}else {
			pl.setShip_date(rs.getString("ship_date"));
			}
			pl.setOrder_qty(rs.getInt("order_qty"));
			pl.setCustomer_no(rs.getString("customer_no"));
			pl.setCustomer_name(rs.getString("customer_name"));
			pl.setFin_flg(rs.getInt("fin_flg"));
			list.add(pl);
		}
		st.close();
		con.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
		return list;
	}

	//③受注日付の終了日のみ入力されているとき
	public List<PurchaseList> getOrderDate2(String product_no,String date) throws Exception{
		//戻り値用の変数（リスト型）を宣言
		List<PurchaseList> list = new ArrayList<>();
		try {
		//接続する
		Connection con = getConnection();
		//SQLセット
		PreparedStatement st = con.prepareStatement(
				"SELECT * FROM PURCHASE_LIST_VIEW WHERE PRODUCT_NO=? AND ORDER_DATE <=? ORDER BY ORDER_DATE");
		st.setString(1, product_no);
		st.setString(2, date);
		//SQL実行 結果を変数rsで取得
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			PurchaseList pl = new PurchaseList();
			pl.setProduct_no(rs.getString("product_no"));
			pl.setProduct_name(rs.getString("product_name"));
			pl.setOrder_date(rs.getString("order_date"));
			pl.setDelivery_date(rs.getString("delivery_date"));
			pl.setPo_no(rs.getString("po_no"));
			if(rs.getString("ship_date") == null) {
			pl.setShip_date("");
			}else {
			pl.setShip_date(rs.getString("ship_date"));
			}
			pl.setOrder_qty(rs.getInt("order_qty"));
			pl.setCustomer_no(rs.getString("customer_no"));
			pl.setCustomer_name(rs.getString("customer_name"));
			pl.setFin_flg(rs.getInt("fin_flg"));
			list.add(pl);
		}
		st.close();
		con.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
		return list;
	}

	//④受注日付の開始日・終了日がともに入力されているとき
	public List<PurchaseList> getOrderDateBetween(String product_no,String date1,String date2) throws Exception{
		//戻り値用の変数（リスト型）を宣言
		List<PurchaseList> list = new ArrayList<>();
		try {
		//接続する
		Connection con = getConnection();
		//SQLセット
		PreparedStatement st = con.prepareStatement(
				"SELECT * FROM PURCHASE_LIST_VIEW WHERE PRODUCT_NO=? AND ORDER_DATE BETWEEN ? AND ? ORDER BY ORDER_DATE");
		st.setString(1, product_no);
		st.setString(2, date1);
		st.setString(3, date2);
		//SQL実行 結果を変数rsで取得
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			PurchaseList pl = new PurchaseList();
			pl.setProduct_no(rs.getString("product_no"));
			pl.setProduct_name(rs.getString("product_name"));
			pl.setOrder_date(rs.getString("order_date"));
			pl.setDelivery_date(rs.getString("delivery_date"));
			pl.setPo_no(rs.getString("po_no"));
			if(rs.getString("ship_date") == null) {
			pl.setShip_date("");
			}else {
			pl.setShip_date(rs.getString("ship_date"));
			}
			pl.setOrder_qty(rs.getInt("order_qty"));
			pl.setCustomer_no(rs.getString("customer_no"));
			pl.setCustomer_name(rs.getString("customer_name"));
			pl.setFin_flg(rs.getInt("fin_flg"));
			list.add(pl);
		}
		st.close();
		con.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
		return list;
	}
}
