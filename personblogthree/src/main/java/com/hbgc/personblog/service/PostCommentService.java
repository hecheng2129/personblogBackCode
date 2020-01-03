package com.hbgc.personblog.service;

import com.hbgc.personblog.domain.PostComment;
import com.hbgc.personblog.repository.PostCommentRepository;

import java.util.List;

public interface PostCommentService extends BaseService<PostComment,Integer, PostCommentRepository>{

    /**
     * 保存评论
     * @param bid
     * @param uid
     * @param htmlContent
     */
    public void save(int bid, int uid, String htmlContent);


    /**
     * 根据博客id查找评论
     * @return
     */
    public List<PostComment> findByBlogId(int blogId);
}
