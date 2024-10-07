package org.zerock.myapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.*;
import org.zerock.myapp.domain.PostsResponseDto;
import org.zerock.myapp.domain.PostsSaveRequestDto;
import org.zerock.myapp.domain.PostsUpdateRequestDto;
import org.zerock.myapp.service.PostsService;

import java.util.Objects;


@Log4j2
@RequiredArgsConstructor

@RestController
public class PostsApiController implements InitializingBean {
    private final PostsService postsService;


    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        log.trace("save({}) invoked.", requestDto);

        return this.postsService.save(requestDto);
    } // save

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable("id") Long id) {
        log.trace("findById({}) invoked.", id);

        return this.postsService.findById(id);
    } // findById

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        log.trace("update({}, {}) invoked.", id, requestDto);

        return this.postsService.update(id, requestDto);
    } // update


    @Override
    public void afterPropertiesSet() {
        log.trace("afterPropertiesSet() invoked.");

        Objects.requireNonNull(this.postsService);
        log.info("\t+ this.postsService: {}", this.postsService);
    } // afterPropertiesSet

} // end class
