package com.hbgc.personblog.repository;

import com.hbgc.personblog.domain.Article;
import com.hbgc.personblog.domain.Interpersonal;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterpersonalRepository extends BaseRepository<Interpersonal,Integer>{

    /**
     *根据楼层和文章查找
     * @return
     */
    public List<Interpersonal> findByFloorAndArticle(int floor, Article article);
}
