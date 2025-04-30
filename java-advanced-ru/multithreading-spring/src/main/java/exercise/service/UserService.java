package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    // BEGIN
    public Mono<User> create(User data) {
        return userRepository.save(data);
    }

    public Mono<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Mono<User> update(User data, Long id) {
        return userRepository.findById(id)
                .flatMap((user) -> {
                    user.setFirstName(data.getFirstName());
                    user.setLastName((data.getLastName()));
                    user.setEmail(data.getEmail());
                    return userRepository.save(user);
                });
    }

    public Mono<Void> delete(Long id) {
        return userRepository.deleteById(id);
    }
    // END
}
