package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.zerock.myapp.job.JobB;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.DateBuilder.futureDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.impl.StdSchedulerFactory.getDefaultScheduler;


@Log4j2
public class CronTriggerExample {

	
    public static void main(String[] args) {
        try {
			// Step.1
            JobDetail jobB = // Quartz Job to Be Scheduled.
    			newJob( JobB.class ).
    				withIdentity(JobKey.jobKey("JobB", "JobGroup")).
	    			build();

			// Step.2
            Trigger cronTrigger = // Job Scheduling Registered To The Quartz Scheduler.
        		newTrigger().
        			withIdentity(TriggerKey.triggerKey("JobBTrigger", "TriggerGroup")).
//        			startNow().													// 1st. method
					startAt(futureDate(1, DateBuilder.IntervalUnit.SECOND)).	// 2nd. method
        			withSchedule(
						/*
						 * ---------------------------------------------------------------------
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
						cronSchedule("0/3 * * * * ?")
    				).
        			build();

			// Step.3
			Scheduler scheduler = getDefaultScheduler();

            Date scheduledDate = scheduler.scheduleJob(jobB, cronTrigger);
			log.info("\t+ scheduledDate: {}", scheduledDate);

			// Step.4
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } // try-catch
    } // main
    
} // end class