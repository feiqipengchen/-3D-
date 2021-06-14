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
public class YongHuQianEntity implements Serializable {
    private static final long serialVersionUID = 1L;
   private String yonghu;
   private String mima;
   private Integer qian;

    public String getYonghu() {
        return yonghu;
    }

    public void setYonghu(String yonghu) {
        this.yonghu = yonghu;
    }

    public String getMima() {
        return mima;
    }

    public void setMima(String mima) {
        this.mima = mima;
    }

    public Integer getQian() {
        return qian;
    }

    public void setQian(Integer qian) {
        this.qian = qian;
    }

    @Override
    public String toString() {
        return "YongHuQianEntity{" +
                "yonghu='" + yonghu + '\'' +
                ", mima='" + mima + '\'' +
                ", qian=" + qian +
                '}';
    }
}
