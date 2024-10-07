package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.zerock.myapp.job.JobA;

import java.util.Date;

import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.impl.StdSchedulerFactory.getDefaultScheduler;


@Log4j2
public class SimpleTriggerExample2 {

	
    public static void main(String[] args) {
        try {
			// Step.1
            JobDetail jobA = // Quartz Job to Be Scheduled.
    			JobBuilder.newJob( JobA.class ).
    				withIdentity(JobKey.jobKey("JobA", "JobGroup")).
	    			usingJobData("greeting", "Hello World!").
	    			usingJobData("floatNumber", 3.141f).
	    			build();

			// Step.2
            Trigger simpleTrigger = // Job Scheduling Registered To The Quartz Scheduler.
        		newTrigger().
        			withIdentity(TriggerKey.triggerKey("JobATrigger", "TriggerGroup")).
        			startNow().
        			withSchedule(
    					SimpleScheduleBuilder.simpleSchedule().
								withIntervalInSeconds(7).				// In seconds
								repeatForever()
    				).
        			build();

			// Step.3
			Scheduler scheduler = getDefaultScheduler();
            Date scheduledDate = scheduler.scheduleJob(jobA, simpleTrigger);
			log.info("\t+ scheduledDate: {}", scheduledDate);

			// Step.4
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } // try-catch
    } // main
    
} // end class