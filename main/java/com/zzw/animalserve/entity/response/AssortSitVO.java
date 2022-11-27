package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.AssortSit;
import com.zzw.animalserve.entity.Chordata;
import com.zzw.animalserve.utils.TimeFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/11/4__0:16
 */
@Data
public class AssortSitVO {

    private Long id;

    private String name;

    private Integer delectTag;

    private String createdAt;

    private String updatedAt;

    private List<ChordataVO> chordataList;

    public static AssortSitVO entityToVO(AssortSit assortSit){
        AssortSitVO assortSitVO = new AssortSitVO();
        assortSitVO.setId(assortSit.getAssortSitId());
        assortSitVO.setName(assortSit.getAssortSitName());
        assortSitVO.setDelectTag(assortSit.getDelectTag());
        assortSitVO.setCreatedAt(TimeFormat.formatTime(assortSit.getCreateTime()));
        assortSitVO.setUpdatedAt(TimeFormat.formatTime(assortSit.getUpdateTime()));
        List<Chordata> chordataList = assortSit.getChordataList();
        List<ChordataVO> chordataVOList = new ArrayList<>();
        for (Chordata temp:chordataList) {
            ChordataVO chordataVO = ChordataVO.entityToVO(temp);
            chordataVOList.add(chordataVO);
        }
        assortSitVO.setChordataList(chordataVOList);
        return assortSitVO;
    }
}
