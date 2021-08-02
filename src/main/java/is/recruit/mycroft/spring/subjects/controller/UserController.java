package is.recruit.mycroft.spring.subjects.controller;

import is.recruit.mycroft.spring.subjects.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("all")
    public Flux<?> getAll() {
        return Flux.fromIterable(userRepository.findAll());
    }
}
