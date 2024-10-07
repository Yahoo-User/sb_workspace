package org.zerock.myapp.job;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


@Log4j2
@NoArgsConstructor

// The object is instantiated per each execution.
public class JobB implements Job {
	private static long instanceCount = 0L;


	@Override
    public void execute(JobExecutionContext ctx) throws JobExecutionException {
		log.trace("execute(key: {}, hashCode: {}, instanceCount: {}) invoked.",
				ctx.getJobDetail().getKey(), this.hashCode(), ++instanceCount);

		try {
			/*
			 * Put the specified value into the context's data map with the given key.
			 * Possibly useful for sharing data between listeners and jobs.
			 *
			 * NOTE: this data is volatile - it is lost after the job execution completes,
			 * 		 and all `TriggerListener`s and `JobListener`s have been notified.
			 */
			ctx.put("sharedDataFromJobToListener", instanceCount);
		} catch(Exception e) {
			throw new JobExecutionException(e);
		} // try-catch
    } // execute

} // end class


