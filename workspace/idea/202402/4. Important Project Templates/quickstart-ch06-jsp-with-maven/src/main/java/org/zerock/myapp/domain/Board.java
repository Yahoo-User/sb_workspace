package org.zerock.myapp.domain;

import lombok.Data;

import java.util.Date;


@Data
public class Board {
    private Long seq;
    private String title;
    private String writer;
    private String content;
    private Date createDate;
    private Long cnt;


} // end class
