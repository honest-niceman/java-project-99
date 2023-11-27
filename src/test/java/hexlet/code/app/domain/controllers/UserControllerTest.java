package hexlet.code.app.domain.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the {@link UserController}
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int localServerPort;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test find all")
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "fixtures/insert-users.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "fixtures/delete-users.sql")
    public void findAllTest() throws Exception {
        var result = mockMvc.perform(get("/api/users")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").password("admin")))
                .andExpect(status().isOk())
                .andReturn();
        var contentAsString = result.getResponse().getContentAsString();
        //language=json
        var expectedResult = """
                  [
                  {
                    "id": 1,
                    "firstName": "admin",
                    "lastName": "admin",
                    "email": "admin",
                    "createdAt": "2023-11-27T20:01:13.340624Z"
                  },
                  {
                    "id": 2,
                    "firstName": "user",
                    "lastName": "user",
                    "email": "user",
                    "createdAt": "2023-11-27T20:01:13.340624Z"
                  },
                  {
                    "id": 3,
                    "firstName": "user1",
                    "lastName": "user1",
                    "email": "user1",
                    "createdAt": "2023-11-27T20:01:13.340624Z"
                  }
                ]
                """;
        JSONAssert.assertEquals(expectedResult, contentAsString,false);
    }

    @Test
    @DisplayName("Test delete by id")
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "fixtures/insert-users.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "fixtures/delete-users.sql")
    public void deleteByIdTest() throws Exception {
        var resultFindAllBefore = mockMvc.perform(get("/api/users")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").password("admin")))
                .andExpect(status().isOk())
                .andReturn();
        var contentAsStringBefore = resultFindAllBefore.getResponse().getContentAsString();
        //language=json
        var expectedResultBefore = """
                  [
                  {
                    "id": 1,
                    "firstName": "admin",
                    "lastName": "admin",
                    "email": "admin",
                    "createdAt": "2023-11-27T20:01:13.340624Z"
                  },
                  {
                    "id": 2,
                    "firstName": "user",
                    "lastName": "user",
                    "email": "user",
                    "createdAt": "2023-11-27T20:01:13.340624Z"
                  },
                  {
                    "id": 3,
                    "firstName": "user1",
                    "lastName": "user1",
                    "email": "user1",
                    "createdAt": "2023-11-27T20:01:13.340624Z"
                  }
                ]
                """;
        JSONAssert.assertEquals(expectedResultBefore, contentAsStringBefore,false);
        //language=json
        var id = "1";
        mockMvc.perform(delete("/api/users/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").password("admin")))
                .andExpect(status().isOk());
        var resultFindAllAfter = mockMvc.perform(get("/api/users")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").password("admin")))
                .andExpect(status().isOk())
                .andReturn();
        var contentAsStringAfter = resultFindAllAfter.getResponse().getContentAsString();
        //language=json
        var expectedResultAfter = """
                  [
                  {
                    "id": 2,
                    "firstName": "user",
                    "lastName": "user",
                    "email": "user",
                    "createdAt": "2023-11-27T20:01:13.340624Z"
                  },
                  {
                    "id": 3,
                    "firstName": "user1",
                    "lastName": "user1",
                    "email": "user1",
                    "createdAt": "2023-11-27T20:01:13.340624Z"
                  }
                ]
                """;
        JSONAssert.assertEquals(expectedResultAfter, contentAsStringAfter,false);
    }

    @Test
    @DisplayName("Test find by id")
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "fixtures/insert-users.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "fixtures/delete-users.sql")
    public void findByIdTest() throws Exception {
        //language=json
        var id = "1";
        var result = mockMvc.perform(get("/api/users/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").password("admin")))
                .andExpect(status().isOk())
                .andReturn();
        var contentAsString = result.getResponse().getContentAsString();
        //language=json
        var expectedResult = """
                {
                  "id": 1,
                  "firstName": "admin",
                  "lastName": "admin",
                  "email": "admin",
                  "createdAt": "2023-11-27T20:01:13.340624Z"
                }""";
        JSONAssert.assertEquals(expectedResult, contentAsString,false);
    }

    @Test
    @DisplayName("Test update by id")
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "fixtures/insert-users.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "fixtures/delete-users.sql")
    public void updateByIdTest() throws Exception {
        //language=json
        var id = "1";
        //language=json
        var userRequestDto = """
                {
                	"firstName": "New",
                	"lastName": "New1",
                	"password": "password"
                }""";
        var result = mockMvc.perform(put("/api/users/{id}", id)
                        .content(userRequestDto)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").password("admin")))
                .andExpect(status().isOk())
                .andReturn();
        var contentAsString = result.getResponse().getContentAsString();
        //language=json
        var expectedResponse = """
                {
                  "id": 1,
                  "firstName": "New",
                  "lastName": "New1",
                  "email": "admin",
                  "createdAt": "2023-11-27T20:01:13.340624Z"
                }
                """;
        JSONAssert.assertEquals(expectedResponse, contentAsString, false);
    }

    @Test
    @DisplayName("Test save")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "fixtures/delete-users.sql")
    public void saveTest() throws Exception {
        //language=json
        var userRequestDto = """
                {
                	"firstName": "John",
                	"lastName": "Doe",
                	"email": "john@doe.com",
                	"password": "johny"
                }""";
        mockMvc.perform(post("/api/users")
                        .content(userRequestDto)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").password("admin")))
                .andExpect(status().isOk());
    }
}
