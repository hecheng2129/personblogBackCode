package com.hbgc.personblog.controller;

import com.hbgc.personblog.domain.Interpersonal;
import com.hbgc.personblog.json.Json;
import com.hbgc.personblog.service.InterpersonalService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("interpersonal")
public class InterpersonalController {

    @Resource
    private InterpersonalService interpersonalService;

    @GetMapping("findByFloorAndBid")
    public Map<String,Object> findByfloor(@Param("floor") int floor,@Param("bid") int bid){
        System.out.println("请求楼层："+floor);
        List<Interpersonal> interpersonalList =null;
        try {
            interpersonalList  = interpersonalService.findByFloorAndBid(floor,bid);
            System.out.println("成功信息："+interpersonalList);
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("查询失败");
        }
        return Json.success(interpersonalList,"查询成功");
    }

    /**
     * 查询所有评论
     * @return
     */
    @GetMapping("findAll")
    public Map<String,Object> findAll(){
        List<Interpersonal> interpersonalList =null;
        try {
            interpersonalList  = interpersonalService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("查询失败");
        }
        return Json.success(interpersonalList,"查询成功");
    }


    @PostMapping("save")
    public Map<String,Object> save(@Param("bid") int bid,@Param("floor") int floor,@Param("users1id") int users1id,@Param("users2id") int users2id,@Param("htmlContent") String htmlContent){
        List<Interpersonal> interpersonalList =null;
        try {
            interpersonalList  = interpersonalService.save(bid,floor,users1id,users2id,htmlContent);
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("查询失败");
        }
        return Json.success(interpersonalList,"查询成功");
    }
}
