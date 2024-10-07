package org.zerock.myapp.job;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Log4j2
@NoArgsConstructor

@Component
public class SimpleTriggerJob {


    /*
     * ======================================
     * @Scheduled
     * ======================================
     * Annotation that marks a method to be scheduled.
     * Exactly one of the `cron`, `fixedDelay`, or `fixedRate` attributes must be specified.
     *
     * The annotated method must expect no arguments. (***)
     * It will typically have a `void` return type; (***)
     * if not, the returned value will be ignored when called through the scheduler.
     *
     * Processing of `@Scheduled` annotations is performed by registering a `ScheduledAnnotationBeanPostProcessor`.
     * This can be done manually or, more conveniently, through the `<task:annotation-driven/>` XML element or
     * `@EnableScheduling` annotation.
     *
     * This annotation can be used as a repeatable annotation. (***)
     * If `several` `@scheduled` declarations are found on the `same` method,
     * each of them will be processed independently, with a separate trigger firing for each of them.
     * As a consequence, such co-located schedules may overlap and execute multiple times
     * in parallel or in immediate succession.
     *
     * This annotation may be used as a meta-annotation to create custom composed annotations
     * with attribute overrides.
     *
     * The `@Scheduled` annotation supports the following properties:
     *
     *  (1) fixedRate   - Allows you to run a task at a specified fixed interval.
     *  (2) fixedDelay - Execute a task with a fixed delay
     *                   between the completion of the last invocation and the start of the next.
     *  (3) initialDelay - The property is used with `fixedRate` and `fixedDelay` to wait
     *                     before the first execution of the task with the specified delay.
     *  (4) cron - Set the task execution schedule using the cron-string.
     *             Also supports macros `@yearly` (or `@annually`), `@monthly`, `@weekly`, `@daily` (or `@midnight`),
     *             and `@hourly`.
     *
     * By default, `fixedRate`, `fixedDelay` and `initialDelay` are set in milliseconds.
     * This can be changed using the `timeUnit` property, setting the value from `NANOSECONDS` to `DAYS`.
     *
     * Furthermore, you can use Spring Boot properties in `@Scheduled` annotation:
     *
     * In the `application.properties` file:
     *
     *  cron-string=0/5 * * * * ?
     *
     * To use Spring Boot properties, you can utilize `fixedRateString`, `fixedDelayString`,
     * and `initialDelayString` parameters instead of `fixedRate`, `fixedDelay` and `initialDelay` accordingly.
     */
    @Scheduled(fixedRate = 10L, timeUnit = TimeUnit.SECONDS)
    public void interval1secondIntervalTask() {
        log.trace("interval1secondIntervalTask() invoked.");

    } // interval1secondIntervalTask

    @Scheduled(fixedRate = 30L, timeUnit = TimeUnit.SECONDS)
    public void interval3secondsIntervalTask() {
        log.trace("interval3secondsIntervalTask() invoked.");

    } // interval3secondsIntervalTask

} // end class
