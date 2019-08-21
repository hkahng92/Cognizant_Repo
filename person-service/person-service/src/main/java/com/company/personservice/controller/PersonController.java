package com.company.personservice.controller;

import com.company.personservice.dto.Person;
import com.company.personservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping(value = "/person/{name}",method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Person findPersonByName(@PathVariable String name){
        return personRepository.findByName(name);
    }

    @RequestMapping(value = "/person/addperson",method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Person addPerson(@RequestBody Person person){
        return personRepository.save(person);
    }

}
