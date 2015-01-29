package mx.com.emp.controllers;

import mx.com.emp.dto.UserDTO;
import mx.com.emp.service.ServiceException;
import mx.com.emp.service.UserService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	private Logger LOGGER = Logger.getLogger(UserController.class);

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public static final String VALIDATE_USER = "/validate-user";

	@RequestMapping(value = VALIDATE_USER, method = RequestMethod.GET)
	public String validateUser(String nickName, String password, ModelMap model)
			throws ServiceException {

		if (LOGGER.isDebugEnabled()) {

			LOGGER.debug(" -- Validar Usuario --");

		}

		model.addAttribute("message", "Validate User");

		nickName = "emp_admin";
		password = "emp";

		UserDTO user = userService.validateUser(nickName, password);

		System.out.println(user.getUserName());
		System.out.println(user.getUserNickname());
		System.out.println(user.getUserSurname());
		System.out.println(user.getStatusId());
		System.out.println(user.getUserId());

		return "createVehicle";
	}

}
