package com.hbgc.personblog.service.impl;

import com.hbgc.personblog.domain.Article;
import com.hbgc.personblog.domain.PostComment;
import com.hbgc.personblog.domain.Users;
import com.hbgc.personblog.repository.PostCommentRepository;
import com.hbgc.personblog.service.ArticleService;
import com.hbgc.personblog.service.PostCommentService;
import com.hbgc.personblog.service.UsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class PostCommentServiceImpl extends BaseServiceImpl<PostComment,Integer, PostCommentRepository> implements PostCommentService {

    @Resource
    private ArticleService articleService;

    @Resource
    private UsersService usersService;
    /**
     * 保存评论
     */
    public void save(int bid,int uid,String htmlContent){
        PostComment postComment = new PostComment();

        //1.更新文章回复数量
        Article article =null;
        article = articleService.getOne(bid);
        article.setReplyNumber(article.getReplyNumber()+1);
        articleService.update(article);
        article = articleService.getOne(bid);

        Users users = usersService.getOne(uid);

        postComment.setArticle(article);
        postComment.setUsers(users);
        postComment.setHtmlContent(htmlContent);

        super.save(postComment);
    }


    /**
     * 根据博客id查找评论
     * @param blogId
     * @return
     */
    @Override
    public List<PostComment> findByBlogId(int blogId) {
        //1.根据博客id查询博客
        Article article = articleService.getOne(blogId);
        //2.返回根据博客查找的评论
        List<PostComment> postCommentList = super.dao.findByArticle(article);
//        Collections.reverse(postCommentList);
        return postCommentList;
    }
}
