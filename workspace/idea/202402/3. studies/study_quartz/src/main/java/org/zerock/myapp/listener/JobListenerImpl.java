package org.zerock.myapp.listener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;


@Log4j2
@NoArgsConstructor
public class JobListenerImpl implements JobListener {
	private Long latestInstantCount = 0L;

	
	@Override
	public String getName() {
		log.trace("getName() invoked.");
		
		return "- JobListener -";
	} // getName

	@Override
	public void jobToBeExecuted(JobExecutionContext ctx) {
		log.trace("jobToBeExecuted(job: {}, hashCode: {}, latestInstantCount: {}) invoked.",
				ctx.getJobDetail().getKey(), this.hashCode(), latestInstantCount);

		ctx.put("sharedDataFromJobListenerToJob", this.latestInstantCount);
	} // jobToBeExecuted

	@Override
	public void jobExecutionVetoed(JobExecutionContext ctx) {
		log.trace("jobExecutionVetoed(job: {}, hashCode: {}, latestInstantCount: {}) invoked.",
				ctx.getJobDetail().getKey(), this.hashCode(), latestInstantCount);

	} // jobExecutionVetoed

	@Override
	public void jobWasExecuted(JobExecutionContext ctx, JobExecutionException e) {
		log.trace("jobWasExecuted(job: {}, hashCode: {}, latestInstantCount: {}, e: {}) invoked.",
				ctx.getJobDetail().getKey(), this.hashCode(), latestInstantCount, e);

		// Get the value with the given key from the context's data map.
		Long sharedDataFromJobToListener = (Long) ctx.get("sharedDataFromJobToListener");
		if(sharedDataFromJobToListener != null) {
			log.info("\t+ sharedDataFromJobToListener: {}", sharedDataFromJobToListener);
			this.latestInstantCount = sharedDataFromJobToListener;
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
	} // jobWasExecuted

} // end class
