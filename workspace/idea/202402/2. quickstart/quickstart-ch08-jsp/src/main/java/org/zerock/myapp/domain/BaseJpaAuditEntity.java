package org.zerock.myapp.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


//=================================================
// 1. For Spring Boot 2.7.x
//=================================================
//import javax.persistence.Column;
//import javax.persistence.EntityListeners;
//import javax.persistence.MappedSuperclass;

//=================================================
// 2. For Spring Boot 3.1.x
//=================================================
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;


@Data

/*
 * ===================================
 * 1. @EntityListeners
 * ===================================
 * Specifies the callback listener classes to be used for an entity or mapped superclass.
 * This annotation may be applied to an entity class or mapped superclass.
 *
 * ===================================
 * 2. org.springframework.data.jpa.domain.support.AuditingEntityListener
 * ===================================
 * JPA entity listener to capture auditing information on persiting and updating entities.
 * To get this one flying be sure you configure it as entity listener in your `orm.xml` as follows:
 *
 *   <persistence-unit-metadata>
 *       <persistence-unit-defaults>
 *           <entity-listeners>
 *               <entity-listener class="org.springframework.data.jpa.domain.support.AuditingEntityListener" />
 *           </entity-listeners>
 *       </persistence-unit-defaults>
 *   </persistence-unit-metadata>
 *
 * After that it's just a matter of activating auditing in your Spring config:
 *
 *   @Configuration     <------------------- *
 *   @EnableJpaAuditing <------------------- ***
 *   class ApplicationConfig {
 *
 *   } // ApplicationConfig
 *
 *   <jpa:auditing auditor-aware-ref="yourAuditorAwarebean" />
 *
 */
@EntityListeners(AuditingEntityListener.class)


/*
 * ===================================
 * @MappedSuperclass
 * ===================================
 * Designates a class whose mapping information is applied to the entities that inherit from it.
 * A mapped superclass has no separate table defined for it.
 *
 * A class designated with the `@MappedSuperclass` annotation can be mapped in the same way as an entity
 * except that the mappings will apply only to its subclasses
 * since no table exists for the mapped superclass itself.
 *
 * When applied to the subclasses the inherited mappings will apply in the context of the subclass tables.
 * Mapping information may be overridden in such subclasses
 * by using the `@AttributeOverride` and `@AssociationOverride` annotations or corresponding XML elements.
 *
 *      Example: Concrete class as a mapped superclass
 *
 *      @MappedSuperclass
 *      public class Employee {
 *          @Id protected Integer empId;
 *          @Version protected Integer version;
 *          @ManyToOne @JoinColumn(name="ADDR")
 *          protected Address address;
 *
 *          public Integer getEmpId() { ... }
 *          public void setEmpId(Integer id) { ... }
 *          public Address getAddress() { ... }
 *          public void setAddress(Address addr) { ... }
 *      } // Employee
 *
 *      // Default table is FTEMPLOYEE table
 *      @Entity
 *      public class FTEmployee extends Employee {
 *          // Inherited empId field mapped to FTEMPLOYEE.EMPID
 *          // Inherited version field mapped to FTEMPLOYEE.VERSION
 *          // Inherited address field mapped to FTEMPLOYEE.ADDR fk
 *
 *          // Defaults to FTEMPLOYEE.SALARY
 *          protected Integer salary;
 *
 *          public FTEmployee() {}
 *          public Integer getSalary() { ... }
 *          public void setSalary(Integer salary) { ... }
 *      } // FTEmployee
 *
 *      @Entity
 *      @Table(name="PT_EMP")
 *      @AssociationOverride(
 *          name="address",
 *          joincolumns=@JoinColumn(name="ADDR_ID"))
 *      public class PartTimeEmployee extends Employee {
 *          // Inherited empId field mapped to PT_EMP.EMPID
 *          // Inherited version field mapped to PT_EMP.VERSION
 *          // address field mapping *overridden* to PT_EMP.ADDR_ID fk
 *
 *          @Column(name="WAGE")
 *          protected Float hourlyWage;
 *
 *          public PartTimeEmployee() {}
 *          public Float getHourlyWage() { ... }
 *          public void setHourlyWage(Float wage) { ... }
 *      } // PartTimeEmployee
 */
@MappedSuperclass
public abstract class BaseJpaAuditEntity {

    /*
     * ===================================
     * 1. @CreatedDate
     * ===================================
     * Declares a field as the one representing the date the entity containing the field was created.
     *
     * ===================================
     * 2. @LastModifiedDate
     * ===================================
     * Declares a field as the one representing the date the entity containing the field was recently modified.
     *
     * ===================================
     * 3. `@Column` Annotation
     * ===================================
     * Specifies the mapped column for a persistent property or field.
     * If no Column annotation is specified, the default values apply.
     *
     * --------------------------
     * 3-1. `name` property (Optional)
     * --------------------------
     * The name of the column.
     * Defaults to the property or field name.
     *
     * --------------------------
     * 3-2. `insertable` property (Optional)
     * --------------------------
     * Whether the column is included in SQL `INSERT` statements generated by the persistence provider.
     *
     * --------------------------
     * 3-3. `updatable` property (Optional)
     * --------------------------
     * Whether the column is included in SQL `UPDATE` statements generated by the persistence provider.
     *
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected LocalDateTime insertTs;

    @LastModifiedDate
    @Column(insertable = false)
    protected LocalDateTime updateTs;

} // end class
