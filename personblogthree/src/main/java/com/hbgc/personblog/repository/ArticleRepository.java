package com.hbgc.personblog.repository;

import com.hbgc.personblog.domain.Article;
import com.hbgc.personblog.domain.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends BaseRepository<Article,Integer>{

    /**
     * 根据用户查询文章
     * @return
     */
    public List<Article> findByUsers(Users users);


    /**
     * 根据文章标题模糊查找
     * @param title
     * @return
     */
    public List<Article> findArticlesByTitleContains(String title);
}
