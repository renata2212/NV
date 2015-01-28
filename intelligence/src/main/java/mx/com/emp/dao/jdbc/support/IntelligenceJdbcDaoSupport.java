package mx.com.emp.dao.jdbc.support;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * @author Renata Armenta
 * 
 * Clase de soporte para capa de persistencia, define
 * Template JDBC y DataSource intelligenceDS
 */
public abstract class IntelligenceJdbcDaoSupport {

	@Resource(name = "intelligenceDS")
	private DataSource dataSource;
	protected JdbcTemplate jdbcTemplate;
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@PostConstruct
	public void initialize() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
		System.out.print("---- " + jdbcTemplate);

	}
}
