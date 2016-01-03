package org.camunda.bpm.infoh420.interview;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents an application for a job opportunity. 
 *
 */

@Entity
public class ApplicationEntity implements Serializable {

	private static  final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	protected Long id;

	@Version
	protected long version;

	protected Contact contact;
	protected ArrayList<Degree> degrees;
	protected ArrayList<Job> experiences;

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
	public Contact getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}

	/**
	 * @return the degrees
	 */
	public ArrayList<Degree> getDegrees() {
		return degrees;
	}

	/**
	 * @param degrees the degrees to set
	 */
	public void setDegrees(ArrayList<Degree> degrees) {
		this.degrees = degrees;
	}

	/**
	 * @return the experiences
	 */
	public ArrayList<Job> getExperiences() {
		return experiences;
	}

	/**
	 * @param experiences the experiences to set
	 */
	public void setExperiences(ArrayList<Job> experiences) {
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
