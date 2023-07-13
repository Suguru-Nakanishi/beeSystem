package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import bean.PurchaseOrder;

public class PurchaseOrderDAO extends DAO {


		//PURCHASE_ORDERテーブルに新規登録するメソッド
		public int insertPurchaseOrder(PurchaseOrder purchase_order) {
			int result = 0;
			try {
				Calendar cl = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Connection con = getConnection();

				PreparedStatement ps = con.prepareStatement("INSERT INTO PURCHASE_ORDER VALUES('PO_' || lpad(to_char(po_no.nextval),5,'0'),"
						+ "?, ?, ?, ?, ?, ?, ?, ?, null, null)");
				ps.setString(1, purchase_order.getCm_customer_no());
				ps.setString(2, purchase_order.getPm_product_no());
				ps.setInt(3, purchase_order.getOrder_qty());
				ps.setString(4, purchase_order.getDelivery_date().replace("-", "/"));
				ps.setString(5,null);
				ps.setInt(6, 0);
				ps.setString(7, purchase_order.getRegistdate());
				ps.setString(8, purchase_order.getRegistuser());

				result = ps.executeUpdate();
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return result;
		}


	}

