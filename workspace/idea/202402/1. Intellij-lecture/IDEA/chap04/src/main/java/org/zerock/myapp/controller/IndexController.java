package org.zerock.myapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.zerock.myapp.domain.PostsResponseDto;
import org.zerock.myapp.service.PostsService;


@Log4j2
@RequiredArgsConstructor

@Controller
public class IndexController {
    private final PostsService postsService;


    @GetMapping("/")
    String index(Model model) {    // View Controller
        log.trace("index() invoked.");

        model.addAttribute("posts", this.postsService.findAllDesc());
        return "index";
    } // index

    @GetMapping("/posts/save")
    String postsSave() { // View Controller
        log.trace("postsSave() invoked.");

        return "posts-save";
    } // postsSave

    @GetMapping("/posts/update/{id}")
    String postsUpdate(@PathVariable Long id, Model model) {
        log.trace("postsUpdate({}, model) invoked.", id);

        PostsResponseDto responseDto = this.postsService.findById(id);
        log.info("\t+ responseDto: {}", responseDto);

        model.addAttribute("post", responseDto);

        return "posts-update";
    } // postsUpdate

} // end class
