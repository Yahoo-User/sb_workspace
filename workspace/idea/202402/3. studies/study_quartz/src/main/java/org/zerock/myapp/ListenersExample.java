package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.zerock.myapp.job.JobC;
import org.zerock.myapp.listener.SchedulerListenerImpl;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.impl.StdSchedulerFactory.getDefaultScheduler;


@Log4j2
public class ListenersExample {

	
    public static void main(String[] args) {
        try {
			// Step.1
            JobDetail jobC = // Quartz Job to Be Scheduled.
    			newJob( JobC.class ).
    				withIdentity(JobKey.jobKey("JobC", "JobGroup")).
	    			build();

			// Step.2
            Trigger jobCTrigger = // Job Scheduling Registered To The Quartz Scheduler.
        		newTrigger().
        			withIdentity(TriggerKey.triggerKey("JobCTrigger", "TriggerGroup")).
        			startNow().													// 1st. method
//					startAt(futureDate(1, DateBuilder.IntervalUnit.MINUTE)).	// 2nd. method
        			withSchedule( cronSchedule("30 * * * * ?") ).
        			build();

			// Step.3
			Scheduler scheduler = getDefaultScheduler();
            Date scheduledDate = scheduler.scheduleJob(jobC, jobCTrigger);
			log.info("\t+ scheduledDate: {}", scheduledDate);

			// Step.4
			ListenerManager listenerManager = scheduler.getListenerManager();

			// Configure global managers in the `quartz.properties` file.
//			listenerManager.addJobListener(new JobListenerImpl());
//			listenerManager.addTriggerListener(new TriggerListenerImpl());

			listenerManager.addSchedulerListener(new SchedulerListenerImpl());

			// Step.5
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } // try-catch
    } // main
    
} // end class