package org.zerock.myapp.direct.job;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


@Log4j2
@NoArgsConstructor
public class SimpleJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.trace("execute(trigger: {}, job: {}, hashCode: {}, nextFireTime: {}) invoked.",
                context.getTrigger().getKey(), context.getJobDetail().getKey(),
                this.hashCode(), context.getNextFireTime());

        try {
            String key = "KEY";

            String value = context.getJobDetail().getJobDataMap().getString(key);
            log.info("\t+ key: {}, value: {}", key, value);
        } catch(Exception e) {
            throw new JobExecutionException(e);
        } // try-catch
    } // execute

} // end class
