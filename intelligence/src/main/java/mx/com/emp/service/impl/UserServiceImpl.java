package mx.com.emp.service.impl;

import javax.annotation.Resource;

import mx.com.emp.dao.DaoException;
import mx.com.emp.dao.UserDAO;
import mx.com.emp.dto.UserDTO;
import mx.com.emp.service.ServiceException;
import mx.com.emp.service.UserService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional(value = "txManager", rollbackFor = ServiceException.class, readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class UserServiceImpl implements UserService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8222440150699212095L;

	private Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

	@Resource
	private UserDAO userDAO;

	@Override
	public UserDTO validateUser(String nickName, String password)
			throws ServiceException {

		try {
			return userDAO.validateUser(nickName, password);
		} catch (DaoException ex) {
			LOGGER.error("Mensaje de error: " + ex);
			throw new ServiceException(ex.getMessage(), ex);
		}
	}

}
