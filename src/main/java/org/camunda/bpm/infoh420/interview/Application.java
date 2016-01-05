package org.camunda.bpm.infoh420.interview;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents an application for a job opportunity. 
 *
 */

public class Application implements Serializable {

	private static  final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	protected Long id;

	@Version
	protected long version;

	protected ContactEntity contact;
	protected ArrayList<DegreeEntity> degrees;
	protected ArrayList<JobEntity> experiences;

	protected boolean approved;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	public long getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(long version) {
		this.version = version;
	}

	/**
	 * @return the contact
	 */
	public ContactEntity getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(ContactEntity contact) {
		this.contact = contact;
	}

	/**
	 * @return the degrees
	 */
	public ArrayList<DegreeEntity> getDegrees() {
		return degrees;
	}

	/**
	 * @param degrees the degrees to set
	 */
	public void setDegrees(ArrayList<DegreeEntity> degrees) {
		this.degrees = degrees;
	}

	/**
	 * @return the experiences
	 */
	public ArrayList<JobEntity> getExperiences() {
		return experiences;
	}

	/**
	 * @param experiences the experiences to set
	 */
	public void setExperiences(ArrayList<JobEntity> experiences) {
		this.experiences = experiences;
	}

	/**
	 * @return the approved
	 */
	public boolean isApproved() {
		return approved;
	}

	/**
	 * @param approved the approved to set
	 */
	public void setApproved(boolean approved) {
		this.approved = approved;
	}


}
