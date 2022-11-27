package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.Message;
import com.zzw.animalserve.utils.TimeFormat;
import lombok.Data;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/27__11:10
 */
@Data
public class MessageVO {

    private Long mid;

    private String name;

    private String email;

    private String content;

    private Integer status;


    private String createdAt;

    private String updatedAt;

    private Integer delete;

    //有回答，返回回答

    private Long pid;

    private String pname;

    private String pcontent;

    private String pemail;

    private String pcreatedAt;

    private String pupdatedAt;

    //消息

    private String username;

    public static MessageVO entityToVO(Message message){
        MessageVO messageVO = new MessageVO();
        messageVO.setMid(message.getMessageId());
        messageVO.setName(message.getMessageName());
        messageVO.setEmail(message.getMessageEmail());
        messageVO.setContent(message.getMessageText());
        messageVO.setStatus(message.getMessageStatus());
        messageVO.setCreatedAt(TimeFormat.formatTime(message.getCreateTime()));
        messageVO.setUpdatedAt(TimeFormat.formatTime(message.getUpdateTime()));
        messageVO.setDelete(message.getDelectTag());
        //回答信息
//        messageVO.setPid(message.getParentId());
//        messageVO.setPname(message.getMessage().getMessageName());
//        messageVO.setPemail(message.getMessage().getMessageEmail());
//        messageVO.setPcontent(message.getMessage().getMessageText());
//        messageVO.setPcreatedAt(TimeFormat.formatTime(message.getMessage().getCreateTime()));
//        messageVO.setPupdatedAt(TimeFormat.formatTime(message.getMessage().getUpdateTime()));
        //用户信息
        messageVO.setUsername(message.getMember().getMemberName());
        return messageVO;
    }




}
