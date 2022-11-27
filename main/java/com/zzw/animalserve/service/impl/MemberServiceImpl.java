package com.zzw.animalserve.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzw.animalserve.common.ErrorCode;
import com.zzw.animalserve.constant.MemberConstant;
import com.zzw.animalserve.entity.Menu;
import com.zzw.animalserve.entity.Role;
import com.zzw.animalserve.entity.response.MemberVO;
import com.zzw.animalserve.exception.BusinessException;
import com.zzw.animalserve.mapper.MemberMapper;
import com.zzw.animalserve.entity.Member;
import com.zzw.animalserve.security.AccountUser;
import com.zzw.animalserve.service.MemberService;
import com.zzw.animalserve.service.MenuService;
import com.zzw.animalserve.service.RoleService;
import com.zzw.animalserve.utils.JwtUtil;
import com.zzw.animalserve.utils.RedisCache;
import com.zzw.animalserve.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * (Member)表服务实现类
 *
 * @author makejava
 * @since 2022-10-09 14:38:04
 */
@Service
@Slf4j
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member>
        implements MemberService{
    @Resource
    private MemberMapper memberDao;

    @Autowired(required = false)
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    RedisUtil redisUtil;


    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    /**
     *混乱密文,言值
     */
    private static final String SALT = "zzw";

    /**
     * 通过ID查询单条数据
     *
     * @param memberId 主键
     * @return 实例对象
     */
    @Override
    public Member queryById(Long memberId) {
        return this.memberDao.queryById(memberId);
    }

    /**
     * 分页查询
     *
     * @param member 筛选条件interlinkage
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Member> queryByPage(Member member, PageRequest pageRequest) {
        long total = this.memberDao.count(member);
        return new PageImpl<>(this.memberDao.queryAllByLimit(member, pageRequest), pageRequest, total);
    }

    @Override
    public List<Member> queryAllBy(Member member){
        return memberDao.queryAllBy(member);
    }
    /**
     * 新增数据
     *
     * @param member 实例对象
     * @return 实例对象
     */
    @Override
    public Member insert(Member member) {
        this.memberDao.insert(member);
        return member;
    }

    /**
     * 修改数据
     *
     * @param member 实例对象
     * @return 实例对象
     */
    @Override
    public Member update(Member member) {
        this.memberDao.update(member);
        return this.queryById(member.getMemberId());
    }

    /**
     * 通过主键删除数据
     *
     * @param memberId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long memberId) {
        return this.memberDao.deleteById(memberId) > 0;
    }

    //todo 用户登录和注册业务代码

    @Override
    public long userRegister(String memberName,String memberEmail, String memberPhone , String verCode, String userPassword, String checkPassword) {
        //1.校验
        if(StringUtils.isAllBlank(memberName,memberEmail, userPassword,memberPhone,verCode, checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        if(memberName.length() < 4){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户账户过短");
        }
        if(userPassword.length() < 8 || checkPassword.length() < 8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码过短");
        }
        String emailMatcher="[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+";
        if(!memberEmail.matches(emailMatcher)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "邮箱格式出现问题！");
        }
        //校验账户，不包含特殊字符
        String valibPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(valibPattern).matcher(memberName);
        if(matcher.find()){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户不能包含特殊字符");
        }
        //密码和校验密码相同
        if(!userPassword.equals(checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次密码不同！");
        }

        //账户不能重复
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_Email", memberEmail);
        long count = memberDao.selectCount(queryWrapper);
        if(count > 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复！");
        }

        Object value = redisCache.getCacheObject(memberEmail);
        System.out.println("+++++++++++"+value);
        System.out.println("++++++++++++++"+verCode);
        if(value == null || !value.toString().equals(verCode)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "无效验证码！");
        }else{
            redisCache.deleteObject(memberEmail);
        }

//        QueryWrapper<Member> queryWrapper1 = new QueryWrapper<>();
//        queryWrapper.eq("member_Name", memberName);
//        long count1 = memberDao.selectCount(queryWrapper1);
//        if(count1 > 0){
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名重复！");
//        }
        //2.加密
//        String newPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String newPassword = passwordEncoder.encode(userPassword);
        //3.插入数据
        Member user = new Member();
//        user.setUserAccount(userAccount);
        user.setMemberName(memberName);
        user.setMemberEmail(memberEmail);
//        user.setUserPassword(newPassword);
        user.setPassword(newPassword);
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setCreateTime(format.format(date));
        user.setMemberImage("https://xingqiu-tuchuang-1256524210.cos.ap-shanghai.myqcloud.com/1754/user-centerLogo.png");
        user.setUpdateTime(format.format(date));
        user.setMemberPhone(memberPhone);
        user.setMemberRole(3);
        user.setDelectTag(0);
        boolean saveResult = this.save(user);
        if(!saveResult){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "数据插入失败！");
        }
        return user.getMemberId();
    }

    @Override
    public long userRegisterAdmin(String memberName, String memberEmail, String memberPhone, String verCode, String userPassword, String checkPassword) {
        //1.校验
        if(StringUtils.isAllBlank(memberName,memberEmail, userPassword,memberPhone,verCode, checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        if(memberName.length() < 4){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户账户过短");
        }
        if(userPassword.length() < 8 || checkPassword.length() < 8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码过短");
        }
        String emailMatcher="[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+";
        if(!memberEmail.matches(emailMatcher)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "邮箱格式出现问题！");
        }
        //校验账户，不包含特殊字符
        String valibPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(valibPattern).matcher(memberName);
        if(matcher.find()){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户不能包含特殊字符");
        }
        //密码和校验密码相同
        if(!userPassword.equals(checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次密码不同！");
        }

        //账户不能重复
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_Email", memberEmail);
        long count = memberDao.selectCount(queryWrapper);
        if(count > 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复！");
        }

        Object value = redisCache.getCacheObject(memberEmail);
        System.out.println("+++++++++++"+value);
        System.out.println("++++++++++++++"+verCode);
        if(value == null || !value.toString().equals(verCode)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "无效验证码！");
        }else{
            redisCache.deleteObject(memberEmail);
        }

        QueryWrapper<Member> queryWrapper1 = new QueryWrapper<>();
        queryWrapper.eq("member_Name", memberName);
        long count1 = memberDao.selectCount(queryWrapper1);
        if(count1 > 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名重复！");
        }
        //2.加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String newPassword = passwordEncoder.encode(userPassword);
        //3.插入数据
        Member user = new Member();
//        user.setUserAccount(userAccount);
        user.setMemberName(memberName);
        user.setMemberEmail(memberEmail);
//        user.setUserPassword(newPassword);
        user.setPassword(newPassword);
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setCreateTime(format.format(date));
        user.setUpdateTime(format.format(date));
        user.setMemberPhone(memberPhone);
        user.setMemberImage("https://xingqiu-tuchuang-1256524210.cos.ap-shanghai.myqcloud.com/1754/user-centerLogo.png");
        user.setMemberRole(2);
        user.setDelectTag(0);
        boolean saveResult = this.save(user);
        if(!saveResult){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "数据插入失败！");
        }
        return user.getMemberId();
    }

    @Override
    public long userRegisterSpuerAdmin(String memberName, String memberEmail, String memberPhone, String verCode, String userPassword, String checkPassword) {
        //1.校验
        if(StringUtils.isAllBlank(memberName,memberEmail, userPassword,memberPhone,verCode, checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        if(memberName.length() < 4){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户账户过短");
        }
        if(userPassword.length() < 8 || checkPassword.length() < 8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码过短");
        }
        String emailMatcher="[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+";
        if(!memberEmail.matches(emailMatcher)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "邮箱格式出现问题！");
        }
        //校验账户，不包含特殊字符
        String valibPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(valibPattern).matcher(memberName);
        if(matcher.find()){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户不能包含特殊字符");
        }
        //密码和校验密码相同
        if(!userPassword.equals(checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次密码不同！");
        }

        //账户不能重复
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_Email", memberEmail);
        long count = memberDao.selectCount(queryWrapper);
        if(count > 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复！");
        }

        Object value = redisCache.getCacheObject(memberEmail);
        System.out.println("+++++++++++"+value);
        System.out.println("++++++++++++++"+verCode);
        if(value == null || !value.toString().equals(verCode)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "无效验证码！");
        }else{
            redisCache.deleteObject(memberEmail);
        }

        QueryWrapper<Member> queryWrapper1 = new QueryWrapper<>();
        queryWrapper.eq("memberName", memberName);
        long count1 = memberDao.selectCount(queryWrapper1);
        if(count1 > 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名重复！");
        }
        //2.加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String newPassword = passwordEncoder.encode(userPassword);
        //3.插入数据
        Member user = new Member();
//        user.setUserAccount(userAccount);
        user.setMemberName(memberName);
        user.setMemberEmail(memberEmail);
//        user.setUserPassword(newPassword);
        user.setPassword(newPassword);
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setCreateTime(format.format(date));
        user.setUpdateTime(format.format(date));
        user.setMemberPhone(memberPhone);
        user.setMemberRole(3);
        user.setDelectTag(0);
        boolean saveResult = this.save(user);
        if(!saveResult){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "数据插入失败！");
        }
        return user.getMemberId();
    }

    @Override
    public Map<String,Object> userLogin(String memberEmail, String userPassword, HttpServletRequest request) {
        //1.校验
        if(StringUtils.isAnyBlank(memberEmail, userPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "邮箱和密码不能为空！");
        }
//        if(userAccount.length() < 4){
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户长度不能小于四位！");
//        }
        if(userPassword.length() < 8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度不能小于四位！");
        }
        String emailMatcher="[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+";
        if(!memberEmail.matches(emailMatcher)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "邮箱格式出现问题！");
        }
        //校验账户，不包含特殊字符
//        String valibPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
//        Matcher matcher = Pattern.compile(valibPattern).matcher(userAccount);
//        if(matcher.find()){
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户不能包含特殊字符");
//        }

        //2.加密
//        String newPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //查询用户是否存在
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_Email", memberEmail);
        Member user = memberDao.selectOne(queryWrapper);
        if(!passwordEncoder.matches(userPassword,user.getPassword())){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
        }
        //用户不存在
        if(user == null){
            log.info("user login failed, memberEmail cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "没有该用户信息！你需要注册一个新的账号");
        }

        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getMemberEmail(), userPassword);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //如果认证没通过，给出对应的提示
        if(Objects.isNull(authenticate)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "登录失败");
        }
        //如果认证通过了，使用userId生成一个jwt
        AccountUser loginUser = (AccountUser) authenticate.getPrincipal();
        String userid = loginUser.getMemberId().toString();
        String jwt = JwtUtil.createJWT(userid);
        user.setToken(jwt);
        Map<String, Object> map = new HashMap<>();
        //3.用户脱敏
        Member safetyUser = getSafetyUser(user);
        MemberVO memberVO = MemberVO.entityToVO(user);
        //4.记录用户的登录状态，设置一个登录态
        request.getSession().setAttribute(MemberConstant.USER_LOGIN_STATE,safetyUser);
        //JSON.toJSONString(loginUser)
        redisCache.setCacheObject("login:"+userid, JSON.toJSONString(loginUser));
        //存储一份用户信息在redis中
        redisCache.setCacheObject(userid,memberVO);
        //返回脱敏后的用户信息
        Object cacheObject = redisCache.getCacheObject(userid);
        map.put("user",memberVO);
        return map;
    }

    @Override
    public Map<String, Object> userLoginAdmin(String memberName, String userPassword, HttpServletRequest request) {
        //1.校验
        if(StringUtils.isAnyBlank(memberName, userPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名和密码不能为空！");
        }
//        if(memberName.length() < 4){
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户长度不能小于四位！");
//        }
        if(userPassword.length() < 8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度不能小于四位！");
        }
//        String emailMatcher="[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+";
//        if(!memberEmail.matches(emailMatcher)){
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "邮箱格式出现问题！");
//        }
        //校验账户，不包含特殊字符
        String valibPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(valibPattern).matcher(memberName);
        if(matcher.find()){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户不能包含特殊字符");
        }

        //2.加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //查询用户是否存在
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_Name", memberName);
        Member user = memberDao.selectOne(queryWrapper);
        if(!passwordEncoder.matches(userPassword,user.getPassword())){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
        }
        //用户不存在
        if(user == null){
            log.info("user login failed, memberEmail cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "没有该用户信息！你需要注册一个新的账号");
        }

        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getMemberName(), userPassword);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //如果认证没通过，给出对应的提示
        if(Objects.isNull(authenticate)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "登录失败");
        }
        //如果认证通过了，使用userId生成一个jwt
        AccountUser loginUser = (AccountUser) authenticate.getPrincipal();
        String userid = loginUser.getMemberId().toString();
        String jwt = JwtUtil.createJWT(userid);
        user.setToken(jwt);
        Map<String, Object> map = new HashMap<>();

        //3.用户脱敏
        Member safetyUser = getSafetyUser(user);
        MemberVO memberVO = MemberVO.entityToVO(safetyUser);
        //4.记录用户的登录状态，设置一个登录态
        request.getSession().setAttribute(MemberConstant.USER_LOGIN_STATE,safetyUser);
        redisCache.setCacheObject("login:"+userid, JSON.toJSONString(loginUser));
        //存储一份用户信息在redis中
        redisCache.setCacheObject(userid,safetyUser);
        Object cacheObject = redisCache.getCacheObject(userid);
        map.put("user",memberVO);
        //返回脱敏后的用户信息
        return map;
    }

    @Override
    public Member getSafetyUser(Member originUser) {
        //为什么需要在控制层校验后在服务层还要校验的原因
        //如果在控制层查数据库查出为空，在脱敏时为空代码就会出现问题。
        if(originUser == null){
            return null;
        }
        Member safetyUser = new Member();
        safetyUser.setMemberId(originUser.getMemberId());
        safetyUser.setMemberName(originUser.getMemberName());
        safetyUser.setMemberEmail(originUser.getMemberEmail());
        safetyUser.setMemberImage(originUser.getMemberImage());
        safetyUser.setMemberSex(originUser.getMemberSex());
        safetyUser.setMemberPhone(originUser.getMemberPhone());
        safetyUser.setMemberRole(originUser.getMemberRole());
        safetyUser.setDelectTag(originUser.getDelectTag());
        safetyUser.setCreateTime(originUser.getCreateTime());
        safetyUser.setToken(originUser.getToken());
        safetyUser.setRoles(originUser.getRoles());
        return safetyUser;
    }

    @Override
    public int userLogout(HttpServletRequest request) {
        // 移除登录态
        request.getSession().removeAttribute(MemberConstant.USER_LOGIN_STATE);
        //清除缓存
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        AccountUser user = (AccountUser) authentication.getPrincipal();
        Long userid = user.getMemberId();
        //删除redis中的值
        String redisKey = "login:"+userid;
        redisCache.deleteObject(redisKey);
        redisCache.deleteObject(userid.toString());
        return 1;
    }


    @Override
    public Member getLoginUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(MemberConstant.USER_LOGIN_STATE);
        Member loginUser = (Member) userObj;
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        return loginUser;
    }

    @Override
    public Member getByUsername(String memberName) {
        return getOne(new QueryWrapper<Member>().eq("member_Name",memberName));
    }

    //getUserAuthorityInfo
    @Override
    public String getUserAuthorityInfo(Long memberId) {
        Member member = memberDao.queryById(memberId);

        String authority = "";

        if(redisUtil.hasKey("GrantedAuthority:" + member.getMemberName())){
            authority = (String) redisUtil.get("GrantedAuthority:" + member.getMemberName());
        }else{
            // 获取角色编码
            List<Role> roles =roleService.list(new QueryWrapper<Role>()
                    .inSql("id","select role_id from sys_member_role where member_id = "+ memberId));

            if (roles.size() > 0) {
                String roleCodes = roles.stream().map(r -> "ROLE_" + r.getCode()).collect(Collectors.joining(","));
                authority = roleCodes.concat(",");
                System.out.println("authority=="+authority);
            }

            // 获取菜单操作编码
            List<Long> menuIds = memberDao.getNavMenuIds(memberId);
            if (menuIds.size() > 0) {

                List<Menu> menus = menuService.listByIds(menuIds);
                String menuPerms = menus.stream().map(m -> m.getPerms()).collect(Collectors.joining(","));

                authority = authority.concat(menuPerms);
                System.out.println("authority2==="+authority);
            }

            //存储到redis中一份权限字符串。
            redisUtil.set("GrantedAuthority:" + member.getMemberName(), authority, 60 * 60);
        }
        return authority;
    }

    @Override
    public void clearUserAuthorityInfo(String memberName) {
        redisUtil.del("GrantedAuthority:"+memberName);
    }

    @Override
    public void clearUserAuthorityInfoByRoleId(Long roleId) {
        List<Member> users = this.list(new QueryWrapper<Member>().inSql("id","select member_id from sys_member_role where role_id = " + roleId));
        users.forEach(u -> {
            this.clearUserAuthorityInfo(u.getMemberName());
        });
    }

    @Override
    public void clearUserAuthorityInfoByMenuId(Long menuId) {
        List<Member> users = memberDao.listByMenuId(menuId);

        users.forEach(u -> {
            this.clearUserAuthorityInfo(u.getMemberName());
        });
    }
}
