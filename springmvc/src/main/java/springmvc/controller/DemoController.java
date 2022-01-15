package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String sayHi(@RequestParam(value = "name") String name,
						@RequestParam("age") Integer age) {
		return "Hi " + name + ", " + age;
	}
	
	// �a�J name(���n�Ѽ�) �P age(�D���n�Ѽ�) �Ѽ�
	// �l���|: /demo/sayhello?name=John&age=18
	// �l���|: /demo/sayhello?name=Mary
	// �l���|: /demo/sayhello  <-- �Y�� defaultValue ���t�m�h�ӰѼƥi�H���γ]�w
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
	public String sayHello(@RequestParam(value = "h", defaultValue = "0" ) Double h,						   
						   @RequestParam(value = "w", defaultValue = "0" ) Double w) {
			if(h <=0 || w <=0) {
				return "bmi: unknow";
			}
		double bmi = w / Math.pow(h/100, 2);
		return String.format("bmi: %.2f", bmi);
			
	}	
}