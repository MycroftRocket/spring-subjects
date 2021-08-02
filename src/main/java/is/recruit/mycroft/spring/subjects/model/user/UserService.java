package is.recruit.mycroft.spring.subjects.model.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @PostConstruct
    void init() {
        String adminUsername = "admin";
        String userUsername = "user";
        User admin = userRepository.findById(adminUsername).orElse(null);
        User user = userRepository.findById(userUsername).orElse(null);

        if (ObjectUtils.isEmpty(admin)) {
            admin = User.builder()
                    .username(adminUsername)
                    .password(Base64.getEncoder().encodeToString(adminUsername.getBytes()))
                    .enabled(true)
                    .build();
            userRepository.save(admin);
        }

        if (ObjectUtils.isEmpty(user)) {
            user = User.builder()
                    .username(userUsername)
                    .password(Base64.getEncoder().encodeToString(userUsername.getBytes()))
                    .enabled(true)
                    .build();
            userRepository.save(user);
        }
    }
}
