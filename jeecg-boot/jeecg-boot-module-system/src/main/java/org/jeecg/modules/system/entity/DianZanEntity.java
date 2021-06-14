package org.jeecg.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Description: 定制衣服记录
 * @Author: jeecg-boot
 * @Date:   2021-04-14
 * @Version: V1.0
 */
@Data
public class DianZanEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String yifuzhonglei;
    private Integer yifubianhao;
    private Integer caizhibianhao;
    private Integer dianzan;
    private  Integer xiaolian;
    private  Integer zhonglian;
    private  Integer kulian;

    public Integer getDianzan() {
        return dianzan;
    }

    public void setDianzan(Integer dianzan) {
        this.dianzan = dianzan;
    }

    public Integer getXiaolian() {
        return xiaolian;
    }

    public void setXiaolian(Integer xiaolian) {
        this.xiaolian = xiaolian;
    }

    public Integer getZhonglian() {
        return zhonglian;
    }

    public void setZhonglian(Integer zhonglian) {
        this.zhonglian = zhonglian;
    }

    public Integer getKulian() {
        return kulian;
    }

    public void setKulian(Integer kulian) {
        this.kulian = kulian;
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

    public Integer getCaizhibianhao() {
        return caizhibianhao;
    }

    public void setCaizhibianhao(Integer caizhibianhao) {
        this.caizhibianhao = caizhibianhao;
    }

    @Override
    public String toString() {
        return "DianZanEntity{" +
                "yifuzhonglei='" + yifuzhonglei + '\'' +
                ", yifubianhao=" + yifubianhao +
                ", caizhibianhao=" + caizhibianhao +
                ", dianzan=" + dianzan +
                ", xiaolian=" + xiaolian +
                ", zhonglian=" + zhonglian +
                ", kulian=" + kulian +
                '}';
    }
}
