package org.zerock.myapp.jpa.mapping;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.util.Date;


@Log4j2

@Data
@ToString(exclude = "member")

@Entity
@Table(name = "board")
public class MyBoard {
    @Id
    @GeneratedValue
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seq;

    private String title;

    @Transient                          // 엔티티를 테이블로 매핑할 때, 제외시킬 필드 지정
    private String writer;

    private String content;

//    @Temporal(TemporalType.DATE)      // 날짜정보만 저장하는 타입으로 매핑
//    @Temporal(TemporalType.TIME)      // 시간정보만 저장하는 타입으로 매핑
    @Temporal(TemporalType.TIMESTAMP)   // 날짜와시간 정보 모두 저장하는 타입으로 매핑
    private Date createDate;

    private Long cnt = 0L;


    // ==========================
    // N : 1 연관관계 매핑
    // ==========================

    // ---------------
    // @ManyToOne
    // ---------------
    // Specifies a single-valued association to another entity class
    // that has many-to-one multiplicity.

//    @ManyToOne  // XX : member_member_id varchar(255) : mapped to the unexpected Column

    /* 아래 참조키 제약 조건 구문을 생성 시킴
     Hibernate:
         alter table if exists board
            add constraint FKo0pdhyrd9qu0a53o74gkceodx
            foreign key (member_member_id) references member
     */
    @ManyToOne(
        targetEntity = Member.class,
        fetch = FetchType.EAGER,
        optional = false
    )

    // ---------------
    // @JoinColumn
    // ---------------
    // Specifies a column for joining an entity association or element collection.

    /* 아래 참조키 제약 조건 구문을 수정함
     * Hibernate:
     *     alter table if exists board
     *        add constraint FKsds8ox89wwf6aihinar49rmfy
     *        foreign key (member_id)
     *        references member
     */
    @JoinColumn(
        // The name of the foreign key column (* Required *)
        name = "member_id",
        nullable = false,
        referencedColumnName = "member_id" // ( Optional )

//        ,foreignKey =
//            @ForeignKey(
//                name = "myboard_member_fk",
//                value = ConstraintMode.CONSTRAINT,

                /* ------------------------------------------
                 * foreignKeyDefinition
                 * ------------------------------------------
                 * The syntax used in the foreignKeyDefinition element
                 * should follow the SQL syntax used by the target database
                 * for foreign key constraints.
                 *
                 * For example, this may be similar the following:
                 *
                 *   FOREIGN KEY ( <COLUMN expression> {, <COLUMN expression>}... )
                 *   REFERENCES <TABLE identifier> [
                 *       (<COLUMN expression> {, <COLUMN expression>}... ) ]
                 *   [ ON UPDATE <referential action> ]
                 *   [ ON DELETE <referential action> ]
                 * ------------------------------------------
                 * Hibernate:
                 *  alter table if exists board
                 *      add constraint myboard_member_fk
                 *      FOREIGN KEY (member_id) REFERENCES member(member_id)
                 */
//                foreignKeyDefinition = "FOREIGN KEY (member_id) REFERENCES member(member_id)")
    )
    private Member member;


    public void setMember(Member member) {
        log.trace("setMember({}) invoked.", member);
        this.member = member;

        // 현재의 게시글이 매개변수로 전달된 회원의 게시글로 추가 (***)
        this.member.getBoardList().add(this);
    } // setMember


} // end class
