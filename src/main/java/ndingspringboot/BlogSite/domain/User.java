package ndingspringboot.BlogSite.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String name;

    protected User() {}; // according the JPA API we must have a public or protected constructor

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("User[id=%d, name='%s', email='%s']", id, name, email);
    }

}
