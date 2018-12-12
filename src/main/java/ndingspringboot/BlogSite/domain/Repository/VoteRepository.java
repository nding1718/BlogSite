package ndingspringboot.BlogSite.domain.Repository;

import ndingspringboot.BlogSite.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote,Long> {
}
