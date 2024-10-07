package org.zerock.myapp.domain;

import lombok.Data;

//=================================================
// 1. For Spring Boot 2.7.x
//=================================================
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;

//=================================================
// 2. For Spring Boot 3.1.x
//=================================================
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;


@Data

@Entity
@Table(name = "MEMBER")
public class Member {
    @Id
    private String id;

    private String password;
    private String name;
    private String role;


} // end class
