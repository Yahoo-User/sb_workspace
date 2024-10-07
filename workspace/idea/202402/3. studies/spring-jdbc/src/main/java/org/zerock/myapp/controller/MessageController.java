package org.zerock.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.domain.Message;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RequestMapping("messages")
@RestController
public class MessageController {
    @Autowired private JdbcTemplate jdbcTemplate;


    @GetMapping
    List<Message> getMessages() throws Exception {
        log.trace("getMessages() invoked.");

        return this.jdbcTemplate.<Message>query("SELECT id, text FROM message ORDER BY id", (rs, i) -> {
            log.trace("mapRow({}, {}) invoked.", rs, i);

            Message m = new Message();
            m.setId(rs.getInt("id"));
            m.setText(rs.getString("text"));

            return m;
        }); // .query
    } // getMessages
    
    
    @PostMapping
    void postMessage(@RequestBody Message message) {
    	log.trace("postMessage({}) invoked.", message);
    	
    	int affectedLines = this.jdbcTemplate.update("INSERT INTO message (text) VALUES (?)", message.getText());
    	log.info("\t+ affectedLines: {}", affectedLines);
    	
    	if(affectedLines == 1) {
    		log.info("\t+ message: {}", message);
    	} // if
    } // postMessages

} // end class
