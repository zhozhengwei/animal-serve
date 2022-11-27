package com.zzw.animalserve.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzw.animalserve.common.BaseController;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ErrorCode;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.Message;
import com.zzw.animalserve.entity.dto.MessageDto;
import com.zzw.animalserve.entity.response.MessageVO;
import com.zzw.animalserve.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzw.animalserve.service.MessageService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags = "消息信息")
@RestController
@RequestMapping("/message")
public class MessageController extends BaseController {

    @Autowired
    private MessageService messageService;


    /**
     * 分页查询
     * @param name
     * @return
     */
    @ApiOperation("消息列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('sys:message:lists')")
    public BaseResponse<List<MessageVO>> queryByPage(String name) {
        if(StringUtils.isAnyBlank(name)){
            List<Message> allMessage = messageService.findAllMessage();
            List<MessageVO> messageVOList = new ArrayList<>();
            for (Message temp : allMessage) {
                if(temp.getDelectTag() == 0){
                    MessageVO messageVO = MessageVO.entityToVO(temp);
                    messageVOList.add(messageVO);
                }
            }
            return ResultUtils.success(messageVOList);
        }else{
            Message message = new Message();
            message.setMessageName(name);
            List<Message> messageByCondition = messageService.findMessageByCondition(message);
            List<MessageVO> messageVOList = new ArrayList<>();
            for (Message temp : messageByCondition) {
                if(temp.getDelectTag() == 0){
                    MessageVO messageVO = MessageVO.entityToVO(temp);
                    messageVOList.add(messageVO);
                }
            }
            return ResultUtils.success(messageVOList);
        }
    }

    @ApiOperation("条件查询")
    @PostMapping("/listSearch")
//    @PreAuthorize("hasAuthority('sys:message:lists')")
    public BaseResponse<List<MessageVO>> queryBySearch(@RequestBody MessageDto messageDto) {
        Message message = messageDto.toEntity();
        List<Message> messageByCondition = messageService.findMessageByCondition(message);
        List<MessageVO> messageVOList = new ArrayList<>();
        for (Message temp : messageByCondition) {
            if(temp.getDelectTag() == 0){
                MessageVO messageVO = MessageVO.entityToVO(temp);
                messageVOList.add(messageVO);
            }
        }
        return ResultUtils.success(messageVOList);
    }


    /**
     * 分页查询
     * @param
     * @return
     */
    @ApiOperation("有回答列表")
    @GetMapping("/listAnswer")
//    @PreAuthorize("hasAuthority('sys:message:lists')")
    public BaseResponse<List<MessageVO>> queryByList() {
        List<Message> allMessage = messageService.findAllMessage();
        List<MessageVO> messageVOList = new ArrayList<>();
        for (Message message:allMessage) {
            if(message.getParentId() != null && message.getDelectTag() == 0){
                MessageVO messageVO = MessageVO.entityToVO(message);
                messageVOList.add(messageVO);
            }
        }
        return ResultUtils.success(messageVOList);
    }

    // Page<RoleVO> roles = roleService.page(getPage(), new QueryWrapper<Role>().like(StrUtil.isNotBlank(name), "name", name));

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("单条消息")
    @GetMapping("/searchId/{id}")
//    @PreAuthorize("hasAuthority('sys:message:sigln')")
    public BaseResponse<Message> queryById(@PathVariable("id") Long id) {
        return ResultUtils.success(this.messageService.findMessageBymessageId(id));
    }

    /**
     * 新增数据
     *
     * @param messageDto 实体
     * @return 新增结果
     */
    @ApiOperation("新增消息")
    @PostMapping("/save")
    public BaseResponse<Integer> add(@RequestBody MessageDto messageDto) {
        System.out.println("===========>"+messageDto.toString());
        if(StringUtils.isAllBlank(messageDto.getName(),messageDto.getContent(), messageDto.getEmail())){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        Message message = messageDto.toEntity();
        message.setCreateTime(new Date());
        message.setUpdateTime(new Date());
        message.setDelectTag(0);
        System.out.println("===========>"+message.toString());
        return ResultUtils.success(this.messageService.addMessage(message));
    }

    /**
     * 编辑数据
     *
     * @param messageDto 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑消息")
    @PutMapping("/update")
//    @PreAuthorize("hasAuthority('sys:message:update')")
    public BaseResponse<Integer> edit(@RequestBody MessageDto messageDto) {
        /**
         * 这里需要有一个用于管理员和超级管理员成功同意的接口就是一个特殊的修改接口
         * 0默认没有通过管理员同意，1正在取得联系审查中，2已经同意该用户可以注册账号了
         * 前端给的逻辑 修改 选择 “状态” 改为 0 | 1 | 2
         */
        Message message = messageDto.toEntity();
        message.setUpdateTime(new Date());
        return ResultUtils.success(this.messageService.updateMessageBymessageId(message));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除消息")
    @DeleteMapping("/delete")
//    @PreAuthorize("hasAuthority('sys:message:delete')")
    public BaseResponse<Integer> deleteById(Long id) {
        Message message = messageService.findMessageBymessageId(id);
        message.setDelectTag(1);
        return ResultUtils.success(this.messageService.updateMessageBymessageId(message));
    }

}