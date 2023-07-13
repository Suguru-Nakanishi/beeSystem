package bean;

public class Stock implements java.io.Serializable {
	private int purchase_order_qty, order_qty, stock_qty;
	private String product_name, order_date, customer_no, order_no, supplier_no;

	public void setProduct_name(String product_name) {this.product_name = product_name;}

	public void setOrder_date(String order_date) {this.order_date = order_date;}

	public void setCustomer_no(String customer_no) {this.customer_no = customer_no;}

	public void setOrder_no(String order_no) {this.order_no = order_no;}

	public void setPurchase_order_qty(int purchase_order_qty) {this.purchase_order_qty = purchase_order_qty;}

	public void setSupplier_no(String supplier_no) {this.supplier_no = supplier_no;}

	public void setOrder_qty(int order_qty) {this.order_qty = order_qty;}

	public void setStock_qty(int stock_qty) {this.stock_qty = stock_qty;}

	public String getProduct_name() {return this.product_name;}

	public String getOrder_date() {return this.order_date;}

	public String getCustomer_no() {return this.customer_no;}

	public String getOrder_no() {return this.order_no;}

	public int getPurchase_order_qty() {return this.purchase_order_qty;}

	public String getSupplier_no() {return this.supplier_no;}

	public int getOrder_qty() {return this.order_qty;}

	public int getStock_qty() {return this.stock_qty;}
}