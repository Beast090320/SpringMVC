package springmvc.lab.repository;

import java.util.List;

import springmvc.lab.entity.Job;

public interface JobDao {
	// �C������
	int LIMIT = 5;

	// ���o�浧 job ���
	public Job get(Integer jid);

	// �s�W job ���
	public int add(Job job);

	// �ק� job ���
	public int update(Job job);

	// �d�ߩҦ�����
	public int getCount();

	// �����������d��
	public List<Job> query();

	// �P�_ httpSession �ȨM�w�O�_�n����
	public List<Job> query(Object httpSessionValue);

	// �����d��
	public List<Job> queryPage(int offset);

}