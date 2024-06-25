package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import domain.User;
import util.DBConnectionUtil;
@Repository
public class UserDaoImpl implements UserDao{

	@Override
	public int addUser(String sql, User user) {
		int count = 0;
		DBConnectionUtil db = new DBConnectionUtil();
		Connection conn = db.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			count = stmt.executeUpdate();
			db.closeResources(conn, stmt, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int deleteUser(String sql, String username) {
		int count = 0;
		DBConnectionUtil db = new DBConnectionUtil();
		Connection conn = db.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			count = stmt.executeUpdate();
			db.closeResources(conn, stmt, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
}
