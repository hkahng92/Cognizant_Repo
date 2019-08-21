package com.company.personservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer personId;
    private String name;
    private Integer age;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(getPersonId(), person.getPersonId()) &&
                Objects.equals(getName(), person.getName()) &&
                Objects.equals(getAge(), person.getAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersonId(), getName(), getAge());
    }
}
