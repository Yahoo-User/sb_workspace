package org.zerock.myapp;

import java.util.Objects;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Component
public class PersonSeeder {
	@Setter(onMethod_ = @Autowired)
	private JdbcTemplate jdbcTemplate;
	
	
	public void insertData() {
		log.trace("insertData() invoked.");
		
		Objects.requireNonNull(this.jdbcTemplate);
		
		this.jdbcTemplate.execute("TRUNCATE TABLE person");
		
		this.jdbcTemplate.execute("""
				INSERT INTO person (name, age)
				VALUES
					('NAME_1', 23),
					('NAME_2', 24),
					('NAME_3', 25),
					('NAME_4', 26),
					('NAME_5', 27),
					('NAME_6', 28),
					('NAME_7', 29)
				""");
	} // insertData

} // end class
