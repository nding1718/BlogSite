package ndingspringboot.BlogSite.domain;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
public class Authority implements GrantedAuthority {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
