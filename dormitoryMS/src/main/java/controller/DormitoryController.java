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

import domain.Dormitory;
import service.DormitoryService;
import validator.DormitoryValidator;
@RequestMapping("/dormitory")
@Controller
public class DormitoryController {
	@Resource
	private DormitoryValidator dormitoryValidator;
	@Autowired
	private DormitoryService service;
	private static final Log logger = LogFactory.getLog(DormitoryController.class);
	@RequestMapping("/addDormitory")
	public String addDormitory(@ModelAttribute Dormitory dormitory, BindingResult result) {
		dormitoryValidator.validate(dormitory, result);
		if(result.hasErrors()) {
			logger.info("添加失败");
			return "addDormitory";
		}
		if(service.addDormitory(dormitory)) {
			logger.info("添加成功");
			return "redirect:/dormitory/getDormitoryList";
		}
		logger.info("添加失败");
		return "addDormitory";
	}
	
	@RequestMapping("/deleteDormitory")
	public String deleteDormitory(@RequestParam String dormitoryName) {
		if(service.deleteDormitory(dormitoryName)) {
			logger.info("删除成功");
		} else {
			logger.info("删除失败");
		}
		return "redirect:/dormitory/getDormitoryList";
	}
	
	@RequestMapping("/updateDormitory")
	public String updateDormitory(Dormitory dormitory) {
		if(service.updateDormitory(dormitory)) {
			logger.info("修改成功");
		} else {
			logger.info("修改失败");
		}
		return "redirect:/dormitory/getDormitoryList";
	}
	
	@RequestMapping("/queryDormitory")
	public String queryDormitory(@RequestParam String dormitoryName, Model model) {
		Dormitory dormitory = service.queryDormitory(dormitoryName);
		ArrayList<Dormitory> dormitories = new ArrayList<>();
		if(dormitory != null) {
			dormitories.add(dormitory);
			model.addAttribute("dormitories", dormitories);
			return "dormitoryList";
		}
		return "empty";
	}
	
	@RequestMapping("getDormitoryList")
	public String queryAllDormitory(Model model) {
		ArrayList<Dormitory> dormitories = service.queryAllDormitory();
		model.addAttribute("dormitories", dormitories);
		return "dormitoryList";
	}
}
