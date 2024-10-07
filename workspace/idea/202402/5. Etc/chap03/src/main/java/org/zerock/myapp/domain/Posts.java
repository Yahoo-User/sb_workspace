package org.zerock.myapp.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;


@Getter

@Entity
@Table
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // OK : For AWS RDS MySQL, auto_increment.
//    @GeneratedValue(strategy = GenerationType.AUTO)         // OK: For AWS RDS MySQL, using sequence.
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @Column(nullable = false)
    private String author;


    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    } // Constructor



} // end class
