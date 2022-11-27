package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.Comment;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {



    @Options(useGeneratedKeys = true, keyProperty = "commentId", keyColumn = "Comment_id")
    @Insert("insert into comment(Comment_id,Comment_content, Member_id, Parent_cmt_id, create_id, create_time, update_id, update_time,delect_tag) values(#{commentId},#{commentContent}, #{memberId}, #{parentCmtId}, #{createId},now(), #{updateId},now(),#{delectTag}))")
    Integer insertComment(Comment comment);

    /**
     * 查询表comment所有信息
     */
    List<Comment> findAllComment();

    /**
     * 根据主键commentId查询表comment信息
     *
     * @param commentId
     */
    Comment findCommentBycommentId(@Param("commentId") Long commentId);

    List<Comment> findCommentByParentCommentId(@Param("parentCmtId") Long parentCmtId);

    /**
     * 根据条件查询表comment信息
     *
     * @param comment
     */
    List<Comment> findCommentByCondition(Comment comment);

    /**
     * 根据主键commentId查询表comment信息
     *
     * @param commentId
     */
    Integer deleteCommentBycommentId(@Param("commentId") Long commentId);

    /**
     * 根据主键commentId更新表comment信息
     *
     * @param comment
     */
    Integer updateCommentBycommentId(Comment comment);

    /**
     * 新增表comment信息
     *
     * @param comment
     */
    Integer addComment(Comment comment);

}