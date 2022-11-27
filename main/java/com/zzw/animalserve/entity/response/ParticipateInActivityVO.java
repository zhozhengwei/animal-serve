package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.ParticipateInActivity;
import com.zzw.animalserve.utils.TimeFormat;
import lombok.Data;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/11/2__2:10
 */
@Data
public class ParticipateInActivityVO {

    private Long id;

    private String name;

    private String email;

    private Integer status;

    private Long uid;

    private String createdAt;

    private String updatedAt;

    private Integer delectTag;

    private Long acid;

    private String title;

    private String cover;

    private String introduction;

    private String start;

    private String end;

    public static ParticipateInActivityVO entityToVO(ParticipateInActivity participateInActivity){
        ParticipateInActivityVO participateInActivityVO = new ParticipateInActivityVO();
        participateInActivityVO.setId(participateInActivity.getParticipateInActivityId());
        participateInActivityVO.setName(participateInActivity.getParticipateInActivityName());
        participateInActivityVO.setEmail(participateInActivity.getParticipateInActivityEmail());
        participateInActivityVO.setStatus(participateInActivity.getParticipateInActivityStatus());
        participateInActivityVO.setUid(participateInActivity.getCreateId());
        participateInActivityVO.setCreatedAt(TimeFormat.formatTime(participateInActivity.getCreateTime()));
        participateInActivityVO.setUpdatedAt(TimeFormat.formatTime(participateInActivity.getUpdateTime()));
        participateInActivityVO.setDelectTag(participateInActivity.getDelectTag());
        participateInActivityVO.setAcid(participateInActivity.getActivityId());
        participateInActivityVO.setTitle(participateInActivity.getActivity().getActivityTitle());
        participateInActivityVO.setCover(participateInActivity.getActivity().getActivityImage());
        participateInActivityVO.setIntroduction(participateInActivity.getActivity().getActivityIntroduction());
        participateInActivityVO.setStart(TimeFormat.formatTime(participateInActivity.getActivity().getStartTime()));
        participateInActivityVO.setEnd(TimeFormat.formatTime(participateInActivity.getActivity().getEndTime()));
        return participateInActivityVO;
    }
}
