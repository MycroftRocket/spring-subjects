package is.recruit.mycroft.spring.subjects.model.user;

import is.recruit.mycroft.spring.subjects.model.user.role.Role;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "mc_user_tb")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {

    @Id
    private String username;
    private String password;
    private boolean enabled;
    @OneToMany(mappedBy = "username")
    private List<Role> roles;
}
