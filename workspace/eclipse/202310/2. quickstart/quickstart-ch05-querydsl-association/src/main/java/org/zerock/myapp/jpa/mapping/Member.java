package org.zerock.myapp.jpa.mapping;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Data
@ToString(exclude = "boardList")

@Entity
public class Member {
    @Id
    @Column(name = "member_id")
    private String id;

    private String password;
    private String name;
    private String role;


    // ==========================
    // N : 1 연관관계 매핑 => 목표: 양방향 연관관계 매핑 위해서 수행
    // ==========================
    @OneToMany(
        targetEntity = MyBoard.class,
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER,

        // The field that owns the relationship. (***)
        // 두 엔티티 간의 연관 관계의 주인(= 자식, Many 에 해당) 엔티티의
        // FK에 해당 되는 필드명 기재 (***)
        mappedBy = "member"               // *******
    )

    // 부모 엔티티에는 적용해서는 안되는 어노테이션 입니다!
    // AnnotationException:
    //  Association 'org.zerock.myapp.jpa.mapping.MyBoard.member' targets
    //  an unknown entity named 'org.zerock.myapp.jpa.mapping.Member'
    // @JoinColumn     // XX : 적용 불가!!! (***)
    private List<MyBoard> boardList = new ArrayList<>();



} // end class
