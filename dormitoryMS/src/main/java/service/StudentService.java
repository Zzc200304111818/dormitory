package service;

import java.util.ArrayList;

import domain.Student;

public interface StudentService {
	boolean addStudent(Student stu);
	boolean deleteStudent(String stuNo);
	Student queryStudent(String stuNo);
	ArrayList<Student> queryAllStudent();
	boolean updateStudent(Student stu);
}
