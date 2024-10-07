package org.zerock.myapp.domain;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;


@Log4j2

@ToString
@Getter
public class PostsListResponseDto {
    private final Long id;
    private final String title;
    private final String author;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;


    public PostsListResponseDto(Posts entity) {
        log.info("PostsListResponseDto({}) invoked.", entity);

        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    } // Constructor



} // end class
