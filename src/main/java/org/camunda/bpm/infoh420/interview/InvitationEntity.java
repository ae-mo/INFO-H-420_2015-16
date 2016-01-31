package org.camunda.bpm.infoh420.interview;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class InvitationEntity {

	private static  final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	protected Long id;

	@Version
	protected long version;
	
	private int leftInterviewers;

	/**
	 * @return the leftInterviewers
	 */
	public int getLeftInterviewers() {
		return leftInterviewers;
	}

	/**
	 * @param leftInterviewers the leftInterviewers to set
	 */
	public void setLeftInterviewers(int leftInterviewers) {
		this.leftInterviewers = leftInterviewers;
	}
	
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}

