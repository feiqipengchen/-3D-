package org.jeecg.modules.system.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 定制衣服记录
 * @Author: jeecg-boot
 * @Date: 2021-04-14
 * @Version: V1.0
 */
@Data
public class YiFuEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String yifuzhonglei;
    private Integer yifubianhao;
    private String yifuxinghao;
    private Integer jianshu;
    private Integer id;
    private Integer jiage;

    public Integer getJiage() {
        return jiage;
    }

    public void setJiage(Integer jiage) {
        this.jiage = jiage;
    }

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

    public String getYifuxinghao() {
        return yifuxinghao;
    }

    public void setYifuxinghao(String yifuxinghao) {
        this.yifuxinghao = yifuxinghao;
    }

    public Integer getJianshu() {
        return jianshu;
    }

    public void setJianshu(Integer jianshu) {
        this.jianshu = jianshu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "YiFuEntity{" +
                "yifuzhonglei='" + yifuzhonglei + '\'' +
                ", yifubianhao=" + yifubianhao +
                ", yifuxinghao='" + yifuxinghao + '\'' +
                ", jianshu=" + jianshu +
                ", id=" + id +
                ", jiage=" + jiage +
                '}';
    }
}
