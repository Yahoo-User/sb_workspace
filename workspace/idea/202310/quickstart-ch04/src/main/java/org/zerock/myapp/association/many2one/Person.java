package org.zerock.myapp.association.many2one;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/*
 * CREATE TABLE person (
 *     id BIGINT NOT NULL,
 *     name VARCHAR(255) NOT NULL,
 *
 *     PRIMARY KEY (id)
 * )
 */

@Data

@Entity(

    /*
     * (Optional) The entity name.
     *
     * Defaults to the *un-qualified* name of the `entity` class.   (***)
     * This name is used to refer to the `entity in queries`.       (***)
     * The name must *Not* be a `reserved` literal in the Java Persistence query language (JPQL).
     */
    name = "Person"
)
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    // This `@NaturalId` annotation specifies
    // that a property is part of the `natural id` (become a UK column) of the `entity`.
    @NaturalId
    private String name;



} // end class
