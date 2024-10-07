package org.zerock.myapp.domain;

import lombok.*;
import lombok.extern.log4j.Log4j2;


// `@Value` already marks `non-static`, `package-local` fields `private final`(*****).
//@Value

// Lombok `@Builder` needs a proper constructor for this class. (*****)
@Builder

// Class contains `required fields`, you have to `force` NoArgsConstructor. (*****)
//@NoArgsConstructor

@Log4j2

@ToString
@Getter
public class PostsUpdateRequestDto {
    private String title;
    private String content;
    private String author;


//    @Builder
//    public PostsUpdateRequestDto(String title, String content, String author) {
//        log.trace("Constructor({}, {}, {}) invoked.", title, content, author);
//
//        this.title = title;
//        this.content = content;
//        this.author = author;
//    } // Constructor

} // end class
