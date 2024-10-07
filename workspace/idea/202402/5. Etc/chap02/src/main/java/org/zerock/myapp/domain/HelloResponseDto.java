package org.zerock.myapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;


@Log4j2

@AllArgsConstructor
@Data
public class HelloResponseDto {
    private String name;
    private Integer amount;


} // end class
