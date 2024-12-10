package org.itheima.dao;

import org.itheima.po.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * CommentRepository接口继承自MongoRepository，专门用于处理Comment实体的CRUD操作
 * 该接口由Spring Data MongoDB提供支持，用于执行针对MongoDB数据库的操作
 */
public interface CommentRepository extends MongoRepository<Comment,String> {
    /**
     * 根据父评论ID查找评论分页查询
     *
     * @param parentid 父评论的唯一标识符
     * @param pageable 分页信息，包括当前页码和每页大小
     * @return 返回一个Page对象，包含查询到的评论列表和分页信息
     */
    Page<Comment> findByParentid(String parentid, Pageable pageable);
}

