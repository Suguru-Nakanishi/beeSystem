package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ProductMasterTable;
import bean.PurchaseOrderTable;
import bean.RequirementsCalculation;
import bean.RequirementsCalculationResult;
import tool.DateUtils;

public class RequirementDAO extends DAO {
	/**
	 * 品番テーブルの全情報を取得するためのメソッド
	 * @return list
	 */
	public List<ProductMasterTable> getProductMaster() {
		List<ProductMasterTable> list = new ArrayList<>();
		try {
			Connection con = getConnection();
			PreparedStatement st = con.prepareStatement("SELECT * FROM PRODUCT_MASTER");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				ProductMasterTable pm = new ProductMasterTable();
				pm.setProduct_no(rs.getString("product_no"));
				pm.setProduct_name(rs.getString("product_name"));
				pm.setSupplier_no(rs.getString("supplier_no"));
				pm.setUnitprice(rs.getInt("unitprice"));
				pm.setSellingprice(rs.getInt("sellingprice"));
				pm.setLeadtime(rs.getInt("leadtime"));
				pm.setLot(rs.getInt("lot"));
				pm.setLocation(rs.getString("location"));
				pm.setBasestock(rs.getInt("basestock"));
				pm.setEtc(rs.getString("etc"));
				pm.setRegistdate(rs.getString("registdate"));
				pm.setRegistuser(rs.getString("registuser"));
				list.add(pm);
			}
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public ProductMasterTable getProductMasterNo(String num) {
		ProductMasterTable pm = new ProductMasterTable();
		try {
			Connection con = getConnection();
			PreparedStatement st = con.prepareStatement("SELECT * FROM PRODUCT_MASTER WHERE PRODUCT_NO=?");
			st.setString(1, num);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				pm = new ProductMasterTable();
				pm.setProduct_no(rs.getString("product_no"));
				pm.setProduct_name(rs.getString("product_name"));
				pm.setSupplier_no(rs.getString("supplier_no"));
				pm.setUnitprice(rs.getInt("unitprice"));
				pm.setSellingprice(rs.getInt("sellingprice"));
				pm.setLeadtime(rs.getInt("leadtime"));
				pm.setLot(rs.getInt("lot"));
				pm.setLocation(rs.getString("location"));
				pm.setBasestock(rs.getInt("basestock"));
				pm.setEtc(rs.getString("etc"));
				pm.setRegistdate(rs.getString("registdate"));
				pm.setRegistuser(rs.getString("registuser"));
			}
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}
	public ProductMasterTable getProductMasterChoice(String num) {
		ProductMasterTable pm =null;
		try {
			Connection con = getConnection();
			PreparedStatement st = con.prepareStatement("SELECT * FROM PRODUCT_MASTER WHERE PRODUCT_NO=?");
			st.setString(1, num);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				pm = new ProductMasterTable();
				pm.setProduct_no(rs.getString("product_no"));
				pm.setProduct_name(rs.getString("product_name"));
				pm.setSupplier_no(rs.getString("supplier_no"));
				pm.setUnitprice(rs.getInt("unitprice"));
				pm.setSellingprice(rs.getInt("sellingprice"));
				pm.setLeadtime(rs.getInt("leadtime"));
				pm.setLot(rs.getInt("lot"));
				pm.setLocation(rs.getString("location"));
				pm.setBasestock(rs.getInt("basestock"));
				pm.setEtc(rs.getString("etc"));
				pm.setRegistdate(rs.getString("registdate"));
				pm.setRegistuser(rs.getString("registuser"));
			}
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;
	}
/**
 *
 * @param num
 * @return
 */
	public RequirementsCalculationResult getPurchaseResult(String num) {
		RequirementsCalculationResult rr = null;
		try {
			Connection con = getConnection();
			PreparedStatement st = con.prepareStatement(
					"SELECT PM.PRODUCT_NO,PM.PRODUCT_NAME,PO.ORDER_QTY,"
							+ "PO.DELIVERY_DATE,PM.SUPPLIER_NO,CM.DELIVERY_LEADTIME "
							+ "FROM PRODUCT_MASTER PM LEFT JOIN PURCHASE_ORDER PO "
							+ "ON PM.PRODUCT_NO=PO.PRODUCT_NO LEFT JOIN "
							+ "CUSTOMER_MASTER CM "
							+ "ON PO.CUSTOMER_NO=CM.CUSTOMER_NO WHERE PM.PRODUCT_NO=?");
			st.setString(1, num);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				rr = new RequirementsCalculationResult();
				rr.setProduct_no(rs.getString("product_no"));
				rr.setProduct_name(rs.getString("product_name"));
				rr.setDelivery_date(rs.getString("delivery_date"));
				rr.setOrder_qty(rs.getInt("order_qty"));
				rr.setSupplier_no(rs.getString("supplier_no"));
				rr.setDelivery_leadtime(rs.getInt("delivery_leadtime"));
			}
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rr;
	}

	public int insertOrder(RequirementsCalculationResult rl, int zaiko, String date, String user) {
		int result = 0;
		try {
			Connection con = getConnection();
			PreparedStatement st = con.prepareStatement(
					"INSERT INTO ORDER_MASTER VALUES('OD-' || to_char(sysdate,'YYMM') || '-' || lpad(to_char(order_no.nextval),5,'0'),"
							+ "?,?,?,?,NULL,NULL,NULL,0,?,?,?,NULL)");
			st.setString(1, rl.getSupplier_no());
			st.setString(2, rl.getProduct_no());
			st.setInt(3, zaiko);
			st.setString(4, date);
			st.setString(5, user);
			st.setString(6, DateUtils.getSystemDateWithSlash());
			st.setString(7, user);
			result = st.executeUpdate();
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<RequirementsCalculation> getOrderData(String num,ProductMasterTable pm) {
		List<RequirementsCalculation> rcl = new ArrayList<>();
		try {
			Connection con = getConnection();
			PreparedStatement st = con.prepareStatement(
					"SELECT DISTINCT PO.PRODUCT_NO,PO.ORDER_QTY AS \"PO_ORDER_QTY\",PO.ORDER_DATE,"
							+ "PO.DELIVERY_DATE,PS.STOCK_QTY,OM.ORDER_QTY AS \"OM_ORDER_QTY\",OM.DUE_DATE,CM.DELIVERY_LEADTIME, "
							+ "OM.FIN_FLG AS \"OM_FIN_FLG\",PO.FIN_FLG AS \"PO_FIN_FLG\",PO.CUSTOMER_NO,OM.SUPPLIER_NO "
							+ "FROM PURCHASE_ORDER PO LEFT JOIN PRODUCT_STOCK PS "
							+ "ON PO.PRODUCT_NO=PS.PRODUCT_NO LEFT JOIN ORDER_MASTER OM "
							+ "ON PO.PRODUCT_NO=OM.PRODUCT_NO INNER JOIN CUSTOMER_MASTER CM "
							+ "ON PO.CUSTOMER_NO=CM.CUSTOMER_NO WHERE PO.PRODUCT_NO=? AND PO.FIN_FLG=0");
			st.setString(1, num);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				RequirementsCalculation rc = new RequirementsCalculation();
				rc.setProduct_no(rs.getString("product_no"));
				rc.setOrder_qty(rs.getInt("om_order_qty"));
				rc.setOrder_date(rs.getString("order_date"));
				rc.setDelivery_date(rs.getString("delivery_date"));
				rc.setStock_qty(rs.getInt("stock_qty"));
				rc.setDue_qty(rs.getInt("po_order_qty"));
				rc.setDue_date(rs.getString("due_date"));
				rc.setOm_fin_flg(rs.getInt("om_fin_flg"));
				rc.setPo_fin_flg(rs.getInt("po_fin_flg"));
				rc.setCustomer_no(rs.getString("customer_no"));
				rc.setDelivery_leadtime(rs.getInt("delivery_leadtime"));
				rc.setSupplier_no(pm.getSupplier_no());
				rcl.add(rc);
			}
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rcl;
	}
	public List<RequirementsCalculation> getOrderDataB(String num) {
		List<RequirementsCalculation> rcl = new ArrayList<>();
		try {
			Connection con = getConnection();
			PreparedStatement st = con.prepareStatement(
					"SELECT PO.PRODUCT_NO,PO.ORDER_QTY,PO.ORDER_DATE,"
							+ "PO.DELIVERY_DATE,PS.STOCK_QTY,"
							+ "PO.FIN_FLG,PO.CUSTOMER_NO "
							+ "FROM PURCHASE_ORDER PO LEFT JOIN PRODUCT_STOCK PS "
							+ "ON PO.PRODUCT_NO=PS.PRODUCT_NO WHERE PO.PRODUCT_NO=?");
			st.setString(1, num);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				RequirementsCalculation rc = new RequirementsCalculation();
				rc.setProduct_no(rs.getString("product_no"));
				rc.setOrder_qty(rs.getInt("order_qty"));
				rc.setOrder_date(rs.getString("order_date"));
				rc.setDelivery_date(rs.getString("delivery_date"));
				rc.setStock_qty(rs.getInt("stock_qty"));
				rc.setPo_fin_flg(rs.getInt("fin_flg"));
				rc.setCustomer_no(rs.getString("customer_no"));
				rcl.add(rc);
			}
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rcl;
	}

	public List<Integer> getQTY(String num) {
		List<Integer> list = new ArrayList<>();
		try {
			Connection con = getConnection();
			PreparedStatement st = con.prepareStatement(
					"SELECT ORDER_QTY,order_no FROM ORDER_MASTER WHERE "
							+ "PRODUCT_NO=? AND FIN_FLG=0");
			st.setString(1, num);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				RequirementsCalculation rc = new RequirementsCalculation();
				rc.setOrder_qty(rs.getInt("order_qty"));
				System.out.println(rs.getString("order_no"));
				list.add(rc.getOrder_qty());
			}
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public PurchaseOrderTable getFinFlg(String num) {
		PurchaseOrderTable pur = null;
		try {
			Connection con = getConnection();
			PreparedStatement st = con
					.prepareStatement("SELECT FIN_FLG,PRODUCT_NO FROM "
							+ "PURCHASE_ORDER WHERE PRODUCT_NO=?");
			st.setString(1, num);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				pur = new PurchaseOrderTable();
				pur.setFin_flg(rs.getInt("fin_flg"));
				pur.setProduct_no(rs.getString("product_no"));
			}
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pur;
	}
	public RequirementsCalculation searchOrder(String num) {
		RequirementsCalculation rc=null;
		try {
			Connection con=getConnection();
			PreparedStatement st=con.prepareStatement("SELECT * FROM ORDER_MASTER WHERE PRODUCT_NO=?");
			st.setString(1, num);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				rc=new RequirementsCalculation();
				rc.setProduct_no(rs.getString("product_no"));
			}
			st.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rc;
	}
	public int getDeliveryDate(String num) {
		int rr = 0;
		try {
			Connection con = getConnection();
			PreparedStatement st = con.prepareStatement(
					"SELECT DELIVERY_LEADTIME FROM CUSTOMER_MASTER WHERE CUSTOMER_NO=?");
			st.setString(1, num);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				rr=rs.getInt("delivery_leadtime");
			}
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rr;
	}
}
