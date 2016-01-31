package org.camunda.bpm.infoh420.interview;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.cdi.jsf.TaskForm;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.util.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@Stateless
@Named
public class InterviewBusinessLogic {

	private final int NBR_APPLICATIONS = 20;

	// Inject the entity manager
	@PersistenceContext
	private EntityManager entityManager;

	// Inject task form available through the Camunda cdi artifact
	@Inject
	private TaskForm taskForm;

	public void persistApplications(DelegateExecution delegateExecution) {

		// Create new application instance
		ArrayList<ApplicationEntity> applications = new ArrayList<ApplicationEntity>();
		for(int i=0; i < NBR_APPLICATIONS; i++) {
			applications.add(new ApplicationEntity());
		}
		ContactEntity contact = new ContactEntity();
		ArrayList<DegreeEntity> degrees = new ArrayList<DegreeEntity>();
		ArrayList<JobEntity> experiences = new ArrayList<JobEntity>();

		// Set contact info
		contact.setName("Andrea");
		contact.setAddress("Boulevard du Triomphe, 153");
		contact.setCountryRegion("Brussels");
		contact.setCity("Ixelles");
		contact.setZip("1050");
		ArrayList<String> phones = new ArrayList<String>();
		phones.add("+32484903921");
		phones.add("+393470622606");

		// Set degree info
		DegreeEntity degree = new DegreeEntity();
		degree.setTitle("Bachelor in Engineering");
		degree.setStatus("OBTAINED");
		degree.setSchool("Politecnico di Milano");
		degree.setField("Computer Engineering");
		degree.setCountryRegion("Italy");
		degrees.add(degree);

		// Set job info
		JobEntity job = new JobEntity();
		job.setEmployer("Freedelity");
		job.setTitle("Web Developer");
		job.setStart(new GregorianCalendar(2015, Calendar.JULY, 6).getTime());
		job.setEnd(new GregorianCalendar(2015, Calendar.SEPTEMBER, 25).getTime());
		job.setCountryRegion("Brussels");
		job.setCity("Ixelles");
		experiences.add(job);

		/*
	     Persist order instance and flush. After the flush the
	     id of the order instance is set.
		 */
		for(ApplicationEntity app: applications) {
			entityManager.persist(app);
		}
		entityManager.persist(contact);
		entityManager.persist(degree);
		entityManager.persist(job);
		entityManager.flush();

		// Add newly created order id as process variable
		for(int i=0; i < NBR_APPLICATIONS; i++) {
			delegateExecution.setVariable("appId" + i, applications.get(i).getId());
		}
		delegateExecution.setVariable("contactId", contact.getId());
		delegateExecution.setVariable("degreeId", degree.getId());
		delegateExecution.setVariable("jobId", job.getId());
	}

	public List<Long> getApplicationIDs(DelegateExecution delegateExecution) {

		List<Long> appIDs = new ArrayList<Long>();

		for(int i=0; i < NBR_APPLICATIONS; i++) {

			appIDs.add((Long) delegateExecution.getVariable("appId" + i));

		}

		return appIDs;
	}

	public ApplicationEntity getApplication(Long applicationId) {
		// Load order entity from database
		return entityManager.find(ApplicationEntity.class, applicationId);
	}

	public ContactEntity getContact(Long contactId) {
		// Load order entity from database
		return entityManager.find(ContactEntity.class, contactId);
	}

	public DegreeEntity getDegree(Long degreeId) {
		// Load order entity from database
		return entityManager.find(DegreeEntity.class, degreeId);
	}

	public JobEntity getJob( Long jobId) {
		// Load order entity from database
		return entityManager.find(JobEntity.class, jobId);
	}

	/*
    Merge updated order entity and complete task form in one transaction. This ensures
    that both changes will rollback if an error occurs during transaction.
	 */
	public void mergeApplicationAndCompleteTask(ApplicationEntity applicationEntity) {
		// Merge detached order entity with current persisted state
		entityManager.merge(applicationEntity);
		try {
			// Complete user task from
			taskForm.completeTask();
		} catch (IOException e) {
			// Rollback both transactions on error
			throw new RuntimeException("Cannot complete task", e);
		}
	}

	public void completeTask() {
		try {
			// Complete user task from
			taskForm.completeTask();
		} catch (IOException e) {
			// Rollback both transactions on error
			throw new RuntimeException("Cannot complete task", e);
		}
	}

	public List<String> getInterviewers() {

		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

		List<User> users = processEngine.getIdentityService()
				.createUserQuery().
				memberOfGroup("interview-team").list();

		List<String> userIDs = new ArrayList<String>();

		for(User user: users)
			userIDs.add(user.getId());

		return userIDs;

	}


	public void mergeInvitationAndCompleteTask(InvitationEntity invitationEntity) {
		// Merge detached order entity with current persisted state
		entityManager.merge(invitationEntity);
		try {
			// Complete user task from
			taskForm.completeTask();
		} catch (IOException e) {
			// Rollback both transactions on error
			throw new RuntimeException("Cannot complete task", e);
		}
	}

}
