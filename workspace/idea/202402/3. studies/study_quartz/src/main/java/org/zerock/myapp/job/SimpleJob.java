package org.zerock.myapp.job;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


@Log4j2
@NoArgsConstructor

// The object is instantiated per each execution.
public class SimpleJob implements Job {
	private static long instanceCount = 0L;

	
	@Override
    public void execute(JobExecutionContext ctx) throws JobExecutionException {
		log.trace("execute(key: {}, hashCode: {}, instanceCount: {}) invoked.",
				ctx.getJobDetail().getKey(), this.hashCode(), ++instanceCount);

    } // execute
	
} // end class
