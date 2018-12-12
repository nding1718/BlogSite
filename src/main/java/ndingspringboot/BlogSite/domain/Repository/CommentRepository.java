package ndingspringboot.BlogSite.domain.Repository;

import ndingspringboot.BlogSite.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
