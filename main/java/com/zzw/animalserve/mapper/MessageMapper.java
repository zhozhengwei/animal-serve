package com.zzw.animalserve.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzw.animalserve.entity.Message;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MessageMapper extends BaseMapper<Message> {

    /**
     * 查询表message所有信息
     */
    List<Message> findAllMessage();

    /**
     * 根据主键messageId查询表message信息
     *
     * @param messageId
     */
    Message findMessageBymessageId(@Param("messageId") Long messageId);


    Message findMessageByparentId(@Param("parentId") Long parentId);
    /**
     * 根据条件查询表message信息
     *
     * @param message
     */
    List<Message> findMessageByCondition(Message message);

    /**
     * 根据主键messageId查询表message信息
     *
     * @param messageId
     */
    Integer deleteMessageBymessageId(@Param("messageId") Long messageId);

    /**
     * 根据主键messageId更新表message信息
     *
     * @param message
     */
    Integer updateMessageBymessageId(Message message);

    /**
     * 新增表message信息
     *
     * @param message
     */
    Integer addMessage(Message message);

}