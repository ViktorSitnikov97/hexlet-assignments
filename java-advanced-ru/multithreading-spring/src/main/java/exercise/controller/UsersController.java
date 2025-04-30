package exercise.controller;

import exercise.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import exercise.service.UserService;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping(path = "")
    public Flux<User> getUsers() {
        return userService.findAll();
    }

    // BEGIN
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<User> getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public Mono<User> createUser(@RequestBody User data) {
        return userService.create(data);
    }
    @PatchMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<User> updateUser(@RequestBody User data, @PathVariable Long id) {
        return userService.update(data, id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteUser(@PathVariable Long id) {
        return userService.delete(id);
    }
    // END
}
