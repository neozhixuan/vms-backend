package com.vms.backend.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.vms.backend.entities.Boss;
import com.vms.backend.repositories.BossRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

// Integration Tests
// - Simulate HTTP requests with MockMvc
// - Use H2/Testcontainers/PG for database
// - Test spring annotations

// Loads the full Spring ApplicationContext but doesn't start a real HTTP server
@SpringBootTest
//  If you want Spring to provide and configure a MockMvc bean.
@AutoConfigureMockMvc
class HomeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BossRepository bossRepository;

    @BeforeEach
    void setup() {
        // Before each test, clear repository and insert one boss
        bossRepository.deleteAll();
        Boss boss = new Boss();
        boss.setId(1);
        boss.setFullname("Alice");
        boss.setEmail("alice@mail.com");
        boss.setPassw("pass");
        boss.setUsern("aliceuser");
        bossRepository.save(boss);
    }

    /**
     * Test the /getbosses endpoint via a POST request
     */
    @Test
    void testGetBossesEndpoint() throws Exception {
        // Expect that the first item in the response has a 'fullname' key with the value 'Alice'
        mockMvc.perform(get("/getbosses"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].fullname").value("Alice"));
    }

    /**
     * Test the /getboss endpoint via a POST request
     */
    @Test
    void testGetBossEndpoint() throws Exception {
        mockMvc.perform(get("/getboss"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].fullname").value("Alice"));
    }


    /**
     * Test the /login endpoint via a POST request
     */
    @Test
    void testLoginEndpoint() throws Exception {

        String json = """
        {
            "email": "alice@mail.com",
            "passw": "pass"
        }
        """;

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fullname").value("Alice"));
    }

}