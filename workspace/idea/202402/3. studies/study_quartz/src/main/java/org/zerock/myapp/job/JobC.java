package org.zerock.myapp.job;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


@Log4j2
@NoArgsConstructor

// The object is instantiated per each execution.
public class JobC implements Job {
	private static long instanceCount = 0L;


	@Override
    public void execute(JobExecutionContext ctx) throws JobExecutionException {
		log.trace("execute(key: {}, hashCode: {}, instanceCount: {}) invoked.",
				ctx.getJobDetail().getKey(), this.hashCode(), ++instanceCount);
		
		try {
			log.info("\t+ sharedDataFromJobListenerToJob: {}", ctx.get("sharedDataFromJobListenerToJob"));

			/*
			 * Put the specified value into the context's data map with the given key.
			 * Possibly useful for sharing data between listeners and jobs.
			 *
			 * NOTE: this data is volatile - it is lost after the job execution completes,
			 * 		 and all `TriggerListener`s and `JobListener`s have been notified.
			 */
			ctx.put("sharedDataFromJobToListener", instanceCount);

			/*
			 * Set the result (if any) of the Job's execution
			 * (the type of object set as the result is entirely up to the particular job).
			 *
			 * The result itself is meaningless to Quartz,
			 * but may be informative to `JobListener`s or `TriggerListener`s that are watching the job's execution.
			 */
			ctx.setResult("__RESULT__");
		} catch(Exception e) {
			throw new JobExecutionException(e);
		} // try-catch
    } // execute

} // end class