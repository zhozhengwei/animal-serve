package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.Artiodactyla;
import com.zzw.animalserve.entity.Bovidae;
import com.zzw.animalserve.utils.TimeFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/27__22:51
 */
@Data
public class ArtiodactylaVO {

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
    
//    private List<BovidaeVO> bovidaeList;

    public static ArtiodactylaVO entityToVO(Artiodactyla artiodactyla){
        ArtiodactylaVO artiodactylaVO = new ArtiodactylaVO();
        artiodactylaVO.setId(artiodactyla.getArtiodactylaId());
        artiodactylaVO.setName(artiodactyla.getArtiodactylaName());
        artiodactylaVO.setCreatedAt(TimeFormat.formatTime(artiodactyla.getCreateTime()));
        artiodactylaVO.setUpdatedAt(TimeFormat.formatTime(artiodactyla.getUpdateTime()));
        artiodactylaVO.setDelectTag(artiodactyla.getDelectTag());
        //界信息
        artiodactylaVO.setAid(artiodactyla.getAnimaliaId());
        artiodactylaVO.setAname(artiodactyla.getAnimalia().getAnimaliaName());
        //门信息
        artiodactylaVO.setCid(artiodactyla.getChordataId());
        artiodactylaVO.setCname(artiodactyla.getChordata().getChordataName());
        //纲信息
        artiodactylaVO.setMid(artiodactyla.getMammaliaId());
        artiodactylaVO.setMname(artiodactyla.getMammalia().getMammaliaName());
        //目列表
//        List<Bovidae> bovidaeList = artiodactyla.getBovidaeList();
//        List<BovidaeVO> bovidaeVOList = new ArrayList<>();
//        for (Bovidae temp:bovidaeList) {
//            BovidaeVO bovidaeVO = BovidaeVO.entityToVO(temp);
//            bovidaeVOList.add(bovidaeVO);
//        }
//        artiodactylaVO.setBovidaeList(bovidaeVOList);
        return artiodactylaVO;
    }

}
