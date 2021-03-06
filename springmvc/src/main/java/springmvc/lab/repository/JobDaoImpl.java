package springmvc.lab.repository;

import java.util.List;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import springmvc.lab.entity.Job;

@Repository
public class JobDaoImpl implements JobDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Job get(Integer jid) {
		String sql = "select jid, jname, eid from job where jid=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {jid}, new BeanPropertyRowMapper<Job>(Job.class));
	}

	@Override
	public int add(Job job) {
		String sql = "insert into job (jname, eid) values(?, ?)";
		return jdbcTemplate.update(sql, job.getJname(), job.getEid());
	}

	@Override
	public int update(Job job) {
		String sql = "update job set jname=?, eid=? where jid=?";
		return jdbcTemplate.update(sql, job.getJname(), job.getEid(), job.getJid());
	}
	
	// 琩高┮Τ掸计
	@Override
	public int getCount() {
		String sql = "select count(*) from job";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	// ぃだ场琩高
	@Override
	public List<Job> query() {
		return queryPage(-1);
	}
	
	// 耞 httpSession ∕﹚琌璶だ
	@Override
	public List<Job> query(Object httpSessionValue) {
		if(httpSessionValue == null) {
			return query();
		}
		return queryPage(Integer.parseInt(httpSessionValue + ""));
	}
	
	// だ琩高
	@Override
	public List<Job> queryPage(int offset) {
		ResultSetExtractor<List<Job>> resultSetExtractor = JdbcTemplateMapperFactory.newInstance()
				.addKeys("jid")  // job 龄
				.newResultSetExtractor(Job.class);
		
		String sql = "select "
					+ "	j.jid, j.jname, j.eid, "
					+ "	e.eid as employee_eid, e.ename as employee_ename, e.salary as employee_salary, "
					+ "	e.createtime as employee_createtime "
					+ "from "
					+ "	job j, employee e "
					+ "where j.eid = e.eid "
					+ "order by j.jid ";
		// だ琩高
		if(offset >= 0) {
			sql += String.format(" limit %d offset %d ", LIMIT, offset);
		}
		List<Job> jobs = jdbcTemplate.query(sql, resultSetExtractor);
		return jobs;
	}
	
}