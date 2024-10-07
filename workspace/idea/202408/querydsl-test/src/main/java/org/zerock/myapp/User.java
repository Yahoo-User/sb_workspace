package org.zerock.myapp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;


@Data

@Entity(name = "User")
@Table(name = "USER")
public class User implements Serializable {
	@Serial private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_member", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_member")
	@Column(name = "member_id")
	private Long seq;
	
	private String name;
	private Integer age;
	private LocalDateTime createDate;
	

} // end class
