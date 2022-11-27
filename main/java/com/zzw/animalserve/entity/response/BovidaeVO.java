package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.Bos;
import com.zzw.animalserve.entity.Bovidae;
import com.zzw.animalserve.utils.TimeFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/27__22:55
 */
@Data
public class BovidaeVO {
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

//    private List<BosVO> bosList;

    public static BovidaeVO entityToVO(Bovidae biology){
        BovidaeVO bovidaeVO = new BovidaeVO();
        bovidaeVO.setId(biology.getBovidaeId());
        bovidaeVO.setName(biology.getBovidaeName());
        bovidaeVO.setCreatedAt(TimeFormat.formatTime(biology.getCreateTime()));
        bovidaeVO.setUpdatedAt(TimeFormat.formatTime(biology.getUpdateTime()));
        bovidaeVO.setDelectTag(biology.getDelectTag());
        //界信息
        bovidaeVO.setAid(biology.getAnimaliaId());
        bovidaeVO.setAname(biology.getAnimalia().getAnimaliaName());
        //门信息
        bovidaeVO.setCid(biology.getChordataId());
        bovidaeVO.setCname(biology.getChordata().getChordataName());
        //纲信息
        bovidaeVO.setMid(biology.getMammaliaId());
        bovidaeVO.setMname(biology.getMammalia().getMammaliaName());
        //目信息
        bovidaeVO.setArid(biology.getArtiodactylaId());
        bovidaeVO.setArname(biology.getArtiodactyla().getArtiodactylaName());
        //属列表
//        List<Bos> bosList = biology.getBosList();
//        List<BosVO> biologyVOList = new ArrayList<>();
//        for (Bos temp : bosList) {
//            BosVO bosVO = BosVO.entityToVO(temp);
//            biologyVOList.add(bosVO);
//        }
//        bovidaeVO.setBosList(biologyVOList);
        return bovidaeVO;
    }

}
