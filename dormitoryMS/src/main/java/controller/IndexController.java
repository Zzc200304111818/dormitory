package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import domain.Dormitory;
import domain.Student;
import domain.User;
import domain.UserForm;
import util.CreateRandom;


@Controller
@RequestMapping("/index")
public class IndexController {
	
	@RequestMapping("/login")
	public String login(@ModelAttribute UserForm userForm) {
		return "login";
	}
	
	@RequestMapping("/register")
	public String register(@ModelAttribute User user) {
		
		return "register";
	}
	
	@RequestMapping("/getCode")
	public void getCode(HttpServletRequest request, HttpServletResponse response) {
		int width = 60;
		int height = 20;
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, width, height);
		String code = CreateRandom.getRandomCode();
		HttpSession session = request.getSession();
		session.setAttribute("realCode", code);
		for(int i = 0; i<code.length(); i++)
		{
			char c = code.charAt(i);
			g.setColor(CreateRandom.getRandomColor());
			g.setFont(new Font("宋体", Font.BOLD, 20));
			g.drawString(c + "", 5 + 12*i, 20);
		}
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ImageIO.write(img, "jpg", out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getHome")
	public String getHome() {
		return "home";
	}
	
	@RequestMapping("/getUserList")
	public String getUserList() {
		return "userList";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		model.addAttribute("userForm", new UserForm());
		return "login";
	}
	
	@RequestMapping("/getAddStudent")
	public String getAddStudent(@ModelAttribute("stu") Student stu) {
		return "addStudent";
	}
	
	@RequestMapping("/getAddDormitory")
	public String getAddDormitory(@ModelAttribute Dormitory dormitory) {
		return "addDormitory";
	}
	
	@RequestMapping("/getQueryDormitory")
	public String getQueryDormitory() {
		return "queryDormitory";
	}
	
	@RequestMapping("/getQueryStudent")
	public String getQueryStudent() {
		return "queryStudent";
	}
}
