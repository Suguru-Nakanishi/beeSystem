package bean;

public class ShipmentUpdate implements java.io.Serializable {
	private String po_no;				//受注テーブルの受注番号
	private String ship_date;			//受注テーブルの出荷日
	private String shipment_registdate;	//受注テーブルの出荷処理日付
	private String shipment_registuser;	//受注テーブルの出荷処理担当者

	private String stock_info_date;		//在庫テーブルの年月
	private String product_no;			//在庫テーブルの品番
	private int stock_qty;				//在庫テーブルの現在庫個数（更新用）
	private int t_syuka;				//在庫テーブルの当月出荷数（更新用）
	private String up_date;				//在庫テーブルの更新日

	public String getPo_no() {
		return po_no;
	}
	public void setPo_no(String po_no) {
		this.po_no = po_no;
	}
	public String getShip_date() {
		return ship_date;
	}
	public void setShip_date(String ship_date) {
		this.ship_date = ship_date;
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
	public String getStock_info_date() {
		return stock_info_date;
	}
	public void setStock_info_date(String stock_info_date) {
		this.stock_info_date = stock_info_date;
	}
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public int getStock_qty() {
		return stock_qty;
	}
	public void setStock_qty(int stock_qty) {
		this.stock_qty = stock_qty;
	}
	public int getT_syuka() {
		return t_syuka;
	}
	public void setT_syuka(int t_syuka) {
		this.t_syuka = t_syuka;
	}
	public String getUp_date() {
		return up_date;
	}
	public void setUp_date(String up_date) {
		this.up_date = up_date;
	}
}
