package mx.com.emp.service;

/**
 * 
 * @author Renata Armenta
 * 
 */

public class ServiceException extends Exception {

	/**
	 * Serial Number
	 */
	private static final long serialVersionUID = -4994967545316472029L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable e) {
		super(e);
	}

	public ServiceException(String message, Throwable e) {
		super(message, e);
	}

}
