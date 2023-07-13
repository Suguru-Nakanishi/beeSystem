package bean;

public class RequirementsCalculationResult implements java.io.Serializable{
	private String product_no;
	private String product_name;
	private String delivery_date;
	private int order_qty;
	private int due_qty;
	private String supplier_no;
	private int delivery_leadtime;
	private int result;
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
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
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getDue_qty() {
		return due_qty;
	}
	public void setDue_qty(int due_qty) {
		this.due_qty = due_qty;
	}


}
