package validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import domain.Dormitory;
import util.DBConnectionUtil;
@Component
public class DormitoryValidator implements Validator{

	@Override
	public boolean supports(Class<?> dormitory) {
		// TODO Auto-generated method stub
		return Dormitory.class.isAssignableFrom(dormitory);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// 强制转成校验对象
        Dormitory dormitory = (Dormitory) target;     

        // 校验必填字段
        ValidationUtils.rejectIfEmpty(errors, "dormitoryName", "dormitoryRegister.dormitoryName.required");
        ValidationUtils.rejectIfEmpty(errors, "totalBeds", "dormitoryRegister.totalBeds.required");
        ValidationUtils.rejectIfEmpty(errors, "availableBeds", "dormitoryRegister.availableBeds.required");
        ValidationUtils.rejectIfEmpty(errors, "remark", "dormitoryRegister.remark.required");
        String dormitoryName = dormitory.getDormitoryName();
        if(!dormitoryName.isEmpty()) {
        	int length = dormitoryName.length();
        	if(length != 5 || dormitoryName.charAt(length - 1) != '室' || dormitoryName.charAt(length - 2) != '寝') {
        		errors.rejectValue("dormitoryName", "dormitoryRegister.dormitoryName.formatInvalid");
        	}
        	// 接下来检查寝室是否已存在
        	DBConnectionUtil db = new DBConnectionUtil();
        	Connection conn = db.getConnection();
        	PreparedStatement stmt = null;
        	ResultSet rs = null;
        	try {
        		String sql = "select * from dormitories where dormitoryName = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, dormitoryName);
				rs = stmt.executeQuery();
				if(rs.next()) {
					errors.rejectValue("dormitoryName", "dormitoryRegister.dormitoryName.isOne");
				}
				db.closeResources(conn, stmt, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}

}
