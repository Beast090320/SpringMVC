package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// 母路徑 : http://localhost:8080/springmvc/mvc
@Controller
@RequestMapping("/demo")
public class DemoController {
	
	// 子路徑: /demo/hello
	@RequestMapping("/hello")
	public String hello() {
		return "hello"; // 會指向 /WEB-INF/jsp/hello.jsp 頁面
	}
	
	// 子路徑: /demo/helloString
	@RequestMapping("/helloString")
	@ResponseBody
	public String helloString() {
		return "hello"; // 因為有 @ResponseBody 的配置, 所以直接回傳 "hello" 字串
	}
	
	// 帶入 name 與 age 參數
	// 子路徑: /demo/sayhi?name=John&age=18
	@RequestMapping("/sayhi")
	@ResponseBody
	public String sayHi(@RequestParam(value = "name") String name,
						@RequestParam("age") Integer age) {
		return "Hi " + name + ", " + age;
	}
	
	// 帶入 name(必要參數) 與 age(非必要參數) 參數
	// 子路徑: /demo/sayhello?name=John&age=18
	// 子路徑: /demo/sayhello?name=Mary
	// 子路徑: /demo/sayhello  <-- 若有 defaultValue 的配置則該參數可以不用設定
	@RequestMapping("/sayhello")
	@ResponseBody
	public String sayHello(@RequestParam(value = "name", defaultValue = "unknow") String name,
						   @RequestParam(value = "age", defaultValue = "0", required = false) Integer age) {
		return "Hello " + name + ", " + age;
	}
	
	// Lab:
	// 子路徑: /demo/BMI?h=170&w=60
	// 輸出結果: bmi: 20.76
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