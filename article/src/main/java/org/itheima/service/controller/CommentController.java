package org.itheima.service.controller;

import org.itheima.po.Comment;
import org.itheima.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class CommentController {
    //注入Service
    @Autowired
    private CommentService commentService;
    @RequestMapping("/hello")
    public String say(){
        return "Hello SpringBoot!";
    }
    @RequestMapping("/listall")
    public List<Comment> testFindAll(){
        List<Comment> list = commentService.findCommentList();
        return list;
    }
    @RequestMapping("/new")
    public void testSaveComment(String articleid,String content){
        Comment comment=new Comment();
        comment.setArticleid(articleid);
        comment.setContent(content);
        comment.setCreatedatetime(LocalDateTime.now());
        comment.setUserid("1003");
        comment.setNickname("凯撒大帝");
        comment.setParentid("1003");
        comment.setState("1");
        comment.setLikenum(0);
        comment.setReplynum(0);
        commentService.saveComment(comment);
        System.out.println("写入数据成功");
    }
    @RequestMapping("/page")
    public String testFindCommentListPageByParentid(String parentid,int page,int size ){
        Page<Comment> pageResponse = commentService.findCommentListPageByParentid(parentid,page,size);
        String result="----总记录数："+pageResponse.getTotalElements()+"----当前页数据："+pageResponse.getContent();
        return result;
    }

    // 点赞评论
    @RequestMapping("/likeComment")
    public ResponseEntity<?> likeComment(@RequestParam String id) {
        try {
            // 调用Service方法更新点赞数
            commentService.updateCommentLikenum(id);
            // 返回成功响应
            return ResponseEntity.ok("点赞成功");
        } catch (Exception e) {
            // 处理异常情况
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("点赞失败: " + e.getMessage());
        }
    }
    @RequestMapping("/updateLikenum")
    public String updateCommentLikenum(String id) {
        // 调用Service方法
        commentService.updateCommentLikenum(id);
        return "点赞数更新成功";
    }
}