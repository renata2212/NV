package mx.com.emp.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import mx.com.emp.dao.DaoException;
import mx.com.emp.dao.UserDAO;
import mx.com.emp.dao.jdbc.support.IntelligenceJdbcDaoSupport;
import mx.com.emp.dto.UserDTO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDAO")
@Transactional(value = "txManager", rollbackFor = { DaoException.class }, readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class UserDAOImpl extends IntelligenceJdbcDaoSupport implements UserDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -915685336546894477L;

	private Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

	@Value("${database.generic}")
	private String genericError;

	@Override
	public UserDTO validateUser(String nickName, String password)
			throws DaoException {

		try {
			StringBuilder sQuery = new StringBuilder(
					"SELECT user_id, user_name, user_surname, user_nickname, status_status_id FROM USER "
							+ "WHERE user_nickname = ? AND user_password = ? ");

			return jdbcTemplate.queryForObject(sQuery.toString(), new Object[] {
					nickName, password }, new int[] { Types.VARCHAR,
					Types.VARCHAR }, new UserMapper());

		} catch (Exception ex) {
			LOGGER.error("Mensaje de error" + ex);
			throw new DaoException(genericError, ex);
		}
	}

	/**
	 * Clase para mapeo de usuarios
	 *
	 **/
	private static final class UserMapper implements RowMapper<UserDTO> {

		private Map<String, UserDTO> userMap;

		public UserMapper() {
			userMap = new HashMap<String, UserDTO>();
		}

		@Override
		public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

			UserDTO user = null;

			if (userMap.containsKey(rs.getString("user_id")))
				user = userMap.get(rs.getString("user_id"));
			else
				user = new UserDTO();

			user.setUserId(new Integer(rs.getString("user_id")));
			user.setUserName((rs.getString("user_name")));
			user.setUserSurname(rs.getString("user_surname"));
			user.setUserNickname(rs.getString("user_nickname"));
			user.setStatusId(new Integer(rs.getString("status_status_id")));

			userMap.put(user.getUserId().toString(), user);

			return user;
		}
	}

}
