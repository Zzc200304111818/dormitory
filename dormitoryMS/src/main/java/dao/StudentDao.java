package dao;

import java.util.ArrayList;

import domain.Student;

public interface StudentDao {
	int addStudent(String sql, Student stu);
	int deleteStudent(String sql, String stuNo);
	int updateStudent(String sql, Student stu);
	Student queryStudent(String sql, String stuNo);
	ArrayList<Student> queryAllStudent(String sql);
}
