package com.vms.backend.services;

import com.vms.backend.entities.Person;
import com.vms.backend.repositories.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  @Autowired
  PersonRepository personRepository;

  public PersonService() {}

  public List<Person> getPersons() {
    return personRepository.findAll();
  }
}
