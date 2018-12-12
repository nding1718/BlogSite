package ndingspringboot.BlogSite.domain.Repository;

import ndingspringboot.BlogSite.domain.Blog;
import ndingspringboot.BlogSite.domain.Catalog;
import ndingspringboot.BlogSite.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    /**
     *
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    Page<Blog> findByUserAndTitleLikeOrderByCreateTimeDesc(User user, String title, Pageable pageable);

    /**
     *
     * @param user
     * @param title
     * @param
     * @param pageable
     * @return
     */
    Page<Blog> findByUserAndTitleLike(User user, String title, Pageable pageable);

    /**
     *
     * @param user
     * @param title
     * @param
     * @param pageable
     * @return
     */
    Page<Blog> findByTitleLikeAndUserOrTagsLikeAndUserOrderByCreateTimeDesc(String title,User user,String tags,User user2,Pageable pageable);
    /**
     *
     * @param
     * @param
     * @param
     * @param pageable
     * @return
     */
    Page<Blog> findByCatalog(Catalog catalog, Pageable pageable);
}
