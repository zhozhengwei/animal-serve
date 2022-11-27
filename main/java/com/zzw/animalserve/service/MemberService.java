package com.zzw.animalserve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzw.animalserve.exception.BusinessException;
import com.zzw.animalserve.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * (Member)表服务接口
 *
 * @author makejava
 * @since 2022-10-09 14:38:02
 */
public interface MemberService extends IService<Member> {

    /**
     * 通过ID查询单条数据
     *
     * @param memberId 主键
     * @return 实例对象
     */
    Member queryById(Long memberId);

    /**
     * 分页查询
     *
     * @param member 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Member> queryByPage(Member member, PageRequest pageRequest);

    List<Member> queryAllBy(Member member);

    /**
     * 新增数据
     *
     * @param member 实例对象
     * @return 实例对象
     */
    Member insert(Member member);

    /**
     * 修改数据
     *
     * @param member 实例对象
     * @return 实例对象
     */
    Member update(Member member);

    /**
     * 通过主键删除数据
     *
     * @param memberId 主键
     * @return 是否成功
     */
    boolean deleteById(Long memberId);

    //todo 有关用户的代码

    /**
     * 普通用户注册
     *
     * @param memberName 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String memberName,String memberEmail, String memberPhone, String verCode, String userPassword, String checkPassword);

    /**
     * 次级管理员注册
     * @param memberName
     * @param memberEmail
     * @param memberPhone
     * @param verCode
     * @param userPassword
     * @param checkPassword
     * @return
     */
    long userRegisterAdmin(String memberName,String memberEmail,String memberPhone,String verCode, String userPassword, String checkPassword);

    /**
     * 超级管理员注册
     * @param memberName
     * @param memberEmail
     * @param memberPhone
     * @param verCode
     * @param userPassword
     * @param checkPassword
     * @return
     */
    long userRegisterSpuerAdmin(String memberName,String memberEmail,String memberPhone,String verCode, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    Map<String,Object> userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 管理员登录
     * @param userAccount
     * @param userPassword
     * @param request
     * @return
     */
    Map<String,Object> userLoginAdmin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     *
     * @param originUser
     * @return
     */
    Member getSafetyUser(Member originUser);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    int userLogout(HttpServletRequest request);

    /**
     * 获取登录用户（查缓存）
     *
     * @param request
     * @return
     * @throws BusinessException 未登录则抛异常
     */
    Member getLoginUser(HttpServletRequest request);


    Member getByUsername(String memberName);

    String getUserAuthorityInfo(Long memberId);
    //删除某个用户的权限信息
    void clearUserAuthorityInfo(String memberName);
    //删除所有与该角色关联的用户的权限信息
    void clearUserAuthorityInfoByRoleId(Long roleId);
    //删除所有与该菜单关联的所有用户的权限信息
    void clearUserAuthorityInfoByMenuId(Long menuId);
}
