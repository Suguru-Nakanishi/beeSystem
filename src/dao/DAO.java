package dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {
	//データソースを保存する変数宣言
	static DataSource ds;

	//データベースへの接続を取得するためのgetConnectionメソッド
	public Connection getConnection() throws Exception {
		if (ds == null) {
			//データソースの初期化
			InitialContext ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:/comp/env/jdbc/beeSystem");
		}
		return ds.getConnection();//接続の取得、呼び出し元に返す
	}
}
