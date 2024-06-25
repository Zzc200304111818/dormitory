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
public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> user) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(user);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User)target;
		ValidationUtils.rejectIfEmpty(errors, "username", "login.username.required");
        ValidationUtils.rejectIfEmpty(errors, "password", "login.password.required");
		String username = user.getUsername();
		String password = user.getPassword();
		try {
			String sql = "select password from users where username = ?";
			DBConnectionUtil db = new DBConnectionUtil();
			Connection conn = db.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				if(!rs.getString("password").equals(password)) {
					errors.rejectValue("password","login.password.isError");
				}
			} else errors.rejectValue("username","login.username.isExist");;
			db.closeResources(conn, stmt, rs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
