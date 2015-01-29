package mx.com.emp.dto;

import java.io.Serializable;
import java.util.Date;

public class RejectionReasonsDTO implements Serializable {

	/**
	 * Serial Number
	 */
	private static final long serialVersionUID = -1980031456075191568L;

	private Integer rejectionReasonId;

	private CatalogueDTO reasonType;

	private Date rejectionDate;

	private String rejectionDescription;

	public Integer getRejectionReasonId() {
		return rejectionReasonId;
	}

	public void setRejectionReasonId(Integer rejectionReasonId) {
		this.rejectionReasonId = rejectionReasonId;
	}

	public CatalogueDTO getReasonType() {
		return reasonType;
	}

	public void setReasonType(CatalogueDTO reasonType) {
		this.reasonType = reasonType;
	}

	public Date getRejectionDate() {
		return rejectionDate;
	}

	public void setRejectionDate(Date rejectionDate) {
		this.rejectionDate = rejectionDate;
	}

	public String getRejectionDescription() {
		return rejectionDescription;
	}

	public void setRejectionDescription(String rejectionDescription) {
		this.rejectionDescription = rejectionDescription;
	}

}
