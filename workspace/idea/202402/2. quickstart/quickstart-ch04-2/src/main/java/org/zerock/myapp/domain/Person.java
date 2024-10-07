package org.zerock.myapp.domain;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data

//@Entity(name="person")
@Entity
@Table(name="person")
public class Person implements Serializable {
	@Serial private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private Integer age;

	
	
} // end class

