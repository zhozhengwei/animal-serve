package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.Animalia;
import com.zzw.animalserve.entity.Chordata;
import com.zzw.animalserve.utils.TimeFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/27__22:38
 */
@Data
public class AnimaliaVO {

    private Long id;

    private String name;

    private String createdAt;

    private String updatedAt;

    private Integer delectTag;

    //门的列表
//    private List<ChordataVO> chordataList;

    public static AnimaliaVO entityToVO(Animalia animalia){
        AnimaliaVO animaliaVo = new AnimaliaVO();
        animaliaVo.setId(animalia.getAnimaliaId());
        animaliaVo.setName(animalia.getAnimaliaName());
        animaliaVo.setCreatedAt(TimeFormat.formatTime(animalia.getCreateTime()));
        animaliaVo.setUpdatedAt(TimeFormat.formatTime(animalia.getUpdateTime()));
        animaliaVo.setDelectTag(animalia.getDelectTag());
        //注释
//        List<Chordata> chordataList = animalia.getChordataList();
//        List<ChordataVO> chordataVOList = new ArrayList<>();
//        for (Chordata temp : chordataList) {
//            ChordataVO chordataVO = ChordataVO.entityToVO(temp);
//            chordataVOList.add(chordataVO);
//        }
//        animaliaVo.setChordataList(chordataVOList);
        return animaliaVo;
    }

}
