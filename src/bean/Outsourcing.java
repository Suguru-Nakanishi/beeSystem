package bean;

public class Outsourcing implements java.io.Serializable {

	//画面操作分ビーン
	private String order_no; //ORDER_MASTER
	private String product_no;//ORDER_MASTER
	private int order_qty; //ORDER_MASTER
	private int due_qty; //ORDER_MASTER
	private String orderdate; //ORDER_MASTER
	private String product_name; //PRODUCT_MASTERより

	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
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
	public int getDue_qty() {
		return due_qty;
	}
	public void setDue_qty(int due_qty) {
		this.due_qty = due_qty;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	//発注テーブル更新用　ORDER_MASTERのうち上記以外全て
		private String supplier_no;
		private String delivery_date;
		private String etc;
		private String due_date;
		private int fin_flg;
		private String registuser;
		private String orderuser;
		private String outsourcinguser;


		public String getSupplier_no() {
			return supplier_no;
		}
		public void setSupplier_no(String supplier_no) {
			this.supplier_no = supplier_no;
		}
		public String getDelivery_date() {
			return delivery_date;
		}
		public void setDelivery_date(String delivery_date) {
			this.delivery_date = delivery_date;
		}
		public String getEtc() {
			return etc;
		}
		public void setEtc(String etc) {
			this.etc = etc;
		}
		public String getDue_date() {
			return due_date;
		}
		public void setDue_date(String due_date) {
			this.due_date = due_date;
		}
		public int getFin_flg() {
			return fin_flg;
		}
		public void setFin_flg(int fin_flg) {
			this.fin_flg = fin_flg;
		}
		public String getRegistuser() {
			return registuser;
		}
		public void setRegistuser(String registuser) {
			this.registuser = registuser;
		}
		public String getOrderuser() {
			return orderuser;
		}
		public void setOrderuser(String orderuser) {
			this.orderuser = orderuser;
		}
		public String getOutsourcinguser() {
			return outsourcinguser;
		}
		public void setOutsourcinguser(String outsourcinguser) {
			this.outsourcinguser = outsourcinguser;
		}
		//在庫テーブル更新用
		private String stock_info_date;
		private int stock_qty;
		private int t_nyuko;
		private String up_date;



		public String getStock_info_date() {
			return stock_info_date;
		}
		public void setStock_info_date(String stock_info_date) {
			this.stock_info_date = stock_info_date;
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
		public String getUp_date() {
			return up_date;
		}
		public void setUp_date(String up_date) {
			this.up_date = up_date;
		}







	}

