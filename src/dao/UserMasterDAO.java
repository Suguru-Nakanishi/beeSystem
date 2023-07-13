package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.UserMaster;

public class UserMasterDAO extends DAO {
	private List<UserMaster> list = null;
	private Connection con;
	private PreparedStatement st;
	private String sql;
	private UserMaster user = null;
	private ResultSet rs;
	private int result;

	public UserMaster getUser() {
		return user;
	}

	public boolean searchUser(String user_id, String password) {
		sql = "SELECT*FROM USER_MASTER WHERE USER_ID='" + user_id + "'AND PASSWORD='" + password + "'";
		doQuery();
		return list.size() != 0;
	}

	private void doQuery() {
		list = new ArrayList<>();
		try {
			con = getConnection();
			st = con.prepareStatement(sql);
			listAdd();
			st.close();
			con.close();
		} catch (Exception e) {e.printStackTrace();}
	}

	private void listAdd() {
		try {
			rs = st.executeQuery();
			while (rs.next()) {
				user = new UserMaster();
				user.setUser_id(rs.getString("user_id"));
				user.setName(rs.getString("name"));
				//user.setPassword(rs.getString("password"));
				user.setPassword("********************");
				user.setDept(rs.getString("dept"));
				user.setHire_date(rs.getString("hire_date"));
				user.setEtc(rs.getString("etc"));
				user.setRegistdate(rs.getString("registdate"));
				user.setRegistuser(rs.getString("registuser"));
				list.add(user);
			}
		} catch (SQLException e) {e.printStackTrace();}
	}

	public UserMaster search(String name, String password) {
		try {
			con = getConnection();
			st = con.prepareStatement("SELECT*FROM USER_MASTER WHERE NAME=? AND PASSWORD=?");
			st.setString(1, name);
			st.setString(2, password);
			listAdd();
			st.close();
			con.close();
		} catch (Exception e) {
			System.out.println("SQLでエラーが発生しました。");
			e.printStackTrace();
		}
		return user;
	}

	public UserMaster searchById(String user_id) {
		try {
			con = getConnection();
			st = con.prepareStatement("SELECT * FROM USER_MASTER WHERE USER_ID=?");
			st.setString(1, user_id);
			rs = st.executeQuery();
			listAdd();
			st.close();
			con.close();
		} catch (Exception e) {e.printStackTrace();}
		return user;
	}

	public int insertUser(UserMaster recv_data) {
		result = 0;
		try {
			con = getConnection();
			st = con.prepareStatement("INSERT INTO USER_MASTER VALUES(LPAD(USER_ID.NEXTVAL,6,'0'),?,?,?,?,?,?,?)");
			st.setString(1, recv_data.getName());
			st.setString(2, recv_data.getPassword());
			st.setString(3, recv_data.getDept());
			st.setString(4, recv_data.getHire_date());
			st.setString(5, recv_data.getEtc());
			st.setString(6, recv_data.getRegistdate());
			st.setString(7, recv_data.getRegistuser());
			result = st.executeUpdate();
			st.close();
			con.close();
		} catch (Exception e) {e.printStackTrace();}
		return result;
	}

	public int updateUser(UserMaster recv_data) {
		searchById(recv_data.getUser_id());
		try {
			String pass=recv_data.getPassword().replace("*","");
			if (pass.equals("")) recv_data.setPassword(rs.getString("password"));
			result = 0;
			con = getConnection();
			st = con.prepareStatement("UPDATE USER_MASTER SET NAME=?,PASSWORD=?, DEPT=?, HIRE_DATE=?, ETC=?, REGISTDATE=?, REGISTUSER=? WHERE USER_ID=?");
			st.setString(1, recv_data.getName());
			st.setString(2, recv_data.getPassword());
			st.setString(3, recv_data.getDept());
			st.setString(4, recv_data.getHire_date());
			st.setString(5, recv_data.getEtc());
			st.setString(6, recv_data.getRegistdate());
			st.setString(7, recv_data.getRegistuser());
			st.setString(8, recv_data.getUser_id());
			result = st.executeUpdate();
			st.close();
			con.close();
		} catch (Exception e) {e.printStackTrace();}
		return result;
	}

	public int deleteUser(String user_id) {
		result = 0;
		try {
			con = getConnection();
			st = con.prepareStatement("DELETE FROM USER_MASTER WHERE USER_ID=?");
			st.setString(1, user_id);
			result = st.executeUpdate();
			st.close();
			con.close();
		} catch (Exception e) {e.printStackTrace();}
		return result;
	}
}