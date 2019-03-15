package cn.atcast.a_tx;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * dao实现，使用Spring对jdbc支持功能
 *
 */
public class DeptDao {

	// 容器注入JdbcTemplate对象
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void save(Dept dept) {
		String sql = "insert into t_dept (deptName) values(?)";
		jdbcTemplate.update(sql, dept.getDeptName());
	}
}
