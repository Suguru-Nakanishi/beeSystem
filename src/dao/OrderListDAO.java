package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.OrderList;

public class OrderListDAO extends DAO{
	public List<OrderList> getOrderPlane(String num){
		List<OrderList> list=new ArrayList<>();
		try {
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
				"SELECT PM.PRODUCT_NO,PM.PRODUCT_NAME,OM.ORDERDATE,"
				+ "OM.DELIVERY_DATE,OM.DUE_DATE,OM.ORDER_QTY,OM.SUPPLIER_NO,"
				+ "SM.SUPPLIER_NAME,OM.FIN_FLG,OM.ORDER_NO FROM PRODUCT_MASTER PM "
				+ "LEFT JOIN ORDER_MASTER OM ON PM.PRODUCT_NO=OM.PRODUCT_NO "
				+ "LEFT JOIN SUPPLIER_MASTER SM ON OM.SUPPLIER_NO=SM.SUPPLIER_NO "
				+ "WHERE PM.PRODUCT_NO=? ORDER BY OM.ORDERDATE");
		st.setString(1, num);
		ResultSet rs=st.executeQuery();
		while(rs.next()) {
			OrderList ol=new OrderList();
			ol.setProduct_no(rs.getString("product_no"));
			ol.setProduct_name(rs.getString("product_name"));
			ol.setOrder_date(rs.getString("orderdate"));
			ol.setDelivery_date(rs.getString("delivery_date"));
			ol.setDue_date(rs.getString("due_date"));
			ol.setOrder_qty(rs.getInt("order_qty"));
			ol.setSupplier_no(rs.getString("supplier_no"));
			ol.setSupplier_name(rs.getString("supplier_name"));
			ol.setFin_flg(rs.getInt("fin_flg"));
			ol.setOrder_no(rs.getString("order_no"));
			list.add(ol);
		}
		st.close();
		con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public OrderList searchProduct(String num) {
		OrderList ol=null;
		try {
			Connection con=getConnection();
			PreparedStatement st=con.prepareStatement("");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ol;
	}
	public List<OrderList> getOrderDate1(String num,String date){
		List<OrderList> list=new ArrayList<>();
		try {
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
				"SELECT PM.PRODUCT_NO,PM.PRODUCT_NAME,OM.ORDERDATE,"
				+ "OM.DELIVERY_DATE,OM.DUE_DATE,OM.ORDER_QTY,OM.SUPPLIER_NO,"
				+ "SM.SUPPLIER_NAME,OM.FIN_FLG,OM.ORDER_NO FROM PRODUCT_MASTER PM "
				+ "LEFT JOIN ORDER_MASTER OM ON PM.PRODUCT_NO=OM.PRODUCT_NO "
				+ "LEFT JOIN SUPPLIER_MASTER SM ON OM.SUPPLIER_NO=SM.SUPPLIER_NO "
				+ "WHERE PM.PRODUCT_NO=? AND ORDERDATE >=? ORDER BY ORDERDATE");
		st.setString(1, num);
		st.setString(2, date);
		ResultSet rs=st.executeQuery();
		while(rs.next()) {
			OrderList ol=new OrderList();
			ol.setProduct_no(rs.getString("product_no"));
			ol.setProduct_name(rs.getString("product_name"));
			ol.setOrder_date(rs.getString("orderdate"));
			ol.setDelivery_date(rs.getString("delivery_date"));
			ol.setDue_date(rs.getString("due_date"));
			ol.setOrder_qty(rs.getInt("order_qty"));
			ol.setSupplier_no(rs.getString("supplier_no"));
			ol.setSupplier_name(rs.getString("supplier_name"));
			ol.setFin_flg(rs.getInt("fin_flg"));
			ol.setOrder_no(rs.getString("order_no"));
			list.add(ol);
		}
		st.close();
		con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<OrderList> getOrderDate2(String num,String date){
		List<OrderList> list=new ArrayList<>();
		try {
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
				"SELECT PM.PRODUCT_NO,PM.PRODUCT_NAME,OM.ORDERDATE,"
				+ "OM.DELIVERY_DATE,OM.DUE_DATE,OM.ORDER_QTY,OM.SUPPLIER_NO,"
				+ "SM.SUPPLIER_NAME,OM.FIN_FLG,OM.ORDER_NO FROM PRODUCT_MASTER PM "
				+ "LEFT JOIN ORDER_MASTER OM ON PM.PRODUCT_NO=OM.PRODUCT_NO "
				+ "LEFT JOIN SUPPLIER_MASTER SM ON OM.SUPPLIER_NO=SM.SUPPLIER_NO "
				+ "WHERE PM.PRODUCT_NO=? AND ORDERDATE <=? ORDER BY ORDERDATE");
		st.setString(1, num);
		st.setString(2, date);
		ResultSet rs=st.executeQuery();
		while(rs.next()) {
			OrderList ol=new OrderList();
			ol.setProduct_no(rs.getString("product_no"));
			ol.setProduct_name(rs.getString("product_name"));
			ol.setOrder_date(rs.getString("orderdate"));
			ol.setDelivery_date(rs.getString("delivery_date"));
			ol.setDue_date(rs.getString("due_date"));
			ol.setOrder_qty(rs.getInt("order_qty"));
			ol.setSupplier_no(rs.getString("supplier_no"));
			ol.setSupplier_name(rs.getString("supplier_name"));
			ol.setFin_flg(rs.getInt("fin_flg"));
			ol.setOrder_no(rs.getString("order_no"));
			list.add(ol);
		}
		st.close();
		con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<OrderList> getOrderDateBetween(String num,String date1,String date2){
		List<OrderList> list=new ArrayList<>();
		try {
		Connection con=getConnection();
		PreparedStatement st=con.prepareStatement(
				"SELECT PM.PRODUCT_NO,PM.PRODUCT_NAME,OM.ORDERDATE,"
				+ "OM.DELIVERY_DATE,OM.DUE_DATE,OM.ORDER_QTY,OM.SUPPLIER_NO,"
				+ "SM.SUPPLIER_NAME,OM.FIN_FLG,OM.ORDER_NO FROM PRODUCT_MASTER PM "
				+ "LEFT JOIN ORDER_MASTER OM ON PM.PRODUCT_NO=OM.PRODUCT_NO "
				+ "LEFT JOIN SUPPLIER_MASTER SM ON OM.SUPPLIER_NO=SM.SUPPLIER_NO "
				+ "WHERE PM.PRODUCT_NO=? AND ORDERDATE BETWEEN ? AND ? ORDER BY ORDERDATE");
		st.setString(1, num);
		st.setString(2, date1);
		st.setString(3, date2);
		ResultSet rs=st.executeQuery();
		while(rs.next()) {
			OrderList ol=new OrderList();
			ol.setProduct_no(rs.getString("product_no"));
			ol.setProduct_name(rs.getString("product_name"));
			ol.setOrder_date(rs.getString("orderdate"));
			ol.setDelivery_date(rs.getString("delivery_date"));
			ol.setDue_date(rs.getString("due_date"));
			ol.setOrder_qty(rs.getInt("order_qty"));
			ol.setSupplier_no(rs.getString("supplier_no"));
			ol.setSupplier_name(rs.getString("supplier_name"));
			ol.setFin_flg(rs.getInt("fin_flg"));
			ol.setOrder_no(rs.getString("order_no"));
			list.add(ol);
		}
		st.close();
		con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
