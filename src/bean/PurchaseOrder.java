package bean;

public class PurchaseOrder implements java.io.Serializable {
	private String cm_customer_no;
	private String cm_customer_name;
	private String pm_product_no;
	private String pm_product_name;
	private String delivery_date;
	private int order_qty;
	private String etc;
	private String registdate;
	private String registuser;
	public String getCm_customer_no() {
		return cm_customer_no;
	}
	public void setCm_customer_no(String cm_customer_no) {
		this.cm_customer_no = cm_customer_no;
	}
	public String getCm_customer_name() {
		return cm_customer_name;
	}
	public void setCm_customer_name(String cm_customer_name) {
		this.cm_customer_name = cm_customer_name;
	}
	public String getPm_product_no() {
		return pm_product_no;
	}
	public void setPm_product_no(String pm_product_no) {
		this.pm_product_no = pm_product_no;
	}
	public String getPm_product_name() {
		return pm_product_name;
	}
	public void setPm_product_name(String pm_product_name) {
		this.pm_product_name = pm_product_name;
	}
	public String getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}
	public int getOrder_qty() {
		return order_qty;
	}
	public void setOrder_qty(int order_qty) {
		this.order_qty = order_qty;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getRegistdate() {
		return registdate;
	}
	public void setRegistdate(String registdate) {
		this.registdate = registdate;
	}
	public String getRegistuser() {
		return registuser;
	}
	public void setRegistuser(String registuser) {
		this.registuser = registuser;
	}


}