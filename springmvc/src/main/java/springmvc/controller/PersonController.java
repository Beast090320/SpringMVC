package springmvc.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springmvc.entity.Person;

@Controller
@RequestMapping("/person")
public class PersonController {
	
	private List<Person> people = new CopyOnWriteArrayList<>();
	
	// @ModelAttribute Person person �|�۰ʷ|��J person ���� view
	// �۰ʰ��� model.addAttribute("person", person);
	@GetMapping("/")
	public String index(@ModelAttribute Person person, Model model) {
		person.setAge(18);
		model.addAttribute("people", people);
		return "person/index";
	}
	/*
	// ��ʥ�J person ����, �ĪG���P��W��
	@GetMapping("/")
	public String index(Model model) {
		Person person = new Person();
		person.setAge(18);
		model.addAttribute("person", person);
		model.addAttribute("people", people);
		return "person/index";
	}
	*/
	
	// @Valid Person person -> person ����ݸg�L����
	// BindingResult result -> �����W�z�����ҵ��G
	@PostMapping("/")
	public String add(Model model, @Valid Person person, BindingResult result) {
		// �P�_�O�_�����~�o�� ?
		if(result.hasErrors()) {
			// �|�۰ʱN���~�T�������w view ���� ("person/index") ��
			model.addAttribute("people", people);
			return "person/index";
		}
		people.add(person);
		return "redirect:./";
	}
	
	
}