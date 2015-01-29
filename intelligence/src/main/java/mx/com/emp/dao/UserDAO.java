package mx.com.emp.dao;

import java.io.Serializable;

import mx.com.emp.dto.UserDTO;

public interface UserDAO extends Serializable {
	
	UserDTO validateUser(String nickName, String password) throws DaoException;

}
