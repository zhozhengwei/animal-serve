package com.zzw.animalserve.controller;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.easysdk.factory.Factory;
import com.zzw.animalserve.config.AliPayConfig;
import com.zzw.animalserve.entity.AliPay;
import com.zzw.animalserve.entity.DonationRecord;
import com.zzw.animalserve.entity.Orders;
import com.zzw.animalserve.mapper.DonationRecordMapper;
import com.zzw.animalserve.mapper.OrderMapper;
import com.zzw.animalserve.service.DonationRecordService;
import com.zzw.animalserve.utils.TimeFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/11/18__2:45
 */

@Api(tags = "支付宝支付接口")
@RestController
@RequestMapping("/alipay")
public class AliPayController {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DonationRecordService donationRecordService;

    @Autowired
    private DonationRecordMapper donationRecordMapper;

    @Autowired
    AliPayConfig aliPayConfig;
    private static final String GATEWAY_URL ="https://openapi.alipaydev.com/gateway.do";
    private static final String FORMAT ="JSON";
    private static final String CHARSET ="utf-8";
    private static final String SIGN_TYPE ="RSA2";

    @ApiOperation("支付")
    @GetMapping("/pay") // &subject=xxx&traceNo=xxx&totalAmount=xxx
    public void pay(AliPay aliPay, HttpServletResponse httpResponse) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, aliPayConfig.getAppId(),
                aliPayConfig.getAppPrivateKey(), FORMAT, CHARSET, aliPayConfig.getAlipayPublicKey(), SIGN_TYPE);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        request.setBizContent("{\"out_trade_no\":\"" + aliPay.getTraceNo() + "\","
                + "\"total_amount\":\"" + aliPay.getTotalAmount() + "\","
                + "\"subject\":\"" + aliPay.getSubject() + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }


    @ApiOperation("回调")
    @PostMapping("/notify")  // 注意这里必须是POST接口
    public String payNotify(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
                // System.out.println(name + " = " + request.getParameter(name));
            }

            String tradeNo = params.get("out_trade_no");
            String gmtPayment = params.get("gmt_payment");
            String alipayTradeNo = params.get("trade_no");
            // 支付宝验签
            if (Factory.Payment.Common().verifyNotify(params)) {
                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));
                String trade = params.get("out_trade_no");
                Orders order=new Orders();
                order.setName(params.get("subject"));
                order.setStatus(params.get("trade_status"));
                order.setTrade(Long.valueOf(trade));
                order.setAmount(Double.valueOf(params.get("total_amount")));
                order.setBuyer(params.get("buyer_id"));
                order.setPaymenttime(TimeFormat.dateTime(params.get("gmt_payment")));
                order.setAmount(Double.valueOf(params.get("buyer_pay_amount")));
                int insert = orderMapper.insert(order);
                System.out.println("===订单输入记录，在案====>"+insert);
                // 更新订单未已支付
//                ordersMapper.updateState(tradeNo, "已支付", gmtPayment, alipayTradeNo);
                DonationRecord donationRecord = donationRecordMapper.findDonationRecordBydonationTrade(Long.valueOf(trade));
                donationRecord.setDonationRecordDenote(1);
                donationRecord.setUpdateTime(gmtPayment);
                donationRecordService.updateDonationRecordBydonationRecordId(donationRecord);
            }
        }
        return "success";
    }


}
