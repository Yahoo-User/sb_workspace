package org.zerock.myapp.domain;

import lombok.Data;

import java.util.Date;


@Data
public class BoardVO {
    private Integer seq;
    private String title;
    private String writer;
    private String content;

//    @DateTimeFormat(pattern="yyyy/MM/dd - HH:mm:ss.SSS")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createDate;
    private Integer cnt;



} // end class
