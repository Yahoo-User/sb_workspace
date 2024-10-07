package org.zerock.myapp.association.many2one;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;


/*
 * CREATE TABLE phone (
 *     id BIGINT NOT NULL,
 *     number VARCHAR(255) NOT NULL,
 *     person_id BIGINT,   -- FK
 *
 *     PRIMARY KEY (id)
 * )
 *
 * ALTER TABLE phone ADD CONSTRAINT person_id_fk
 * FOREIGN KEY (person_id) REFERENCES person(id)
 */

@Data

@Entity(name = "Phone")
public class Phone {
    @Id @GeneratedValue
    private Long id;

    // This `@NaturalId` annotation specifies
    // that a property is part of the `natural id` (become a UK column) of the `entity`.
    @NaturalId
    private String number;

    @ManyToOne
    @JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "person_id_fk"))
    private Person person;


} // end class
