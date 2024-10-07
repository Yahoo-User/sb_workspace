package org.zerock.myapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Posts;
import org.zerock.myapp.domain.PostsResponseDto;
import org.zerock.myapp.domain.PostsSaveRequestDto;
import org.zerock.myapp.domain.PostsUpdateRequestDto;
import org.zerock.myapp.persistence.PostsRepository;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;


@Log4j2
@RequiredArgsConstructor

@Service
public class PostsService implements InitializingBean {
    private final PostsRepository postsRepository;


    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        log.trace("save({}) invoked.", requestDto);

        return this.postsRepository.<Posts>save(requestDto.toEntity()).getId();
    } // save

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        log.trace("update({}, {}) invoked.", id, requestDto);

        Posts posts =
                this.postsRepository.
                        findById(id).   // returns `Optional<Posts>`
                        orElseThrow( () -> new IllegalArgumentException("No Posts Found. id = " + id) );
        log.info("\t+ Found posts: {}", posts);

        posts.update(requestDto.getTitle(), requestDto.getContent());
        log.info("\t+ Updated posts: {}", posts);

//        return id;
        return posts.getId();   // Safe Code.
    } // update

    public PostsResponseDto findById(Long id) {
        log.trace("findById({}) invoked.", id);

        Posts posts =
                this.postsRepository.
                        findById(id).   // returns `Optional<Posts>`
                        orElseThrow( () -> new IllegalArgumentException("No Posts Found. id = " + id) );
        log.info("\t+ Found posts: {}", posts);

        return new PostsResponseDto(posts);
    } // findById


    @Override
    public void afterPropertiesSet() {
        log.trace("afterPropertiesSet() invoked.");

        Objects.requireNonNull(this.postsRepository);
        log.info("\t+ this.postsRepository: {}", this.postsRepository);
    } // afterPropertiesSet
} // end class
