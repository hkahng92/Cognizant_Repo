package com.company.clientservice.util.feign;

import com.company.clientservice.dto.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="person-service")
public interface PersonClient {

    @RequestMapping(value = "/person/{name}",method= RequestMethod.GET)
    public Person findPersonByName(@PathVariable String name);

    @RequestMapping(value = "/person/addperson",method= RequestMethod.POST)
    public Person addPerson(@RequestBody Person person);
}
