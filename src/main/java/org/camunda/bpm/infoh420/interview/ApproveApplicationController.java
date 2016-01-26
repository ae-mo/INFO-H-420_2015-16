package org.camunda.bpm.infoh420.interview;

import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.infoh420.interview.*;
import org.camunda.bpm.engine.cdi.jsf.TaskForm;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.io.Serializable;

@Named
@ConversationScoped
public class ApproveApplicationController implements Serializable {

  private static  final long serialVersionUID = 1L;

  // Inject the BusinessProcess to access the process variables
  @Inject
  private BusinessProcess businessProcess;

  // Inject the EntityManager to access the persisted order
  @PersistenceContext
  private EntityManager entityManager;

  // Inject the OrderBusinessLogic to update the persisted order
  @Inject
  private InterviewBusinessLogic interviewBusinessLogic;

  // Caches the OrderEntity during the conversation
  private ApplicationEntity applicationEntity;
  private ContactEntity contact;
  private DegreeEntity degree;
  private JobEntity job;

  public ApplicationEntity getApplicationEntity() {
    if (applicationEntity == null) {
      // Load the order entity from the database if not already cached
    	applicationEntity = interviewBusinessLogic.getApplication((Long) businessProcess.getVariable("applicationId"));
    }
    return applicationEntity;
  }
  
  public ContactEntity getContact() {
	    if (contact == null) {
	      // Load the order entity from the database if not already cached
	    	contact= interviewBusinessLogic.getContact((Long) businessProcess.getVariable("contactId"));
	    }
	    return contact;
	  }
  
  public DegreeEntity getDegree() {
	    if (degree == null) {
	      // Load the order entity from the database if not already cached
	    	degree = interviewBusinessLogic.getDegree((Long) businessProcess.getVariable("degreeId"));
	    }
	    return degree;
	  }
  
  public JobEntity getJob() {
	    if (job == null) {
	      // Load the order entity from the database if not already cached
	    	job = interviewBusinessLogic.getJob((Long) businessProcess.getVariable("jobId"));
	    }
	    return job;
	  }

  public void submitForm() throws IOException {
    // Persist updated order entity and complete task form
    interviewBusinessLogic.mergeOrderAndCompleteTask(applicationEntity);
  }
  
 
}