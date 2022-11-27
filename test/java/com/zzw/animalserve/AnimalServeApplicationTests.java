package com.zzw.animalserve;

import com.zzw.animalserve.constant.MemberConstant;
import com.zzw.animalserve.entity.Chordata;
import com.zzw.animalserve.entity.DonationRecord;
import com.zzw.animalserve.entity.response.PercentageVO;
import com.zzw.animalserve.mapper.DonationRecordMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AnimalServeApplicationTests {
	@Autowired
	DonationRecordMapper donationRecordService;

	@Test
	void contextLoads() {

		List<DonationRecord> allDonationRecord = donationRecordService.findAllDonationRecord();
		long count = 0;
		long sum = 0;
		for (DonationRecord donationRecord:allDonationRecord) {
			if(donationRecord.getCreateId() == 0){
				count += donationRecord.getDonationRecordAmount();
			}
			sum += donationRecord.getDonationRecordAmount();
		}
		List<PercentageVO> perm = new ArrayList<>();
		PercentageVO percentageVO = new PercentageVO();
		percentageVO.setName("游客");
		percentageVO.setValue((count/sum)*100);
		perm.add(percentageVO);
		PercentageVO percentageVO1 = new PercentageVO();
		percentageVO1.setName("用户");
		percentageVO1.setValue(((sum-count)/sum)*100);
		perm.add(percentageVO1);

		System.out.println("=====数据====>"+perm.toString());
	}

}
