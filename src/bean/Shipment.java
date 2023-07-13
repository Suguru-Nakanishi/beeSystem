package bean;

public class Shipment implements java.io.Serializable {
	private String po_no;				//受注テーブルの受注番号
	private String order_date;			//受注テーブルの受注日
	private String customer_no;			//受注テーブルの顧客コード
	private String customer_name;		//顧客テーブルの顧客名
	private String branch_name;			//顧客テーブルの支店名
	private String product_no;			//受注テーブルの品番
	private String product_name;		//品番マスタの品名
	private int order_qty;				//受注テーブルの注文数量
	private String ship_date;			//受注テーブルの出荷日
	private int fin_flg;				//受注テーブルの出荷完了フラグ
	private String shipment_registdate;	//受注テーブルの出荷処理日付
	private String shipment_registuser;	//受注テーブルの出荷処理担当者

	public String getPo_no() {
		return po_no;
	}
	public void setPo_no(String po_no) {
		this.po_no = po_no;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
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
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
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
	public int getOrder_qty() {
		return order_qty;
	}
	public void setOrder_qty(int order_qty) {
		this.order_qty = order_qty;
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
	public String getShipment_registdate() {
		return shipment_registdate;
	}
	public void setShipment_registdate(String shipment_registdate) {
		this.shipment_registdate = shipment_registdate;
	}
	public String getShipment_registuser() {
		return shipment_registuser;
	}
	public void setShipment_registuser(String shipment_registuser) {
		this.shipment_registuser = shipment_registuser;
	}
}




