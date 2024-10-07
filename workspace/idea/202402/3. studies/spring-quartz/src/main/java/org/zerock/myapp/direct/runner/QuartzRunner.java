package org.zerock.myapp.direct.runner;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.zerock.myapp.direct.job.SimpleJob;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Objects;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;


@Log4j2
@NoArgsConstructor

@Component
public class QuartzRunner implements CommandLineRunner {
    //------Start: When using Spring-Quartz, (***)
    @Setter(onMethod_ = @Autowired)
    private SchedulerFactoryBean schedulerFactoryBean;          // OK

    @Setter(onMethod_ = @Autowired)
    private Scheduler scheduler;                                // OK
    //------End : When using Spring-Quartz, (***)


    @Override
    public void run(String... args) throws Exception {
        log.trace("run({}) invoked.", Arrays.toString(args));

        //------Start: When using Spring-Quartz, (***)
        Objects.requireNonNull(this.schedulerFactoryBean);
        log.info("\t+ this.schedulerFactoryBean: {}", this.schedulerFactoryBean);
        this.scheduler = this.schedulerFactoryBean.getScheduler();

        Objects.requireNonNull(this.scheduler);
        log.info("\t+ this.scheduler: {}, schedulerName: {}", this.scheduler, this.scheduler.getSchedulerName());
        //------End : When using Spring-Quartz, (***)

        JobDetail job =
            JobBuilder.newJob( SimpleJob.class ).
                withIdentity("SimpleJob", "JobGroup").
                usingJobData("KEY", "VALUE").
                build();

        Trigger trigger =
            TriggerBuilder.newTrigger().
                withIdentity("SimpleTrigger", "TriggerGroup").
                //startNow().
                startAt(
                    Date.from(
                        LocalDateTime.now().
                            plusSeconds(5L).
                            atZone( ZoneId.systemDefault() ).
                            toInstant()
                    )
                ).
                withSchedule(
//                    cronSchedule("* * * * * ?")
                    simpleSchedule().
                        withIntervalInMilliseconds(7000L).
//                        withIntervalInSeconds(1).
//                        withIntervalInMinutes(1).
//                        withIntervalInHours(1).

//                        withRepeatCount(10).
                        repeatForever()
                ).
                build();

        //------Start: 1. When using Spring-Quartz, (***)
        this.scheduler.start();
        this.scheduler.scheduleJob(job, trigger);
        //------End  : 1. When using Spring-Quartz, (***)

        // 2. To use Quartz directly.
//        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//        scheduler.start();
//
//        scheduler.scheduleJob(job, trigger);
    } // run

} // end class
