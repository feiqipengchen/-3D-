package org.jeecg.modules.system.entity;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 定制衣服记录
 * @Author: jeecg-boot
 * @Date:   2021-04-14
 * @Version: V1.0
 */
@Data
public class CaiZhiEntity implements Serializable {
    private static final long serialVersionUID = 1L;
   private Integer caizhibianhao;
   private Integer jianshu;
   private Integer jiage;

    public Integer getJiage() {
        return jiage;
    }

    public void setJiage(Integer jiage) {
        this.jiage = jiage;
    }

    public Integer getCaizhibianhao() {
        return caizhibianhao;
    }

    public void setCaizhibianhao(Integer caizhibianhao) {
        this.caizhibianhao = caizhibianhao;
    }

    public Integer getJianshu() {
        return jianshu;
    }

    public void setJianshu(Integer jianshu) {
        this.jianshu = jianshu;
    }

    @Override
    public String toString() {
        return "CaiZhiEntity{" +
                "caizhibianhao=" + caizhibianhao +
                ", jianshu=" + jianshu +
                ", jiage=" + jiage +
                '}';
    }
}
