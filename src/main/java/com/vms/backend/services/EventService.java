package com.vms.backend.services;

import com.vms.backend.entities.Event;
import com.vms.backend.entities.Shift;
import com.vms.backend.pojos.EventRequest;
import com.vms.backend.pojos.ShiftRequest;
import com.vms.backend.repositories.BossRepository;
import com.vms.backend.repositories.EventRepository;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

  @Autowired
  EventRepository eventRepository;

  @Autowired
  BossRepository bossRepository;

  public EventService() {}

  public Event saveEvent(EventRequest eventRequest) {
    Event event = new Event();

    List<ShiftRequest> shiftRequests = eventRequest.getShifts();
    if (shiftRequests != null && !shiftRequests.isEmpty()) {
      List<Shift> shifts = shiftRequests
        .stream()
        .map(shiftRequest -> {
          Shift shift = new Shift();
          shift.setStart_time(shiftRequest.getStart_time());
          shift.setEnd_time(shiftRequest.getEnd_time());
          shift.setEvent(event);
          shift.setAvailabilities(Collections.emptyList());
          return shift;
        })
        .collect(Collectors.toList());
      event.setShifts(shifts);
    }

    event.setBoss(bossRepository.findById(eventRequest.getBoss_id()));
    event.setTitle(eventRequest.getTitle());
    event.setDescription(eventRequest.getDescription());
    return eventRepository.save(event);
  }
}
