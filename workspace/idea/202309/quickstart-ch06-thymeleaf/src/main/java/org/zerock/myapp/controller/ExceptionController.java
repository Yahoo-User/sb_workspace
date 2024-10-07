package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.zerock.myapp.exception.BoardNotFoundException;

import java.sql.SQLException;


@Log4j2
@NoArgsConstructor

@Controller
public class ExceptionController {

    @GetMapping("/boardError")
    String boardError() {
        log.trace("boardError() invoked.");

        throw new BoardNotFoundException("검색된 게시글이 없습니다.");
    } // boardError

    @GetMapping("/illegalArgumentError")
    String illegalArgumentError() {
        log.trace("illegalArgumentError() invoked.");

        throw new IllegalArgumentException("부적절한 인자가 전달되었습니다.");
    } // illegalArgumentError

    @GetMapping("/sqlError")
    String sqlError() throws SQLException {
        log.trace("sqlError() invoked.");

        throw new SQLException("SQL구문에 오류가 있습니다.");
    } // sqlError

    // Local Exception Controller declared by `@ExceptionHandler` annotation in the `@Controller` class.
    @ExceptionHandler(SQLException.class)
    String handleSQLException(SQLException e, Model model) {
        log.trace("handleSQLException({}, {}) invoked.", e, model);

        model.addAttribute("exception", e);
        return "errors/sqlError";
    } // handleSQLException

} // end class
