package com.hbgc.personblog.service;

import com.hbgc.personblog.domain.Article;
import com.hbgc.personblog.repository.ArticleRepository;

import java.util.List;

public interface ArticleService extends BaseService<Article,Integer, ArticleRepository> {
    /**
     * 根据用户查询文章
     * @return
     */
    public List<Article> findByUsername(String username);


    /**
     * 根据id获取所属用户，根据用户id和获取所有文章
     * @param bid
     * @return
     */
    public List<Article> findAllUserArticleByBid(int bid);

    /**
     * 保存文章
     * @param username
     * @param title
     * @param mdContent
     * @param htmlContent
     */
    public void saveArticle(String username, String title, String mdContent, String htmlContent, String image);

    /**
     * 更新点赞
     * @param uid
     * @return
     */
    public Article updateLikeNumber(int uid);


    /**
     * 根据文章标题模糊查找
     * @param title
     * @return
     */
    public List<Article> findArticlesByTitleContains(String title);
}
