package mx.com.emp.service;

import java.io.Serializable;

import mx.com.emp.dto.UserDTO;

public interface UserService extends Serializable {

	UserDTO validateUser(String nickName, String password)
			throws ServiceException;

}