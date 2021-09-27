package com.nagarro.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nagarro.dao.EmployeeRepository;
import com.nagarro.dao.ManagerRepository;
import com.nagarro.entities.EmployeeDetail;
import com.nagarro.entities.Manager;


@Controller
public class HomeController {

	@Autowired
	private ManagerRepository managerRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	private String manageName;

	@ModelAttribute
	public void addCommonData(Model model) {
		String manName = manageName;
		System.out.println(manName);
		model.addAttribute("managerName", manName);
	}

	public HomeController() {
		super();
	}

	// For Login Page
	@RequestMapping("/")
	public String login(Model model) {
		model.addAttribute("title", "HR Manager Login");
		model.addAttribute("manager", new Manager());
		return "login";
	}

	// For Employee Detail List
	@GetMapping("/details")
	public String details(Model model) {
		model.addAttribute("title", "Employee List");
		List<EmployeeDetail> l = this.employeeRepository.findAll();
		System.out.println(l);
		model.addAttribute("employees", l);
		return "details";
	}

	@PostMapping("/details")
	public String detail(Model model) {
		model.addAttribute("title", "Employee List");
		return "details";
	}

	// Validating manager after login
	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public String validate(@Valid @ModelAttribute("manager") Manager u, BindingResult res, Model m,
			HttpSession session) {
		if (res.hasErrors()) {
			return "login";
		}

		try {
			List<Manager> list = this.managerRepository.findAll();
			System.out.println(list);
			for (Manager man : list) {
				if (man.getId().equals(u.getId()) && man.getPassword().equals(u.getPassword())) {
					System.out.println("true");
					manageName = u.getId();
					System.out.println(manageName);
					m.addAttribute("title", "Employee List");
					List<EmployeeDetail> l = this.employeeRepository.findAll();
					System.out.println(l);
					m.addAttribute("employees", l);
				}
			}
			return "details";
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("manager", u);
			return "login";
		}
	}

	// Upload Employee Detail Page
	@GetMapping("/add-detail")
	public String addDetail(Model model) {
		model.addAttribute("title", "Add Employee");
		model.addAttribute("employeeDetail", new EmployeeDetail());
		return "add-detail";
	}

	// To save new employee details
	@PostMapping("/process-details")
	public String processDetail(@Valid @ModelAttribute EmployeeDetail employeeDetail, BindingResult res, Model m) {
		System.out.println(employeeDetail);
		if (res.hasErrors()) {
			System.out.println(res);
			return "add-detail";
		}
		this.employeeRepository.save(employeeDetail);
		return "redirect:/details";
	}

	// To go to edit employee details page
	@PostMapping("/edit/{code}")
	public String editDetail(@PathVariable("code") Integer code, Model m) {
		m.addAttribute("title", "Update Employee Detail");
		EmployeeDetail employeeDetail = this.employeeRepository.findById(code).get();
		m.addAttribute("employeeDetail", employeeDetail);
		return "edit-detail";
	}

	// To edit details
	@PostMapping("/process-edit")
	public String editHandler(@ModelAttribute EmployeeDetail employeeDetail, Model m) {
		this.employeeRepository.save(employeeDetail);
		return "redirect:/details";
	}

}
