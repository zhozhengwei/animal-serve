package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.Artiodactyla;
import com.zzw.animalserve.entity.Mammalia;
import com.zzw.animalserve.utils.TimeFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/27__22:47
 */
@Data
public class MammaliaVO {

    private Long id;

    private String name;

    private String createdAt;

    private String updatedAt;

    private Integer delectTag;

    private Long aid;

    private String aname;

    private Long cid;

    private String cname;

//    private List<ArtiodactylaVO> artiodactylaList;

    public static MammaliaVO entityToVO(Mammalia mammalia){
        MammaliaVO mammaliaVO = new MammaliaVO();
        mammaliaVO.setId(mammalia.getMammaliaId());
        mammaliaVO.setName(mammalia.getMammaliaName());
        mammaliaVO.setCreatedAt(TimeFormat.formatTime(mammalia.getCreateTime()));
        mammaliaVO.setUpdatedAt(TimeFormat.formatTime(mammalia.getUpdateTime()));
        mammaliaVO.setDelectTag(mammalia.getDelectTag());
        //界信息
        mammaliaVO.setAid(mammalia.getAnimaliaId());
        mammaliaVO.setAname(mammalia.getAnimalia().getAnimaliaName());
        //门信息
        mammaliaVO.setCid(mammalia.getChordataId());
        mammaliaVO.setCname(mammalia.getChordata().getChordataName());
        //目列表
//        List<Artiodactyla> artiodactylaList = mammalia.getArtiodactylaList();
//        List<ArtiodactylaVO> artiodactylaVOList = new ArrayList<>();
//        for (Artiodactyla temp : artiodactylaList){
//            ArtiodactylaVO artiodactylaVO = ArtiodactylaVO.entityToVO(temp);
//            artiodactylaVOList.add(artiodactylaVO);
//        }
//        mammaliaVO.setArtiodactylaList(artiodactylaVOList);
        return mammaliaVO;
    }

}
