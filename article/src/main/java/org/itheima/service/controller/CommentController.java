package org.itheima.service.controller;

import org.itheima.po.Comment;
import org.itheima.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class CommentController {
    //注入Service
    @Autowired
    private CommentService commentService;

    /**
     * 测试接口，返回欢迎信息
     * @return 欢迎信息字符串
     */
    @RequestMapping("/hello")
    public String say(){
        return "Hello SpringBoot!";
    }

    /**
     * 获取所有评论
     * @return 评论列表
     */
    @RequestMapping("/listall")
    public List<Comment> testFindAll(){
        List<Comment> list = commentService.findCommentList();
        return list;
    }

    /**
     * 新增评论
     * @param articleid 文章ID
     * @param content 评论内容
     */
    @RequestMapping("/new")
    public void testSaveComment(String articleid,String content){
        Comment comment=new Comment();
        comment.setArticleid(articleid);
        comment.setContent("评论内容");
        comment.setCreatedatetime(LocalDateTime.now());
        comment.setUserid("1001");
        comment.setNickname("刘一山");
        comment.setParentid("1001");
        comment.setState("1");
        comment.setLikenum(0);
        comment.setReplynum(0);
        commentService.saveComment(comment);
        System.out.println("写入数据成功");
    }

    /**
     * 分页获取指定父ID的评论
     * @param parentid 父评论ID
     * @param page 页码
     * @param size 每页大小
     * @return 分页结果字符串
     */
    @RequestMapping("/page")
    public String testFindCommentListPageByParentid(String parentid,int page,int size ){
        Page<Comment> pageResponse = commentService.findCommentListPageByParentid(parentid,page,size);
        String result="----总记录数："+pageResponse.getTotalElements()+"----当前页数据："+pageResponse.getContent();
        return result;
    }

    /**
     * 更新评论内容
     * @param id 评论ID
     * @return 更新结果字符串
     */
    @RequestMapping("/update")
    public String updateComment(String id){
        Comment comment=commentService.findCommentById(id);
        comment.setContent("更新后的评论内容");
        comment.setParentid("1003");
        commentService.updateComment(comment);
        return "更新成功";
    }

    /**
     * 删除评论
     * @param id 评论ID
     * @return 删除结果字符串
     */
    @RequestMapping("/delete")
    public String testDeleteCommentById(String id){
        commentService.deleteCommentById(id);
        return "删除成功";
    }

    /**
     * 根据ID获取评论
     * @param id 评论ID
     * @return ResponseEntity对象，包含HTTP状态和评论对象
     */
    @RequestMapping("/findbyid")
    public ResponseEntity<Comment> getCommentById(String id) {
        // 调用 Service 层方法
        Comment comment = commentService.findCommentById(id);

        // 判断是否找到评论
        if (comment != null) {
            return ResponseEntity.ok(comment); // 返回 200 和评论对象
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 返回 404 状态
        }
    }

    /**
     * 点赞评论
     * @param id 评论ID
     * @return ResponseEntity对象，包含HTTP状态和点赞结果消息
     */
    @RequestMapping("/likeComment")
    public ResponseEntity<?> likeComment(@RequestParam String id) {
        try {
            // 调用Service方法更新点赞数
            commentService.Likenum(id);
            // 返回成功响应
            return ResponseEntity.ok("点赞成功");
        } catch (Exception e) {
            // 处理异常情况
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("点赞失败: " + e.getMessage());
        }
    }

    /**
     * 更新评论的点赞数
     * @param id 评论ID
     * @return 更新结果字符串
     */
    @RequestMapping("/updateLikenum")
    public String updateCommentLikenum(String id) {
        // 调用Service方法
        commentService.updateCommentLikenum(id);
        return "点赞数更新成功";
    }
}
