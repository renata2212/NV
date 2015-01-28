package mx.com.emp.dao;

/**
 * 
 * @author Renata Armenta
 * 
 */
public class DaoException extends Exception {

	/**
	 * Serial Number
	 */
	private static final long serialVersionUID = -8325192559118097204L;

	public DaoException() {
		super();
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable e) {
		super(e);
	}

	public DaoException(String message, Throwable e) {
		super(message, e);
	}

}
