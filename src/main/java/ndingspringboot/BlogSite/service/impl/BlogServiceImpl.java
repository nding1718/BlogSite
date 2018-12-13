package ndingspringboot.BlogSite.service.impl;

import ndingspringboot.BlogSite.domain.*;
import ndingspringboot.BlogSite.domain.Repository.BlogRepository;
import ndingspringboot.BlogSite.service.BlogService;
import ndingspringboot.BlogSite.service.EsBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BlogServiceImpl implements BlogService {

    private BlogRepository blogRepository;

    private EsBlogService esBlogService;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository, EsBlogService esBlogService) {
        this.blogRepository = blogRepository;
        this.esBlogService = esBlogService;
    }

    /**
     * Save and update the blog and the corresponding Esblog
     * @param blog
     * @return
     */
    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        boolean isNew = (blog.getId() == null);
        EsBlog esBlog = null;

        Blog returnBlog = blogRepository.save(blog);

        if (isNew) {
            esBlog = new EsBlog(returnBlog);
        } else {
            esBlog = esBlogService.getEsBlogByBlogId(blog.getId());
            esBlog.update(returnBlog);
        }

        esBlogService.updateEsBlog(esBlog);
        return returnBlog;
    }

    /**
     * Remove a blog and the corresponding esblog
     * @param id
     */
    @Transactional
    @Override
    public void removeBlog(Long id) {
        blogRepository.delete(id);
        EsBlog esblog = esBlogService.getEsBlogByBlogId(id);
        esBlogService.removeEsBlog(esblog.getId());
    }

    /**
     * Find a blog by Id
     * @param id
     * @return
     */
    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findOne(id);
    }

    /**
     * Like search according to title and tags search
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    @Override
    public Page<Blog> listBlogsByTitleLike(User user, String title, Pageable pageable) {
        title = "%" + title + "%";
        String tags = title;
        Page<Blog> blogs = blogRepository.findByTitleLikeAndUserOrTagsLikeAndUserOrderByCreateTimeDesc(title,user, tags,user, pageable);
        return blogs;
    }

    /**
     * Like search for a specific user's blogs according to the title
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    @Override
    public Page<Blog> listBlogsByTitleLikeAndSort(User user, String title, Pageable pageable) {
        // 模糊查询
        title = "%" + title + "%";
        Page<Blog> blogs = blogRepository.findByUserAndTitleLike(user, title, pageable);
        return blogs;
    }

    /**
     * Search blogs by catalog
     * @param catalog
     * @param pageable
     * @return
     */
    @Override
    public Page<Blog> listBlogsByCatalog(Catalog catalog, Pageable pageable) {
        return blogRepository.findByCatalog(catalog, pageable);
    }

    /**
     * Increase the read times for a specific blog
     * @param id
     */
    @Override
    public void readingIncrease(Long id) {
        Blog blog = blogRepository.findOne(id);
        blog.setReadSize(blog.getReadSize()+1);
        this.saveBlog(blog);
    }

    /**
     * Note we don't pass user information, since we can get that from the context
     * @param blogId
     * @param commentContent
     * @return
     */
    @Override
    public Blog createComment(Long blogId, String commentContent) {
        Blog originalBlog = blogRepository.findOne(blogId);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = new Comment(user, commentContent);
        originalBlog.addComment(comment);
        return this.saveBlog(originalBlog);
    }

    /**
     * Remove specific comment for a specific blog
     * @param blogId
     * @param commentId
     */
    @Override
    public void removeComment(Long blogId, Long commentId) {
        Blog originalBlog = blogRepository.findOne(blogId);
        originalBlog.removeComment(commentId);
        this.saveBlog(originalBlog);
    }

    /**
     * Create a like vote for a specific blog
     * @param blogId
     * @return
     */
    @Override
    public Blog createVote(Long blogId) {
        Blog originalBlog = blogRepository.findOne(blogId);
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Vote vote = new Vote(user);
        boolean isExist = originalBlog.addVote(vote);
        if (isExist) {
            throw new IllegalArgumentException("This user has already liked!");
        }
        return this.saveBlog(originalBlog);
    }

    /**
     * Remove a specific like vote for a specific blog
     * @param blogId
     * @param voteId
     */
    @Override
    public void removeVote(Long blogId, Long voteId) {
        Blog originalBlog = blogRepository.findOne(blogId);
        originalBlog.removeVote(voteId);
        this.saveBlog(originalBlog);
    }

}
