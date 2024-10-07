package org.zerock.myapp.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

// The object is instantiated per each execution.
public class JobA implements Job {
	private static long instanceCount = 0L;


	@Override
    public void execute(JobExecutionContext ctx) throws JobExecutionException {
		log.trace("execute(key: {}, hashCode: {}, instanceCount: {}) invoked.",
				ctx.getJobDetail().getKey(), this.hashCode(), ++instanceCount);

		try {
			// -----------------------
			// To get the given data through `JobExecutionContext`
			// -----------------------
//	        JobDataMap jobDataMap = ctx.getJobDetail().getJobDataMap();		// 1st. method
			JobDataMap jobDataMap = ctx.getMergedJobDataMap();				// 2nd. method

//			log.info("\t+ jobDataMap: {}", jobDataMap);

	        String greeting 	= jobDataMap.getString("greeting");
	        float floatNumber 	= jobDataMap.getFloat("floatNumber");

	        log.info("\t\t+ greeting: {}, floatNumber: {}", greeting ,floatNumber);
		} catch(Exception e) {
			throw new JobExecutionException(e);
		} // try-catch
    } // execute

} // end class