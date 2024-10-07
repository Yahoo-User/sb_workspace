package org.zerock.myapp.listener;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class SchedulerListenerImpl implements SchedulerListener {

	
	@Override
	public void jobScheduled(Trigger trigger) {
		log.trace("jobScheduled({}) invoked.", trigger);

	} // jobScheduled

	@Override
	public void jobUnscheduled(TriggerKey triggerKey) {
		log.trace("jobUnscheduled({}) invoked.", triggerKey);

	} // jobUnscheduled

	@Override
	public void triggerFinalized(Trigger trigger) {
		log.trace("triggerFinalized({}) invoked.", trigger);

	} // triggerFinalized

	@Override
	public void triggerPaused(TriggerKey triggerKey) {
		log.trace("triggerPaused({}) invoked.", triggerKey);

	} // triggerPaused

	@Override
	public void triggersPaused(String triggerGroup) {
		log.trace("triggersPaused({}) invoked.", triggerGroup);

	} // triggersPaused

	@Override
	public void triggerResumed(TriggerKey triggerKey) {
		log.trace("triggerResumed({}) invoked.", triggerKey);

	} // triggerResumed

	@Override
	public void triggersResumed(String triggerGroup) {
		log.trace("triggersResumed({}) invoked.", triggerGroup);

	} // triggersResumed

	@Override
	public void jobAdded(JobDetail jobDetail) {
		log.trace("jobAdded({}) invoked.", jobDetail);

	} // jobAdded

	@Override
	public void jobDeleted(JobKey jobKey) {
		log.trace("jobDeleted({}) invoked.", jobKey);

	} // jobDeleted

	@Override
	public void jobPaused(JobKey jobKey) {
		log.trace("jobPaused({}) invoked.", jobKey);

	} // jobPaused

	@Override
	public void jobsPaused(String jobGroup) {
		log.trace("jobsPaused({}) invoked.", jobGroup);

	} // jobsPaused

	@Override
	public void jobResumed(JobKey jobKey) {
		log.trace("jobResumed({}) invoked.", jobKey);

	} // jobResumed

	@Override
	public void jobsResumed(String jobGroup) {
		log.trace("jobsResumed({}) invoked.", jobGroup);

	} // jobsResumed

	@Override
	public void schedulerError(String msg, SchedulerException cause) {
		log.trace("schedulerError({}, {}) invoked.", msg, cause);

	} // schedulerError

	@Override
	public void schedulerInStandbyMode() {
		log.trace("--- schedulerInStandbyMode() invoked.");

	} // schedulerInStandbyMode

	@Override
	public void schedulerStarting() {
		log.trace("--- schedulerStarting() invoked.");

	} // schedulerStarting

	@Override
	public void schedulerStarted() {
		log.trace("--- schedulerStarted() invoked.");

	} // schedulerStarted

	@Override
	public void schedulerShuttingdown() {
		log.trace("--- schedulerShuttingdown() invoked.");

	} // schedulerShuttingdown

	@Override
	public void schedulerShutdown() {
		log.trace("--- schedulerShutdown() invoked.");

	} // schedulerShutdown

	@Override
	public void schedulingDataCleared() {
		log.trace("--- schedulingDataCleared() invoked.");

	} // schedulingDataCleared
	
} // end class
