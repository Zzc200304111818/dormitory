package controller;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Student;
import service.StudentServiceImpl;
import validator.StudentValidator;
@RequestMapping("/student")
@Controller
public class StudentController {
	
	@Autowired
	private StudentServiceImpl service;
	@Resource
	private StudentValidator studentValidator;
	private static final Log logger = LogFactory.getLog(StudentController.class);
	@RequestMapping("/addStudent")
	public String addStudent(@ModelAttribute("stu") Student stu, BindingResult result) {
		studentValidator.validate(stu,result);
	    if(result.hasErrors()) {
	    	logger.info("添加失败");
		    return "addStudent"; 
	    }
	    if(service.addStudent(stu)) {
	    	logger.info("添加成功");
		    return "redirect:/student/getStudentList";
	    }
	    logger.info("添加失败");
	    return "addStudent";
	}
	
	@RequestMapping("/deleteStudent")
	public String deleteStudent(@RequestParam String stuNo) {
		if(service.deleteStudent(stuNo)) {
			logger.info("删除成功");
		} else {
			logger.info("删除失败");
		}
		return "redirect:/student/getStudentList";
	}
	
	@RequestMapping("/updateStudent")
	public String updateStudent(Student stu) {
		if(service.updateStudent(stu)) {
			logger.info("修改成功");
		} else {
			logger.info("修改失败");
		}
		return "redirect:/student/getStudentList";
	}
	
	@RequestMapping("/queryStudent")
	public String queryStudent(@RequestParam String stuNo, Model model) {
		Student stu = service.queryStudent(stuNo);
		ArrayList<Student> students = new ArrayList<>();
		if(stu != null) {
			students.add(stu);
			model.addAttribute("students",students);
			return "studentList";
		}
		return "empty";
	}
	
	@RequestMapping("/getStudentList")
	public String queryAllStudent(Model model) {
		ArrayList<Student> students = service.queryAllStudent();
		model.addAttribute("students", students);
		return "studentList";
	}
}
