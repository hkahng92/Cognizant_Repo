package com.company.clientservice.service;

import com.company.clientservice.dto.Person;
import com.company.clientservice.util.feign.PersonClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    @Mock
    private PersonClient personClient;
    private ServiceLayer serviceLayer;

    @Before
    public void setUp() throws Exception {

        setUpPersonClientMock();
        serviceLayer = new ServiceLayer(personClient);
    }

    public void setUpPersonClientMock(){
        personClient = mock(PersonClient.class);

        Person person = new Person();
        person.setPersonId(1);
        person.setName("John");
        person.setAge(21);

        Person person1 = new Person();
        person1.setName("John");
        person1.setAge(21);

        doReturn(person).when(personClient).addPerson(person1);
        doReturn(person).when(personClient).findPersonByName("John");

    }

    @Test
    public void addFindPersonByName() {
        Person person1 = new Person();
        person1.setPersonId(1);
        person1.setName("John");
        person1.setAge(21);

        Person person = new Person();
        person.setName("John");
        person.setAge(21);

        person = serviceLayer.addPerson(person);

        Person fromService = serviceLayer.findPersonByName("John");

        assertEquals(person,fromService);


        //integration test
        Mockito.when(personClient.addPerson(person)).thenReturn(person1);
        Mockito.when(personClient.findPersonByName("John")).thenReturn(person1);

        Person fromService2 = serviceLayer.addPerson(person);
        assertEquals(fromService2.getAge(),person1.getAge());
        assertEquals(fromService2.getName(),person1.getName());

    }

//    @Test
//    public void findPersonByName() {
//
//    }
}