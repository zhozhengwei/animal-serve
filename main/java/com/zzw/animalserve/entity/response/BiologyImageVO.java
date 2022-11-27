package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.BiologyImage;
import lombok.Data;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/27__15:22
 */
@Data
public class BiologyImageVO {
    private Long bid;

    private Long imid;

    private String name;

    private String url;

    public static BiologyImageVO entityToVO(BiologyImage biologyImage){
        BiologyImageVO biologyImageVO = new BiologyImageVO();
        biologyImageVO.setBid(biologyImage.getBiologyId());
        biologyImageVO.setImid(biologyImage.getImageInformationId());
        biologyImageVO.setName(biologyImage.getImageInformation().getImageInformationName());
        biologyImageVO.setUrl(biologyImage.getImageInformation().getImageInformationUrl());
        return biologyImageVO;
    }
}
