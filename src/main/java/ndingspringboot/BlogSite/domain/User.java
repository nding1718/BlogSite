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

    private String username;
    private String password;

    private String avatar;

    protected User() {}; // according the JPA API we must have a public or protected constructor

    public User(String email, String name, String username) {
        this.email = email;
        this.name = name;
        this.username = username;
    }

    @Override
    public String toString() {
        return String.format("User[id=%d, name='%s', username='%s', email='%s']", id, name, username,email);
    }

}
