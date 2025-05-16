import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.vms.backend.controllers.HomeController;
import com.vms.backend.entities.Boss;
import com.vms.backend.repositories.BossRepository;
import com.vms.backend.pojos.LoginRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

// Extend Mockito to use mocking annotations
@ExtendWith(MockitoExtension.class)
class HomeControllerTest {
    // @InjectMocks creates an instance of the class and injects the mocks that are created with the @Mock annotations
    @InjectMocks
    private HomeController homeController;

    // Creates a mock of bossRepository and injects it above into homeController
    @Mock
    private BossRepository bossRepository;

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
        // Create mock behaviour for bossRepository to return the above bosses variable
        when(bossRepository.findAll()).thenReturn(bosses);
        // Get bosses from the homeController instance
        List<Boss> result = homeController.getBosses();

        //
        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getFullname());
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
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Alice", response.getBody().getFullname());
    }

}