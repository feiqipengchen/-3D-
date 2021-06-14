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
public class YiFuBiaoJiaEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String yifuzhonglei;
    private Integer yifubianhao;
    private Integer caizhibianhao;
    private Integer jiage;

    public String getYifuzhonglei() {
        return yifuzhonglei;
    }

    public void setYifuzhonglei(String yifuzhonglei) {
        this.yifuzhonglei = yifuzhonglei;
    }

    public Integer getYifubianhao() {
        return yifubianhao;
    }

    public void setYifubianhao(Integer yifubianhao) {
        this.yifubianhao = yifubianhao;
    }

    public Integer getCaizhibianhao() {
        return caizhibianhao;
    }

    public void setCaizhibianhao(Integer caizhibianhao) {
        this.caizhibianhao = caizhibianhao;
    }

    public Integer getJiage() {
        return jiage;
    }

    public void setJiage(Integer jiage) {
        this.jiage = jiage;
    }

    @Override
    public String toString() {
        return "YiFuBiaoJiaEntity{" +
                "yifuzhonglei='" + yifuzhonglei + '\'' +
                ", yifubianhao=" + yifubianhao +
                ", caizhibianhao=" + caizhibianhao +
                ", jiage=" + jiage +
                '}';
    }
}
