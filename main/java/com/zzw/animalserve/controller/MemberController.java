package com.zzw.animalserve.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzw.animalserve.common.BaseController;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ErrorCode;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.common.lang.Const;
import com.zzw.animalserve.common.result.Result;
import com.zzw.animalserve.entity.Mammalia;
import com.zzw.animalserve.entity.Role;
import com.zzw.animalserve.entity.UserRole;
import com.zzw.animalserve.entity.dto.MemberDto;
import com.zzw.animalserve.entity.dto.PassDto;
import com.zzw.animalserve.entity.response.MemberVO;
import com.zzw.animalserve.entity.response.MenuVO;
import com.zzw.animalserve.exception.BusinessException;
import com.zzw.animalserve.entity.Member;
import com.zzw.animalserve.service.MemberService;
import com.zzw.animalserve.entity.dto.AuthMemberDto;
import com.zzw.animalserve.utils.COSStorageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * (Member)表控制层
 *
 * @author makejava
 * @since 2022-10-09 14:54:27
 */
@Slf4j
@RestController
@RequestMapping("member")
@Api(tags = "授权接入接口")
public class MemberController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 分页查询
     *
     * @param member 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Member>> queryByPage(Member member, PageRequest pageRequest) {
        return ResponseEntity.ok(this.memberService.queryByPage(member, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Member> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.memberService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param member 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Member> add(Member member) {
        return ResponseEntity.ok(this.memberService.insert(member));
    }

    /**
     * 编辑数据
     *
     * @param member 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Member> edit(Member member) {
        return ResponseEntity.ok(this.memberService.update(member));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.memberService.deleteById(id));
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public BaseResponse<Map<String, Object>> userLogin(@RequestBody AuthMemberDto userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        log.info("输入参数========>"+userLoginRequest.toString());
        String memberEmail = userLoginRequest.getEmail();
        String password = userLoginRequest.getPassword();
        if (StringUtils.isAnyBlank(memberEmail, password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Map<String, Object> map = memberService.userLogin(memberEmail, password, request);
        return ResultUtils.success(map);
    }

    @ApiOperation("后台登录")
    @PostMapping("/loginAdmin")
    public BaseResponse<Map<String, Object>> userLoginAdmin(@RequestBody AuthMemberDto userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String memberName = userLoginRequest.getUsername();
        String password = userLoginRequest.getPassword();
        System.out.println("==============="+memberName);
        System.out.println("==============="+password);
        if (StringUtils.isAnyBlank(memberName, password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Map<String, Object> map = memberService.userLoginAdmin(memberName, password, request);
        return ResultUtils.success(map);
    }

    @ApiOperation("注册授权")
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody AuthMemberDto userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        log.warn("========================>"+userRegisterRequest.toString());
        String memberName = userRegisterRequest.getUsername();
        String memberEmail = userRegisterRequest.getEmail();
        String password = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getNewPassword();
        String memberPhone = userRegisterRequest.getPhone();
        String verCode = userRegisterRequest.getCode();
        if (StringUtils.isAnyBlank(memberName,memberEmail, memberPhone, verCode, password, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long result = memberService.userRegister(memberName, memberEmail,memberPhone, verCode,password,checkPassword);
        return ResultUtils.success(result);
    }

    @ApiOperation("次级管理员注册授权")
    @PostMapping("/registerAdmin")
    public BaseResponse<Long> userRegisterAdmin(@RequestBody AuthMemberDto userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String memberName = userRegisterRequest.getUsername();
        String memberEmail = userRegisterRequest.getEmail();
        String password = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getNewPassword();
        String memberPhone = userRegisterRequest.getPhone();
        String verCode = userRegisterRequest.getCode();
        if (StringUtils.isAnyBlank(memberName,memberEmail, memberPhone, verCode, password, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long result = memberService.userRegisterAdmin(memberName, memberEmail,memberPhone, verCode,password,checkPassword);
        return ResultUtils.success(result);
    }

    @ApiOperation("超级管理员添加授权")
    @PostMapping("/addSuperAdmin")
    @PreAuthorize("hasAuthority('sys:user:save')")
    public BaseResponse<Long> userRegisterSuperAdmin(@RequestBody AuthMemberDto userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //user.setAvatar(Const.DEFULT_AVATAR);
        String memberName = userRegisterRequest.getUsername();
        String memberEmail = userRegisterRequest.getEmail();
        String password = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getNewPassword();
        String memberPhone = userRegisterRequest.getPhone();
        String verCode = userRegisterRequest.getCode();
        if (StringUtils.isAnyBlank(memberName,memberEmail, memberPhone, verCode, password, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long result = memberService.userRegisterSpuerAdmin(memberName, memberEmail,memberPhone, verCode,password,checkPassword);
        return ResultUtils.success(result);
    }

    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        int result = memberService.userLogout(request);
        return ResultUtils.success(result);
    }

    @ApiOperation("确认登录状态")
    @GetMapping("/current")
    public BaseResponse<MemberVO> getCurrentUser(HttpServletRequest request) {
        Member loginUser = memberService.getLoginUser(request);
        Long userId = loginUser.getMemberId();
        log.warn("用户脱敏信息"+userId.toString());
        Member user = memberService.queryById(userId);
//        log.warn("用户脱敏信息"+user.toString());
        Member safetyUser = memberService.getSafetyUser(user);
//        log.warn("用户脱敏信息"+safetyUser.toString());
        MemberVO memberVO = MemberVO.entityToVO(safetyUser);
        return ResultUtils.success(memberVO);
    }

    //根据用户的编号 查询该用户的详细信息
    @ApiOperation("根据用户的编号 查询该用户的详细信息")
    @GetMapping("/info/{id}")
//    @PreAuthorize("hasAuthority('sys:user:list')")
    public BaseResponse<MemberVO> info(@PathVariable Long id){
        Member user = memberService.queryById(id);
        Assert.notNull(user,"查询不到用户信息");
        List<Role> roles = roleService.listRolesByUserId(id);
        user.setRoles(roles);
        MemberVO memberVO = MemberVO.entityToVO(user);
        return ResultUtils.success(memberVO);
    }

    //用户修改自己的密码      PassDto为定义专门用来更新密码的实体类
    //spring security在进行认证时，会将用户名和密码封装成一个Authentication对象，在进行认证后，会将Authentication的权限等信息填充完全返回。Authentication会被存在SecurityContext中，供应用之后的授权等操作使用。
    //public interface Authentication extends Principal  Authentication继承自Principal，所以直接使用Principal获得登录时的用户信息
    @ApiOperation("用户修改自己的密码")
    @PostMapping("/updatePass")
    public BaseResponse<Object> updatePass(@RequestBody PassDto passDto, Principal principal){
        Member user = memberService.getByUsername(principal.getName());
        boolean matches = passwordEncoder.matches(passDto.getCurrentPass(), user.getPassword());
        if(!matches){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"旧密码错误");
        }
        user.setPassword(passwordEncoder.encode(passDto.getPassword()));
        user.setUpdateTime(LocalDateTime.now().toString());
        memberService.updateById(user);
        return ResultUtils.success(null);
    }

    /*
    PasswordEncoder的方法介绍：
    encode() ： 把参数按照特定的解析规则进行解析。
    matches() ： 验证从存储中获取的编码密码与编码后提交的原始密码是否匹配。如果密码匹配，则返回 true；如果不匹配，则返回 false。第一个参数表示需要被解析的密码。第二个参数表示存储的密码。
    upgradeEncoding() ：如果解析的密码能够再次进行解析且达到更安全的结果则返回 true，否则返回 false。 默认返回 false。
    * */


    ////超级管理员重置密码

    @PostMapping("/repass")
    @ApiOperation("超级管理员重置密码")
    @PreAuthorize("hasAuthority('sys:user:repass')")
    public BaseResponse<Object> repass(@RequestBody Long userId){
        Member user =  memberService.queryById(userId);
        user.setPassword(passwordEncoder.encode(Const.DEFULT_PASSWORD));
        user.setUpdateTime(LocalDateTime.now().toString());
        memberService.updateById(user);
        return ResultUtils.success(null);
    }

    ////获得用户的列表信息   参数username为页面搜索栏输入的用户名搜索，如果有值就代入SQL模糊查询，没有就查询所有用户
    @GetMapping("/list")
    @ApiOperation("获得用户的列表信息")
//    @PreAuthorize("hasAuthority('sys:user:list')")
    public  BaseResponse<List<MemberVO>> page(String username){
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            queryWrapper.like("username",username);
        }
        List<Member> list1 = memberService.list(queryWrapper);
        List<MemberVO> list = new ArrayList<>();
        for (   Member temp : list1) {
            if(temp.getDelectTag() == 0){
                MemberVO memberVO = MemberVO.entityToVO(temp);
                list.add(memberVO);
            }
        }
        return ResultUtils.success(list);
    }

    @ApiOperation("条件查询")
    @PostMapping("/listSearch")
//    @PreAuthorize("hasAuthority('sys:user:list')")
    public  BaseResponse<List<MemberVO>> queryBySearch(@RequestBody MemberDto memberDto){
        Member member = memberDto.toEntity();
        List<Member> list1 = memberService.queryAllBy(member);
        List<MemberVO> list = new ArrayList<>();
        for (   Member temp : list1) {
            if(temp.getDelectTag() == 0){
                MemberVO memberVO = MemberVO.entityToVO(temp);
                list.add(memberVO);
            }
        }
        return ResultUtils.success(list);
    }

    @PostMapping("/update")
    @ApiOperation("更新用户信息")
//    @PreAuthorize("hasAuthority('sys:user:update')")
    public BaseResponse<Object> update(@RequestBody MemberDto member, MultipartFile file){
        if(file==null){
            Member user = new Member();
            user.setMemberId(member.getId());
            user.setMemberEmail(member.getEmail());
            user.setMemberName(member.getUsername());
            user.setMemberPhone(member.getMobile());
            member.setAvatar(member.getAvatar());
            user.setMemberSex(member.getGender());
            user.setMemberImage(member.getAvatar());
            user.setUpdateTime(LocalDateTime.now().toString());  //更新时间为当前时间
            Member update = memberService.update(user);
            MemberVO memberVO = MemberVO.entityToVO(update);
            return ResultUtils.success(memberVO);
        }
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "图片上传不能为空！");
        }
        String contentType = file.getContentType();
        String originalFilename = file.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
        //拼接好的路径
        String result = COSStorageUtil.upLoad(inputStream, ext, contentType);
        member.setAvatar(result);
        Member user = new Member();
        user.setMemberId(member.getId());
        user.setMemberEmail(member.getEmail());
        user.setMemberName(member.getUsername());
        user.setMemberPhone(member.getMobile());
        user.setMemberSex(member.getGender());
        user.setMemberImage(member.getAvatar());
        user.setUpdateTime(LocalDateTime.now().toString());  //更新时间为当前时间
        boolean update = memberService.updateById(user);
        MemberVO memberVO = MemberVO.entityToVO(user);
        return ResultUtils.success(memberVO);
    }


    @Transactional
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    public BaseResponse<Object> delete(@RequestBody Long[] id){
        //删除用户
        memberService.removeByIds(Arrays.asList(id));
        //删除用户关联的角色
        userRoleService.remove(new QueryWrapper<UserRole>().in("member_Id",id));
        return ResultUtils.success(null);
    }

    ////分配用户角色
    @Transactional
    @PostMapping("/role/{userId}")
    @PreAuthorize("hasAuthority('sys:user:role')")
    public BaseResponse<Object> rolePerm(@PathVariable("userId") Long userId,@RequestBody Long[] roleIds){
        List<UserRole> userRoleList = new ArrayList<>();

        Arrays.stream(roleIds).forEach(r->{
            UserRole userrole = new UserRole();
            userrole.setRoleId(r);
            userrole.setMemberId(userId);

            userRoleList.add(userrole);
        });

        userRoleService.remove(new QueryWrapper<UserRole>().eq("member_Id",userId));
        userRoleService.saveBatch(userRoleList);

        //删除缓存
        Member user = memberService.queryById(userId);
        memberService.clearUserAuthorityInfo(user.getMemberName());

        return ResultUtils.success(null);
    }

    /**
     * 删除数据
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除用户信息")
    @DeleteMapping("/deleteUser")
    //@PreAuthorize("hasAuthority('sys:mammalia:delete')")
    public BaseResponse<Boolean> deleteBy(Long id) {
        System.out.println("==传入的参数===>"+id);
        Member member = memberService.queryById(id);
        member.setDelectTag(1);
        Member update = this.memberService.update(member);
        boolean show = true;
        if (Objects.isNull(update)){
            show = false;
        }
        return ResultUtils.success(show);
    }


}

