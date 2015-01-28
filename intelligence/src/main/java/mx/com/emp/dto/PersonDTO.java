package mx.com.emp.dto;

import java.io.Serializable;

public class PersonDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 205006207105665881L;

	private Integer personId;

	private Integer statusId;

	private Integer personRelativeId;

	private Integer personTypeId;

	private String personName;

	private String personSurname;

	private String personAddress;

	private Integer personPhone;

	private Integer personMobile;

	private String personDocument;

	private String personDocumentNumber;

	private String personAdditionalData;

	private String personCompany;

	private String personDegree;

	private String personWorkingSection;

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Integer getPersonRelativeId() {
		return personRelativeId;
	}

	public void setPersonRelativeId(Integer personRelativeId) {
		this.personRelativeId = personRelativeId;
	}

	public Integer getPersonTypeId() {
		return personTypeId;
	}

	public void setPersonTypeId(Integer personTypeId) {
		this.personTypeId = personTypeId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonSurname() {
		return personSurname;
	}

	public void setPersonSurname(String personSurname) {
		this.personSurname = personSurname;
	}

	public String getPersonAddress() {
		return personAddress;
	}

	public void setPersonAddress(String personAddress) {
		this.personAddress = personAddress;
	}

	public Integer getPersonPhone() {
		return personPhone;
	}

	public void setPersonPhone(Integer personPhone) {
		this.personPhone = personPhone;
	}

	public Integer getPersonMobile() {
		return personMobile;
	}

	public void setPersonMobile(Integer personMobile) {
		this.personMobile = personMobile;
	}

	public String getPersonDocument() {
		return personDocument;
	}

	public void setPersonDocument(String personDocument) {
		this.personDocument = personDocument;
	}

	public String getPersonDocumentNumber() {
		return personDocumentNumber;
	}

	public void setPersonDocumentNumber(String personDocumentNumber) {
		this.personDocumentNumber = personDocumentNumber;
	}

	public String getPersonAdditionalData() {
		return personAdditionalData;
	}

	public void setPersonAdditionalData(String personAdditionalData) {
		this.personAdditionalData = personAdditionalData;
	}

	public String getPersonCompany() {
		return personCompany;
	}

	public void setPersonCompany(String personCompany) {
		this.personCompany = personCompany;
	}

	public String getPersonDegree() {
		return personDegree;
	}

	public void setPersonDegree(String personDegree) {
		this.personDegree = personDegree;
	}

	public String getPersonWorkingSection() {
		return personWorkingSection;
	}

	public void setPersonWorkingSection(String personWorkingSection) {
		this.personWorkingSection = personWorkingSection;
	}

}
