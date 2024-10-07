package org.zerock.myapp.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


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
