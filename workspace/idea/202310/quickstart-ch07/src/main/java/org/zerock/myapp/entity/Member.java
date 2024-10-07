package org.zerock.myapp.entity;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;


@Log4j2
@Data

@Entity
@Table(name = "MEMBER")
public class Member {
    @Id
    private String id;

    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean enabled;



} // end class
