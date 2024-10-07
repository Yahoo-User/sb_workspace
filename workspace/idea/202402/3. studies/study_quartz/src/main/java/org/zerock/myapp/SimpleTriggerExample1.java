package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.zerock.myapp.job.SimpleJob;

import java.util.Date;


@Log4j2
public class SimpleTriggerExample1 {

	
    public static void main(String[] args) {
        try {
			// ============================================
          	// 1. To create a Quartz jobs with the associated triggers
			// ============================================
            JobDetail simpleJobDetail = // Quartz Job to Be Scheduled.

				// -----------------------
				// Create a `JobBuilder` with which to define a `JobDetail`,
				// and set the class name of the `Job` to be executed.
				// -----------------------
    			JobBuilder.newJob( SimpleJob.class ).

					// -----------------------
					// Use a `JobKey` with the given name and group to identify the `JobDetail`.
					// If none of the 'withIdentity' methods are set on the `JobBuilder`,
					// then a random, unique `JobKey` will be generated.
					// -----------------------
	    			withIdentity("SimpleJob", "JobGroup").						// 1st. method
//    				withIdentity( JobKey.jobKey("SimpleJob", "JobGroup") ).		// 2nd. method

					// -----------------------
					// Add the given key-value pair to the `JobDetail`'s `JobDataMap`.
					// -----------------------
	    			usingJobData("jobSays", "Hello World!").
	    			usingJobData("myFloatValue", 3.141f).

					// -----------------------
					// Produce the `JobDetail` instance defined by this `JobBuilder`.
					// -----------------------
	    			build();

			log.info("\t1. simpleJobDetail: {}", simpleJobDetail);
            
//            ---

            Trigger simpleJobTrigger = // Job Scheduling Registered To The Quartz Scheduler.
				// -----------------------
				// Create a new `TriggerBuilder` with which to define a specification for a `Trigger`.
				// -----------------------
        		TriggerBuilder.newTrigger().

					// -----------------------
					// Use a `TriggerKey` with the given name and group to identify the `Trigger`.
					// If none of the 'withIdentity' methods are set on the `TriggerBuilder`,
					// then a random, unique `TriggerKey` will be generated.
					// -----------------------
        			withIdentity("simpleJobTrigger", "TriggerGroup").							// 1st. method
//        			withIdentity( TriggerKey.triggerKey("simpleJobTrigger", "TriggerGroup") ).	// 2nd. method

					// -----------------------
					// Set the time the `Trigger` should start at to the current moment -
					// the trigger may or may not fire at this time -
					// depending upon the schedule configured for the Trigger.
					// -----------------------
        			startNow().													// 1st. method

					// -----------------------
					// Set the time the Trigger should start at - the trigger may or may not fire at this time -
					// depending upon the schedule configured for the Trigger.
					// However the Trigger will NOT fire before this time, regardless of the Trigger's schedule.
					// -----------------------
//					startAt(futureDate(3, DateBuilder.IntervalUnit.MINUTE)).	// 2nd. method

					// -----------------------
					// Set the `ScheduleBuilder` that will be used to define the `Trigger`'s schedule.
					// The particular `SchedulerBuilder` used will dictate the concrete type of `Trigger`
					// that is produced by the `TriggerBuilder`.
					// -----------------------
        			withSchedule(

						// Create a `SimpleScheduleBuilder`.
    					SimpleScheduleBuilder.simpleSchedule().
//								withIntervalInMilliseconds(1000L).		// In milliseconds
//								withIntervalInSeconds(1).				// In seconds
								withIntervalInMinutes(1). 				// In minutes
//								withIntervalInHours(1).					// In hours

//								repeatForever()							// 1st. method
    							withRepeatCount(3)						// 2nd. method

    				).

					// Produce the Trigger.
        			build();

			log.info("\t2. simpleJobTrigger: {}", simpleJobTrigger);


			// ============================================
			// 2. To create a Quartz job scheduler
			// ============================================
			// 1st. method
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			// 2nd. method
//			Scheduler scheduler = new StdSchedulerFactory().getScheduler();

			log.info("\t3. schedulerName: {}", scheduler.getSchedulerName());


			// ============================================
          	// 3. To schedule Quartz Job with the associated `JobDetail` & `Trigger`
			// ============================================
			// Add the given `JobDetail` to the `Scheduler`, and associate the given `Trigger` with it.
			// If the given `Trigger` does not reference any Job,
			// then it will be set to reference the `Job` passed with it into this method.
			// -----------------------

            Date scheduledDate = scheduler.scheduleJob(simpleJobDetail, simpleJobTrigger);
			log.info("\t4. scheduledDate: {}", scheduledDate);


			// ============================================
          	// 4. To Start a Quartz Scheduler
        	// ============================================
			// Starts the `Scheduler`'s threads that fire `Triggers`.
			// When a scheduler is first created, it is in "stand-by" mode, and will not fire triggers.
			// The scheduler can also be put into stand-by mode by calling the `standby()` method.
			// The misfire/recovery process will be started,
			// if it is the initial call to this method on this scheduler instance.
			// -----------------------

            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } // try-catch
    } // main
    
} // end class