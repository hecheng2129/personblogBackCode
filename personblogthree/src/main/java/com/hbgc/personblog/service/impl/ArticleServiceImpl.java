package com.hbgc.personblog.service.impl;

import com.hbgc.personblog.domain.Article;
import com.hbgc.personblog.domain.Users;
import com.hbgc.personblog.repository.ArticleRepository;
import com.hbgc.personblog.service.ArticleService;
import com.hbgc.personblog.service.UsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article,Integer, ArticleRepository> implements ArticleService {

    @Resource
    private UsersService usersService;

    @Resource
    private ArticleService articleService;

    /**
     * 根据用户名查找文章
     * @param username
     * @return
     */
    @Override
    public List<Article> findByUsername(String username) {
        //1.根据用户查找用户信息
        Users user = usersService.findByUsername(username);
        //2.返回根据用户查找的文章集合
        List<Article> articleList = super.dao.findByUsers(user);
        //3.逆序
        Collections.reverse(articleList);

        return articleList;
    }


    /**
     * 根据id获取所属用户，根据用户id和获取所有文章
     * @param bid
     * @return
     */
    @Override
    public List<Article> findAllUserArticleByBid(int bid) {
        Article article =null;
        //1.根据id获取文章
        article  = super.getOne(bid);
        //2.更新浏览数量
        article.setReadNumber(article.getReadNumber()+1);
        super.update(article);
        //3.再次查询
        article  = super.getOne(bid);
        //4.根据文章获取用户名
        String username = article.getUsers().getUsername();
        //5.调用根据用户名查找用户所有文章的集合
        return findByUsername(username);
    }

    @Override
    public void saveArticle(String username, String title, String mdContent, String htmlContent,String image) {

        //1.根据用户查找用户信息
        Users user = usersService.findByUsername(username);

        //2.保存文章
        Article article = new Article();
        article.setTitle(title);
        article.setMdContent(mdContent);
        article.setHtmlContent(htmlContent);
        article.setUsers(user);
        article.setImage(image);

        super.save(article);
    }

    @Override
    public Article updateLikeNumber(int bid) {

        Article article=null;

        //1.查询
        article = super.getOne(bid);

        //2.保存更新
        article.setLikeNumber(article.getLikeNumber()+1);
        super.update(article);

        article = super.getOne(bid);

        return article;
    }

    @Override
    public List<Article> findArticlesByTitleContains(String title) {
        return super.dao.findArticlesByTitleContains(title);
    }
}
