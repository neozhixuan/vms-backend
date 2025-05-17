package com.vms.backend.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;


import com.vms.backend.entities.Boss;
import com.vms.backend.entities.Participant;
import com.vms.backend.pojos.LoginRequest;
import com.vms.backend.repositories.BossRepository;
import com.vms.backend.repositories.ParticipantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

// Extend Mockito to use mocking annotations
@ExtendWith(MockitoExtension.class)
class HomeControllerTest {
    // @InjectMocks creates an instance of the class and injects the mocks that are created with the @Mock annotations
    @InjectMocks
    private HomeController homeController;

    // Creates a mock of bossRepository and injects it above into homeController
    @Mock
    private BossRepository bossRepository;

    @Mock
    private ParticipantRepository participantRepository;

    /**
     * Test the getBosses() function
     */
    @Test
    void testGetBosses() {
        // Generate a list of bosses
        Boss boss = new Boss();
        boss.setId(1);
        boss.setFullname("Alice");
        boss.setEmail("alice@mail.com");
        boss.setPassw("pass");
        boss.setUsern("aliceuser");

        List<Boss> bosses = List.of(boss);
        when(bossRepository.findAll()).thenReturn(bosses);
        // Get bosses from the homeController instance
        List<Boss> result = homeController.getBosses();

        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getFullname());
    }

    /**
     * Test the getBoss() function
     */
    @Test
    void testGetBoss() {
        // Generate a list of bosses
        Boss boss = new Boss();
        boss.setId(1);
        boss.setFullname("Alice");
        boss.setEmail("alice@mail.com");
        boss.setPassw("pass");
        boss.setUsern("aliceuser");

        when(bossRepository.findById(eq(1))).thenReturn(Optional.of(boss));

        Optional<Boss> result = homeController.getBoss(1);

        // Make sure the Optional has a value
        assertTrue(result.isPresent());
        // Get the value if it is present
        assertEquals("Alice", result.get().getFullname());
    }

    /**
     * Test the addBoss() function
     */
    @Test
    void testAddBoss() {
        // Generate a boss
        Boss boss = new Boss();
        boss.setId(1);
        boss.setFullname("Alice");
        boss.setEmail("alice@mail.com");
        boss.setPassw("pass");
        boss.setUsern("aliceuser");

        // Use mockito to do mock behaviour when we pass in this specific `boss` variable
        // - bossRepository to return the bosses variable
        when(bossRepository.save(boss)).thenReturn(boss);
        // Actually pass in the boss variable
        Boss result = homeController.addBoss(boss);

        assertEquals("Alice", result.getFullname());
    }


    @Test
    void testLoginSuccess() throws NotFoundException {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@mail.com");
        loginRequest.setPassw("pass123");

        Boss boss = new Boss();
        boss.setId(1);
        boss.setFullname("Alice");
        boss.setEmail("test@mail.com");
        boss.setPassw("pass123");
        boss.setUsern("aliceuser");
        // Creates an Optional instance of Boss with this value
        // - Indication that there might be no result
        when(bossRepository.findByEmailAndPassw("test@mail.com", "pass123"))
            .thenReturn(Optional.of(boss));

        // Test the function call and assert a 200 response
        ResponseEntity<Boss> response = homeController.login(loginRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Alice", response.getBody().getFullname());
    }

    @Test
    void testLoginNotFound() throws NotFoundException {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@mail.com");
        loginRequest.setPassw("pass123");

        when(bossRepository.findByEmailAndPassw("test@mail.com", "pass123"))
            .thenReturn(Optional.empty());

        // Test the homeController.login function, and assert error
        assertThrows(NotFoundException.class, () -> {
            homeController.login(loginRequest);
        });
    }

    @Test
    void testIsParticipant() throws NotFoundException {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@mail.com");
        loginRequest.setPassw("pass123");

        Participant participant = new Participant();
        participant.setId(1);
        participant.setFullname("Alice");
        participant.setEmail("test@mail.com");
        participant.setPassw("pass123");

        when(participantRepository.findByEmailAndPassw("test@mail.com", "pass123"))
            .thenReturn(Optional.of(participant));

        // Test the function call and assert a 200 response
        ResponseEntity<Boss> response = homeController.login(loginRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Alice", response.getBody().getFullname());

    }

}