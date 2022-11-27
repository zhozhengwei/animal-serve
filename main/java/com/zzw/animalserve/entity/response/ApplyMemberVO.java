package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.ApplyMember;
import com.zzw.animalserve.utils.TimeFormat;
import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/11/2__0:42
 */
@Data
public class ApplyMemberVO {

    private Long id;

    private String name;

    private String email;

    private String address;

    private String phone;

    private String introduction;

    private Integer status;

    private Long uid;

    private String createdAt;

    private String updatedAt;

    private Integer delectTag;

    public static ApplyMemberVO entityToVO(ApplyMember applyMember){
        ApplyMemberVO applyMemberVO = new ApplyMemberVO();
        applyMemberVO.setId(applyMember.getApplyMemberId());
        applyMemberVO.setName(applyMember.getApplyMemberName());
        applyMemberVO.setEmail(applyMember.getApplyMemberEmail());
        applyMemberVO.setAddress(applyMember.getApplyMemberAddress());
        applyMemberVO.setPhone(applyMember.getApplyMemberPhone());
        applyMemberVO.setIntroduction(applyMember.getApplyMemberIntroduction());
        applyMemberVO.setStatus(applyMember.getApplyMemberStatus());
        applyMemberVO.setUid(applyMember.getUpdateId());
        applyMemberVO.setCreatedAt(TimeFormat.formatTime(applyMember.getCreateTime()));
        applyMemberVO.setUpdatedAt(TimeFormat.formatTime(applyMember.getUpdateTime()));
        applyMemberVO.setDelectTag(applyMember.getDelectTag());
        return applyMemberVO;
    }
}
