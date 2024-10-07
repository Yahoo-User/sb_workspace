package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.zerock.myapp.Ch01Application;
import org.zerock.myapp.domain.BoardVO;
import other.Hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Log4j2
@NoArgsConstructor

@ComponentScan(basePackages = "other")
@RequestMapping("/board")
@RestController
public class BoardController implements InitializingBean {
    @Autowired private WebApplicationContext ctx;      // AnnotationConfigServletWebServerApplicationContext
    @Autowired private Hotel hotel;
    @Autowired private Ch01Application app;


    @GetMapping("/hello")
    String hello(String name) {
        log.trace("hello({}) invoked.", name);

        Objects.requireNonNull(this.ctx);
        log.info("\t+ this.ctx: {}", this.ctx);

        Objects.requireNonNull(this.hotel);
        log.info("\t+ this.hotel: {}", this.hotel);

        Objects.requireNonNull(this.app);
        log.info("\t+ this.app: {}", this.app);

        return "hello: " + name;
    } // hello


    @GetMapping("/{id}")
    BoardVO getBoard(@PathVariable("id") Long seq) {
        log.trace("getBoard({}) invoked.", seq);

        BoardVO vo = new BoardVO();
        vo.setSeq(seq);
        vo.setTitle("TITLE");
        vo.setWriter("YOSEPH");
        vo.setContent("CONTENT");
        vo.setCnt(1);

        return vo;
    } // getBoard


    @GetMapping("/list")
    List<BoardVO> getList() {
        log.trace("getList() invoked.");

        List<BoardVO> list = new ArrayList<>();

        for(int i=0; i<10; i++) {
            BoardVO vo = new BoardVO();

            vo.setSeq((long) i);
            vo.setTitle("TITLE_"+i);
            vo.setWriter("TRINITY_"+i);
            vo.setContent("CONTENT_"+i);

            list.add(vo);
        } // for

        list.forEach(log::info);

        return list;
    } // getList



    // ============================================================ //

    @Override
    public void afterPropertiesSet() throws Exception {
        log.trace("afterPropertiesSet() invoked.");

        int totalBeans = this.ctx.getBeanDefinitionCount();
        log.info("\t+ 1. totalBeans: {}", totalBeans);

//        String[] beans = this.ctx.getBeanDefinitionNames();
//        List<String> list = Arrays.asList(beans);
//
//        log.info("\t+ 2. beans:");
//        list.forEach(log::info);
    } // afterPropertiesSet

} // end class
