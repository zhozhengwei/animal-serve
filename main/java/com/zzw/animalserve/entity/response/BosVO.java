package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.Animalia;
import com.zzw.animalserve.entity.Biology;
import com.zzw.animalserve.entity.Bos;
import com.zzw.animalserve.utils.TimeFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/27__22:58
 */
@Data
public class BosVO {

    private Long id;

    private String name;

    private String createdAt;

    private String updatedAt;

    private Integer delectTag;

    private Long aid;

    private String aname;

    private Long cid;

    private String cname;

    private Long mid;

    private String mname;

    private Long arid;

    private String arname;

    private Long bid;

    private String bname;

    private List<BiologyVO> animal;

    public static BosVO entityToVO(Bos animalia){
        BosVO animaliaVo = new BosVO();
        animaliaVo.setId(animalia.getBosId());
        animaliaVo.setName(animalia.getBosName());
        animaliaVo.setCreatedAt(TimeFormat.formatTime(animalia.getCreateTime()));
        animaliaVo.setUpdatedAt(TimeFormat.formatTime(animalia.getUpdateTime()));
        animaliaVo.setDelectTag(animalia.getDelectTag());
        //界信息
        animaliaVo.setAid(animalia.getAnimaliaId());
        animaliaVo.setAname(animalia.getAnimalia().getAnimaliaName());
        //门信息
        animaliaVo.setCid(animalia.getChordataId());
        animaliaVo.setCname(animalia.getChordata().getChordataName());
        //纲信息
        animaliaVo.setMid(animalia.getMammaliaId());
        animaliaVo.setMname(animalia.getMammalia().getMammaliaName());
        //目信息
        animaliaVo.setArid(animalia.getArtiodactylaId());
        animaliaVo.setArname(animalia.getArtiodactyla().getArtiodactylaName());
        //科信息
        animaliaVo.setBid(animalia.getBovidaeId());
        animaliaVo.setBname(animalia.getBovidae().getBovidaeName());
        //动物列表
        List<Biology> biologyList = animalia.getBiologyList();
        List<BiologyVO> biologyVOList = new ArrayList<>();
        for (Biology temp:biologyList) {
            BiologyVO biologyVO = BiologyVO.entityToVO(temp);
            biologyVOList.add(biologyVO);
        }
        animaliaVo.setAnimal(biologyVOList);
        return animaliaVo;
    }
}
