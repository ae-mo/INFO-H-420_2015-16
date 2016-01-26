package org.camunda.bpm.infoh420.interview;

import org.camunda.bpm.engine.cdi.jsf.TaskForm;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.impl.util.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

	// Inject the entity manager
	@PersistenceContext
	private EntityManager entityManager;

	// Inject task form available through the Camunda cdi artifact
	@Inject
	private TaskForm taskForm;

	public void persistApplication(DelegateExecution delegateExecution) {

		// Create new application instance
		ApplicationEntity applicationEntity = new ApplicationEntity();

		ContactEntity contact = new ContactEntity();
		ArrayList<DegreeEntity> degrees = new ArrayList<DegreeEntity>();
		ArrayList<JobEntity> experiences = new ArrayList<JobEntity>();

		// Set contact info
		contact.setApplicationId(applicationEntity.getId());
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
		degree.setApplicationId(applicationEntity.getId());
		degree.setTitle("Bachelor in Engineering");
		degree.setStatus("OBTAINED");
		degree.setSchool("Politecnico di Milano");
		degree.setField("Computer Engineering");
		degree.setCountryRegion("Italy");
		degrees.add(degree);

		// Set job info
		JobEntity job = new JobEntity();
		job.setApplicationId(applicationEntity.getId());
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
		entityManager.persist(applicationEntity);
		entityManager.persist(contact);
		entityManager.persist(degree);
		entityManager.persist(job);
		entityManager.flush();

		// Add newly created order id as process variable
		delegateExecution.setVariable("applicationId", applicationEntity.getId());
		delegateExecution.setVariable("contactId", contact.getId());
		delegateExecution.setVariable("degreeId", degree.getId());
		delegateExecution.setVariable("jobId", job.getId());
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
	public void mergeOrderAndCompleteTask(ApplicationEntity applicationEntity) {
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
	
	public void mergeOrderAndCompleteTask() {
		try {
			// Complete user task from
			taskForm.completeTask();
		} catch (IOException e) {
			// Rollback both transactions on error
			throw new RuntimeException("Cannot complete task", e);
		}
	}

	public void getInterviewers(DelegateExecution delegateExecutionn) {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/engine-rest/user?firstName=John");
		JSONArray response = target.request(MediaType.APPLICATION_JSON).get(JSONArray.class);
	}

}
