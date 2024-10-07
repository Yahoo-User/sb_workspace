package org.zerock.myapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
@AllArgsConstructor
public enum Role {                  // Value Object (VO)
    GUEST("ROLE_GUEST", "guest"),
    USER("ROLE_USER", "user");

    private String key;
    private String title;



} // end enum
