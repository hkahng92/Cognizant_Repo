package com.company.clientservice.service;

import com.company.clientservice.dto.Person;
import com.company.clientservice.util.feign.PersonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceLayer {

    @Autowired
    PersonClient client;

    public ServiceLayer(PersonClient client){
        this.client=client;
    }

    public Person addPerson(Person person){
        return client.addPerson(person);
    }

    public Person findPersonByName(String name){
        return client.findPersonByName(name);
    }


}
