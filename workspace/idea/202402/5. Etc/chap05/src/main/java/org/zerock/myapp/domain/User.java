package org.zerock.myapp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;


@Log4j2

@ToString
@Getter
@NoArgsConstructor

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    @Builder
    public User(String name, String email, String picture, Role role) {
        log.trace("User({}, {}, {}, {}) invoked.", name, email, picture, role);

        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    } // Constructor

    public User update(String name, String picture) {
        log.trace("update({}, {}) invoked.", name, picture);

        this.name = name;
        this.picture = picture;

        return this;
    } // update

    public String getRoleKey() {
        log.trace("getRoleKey() invoked.");

        return this.role.getKey();
    } // getRoleKey




} // end class
