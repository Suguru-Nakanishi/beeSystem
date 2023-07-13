package bean;

import java.io.Serializable;

public class EntryExitInfoList implements Serializable{

	private String product_no;
	private String product_name;
	private String eb_ex_id;
	private String en_ex_date;
	private int nyuko_qty;
	private int syuko_qty;
	private String reason;
	private String situation;
	private int count;
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
	public String getEb_ex_id() {
		return eb_ex_id;
	}
	public void setEb_ex_id(String eb_ex_id) {
		this.eb_ex_id = eb_ex_id;
	}
	public String getEn_ex_date() {
		return en_ex_date;
	}
	public void setEn_ex_date(String en_ex_date) {
		this.en_ex_date = en_ex_date;
	}
	public int getNyuko_qty() {
		return nyuko_qty;
	}
	public void setNyuko_qty(int nyuko_qty) {
		this.nyuko_qty = nyuko_qty;
	}
	public int getSyuko_qty() {
		return syuko_qty;
	}
	public void setSyuko_qty(int syuko_qty) {
		this.syuko_qty = syuko_qty;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}


}
