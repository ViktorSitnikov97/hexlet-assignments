package exercise.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;

    private Task task;

    @BeforeEach
    public void setUp() {
        task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().sentence(3))
                .create();
        taskRepository.save(task);
    }

    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    @Test
    public void testShow() throws Exception {
        var result = mockMvc.perform(get("/tasks/{id}", task.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(task)))
                .andReturn();
        var body = result.getResponse().getContentAsString();

        assertThatJson(body).and(
                a -> a.node("title").isEqualTo(task.getTitle()),
                a -> a.node("description").isEqualTo(task.getDescription())
        );
    }

    @Test
    public void testShowNegative() throws Exception {
        var result = mockMvc.perform(get("/tasks/{id}", 1337))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void testCreate() throws Exception {
        var taskNew = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().sentence(2))
                .create();

        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(taskNew));

        mockMvc.perform(request)
                .andExpect(status().isCreated());

        var task1 = taskRepository.findByTitle(taskNew.getTitle()).get();

        assertThat(task1).isNotNull();
        assertThat(task1.getTitle()).isEqualTo(taskNew.getTitle());
        assertThat(task1.getDescription()).isEqualTo(taskNew.getDescription());
    }

    @Test
    public void testUpdate() throws Exception {
        var data = new HashMap<>();
        data.put("title", "Test Title");
        data.put("description", "Test Description");

        var request = put("/tasks/{id}", task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));

        mockMvc.perform(request)
                .andExpect(status().isOk());

        Task task1 = taskRepository.findById(task.getId()).get();
        assertThat(task1.getTitle()).isEqualTo(data.get("title"));
        assertThat(task1.getDescription()).isEqualTo(data.get("description"));
    }

    @Test
    public void testUpdateNegative() throws Exception {
        var request = put("/tasks/{id}", 322)
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(task));
        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/tasks/{id}", task.getId()))
                .andExpect(status().isOk());

        assertThat(taskRepository.findById(task.getId())).isEmpty();
    }
    // END
}
