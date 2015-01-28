package mx.com.emp.dto;

import java.io.Serializable;

public class CatalogueDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6714167012708912655L;

	private Integer catalogueId;

	private String catalogueDescription;

	public Integer getCatalogueId() {
		return catalogueId;
	}

	public void setCatalogueId(Integer catalogueId) {
		this.catalogueId = catalogueId;
	}

	public String getCatalogueDescription() {
		return catalogueDescription;
	}

	public void setCatalogueDescription(String catalogueDescription) {
		this.catalogueDescription = catalogueDescription;
	}

}
