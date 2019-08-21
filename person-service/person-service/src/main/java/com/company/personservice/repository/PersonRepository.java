package com.company.personservice.repository;

import com.company.personservice.dto.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Integer> {

    Person findByName(String name);
}
