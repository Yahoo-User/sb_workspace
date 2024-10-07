package org.zerock.myapp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;


// `@Value` already marks `non-static`, `package-local` fields `private final`(*****).
//@Value

// Lombok `@Builder` needs a proper constructor for this class. (*****)
//@Builder

// Class contains `required fields`, you have to `force` NoArgsConstructor. (*****)
@NoArgsConstructor

@Log4j2

@ToString
@Getter

// `@Entity` class must have a `default constructor`. (*****)
@Entity
@Table(name = "POSTS")
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // OK
//    @GeneratedValue(strategy = GenerationType.AUTO)       // XX : For OCI, `SQLSyntaxErrorException` occurred.
    private Long id;

    @Column(length = 500, nullable = false, name = "title")
    private String title;

    @Column(length = 4000, nullable = false, name = "content")
    private String content;

    private String author;


    @Builder
    public Posts(String title, String content, String author) {
        log.trace("Constructor({}, {}, {}) invoked.", title, content, author);

        this.title = title;
        this.content = content;
        this.author = author;
    } // Constructor

    public void update(String title, String content) {
        log.trace("update({}, {}) invoked.", title, content);

        this.title = title;
        this.content = content;
    } // update



} // end class
