package hexlet.code.domain.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.dto.TaskRequest;
import hexlet.code.model.Task;
import hexlet.code.repository.LabelRepository;
import hexlet.code.repository.TaskRepository;
import hexlet.code.repository.TaskStatusRepository;
import hexlet.code.utils.ModelGenerator;
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

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the {@link hexlet.code.controller.TaskController}
 */
@SpringBootTest(
        properties = {
                "spring.jpa.defer-datasource-initialization=false",
                "spring.sql.init.mode=never"
        }
)
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper om;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskStatusRepository taskStatusRepository;
    @Autowired
    private LabelRepository labelRepository;
    @Autowired
    private ModelGenerator modelGenerator;

    private Task testTask;

    @BeforeEach
    public void beforeEach() {
        var taskStatus = taskStatusRepository.findBySlug("draft").orElseThrow();
        testTask = Instancio.of(modelGenerator.getTaskModel())
                .set(Select.field(Task::getAssignee), null)
                .create();
        testTask.setTaskStatus(taskStatus);
        testTask.setLabels(Set.of());
        taskRepository.save(testTask);
    }

    @Test
    @DisplayName("Test delete by id")
    public void deleteByIdTest() throws Exception {
        mockMvc.perform(delete("/api/tasks/{id}", testTask.getId())
                        .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status()
                        .isNoContent())
                .andDo(print());
    }

    @Test
    @DisplayName("Test find all")
    public void findAllTest() throws Exception {
        mockMvc.perform(get("/api/tasks")
                        .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status()
                        .isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Test find by id")
    public void findByIdTest() throws Exception {
        mockMvc.perform(get("/api/tasks/{id}", testTask.getId())
                        .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status()
                        .isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Test save")
    public void saveTest() throws Exception {
        var taskStatus = taskStatusRepository.findBySlug("draft").get();
        var label = labelRepository.findByName("feature").get();
        var data = new TaskRequest();
        String name = "New Task Name";
        data.setTitle(name);
        data.setStatus(taskStatus.getSlug());
        data.setTaskLabelIds(Set.of(label.getId()));

        mockMvc.perform(post("/api/tasks")
                        .content(om.writeValueAsString(data))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(status()
                        .isCreated())
                .andDo(print());

        var task = taskRepository.findByName(name);
        assertNotNull(task.get());
    }

    @Test
    @DisplayName("Test update by id")
    public void updateByIdTest() throws Exception {
        var data = Instancio.of(modelGenerator.getTaskModel())
                .create();

        mockMvc.perform(put("/api/tasks/{id}", testTask.getId())
                        .content(om.writeValueAsString(data))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(status()
                        .isOk())
                .andDo(print());
    }
}
