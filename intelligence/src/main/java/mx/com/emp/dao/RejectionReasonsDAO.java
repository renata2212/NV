package mx.com.emp.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.emp.dto.RejectionReasonsDTO;

public interface RejectionReasonsDAO extends Serializable {

	RejectionReasonsDTO saveRejectionReasons(RejectionReasonsDTO rejectionReason)
			throws DaoException;

	List<RejectionReasonsDTO> getAllRejectionReasonByPersonId(Integer personId)
			throws DaoException;

	void saveRejectionReasonPerson(Integer rejectionReasonId, Integer personId)
			throws DaoException;

}
