package com.hbgc.personblog.repository;


import com.hbgc.personblog.domain.Article;
import com.hbgc.personblog.domain.PostComment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCommentRepository extends BaseRepository<PostComment,Integer> {

    /**
     * 根据博客id查找评论
     * @return
     */
    public List<PostComment> findByArticle(Article article);
}
