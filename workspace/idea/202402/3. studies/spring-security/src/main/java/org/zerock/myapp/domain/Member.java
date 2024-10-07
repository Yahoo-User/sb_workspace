package org.zerock.myapp.domain;

import jakarta.persistence.*;
import lombok.Data;


@Data

@Entity
@Table(name = "member")
public class Member {
    @Id
    private String id;

    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Integer enabled;



} // end class
