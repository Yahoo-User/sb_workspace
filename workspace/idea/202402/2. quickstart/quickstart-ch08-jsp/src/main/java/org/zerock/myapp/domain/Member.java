package org.zerock.myapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


//=================================================
// 1. For Spring Boot 2.7.x
//=================================================
//import javax.persistence.*;

//=================================================
// 2. For Spring Boot 3.1.x
//=================================================
import jakarta.persistence.*;


@Data
@ToString(exclude = "boardList")
@EqualsAndHashCode(callSuper = false)

@Table(name = "t_member")
@Entity
public final class Member extends BaseJpaAuditEntity {
    @Id
//    @Column(name = "member_id")
    private String id;

    private String name;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean enabled;


    /*
     * ===================================
     * `@OneToMany` Annotation
     * ===================================
     * Specifies a many-valued association with `one-to-many` multiplicity.
     *
     * If the collection is defined using generics to specify the element type,
     * the associated target entity type need not be specified; otherwise the target entity class must be specified.
     *
     * If the relationship is `bidirectional`, the `mappedBy` element must be used to
     * specify the relationship field or property of the entity that is the `owner of the relationship`.
     *
     * The `OneToMany` annotation may be used within an embeddable class contained
     * within an entity class to specify a relationship to a collection of entities.
     *
     * If the relationship is `bidirectional`, the `mappedBy` element must be used to
     * specify the relationship field or property of the entity that is the `owner of the relationship`.
     *
     * When the collection is a `java.util.Map`,
     * the `cascade` element and the `orphanRemoval` element apply to the map value.
     *
     *   Example 1: `One-to-Many` association using generics
     *
     *    // In `Customer` class :
     *    @OneToMany(cascade=ALL, mappedBy="customer")
     *    public Set<Order> getOrders() { return orders; }
     *
     *    // In `Order` class :
     *    @ManyToOne
     *    @JoinColumn(name="CUST_ID", nullable=false)
     *    public Customer getCustomer() { return customer; }
     *
     *
     *   Example 2: `One-to-Many` association without using generics
     *
     *    // In `Customer` class :
     *    @OneToMany(targetEntity=com.acme.Order.class, cascade=ALL, mappedBy="customer")
     *    public Set getOrders() { return orders; }
     *
     *    // In `Order` class :
     *    @ManyToOne
     *    @JoinColumn(name="CUST_ID", nullable=false)
     *    public Customer getCustomer() { return customer; }
     *
     *
     *   Example 3: `Unidirectional One-to-Many` association using a foreign key mapping
     *
     *    // In `Customer` class :
     *    @OneToMany(orphanRemoval=true)
     *    @JoinColumn(name="CUST_ID")     // join column is in table for Order
     *    public Set<Order> getOrders() {return orders;}
     *
     *
     * -----------------------
     * 1. `mappedBy` property ( `Required` unless the relationship is `unidirectional`. )
     * -----------------------
     *  The field that owns the relationship.
     *
     * -----------------------
     * 2. `fetch` property (Optional)
     * -----------------------
     *  Whether the association should be lazily loaded or must be eagerly fetched.
     *    - The `EAGER` strategy is a requirement on the persistence provider runtime
     *      that the associated entities must be eagerly fetched.
     *    - The `LAZY` strategy is a hint to the persistence provider runtime.
     *
     * -----------------------
     * 3. `orphanRemoval` property (Optional)
     * -----------------------
     *  Whether to apply the remove operation to entities that have been removed from the relationship
     *  and to cascade the remove operation to those entities.
     *
     * -----------------------
     * 4. `targetEntity` property (Optional)
     * -----------------------
     *  The entity class that is the target of the association.
     *  Optional only if the collection property is defined using Java generics.
     *  Must be specified otherwise.
     *  Defaults to the parameterized type of the collection when defined using generics.
     *
     * -----------------------
     * 5. `cascade` property (Optional)
     * -----------------------
     *  The operations that must be cascaded to the target of the association.
     *  Defaults to no operations being cascaded.
     *  When the target collection is a `java.util.Map`, the `cascade` element applies to the map value.
     *
     */
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private List<Board> boardList = new ArrayList<>();


} // end class
