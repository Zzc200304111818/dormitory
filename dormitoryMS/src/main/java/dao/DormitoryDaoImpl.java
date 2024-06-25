package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import domain.Dormitory;
import util.DBConnectionUtil;
@Repository
public class DormitoryDaoImpl implements DormitoryDao {

	@Override
	public int updateBeds(String sql, String dormitoryName) {
		int count = 0;
		DBConnectionUtil db = new DBConnectionUtil();
		Connection conn = db.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, dormitoryName);
			count = stmt.executeUpdate();
			db.closeResources(conn, stmt, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int addDormitory(String sql, Dormitory dormitory) {
		int count = 0;
		DBConnectionUtil db = new DBConnectionUtil();
		Connection conn = db.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, dormitory.getDormitoryName());
			stmt.setString(2, dormitory.getTotalBeds().toString());
			stmt.setString(3, dormitory.getAvailableBeds().toString());
			stmt.setString(4, dormitory.getRemark());
			count = stmt.executeUpdate();
			db.closeResources(conn, stmt, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int deleteDormitory(String sql, String dormitoryName) {
		int count = 0;
		DBConnectionUtil db = new DBConnectionUtil();
		Connection conn = db.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, dormitoryName);
			count = stmt.executeUpdate();
			db.closeResources(conn, stmt, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public Dormitory queryDormitory(String sql, String dormitoryName) {
		DBConnectionUtil db = new DBConnectionUtil();
		Connection conn = db.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dormitoryName);
			rs = stmt.executeQuery();
			if(rs.next()) {
				Dormitory dormitory = new Dormitory();
				dormitory.setDormitoryName(rs.getString("dormitoryName"));
				dormitory.setTotalBeds(Integer.valueOf(rs.getString("totalBeds")));
				dormitory.setAvailableBeds(Integer.valueOf(rs.getString("availableBeds")));
				dormitory.setRemark(rs.getString("remark"));
				return dormitory;
			}
			db.closeResources(conn, stmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Dormitory> queryAllDormitory(String sql) {
		DBConnectionUtil db = new DBConnectionUtil();
		Connection conn = db.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Dormitory> dormitories = new ArrayList<>();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Dormitory dormitory = new Dormitory();
				dormitory.setDormitoryName(rs.getString("dormitoryName"));
				dormitory.setTotalBeds(Integer.valueOf(rs.getString("totalBeds")));
				dormitory.setAvailableBeds(Integer.valueOf(rs.getString("availableBeds")));
				dormitory.setRemark(rs.getString("remark"));
				dormitories.add(dormitory);
			}
			db.closeResources(conn, stmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dormitories;
	}

	@Override
	public int updateDormitory(String sql, Dormitory dormitory) {
		int count = 0;
		DBConnectionUtil db = new DBConnectionUtil();
		Connection conn = db.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, dormitory.getTotalBeds().toString());
			stmt.setString(2, dormitory.getAvailableBeds().toString());
			stmt.setString(3, dormitory.getRemark());
			stmt.setString(4, dormitory.getDormitoryName());
			count = stmt.executeUpdate();
			db.closeResources(conn, stmt, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
}
