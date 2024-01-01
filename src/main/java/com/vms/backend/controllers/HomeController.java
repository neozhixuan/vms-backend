package com.vms.backend.controllers;

import com.vms.backend.ResourceNotFoundException;
import com.vms.backend.entities.Boss;
import com.vms.backend.entities.Event;
import com.vms.backend.entities.Participant;
import com.vms.backend.entities.ParticipantAvailability;
import com.vms.backend.entities.Shift;
import com.vms.backend.pojos.EventRequest;
import com.vms.backend.pojos.ParticipantAvailabilityRequest;
import com.vms.backend.pojos.ShiftRequest;
import com.vms.backend.repositories.BossRepository;
import com.vms.backend.repositories.EventRepository;
import com.vms.backend.repositories.ParticipantAvailabilityRepository;
import com.vms.backend.repositories.ParticipantRepository;
import com.vms.backend.repositories.ShiftRepository;
import com.vms.backend.services.EventService;
import com.vms.backend.services.ParticipantAvailabilityService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  private static final Logger log = LoggerFactory.getLogger(
    HomeController.class
  );

  @Autowired
  ParticipantAvailabilityRepository participantAvailabilityRepository;

  @Autowired
  ParticipantAvailabilityService participantAvailabilityService;

  @Autowired
  ShiftRepository shiftRepository;

  @Autowired
  ParticipantRepository participantRepository;

  @Autowired
  BossRepository bossRepository;

  @Autowired
  EventRepository eventRepository;

  @Autowired
  EventService eventService;

  ///////////////////////////////////

  @PostMapping("addboss")
  public Boss addBoss(@RequestBody Boss boss) {
    return bossRepository.save(boss);
  }

  @GetMapping("getbosses")
  public List<Boss> getBosses() {
    return bossRepository.findAll();
  }

  ////////////////////////////////////

  @PostMapping("addparticipant")
  public Participant addParticipant(@RequestBody Participant participant) {
    return participantRepository.save(participant);
  }

  @GetMapping("getparticipants")
  public List<Participant> getParticipants() {
    return participantRepository.findAll();
  }

  ////////////////////////////////////

  @PostMapping("addevent")
  public Event addEvent(@RequestBody EventRequest eventRequest) {
    return eventService.saveEvent(eventRequest);
  }

  @GetMapping("getevents")
  public List<Event> getEvents() {
    return eventRepository.findAll();
  }

  /////////////////////////////////////

  @PostMapping("addavailability")
  public ParticipantAvailability addParticipantAvailability(
    @RequestBody ParticipantAvailabilityRequest request
  ) {
    return participantAvailabilityService.saveParticipantAvailability(request);
  }

  @GetMapping("getavailability")
  public List<ParticipantAvailability> getAvailability() {
    return participantAvailabilityRepository.findAll();
  }

  //////////////////////////////////////
  @GetMapping("getshifts")
  public List<Shift> getShifts() {
    return shiftRepository.findAll();
  }
}
