package org.zerock.myapp.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data

@Entity
@Table(name = "tbl_board")
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seq;

    private String title;
    private String writer;
    private String content;

    @Temporal(TemporalType.DATE)
    private Date createDate = new Date();

    private Long cnt = 0L;


} // end class
