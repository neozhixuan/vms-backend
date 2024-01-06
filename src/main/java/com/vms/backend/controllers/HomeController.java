package com.vms.backend.controllers;

import com.vms.backend.entities.Boss;
import com.vms.backend.entities.Event;
import com.vms.backend.entities.Participant;
import com.vms.backend.entities.ParticipantAvailability;
import com.vms.backend.entities.Shift;
import com.vms.backend.pojos.EventRequest;
import com.vms.backend.pojos.LoginRequest;
import com.vms.backend.pojos.ParticipantAvailabilityRequest;
import com.vms.backend.repositories.BossRepository;
import com.vms.backend.repositories.EventRepository;
import com.vms.backend.repositories.ParticipantAvailabilityRepository;
import com.vms.backend.repositories.ParticipantRepository;
import com.vms.backend.repositories.PersonRepository;
import com.vms.backend.repositories.ShiftRepository;
import com.vms.backend.services.EventService;
import com.vms.backend.services.ParticipantAvailabilityService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
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

  private static final String FRONTEND_URL = "https://vms-frontend-zeta.vercel.app/";

  ///////////////////////////////////
  @CrossOrigin(origins = FRONTEND_URL)
  @PostMapping("addboss")
  public Boss addBoss(@RequestBody Boss boss) {
    return bossRepository.save(boss);
  }

  @CrossOrigin(origins = FRONTEND_URL)
  @GetMapping("getbosses")
  public List<Boss> getBosses() {
    return bossRepository.findAll();
  }

  @CrossOrigin(origins = FRONTEND_URL)
  @GetMapping("getboss")
  public Optional<Boss> getBoss(@RequestParam Integer id) {
    return bossRepository.findById(id);
  }

  ////////////////////////////////////

  @CrossOrigin(origins = FRONTEND_URL)
  @PostMapping("addparticipant")
  public Participant addParticipant(@RequestBody Participant participant) {
    return participantRepository.save(participant);
  }

  @CrossOrigin(origins = FRONTEND_URL)
  @GetMapping("getparticipants")
  public List<Participant> getParticipants() {
    return participantRepository.findAll();
  }

  ////////////////////////////////////

  @CrossOrigin(origins = FRONTEND_URL)
  @PostMapping("addevent")
  public Event addEvent(@RequestBody EventRequest eventRequest) {
    return eventService.saveEvent(eventRequest);
  }

  @CrossOrigin(origins = FRONTEND_URL)
  @GetMapping("getevents")
  public List<Event> getEvents() {
    return eventRepository.findAll();
  }

  @CrossOrigin(origins = FRONTEND_URL)
  @GetMapping("getevent")
  public Optional<Event> getEvent(@RequestParam Integer id) {
    return eventRepository.findById(id);
  }

  @CrossOrigin(origins = FRONTEND_URL)
  @GetMapping("getbossevent")
  public List<Event> getBossEvent(@RequestParam Integer boss_id) {
    return eventRepository.findBossEvents(boss_id);
  }

  /////////////////////////////////////

  @CrossOrigin(origins = FRONTEND_URL)
  @PostMapping("addavailability")
  public ParticipantAvailability addParticipantAvailability(
    @RequestBody ParticipantAvailabilityRequest request
  ) {
    return participantAvailabilityService.saveParticipantAvailability(request);
  }

  @CrossOrigin(origins = FRONTEND_URL)
  @GetMapping("getavailability")
  public List<ParticipantAvailability> getAvailability() {
    return participantAvailabilityRepository.findAll();
  }

  //////////////////////////////////////

  @CrossOrigin(origins = FRONTEND_URL)
  @GetMapping("getshifts")
  public List<Shift> getShifts() {
    return shiftRepository.findAll();
  }

  //////////////////////////////////////

  @CrossOrigin(origins = FRONTEND_URL)
  @PostMapping("login")
  public ResponseEntity<Boss> login(@RequestBody LoginRequest loginRequest)
    throws NotFoundException {
    Optional<Boss> optionalBoss = bossRepository.findByEmailAndPassw(
      loginRequest.getEmail(),
      loginRequest.getPassw()
    );

    if (optionalBoss.isPresent()) {
      Boss boss = optionalBoss.get();
      return ResponseEntity.ok(boss);
    } else {
      throw new NotFoundException();
    }
  }

  @CrossOrigin(origins = FRONTEND_URL)
  @PostMapping("is_participant")
  public ResponseEntity<Participant> checkParticipant(
    @RequestBody LoginRequest loginRequest
  ) throws NotFoundException {
    Optional<Participant> optionalParticipant = participantRepository.findByEmailAndPassw(
      loginRequest.getEmail(),
      loginRequest.getPassw()
    );

    if (optionalParticipant.isPresent()) {
      Participant participant = optionalParticipant.get();
      return ResponseEntity.ok(participant);
    } else {
      throw new NotFoundException();
    }
  }
}
