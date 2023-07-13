package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Stock;

public class StockDAO extends DAO {
	private int line = 0, purchase_order_qty, order_qty, stock_qty;;
	private String product_no, product_name, order_date, customer_no, order_no, supplier_no, sql;
	private List<Stock> list = null;
	private Stock stock = null;
	private PreparedStatement st;
	private Connection con;
	private ResultSet rs;

	public StockDAO(String product_no) {setProduct_no(product_no);}

	private void setPurchase_order_qty(int purchase_order_qty) {this.purchase_order_qty = purchase_order_qty;}

	private void setOrder_qty(int order_qty) {this.order_qty = order_qty;}

	private void setStock_qty(int stock_qty) {this.stock_qty = stock_qty;}

	private void setProduct_no(String product_no) {this.product_no = product_no;}

	private void setProduct_name(String product_name) {this.product_name = product_name;}

	private void setOrder_date(String order_date) {this.order_date = order_date;}

	private void setCustomer_no(String customer_no) {this.customer_no = customer_no;}

	private void setOrder_no(String order_no) {this.order_no = order_no;}

	private void setSupplier_no(String supplier_no) {this.supplier_no = supplier_no;}

	public int getLine() {	return line;}

	public Stock getStock() {return stock;}

	public int getPurchase_order_qty() {return purchase_order_qty;}

	public int getOrder_qty() {return order_qty;}

	public int getStock_qty() {return stock_qty;}

	public String getProduct_no() {return product_no;}

	public String getProduct_name() {return product_name;}

	public String getOrder_date() {return order_date;}

	public String getCustomer_no() {return customer_no;}

	public String getOrder_no() {return order_no;}

	public String getSupplier_no() {return supplier_no;}

	public List<Stock> searchStock() {
		sql = "SELECT PRODUCT_NAME,NULL AS ORDER_DATE,NULL AS CUSTOMER_NO,NULL AS ORDER_NO,NULL AS PURCHASE_ORDER_QTY,NULL AS SUPPLIER_NO,NULL AS ORDER_QTY,NULL AS STOCK_QTY FROM PRODUCT_MASTER WHERE PRODUCT_NO='" + product_no + "'UNION SELECT NULL,NULL,NULL,NULL,NULL,NULL,NULL,STOCK_QTY FROM PRODUCT_STOCK WHERE PRODUCT_NO='" + product_no + "'UNION SELECT NULL,ORDER_DATE,CUSTOMER_NO,PO_NO,ORDER_QTY,NULL,NULL,NULL FROM PURCHASE_ORDER WHERE PRODUCT_NO='" + product_no + "'AND FIN_FLG=0 UNION SELECT NULL,ORDERDATE,NULL,NULL,NULL,SUPPLIER_NO,ORDER_QTY,NULL FROM ORDER_MASTER WHERE PRODUCT_NO='" + product_no + "'AND FIN_FLG=0";
		doQuery();
		return list;
	}

	private void doQuery() {
		list = new ArrayList<>();
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
				stock = new Stock();
				setProduct_name(rs.getString("product_name"));
				setOrder_date(rs.getString("order_date"));
				setCustomer_no(rs.getString("customer_no"));
				setOrder_no(rs.getString("order_no"));
				setPurchase_order_qty(rs.getInt("purchase_order_qty"));
				setSupplier_no(rs.getString("supplier_no"));
				setOrder_qty(rs.getInt("order_qty"));
				setStock_qty(rs.getInt("stock_qty"));
				stock.setProduct_name(getProduct_name());
				stock.setOrder_date(getOrder_date());
				stock.setCustomer_no(getCustomer_no());
				stock.setOrder_no(getOrder_no());
				stock.setPurchase_order_qty(getPurchase_order_qty());
				stock.setSupplier_no(getSupplier_no());
				stock.setOrder_qty(getOrder_qty());
				stock.setStock_qty(getStock_qty());
				list.add(stock);
			}
		} catch (SQLException e) {e.printStackTrace();}
	}
}