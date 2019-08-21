package com.company.clientservice.controller;

import com.company.clientservice.dto.Person;
import com.company.clientservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
public class ClientController {

    @Autowired
    ServiceLayer serviceLayer;

    @RequestMapping(value = "/clientfe/person/{name}",method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Person findPersonByName(@PathVariable String name){
        return serviceLayer.findPersonByName(name);
    }

    @RequestMapping(value = "/clientfe/addperson",method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Person addPerson(@RequestBody Person person){
        return serviceLayer.addPerson(person);
    }
}
