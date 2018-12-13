package ndingspringboot.BlogSite.service;

import ndingspringboot.BlogSite.domain.EsBlog;
import ndingspringboot.BlogSite.domain.User;
import ndingspringboot.BlogSite.utils.TagVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface EsBlogService {
    /**
     * Remove esblog by id
     * @param id
     */
    void removeEsBlog(String id);

    /**
     * update esblog
     * @param esBlog
     * @return
     */
    EsBlog updateEsBlog(EsBlog esBlog);

    /**
     * Get esblog by id
     * @param blogId
     * @return
     */
    EsBlog getEsBlogByBlogId(Long blogId);

    /**
     * Find the newest blog
     * @param keyword
     * @param pageable
     * @return
     */
    Page<EsBlog> listNewestEsBlogs(String keyword, Pageable pageable);

    /**
     * Get hotest esblog
     * @param keyword
     * @param pageable
     * @return
     */
    Page<EsBlog> listHotestEsBlogs(String keyword, Pageable pageable);

    /**
     * Pagable search
     * @param pageable
     * @return
     */
    Page<EsBlog> listEsBlogs(Pageable pageable);

    /**
     * Top five newest esblog
     * @return
     */
    List<EsBlog> listTop5NewestEsBlogs();

    /**
     * Top five hotest blog
     * @return
     */
    List<EsBlog> listTop5HotestEsBlogs();

    /**
     * Top thirty tags
     * @return
     */
    List<TagVO> listTop30Tags();

    /**
     * Top twelve uses
     * @return
     */
    List<User> listTop12Users();
}

