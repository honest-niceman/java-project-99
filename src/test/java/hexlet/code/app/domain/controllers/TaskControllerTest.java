package hexlet.code.app.domain.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the {@link TaskController}
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.jpa.defer-datasource-initialization=false",
                "spring.sql.init.mode=never"
        }
)
@AutoConfigureMockMvc
@DirtiesContext
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test delete by id")
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {
            "fixtures/label/insert-labels.sql",
            "fixtures/status/insert-statuses.sql",
            "fixtures/user/insert-users.sql",
            "fixtures/task/insert-tasks.sql"
    })
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {
            "fixtures/task/delete-tasks.sql",
            "fixtures/label/delete-labels.sql",
            "fixtures/status/delete-statuses.sql",
            "fixtures/user/delete-users.sql"
    })
    public void deleteByIdTest() throws Exception {
        //language=json
        String id = "1";

        mockMvc.perform(delete("/api/tasks/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status()
                        .isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Test find all")
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {
            "fixtures/label/insert-labels.sql",
            "fixtures/status/insert-statuses.sql",
            "fixtures/user/insert-users.sql",
            "fixtures/task/insert-tasks.sql"
    })
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {
            "fixtures/task/delete-tasks.sql",
            "fixtures/label/delete-labels.sql",
            "fixtures/status/delete-statuses.sql",
            "fixtures/user/delete-users.sql"
    })
    public void findAllTest() throws Exception {
        //language=json
        String taskParams = """
                {
                	"titleCont": "",
                	"assigneeId": "",
                	"status": "",
                	"labelId": ""
                }""";

        mockMvc.perform(get("/api/tasks")
                        .content(taskParams)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status()
                        .isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Test find by id")
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {
            "fixtures/label/insert-labels.sql",
            "fixtures/status/insert-statuses.sql",
            "fixtures/user/insert-users.sql",
            "fixtures/task/insert-tasks.sql"
    })
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {
            "fixtures/task/delete-tasks.sql",
            "fixtures/label/delete-labels.sql",
            "fixtures/status/delete-statuses.sql",
            "fixtures/user/delete-users.sql"
    })
    public void findByIdTest() throws Exception {
        //language=json
        String id = "1";

        mockMvc.perform(get("/api/tasks/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status()
                        .isOk())
                .andDo(print());
    }

    @Test
    @Order(Integer.MAX_VALUE)
    @DisplayName("Test save")
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {
            "fixtures/label/insert-labels.sql",
            "fixtures/status/insert-statuses.sql",
            "fixtures/user/insert-users.sql",
            "fixtures/task/insert-tasks.sql"
    })
    public void saveTest() throws Exception {
        //language=json
        String taskRequest = """
                {
                  "title": "t",
                  "index": "121",
                  "content": "c",
                  "status": "first_status",
                  "assignee_id": 1,
                  "taskLabelIds": [
                    "1",
                    "2"
                  ]
                }""";

        mockMvc.perform(post("/api/tasks")
                        .content(taskRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(status()
                        .isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Test update by id")
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {
            "fixtures/label/insert-labels.sql",
            "fixtures/status/insert-statuses.sql",
            "fixtures/user/insert-users.sql",
            "fixtures/task/insert-tasks.sql"
    })
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {
            "fixtures/task/delete-tasks.sql",
            "fixtures/label/delete-labels.sql",
            "fixtures/status/delete-statuses.sql",
            "fixtures/user/delete-users.sql"
    })
    public void updateByIdTest() throws Exception {
        //language=json
        String id = "1";
        //language=json
        String taskRequest = """
                {
                  "title": "newT",
                  "index": 121,
                  "content": "newC",
                  "status": "second_status",
                  "assigneeId": 2,
                  "taskLabelIds": [
                    2
                  ]
                }""";

        mockMvc.perform(put("/api/tasks/{id}", id)
                        .content(taskRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(status()
                        .isOk())
                .andDo(print());
    }
}
