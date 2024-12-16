package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> index() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post show(@PathVariable long id) {
        return postRepository.findAll().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post dataPost) {
        return postRepository.save(dataPost);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post update(@RequestBody Post postData, @PathVariable long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + postData.getId() + " not found"));

        post.setBody(postData.getBody());
        post.setTitle(postData.getTitle());
        return postRepository.save(post);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void destroy(@PathVariable long id) {
        postRepository.deleteById(id);
        commentRepository.deleteByPostId(id);
    }
}
// END
