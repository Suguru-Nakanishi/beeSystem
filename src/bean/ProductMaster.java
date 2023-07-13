package bean;

public class ProductMaster implements java.io.Serializable {
	private String product_no;
	private String product_name;
	private String sm_supplier_no;
	private String sm_supplier_name;
	private int unitprice;
	private int sellingprice;
	private int leadtime;
	private int lot;
	private String location;
	private int basestock;
	private String etc;
	private String registdate;
	private String registuser;

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

	public String getSm_supplier_no() {
		return sm_supplier_no;
	}

	public void setSm_supplier_no(String sm_supplier_no) {
		this.sm_supplier_no = sm_supplier_no;
	}

	public String getSm_supplier_name() {
		return sm_supplier_name;
	}

	public void setSm_supplier_name(String sm_supplier_name) {
		this.sm_supplier_name = sm_supplier_name;
	}

	public int getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(int unitprice) {
		this.unitprice = unitprice;
	}

	public int getSellingprice() {
		return sellingprice;
	}

	public void setSellingprice(int sellingprice) {
		this.sellingprice = sellingprice;
	}

	public int getLeadtime() {
		return leadtime;
	}

	public void setLeadtime(int leadtime) {
		this.leadtime = leadtime;
	}

	public int getLot() {
		return lot;
	}

	public void setLot(int lot) {
		this.lot = lot;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getBasestock() {
		return basestock;
	}

	public void setBasestock(int basestock) {
		this.basestock = basestock;
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