package is.recruit.mycroft.spring.subjects.model.user.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "mc_role_tb")
@NoArgsConstructor @AllArgsConstructor
@Data
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long no;
    private String roleName;
    private String username;
}
