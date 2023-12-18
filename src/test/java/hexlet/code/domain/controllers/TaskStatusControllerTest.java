package hexlet.code.domain.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.model.TaskStatus;
import hexlet.code.repository.TaskStatusRepository;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        properties = {
                "spring.jpa.defer-datasource-initialization=false",
                "spring.sql.init.mode=never"
        }
)
@AutoConfigureMockMvc
public class TaskStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper om;
    @Autowired
    private TaskStatusRepository taskStatusRepository;

    private TaskStatus testTaskStatus;

    @BeforeEach
    public void beforeEach() {
        testTaskStatus = Instancio.of(TaskStatus.class)
                .ignore(Select.field(TaskStatus.class, "id"))
                .create();
        taskStatusRepository.save(testTaskStatus);
    }

    @Test
    @DisplayName("Test delete")
    public void deleteTest() throws Exception {
        mockMvc.perform(delete("/api/task_statuses/{id}", testTaskStatus.getId())
                        .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status()
                        .isNoContent())
                .andDo(print());
    }

    @Test
    @DisplayName("Test find all")
    public void findAllTest() throws Exception {
        mockMvc.perform(get("/api/task_statuses")
                        .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status()
                        .isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Test find by id")
    public void findByIdTest() throws Exception {
        mockMvc.perform(get("/api/task_statuses/{id}", testTaskStatus.getId())
                        .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status()
                        .isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Test save")
    public void saveTest() throws Exception {
        var data = Instancio.of(TaskStatus.class)
                .ignore(Select.field(TaskStatus.class, "id"))
                .create();

        mockMvc.perform(post("/api/task_statuses")
                        .content(om.writeValueAsString(data))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status()
                        .isCreated())
                .andDo(print());

        var taskStatus = taskStatusRepository.findBySlug(data.getSlug());
        assertNotNull(taskStatus.get());
    }

    @Test
    @DisplayName("Test update by id")
    public void updateByIdTest() throws Exception {
        var data = Instancio.of(TaskStatus.class)
                .ignore(Select.field(TaskStatus.class, "id"))
                .create();

        mockMvc.perform(put("/api/task_statuses/{id}", testTaskStatus.getId())
                        .content(om.writeValueAsString(data))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status()
                        .isOk())
                .andDo(print());
    }
}
