package exercise;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;
import lombok.Setter;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    @Setter
    private static  List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN

    @GetMapping("/posts") // Список страниц
    public ResponseEntity<List<Post>> index() {

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(posts);

    }

    @GetMapping("/posts/{id}") // Вывод страницы
    public ResponseEntity<Post> show(@PathVariable String id) {
        System.out.println(" ====== i am tut ======");

        var ret = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (ret.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok().body(ret.get());
    }


    @PostMapping("/posts") // Создание страницы
    public ResponseEntity<Post> create(@RequestBody Post page) {
        posts.add(page);
        return ResponseEntity.status(201).body(page);
    }

    @PutMapping("/posts/{id}") // Обновление страницы
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post data) {
        var maybePage = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        int stat = 200;
        if (maybePage.isPresent()) {
            var page = maybePage.get();
            page.setTitle(data.getTitle());
            page.setBody(data.getBody());
            page.setId(data.getId());
        } else {
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.ok().body(maybePage.get());
    }
    
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
