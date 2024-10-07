package org.zerock.myapp.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;

//=================================================
// 1. For Spring Boot 2.7.x
//=================================================
//import javax.persistence.*;

//=================================================
// 2. For Spring Boot 3.1.x
//=================================================
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

@Data

@Entity
@Table(name = "board")
public class Board {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String title;
    private String writer;
    private String content;

    private Long cnt = 0L;

    // Timestamp Value *NOT* Auto-Generated.
    // Instead, use `@CreationTimeStamp` Annotation.

    //@Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createDate;    // Created column : create_date
//    private Date createdate;    // Create Column : createdate

    @UpdateTimestamp
    private LocalDateTime updateDate;



} // end class
