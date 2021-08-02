package is.recruit.mycroft.spring.subjects.model.user.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Collection<Role> findAllByUsername(String username);
}
