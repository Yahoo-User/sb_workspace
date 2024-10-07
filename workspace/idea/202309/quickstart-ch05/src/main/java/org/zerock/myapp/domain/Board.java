package org.zerock.myapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data

@Entity
@Table(name = "BOARD")
public class Board {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String title;
    private String writer;
    private String content;

    // Timestamp Value Auto-Generated.
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    private Long cnt;



} // end class
