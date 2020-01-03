package com.hbgc.personblog.controller;

import com.hbgc.personblog.domain.Article;
import com.hbgc.personblog.json.Json;
import com.hbgc.personblog.service.ArticleService;
import com.hbgc.personblog.service.UsersService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private UsersService usersService;

    /**
     * 根据用户名查找
     * @param username
     * @return
     */
    @GetMapping("/")
    public Map<String, Object> findByUsername(@Param("username") String username){
        System.out.println("接收到用户名："+username);

        List<Article> articleList =null;
        try {
            articleList  = articleService.findByUsername(username);
            if(articleList==null){
                return Json.fail("查询失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("查询失败");
        }
        return Json.success(articleList,"查询成功");
    }

    /**
     * 查询所有文章
     * @return
     */
    @GetMapping("allArticle")
    public Map<String,Object> findAllArticle(){
        List<Article> articleList =null;
        try {
            articleList = articleService.findAll();
            if(articleList==null){
                return Json.fail("查询失败");
            }
        } catch (Exception e){
            e.printStackTrace();
            return Json.fail("查询失败");
        }
        return Json.success(articleList,"查询成功");
    }

    /**
     * 发布文章
     */
    @PostMapping("publish")
    public Map<String,Object> publishArticle(@Param("username") String username,@Param("title") String title,@Param("mdContent") String mdContent,@Param("htmlContent") String htmlContent,@Param("image") String image){
        try {
            articleService.saveArticle(username,title,mdContent,htmlContent,image);
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("保存失败！");
        }
        return Json.success("保存成功！");
    }

    /**
     * 根据id查找
     */
    @GetMapping("findArticle")
    public Map<String,Object> findByBid(@Param("bid") int bid){
        Article article =null;
        try {
            article  = articleService.getOne(bid);
            if(article==null){
                return Json.fail("查询失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("查询失败");
        }
        return Json.success(article,"查询成功");
    }

    /**
     * 根据id获取所属用户，根据用户id和获取所有文章
     */
    @GetMapping("findAllUserArticleByBid")
    public Map<String,Object> findAllUserArticleByBid(@Param("bid") int bid){
        List<Article> articleList =null;
        try {
            articleList  = articleService.findAllUserArticleByBid(bid);
            if(articleList==null){
                return Json.fail("查询失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("查询失败");
        }
        return Json.success(articleList,"查询成功");
    }

    /**
     * 文章点赞
     * @param bid
     * @return
     */
    @PatchMapping("updateLikeNumber")
    public Map<String,Object> updateLikeNumber(@Param("bid") int bid){
        Article article =null;
        try {
            article  = articleService.updateLikeNumber(bid);
            if(article==null){
                return Json.fail("查询失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("查询失败");
        }
        return Json.success(article,"点赞成功");
    }

    /**
     * 模糊查找文章
     * @param value
     * @return
     */
    @GetMapping("vagueArticle")
    public Map<String,Object> findVagueArticle(@Param("value") String value){
        System.out.println("value:"+value);
        List<Article> articleList =null;
        try {
            articleList = articleService.findArticlesByTitleContains(value);
            if(articleList==null){
                return Json.fail("查询失败");
            }
        } catch (Exception e){
            e.printStackTrace();
            return Json.fail("查询失败");
        }
        return Json.success(articleList,"查询成功");
    }
}
