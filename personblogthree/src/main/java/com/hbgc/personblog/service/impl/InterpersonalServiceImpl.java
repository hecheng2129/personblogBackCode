package com.hbgc.personblog.service.impl;

import com.hbgc.personblog.domain.Article;
import com.hbgc.personblog.domain.Interpersonal;
import com.hbgc.personblog.domain.Users;
import com.hbgc.personblog.repository.InterpersonalRepository;
import com.hbgc.personblog.service.ArticleService;
import com.hbgc.personblog.service.InterpersonalService;
import com.hbgc.personblog.service.UsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InterpersonalServiceImpl extends BaseServiceImpl<Interpersonal,Integer, InterpersonalRepository> implements InterpersonalService {

    @Resource
    private ArticleService articleService;

    @Resource
    private UsersService usersService;

    /**
     *根据楼层查找
     * @return
     */
    @Override
    public List<Interpersonal> findByFloorAndBid(int floor, int bid) {
        Article article = articleService.getOne(bid);
        List<Interpersonal> interpersonalList = super.dao.findByFloorAndArticle(floor,article);
        return interpersonalList;
    }

    /**
     * 保存评论
     * @param bid       哪篇文章发表
     * @param floor     哪层楼
     * @param users1id  对哪个用户说的
     * @param users2id  谁说的
     * @param htmlContent 说的啥
     * @return
     */
    @Override
    public List<Interpersonal> save(int bid, int floor, int users1id, int users2id, String htmlContent) {

        Interpersonal interpersonal = new Interpersonal();

        Article article = articleService.getOne(bid);
        interpersonal.setArticle(article);

        interpersonal.setFloor(floor);

        Users users1 = usersService.getOne(users1id);
        interpersonal.setUsers1(users1);

        Users users2 = usersService.getOne(users2id);
        interpersonal.setUsers2(users2);

        interpersonal.setHtmlContent(htmlContent);

        //保存
        super.save(interpersonal);

        //查找返回
        return findByFloorAndBid(floor,bid);
    }
}
