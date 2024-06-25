package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.User;
import domain.UserForm;
import service.UserServiceImpl;
import util.DBConnectionUtil;
import validator.LoginValidator;
import validator.RegisterValidator;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServiceImpl service;
	@Resource
	private RegisterValidator registerValidator;
	@Resource 
	private LoginValidator loginValidator;
	private static final Log logger = LogFactory.getLog(UserController.class);

	@RequestMapping("/addUser")  
	public String addUser(@ModelAttribute User user, BindingResult result) {  
		registerValidator.validate(user, result);
	    if(result.hasErrors()) {
	    	logger.info("添加失败");
		    return "register"; 
	    }
	    if(service.addUser(user)) {
	    	logger.info("添加成功");
		    return "redirect:/index/login";
	    }
	    logger.info("添加失败");
	    return "register";
	}
	
	@RequestMapping("/doLogin")
	public String doLogin(@ModelAttribute UserForm userForm, BindingResult result, HttpSession session) {
		User user = new User();
		user.setUsername(userForm.getUsername());
		user.setPassword(userForm.getPassword());
		loginValidator.validate(user, result);
		if(result.hasErrors()) {
			logger.info("登录失败");
			return "login";
		}
		if(!session.getAttribute("realCode").equals(userForm.getCode())){
			logger.info("验证码错误");
			return "login";
		}
		logger.info("登录成功");
		session.setAttribute("user", user);
		return "redirect:/index/getHome";
	}
	
	@RequestMapping("/deleteUser")
	public String deleteUser(Model model, HttpSession session) {
		User user = (User)session.getAttribute("user");
		String username = user.getUsername();
		model.addAttribute("user", new User());
		if(service.deleteUser(username)) {
			logger.info("注销成功");
			return "register";
		}
		logger.info("注销失败");
		return "home";
	}
	
	@RequestMapping("/updatePassword")
	public String updatePassword(HttpSession session, @RequestParam String newPassword) {
		User user = (User)session.getAttribute("user");
		DBConnectionUtil db = new DBConnectionUtil();
		Connection conn = db.getConnection();
		PreparedStatement stmt = null;
		String sql = "update users set password = ? where username = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, newPassword);
			stmt.setString(2, user.getUsername());
			int count = stmt.executeUpdate();
			if(count > 0) {
				user.setPassword(newPassword);
				logger.info("修改成功");
				
			} else {
				logger.info("修改失败");
			}
			db.closeResources(conn, stmt, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return "redirect:/index/getUserList";
	}
}
