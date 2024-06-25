package validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import domain.User;
import util.DBConnectionUtil;
@Component
public class RegisterValidator implements Validator {

	@Override
	public boolean supports(Class<?> user) {
		return User.class.isAssignableFrom(user);
	}

	@Override
	public void validate(Object target, Errors errors) {
		 // 强制转成校验对象
        User user = (User) target;     

        // 校验必填字段
        ValidationUtils.rejectIfEmpty(errors, "username", "register.username.required");
        ValidationUtils.rejectIfEmpty(errors, "password", "register.password.required");
        String username = user.getUsername();
        String password = user.getPassword();
        if(!username.isEmpty()) {
        	char ch = username.charAt(0);
        	int length = username.length();
        	if(ch < 65 || (ch > 90 && ch < 97) || ch > 122) {
        		errors.rejectValue("username", "register.username.formatInvalid");
        	}
        	if(!(length >= 5 && length <= 8)) {
        		errors.rejectValue("username", "register.username.lengthInvalid");
        	}
        	// 接下来检查用户名是否已存在
        	DBConnectionUtil db = new DBConnectionUtil();
        	Connection conn = db.getConnection();
        	PreparedStatement stmt = null;
        	ResultSet rs = null;
        	try {
        		String sql = "select * from users where username = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, username);
				rs = stmt.executeQuery();
				if(rs.next()) {
					errors.rejectValue("username", "register.username.isOne");
				}
				db.closeResources(conn, stmt, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        if(password != null) {
        	if(password.length() >= 8) {
        		errors.rejectValue("password", "register.password.invalid");
        	}
        }
	}

}
