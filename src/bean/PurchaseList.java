package bean;

public class PurchaseList implements java.io.Serializable {
	private String product_no;		//受注テーブルの品番
	private String product_name;	//品番マスタの品名
	private String order_date;		//受注テーブルの注文日
	private String delivery_date;	//受注テーブルの納期
	private String po_no;			//受注テーブルの受注番号
	private String ship_date;		//受注テーブルの出荷日
	private int order_qty;			//受注テーブルの注文個数
	private String customer_no;		//受注テーブルの顧客先番号
	private String customer_name;	//顧客先マスタの会社名
	private int fin_flg;			//受注テーブルの完了フラグ

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
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
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
	public int getOrder_qty() {
		return order_qty;
	}
	public void setOrder_qty(int order_qty) {
		this.order_qty = order_qty;
	}
	public String getCustomer_no() {
		return customer_no;
	}
	public void setCustomer_no(String customer_no) {
		this.customer_no = customer_no;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public int getFin_flg() {
		return fin_flg;
	}
	public void setFin_flg(int fin_flg) {
		this.fin_flg = fin_flg;
	}
	public String getPo_no() {
		return po_no;
	}
	public void setPo_no(String po_no) {
		this.po_no = po_no;
	}
}
