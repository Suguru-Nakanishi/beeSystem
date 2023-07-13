package bean;

public class RequirementsCalculation implements java.io.Serializable {
	private String product_no;
	private int order_qty;
	private String order_date;
	private String delivery_date;
	private int stock_qty;
	private int due_qty;
	private String due_date;
	private int po_fin_flg;
	private int om_fin_flg;
	private String customer_no;
	private String supplier_no;
	private int delivery_leadtime;
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public int getOrder_qty() {
		return order_qty;
	}
	public void setOrder_qty(int order_qty) {
		this.order_qty = order_qty;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(String ship_date) {
		this.delivery_date = ship_date;
	}
	public int getStock_qty() {
		return stock_qty;
	}
	public void setStock_qty(int stock_qty) {
		this.stock_qty = stock_qty;
	}
	public int getDue_qty() {
		return due_qty;
	}
	public void setDue_qty(int due_qty) {
		this.due_qty = due_qty;
	}
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	public int getPo_fin_flg() {
		return po_fin_flg;
	}
	public void setPo_fin_flg(int po_fin_flg) {
		this.po_fin_flg = po_fin_flg;
	}
	public int getOm_fin_flg() {
		return om_fin_flg;
	}
	public void setOm_fin_flg(int om_fin_flg) {
		this.om_fin_flg = om_fin_flg;
	}
	public String getCustomer_no() {
		return customer_no;
	}
	public void setCustomer_no(String customer_no) {
		this.customer_no = customer_no;
	}
	public String getSupplier_no() {
		return supplier_no;
	}
	public void setSupplier_no(String supplier_no) {
		this.supplier_no = supplier_no;
	}
	public int getDelivery_leadtime() {
		return delivery_leadtime;
	}
	public void setDelivery_leadtime(int delivery_leadtime) {
		this.delivery_leadtime = delivery_leadtime;
	}


}
