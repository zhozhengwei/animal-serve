package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.Message;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/22__14:59
 */

@ApiModel(value = "发送消息DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    @ApiModelProperty(value= "ID")
    private Long id;

    @ApiModelProperty(value= "消息发送人的姓名")
    private String name;

    @ApiModelProperty(value= "消息发送人的邮箱")
    private String email;

    @ApiModelProperty(value= "消息发送人的主体消息")
    private String content;

    @ApiModelProperty(value= "消息发送人的类型")
    private Integer status;

    @ApiModelProperty(value= "uid")
    private Long uid;

    public Message toEntity() {
        Message toEntity = new Message();
        toEntity.setMessageId(id);
        toEntity.setMessageName(name);
        toEntity.setMessageEmail(email);
        toEntity.setMessageText(content);
        toEntity.setMessageStatus(status);
        toEntity.setCreateId(uid);
        return toEntity;
    }

}
