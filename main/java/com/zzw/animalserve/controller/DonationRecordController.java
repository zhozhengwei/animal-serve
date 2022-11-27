package com.zzw.animalserve.controller;


import com.zzw.animalserve.common.BaseController;
import com.zzw.animalserve.common.BaseResponse;
import com.zzw.animalserve.common.ResultUtils;
import com.zzw.animalserve.entity.DonationRecord;
import com.zzw.animalserve.entity.Orders;
import com.zzw.animalserve.entity.dto.DonationRecordDto;
import com.zzw.animalserve.entity.response.DonationRecordVO;
import com.zzw.animalserve.entity.response.PercentageVO;
import com.zzw.animalserve.mapper.OrderMapper;
import com.zzw.animalserve.utils.TimeFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.zzw.animalserve.service.DonationRecordService;

import javax.annotation.Resource;
import java.util.*;


@Api(tags = "捐献记录")
@RestController
@RequestMapping("/donationRecord")
public class DonationRecordController extends BaseController {

    @Resource
    private OrderMapper orderMapper;

    @Autowired
    private DonationRecordService donationRecordService;

    /**
     * 分页查询
     * @param name
     * @return
     */
    @ApiOperation("捐献列表")
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('sys:volunteer:lists')")
    public BaseResponse<List<DonationRecordVO>> queryByPage(String name) {
        if(StringUtils.isAnyBlank(name)){
            List<DonationRecord> allDonationRecord = donationRecordService.findAllDonationRecord();
            List<DonationRecordVO> donationRecordVOList = new ArrayList<>();
            for (DonationRecord temp: allDonationRecord) {
                if(temp.getDelectTag() == 0){
                    DonationRecordVO donationRecordVO = DonationRecordVO.entityToVO(temp);
                    donationRecordVOList.add(donationRecordVO);
                }
            }
            return ResultUtils.success(donationRecordVOList);
        }
        DonationRecord donationRecord = new DonationRecord();
        donationRecord.setDonationRecordName(name);
        List<DonationRecord> donationRecordByCondition = donationRecordService.findDonationRecordByCondition(donationRecord);
        List<DonationRecordVO> donationRecordVOList = new ArrayList<>();
        for (DonationRecord temp: donationRecordByCondition) {
            if(temp.getDelectTag() == 0){
                DonationRecordVO donationRecordVO = DonationRecordVO.entityToVO(temp);
                donationRecordVOList.add(donationRecordVO);
            }
        }
        return ResultUtils.success(donationRecordVOList);
    }

    @ApiOperation("条件查询")
    @PostMapping("/listSearch")
//    @PreAuthorize("hasAuthority('sys:volunteer:lists')")
    public BaseResponse<List<DonationRecordVO>> queryBySearch(@RequestBody DonationRecordDto donationRecordDto) {
        DonationRecord donationRecord = donationRecordDto.toEntity();
        List<DonationRecord> donationRecordByCondition = donationRecordService.findDonationRecordByCondition(donationRecord);
        List<DonationRecordVO> donationRecordVOList = new ArrayList<>();
        for (DonationRecord temp: donationRecordByCondition) {
            if(temp.getDelectTag() == 0){
                DonationRecordVO donationRecordVO = DonationRecordVO.entityToVO(temp);
                donationRecordVOList.add(donationRecordVO);
            }
        }
        return ResultUtils.success(donationRecordVOList);
    }

    // Page<RoleVO> roles = roleService.page(getPage(), new QueryWrapper<Role>().like(StrUtil.isNotBlank(name), "name", name));

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("单条捐献")
    @GetMapping("/searchId/{id}")
    //@PreAuthorize("hasAuthority('sys:volunteer:sigln')")
    public BaseResponse<DonationRecordVO> queryById(@PathVariable("id") Long id) {
        DonationRecord donationRecordBydonationRecordId = this.donationRecordService.findDonationRecordBydonationRecordId(id);
        DonationRecordVO donationRecordVO = DonationRecordVO.entityToVO(donationRecordBydonationRecordId);
        return ResultUtils.success(donationRecordVO);
    }

    /**
     * 新增数据
     *
     * @param donationRecordDto 实体
     * @return 新增结果
     */
    @ApiOperation("新增捐献")
    @PostMapping("/save")
    public BaseResponse<Integer> add(@RequestBody DonationRecordDto donationRecordDto) {
        DonationRecord donationRecord = donationRecordDto.toEntity();
        System.out.println("=====================捐献信息输入==========>"+donationRecord.toString());
        donationRecord.setCreateTime(TimeFormat.now());
        donationRecord.setUpdateTime(TimeFormat.now());
        donationRecord.setDelectTag(0);
        donationRecord.setDonationRecordDenote(0);
        return ResultUtils.success(this.donationRecordService.addDonationRecord(donationRecord));
    }

    @ApiOperation("前台数据百分比")
    @PostMapping("/count")
    public BaseResponse<List<PercentageVO>> getCount(){
        List<DonationRecord> allDonationRecord = donationRecordService.findAllDonationRecord();
        List<DonationRecord> countList = new ArrayList<>();
        for (DonationRecord temp: allDonationRecord) {
            if(temp.getDelectTag() == 0){
                countList.add(temp);
            }
        }
        double count = 0;
        double sum = 0;
        for (DonationRecord donationRecord:countList) {
            if(donationRecord.getCreateId() == 0){
                count += donationRecord.getDonationRecordAmount();
            }
            sum += donationRecord.getDonationRecordAmount();
        }
        List<PercentageVO> perm = new ArrayList<>();
        PercentageVO percentageVO = new PercentageVO();
        percentageVO.setName("游客");
        percentageVO.setValue(Math.round((count/sum)*100));
        perm.add(percentageVO);
        PercentageVO percentageVO1 = new PercentageVO();
        percentageVO1.setName("用户");
        percentageVO1.setValue(Math.round(((sum-count)/sum)*100));
        perm.add(percentageVO1);
        return ResultUtils.success(perm);
    }

    /**
     * 编辑数据
     *
     * @param donationRecordDto 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑捐献")
    @PutMapping("/update")
//    @PreAuthorize("hasAuthority('sys:apply:update')")
    public BaseResponse<Integer> edit(@RequestBody DonationRecordDto donationRecordDto) {
        DonationRecord donationRecord = donationRecordDto.toEntity();
        donationRecord.setUpdateTime(TimeFormat.now());
        return ResultUtils.success(this.donationRecordService.updateDonationRecordBydonationRecordId(donationRecord));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除捐献记录")
    @DeleteMapping("/delete")
//    @PreAuthorize("hasAuthority('sys:apply:delete')")
    public BaseResponse<Integer> deleteById(Long id) {
        DonationRecord applyMember = donationRecordService.findDonationRecordBydonationRecordId(id);
        applyMember.setDelectTag(1);
        Orders order = orderMapper.queryByTrade(applyMember.getDonationRecordTrade());
        if(!Objects.isNull(order)) {
            int i = orderMapper.deleteById(order.getId());
        }
        return ResultUtils.success(this.donationRecordService.updateDonationRecordBydonationRecordId(applyMember));
    }

}