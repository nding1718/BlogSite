package ndingspringboot.BlogSite.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;


@Data
@Entity
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "the content con not be empty")
    @Size(min = 2, max = 500)
    @Column(nullable = false)
    private String content;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @org.hibernate.annotations.CreationTimestamp // the create time will be created by the database itself
    private Timestamp createTime;

    protected Comment() {}

    public Comment(User user, String content) {
        this.content = content;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
}
