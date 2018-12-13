package ndingspringboot.BlogSite.service;

import ndingspringboot.BlogSite.domain.Comment;

public interface CommentService {
    Comment getCommentById(Long id);

    void removeComment(Long id);
}
