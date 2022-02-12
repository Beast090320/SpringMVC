package springmvc.entity;

import java.util.Arrays;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Employee {
	
	private String name; // �m�W -> text ��J�� 
	private Integer age; // �~�� -> number ��J��
	private Integer salary; // �~�� -> ��J��
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") // ��^�ɶ����(�x�W)
	@DateTimeFormat(pattern = "yyyy-MM-dd") // �����ɶ�����
	private Date birth;   // �ͤ� -> date ��J��
	private String sex;  // �ʧO -> radion ���ֿﲰ
	private String education; // �Ǿ� -> select �U�Կ�� 
	private String[] interest; // ���� -> checkbox �h��ֿﲰ
	private String resume; // �i�� -> textarea �j��r���
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String[] getInterest() {
		return interest;
	}
	public void setInterest(String[] interest) {
		this.interest = interest;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + ", salary=" + salary + ", birth=" + birth + ", sex=" + sex
				+ ", education=" + education + ", interest=" + Arrays.toString(interest) + ", resume=" + resume + "]";
	}
	
	
}
