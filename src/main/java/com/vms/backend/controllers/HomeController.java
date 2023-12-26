package com.vms.backend.controllers;

import com.vms.backend.entities.Boss;
import com.vms.backend.repositories.BossRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  // @Autowired
  // PersonService personService;

  @Autowired
  BossRepository bossRepository;

  // @GetMapping("getusers")
  // public List<Person> getPersons() {
  //   return personService.getPersons();
  // }

  @PostMapping("addboss")
  public List<Boss> addBoss(@RequestBody List<Boss> bosses) {
    return bossRepository.saveAll(bosses);
  }

  @GetMapping("getbosses")
  public List<Boss> getBoss() {
    return bossRepository.findAll();
  }
}
