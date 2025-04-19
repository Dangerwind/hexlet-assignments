package exercise.controller;

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
import org.springframework.web.bind.annotation.PathVariable;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


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

    public Task dataPrepare() {
        return Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().sentence(25))
                .create();
    }
   // Просмотр конкретной задачи
    @Test
    public void testShowTask() throws Exception {

        var task = dataPrepare();

        taskRepository.save(task);

        var result = mockMvc.perform(get("/tasks/" + task.getId()))
        .andExpect(status().isOk())
        .andReturn();

        var body = result.getResponse().getContentAsString();
        System.out.println(body + " !!!!!!!!!!!");
        assertThatJson(body)
                .and(
                        a -> a.node("title").isEqualTo(task.getTitle()),
                        a -> a.node("description")  .isEqualTo(task.getDescription())
                );
        taskRepository.delete(task);
    }



   // Создание новой задачи
   @Test
   public void testCreateTask() throws Exception {

       var task = dataPrepare();

       var request = post("/tasks")
               .contentType(MediaType.APPLICATION_JSON)
               .content(om.writeValueAsString(task));

       var result = mockMvc.perform(request)
               .andExpect(status().isCreated())
               .andReturn();

       var res = taskRepository.findByTitle(task.getTitle()).get();
       assertThat(res).isNotNull();
       assertThat(res.getTitle()).isEqualTo(task.getTitle());
       assertThat(res.getDescription()).isEqualTo(task.getDescription());

       taskRepository.delete(task);
   }

   // Обновление существующей задачи
   @Test
   public void testUpdateTask() throws Exception {

       var task = dataPrepare();
       taskRepository.save(task);

       task.setDescription(faker.lorem().sentence(25));

       var request = put("/tasks/" + task.getId())
               .contentType(MediaType.APPLICATION_JSON)
               .content(om.writeValueAsString(task));

       var result = mockMvc.perform(request)
               .andExpect(status().isOk())
               .andReturn();

       var res = taskRepository.findById(task.getId()).get();
       assertThat(res).isNotNull();
       assertThat(res.getDescription()).isEqualTo(task.getDescription());

       taskRepository.delete(task);
   }
   // Удаление задачи
   @Test
   public void testDeleteTask() throws Exception {

       var task = dataPrepare();
       taskRepository.save(task);

       var request = delete("/tasks/" + task.getId());
              // .contentType(MediaType.APPLICATION_JSON)
              // .content(om.writeValueAsString(task));

       mockMvc.perform(request)
               .andExpect(status().isOk())
               .andReturn();

       var res = taskRepository.findById(task.getId()).orElse(null);
       assertThat(res).isNull();
       

       taskRepository.delete(task);
   }


    // END
}
