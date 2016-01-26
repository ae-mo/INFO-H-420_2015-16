package org.camunda.bpm.infoh420.interview;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.cdi.jsf.TaskForm;
import org.camunda.bpm.engine.delegate.DelegateExecution;

@Stateless
@Named
public class AppointBusinessLogic {


	// Inject the entity manager
		  @PersistenceContext
		  private EntityManager entityManager;
		  
		  // Inject task form available through the Camunda cdi artifact
		  @Inject
		  private TaskForm taskForm;
		  private static Logger LOGGER = Logger.getLogger(BookBusinessLogic.class.getName());

		  public void persistAppointment(DelegateExecution delegateExecution) {
		    // Create new order instance
		    AppointEntity AppEntity = new AppointEntity();

		    // Get all process variables
		    Map<String, Object> variables = delegateExecution.getVariables();

		    // Set order attributes
		    AppEntity.setNamei((String) variables.get("namei"));
		    AppEntity.setNameie((String) variables.get("nameie"));
		    AppEntity.setDay2((String) variables.get("day2"));
		    AppEntity.setMonth2((String) variables.get("month2"));
		    AppEntity.setYear2((String) variables.get("year2"));
		    AppEntity.setHour2((String) variables.get("hour2"));
		    AppEntity.setMinute2((String) variables.get("minute2"));

		    // Persist order instance and flush. After the flush the
		    // id of the order instance is set.
		    entityManager.persist(AppEntity);
		    entityManager.flush();

		    // Remove no longer needed process variables
		    delegateExecution.removeVariables(variables.keySet());

		    // Add newly created order id as process variable
		    delegateExecution.setVariable("AppointmentId", AppEntity.getId());
		  }
		  	  
		  public AppointEntity getOrder(Long AppointmentId) {
			    // Load order entity from database
			    return entityManager.find(AppointEntity.class, AppointmentId);
		  }

		  /*
		    Merge updated order entity and complete task form in one transaction. This ensures
		    that both changes will rollback if an error occurs during transaction.
		   */
		  public void mergeOrderAndCompleteTask(AppointEntity AppEntity) {
		    // Merge detached order entity with current persisted state
		    entityManager.merge(AppEntity);
		    try {
		      // Complete user task from
		      taskForm.completeTask();
		    } catch (IOException e) {
		      // Rollback both transactions on error
		      throw new RuntimeException("Cannot complete task", e);
		    }
		  }
			  
		 public void appointOrder(DelegateExecution delegateExecution) {
			 	AppointEntity order = getOrder((Long) delegateExecution.getVariable("AppointmentId"));
			    LOGGER.log(Level.INFO, "\n\n\nSending Email:\nDear {0}, your Appointmen with {1} is set {2} for: \n day: {3} month {4} year {5} at {6}:{7}.\n\n\n",
			    		new String[]{order.getNamei(), order.getNameie(), order.getDay2(), order.getMonth2(), order.getYear2(), order.getHour2(), order.getMinute2()});
			  }

		
	
}
