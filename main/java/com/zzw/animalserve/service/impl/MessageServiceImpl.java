package com.zzw.animalserve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzw.animalserve.entity.Message;
import com.zzw.animalserve.mapper.MessageMapper;
import com.zzw.animalserve.service.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Autowired(required = false)
    private MessageMapper messageMapper;

    /**
     * 查询表message所有信息
     */
    @Override
    public List<Message> findAllMessage() {
        return messageMapper.findAllMessage();
    }

    /**
     * 根据主键messageId查询表message信息
     *
     * @param messageId
     */
    @Override
    public Message findMessageBymessageId(@Param("messageId") Long messageId) {
        return messageMapper.findMessageBymessageId(messageId);
    }

    /**
     * 根据条件查询表message信息
     *
     * @param message
     */
    @Override
    public List<Message> findMessageByCondition(Message message) {
        return messageMapper.findMessageByCondition(message);
    }

    /**
     * 根据主键messageId查询表message信息
     *
     * @param messageId
     */
    @Override
    public Integer deleteMessageBymessageId(@Param("messageId") Long messageId) {
        return messageMapper.deleteMessageBymessageId(messageId);
    }

    /**
     * 根据主键messageId更新表message信息
     *
     * @param message
     */
    @Override
    public Integer updateMessageBymessageId(Message message) {
        return messageMapper.updateMessageBymessageId(message);
    }

    /**
     * 新增表message信息
     *
     * @param message
     */
    @Override
    public Integer addMessage(Message message) {
        return messageMapper.addMessage(message);
    }

}