package com.zzw.animalserve.entity.response;

import com.zzw.animalserve.entity.Member;
import com.zzw.animalserve.entity.Role;
import lombok.Data;

import java.util.List;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/12__23:39
 */
@Data
public class MemberVO {
    private long uid ;

    private String email;

    private String username ;

    private String mobile;

    private String avatar ;

    private String gender;

    private Integer status;

    private String token;

    private Integer role;

    private List<Role> roleList ;

    private String createdAt ;

    private String updatedAt ;


    public static MemberVO entityToVO(Member userEntity){
        MemberVO userVO = new MemberVO();
        userVO.setUid(userEntity.getMemberId());
        userVO.setUsername(userEntity.getMemberName());
        userVO.setGender(userEntity.getMemberSex());
        userVO.setEmail(userEntity.getMemberEmail());
        userVO.setMobile(userEntity.getMemberPhone());
        userVO.setAvatar(userEntity.getMemberImage());
        userVO.setStatus(userEntity.getDelectTag());
        userVO.setCreatedAt(userEntity.getCreateTime());
        userVO.setUpdatedAt(userEntity.getUpdateTime());
        userVO.setRole(userEntity.getMemberRole());
        userVO.setRoleList(userEntity.getRoles());
        userVO.setToken(userEntity.getToken());
        return userVO;
    }
}
