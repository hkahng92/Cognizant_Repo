package com.company.clientservice.controller;

import com.company.clientservice.dto.Person;
import com.company.clientservice.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findPersonByNameShouldReturnPersonWithName() throws Exception{
        Person person1 = new Person();
        person1.setPersonId(1);
        person1.setName("John");
        person1.setAge(21);

        String outputJson = mapper.writeValueAsString(person1);

        when(serviceLayer.findPersonByName("John")).thenReturn(person1);


        this.mockMvc.perform(get("/clientfe/person/John"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        //Below - use the objectmapper output with the json method
                        .json(outputJson));
    }

    @Test
    public void findPersonByNameShouldReturnNull() throws Exception{
        Person person1 = new Person();
        person1.setPersonId(1);
        person1.setName("John");
        person1.setAge(21);

        String outputJson = "null";

        when(serviceLayer.findPersonByName("Steve")).thenReturn(null);


        this.mockMvc.perform(get("/clientfe/person/Steve"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void addPersonShouldReturnAddedPerson() throws Exception{

        Person person = new Person();
        person.setName("John");
        person.setAge(21);

        String inputJson = mapper.writeValueAsString(person);

        Person person1 = new Person();
        person1.setPersonId(1);
        person1.setName("John");
        person1.setAge(21);

        when(serviceLayer.addPerson(person)).thenReturn(person1);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/clientfe/addperson")
                .content(inputJson)
                .contentType(javax.ws.rs.core.MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(person1)));
    }
}