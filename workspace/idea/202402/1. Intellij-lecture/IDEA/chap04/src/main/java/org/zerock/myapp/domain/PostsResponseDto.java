package org.zerock.myapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;


// Class contains `required fields`, you have to `force` NoArgsConstructor. (*****)
@NoArgsConstructor

@Log4j2
@ToString
@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;


    public PostsResponseDto(Posts entity) {
        log.trace("Constructor({}) invoked.", entity);

        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    } // Constructor

} // end class
