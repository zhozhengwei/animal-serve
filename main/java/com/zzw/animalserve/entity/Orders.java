package com.zzw.animalserve.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Order)实体类
 *
 * @author makejava
 * @since 2022-11-18 14:59:29
 */
@TableName(value = "orders")
@Data
public class Orders implements Serializable {
    private static final long serialVersionUID = -19107689725412097L;
    
    private Long id;
    
    private String name;
    
    private String status;
    
    private Long trade;
    
    private Double amount;
    
    private String buyer;
    
    private Date paymenttime;
    
    private Double payamount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTrade() {
        return trade;
    }

    public void setTrade(Long trade) {
        this.trade = trade;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public Date getPaymenttime() {
        return paymenttime;
    }

    public void setPaymenttime(Date paymenttime) {
        this.paymenttime = paymenttime;
    }

    public Double getPayamount() {
        return payamount;
    }

    public void setPayamount(Double payamount) {
        this.payamount = payamount;
    }

}

