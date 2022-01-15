package springmvc.controller;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.Valid;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import springmvc.entity.User;

// �����| : http://localhost:8080/springmvc/mvc
@Controller
@RequestMapping("/demo")
public class DemoController {

	// �l���|: /demo/hello
	@RequestMapping("/hello")
	public String hello() {
		return "hello"; // �|���V /WEB-INF/jsp/hello.jsp ����
	}

	// �l���|: /demo/helloString
	@RequestMapping("/helloString")
	@ResponseBody
	public String helloString() {
		return "hello"; // �]���� @ResponseBody ���t�m, �ҥH�����^�� "hello" �r��
	}

	// �a�J name �P age �Ѽ�
	// �l���|: /demo/sayhi?name=John&age=18
	@RequestMapping("/sayhi")
	@ResponseBody
	public String sayHi(@RequestParam(value = "name") String name, @RequestParam("age") Integer age) {
		return "Hi " + name + ", " + age;
	}

	// �a�J name(���n�Ѽ�) �P age(�D���n�Ѽ�) �Ѽ�
	// �l���|: /demo/sayhello?name=John&age=18
	// �l���|: /demo/sayhello?name=Mary
	// �l���|: /demo/sayhello <-- �Y�� defaultValue ���t�m�h�ӰѼƥi�H���γ]�w
	@RequestMapping("/sayhello")
	@ResponseBody
	public String sayHello(@RequestParam(value = "name", defaultValue = "unknow") String name,
			@RequestParam(value = "age", defaultValue = "0", required = false) Integer age) {
		return "Hello " + name + ", " + age;
	}

	// Lab:
	// �l���|: /demo/BMI?h=170&w=60
	// ��X���G: bmi: 20.76
	@RequestMapping("/BMI")
	@ResponseBody
	public String sayHello(@RequestParam(value = "h", defaultValue = "0") Double h,
			@RequestParam(value = "w", defaultValue = "0") Double w) {
		if (h <= 0 || w <= 0) {
			return "bmi: unknow";
		}
		double bmi = w / Math.pow(h / 100, 2);
		return String.format("bmi: %.2f", bmi);

	}

	// ���|�Ѽ�: PathVariable
	// �l���|: /demo/exam/75 -> ���G: 75 pass
	// �l���|: /demo/exam/45 -> ���G: 45 fail
	@RequestMapping("/exam/{score}")
	@ResponseBody
	public String exam(@PathVariable("score") Integer score) {
		return String.format("score: %d %s", score, (score >= 60) ? "Pass" : "Fail");
	}
	
	/*
	 * Lab: PathVariable + RequestParam
	 * add ��ܥ[�k, sub ��ܴ�k
	 * ���|: /demo/calc/add?x=30y=20 -> ���G:50
	 * ���|: /demo/calc/sub?x=30y=20 -> ���G:10
	 * ���|: /demo/calc/sub?y=20     -> ���G:20
	 * ���|: /demo/calc/sub?x=0&y=20 -> ���G:-20
	 * ���|: /demo/calc/add          -> ���G:0
	 * ���|: /demo/calc/abc          -> ���G:"None" <- �L�����|
	 * ����: �i�H�ϥ� Optional<Integer> �Ө��N Integer
	 * �t�m�ҭn�[�W<mvc:annotation-driven /> �~�i�H�ϥ� Optional<Integer>
	 * */
	
	// �l���| /clac/add?x=30&y=20
	
	@RequestMapping("/clac/{exp}")
	@ResponseBody
	public String clac (@PathVariable("exp") String exp,
			@RequestParam(value = "x", required = false) Optional<Integer> x,
			@RequestParam(value = "y", required = false) Optional<Integer> y) {
	
		if (x.isPresent() && y.isPresent()) {
			
			switch (exp) {
			case "add":
				return x.get() + y.get() + "";
			case "sub":
				return x.get() - y.get() + "";

			default:
				return "None";
			}
		}
		if (!exp.equals("add") || !exp.equals("sub")) return "None";
		if(x.isPresent()) return x.get() + "";
		if(x.isPresent()) return y.get() + "";
		return "0";
	}
	// PathVariable �U�Φr��: *(���N�h�r), ?(���N�@�r)
	// ���|:/demo/any/abcdefg/java8
	// ���|:/demo/any/car/java7
	// �Τ@�L�X: Java
	@RequestMapping("/any/*/java?")
	@ResponseBody
	public String any() {
		return "Java";
	}

	// RequestParam ���N�h�զP�W�Ѽ�
	// ���|: /demo/age?age=18age=21&age=35
	// ���G: �L�X�����̤j�P�̤p
	@RequestMapping("/age")
	@ResponseBody
	public String age(@RequestParam("age") List<Integer> ageList) {
		IntSummaryStatistics stat = ageList.stream().mapToInt(Integer::intValue).summaryStatistics();
		return String.format("avg: %.1f, max: %d, min: %d", stat.getAverage(), stat.getMax(), stat.getMin());
	}
	
	// Map �Ѽ� key=value �Φ�
	// ���|: /demo/score?chinese=100&english=80&math=70
	// ���G: sum: 250
	@RequestMapping("/score")
	@ResponseBody
	public String score(@RequestParam Map<String, String> scores) {
		int sum = scores.entrySet().stream().map(Entry::getValue).mapToInt(Integer::parseInt).sum();
		return String.format("sum: %d", sum);
	}
	
	// Java pojo(Plan Old Java Object) ����
	// ���|: /demo/user?name=Joe&age=18
	@RequestMapping("/user")
	@ResponseBody
	public String addUser(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return String.format("Add User Fail: %s", result);
		}
		return String.format("Add OK: %s", user);
	}
}
