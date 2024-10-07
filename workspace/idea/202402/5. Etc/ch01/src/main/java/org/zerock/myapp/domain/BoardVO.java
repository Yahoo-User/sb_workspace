package org.zerock.myapp.domain;

import lombok.Data;

import java.util.Date;


@Data
public class BoardVO {
    private Long seq;
    private String title;
    private String writer;
    private String content;
    private Date createDate = new Date();
    private Integer cnt = 0;



} // end class
