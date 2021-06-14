package org.jeecg.modules.system.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 定制衣服记录
 * @Author: jeecg-boot
 * @Date:   2021-04-14
 * @Version: V1.0
 */
@Data
public class BangDingEntity implements Serializable {
    private static final long serialVersionUID = 1L;
   private String yonghu;
   private String kahao;

    public String getYonghu() {
        return yonghu;
    }

    public void setYonghu(String yonghu) {
        this.yonghu = yonghu;
    }

    public String getKahao() {
        return kahao;
    }

    public void setKahao(String kahao) {
        this.kahao = kahao;
    }

    @Override
    public String toString() {
        return "BangDingEntity{" +
                "yonghu='" + yonghu + '\'' +
                ", kahao='" + kahao + '\'' +
                '}';
    }
}
