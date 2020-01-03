package com.hbgc.personblog.service;

import com.hbgc.personblog.domain.Interpersonal;
import com.hbgc.personblog.repository.InterpersonalRepository;

import java.util.List;

public interface InterpersonalService extends BaseService<Interpersonal,Integer, InterpersonalRepository> {

    /**
     *根据楼层查找
     * @return
     */
    public List<Interpersonal> findByFloorAndBid(int floor, int bid);

    /**
     * 保存评论
     * @param bid       哪篇文章发表
     * @param floor     哪层楼
     * @param users1id  对哪个用户说的
     * @param users2id  谁说的
     * @param htmlContent 说的啥
     * @return
     */
    public List<Interpersonal> save(int bid, int floor, int users1id, int users2id, String htmlContent);
}
