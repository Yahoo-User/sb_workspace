package org.zerock.myapp.domain;

import lombok.Data;

import java.io.Serializable;


@Data
public class Hotel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long city;
    private String name;
    private String address;
    private String zip;


} // end class
