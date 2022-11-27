package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.ApplyVolunteer;
import com.zzw.animalserve.utils.TimeFormat;
import lombok.Data;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/11/2__1:02
 */
@Data
public class ApplyVolunteerVO {
    private Long id;

    private String name;

    private String email;

    private String address;

    private String introduction;

    private String SkillIntroduction;

    private Integer status;

    private Long uid;

    private String username;

    private String createdAt;

    private String updatedAt;

    private Integer delectTag;

    public static ApplyVolunteerVO entityToVO(ApplyVolunteer applyVolunteer){
        ApplyVolunteerVO applyVolunteerVO = new ApplyVolunteerVO();
        applyVolunteerVO.setId(applyVolunteer.getApplyVolunteerId());
        applyVolunteerVO.setName(applyVolunteer.getApplyVolunteerName());
        applyVolunteerVO.setEmail(applyVolunteer.getApplyVolunteerEmail());
        applyVolunteerVO.setAddress(applyVolunteer.getApplyVolunteerAddress());
        applyVolunteerVO.setIntroduction(applyVolunteer.getApplyVolunteerIntroduction());
        applyVolunteerVO.setSkillIntroduction(applyVolunteer.getApplyVolunteerSkillIntroduction());
        applyVolunteerVO.setStatus(applyVolunteer.getApplyVolunteerStatus());
        applyVolunteerVO.setUid(applyVolunteer.getCreateId());
        applyVolunteerVO.setUsername(applyVolunteer.getMember().getMemberName());
        applyVolunteerVO.setCreatedAt(TimeFormat.formatTime(applyVolunteer.getCreateTime()));
        applyVolunteerVO.setUpdatedAt(TimeFormat.formatTime(applyVolunteer.getUpdateTime()));
        applyVolunteerVO.setDelectTag(applyVolunteer.getDelectTag());
        return applyVolunteerVO;
    }
}
