package com.zzw.animalserve.service;

import com.zzw.animalserve.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentService{

/**
*  查询表comment所有信息
*/
List<Comment> findAllComment();

        /**
        *  根据主键commentId查询表comment信息
        *  @param commentId
        */
        Comment findCommentBycommentId(@Param("commentId") Long commentId);

/**
*  根据条件查询表comment信息
*  @param comment
*/
List<Comment> findCommentByCondition(Comment comment);

        /**
        *  根据主键commentId查询表comment信息
        *  @param commentId
        */
        Integer deleteCommentBycommentId(@Param("commentId") Long commentId);

        /**
        *  根据主键commentId更新表comment信息
        *  @param comment
        */
        Integer updateCommentBycommentId(Comment comment);

        /**
        *  新增表comment信息
        *  @param comment
        */
        Integer addComment(Comment comment);
}