package is.recruit.mycroft.spring.subjects.model.user.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    @PostConstruct
    void init() {
        String admin = "admin";
        String user = "user";
        Collection<Role> adminRoleList = roleRepository.findAllByUsername(admin);
        Collection<Role> userRoleList = roleRepository.findAllByUsername(user);

        if (adminRoleList.size() == 0) {
            Role adminRole = Role.builder().username(admin).roleName("ROLE_ADMIN").build();
            roleRepository.save(adminRole);
        }

        if (userRoleList.size() == 0) {
            Role userRole = Role.builder().username(user).roleName("ROLE_USER").build();
            roleRepository.save(userRole);
        }
    }
}
