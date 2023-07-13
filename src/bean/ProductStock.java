package bean;

public class ProductStock implements java.io.Serializable {
	private String stock_info_date;  //年月
	private String product_no;		  //品番
	private int stock_qty;			  //現在在庫数
	private int t_nyuko;			  //当月入庫数
	private int t_syuko;			  //当月出庫数
	private int t_syuka;			  //当月出荷数
	private String up_date;			  //更新日

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
	public int getT_nyuko() {
		return t_nyuko;
	}
	public void setT_nyuko(int t_nyuko) {
		this.t_nyuko = t_nyuko;
	}
	public int getT_syuko() {
		return t_syuko;
	}
	public void setT_syuko(int t_syuko) {
		this.t_syuko = t_syuko;
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
