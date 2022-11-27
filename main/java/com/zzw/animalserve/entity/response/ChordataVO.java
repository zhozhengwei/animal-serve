package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.Chordata;
import com.zzw.animalserve.entity.Mammalia;
import com.zzw.animalserve.utils.TimeFormat;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/27__22:44
 */
@Data
public class ChordataVO {

    private Long id;

    private String name;

    private String createdAt;

    private String updatedAt;

    private Integer delectTag;

    private Long aid;

    private String aname;

//    private List<MammaliaVO> mammaliaList;

    public static ChordataVO entityToVO(Chordata chordata){
        ChordataVO chordataVO = new ChordataVO();
        chordataVO.setId(chordata.getChordataId());
        chordataVO.setName(chordata.getChordataName());
        chordataVO.setCreatedAt(TimeFormat.formatTime(chordata.getCreateTime()));
        chordataVO.setUpdatedAt(TimeFormat.formatTime(chordata.getUpdateTime()));
        chordataVO.setDelectTag(chordata.getDelectTag());
        chordataVO.setAid(chordata.getAnimaliaId());
        chordataVO.setAname(chordata.getAnimalia().getAnimaliaName());
        //纲列表
//        List<Mammalia> mammaliaList = chordata.getMammaliaList();
//        List<MammaliaVO> mammaliaVOList = new ArrayList<>();
//        for (Mammalia temp :mammaliaList) {
//            MammaliaVO mammaliaVO = MammaliaVO.entityToVO(temp);
//            mammaliaVOList.add(mammaliaVO);
//        }
//        chordataVO.setMammaliaList(mammaliaVOList);
        return chordataVO;
    }

}
