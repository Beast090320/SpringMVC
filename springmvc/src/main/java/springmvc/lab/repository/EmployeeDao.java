package springmvc.lab.repository;

import java.util.List;

import springmvc.lab.entity.Employee;

public interface EmployeeDao {
	// �d�ߥ������-������
	public List<Employee> query();
	
	// �d�ߥ������-�z�L httpSessionValue �ȨM�w�O�_�n����
	public List<Employee> query(Object httpSessionValue);
	
	// �d�߸��-�����d�� (offset �q�ĴX���}�l�d)
	public List<Employee> queryPage(int offset);

	// ���o�浧���
	public Employee get(Integer eid);
	
	// �s�W
	public int add(Employee employee);

	// �ק�
	public int update(Employee employee);

	// �R��
	public int delete(Integer eid);
	
}