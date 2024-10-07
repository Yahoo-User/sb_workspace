package org.zerock.myapp.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Data

@Entity
@Table(name = "member")
public class Member {
//        implements UserDetails {

    @Id private String id;

    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled;


//    ========== Inteface UserDetails ========== //

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    } // getAuthorities
//
//    @Override
//    public String getUsername() {
//        return null;
//    } // getUsername
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    } // isAccountNonExpired
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    } // isAccountNonLocked
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    } // isCredentialsNonExpired

} // end class
