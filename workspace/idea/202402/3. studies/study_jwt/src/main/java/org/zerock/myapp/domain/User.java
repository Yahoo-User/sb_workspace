package org.zerock.myapp.domain;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;


@Data
@EqualsAndHashCode(callSuper=false)

@Entity
@Table(name = "jwt_user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;



} // end class
