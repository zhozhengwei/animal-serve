package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.Biology;
import com.zzw.animalserve.entity.BiologyImage;
import com.zzw.animalserve.utils.TimeFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/27__15:07
 */
@Data
public class BiologyVO {

    private Long id;

    private String name;

    private String mdescription;

    private String biology;

    private String distributionAbroad;

    private String domesticDistribution;

    private Integer delete;

    //其它信息
    //属
    private String bosName;

    //科
    private String bovideaName;

    //目
    private String artiodactyName;

    //纲
    private String mammaliaName;

    //门
    private String chordataName;

    //界
    private String animaliaName;

    //时间

    private String createdAt;

    private String updatedAt;

    //图片列表

    private List<BiologyImageVO> images;

    public static BiologyVO entityToVO(Biology biology){
        BiologyVO biologyVO = new BiologyVO();
        biologyVO.setId(biology.getBiologyId());
        biologyVO.setName(biology.getBiologyName());
        biologyVO.setMdescription(biology.getMorphologicalDescription());
        biologyVO.setBiology(biology.getBiology());
        biologyVO.setDistributionAbroad(biology.getDistributionAbroad());
        biologyVO.setDomesticDistribution(biology.getDomesticDistribution());
        //其它信息
        biologyVO.setBosName(biology.getBos().getBosName());
        biologyVO.setBovideaName(biology.getBovidae().getBovidaeName());
        biologyVO.setArtiodactyName(biology.getArtiodactyla().getArtiodactylaName());
        biologyVO.setMammaliaName(biology.getMammalia().getMammaliaName());
        biologyVO.setChordataName(biology.getChordata().getChordataName());
        biologyVO.setAnimaliaName(biology.getAnimalia().getAnimaliaName());
        //时间
        biologyVO.setCreatedAt(TimeFormat.formatTime(biology.getCreateTime()));
        biologyVO.setUpdatedAt(TimeFormat.formatTime(biology.getUpdateTime()));
        //图片列表
        List<BiologyImageVO> biologyImageVOList = new ArrayList<>();
        for (BiologyImage temp: biology.getBiologyImageList()) {
            BiologyImageVO biologyImageVO = BiologyImageVO.entityToVO(temp);
            biologyImageVOList.add(biologyImageVO);
        }
        biologyVO.setImages(biologyImageVOList);
        biologyVO.setDelete(biology.getDelectTag());
        return biologyVO;
    }

}
