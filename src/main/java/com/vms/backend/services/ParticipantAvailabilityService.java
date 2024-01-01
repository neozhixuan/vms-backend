package com.vms.backend.services;

import com.vms.backend.entities.Event;
import com.vms.backend.entities.Participant;
import com.vms.backend.entities.ParticipantAvailability;
import com.vms.backend.entities.Shift;
import com.vms.backend.pojos.ParticipantAvailabilityRequest;
import com.vms.backend.repositories.EventRepository;
import com.vms.backend.repositories.ParticipantAvailabilityRepository;
import com.vms.backend.repositories.ParticipantRepository;
import com.vms.backend.repositories.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantAvailabilityService {

  @Autowired
  ParticipantRepository participantRepository;

  @Autowired
  ParticipantAvailabilityRepository participantAvailabilityRepository;

  @Autowired
  EventRepository eventRepository;

  @Autowired
  ShiftRepository shiftRepository;

  public ParticipantAvailability saveParticipantAvailability(
    ParticipantAvailabilityRequest request
  ) {
    ParticipantAvailability participantAvailability = new ParticipantAvailability();

    Participant participant = participantRepository.findById(
      request.getParticipant_id()
    );
    Event event = eventRepository.findById(request.getEvent_id());
    Shift shift = shiftRepository.findById(request.getShift_id());

    participantAvailability.setIs_allocated(request.getIs_allocated());
    participantAvailability.setParticipant(participant);
    participantAvailability.setEvent(event);
    participantAvailability.setShift(shift);

    return participantAvailabilityRepository.save(participantAvailability);
  }
}
