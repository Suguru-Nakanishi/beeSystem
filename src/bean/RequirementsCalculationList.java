package bean;

public class RequirementsCalculationList implements java.io.Serializable{
	private String product_no;
	private int result;
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}

}
