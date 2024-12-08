package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> index(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(name = "limit", required = false, defaultValue = "1") int limit) {
        return posts.stream()
                .skip((long) page * limit - limit)
                .limit(limit)
                .toList();
    }

    @GetMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Post> show(@PathVariable String id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public Post create(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post update(@PathVariable String id, @RequestBody Post data) {
        var maybePost = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (maybePost.isPresent()) {
            Post currentPost = maybePost.get();
            currentPost.setId(data.getId());
            currentPost.setBody(data.getBody());
            currentPost.setTitle(data.getTitle());
        }
        return data;
    }

    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
    // END
}
