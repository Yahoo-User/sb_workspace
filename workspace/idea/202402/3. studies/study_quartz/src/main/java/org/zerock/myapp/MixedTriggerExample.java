package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.zerock.myapp.job.JobA;
import org.zerock.myapp.job.JobB;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.DateBuilder.futureDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.impl.StdSchedulerFactory.getDefaultScheduler;


@Log4j2
public class MixedTriggerExample {

	
    public static void main(String[] args) {
        try {
			// Step.1-1
            JobDetail jobA = // Quartz Job to Be Scheduled.
    			newJob( JobA.class ).
    				withIdentity(JobKey.jobKey("JobA", "JobGroup")).
	    			usingJobData("greeting", "Hello World!").
	    			usingJobData("floatNumber", 3.141f).
	    			build();

			// Step.1-1
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("now", new Date());

			JobDetail jobB =
				newJob( JobB.class ).
					withIdentity("JobB", "JobGroup").
					usingJobData(jobDataMap).
					build();

			// Step.2-1
            Trigger jobATrigger = // Job Scheduling Registered To The Quartz Scheduler.
        		newTrigger().
        			withIdentity(TriggerKey.triggerKey("JobATrigger", "TriggerGroup")).
					withPriority(15).
        			startNow().
//					startAt(futureDate(3, DateBuilder.IntervalUnit.SECOND)).
        			withSchedule(
    					simpleSchedule().
							withIntervalInSeconds(7).				// In seconds
							repeatForever()
    				).
        			build();

			// Step.2-2
			Trigger jobBTrigger =
				newTrigger().
					withIdentity("JobBTrigger", "TriggerGroup").
					withPriority(10).
					withSchedule(
						/* ---------------------------------------------------------------------
						 *      Field		Mandatory	Allowed				Allowed
						 *      Name					Values				Special Characters
						 * ---------------------------------------------------------------------
						 * 1st. Seconds			YES		0-59				, - * /
						 * 2nd. Minutes			YES		0-59				, - * /
						 * 3rd. Hours			YES		0-23				, - * /
						 * 4th. Day of month	YES		1-31				, - * ? / L W
						 * 5th. Month			YES		1-12 or JAN-DEC	, 	- * /
						 * 6th. Day of week		YES		1-7 or SUN-SAT	, 	- * ? / L #
						 * 7th. Year			NO		empty, 1970-2099	, - * /
						 * ---------------------------------------------------------------------
						 * Note: Support for specifying both a `day-of-week` AND a `day-of-month` parameter is NOT implemented.
						 */
						cronSchedule("0/7 * * * * ?")
					).
					startNow().
//					startAt(futureDate(3, DateBuilder.IntervalUnit.SECOND)).
					build();

			// Step.3
			Scheduler scheduler = getDefaultScheduler();

            Date scheduledDateForJobA = scheduler.scheduleJob(jobA, jobATrigger);
			Date scheduledDateForJobB = scheduler.scheduleJob(jobB, jobBTrigger);

			log.info("\t+ scheduledDateForJobA: {}", scheduledDateForJobA);
			log.info("\t+ scheduledDateForJobB: {}", scheduledDateForJobB);

			// Step.4
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } // try-catch
    } // main
    
} // end class