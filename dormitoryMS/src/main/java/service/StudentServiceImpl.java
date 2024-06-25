package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DormitoryDao;
import dao.StudentDao;
import domain.Student;
import util.DBConnectionUtil;
@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private DormitoryDao dormitoryDao;
	@Override
	public boolean addStudent(Student stu) {
		String sql = "insert into students(stuNo, name, sex, age, dormitoryName) values(?, ?, ?, ?, ?)";
	    if(studentDao.addStudent(sql, stu) > 0) {
	    	String updateSql = "update dormitories set availableBeds = availableBeds - 1 where dormitoryName = ?";
	    	if(dormitoryDao.updateBeds(updateSql, stu.getDormitoryName()) > 0) 
	    		return true;// 添加成功，返回true
	    }
	    return false;   
	}

	@Override
	public boolean deleteStudent(String stuNo) {

		DBConnectionUtil db = new DBConnectionUtil();
		Connection conn = db.getConnection();
		String dormitoryName = null;
		try {
			String selectSql = "select * from students where stuNo = ?";
			PreparedStatement stmt = conn.prepareStatement(selectSql);
			stmt.setString(1, stuNo);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				dormitoryName = rs.getString("dormitoryName");			
			}
			db.closeResources(conn, stmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "delete from students where stuNo = ?";
		if(studentDao.deleteStudent(sql, stuNo) > 0) {
			String updateSql = "update dormitories set availableBeds = availableBeds + 1 where dormitoryName = ?";
			if(dormitoryDao.updateBeds(updateSql, dormitoryName) > 0)	return true;
		}
		return false;
	}

	@Override
	public Student queryStudent(String stuNo) {
		String sql = "select * from students where stuNo = ?";
		return studentDao.queryStudent(sql, stuNo);
	}

	@Override
	public ArrayList<Student> queryAllStudent() {
		String sql = "select * from students";
		return studentDao.queryAllStudent(sql);
	}

	@Override
	public boolean updateStudent(Student stu) {
		String sql = "update students set name = ?, sex = ?, age = ? where stuNo = ?";
		if(studentDao.updateStudent(sql, stu) > 0) return true;
		return false;
	}

}
