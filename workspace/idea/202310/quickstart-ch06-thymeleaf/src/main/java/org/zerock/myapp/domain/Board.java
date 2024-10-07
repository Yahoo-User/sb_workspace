package org.zerock.myapp.domain;

//=================================================
// 1. For Spring Boot 2.7.x
//=================================================
//import javax.persistence.*;

//=================================================
// 2. For Spring Boot 3.1.x
//=================================================
import jakarta.persistence.*;

import java.util.Date;
import lombok.Data;


@Data

@Entity
@Table(name = "BOARD")
public class Board {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)         // OK: By `sequence` object if Oracle.
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // OK: By `generated as identity` if Oracle.
//    @GeneratedValue(strategy = GenerationType.TABLE)     // OK: By `hibernate_sequences` table if Oracle.
    private Long seq;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    private String content;

    @Column(insertable = false, updatable = false, columnDefinition = "date default current_date")
    private Date createDate;

    @Column(insertable = false, updatable = true, columnDefinition = "number default 0")
    private Long cnt;



} // end class
