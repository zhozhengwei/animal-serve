package com.zzw.animalserve.service.impl;

import com.zzw.animalserve.entity.Comment;
import com.zzw.animalserve.mapper.CommentMapper;
import com.zzw.animalserve.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

@Autowired
private CommentMapper commentMapper;

/**
*  查询表comment所有信息
*/
@Override
public List<Comment> findAllComment() { return commentMapper.findAllComment();}

        /**
        *  根据主键commentId查询表comment信息
        *  @param commentId
        */
        @Override
        public Comment findCommentBycommentId(@Param("commentId") Long commentId) { return commentMapper.findCommentBycommentId(commentId);}

/**
*  根据条件查询表comment信息
*  @param comment
*/
@Override
public List<Comment> findCommentByCondition(Comment comment) { return commentMapper.findCommentByCondition(comment);}

        /**
        *  根据主键commentId查询表comment信息
        *  @param commentId
        */
        @Override
        public Integer deleteCommentBycommentId(@Param("commentId") Long commentId) { return commentMapper.deleteCommentBycommentId(commentId);}

        /**
        *  根据主键commentId更新表comment信息
        *  @param comment
        */
        @Override
        public Integer updateCommentBycommentId(Comment comment) { return commentMapper.updateCommentBycommentId(comment);}

        /**
        *  新增表comment信息
        *  @param comment
        */
        @Override
        public Integer addComment(Comment comment) { return commentMapper.addComment(comment);}

}