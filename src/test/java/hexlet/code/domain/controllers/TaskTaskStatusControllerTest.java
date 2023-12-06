package hexlet.code.domain.controllers;

import hexlet.code.repository.TaskStatusRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the {@link hexlet.code.controller.TaskStatusController}
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
public class TaskTaskStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Test
    @DisplayName("Test delete")
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "fixtures/status/insert-statuses.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "fixtures/status/delete-statuses.sql")
    public void deleteTest() throws Exception {
        //language=json
        String id = "1";

        mockMvc.perform(delete("/api/task_statuses/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status()
                        .isNoContent())
                .andDo(print());
    }

    @Test
    @DisplayName("Test find all")
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "fixtures/status/insert-statuses.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "fixtures/status/delete-statuses.sql")
    public void findAllTest() throws Exception {
        mockMvc.perform(get("/api/task_statuses")
                        .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status()
                        .isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Test find by id")
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "fixtures/status/insert-statuses.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "fixtures/status/delete-statuses.sql")
    public void findByIdTest() throws Exception {
        //language=json
        String id = "1";

        mockMvc.perform(get("/api/task_statuses/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status()
                        .isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Test save")
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "fixtures/status/insert-statuses.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "fixtures/status/delete-statuses.sql")
    public void saveTest() throws Exception {
        //language=json
        String statusRequest = """
                {
                  "name": "To Be Reviewed",
                  "slug": "to_be_reviewed"
                }""";

        mockMvc.perform(post("/api/task_statuses")
                        .content(statusRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status()
                        .isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("Test update by id")
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "fixtures/status/insert-statuses.sql")
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "fixtures/status/delete-statuses.sql")
    public void updateByIdTest() throws Exception {
        //language=json
        String id = "101";
        //language=json
        String statusRequest = """
                {
                  "name": "New Name",
                  "slug": "new_name"
                }""";

        mockMvc.perform(put("/api/task_statuses/{id}", id)
                        .content(statusRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status()
                        .isOk())
                .andDo(print());
    }
}
