package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.domain.HelloResponseDto;


@Log4j2
@NoArgsConstructor

@RestController
public class HelloController {


//    @GetMapping("/hello")                                                         // OK : Sent by String
    @GetMapping(path = "/hello", produces = { "application/json; charset=utf8" })   // OK : Sent by JSON
    String hello() {
        log.trace("hello() invoked.");

        return "Hello, Yoseph";
    } // hello

    @GetMapping("/hello/dto")                                                         // OK : Sent by JSON
//    @GetMapping(path = "/hello/dto", params = { "name", "amount" })                   // OK : Sent by JSON
//    @GetMapping(path = "/hello/dto", produces = { "application/json; charset=utf8" })   // OK : Sent by JSON
    HelloResponseDto helloDto(
        @RequestParam("name") String name,
        @RequestParam("amount") Integer amount ) {
        log.trace("helloDto({}, {}) invoked.", name, amount);

        return new HelloResponseDto(name, amount);
    } // helloDto

} // end class
