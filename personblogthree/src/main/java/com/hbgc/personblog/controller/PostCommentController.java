package com.hbgc.personblog.controller;

import com.hbgc.personblog.domain.PostComment;
import com.hbgc.personblog.json.Json;
import com.hbgc.personblog.service.PostCommentService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("postComment")
public class PostCommentController {

    @Resource
    private PostCommentService postCommentService;

    /**
     * 保存评论
     * @return
     */
    @PostMapping("save")
    public Map<String,Object> saveComment(@Param("bid") int bid,@Param("uid") int uid,@Param("htmlContent") String htmlContent){
//        System.out.println(bid +"=="+ uid + "==" + htmlContent);
        List<PostComment> postCommentList=null;
        try {
            postCommentService.save(bid,uid,htmlContent);
            postCommentList = postCommentService.findByBlogId(bid);

            if(postCommentList==null){
                return Json.fail("保存失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("保存失败");
        }
        return Json.success(postCommentList,"保存成功");
    }

    /**
     * 获取全部评论
     * @param bid
     * @return
     */
    @GetMapping("findAllComments")
    public Map<String,Object> findAllCommentsByBid(@Param("bid") int bid){
        List<PostComment> postCommentList=null;
        try {
            postCommentList = postCommentService.findByBlogId(bid);

            if(postCommentList==null){
                return Json.fail("请求评论失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Json.fail("请求评论失败");
        }
        return Json.success(postCommentList,"请求评论成功");
    }
}
