package se.sawano.spring.data.jpa.issue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person {

    @Id
    @GeneratedValue
    Long id;

    String name;
    String city;
}
