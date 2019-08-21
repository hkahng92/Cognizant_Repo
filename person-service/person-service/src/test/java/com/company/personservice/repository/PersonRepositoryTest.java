package com.company.personservice.repository;

import com.company.personservice.dto.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Before
    public void setUp() throws Exception {
        personRepository.deleteAll();
    }

    @Test
    public void findByName() {
        Person person = new Person();
        person.setName("John");
        person.setAge(21);

        person = personRepository.save(person);

        //Person fromDB = personRepository.findByName(person.getName());
        Person fromDB = personRepository.findByName("John");
        assertEquals(person,fromDB);


    }
}