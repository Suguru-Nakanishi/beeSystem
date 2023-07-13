package bean;

public class PurchaseOrderTable implements java.io.Serializable {
	private String order_no;
	private String customer_no;
	private String product_no;
	private int order_qty;
	private String delivery_date;
	private String ship_date;
	private int fin_flg;
	private String order_date;
	private String registuser;
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getCustomer_no() {
		return customer_no;
	}
	public void setCustomer_no(String customer_no) {
		this.customer_no = customer_no;
	}
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
	public String getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}
	public String getShip_date() {
		return ship_date;
	}
	public void setShip_date(String ship_date) {
		this.ship_date = ship_date;
	}
	public int getFin_flg() {
		return fin_flg;
	}
	public void setFin_flg(int fin_flg) {
		this.fin_flg = fin_flg;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getRegistuser() {
		return registuser;
	}
	public void setRegistuser(String registuser) {
		this.registuser = registuser;
	}
}
