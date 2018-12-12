package ndingspringboot.BlogSite.domain.Repository;

import ndingspringboot.BlogSite.domain.Catalog;
import ndingspringboot.BlogSite.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {
    /**
     *
     * @param user
     * @return
     */
    List<Catalog> findByUser(User user);

    /**
     *
     * @param user
     * @param name
     * @return
     */
    List<Catalog> findByUserAndName(User user, String name);
}
