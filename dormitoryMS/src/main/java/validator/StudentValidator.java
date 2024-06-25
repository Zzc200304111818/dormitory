package validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import domain.Student;
import util.DBConnectionUtil;
@Component
public class StudentValidator implements Validator{

	@Override
	public boolean supports(Class<?> student) {
		return Student.class.isAssignableFrom(student);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// 强制转成校验对象
        Student stu = (Student) target;     

        // 校验必填字段
        ValidationUtils.rejectIfEmpty(errors, "stuNo", "studentRegister.stuNo.required");
        ValidationUtils.rejectIfEmpty(errors, "dormitoryName", "studentRegister.dormitoryName.required");
        ValidationUtils.rejectIfEmpty(errors, "name", "studentRegister.name.required");
        ValidationUtils.rejectIfEmpty(errors, "sex", "studentRegister.sex.required");
        ValidationUtils.rejectIfEmpty(errors, "age", "studentRegister.age.required");
        String stuNo = stu.getStuNo();
        String dormitoryName = stu.getDormitoryName();
        if(!stuNo.isEmpty() && !dormitoryName.isEmpty()) {
        	for(int i = 0; i < stuNo.length(); i++) {
        		char ch = stuNo.charAt(i);
        		if(!(ch >= '0' && ch <= '9') || stuNo.length() != 10) {
        			errors.rejectValue("stuNo", "studentRegister.stuNo.formatInvalid");
        		}
        	}
        	// 接下来检查学号是否已存在
        	DBConnectionUtil db = new DBConnectionUtil();
        	Connection conn = db.getConnection();
        	PreparedStatement stmt = null;
        	ResultSet rs = null;
        	try {
        		String sql = "select * from students where stuNo = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, stuNo);
				rs = stmt.executeQuery();
				if(rs.next()) {
					errors.rejectValue("stuNo", "studentRegister.stuNo.isOne");
				}	
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	// 接下来检查寝室是否已存在
        	try {
        		String sql = "select * from dormitories where dormitoryName = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, dormitoryName);
				rs = stmt.executeQuery();
				if(!rs.next()) {
					errors.rejectValue("dormitoryName", "studentRegister.dormitoryName.isExist");
				} else {
					int count = Integer.valueOf(rs.getString("availableBeds"));
					if(count == 0) {
						errors.rejectValue("dormitoryName", "studentRegister.dormitoryName.noMore");
					}
				}
				db.closeResources(conn, stmt, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}

}
