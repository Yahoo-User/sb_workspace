package org.zerock.myapp.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;


@Log4j2
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(name = "jwt_refresh_token")
public class RefreshToken extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;


    public RefreshToken(Long userId, String refreshToken) {
        log.trace("Constructor({}, {}) invoked.", userId, refreshToken);

        this.userId  = userId;
        this.refreshToken = refreshToken;
    } // Constructor

    public RefreshToken update(String newRefreshToken) {
        log.trace("update({}) invoked.", newRefreshToken);

        this.refreshToken = newRefreshToken;
        return this;
    } // update

} // end class
