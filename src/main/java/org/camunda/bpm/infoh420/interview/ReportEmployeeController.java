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
public class ReportEmployeeController implements Serializable {

	private static  final long serialVersionUID = 2L;
	
	@Inject
	private InterviewBusinessLogic interviewBusinessLogic;

	public void submitForm()  throws IOException {

		interviewBusinessLogic.mergeOrderAndCompleteTask();

	}
}
