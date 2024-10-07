package org.zerock.myapp.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class TriggerListenerImpl implements TriggerListener {

	
	@Override
	public String getName() {
		log.trace("getName() invoked.");
		
		return "- TriggerListener -";
	} // getName

	@Override
	public void triggerFired(Trigger t, JobExecutionContext ctx) {
		log.trace("triggerFired(trigger: {}, job: {}, hashCode: {}) invoked.",
				t.getKey(), ctx.getJobDetail().getKey(), this.hashCode());

	} // triggerFired

	@Override
	public boolean vetoJobExecution(Trigger t, JobExecutionContext ctx) {
		log.trace("vetoJobExecution(trigger: {}, job: {}, hashCode: {}) invoked.",
				t.getKey(), ctx.getJobDetail().getKey(), this.hashCode());
		
		return false;
	} // vetoJobExecution

	@Override
	public void triggerMisfired(Trigger t) {
		log.trace("triggerMisfired({}) invoked.", t);

	} // triggerMisfired
	
	@Override
	public void triggerComplete(Trigger t, JobExecutionContext ctx, CompletedExecutionInstruction triggerInstructionCode) {
		log.trace("triggerComplete(trigger: {}, job: {}, hashCode: {}, triggerInstructionCode: {}) invoked.",
				t.getKey(), ctx.getJobDetail().getKey(), this.hashCode(), triggerInstructionCode);

		// Get the value with the given key from the context's data map.
		Long sharedDataFromJobToListener = (Long) ctx.get("sharedDataFromJobToListener");
		if(sharedDataFromJobToListener != null) {
			log.info("\t+ sharedDataFromJobToListener: {}", sharedDataFromJobToListener);
		} // if

		/*
		 * Returns the result (if any) that the `Job` set before its execution completed
		 * (the type of object set as the result is entirely up to the particular job).
		 * The result itself is meaningless to Quartz,
		 * but may be informative to `JobListener`s or `TriggerListener`s that are watching the job's execution.
		 */
		if(ctx.getResult() instanceof String result) {
			log.info("\t+ result: {}", result);
		} // if
	} // triggerComplete

} // end class
