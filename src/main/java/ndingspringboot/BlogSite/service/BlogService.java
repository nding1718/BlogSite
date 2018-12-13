package ndingspringboot.BlogSite.service;

import ndingspringboot.BlogSite.domain.Blog;
import ndingspringboot.BlogSite.domain.Catalog;
import ndingspringboot.BlogSite.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    /**
     * Save the blog
     * @param blog
     * @return
     */
    Blog saveBlog(Blog blog);

    /**
     * Delete the blog
     * @param id
     */
    void removeBlog(Long id);

    /**
     * Get a specific blog by id
     * @param id
     * @return
     */
    Blog getBlogById(Long id);

    /**
     * Like search by title
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    Page<Blog> listBlogsByTitleLike(User user, String title, Pageable pageable);

    /**
     * Like search by title and rank
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    Page<Blog> listBlogsByTitleLikeAndSort(User user, String title, Pageable pageable);

    /**
     * Increase the readingsize
     * @param id
     */
    void readingIncrease(Long id);

    /**
     * Create a comment
     * @param blogId
     * @param commentContent
     * @return
     */
    Blog createComment(Long blogId, String commentContent);

    /**
     * Remove a comment
     * @param blogId
     * @param commentId
     */
    void removeComment(Long blogId, Long commentId);

    /**
     * Create a Vote
     * @param blogId
     * @return
     */
    Blog createVote(Long blogId);

    /**
     * Remove a vote
     * @param blogId
     * @param voteId
     */
    void removeVote(Long blogId, Long voteId);

    /**
     * Search by catalog
     * @param catalog
     * @param pageable
     * @return
     */
    Page<Blog> listBlogsByCatalog(Catalog catalog, Pageable pageable);


}

