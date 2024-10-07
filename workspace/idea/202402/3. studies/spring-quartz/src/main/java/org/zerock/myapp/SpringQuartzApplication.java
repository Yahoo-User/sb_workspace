package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;


@Log4j2

/*
 * ======================================
 * @EnableScheduling
 * ======================================
 * Enables Spring's scheduled task execution capability, similar to functionality found in Spring's <task:*> XML namespace.
 * To be used on `@Configuration` classes as follows:
 *
 *   @Configuration
 *   @EnableScheduling
 *   public class AppConfig {
 *       // various @Bean definitions
 *
 *   } // AppConfig
 *
 * This enables detection of `@Scheduled` annotations on any Spring-managed bean in the container.
 * For example, given a class MyTask:
 *
 *   package com.myco.tasks;
 *
 *   public class MyTask {
 *
 *       @Scheduled(fixedRate=1000)
 *       public void work() {
 *           // task execution logic ...
 *       } // work
 *   } // end class
 *
 * The following configuration would ensure that MyTask.work() is called once every 1000 ms:
 *
 *   @Configuration
 *   @EnableScheduling
 *   public class AppConfig {
 *
 *       @Bean
 *       public MyTask task() {
 *           return new MyTask();
 *       } // task
 *
 *   } // end class
 *
 * Alternatively, if MyTask were annotated with `@Component`,
 * the following configuration would ensure that its `@Scheduled` method is invoked at the desired interval:
 *
 *   @Configuration
 *   @EnableScheduling
 *   @ComponentScan(basePackages="com.myco.tasks")
 *   public class AppConfig {
 *
 *   } // end class
 *
 * Methods annotated with `@Scheduled` may even be declared directly within `@Configuration` classes:
 *
 *   @Configuration
 *   @EnableScheduling
 *   public class AppConfig {
 *
 *       @Scheduled(fixedRate=1000)
 *       public void work() {
 *           // task execution logic ...
 *
 *       } // work
 *
 *   } // end class
 *
 * By default, Spring will search for an associated scheduler definition:
 * 		either a unique `org.springframework.scheduling.TaskScheduler` bean in the context,
 * 		or a `TaskScheduler` bean named "taskScheduler" otherwise;
 * the same lookup will also be performed for a `java.util.concurrent.ScheduledExecutorService` bean.
 *
 * If neither of the two is resolvable, a local single-threaded default scheduler will be created
 * and used within the registrar.
 *
 * When more control is desired, a `@Configuration` class may implement `SchedulingConfigurer`.
 * This allows access to the underlying `ScheduledTaskRegistrar` instance.
 *
 * For example, the following example demonstrates how to customize the `Executor` used to execute scheduled tasks:
 *
 *   @Configuration
 *   @EnableScheduling
 *   public class AppConfig implements SchedulingConfigurer {
 *
 *       @Override
 *       public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
 *           taskRegistrar.setScheduler(taskExecutor());
 *       } // configureTasks
 *
 *       @Bean(destroyMethod="shutdown")
 *       public Executor taskExecutor() {
 *           return Executors.newScheduledThreadPool(100);
 *       } // taskExecutor
 *
 *   } // end class
 *
 * Note in the example above the use of `@Bean(destroyMethod="shutdown")`.
 * This ensures that the task executor is properly shut down when the Spring application context itself is closed.
 *
 * Implementing `SchedulingConfigurer` also allows for fine-grained control over task registration via
 * the `ScheduledTaskRegistrar`.
 *
 * For example,
 * the following configures the execution of a particular bean method per a custom `Trigger` implementation:
 *
 *   @Configuration
 *   @EnableScheduling
 *   public class AppConfig implements SchedulingConfigurer {
 *
 *       @Override
 *       public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
 *           taskRegistrar.setScheduler(taskScheduler());
 *           taskRegistrar.addTriggerTask(
 *               () -> myTask().work(),
 *               new CustomTrigger()
 *           );
 *       } // configureTasks
 *
 *       @Bean(destroyMethod="shutdown")
 *       public Executor taskScheduler() {
 *           return Executors.newScheduledThreadPool(42);
 *       } // taskScheduler
 *
 *       @Bean
 *       public MyTask myTask() {
 *           return new MyTask();
 *       } // myTask
 *
 *   } // end class
 *
 *  Note: `@EnableScheduling` applies to its local application context only,
 *  	  allowing for selective scheduling of beans at different levels.
 *
 *  	  Please redeclare `@EnableScheduling` in each individual context, e.g.
 *  	  the common root web application context and any separate `DispatcherServlet` application contexts,
 *  	  if you need to apply its behavior at multiple levels.
 */

// 1. To work with Spring-Quartz,
@EnableScheduling

// 2. To work with Quartz directly,
// it is not necessary to define a configuration with the `@EnableScheduling` annotation. (***)
//@EnableScheduling

@ServletComponentScan
@SpringBootApplication
public class SpringQuartzApplication
		extends ServletInitializer implements ApplicationListener<ApplicationEvent> {


	public static void main(String[] args) {
		SpringApplication.run(SpringQuartzApplication.class, args);

		log.trace("main({}) invoked.", Arrays.toString(args));
	} // main

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
//		log.trace("onApplicationEvent({}) invoked.", event);

	} // onApplicationEvent

} // end class
