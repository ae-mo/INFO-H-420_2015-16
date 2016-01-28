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
	
	private String subject;
	private boolean accepted;
	private String reasonRefused;
	private String moreInfo;
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
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the accepted
	 */
	public boolean isAccepted() {
		return accepted;
	}
	/**
	 * @param accepted the accepted to set
	 */
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	/**
	 * @return the reasonRefused
	 */
	public String getReasonRefused() {
		return reasonRefused;
	}
	/**
	 * @param reasonRefused the reasonRefused to set
	 */
	public void setReasonRefused(String reasonRefused) {
		this.reasonRefused = reasonRefused;
	}
	/**
	 * @return the moreInfo
	 */
	public String getMoreInfo() {
		return moreInfo;
	}
	/**
	 * @param moreInfo the moreInfo to set
	 */
	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}

