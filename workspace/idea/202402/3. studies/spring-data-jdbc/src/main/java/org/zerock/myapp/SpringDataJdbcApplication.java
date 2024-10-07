package org.zerock.myapp;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.zerock.myapp.domain.Person;
import org.zerock.myapp.persistence.PersonRepository;

import java.util.Arrays;
import java.util.Optional;


@Log4j2
@SpringBootApplication
public class SpringDataJdbcApplication implements CommandLineRunner {
	@Setter(onMethod_= {@Autowired})
	private PersonRepository personRepository;
	
	@Setter(onMethod_= {@Autowired})
	private PersonSeeder personSeeder;
	

	public static void main(String[] args) {
		log.info("main({}) invoked.", Arrays.toString(args));
		
		SpringApplication.run(SpringDataJdbcApplication.class, args);
	} // main

	
	@Override
	public void run(String... args) {
		log.trace("run({}) invoked.", Arrays.toString(args));
		
		log.info("\t+1. this.personRepository: {}, type: {}", this.personRepository, this.personRepository.getClass().getName());
		log.info("\t+2. this.personSeeder: {}", this.personSeeder);
		
//		Thread.dumpStack();

		log.info("==============================");
		this.personSeeder.insertData();
		
		log.info("==============================");
		this.personRepository.findAll().forEach(log::info);

		log.info("==============================");
		Optional<Person> foundPerson = this.personRepository.findById(7L);
		log.info("\t+3. Found Person: {}", foundPerson);

		log.info("==============================");
		foundPerson.ifPresent(this.personRepository::delete);
		log.info("\t+4. Person: {} deleted.", foundPerson);
		
		Person newPerson = new Person();
		newPerson.setName("Trinity");
		newPerson.setAge(24);
		
		this.personRepository.save(newPerson);
		
		log.info("==============================");
		log.info("\t+5. New Person: {} inserted.", newPerson);

		log.info("==============================");
		this.personRepository.findAll().forEach(log::info);
		this.personRepository.findByName("Trinity").forEach(log::info);

		log.info("==============================");
		this.personRepository.updateByName(1L, "Pyramide");
		this.personRepository.findById(1L).ifPresent(log::info);
	} // run

} // end class
