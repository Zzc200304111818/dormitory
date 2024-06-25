package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import domain.Student;
import util.DBConnectionUtil;
@Repository
public class StudentDaoImpl implements StudentDao {

	@Override
	public int addStudent(String sql, Student stu) {
		int count = 0;
		DBConnectionUtil db = new DBConnectionUtil();
		Connection conn = db.getConnection();
		PreparedStatement stmt = null;
		try {
			PreparedStatement prepareStatement = conn.prepareStatement("select * from dormitories where dormitoryName = ?");
			prepareStatement.setString(1,stu.getDormitoryName());
			ResultSet rs = prepareStatement.executeQuery();
			if(rs.next()) {
				if(Integer.valueOf(rs.getString("availableBeds")) > 0) {
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, stu.getStuNo());
					stmt.setString(2, stu.getName());
					stmt.setString(3, stu.getSex());
					stmt.setString(4, stu.getAge().toString());
					stmt.setString(5, stu.getDormitoryName());
					count = stmt.executeUpdate();
				}
				rs.close();
				prepareStatement.close();
			}
			db.closeResources(conn, stmt, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int deleteStudent(String sql, String stuNo) {
		int count = 0;
		DBConnectionUtil db = new DBConnectionUtil();
		Connection conn = db.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, stuNo);
			count = stmt.executeUpdate();
			db.closeResources(conn, stmt, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public Student queryStudent(String sql, String stuNo) {
		DBConnectionUtil db = new DBConnectionUtil();
		Connection conn = db.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, stuNo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				Student stu = new Student();
				stu.setStuNo(rs.getString("stuNo"));
				stu.setDormitoryName(rs.getString("dormitoryName"));
				stu.setName(rs.getString("name"));
				stu.setAge(Integer.valueOf(rs.getString("age")));
				stu.setSex(rs.getString("sex"));
				return stu;
			}
			db.closeResources(conn, stmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Student> queryAllStudent(String sql) {
		DBConnectionUtil db = new DBConnectionUtil();
		Connection conn = db.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Student> students = new ArrayList<>();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Student stu = new Student();
				stu.setStuNo(rs.getString("stuNo"));
				stu.setName(rs.getString("name"));
				stu.setAge(Integer.valueOf(rs.getString("age")));
				stu.setSex(rs.getString("sex"));
				stu.setDormitoryName(rs.getString("dormitoryName"));
				students.add(stu);
			}
			db.closeResources(conn, stmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return students;
	}

	@Override
	public int updateStudent(String sql, Student stu) {
		int count = 0;
		DBConnectionUtil db = new DBConnectionUtil();
		Connection conn = db.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, stu.getName());
			stmt.setString(2, stu.getSex());
			stmt.setString(3, stu.getAge().toString());
			stmt.setString(4, stu.getStuNo());
			count = stmt.executeUpdate();
			db.closeResources(conn, stmt, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

}
