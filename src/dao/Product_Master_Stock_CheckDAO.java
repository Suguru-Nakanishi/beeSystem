package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import bean.ProductStock;
import tool.DateUtils;

/**
 * 品番マスターに対して在庫テーブルがない場合に
 * 在庫テーブルを作成する。
 * @author Admin
 *
 */
public class Product_Master_Stock_CheckDAO extends DAO{
	public void Stock_Checker() {
		Connection con = null;
		Statement smt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			//品番マスター読み込み用
			smt = con.createStatement();
            //品番マスター読み込みSQL実行
            rs = smt.executeQuery("SELECT * FROM PRODUCT_MASTER ORDER BY PRODUCT_NO");
			//在庫マスター読み込み用
            ShipmentDAO psStock = new ShipmentDAO();
            //在庫用
 			while (rs.next()) {
				//品番マスターに一件でも登録があれば、在庫テーブルを確認する。
 				ProductStock ps_Data= psStock.searchProductStock(DateUtils.getFormatDateWithYYYYMM(),rs.getString("product_no"));

				if (ps_Data == null) {
					PreparedStatement psStock_insert = con.prepareStatement(
							"INSERT INTO PRODUCT_STOCK VALUES(?,?,0,0,0,0,TO_CHAR(SYSDATE,'YYYY/MM'))");
					psStock_insert.setString(1,DateUtils.getFormatDateWithYYYYMM());
					psStock_insert.setString(2,rs.getString("Product_no"));
					psStock_insert.execute();

				}

			}

			smt.close();
			con.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}


}
